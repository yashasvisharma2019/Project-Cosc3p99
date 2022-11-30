

import org.jparsec.OperatorTable;
import org.jparsec.Parser;
import org.jparsec.Scanners;
import org.jparsec.Terminals;

public class RelTermParser {

    // all legal symbols
    private static final String[] symbols = {
            "(", ")", "I", "^", "/\\", ";"
    };

    // generate operator terminals from symbols
    private static final Terminals operators = Terminals.operators(symbols);

    private static RelTermParser parser;

    public static RelTermParser getRelTermParser() {
        if (parser == null) {
            parser = new RelTermParser();
        }
        return parser;
    }

    private RelTermParser() {
    }

    public Parser<RelTerm> getParser() {
        Parser.Reference<RelTerm> ref = org.jparsec.Parser.newReference();
        Parser<RelTerm> atoms = ref.lazy()
                .between(operators.token("("), operators.token(")"))
                .or(operators.token("I").retn(new Ident()))
                .or(Terminals.Identifier.PARSER.map(RelVar::new));
        Parser<RelTerm> result = new OperatorTable<RelTerm>()
                .infixl(operators.token("/\\").retn(Meet::new), 1)
                .infix1(operators.token(";").retn(Comp::new),2)
                .postfix(operators.token("^").retn(Conv::new), 3)
                .build(atoms);
        ref.set(result);
        return result;
    }

    public RelTerm parse(CharSequence source) {// input is CharSequence for example : string.
        return getParser()// use the parser we had implemented up here
                .from(operators.tokenizer()// detective above symbols(line 29)
                        .cast()
                        .or(Terminals.Identifier.TOKENIZER), Scanners.WHITESPACES.skipMany())
                .parse(source);
    }
}

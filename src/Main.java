

import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class Main {

    public static void main(String[] args) {
        RelTermParser parser = RelTermParser.getRelTermParser();
        RelTerm t = parser.parse("Q");
        JFrame frame = new JFrame();
        mxGraph graph = t.computeGraph(20, 20);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        frame.getContentPane().add(graphComponent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setVisible(true);
    }
}

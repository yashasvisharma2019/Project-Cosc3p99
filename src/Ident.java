


import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

public class Ident extends RelTerm
{
    @Override
    public String toStringPrec(int precedence)
    {
        return "I";
    }

    @Override
    protected void computeBounds(boolean spaceAtmeet) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void computeGraph(Object parent, mxCell source, mxCell target, boolean forward, mxGraph graph, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 
} 
    




import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStyleRegistry;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class RelVar extends RelTerm {
    /**
     * Name of the Variable
     */
    private final String name;

    public RelVar(String name) {
        this.name = name;
    }

    @Override
    public String toStringPrec(int precedence) {
        return this.name;
    }

    @Override
    public void computeBounds(boolean spaceAtMeet) {
        bounds = new Dimension(80, 50);
    }

    @Override
    protected void computeGraph(Object parent, mxCell source, mxCell target, boolean forward, mxGraph graph, int x,
            int y) {
        mxCell from = forward ? source : target;
        mxCell to = forward ? target : source;
        mxPoint entry = forward ? new mxPoint(x, y + bounds.height / 2)
                : new mxPoint(x + bounds.width, y + bounds.height / 2);
        mxPoint exit = forward ? new mxPoint(x + bounds.width, y + bounds.height / 2)
                : new mxPoint(x, y + bounds.height / 2);
        List<mxPoint> interPoints = new ArrayList();
        if (from.getGeometry().getX() != entry.getX() || from.getGeometry().getY() != entry.getY()) { // if the source
                                                                                                      // is different
                                                                                                      // from the enty
                                                                                                      // point, we have
                                                                                                      // an intermediate
                                                                                                      // point.
            interPoints.add(entry);
        }
        if (to.getGeometry().getX() != exit.getX() || to.getGeometry().getY() != exit.getY()) { // if the target is
                                                                                                // different from the
                                                                                                // exit point, we have
                                                                                                // an intermediate
                                                                                                // point.
            interPoints.add(exit);
        }
        if (interPoints.isEmpty()) {
            graph.insertEdge(parent, null, name, from, to);
        } else {
            String fctName = name + System.nanoTime(); // create a unique name for the function.
            mxEdgeStyle.mxEdgeStyleFunction styleFct = new mxEdgeStyle.mxEdgeStyleFunction() {
                @Override
                public void apply(mxCellState state, mxCellState source, mxCellState target, List<mxPoint> points,
                        List<mxPoint> result) {
                    result.addAll(interPoints);
                }
            };
            mxStyleRegistry.putValue(fctName, styleFct);
            graph.insertEdge(parent, null, name, from, to, "defaultEdge;edgeStyle=" + fctName);
        }
    }
}

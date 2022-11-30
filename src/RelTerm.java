

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxHtmlColor;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

public abstract class RelTerm {
    protected Dimension bounds;

    protected abstract void computeBounds(boolean spaceAtMeet);

    public Dimension getBounds() {
        return bounds;
    }

    public mxGraph computeGraph(int x, int y) {
        mxGraph graph = new mxGraph();
        graph.setAllowLoops(true);

        Object parent = graph.getDefaultParent();

        mxStylesheet stylesheet = graph.getStylesheet();

        Map<String, Object> vertexStyle = stylesheet.getDefaultVertexStyle();
        vertexStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        vertexStyle.put(mxConstants.STYLE_STROKECOLOR, mxHtmlColor.hexString(Color.BLACK));
        vertexStyle.put(mxConstants.STYLE_FILLCOLOR, mxHtmlColor.hexString(Color.BLACK));

        Map<String, Object> edgeStyle = stylesheet.getDefaultEdgeStyle();
        edgeStyle.put(mxConstants.STYLE_FONTCOLOR, mxHtmlColor.hexString(Color.BLACK));
        edgeStyle.put(mxConstants.STYLE_STROKECOLOR, mxHtmlColor.hexString(Color.BLACK));
        edgeStyle.put(mxConstants.STYLE_SPACING_BOTTOM, 5);

        computeBounds(true);

        graph.getModel().beginUpdate();
        try {
            mxCell v1 = (mxCell) graph.insertVertex(parent, null, null, x, y + bounds.height / 2, 6, 6);
            mxCell v2 = (mxCell) graph.insertVertex(parent, null, null, x + bounds.width, y + bounds.height / 2, 6, 6);
            computeGraph(parent, v1, v2, true, graph, x, y);
        } finally {
            graph.getModel().endUpdate();
        }
        return graph;
    }

    protected abstract void computeGraph(Object parent, mxCell source, mxCell target, boolean forward, mxGraph graph,
            int x, int y);

    /**
     * Converts an Object int a String by respecting precedence of operators
     *
     * @param precedence precedence
     *
     * @return String representation of this Object
     */
    public abstract String toStringPrec(int precedence);

    @Override
    public String toString() {
        return toStringPrec(0);
    }
}
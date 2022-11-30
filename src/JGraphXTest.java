

import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxHtmlColor;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxEdgeStyle.mxEdgeStyleFunction;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStyleRegistry;
import com.mxgraph.view.mxStylesheet;
import java.awt.Color;
import java.util.List;
import java.util.Map;

public class JGraphXTest extends JFrame {

    public JGraphXTest() {
        super("Hello, World!");

        mxGraph graph = new mxGraph();
        graph.setAllowLoops(true);
               
        Object parent = graph.getDefaultParent();
        
        mxStylesheet stylesheet = graph.getStylesheet();
                
        Map<String,Object> vertexStyle = stylesheet.getDefaultVertexStyle();
        vertexStyle.put(mxConstants.STYLE_SHAPE,mxConstants.SHAPE_ELLIPSE);
        vertexStyle.put(mxConstants.STYLE_STROKECOLOR,mxHtmlColor.hexString(Color.BLACK));
        vertexStyle.put(mxConstants.STYLE_FILLCOLOR,mxHtmlColor.hexString(Color.BLACK));
        
        Map<String,Object> edgeStyle = stylesheet.getDefaultEdgeStyle();
        edgeStyle.put(mxConstants.STYLE_FONTCOLOR,mxHtmlColor.hexString(Color.BLACK));
        edgeStyle.put(mxConstants.STYLE_STROKECOLOR,mxHtmlColor.hexString(Color.BLACK));
        edgeStyle.put(mxConstants.STYLE_SPACING_BOTTOM,5);
        
        mxEdgeStyleFunction lowerArrow = new mxEdgeStyleFunction() {
            @Override
            public void apply(mxCellState state, mxCellState source, mxCellState target, List<mxPoint> points, List<mxPoint> result) {
                result.add(new mxPoint(source.getX()+3,source.getY()+20));
                result.add(new mxPoint(target.getX()+3,target.getY()+20));
            } 
        }; 
        mxEdgeStyleFunction upperArrow = new mxEdgeStyleFunction() {
            @Override
            public void apply(mxCellState state, mxCellState source, mxCellState target, List<mxPoint> points, List<mxPoint> result) {
                result.add(new mxPoint(source.getX()+3,source.getY()-15));
                result.add(new mxPoint(target.getX()+3,target.getY()-15));
            } 
        }; 
        mxStyleRegistry.putValue("lowerArrow",lowerArrow);
        mxStyleRegistry.putValue("upperArrow",upperArrow);
        
        graph.getModel().beginUpdate();
        try
        {
            Object v1 = graph.insertVertex(parent,null,null,20,50,6,6);
            Object v2 = graph.insertVertex(parent,null,null,100,50,6,6);
            Object v3 = graph.insertVertex(parent,null,null,180,50,6,6);
            Object v4 = graph.insertVertex(parent,null,null,260,50,6,6);
            graph.insertEdge(parent,null,"Q",v1,v2,"defaultEdge;edgeStyle=lowerArrow");
            graph.insertEdge(parent,null,"R",v1,v2,"defaultEdge;edgeStyle=upperArrow");
            graph.insertEdge(parent,null,"R",v2,v3);
            graph.insertEdge(parent,null,"S1",v3,v4,"defaultEdge;edgeStyle=lowerArrow");
            graph.insertEdge(parent,null,"S2",v3,v4);
            graph.insertEdge(parent,null,"S3",v3,v4,"defaultEdge;edgeStyle=upperArrow");
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    public static void main(String[] args) {
        JGraphXTest frame = new JGraphXTest();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,320);
        frame.setVisible(true);
    }  
}

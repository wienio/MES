package local;

/**
 * Created by Wienio on 2018-01-03.
 */
public class LocalSurface {

    private LocalNode[] nodes;
    private double[][] shapesFunc;

    public LocalSurface(LocalNode node1, LocalNode node2) {
        nodes = new LocalNode[2];
        nodes[0] = node1;
        nodes[1] = node2;
        shapesFunc = new double[2][4];
    }

    public LocalNode[] getNodes() {
        return nodes;
    }

    public void setNodes(LocalNode[] nodes) {
        this.nodes = nodes;
    }

    public double[][] getShapesFunc() {
        return shapesFunc;
    }

    public void setShapesFunc(double[][] shapesFunc) {
        this.shapesFunc = shapesFunc;
    }

}

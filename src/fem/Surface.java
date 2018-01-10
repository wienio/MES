package fem;

/**
 * Created by Wienio on 2018-01-03.
 */
public class Surface {

    private Node[] nodes;

    public Surface(Node node1, Node node2) {
        nodes = new Node[2];
        nodes[0] = node1;
        nodes[1] = node2;
    }

    public Node[] getNodes() {
        return nodes;
    }

}

package fem;

import main.Node;

/**
 * Created by Wienio on 2018-01-03.
 */
public class Surface {

    private Node[] ID;

    public Surface(Node node1, Node node2) {
        ID = new Node[2];
        ID[0] = node1;
        ID[1] = node2;
    }

    public Node[] getID() {
        return ID;
    }

    public void setID(Node[] ID) {
        this.ID = ID;
    }

}

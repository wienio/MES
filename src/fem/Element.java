package fem;

/**
 * Created by Wienio on 2018-01-03.
 */
public class Element {

    private final int borderAmount = 4;

    private Node[] node;
    private Surface[] surface;

    private int surfaceAround;
    private int[] surfaceNumber;
    private int[] globalNodeId;

    public Element(int i, int j, Node[] node, GlobalData globalData) {
        this.node = new Node[borderAmount];
        this.surface = new Surface[borderAmount];
        this.globalNodeId = new int[borderAmount];

        // Współrzędne wezłów w elemencie
        this.node[0] = node[0];
        this.node[1] = node[1];
        this.node[2] = node[2];
        this.node[3] = node[3];

        // id węzłów w elemencie
        globalNodeId[0] = globalData.getnH() * i + j;
        globalNodeId[1] = globalData.getnH() * (i + 1) + j;
        globalNodeId[2] = globalData.getnH() * (i + 1) + (j + 1);
        globalNodeId[3] = globalData.getnH() * i + (j + 1);

        // węzły na powierzchni
        surface[0] = new Surface(this.node[3], this.node[0]);
        surface[1] = new Surface(this.node[0], this.node[1]);
        surface[2] = new Surface(this.node[1], this.node[2]);
        surface[3] = new Surface(this.node[2], this.node[3]);

        surfaceAround = 0;
        for (int k = 0; k < borderAmount; ++k) {
            if (surface[k].getNodes()[0].getStatus() == 1 && surface[k].getNodes()[1].getStatus() == 1) {
                surfaceAround++;
            }
        }
        surfaceNumber = new int[surfaceAround];

        int counter = 0;
        for (int k = 0; k < borderAmount; ++k) {
            if(surface[k].getNodes()[0].getStatus() == 1 && surface[k].getNodes()[1].getStatus() == 1) {
                surfaceNumber[counter] = k;
                counter++;
            }
        }
    }

    public Node[] getNode() {
        return node;
    }

    public void setNode(Node[] node) {
        this.node = node;
    }

    public Surface[] getSurface() {
        return surface;
    }

    public void setSurface(Surface[] surface) {
        this.surface = surface;
    }

    public int getSurfaceAround() {
        return surfaceAround;
    }

    public void setSurfaceAround(int surfaceAround) {
        this.surfaceAround = surfaceAround;
    }

    public int[] getSurfaceNumber() {
        return surfaceNumber;
    }

    public void setSurfaceNumber(int[] surfaceNumber) {
        this.surfaceNumber = surfaceNumber;
    }

    public int[] getGlobalNodeId() {
        return globalNodeId;
    }

    public void setGlobalNodeId(int[] globalNodeId) {
        this.globalNodeId = globalNodeId;
    }

}

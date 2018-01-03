package fem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wienio on 2017-10-28.
 */
public class Grid {

    private final int borders = 4;

    private Node[] nodes;
    private Element[] elements;
    private GlobalData globalData;

    public Grid(GlobalData globalData) {
        this.globalData = globalData;

        nodes = new Node[globalData.getNh()];
        elements = new Element[globalData.getNe()];

        int counter = 0;
        for (int i = 0; i < globalData.getnB(); ++i) {
            for (int j = 0; j < globalData.getnH(); ++j) {
                nodes[counter] = new Node(i * globalData.getB() / ((globalData.getnB() - 1)), j * globalData.getH() / (globalData.getnH() - 1), globalData);
                ++counter;
            }
        }

        counter = 0;
        for (int i = 0; i < globalData.getnB() - 1; ++i) {
            for (int j = 0; j < globalData.getnH() - 1; ++j) {
                List<Node> nodeList = new ArrayList<>();
                nodeList.add(nodes[globalData.getnH() * i + j]);
                nodeList.add(nodes[globalData.getnH() * (i + 1) + j]);
                nodeList.add(nodes[globalData.getnH() * i + j + 1]);
                nodeList.add(nodes[globalData.getnH() * (i + 1) + j + 1]);

                elements[counter] = new Element(i, j, nodeList.toArray(new Node[nodeList.size()]), globalData);
                ++counter;
            }
        }
    }

    public void printNodes() {
        for (int i = 0; i < globalData.getNh(); ++i) {
            System.out.println("i: " + i + "\nStatus: " + nodes[i].getStatus() + "\n [ " + nodes[i].getX() + ", " + nodes[i].getY() + " ]");
        }
    }

    public void printElements(int i) {
        System.out.println("Element " + i);
        for (int j = 0; j < borders; ++j) {
            System.out.println("{\nId: " + j + "\nGlobal id: " + elements[i].getGlobalNodeId()[i] + "\nStatus: " + elements[i].getNode()[j].getStatus() + "\n[ " + elements[i].getNode()[j].getX() + ", " + elements[i].getNode()[j].getY() + " ]\n}");
        }
    }

}

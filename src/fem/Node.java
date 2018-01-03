package fem;

/**
 * Created by Wienio on 2018-01-03.
 */
public class Node {

    private double x, y, t;
    private int status;

    public Node(double x, double y, GlobalData globalData) {
        this.x = x;
        this.y = y;
        this.t = globalData.getTemperatureStart();

        if (x == 0 || y == 0 || x == globalData.getB() || y == globalData.getH()) {
            this.status = 1;
        } else {
            this.status = 0;
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}

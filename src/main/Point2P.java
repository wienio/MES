package main;


/**
 * Created by Wienio on 2017-11-03.
 */
public class Point2P {

    private double x, y, weight1, weight2;

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

    public double getWeight1() {
        return weight1;
    }

    public void setWeight1(double weight1) {
        this.weight1 = weight1;
    }

    public double getWeight2() {
        return weight2;
    }

    public void setWeight2(double weight2) {
        this.weight2 = weight2;
    }

    public Point2P(double x, double y, double weight1, double weight2) {
        this.x = x;
        this.y = y;
        this.weight1 = weight1;
        this.weight2 = weight2;
    }

}

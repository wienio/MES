package main;

/**
 * Created by Wienio on 2017-11-03.
 */
public class Point3P {

    private double x, y, z, weight1, weight2, weight3;

    public Point3P(double x, double y, double z, double weight1, double weight2, double weight3) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.weight1 = weight1;
        this.weight2 = weight2;
        this.weight3 = weight3;
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
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

    public double getWeight3() {
        return weight3;
    }

    public void setWeight3(double weight3) {
        this.weight3 = weight3;
    }

}

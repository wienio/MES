package fem;

import local.LocalElement;

/**
 * Created by Wienio on 2018-01-04.
 */
public class Data {

    private LocalElement localElement = LocalElement.getInstance();
    private double[][] H_current;
    private double[] P_current;
    private double[][] H_global;
    private double[] P_global;

    public Data(int number, int nh) {
        H_current = new double[number][number];
        P_current = new double[number];
        H_global = new double[nh][nh];
        P_global = new double[nh];
    }

    public LocalElement getLocalElement() {
        return localElement;
    }

    public double[][] getH_current() {
        return H_current;
    }

    public double[] getP_current() {
        return P_current;
    }

    public double[][] getH_global() {
        return H_global;
    }

    public double[] getP_global() {
        return P_global;
    }

}

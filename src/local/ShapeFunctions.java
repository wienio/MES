package local;

/**
 * Created by Wienio on 2018-01-03.
 */
public class ShapeFunctions {

    public static double N1(double ksi, double eta) {
        return 0.25 * (1 - ksi) * (1 - eta);
    }

    public static double N2(double ksi, double eta) {
        return 0.25 * (1 + ksi) * (1 - eta);
    }

    public static double N3(double ksi, double eta) {
        return 0.25 * (1 + ksi) * (1 + eta);
    }

    public static double N4(double ksi, double eta) {
        return 0.25 * (1 - ksi) * (1 + eta);
    }

    public static double dN1_Ksi(double ksi) {
        return -0.25 * (1 - ksi);
    }

    public static double dN2_Ksi(double ksi) {
        return 0.25 * (1 - ksi);
    }

    public static double dN3_Ksi(double ksi) {
        return 0.25 * (1 + ksi);
    }

    public static double dN4_Ksi(double ksi) {
        return -0.25 * (1 + ksi);
    }

    public static double dN1_Eta(double eta) {
        return -0.25 * (1 - eta);
    }

    public static double dN2_Eta(double eta) {
        return -0.25 * (1 + eta);
    }

    public static double dN3_Eta(double eta) {
        return 0.25 * (1 + eta);
    }

    public static double dN4_Eta(double eta) {
        return 0.25 * (1 - eta);
    }

}

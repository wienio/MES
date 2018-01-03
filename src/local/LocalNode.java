package local;

/**
 * Created by Wienio on 2018-01-03.
 */
public class LocalNode {

    private double ksi, eta;

    public LocalNode(double ksi, double eta) {
        this.ksi = ksi;
        this.eta = eta;
    }

    public double getKsi() {
        return ksi;
    }

    public void setKsi(double ksi) {
        this.ksi = ksi;
    }

    public double getEta() {
        return eta;
    }

    public void setEta(double eta) {
        this.eta = eta;
    }

}

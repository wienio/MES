package fem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Wienio on 2017-10-28.
 */

@XmlRootElement
public class GlobalData {

    // TODO dokonczyc ta klase

    private double H, B, temperatureStart, time, deltaTime, temperature, alfa, cw, k, density;
    private int nH, nB;
    private int ne, nh; // liczba elementów i liczba węzłów

    public double getH() {
        return H;
    }

    @XmlElement(name = "H")
    public void setH(double h) {
        H = h;
    }

    public double getB() {
        return B;
    }

    @XmlElement(name = "B")
    public void setB(double b) {
        B = b;
    }

    public int getnH() {
        return nH;
    }

    @XmlElement
    public void setnH(int nH) {
        this.nH = nH;
    }

    public int getnB() {
        return nB;
    }

    @XmlElement
    public void setnB(int nB) {
        this.nB = nB;
    }

    public double getTemperatureStart() {
        return temperatureStart;
    }

    @XmlElement
    public void setTemperatureStart(double temperatureStart) {
        this.temperatureStart = temperatureStart;
    }

    public double getTime() {
        return time;
    }

    @XmlElement
    public void setTime(double time) {
        this.time = time;
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    @XmlElement
    public void setDeltaTime(double deltaTime) {
        this.deltaTime = deltaTime;
    }

    public double getTemperature() {
        return temperature;
    }

    @XmlElement
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getAlfa() {
        return alfa;
    }

    @XmlElement
    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }

    public double getCw() {
        return cw;
    }

    @XmlElement
    public void setCw(double cw) {
        this.cw = cw;
    }

    public double getK() {
        return k;
    }

    @XmlElement
    public void setK(double k) {
        this.k = k;
    }

    public double getDensity() {
        return density;
    }

    @XmlElement
    public void setDensity(double density) {
        this.density = density;
    }

    public int getNe() {
        return ne;
    }

    public void setNe(int ne) {
        this.ne = ne;
    }

    public int getNh() {
        return nh;
    }

    public void setNh(int nh) {
        this.nh = nh;
    }

}

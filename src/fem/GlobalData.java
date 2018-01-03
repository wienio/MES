package fem;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

/**
 * Created by Wienio on 2017-10-28.
 */

@XmlRootElement
public class GlobalData {

    private static Logger log = Logger.getLogger(GlobalData.class);

    private double H, B, temperatureStart, time, deltaTime, temperature, alfa, cw, k, density;
    private int nH, nB;
    private int ne, nh; // liczba elementów i liczba węzłów

    public GlobalData readConfiguration() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GlobalData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GlobalData data = (GlobalData) jaxbUnmarshaller.unmarshal(new File("C:\\Users\\Wienio\\IdeaProjects\\MES\\resources\\data.xml"));
            data.setNe((data.getnH() - 1) * (data.getnB() - 1));
            data.setNh(data.getnH() * data.getnB());


            // TODO dokonczyc
            return data;
        } catch (JAXBException e) {
            log.error("Can't read data,xml file with Global Data", e);
        }
        return null;
    }

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

package main;

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

    private static final Logger log = Logger.getLogger(GlobalData.class);

    private double H, B, nH, nB, nh, ne;

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

    public double getnH() {
        return nH;
    }

    @XmlElement(name = "nH")
    public void setnH(double nH) {
        this.nH = nH;
    }

    public double getnB() {
        return nB;
    }

    @XmlElement(name = "nB")
    public void setnB(double nB) {
        this.nB = nB;
    }

    public double getNh() {
        return nh;
    }

    public void setNh(double nh) {
        this.nh = nh;
    }

    public double getNe() {
        return ne;
    }

    public void setNe(double ne) {
        this.ne = ne;
    }

    public GlobalData readConfig() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GlobalData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GlobalData data = (GlobalData) jaxbUnmarshaller.unmarshal(new File("C:\\Users\\Wienio\\IdeaProjects\\MES\\resources\\data.xml"));
            data.setNe((data.getnH() - 1) * (data.getnB() - 1));
            data.setNh(data.getnH() * data.getnB());
            return data;
        } catch (JAXBException e) {
            log.error("Some error occured when unmarshall xml file!", e);
        }
        return null;
    }

}

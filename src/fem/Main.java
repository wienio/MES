package fem;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Wienio on 2018-01-03.
 */
public class Main {

    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        GlobalData data = readConfiguration();
    }

    private static GlobalData readConfiguration() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GlobalData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GlobalData data = (GlobalData) jaxbUnmarshaller.unmarshal(new File("./resources/data.xml"));
            data.setNe((data.getnH() - 1) * (data.getnB() - 1));
            data.setNh(data.getnH() * data.getnB());


            // TODO dokonczyc
            return data;
        } catch (JAXBException e) {
            log.error("Can't read data,xml file with Global Data", e);
        }
        return null;
    }

}

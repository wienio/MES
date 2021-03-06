package fem;

import linear.LinearEquations;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Wienio on 2018-01-03.
 */
public class Main {

    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        GlobalData data = readConfiguration();
        Grid grid = new Grid(data);
        for (double i = 0; i <= data.getTime(); i += data.getDeltaTime()) {
            data.compute(grid);
            LinearEquations linearEquations = new LinearEquations(data.getData().getH_global(), data.getData().getP_global(), data.getNh());
            double[] result = linearEquations.gaussEliminationMethod();
            for (int j = 0; j < data.getNh(); ++j) {
                grid.getNodes()[j].setT(result[j]);
            }
            System.out.println("Step: " + i +  " / " + data.getTime());
            prettyPrint(result, i + 45);
        }
    }

    private static GlobalData readConfiguration() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GlobalData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GlobalData data = (GlobalData) jaxbUnmarshaller.unmarshal(new File("./resources/data.xml"));
            data.setNe((data.getnH() - 1) * (data.getnB() - 1));
            data.setNh(data.getnH() * data.getnB());
            Data global = new Data(4, data.getNh());
            data.setData(global);

            return data;
        } catch (JAXBException e) {
            log.error("Can't read data,xml file with Global Data", e);
        }
        return null;
    }

    private static void prettyPrint(double[] tab, double step) throws IOException {
        FileWriter fileWriter = new FileWriter("./wyniki/result-step" + String.valueOf(step) + "[s].txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.append("Step: " + step);
        for (int i = 0; i < tab.length; ++i) {
            if (i % 31 == 0) {
                printWriter.append("\n");
                System.out.println();
            }
            printWriter.write(tab[i] + " ");
            System.out.print(tab[i] + "   ");
        }
        printWriter.close();
        fileWriter.close();
        System.out.println();
    }

}

package fem;

/**
 * Created by Wienio on 2018-01-03.
 */
public class Main {

    public static void main(String[] args) {
        GlobalData data = new GlobalData();
        data = data.readConfiguration();
        System.out.println(data.getB());
    }

}

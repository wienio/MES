package local;

/**
 * Created by Wienio on 2018-01-03.
 */
public class LocalElement {

    private final int border = 4;

    private double[][] dN_Ksi;
    private double[][] dN_Eta;
    private double[][] shapesFunction;

    private static LocalElement instance = null;  // Singleton pattern

    private LocalElement() {
        dN_Eta = new double[border][border];
        dN_Ksi = new double[border][border];
        shapesFunction = new double[border][border];

        for (int i = 0; i < border; ++i) {
            shapesFunction[i][0] = ShapeFunctions.N1(GaussIntegralCoords.gaussNodesCoords[i].getKsi(), GaussIntegralCoords.gaussNodesCoords[i].getEta());
            shapesFunction[i][1] = ShapeFunctions.N2(GaussIntegralCoords.gaussNodesCoords[i].getKsi(), GaussIntegralCoords.gaussNodesCoords[i].getEta());
            shapesFunction[i][2] = ShapeFunctions.N3(GaussIntegralCoords.gaussNodesCoords[i].getKsi(), GaussIntegralCoords.gaussNodesCoords[i].getEta());
            shapesFunction[i][3] = ShapeFunctions.N4(GaussIntegralCoords.gaussNodesCoords[i].getKsi(), GaussIntegralCoords.gaussNodesCoords[i].getEta());

            dN_Ksi[i][0] = ShapeFunctions.dN1_Ksi(GaussIntegralCoords.gaussNodesCoords[i].getEta(), GaussIntegralCoords.gaussNodesCoords[i].getEta());
            dN_Ksi[i][1] = ShapeFunctions.dN2_Ksi(GaussIntegralCoords.gaussNodesCoords[i].getEta(), GaussIntegralCoords.gaussNodesCoords[i].getEta());
            dN_Ksi[i][2] = ShapeFunctions.dN3_Ksi(GaussIntegralCoords.gaussNodesCoords[i].getEta(), GaussIntegralCoords.gaussNodesCoords[i].getEta());
            dN_Ksi[i][3] = ShapeFunctions.dN4_Ksi(GaussIntegralCoords.gaussNodesCoords[i].getEta(), GaussIntegralCoords.gaussNodesCoords[i].getEta());

            dN_Eta[i][0] = ShapeFunctions.dN1_Eta(GaussIntegralCoords.gaussNodesCoords[i].getKsi(), GaussIntegralCoords.gaussNodesCoords[i].getEta());
            dN_Eta[i][1] = ShapeFunctions.dN2_Eta(GaussIntegralCoords.gaussNodesCoords[i].getKsi(), GaussIntegralCoords.gaussNodesCoords[i].getEta());
            dN_Eta[i][2] = ShapeFunctions.dN3_Eta(GaussIntegralCoords.gaussNodesCoords[i].getKsi(), GaussIntegralCoords.gaussNodesCoords[i].getEta());
            dN_Eta[i][3] = ShapeFunctions.dN4_Eta(GaussIntegralCoords.gaussNodesCoords[i].getKsi(), GaussIntegralCoords.gaussNodesCoords[i].getEta());

        }

        for (int i = 0; i < border; ++i) {
            for (int j = 0; j < border / 2; ++j) {
                GaussIntegralCoords.gaussSurfaceCoords[i].getShapesFunc()[j][0] = ShapeFunctions.N1(GaussIntegralCoords.gaussSurfaceCoords[i].getNodes()[j].getKsi(), GaussIntegralCoords.gaussSurfaceCoords[i].getNodes()[j].getEta());
                GaussIntegralCoords.gaussSurfaceCoords[i].getShapesFunc()[j][1] = ShapeFunctions.N1(GaussIntegralCoords.gaussSurfaceCoords[i].getNodes()[j].getKsi(), GaussIntegralCoords.gaussSurfaceCoords[i].getNodes()[j].getEta());
                GaussIntegralCoords.gaussSurfaceCoords[i].getShapesFunc()[j][2] = ShapeFunctions.N1(GaussIntegralCoords.gaussSurfaceCoords[i].getNodes()[j].getKsi(), GaussIntegralCoords.gaussSurfaceCoords[i].getNodes()[j].getEta());
                GaussIntegralCoords.gaussSurfaceCoords[i].getShapesFunc()[j][3] = ShapeFunctions.N1(GaussIntegralCoords.gaussSurfaceCoords[i].getNodes()[j].getKsi(), GaussIntegralCoords.gaussSurfaceCoords[i].getNodes()[j].getEta());
            }
        }
    }

    public static LocalElement getInstance() {
        if (instance == null) instance = new LocalElement();
        return instance;
    }

    public double[][] getdN_Ksi() {
        return dN_Ksi;
    }

    public double[][] getdN_Eta() {
        return dN_Eta;
    }

    public double[][] getShapesFunction() {
        return shapesFunction;
    }

}

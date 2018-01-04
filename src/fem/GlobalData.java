package fem;

import jacoby.Jacoby;
import local.GaussIntegralCoords;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Wienio on 2017-10-28.
 */

@XmlRootElement
public class GlobalData {

    private double H, B, temperatureStart, time, deltaTime, temperature, alfa, cw, k, density;
    private int nH, nB;
    private int ne, nh; // liczba elementów i liczba węzłów
    private Data data;

    public void compute(Grid grid) {

        fill2Dtab(0, data.getH_global());
        fill1Dtab(0, data.getP_global());

        double[] dndx = new double[4];
        double[] dndy = new double[4];
        double[] x = new double[4];
        double[] y = new double[4];
        double[] temp0 = new double[4];

        double det = 0;
        for (int element = 0; element < ne; ++element) {
            // fill
            fill2Dtab(0, data.getH_current());
            fill1Dtab(0, data.getP_current());

            int id = 0;
            for (int i = 0; i < 4; ++i) {
                id = grid.getElements()[element].getGlobalNodeId()[i];
                x[i] = grid.getNodes()[id].getX();
                y[i] = grid.getNodes()[id].getY();
                temp0[i] = grid.getNodes()[id].getT();
            }

            double t0 = 0;
            for (int point = 0; point < 4; ++point) {
                Jacoby jacoby = new Jacoby(point, x, y);
                t0 = 0;
                for (int i = 0; i < 4; ++i) {
                    dndx[i] = 1 / jacoby.getDet() * (jacoby.getInvertedMatrix()[0][0] * data.getLocalElement().getdN_Ksi()[point][i] + jacoby.getInvertedMatrix()[0][1] * data.getLocalElement().getdN_Eta()[point][i]);
                    dndy[i] = 1 / jacoby.getDet() * (jacoby.getInvertedMatrix()[1][0] * data.getLocalElement().getdN_Ksi()[point][i] + jacoby.getInvertedMatrix()[1][1] * data.getLocalElement().getdN_Eta()[point][i]);

                    t0 += temp0[i] * data.getLocalElement().getShapesFunction()[point][i];
                }

                det = Math.abs(jacoby.getDet());
                for (int i = 0; i < 4; ++i) {
                    for (int j = 0; j < 4; ++j) {
                        double cij = cw * density * data.getLocalElement().getShapesFunction()[point][i] * data.getLocalElement().getShapesFunction()[point][j] * det;
                        data.getH_current()[i][j] += k * (dndx[i] * dndx[j] + dndy[i] * dndy[j]) * det + cij / deltaTime;
                        data.getP_current()[i] += cij / deltaTime * t0;
                    }
                }
            }

            for (int i = 0; i < grid.getElements()[element].getSurfaceAround(); ++i) {
                id = grid.getElements()[element].getSurfaceNumber()[i];
                switch (id) {
                    case 0:
                        det = Math.sqrt(Math.pow(grid.getElements()[element].getNode()[3].getX() - grid.getElements()[element].getNode()[0].getX(), 2) + Math.pow(grid.getElements()[element].getNode()[3].getY() - grid.getElements()[element].getNode()[0].getY(), 2)) / 2.0;
                        break;
                    case 1:
                        det = Math.sqrt(Math.pow(grid.getElements()[element].getNode()[0].getX() - grid.getElements()[element].getNode()[1].getX(), 2) + Math.pow(grid.getElements()[element].getNode()[0].getY() - grid.getElements()[element].getNode()[1].getY(), 2)) / 2.0;
                        break;
                    case 2:
                        det = Math.sqrt(Math.pow(grid.getElements()[element].getNode()[1].getX() - grid.getElements()[element].getNode()[2].getX(), 2) + Math.pow(grid.getElements()[element].getNode()[1].getY() - grid.getElements()[element].getNode()[2].getY(), 2)) / 2.0;
                        break;
                    case 3:
                        det = Math.sqrt(Math.pow(grid.getElements()[element].getNode()[2].getX() - grid.getElements()[element].getNode()[3].getX(), 2) + Math.pow(grid.getElements()[element].getNode()[2].getY() - grid.getElements()[element].getNode()[3].getY(), 2)) / 2.0;
                        break;
                }

                for (int p = 0; p < 2; ++p) {
                    for (int j = 0; j < 4; ++j) {
                        for (int k = 0; k < 4; ++k) {
                            data.getH_current()[j][k] += alfa * GaussIntegralCoords.gaussSurfaceCoords[id].getShapesFunc()[p][j] * GaussIntegralCoords.gaussSurfaceCoords[id].getShapesFunc()[p][k] * det;
                        }
                        data.getP_current()[j] += alfa * temperature * GaussIntegralCoords.gaussSurfaceCoords[id].getShapesFunc()[p][j] * det;
                    }
                }
            }

            // agregation
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    data.getH_global()[grid.getElements()[element].getGlobalNodeId()[i]][grid.getElements()[element].getGlobalNodeId()[j]] += data.getH_current()[i][j];
                }
                data.getP_global()[grid.getElements()[element].getGlobalNodeId()[i]] += data.getP_current()[i];
            }
        }
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private void fill2Dtab(double value, double[][] A) {
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[i].length; ++j) {
                A[i][j] = value;
            }
        }
    }

    private void fill1Dtab(double value, double[] A) {
        for (int i = 0; i < A.length; ++i) {
            A[i] = value;
        }
    }

}

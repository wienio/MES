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

    private double H, B, temperatureStart, time, deltaTime, temperatureleft, temperatureright, alfaleft, alfaright, cwblock, cwmineral, cwstyrofoam, kblock, kmineral, kstyrofoam, densityblock, densitymineral, densitystyrofoam;
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
        double lastElement = -999;
        for (int element = 0; element < ne; ++element) {
            // fill
            fill2Dtab(0, data.getH_current());
            fill1Dtab(0, data.getP_current());

            int id = 0;
            for (int i = 0; i < 4; ++i) {
                id = grid.getElements()[element].getGlobalNodeId()[i];
                x[i] = grid.getNodes()[id].getX(); // interpolation for coordinates {N}^T{x}
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

                double cw, density, k;
                if (element < 600) {
                    cw = cwblock;
                    density = densityblock;
                    k = kblock;
                } else if (element < 750) {
                    cw = cwmineral;
                    density = densitymineral;
                    k = kmineral;
                } else {
                    cw = cwstyrofoam;
                    density = densitystyrofoam;
                    k = kstyrofoam;
                }
                det = Math.abs(jacoby.getDet()); // volume integral
                for (int i = 0; i < 4; ++i) {
                    for (int j = 0; j < 4; ++j) {
                        double cij = cw * density * data.getLocalElement().getShapesFunction()[point][i] * data.getLocalElement().getShapesFunction()[point][j] * det;
                        data.getH_current()[i][j] += k * (dndx[i] * dndx[j] + dndy[i] * dndy[j]) * det + cij / deltaTime;
                        data.getP_current()[i] += cij / deltaTime * t0;
                    }
                }
            }

            for (int i = 0; i < grid.getElements()[element].getSurfaceAround(); ++i) {
                if (element == 0 || element == 29 || element == 899 || element == 870) {
                    ++i;
                }
                if (grid.getElements()[element].getSurfaceAround() == 1 && element % 29 == 0) {
                    lastElement = element;
                    break;
                }
                if ((lastElement + 1) == element) break;

                id = grid.getElements()[element].getSurfaceNumber()[i];

                if (id == 0) {
                    det = Math.sqrt(Math.pow(grid.getElements()[element].getNode()[id + 3].getX() - grid.getElements()[element].getNode()[id].getX(), 2) + Math.pow(grid.getElements()[element].getNode()[id + 3].getY() - grid.getElements()[element].getNode()[id].getY(), 2)) / 2.0;
                } else {
                    det = Math.sqrt(Math.pow(grid.getElements()[element].getNode()[id - 1].getX() - grid.getElements()[element].getNode()[id].getX(), 2) + Math.pow(grid.getElements()[element].getNode()[id - 1].getY() - grid.getElements()[element].getNode()[id].getY(), 2)) / 2.0;
                }

                double alfa = element < 40 ? alfaleft : alfaright;
                double temperature = element < 40 ? temperatureleft : temperatureright;
                for (int p = 0; p < 2; ++p) {  // surface integral
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

    public double getAlfaleft() {
        return alfaleft;
    }

    @XmlElement
    public void setAlfaleft(double alfaleft) {
        this.alfaleft = alfaleft;
    }

    public double getAlfaright() {
        return alfaright;
    }

    @XmlElement
    public void setAlfaright(double alfaright) {
        this.alfaright = alfaright;
    }

    public double getCwblock() {
        return cwblock;
    }

    @XmlElement
    public void setCwblock(double cwblock) {
        this.cwblock = cwblock;
    }

    public double getCwmineral() {
        return cwmineral;
    }

    @XmlElement
    public void setCwmineral(double cwmineral) {
        this.cwmineral = cwmineral;
    }

    public double getCwstyrofoam() {
        return cwstyrofoam;
    }

    @XmlElement
    public void setCwstyrofoam(double cwstyrofoam) {
        this.cwstyrofoam = cwstyrofoam;
    }

    public double getKblock() {
        return kblock;
    }

    @XmlElement
    public void setKblock(double kblock) {
        this.kblock = kblock;
    }

    public double getKmineral() {
        return kmineral;
    }

    @XmlElement
    public void setKmineral(double kmineral) {
        this.kmineral = kmineral;
    }

    public double getKstyrofoam() {
        return kstyrofoam;
    }

    @XmlElement
    public void setKstyrofoam(double kstyrofoam) {
        this.kstyrofoam = kstyrofoam;
    }

    public double getDensityblock() {
        return densityblock;
    }

    @XmlElement
    public void setDensityblock(double densityblock) {
        this.densityblock = densityblock;
    }

    public double getDensitymineral() {
        return densitymineral;
    }

    @XmlElement
    public void setDensitymineral(double densitymineral) {
        this.densitymineral = densitymineral;
    }

    public double getDensitystyrofoam() {
        return densitystyrofoam;
    }

    @XmlElement
    public void setDensitystyrofoam(double densitystyrofoam) {
        this.densitystyrofoam = densitystyrofoam;
    }

    public double getTemperatureleft() {
        return temperatureleft;
    }

    @XmlElement
    public void setTemperatureleft(double temperatureleft) {
        this.temperatureleft = temperatureleft;
    }

    public double getTemperatureright() {
        return temperatureright;
    }

    @XmlElement
    public void setTemperatureright(double temperatureright) {
        this.temperatureright = temperatureright;
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

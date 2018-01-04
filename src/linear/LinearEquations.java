package linear;

import org.apache.log4j.Logger;

/**
 * Created by Wienio on 2018-01-03.
 */
public class LinearEquations {

    private static final Logger log = Logger.getLogger(LinearEquations.class);

    private final int iterationNumber = 100;
    private final double e = Math.pow(10, -12);

    private double[][] A;
    private double[] B;
    private int size;

    public LinearEquations(double[][] A, double[] B, int size) {
        this.A = A;
        this.B = B;
        this.size = size;
    }

    public double[] gaussEliminationMethod() {
        double[] result = new double[size];
        double[][] U = new double[size][size + 1];


        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                U[j][i] = A[j][i];
            }
        }

        for (int i = 0; i < size; ++i) {
            U[i][size] = B[i];
        }

        for (int i = 0; i < size - 1; ++i) {
            for (int j = i + 1; j < size; ++j) {
                if (Math.abs(U[i][i]) < e) {
                    log.error("Can't divide by 0!");
                    break;
                }

                double x = -U[j][i] / U[i][i];
                for (int k = 0; k < size + 1; ++k) {
                    U[j][k] += x * U[i][k];
                }
            }
        }

        for (int i = size - 1; i >= 0; --i) {
            double x = U[i][size];
            for (int j = size - 1; j >= 0; --j) {
                x -= U[i][j] * result[j];
            }
            if(Math.abs(U[i][i]) < e) {
                log.error("Can't divide by 0!");
                break;
            }
            result[i] = x / U[i][i];
        }

        return result;
    }

}

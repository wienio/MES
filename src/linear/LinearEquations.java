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

    public LinearEquations(double[][] A, double[] B) {
        this.A = A;
        this.B = B;
    }

    public double[] jacobyMethod(int size) {
        double[][] M = new double[size][size];
        double[] N = new double[size];
        double[] x1 = new double[size];
        double[] x2 = new double[size];

        for (int i = 0; i < size; ++i) {
            x1[i] = 0;
            N[i] = 1 / A[i][i];
        }

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (i == j) M[i][j] = 0;
                else M[i][j] = -(A[i][j] * N[i]);
            }
        }

        for (int i = 0; i < iterationNumber; ++i) {
            for (int j = 0; j < size; ++j) {
                x2[j] = N[j] * B[j];
                for (int k = 0; k < size; ++k) {
                    x2[j] += M[j][k] * x1[k];
                }
            }
            for (int j = 0; j < size; ++j) {
                x1[j] = x2[j];
            }
        }

        return x1;
    }

    public double[] gaussEliminationMethod(int size, double[][] matrix, double[] vector) {
        double[] result = new double[size];
        double[][] U = new double[size][size + 1];


        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                U[j][i] = matrix[j][i];
            }
        }

        for (int i = 0; i < size; ++i) {
            U[i][size] = vector[i];
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

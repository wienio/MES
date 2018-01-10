package jacoby;

import local.LocalElement;

/**
 * Created by Wienio on 2018-01-03.
 */
public class Jacoby {

    private double[][] matrix;
    private double[][] invertedMatrix;
    private double det;
    private int point;  // point to integral in

    public static final LocalElement localElement = LocalElement.getInstance(); // singleton pattern

    public Jacoby(int point, double[] x, double[] y) {
        this.point = point;

        matrix = new double[2][2];
        invertedMatrix = new double[2][2];
        // fill jacoby matrix
        matrix[0][0] = localElement.getdN_Ksi()[point][0] * x[0] + localElement.getdN_Ksi()[point][1] * x[1] + localElement.getdN_Ksi()[point][2] * x[2] + localElement.getdN_Ksi()[point][3] * x[3];
        matrix[0][1] = localElement.getdN_Ksi()[point][0] * y[0] + localElement.getdN_Ksi()[point][1] * y[1] + localElement.getdN_Ksi()[point][2] * y[2] + localElement.getdN_Ksi()[point][3] * y[3];
        matrix[1][0] = localElement.getdN_Eta()[point][0] * x[0] + localElement.getdN_Eta()[point][1] * x[1] + localElement.getdN_Eta()[point][2] * x[2] + localElement.getdN_Eta()[point][3] * x[3];
        matrix[1][1] = localElement.getdN_Eta()[point][0] * y[0] + localElement.getdN_Eta()[point][1] * y[1] + localElement.getdN_Eta()[point][2] * y[2] + localElement.getdN_Eta()[point][3] * y[3];

        det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        // inverted matrix
        invertedMatrix[0][0] = matrix[1][1];
        invertedMatrix[0][1] = -matrix[0][1];
        invertedMatrix[1][0] = -matrix[1][0];
        invertedMatrix[1][1] = matrix[0][0];
    }

    public void printMatrix() {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Det = " + det);
    }

    public double[][] getInvertedMatrix() {
        return invertedMatrix;
    }
    public double getDet() {
        return det;
    }

}

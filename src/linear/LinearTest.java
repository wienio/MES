package linear;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Wienio on 2018-01-03.
 */
public class LinearTest {

    private static final Logger log = Logger.getLogger(LinearTest.class);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Jacoby matrix calculation for linear equation");
        System.out.print("Set matrix size: ");
        int size = in.nextInt();
        if (size < 1) {
            log.error("Matrix size is incorrect! Failed");
            return;
        }

        double A[][] = new double[size][size];
        double B[] = new double[size];

        System.out.println("Enter values into matrix");
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                System.out.print("[ " + (i + 1) + " ][ " + (j + 1) + " ] = ");
                A[i][j] = in.nextDouble();
                if ((i == j) && (A[i][j] == 0)) {
                    log.error("In Jacoby matrix diagonal value must be diffrent from 0, failed");
                    return;
                }
            }
        }

        System.out.println("Fill vector (equations results)");
        for (int i = 0; i < size; ++i) {
            System.out.print("[ " + (i + 1) + " ] = ");
            B[i] = in.nextDouble();
        }

        LinearEquations equations = new LinearEquations(A, B, size);
        double[] result = equations.gaussEliminationMethod();
        System.out.println("Result of Gauss elimination method: " + Arrays.toString(result));
        result = equations.jacobyMethod();
        System.out.println("Result of Jacoby method: " + Arrays.toString(result));
    }

}

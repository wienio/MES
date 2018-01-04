package integral;

import jacoby.Jacoby;

/**
 * Created by Wienio on 2018-01-03.
 */
public class Integral {

    private final double[] p2w = {1, 1};
    private final double[] p2coordinates = {-0.577, 0.577};

    private final double[] p3w = {5.0 / 9.0, 8.0 / 9.0, 5.0 / 9.0};
    private final double[] p3coordinates = {-0.7745, 0, 0.7455};

    private double function(double x, double y) {
        return Math.sin(x) + Math.cos(y);
    }

    public double integral2P() {
        double integral = 0;
        for (int i = 0 ; i < 2 ; ++i) {
            for (int j = 0 ; j < 2 ; ++j) {
                integral += function(p2coordinates[i], p2coordinates[j]) * p2w[i] * p2w[j];
            }
        }
        return  integral;
    }

    public double integral2P(Jacoby[] jacoby, double[] x, double[] y) {
        double integral = 0;
        double global_X[] = new double[4];
        double global_Y[] = new double[4];

        for (int i = 0; i < 4; ++i) {
            global_X[i] = 0;
            global_Y[i] = 0;
            for(int j = 0 ; j < 4 ; ++j) {
                global_X[i] += Jacoby.localElement.getShapesFunction()[i][j] * x[j];
                global_Y[i] += Jacoby.localElement.getShapesFunction()[i][j] * y[j];
            }
        }

        for (int i = 0 ; i < global_X.length; ++i) {
            integral += function(global_X[i], global_Y[i] * jacoby[i].getDet());
        }

        return integral;
    }

    public double integral3P() {
        double integral = 0;
        for (int i = 0; i < p3w.length; ++i) {
            for (int j = 0; j < p3w.length; ++j) {
                integral += function(p3coordinates[i], p3coordinates[j]) * p3w[i] * p3w[j];
            }
        }
        return integral;
    }

}

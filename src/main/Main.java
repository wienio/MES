package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wienio on 2017-11-03.
 */

public class Main {

    public static void main(String[] args) {
        GlobalData data = new GlobalData();
        data.readConfig();

        List<Point2P> points2P = setup2P();
        List<Point3P> points3P = setup3P();
        calculate2P(points2P);
        calculate3P(points3P);
    }

    private static List<Point2P> setup2P() {
        List<Point2P> pointsList = new ArrayList<>();
        pointsList.add(new Point2P(-0.577, -0.577, 1, 1));
        pointsList.add(new Point2P(-0.577, 0.577, 1, 1));
        pointsList.add(new Point2P(0.577, -0.577, 1, 1));
        pointsList.add(new Point2P(0.577, 0.577, 1, 1));

        return pointsList;
    }

    private static void calculate2P(List<Point2P> points2P) {
        double result = 0;
        for (int i = 0; i < points2P.size(); ++i) {
            result += Equation.function(points2P.get(i).getX(), points2P.get(i).getY()) * points2P.get(i).getWeight1() * points2P.get(i).getWeight2();
        }
        System.out.println("Wynik podanej funkcji dla dwoch punktow to: " + result);
    }

    private static List<Point3P> setup3P() {
        List<Point3P> pointsList = new ArrayList<>();
        pointsList.add(new Point3P(0.7745, -0.7745, 0.7745, 5 / (double) 9, 5 / (double) 9, 5 / (double) 9));
        pointsList.add(new Point3P(0.7745, 0, 0, 5 / (double) 9, 8 / (double) 9, 8 / (double) 9));
        pointsList.add(new Point3P(0.7745, 0.7745, -0.7745, 5 / (double) 9, 5 / (double) 9, 5 / (double) 9));
        pointsList.add(new Point3P(-0.7745, -0.7745, 0.7745, 5 / (double) 9, 5 / (double) 9, 5 / (double) 9));
        pointsList.add(new Point3P(-0.7745, 0, 0.7745, 5 / (double) 9, 8 / (double) 9, 5 / (double) 9));
        pointsList.add(new Point3P(-0.7745, 0.7745, 0.7745, 5 / (double) 9, 5 / (double) 9, 5 / (double) 9));
        pointsList.add(new Point3P(0, -0.7745, 0.7745, 8 / (double) 9, 5 / (double) 9, 5 / (double) 9));
        pointsList.add(new Point3P(0, 0, 0.7745, 8 / (double) 9, 8 / (double) 9, 5 / (double) 9));
        pointsList.add(new Point3P(0, 0.7745, 0.7745, 8 / (double) 9, 5 / (double) 9, 5 / (double) 9));
        return pointsList;
    }

    private static void calculate3P(List<Point3P> pointsList) {
        double result = 0;
        for (int i = 0; i < pointsList.size(); ++i) {
            result += Equation.function2(pointsList.get(i).getX(), pointsList.get(i).getY(), pointsList.get(i).getZ())
                    * pointsList.get(i).getWeight1() * pointsList.get(i).getWeight2();
        }
        System.out.println("Wynik podanej funkcji dla trzech punktow to: " + result);
    }

}

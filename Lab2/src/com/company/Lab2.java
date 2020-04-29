package com.company;

import java.util.Scanner;

public class Lab2 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Point3d[] points = new Point3d[3];

        for (int i = 0; i < 3; i++) {

            System.out.println("Enter x, y, z fot point " + i);

            System.out.print("x: ");
            double x = in.nextDouble();

            System.out.print("y: ");
            double y = in.nextDouble();

            System.out.print("z: ");
            double z = in.nextDouble();

            points[i] = new Point3d(x, y, z);

            if (i > 0 && points[i].equal(points[i - 1])){
                System.out.println("Invalid point!");
                i--;
            }
        }

        double s = computeArea(points[0], points[1], points[2]);

        System.out.println("Area = " + s);
    }

    public static double computeArea(Point3d a, Point3d b, Point3d c){

        double ab = a.distanceTo(b);
        double bc = b.distanceTo(c);
        double ca = c.distanceTo(a);

        double p = 0.5 * (ab + bc + ca);
        double s = Math.sqrt(p * (p - ab) * (p - bc) * (p - ca));

        return s;
    }
}

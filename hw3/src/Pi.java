/*
 * Sarah Ferguson
 * CS4050 - Assignment 3
 * A square has a side of length 2, with a circle inscribed at origin,
 * if you throw N darts that land in the square, M will land inside the circle
 * The ratio of circle's are to square area is Pi/4
 *
 * Simulate the throwing of darts and printout the approximate value of Pi
 * computed for various iterations
 */

import java.util.Random;

public class Pi {
    private static int num_of_darts = 100000000;
    private int count = 0;

    /*
     * Throws a specified number of darts,
     * console will print out the (X,Y) coordinates,
     * will check if the dart lands in a circle, add to count if so
     */
    private void throwDarts() {
        for (int i = 0; i < num_of_darts; i++) {
            Random num = new Random();
            double x = num.nextDouble() * 2;
            double y = num.nextDouble() * 2;
            System.out.println("X: " + x + ", Y: " + y);

            boolean inCircle = isDartInCircle(x, y);
            if (inCircle)
                count++;
            System.out.println("Dart in circle? " + inCircle);
        }
    }

    /*
     * Uses the circle formula: (x - h)^2 + (y - k)^2 = r^2
     * to calculate whether or not the dart landed in the circle
     * h: X coordinate value
     * k: Y coordinate value
     * x: 1, x value of center of circle
     * y: 1, y value of center of circle
     * r: 1, since square length is 2
     *
     * if calculated value is less than or equal to the radius of the circle,
     * return true, meaning the dart is inside the circle
     */
    private boolean isDartInCircle(double x, double y) {
        double value = Math.pow((x - 1), 2) + Math.pow((y - 1), 2);
        return value <= 1;
    }

    public static void main(String[] args) {
        Pi runner = new Pi();
        runner.throwDarts();

        System.out.println("***********************************************");
        System.out.println("Total number of darts thrown: " + num_of_darts);
        System.out.println("Total number of darts made into circle: " + runner.count);
    }
}

/*
 * References used:
 * https://www.mathwarehouse.com/geometry/circle/equation-of-a-circle.php
 */
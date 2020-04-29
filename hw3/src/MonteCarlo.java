/*
 * Sarah Ferguson
 * CS4050 - Assignment 3
 * Determine the approximate integral of a function on an interval 2 ways using probabilities:
 * 1) Dart throwing
 *      construct a rectangular region around function, pick random points in rectangle,
 *      find percentage of these that are in the area under the function
 * 2) Mean of values at random locations
 *      compute mean value of function at random locations in interval and multiply this value
 *      by the interval's width
 *
 * And then compare these two methods to the trapezoid method (accuracy and running times)
 */

import java.util.Random;

public class MonteCarlo {
    private int integralStart = 0;
    private int integralStop = 300;

    private int numOfPoints = 300;
    private int dartsUnderFunction = 0;

    /*
     * Returns the height of the integral at specified x value,
     * for the integral: x^2 + 3x - 9
     */
    private double returnIntegralValue(double x) {
        return Math.pow(x, 2) + (3 * x) - 9;
    }

    /*
     * Throws pre-defined number of darts in rectangular region around function,
     * finds percentage of darts under actual integral and multiplies by rectangle area
     * returns the area calculated from this method
     */
    private Double dartThrowing() {
        double rectangleWidth = integralStop - integralStart;
        double rectangleHeight = getHighestValue(integralStart, integralStop)
                - getLowestValue(integralStart, integralStop);
        for (int i = 0; i < numOfPoints; i++) {
            Random num = new Random();
            double x = (rectangleWidth) * num.nextDouble();
            double y = (rectangleHeight) * num.nextDouble();
            if (isUnderIntegral(x, y))
                dartsUnderFunction++;
        }
        double percentageUnderFunction = ((double) dartsUnderFunction / (double) numOfPoints);
        return percentageUnderFunction * (rectangleWidth * rectangleHeight);
    }

    /*
     * Returns the highest y value, or the top of the integral
     * through user defined range (start to stop)
     */
    private double getHighestValue(int start, int stop) {
        double max = -999999999;
        for (int i = start; i <= stop; i++) {
            double value = returnIntegralValue(i);
            if (value > max)
                max = value;
        }
        return max;
    }

    /*
     * Returns the lowest y value, or the bottom of the integral
     * through user defined range (start to stop)
     */
    private double getLowestValue(int start, int stop) {
        double min = 999999999;
        for (int i = start; i <= stop; i++) {
            double value = returnIntegralValue(i);
            if (value < min)
                min = value;
        }
        return min;
    }

    /*
     * Through given X and Y coordinates,
     * calculate if dart is under the integral
     */
    private boolean isUnderIntegral(double x, double y) {
        double integralValue = returnIntegralValue(x);
        return y <= integralValue;
    }

    /*
     * Through random number of points selected, find mean value of actual y values
     * multiply found mean area by the interval width
     * returns the area calculated from method
     */
    private double meanValueOfRandomLocations() {
        double meanValue = 0;
        double rectangleWidth = integralStop - integralStart;
        for (int i = 0; i < numOfPoints; i++) {
            Random num = new Random();
            double x = (rectangleWidth) * num.nextDouble();
            double y = returnIntegralValue(x);
            meanValue += y;
        }
        meanValue = meanValue / numOfPoints;
        return meanValue * rectangleWidth;
    }

    /*
     * Draws trapezoids at each interval, finds first height and second height (both are y values of integral)
     * Through area of trapezoid, 1/2 (height1 + height2) * width
     */
    private double trapezoidMethod() {
        double area = 0;
        for (int i = 0; i < numOfPoints; i++) {
            double firstY = returnIntegralValue(i);
            double secondY = returnIntegralValue(i+1);
            area += ((secondY + firstY) * 1/2) * 1;
        }
        return area;
    }

    public static void main(String[] args) {
        MonteCarlo runner = new MonteCarlo();
        System.out.println("Integral function: x^2 + 3x - 9");

        System.out.println("***********************************************");
        System.out.println("Number of darts thrown: " + runner.numOfPoints);
        Long dartStart = System.nanoTime();
        System.out.println("Area calculated from dart throwing: " + runner.dartThrowing());
        Long dartStop = System.nanoTime();
        System.out.println("Time taken: " + (dartStop - dartStart));

        System.out.println("***********************************************");
        System.out.println("Number of random points: " + runner.numOfPoints);
        Long meanValueStart = System.nanoTime();
        System.out.println("Area calculated from mean value: " + runner.meanValueOfRandomLocations());
        Long meanValueStop = System.nanoTime();
        System.out.println("Time taken: " + (meanValueStop - meanValueStart));

        System.out.println("***********************************************");
        System.out.println("Number of trapezoid points: " + runner.numOfPoints);
        Long trapezoidStart = System.nanoTime();
        System.out.println("Area calculated from trapezoids: " + runner.trapezoidMethod());
        Long trapezoidStop = System.nanoTime();
        System.out.println("Time taken: " + (trapezoidStop - trapezoidStart));
    }
}

/*
 * References used:
 * https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
 * https://www.math24.net/trapezoidal-rule/
 */

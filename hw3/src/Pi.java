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
    private static int num_of_darts = 1000;
    private Boolean[] dartsInCircle = new Boolean[num_of_darts];

    /*
     * Throws a specified number of darts,
     * console will print out the (X,Y) coordinates,
     * will check if the dart lands in a circle, puts this result in an array
     */
    private void throwDarts() {
        for (int i = 0; i < num_of_darts; i++) {
            Coordinate coordinates = new Coordinate();
            System.out.println("X: " + coordinates.getX() + ", Y: " + coordinates.getY());
            dartsInCircle[i] = isDartInCircle(coordinates);
            System.out.println("Dart in circle? " + dartsInCircle[i]);
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
    private boolean isDartInCircle(Coordinate coordinates) {
        double x = coordinates.getX();
        double y = coordinates.getY();

        double value = Math.pow((x - 1), 2) + Math.pow((y - 1), 2);

        if (value <= 1) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Prints out the total number of darts thrown and the total that made it into the circle
     * by checking how many entries in the array are true
     */
    private void printOutResults() {
        System.out.println("***********************************************");
        System.out.println("Total number of darts thrown: " + num_of_darts);

        int count = 0;
        for (int i = 0; i < dartsInCircle.length; i++) {
            if (dartsInCircle[i]) {
                count++;
            }
        }
        System.out.println("Total number of darts made into circle: " + count);
    }

    public static void main(String[] args) {
        Pi runner = new Pi();
        runner.throwDarts();
        runner.printOutResults();
    }

    /*
     * Class to represent an coordinate object,
     * each object has its own X and Y values,
     * will represent the coordinates from when a dart is thrown
     */
    class Coordinate {
        private double x;
        private double y;

        public Coordinate() {
            setX();
            setY();
        }

        /*
         * Sets the X value as a random double between 0 to 2
         */
        public void setX() {
            Random num = new Random();
            x = num.nextDouble() * 2;
        }

        /*
         * Sets the Y value as a random double between 0 to 2
         */
        public void setY() {
            Random num = new Random();
            y = num.nextDouble() * 2;
        }
        /*
         * Returns the X coordinate value
         */
        public double getX() {
            return this.x;
        }

        /*
         * Returns the Y coordinate value
         */
        public double getY() {
            return this.y;
        }
    }
}

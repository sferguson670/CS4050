/*
 * Sarah Ferguson
 * CS4050 - Assignment 4
 * Use random number generator from programming language,
 * positive ints only!
 */
import java.util.Random;

public class JavaRandom {
    private static int numOfRandoms = 1000;
    Random random = new Random();

    private int getRandomInt() {
        return random.nextInt(Integer.MAX_VALUE);
    }

    public static void main (String[] args) {
        JavaRandom runner = new JavaRandom();

        for (int i = 0; i < numOfRandoms; i++) {
            System.out.println(runner.getRandomInt());
        }
    }
}

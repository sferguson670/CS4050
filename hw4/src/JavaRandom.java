/*
 * Sarah Ferguson
 * CS4050 - Assignment 4
 * Use random number generator from programming language,
 * positive ints up to 1000 only
 */
import java.util.Random;

public class JavaRandom {
    Random random = new Random();

    private int getRandomInt() {
        return random.nextInt(1001);
    }

    public static void main (String[] args) {
        JavaRandom runner = new JavaRandom();

        int numOfRandoms = 1000000;
        for (int i = 0; i < numOfRandoms; i++) {
            System.out.println(runner.getRandomInt());
        }
    }
}

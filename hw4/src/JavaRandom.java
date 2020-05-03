/*
 * Sarah Ferguson
 * CS4050 - Assignment 4
 * Use random number generator from programming language,
 * positive ints 0 to 999
 */
import java.util.Random;

public class JavaRandom {
    Random random = new Random();

    private int getRandomInt() {
        return random.nextInt(1000);
    }

    public static void main (String[] args) {
        JavaRandom runner = new JavaRandom();

        int numOfRandoms = 1000000;
        for (int i = 0; i < numOfRandoms; i++) {
            System.out.println(runner.getRandomInt());
        }
    }
}

/*
 * Sarah Ferguson
 * CS4050 - Assignment 4
 * Use random number generator from programming language
 */
import java.util.Random;

public class JavaRandom {
    private static int numOfRandoms = 100;
    Random random = new Random();

    private int getRandomInt() {
        return random.nextInt();
    }

    public static void main (String[] args) {
        JavaRandom runner = new JavaRandom();

        for (int i = 0; i < numOfRandoms; i++) {
            System.out.println(runner.getRandomInt());
        }
    }
}

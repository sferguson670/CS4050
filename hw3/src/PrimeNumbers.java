/*
 * Sarah Ferguson
 * CS4050 - Assignment 3
 * Input a number (or generate a number randomly) and test it against k random values
 * Pick a bunch of known composite numbers and see how accurately your program rejects them
 * See if results improve for larger values of k
 */

import java.util.Random;
import java.util.Scanner;

public class PrimeNumbers {
    private static int p;
    private static int k = 10000;
    int counter = 0;

    /*
     * Prompts user to enter a value to be considered the P value
     */
    private static void getNumberFromConsole() {
        System.out.println("Enter an int to be considered P value:");
        Scanner input = new Scanner(System.in);
        p = input.nextInt();
    }

    /*
     * Returns a random even number
     * from 1,000,000 to 5,000,000
     */
    private int getRandomEvenNumber() {
        Random num = new Random();
        int randomNum;
        do {
            randomNum = num.nextInt(((5000000 - 1000000) + 1) + 1000000);
        }
        while ( randomNum % 2 != 0);
        return randomNum;
    }

    /*
     * Returns a random number where final digit is 5
     * from 1,000,000 to 5,000,000
     */
    private int getRandomFinalDigit5Number() {
        Random num = new Random();
        int randomNum;
        do {
            randomNum = num.nextInt(((5000000 - 1000000) + 1) + 1000000);
        }
        while ( randomNum % 10 != 5);
        return randomNum;
    }

    /*
     * Returns a random number where sum of all digits is divisible by 3
     * from 1,000,000 to 5,000,000
     */
    private int getRandomAllDigitsDivisibleBy3Number() {
        Random num = new Random();
        int randomNum;
        do {
            randomNum = num.nextInt(((5000000 - 1000000) + 1) + 1000000);
        }
        while ( getSumDigits(randomNum) % 3 != 0);
        return randomNum;
    }

    private int getSumDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum = sum + num % 10;
            num = num / 10;
        }
        return sum;
    }

    /*
     * Returns a random number where it is a product of 2 positive ints
     * where each positive int is between 1 to 5000
     */
    private int getRandomProductOf2PositiveIntsNumber() {
        Random num = new Random();
        int randomNum = num.nextInt(5000 + 1);
        int randomNum2 = num.nextInt(5000 + 1);
        return randomNum * randomNum2;
    }

    /*
     * Generate a random number n where 1 < n < P, k number of times,
     * each time do check if % will return it as a Prime number,
     * for each time it is REJECTED (NOT a prime number), add to counter
     */
    private void checkRandomValues(int p) {
        for (int i = 0; i < k; i++) {
            Random num = new Random();
            int randomNum = num.nextInt(p);
            System.out.println("Checking P % " + randomNum);
            if (!checkIfPrime(p, randomNum))
                counter++;
        }
    }

    /*
     * Checks if P is prime by doing P % n, where n is random value 1 < n < P
     * Returns true if "prime", returns false if "not prime" by calculation
     */
    private boolean checkIfPrime(int p, int n) {
        return (p % n != 0);
    }

    public static void main(String[] args) {
        PrimeNumbers runner = new PrimeNumbers();

        //getNumberFromConsole();
        getNumberFromConsole();
        runner.checkRandomValues(p);

        System.out.println("***********************************************");
        System.out.println("P value, checking if this is prime: " + p);
        System.out.println("K value, number of random values to test against: " + k);
        System.out.println("Total number of rejections: " + runner.counter);
    }
}

/*
 * References used:
 * https://www.tutorialspoint.com/read-integers-from-console-in-java
 * https://www.mathsisfun.com/prime_numbers.html
 * https://primes.utm.edu/curios/includes/primetest.php
 * https://mkyong.com/java/java-generate-random-integers-in-a-range/
 * https://stackoverflow.com/questions/17144997/gets-last-digit-of-a-number
 * https://stackoverflow.com/questions/27096670/how-to-sum-digits-of-an-integer-in-java
 */

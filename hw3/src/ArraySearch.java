/*
 * Sarah Ferguson
 * CS4050 - Assignment 3
 * Create an array of unique 1000 ints, search for a value known in the array by generating
 * an index at random and test to see if it's at that position
 *
 * There is a maximum of of 5000 guesses, try 100 different searches and compute the
 * average number of comparisons the program has to do
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ArraySearch {
    private ArrayList<Integer> array = new ArrayList<>();
    private int comparisons = 0;

    /*
     * Adds value 0 - 1000 to array list, and uses shuffle function to randomize values
     * This ensures every entry in the array to be unique
     */
    private void generateArrayValues() {
        int arraySize = 1000;
        for (int i = 0; i < arraySize; i++) {
            array.add(i);
        }
        Collections.shuffle(array);
    }

    /*
     * Gets a random known value from the array by randomly selecting an entry
     * from the array
     */
    private int getRandomValueFromArray() {
        Random num = new Random();
        int pos = num.nextInt(1000);
        return array.get(pos);
    }

    /*
     * Generates an index at random and tests to see if known value from array
     * is at the random position, will do until found or until number of guesses
     */
    private void checkArrayForRandomValue(int numInArray) {
        Random num = new Random();
        int numOfGuesses = 5000;
        for (int i = 0; i < numOfGuesses; i++) {
            comparisons++;
            int pos = num.nextInt(1000);
            if (array.get(pos) == numInArray) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        ArraySearch runner = new ArraySearch();
        runner.generateArrayValues();
        int knownNumInArray = runner.getRandomValueFromArray();
        runner.checkArrayForRandomValue(knownNumInArray);

        System.out.println("***********************************************");
        System.out.println("Number to be found: " + knownNumInArray);
        System.out.println("Total number of comparisons: " + runner.comparisons);
    }
}

/*
 * Sarah Ferguson
 * CS4050 - Assignment 2
 * A program that uses text prediction with the use of a tree,
 * each word is broken into letters, each letter being a node
 */

import java.util.Scanner;

public class WordTree {
    private String FILENAME = "words.txt";
    private Tree genericTree;

    private void createTree() {
        genericTree = new Tree();
    }

    /*
     * Reads file and fills in a tree for each word,
     * each word is broken into each character,
     * each character is a child node to the previous letter
     * to signal the end of the word, the last node will be *
     */
    private void fillInTree() {

    }

    private static void commandLinePrompt() {
        System.out.println("Enter a word, one character at a time.");
        System.out.println("At the end of the word, enter a space ' '");
    }

    private static String getCharaFromConsole() {
        Scanner sc = new Scanner(System.in);
        Character c = sc.next().charAt(0);
        if (!Character.isAlphabetic(c)) {
            if (Character.isSpaceChar(c)) {
                return "*";
            }
            System.out.println("Character entered is not a letter, try again.");
            return null;
        }
        return c.toString();
    }

    public static void main(String[] args) {

    }
}

/*
 * references used:
 * https://www.geeksforgeeks.org/binary-tree-set-1-introduction/
 * https://www.tutorialspoint.com/how-to-check-if-a-given-character-is-a-number-letter-in-java
 */
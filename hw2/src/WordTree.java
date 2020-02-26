/*
 * Sarah Ferguson
 * CS4050 - Assignment 2
 * A program that uses text prediction with the use of a tree,
 * each word is broken into letters, each letter being a node
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class WordTree {

    private Tree genericTree;
    private Tree.Node root;

    private String FILENAME = "words.txt";

    /*
     * Initializes the tree, creates only parent node
     * and returns that parent node
     */
    private Tree.Node createTree() {
        genericTree = new Tree();
        return root = genericTree.newNode("Root");
    }

    /*
     * Reads file line by line,
     * passes a string to fillInTree method for each line,
     * does this until it hits end of file.
     */
    private void readWordsFromFile() throws IOException {
        File file = new File(FILENAME);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String word = null;
        while ((word=br.readLine())!=null) {
            System.out.println(word);
            fillInTree(word);
        }
        fr.close();
    }

    /*
     * Each word is broken into each character,
     * each character is a child node to the previous letter
     * to signal the end of the word, the last node will be *
     */
    private void fillInTree(String word) {
        int position = 0;
        List<String> firstLetters = genericTree.getChildrenLetters(root);
        if (!firstLetters.contains(word.substring(0,1))) {
            root.child.add(genericTree.newNode(word.substring(0,1)));
            while (position < word.length()) {
                //Tree.Node nodeAtPosition =
            }
        } else {
            int pos = firstLetters.indexOf(word.substring(0,1));
            Tree.Node specifiedChild = genericTree.getSpecifiedChild(root, pos);
        }
    }

    /*
     * Prints out the prompt to the user in the console
     */
    private static void commandLinePrompt() {
        System.out.println("Enter a word, one character at a time.");
        System.out.println("At the end of each character, hit enter.");
    }

    /*
     * Gets each character from user input,
     * only accepts alphabetical characters
     */
    private static String getCharaFromConsole() {
        Scanner sc = new Scanner(System.in);
        Character c = sc.next().charAt(0);
        if (!Character.isAlphabetic(c)) {
            System.out.println("Character entered is not a letter, try again.");
            return null;
        }
        return c.toString();
    }

    /*
     * Returns the top 10 autofill results by traversing the tree
     * with what already exists from user input (word)
     */
    private static String getAutofillResults(String word) {
        return word;
    }

    /*
     * Driver code
     */
    public static void main(String[] args) throws IOException {
        WordTree runner = new WordTree();
        runner.createTree();
        runner.fillInTree("hi");
    }
}

/*
 * references used:
 * https://www.geeksforgeeks.org/binary-tree-set-1-introduction/
 * https://www.tutorialspoint.com/how-to-check-if-a-given-character-is-a-number-letter-in-java
 */
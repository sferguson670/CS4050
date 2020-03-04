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
import java.util.ArrayList;
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
        return root = genericTree.createRootNode();
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
        String word;
        while ((word=br.readLine())!=null) {
            fillInTree(word);
        }
        fr.close();
    }

    /*
     * Each word is broken into each character (all lowercase),
     * each character is a child node to the previous letter
     * to signal the end of the word, the last node will be *
     * ex) Root -> H -> E -> L -> L -> O -> *
     */
    private void fillInTree(String word) {
        // if word already exists, then do nothing
        if (genericTree.searchTree(word.toLowerCase(), root)) {
            return ;
        }

        // starts off with root node and gets all of it's children in a list to check against
        List<String> nodeLetters = genericTree.getChildrenLetters(root);
        Tree.Node node = root;

        // goes through each letter of the word, and checks if it exists as as the node's child
        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i+1).toLowerCase();

            // if it doesn't exist, then it will add the letter as a child to the node
            if (!genericTree.getChildrenLetters(node).contains(letter)) {
                node.child.add(genericTree.newNode(letter, false));
            }

            // reassigns the nodeLetters and node to the next node, which is the child to previous
            nodeLetters = genericTree.getChildrenLetters(node);
            node = genericTree.getSpecifiedChild(node, letter);
        }

        // at the end of the word, change isEnd to true to signify the end of the word
        node.isEnd = true;
    }

    /*
     * Prints out the prompt to the user in the console
     */
    private static void commandLinePrompt() {
        System.out.println("Enter a word, one character at a time.");
        System.out.println("At the end of each character, hit enter. If done filling out word, enter '*'.");
    }

    /*
     * Gets each character from user input,
     * only accepts alphabetical characters
     */
    private static String getCharaFromConsole() {
        Scanner sc = new Scanner(System.in);
        Character c = sc.next().charAt(0);

        // checks if user inputs '*' to signal end of word or non-alphabetical value
        if (c.charValue() == 42) {
            return "*";
        } else if (!Character.isAlphabetic(c)) {
            System.out.println("Character entered is not a letter, try again.");
            return null;
        }
        return c.toString();
    }

    /*
     * Returns a string to represent what the user is inputted,
     * concat each character entered into one word
     */
    private static String getWordFromConsole() {
        commandLinePrompt();

        Boolean repeat = true;
        String getCharaFromConsole = getCharaFromConsole();
        String word = "";

        while (repeat = true) {
            if (getCharaFromConsole.equals("*")) {
                word += "*";
                break;
            } else if (getCharaFromConsole.equals(null)) {
                word = null;
                break;
            } else {
                word += getCharaFromConsole;
            }
            getCharaFromConsole();
        }
        System.out.println(word);
        return word;
    }

    protected List getWords(Tree.Node root) {
        List words = new ArrayList();
        String word = root.letter;

        for (int i = 0; i < root.child.size(); i++) {
            getWords(root.child.get(i));
            word += root.child.get(i).letter;
            if (root.isEnd) {
                words.add(word);
            }
        }

        return words;
    }

    /*
     * Returns the top 10 autofill results by traversing the tree
     * with what already exists from user input (word)
     */
    private List getAutofillResults(String prefix) {
        Tree.Node lastNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            String letter = prefix.substring(i, i+1);
            lastNode = genericTree.getSpecifiedChild(lastNode,letter);
            if (lastNode == null) {
                break;
            }
        }
        return getWords(lastNode);
    }

    private void printPreOrder(Tree.Node parent) {
        if (parent == null)
            return ;

        System.out.println(parent.letter);

        for (int i = 0; i < parent.child.size(); i++) {
            printPreOrder(parent.child.get(i));
        }
    }

    private void hellothere() {
        /*
        List<String> words = new LinkedList<>();
        words = getWords(root);

        for (int i = 0; i < words.size(); i++) {
            System.out.println(words.get(i));
        }

        List<String> autofill = new LinkedList<>();
        autofill = getAutofillResults("he");
        for (int i = 0; i < autofill.size(); i++) {
            System.out.println(autofill.get(i));
        }

         */
        printPreOrder(root);

        System.out.println(genericTree.searchTree("Goodbye", root));

        List words = getWords(root);
        for (int i = 0; i < words.size(); i++) {
            System.out.println("letter: " + words.get(i));
        }
    }

    /*
     * Driver code
     */
    public static void main(String[] args) throws IOException {
        WordTree runner = new WordTree();
        runner.createTree();
        runner.readWordsFromFile();
        runner.hellothere();

    }
}

/*
 * references used:
 * https://www.geeksforgeeks.org/binary-tree-set-1-introduction/
 * https://www.tutorialspoint.com/how-to-check-if-a-given-character-is-a-number-letter-in-java
 * https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 * https://www.geeksforgeeks.org/level-order-tree-traversal/
 * https://www.lavivienpost.com/autocomplete-with-trie-code/
 */
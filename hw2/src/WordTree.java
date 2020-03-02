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
import java.util.LinkedList;
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
        String word = "";
        while ((word=br.readLine())!=null) {
            fillInTree(word);
        }
        fr.close();
    }

    /*
     * Each word is broken into each character,
     * each character is a child node to the previous letter
     * to signal the end of the word, the last node will be *
     * ex) Root -> H -> E -> L -> L -> O -> *
     */
    private void fillInTree(String word) {
        // starts off with root node and gets all of it's children in a list to check against
        List<String> nodeLetters = genericTree.getChildrenLetters(root);
        Tree.Node node = root;

        // goes through each letter of the word, and checks if it exists as as the node's child
        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i+1);

            // if it doesn't exist, then it will add the letter as a child to the node
            if (!genericTree.getChildrenLetters(node).contains(letter)) {
                node.child.add(genericTree.newNode(letter));
            }

            // reassigns the nodeLetters and node to the next node, which is the child to previous
            nodeLetters = genericTree.getChildrenLetters(node);
            int pos = genericTree.getPositionOfSpecifiedChild(node, letter);
            node = genericTree.getSpecifiedChild(node, pos);

            // at the end of the word, add a "*" at the end to signify the end of the word
            if (i == word.length() - 1 ) {
                node.child.add(genericTree.newNode("*"));
            }
        }
    }

    private void printPreOrder(Tree.Node parent) {
        if (parent == null)
            return ;

        System.out.println(parent.letter);

        for (int i = 0; i < parent.child.size(); i++) {
            printPreOrder(parent.child.get(i));
        }
    }

    /*
     * Traverses the parent node and returns all words from that node,
     * does it in a pre-order fashion
     */
    private List<String> getWordsFromTree() {
        //List<String> childrenLetters = genericTree.getChildrenLetters(parent);
        List<String> words = new LinkedList<>();

        return words;
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

    /*
     * Returns the top 10 autofill results by traversing the tree
     * with what already exists from user input (word)
     */
    private List<String> getAutofillResults(String word) {
        List<String> autofillResults = new LinkedList<>();

        return autofillResults;
    }

    private void hithere() {
        printPreOrder(root);
        System.out.println(genericTree.getSpecifiedChildFromWord(root, "Hello").child.get(0).letter);
    }

    /*
     * Driver code
     */
    public static void main(String[] args) throws IOException {
        WordTree runner = new WordTree();
        runner.createTree();
        runner.readWordsFromFile();
        runner.fillInTree("hi");
        runner.fillInTree("hihihihi");
        runner.fillInTree("Hellos");
        runner.fillInTree("watup");
        runner.hithere();
    }
}

/*
 * references used:
 * https://www.geeksforgeeks.org/binary-tree-set-1-introduction/
 * https://www.tutorialspoint.com/how-to-check-if-a-given-character-is-a-number-letter-in-java
 * https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 * https://www.geeksforgeeks.org/level-order-tree-traversal/
 */
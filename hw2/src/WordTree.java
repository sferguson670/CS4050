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

    /*
     * Initializes the tree, creates only parent node
     * and returns that parent node
     */
    private void createTree() {
        genericTree = new Tree();
        root = genericTree.createRootNode();
    }

    /*
     * Reads file line by line,
     * passes a string to fillInTree method for each line,
     * does this until it hits end of file.
     */
    private void readWordsFromFile() throws IOException {
        String FILENAME = "words2.txt";
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
     * ex) Root -> H -> E -> L -> L -> O
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
     * Get all words associated with node, uses a pre-order traversal
     * Takes in a word that should be initialized as "" to use for recursion
     */
    private List getWords(Tree.Node node, String word) {
        List words = new ArrayList();
        word += node.letter;

        if (node.isEnd)
            words.add(word);

        if (!node.child.isEmpty()) {
            for (int i = 0; i < node.child.size(); i++) {
                words.addAll(getWords(node.child.get(i), word));
            }
        }

        return words;
    }

    /*
     * Returns a list of the the autofill results from a prefix
     */
    private List getAutoFillResults(String prefix) {
        Tree.Node node = root;

        for (int i = 0; i < prefix.length(); i++) {
            String letter = prefix.substring(i, i+1);
            int pos = genericTree.getPositionOfSpecifiedChild(node, letter);
            node = node.child.get(pos);
            if (node == null) {
                return new ArrayList();
            }
        }
        String word = "";
        return getWords(node, word);
    }

    /*
     * Gets each character from user input,
     * only accepts alphabetical characters
     */
    private static String getCharaFromConsole() {
        Scanner sc = new Scanner(System.in);
        Character c = sc.next().charAt(0);

        // checks if user inputs '*' to signal end of word or non-alphabetical value
        if (c == 42) {
            return "*";
        } else if (!Character.isAlphabetic(c)) {
            System.out.println("Character entered is not a letter, try again.");
            return null;
        }
        return c.toString();
    }

    /*
     * Prints out instructions to console for user and gets the word input,
     * then prints out up to 10 results for autofill
     */
    private void commandLinePrompt() {
        System.out.println("Enter a word, one character at a time.");
        System.out.println("At the end of each character, hit enter. If done filling out word, enter '*'.");

        StringBuilder prefix = new StringBuilder();
        String character = getCharaFromConsole();
        while (character!= null && !character.equals("*")) {
            prefix.append(character);
            character = getCharaFromConsole();
        }
        System.out.println("word entered: " + prefix);

        List autofill = getAutoFillResults(prefix.toString());
        if (autofill.size() > 10) {
            autofill = autofill.subList(0, 10);
        }
        for (Object o : autofill) {
            System.out.println("autofill: " + prefix.substring(0, prefix.length() - 1) + o);
        }
    }

    /*
     * Driver code
     */
    public static void main(String[] args) throws IOException {
        WordTree runner = new WordTree();
        runner.createTree();
        runner.readWordsFromFile();
        runner.commandLinePrompt();
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
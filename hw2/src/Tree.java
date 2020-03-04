/*
 * Sarah Ferguson
 * CS4050 - Assignment 2
 * A program that uses text prediction with the use of a tree,
 * each word is broken into letters, each letter being a node
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Tree {

    /*
     * Represents a node of a tree
     */
    class Node {
        String letter;
        Vector<Node> child = new Vector<>();
        Boolean isEnd = false;
    }

    /*
     * Creates a new node, where it stores a letter in it
     */
    public Node newNode(String letter, Boolean isEnd) {
        Node temp = new Node();
        temp.letter = letter;
        temp.isEnd = isEnd;
        return temp;
    }

    /*
     * Creates the root node, where "Root" is stored in it
     */
    public Node createRootNode() {
        Node temp = new Node();
        temp.letter = "Root";
        temp.isEnd = false;
        return temp;
    }

    /*
     * Takes the parent node, and returns the position of the specified child node
     */
    public int getNumberChildren(Node node) {
        return node.child.size();
    }

    /*
     * Takes the parent node, and returns the position of the specified child node
     */
    public int getPositionOfSpecifiedChild(Node node, String letter) {
        return getChildrenLetters(node).indexOf(letter);
    }

    /*
     * Takes the parent node, and returns child node at specified position
     */
    public Node getSpecifiedChild(Node parent, int pos) {
        if (parent == null)
            return null;

        int size = parent.child.size();
        if (pos > size - 1)
            return null;

        return parent.child.get(pos);
    }

    /*
     * Takes the parent node, and returns child node with specified letter
     */
    public Node getSpecifiedChild(Node parent, String letter) {
        if (parent == null)
            return null;

        int pos = getPositionOfSpecifiedChild(parent, letter);

        int size = parent.child.size();
        if (pos > size - 1)
            return null;

        return parent.child.get(pos);
    }

    /*
     * returns a list of all the children of a node,
     * in the format of what letter they contain
     */
    public List<String> getChildrenLetters(Node node) {
        List<String> children = new LinkedList<>();

        if (node == null)
            children = null;

        int size = node.child.size();
        for (int i = 0; i < size; i++) {
            children.add(node.child.get(i).letter);
        }

        return children;
    }

    /*
     * searches the tree from provided root to see if word exists
     */
    public boolean searchTree(String word, Node root) {
        // starts off with root node and gets all of it's children in a list to check against
        List<String> nodeLetters = getChildrenLetters(root);
        Tree.Node node = root;

        // goes through each letter of the word, and checks if it exists as as the node's child
        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i + 1).toLowerCase();

            // if it doesn't exist, then it will add the letter as a child to the node
            if (!getChildrenLetters(node).contains(letter)) {
                return false;
            } else {
                // reassigns the nodeLetters and node to the next node, which is the child to previous
                nodeLetters = getChildrenLetters(node);
                node = getSpecifiedChild(node, letter);
            }
        }

        if (node.isEnd) {
            return true;
        } else {
            return false;
        }
    }
}

/*
 * references used:
 * https://www.cs.colorado.edu/~main/docs/edu/colorado/nodes/Node.html
 * https://stackoverflow.com/questions/3522454/java-tree-data-structure
 * https://www.geeksforgeeks.org/generic-tree-level-order-traversal/
 */

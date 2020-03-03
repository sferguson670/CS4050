/*
 * Sarah Ferguson
 * CS4050 - Assignment 2
 * A program that uses text prediction with the use of a tree,
 * each word is broken into letters, each letter being a node
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    public int getNumberofChildren(Node node) {
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
    public Node getSpecifiedChild(Node parent, String chara) {
        if (parent == null)
            return null;

        int pos = getPositionOfSpecifiedChild(parent, chara);

        int size = parent.child.size();
        if (pos > size - 1)
            return null;

        return parent.child.get(pos);
    }

    /*
     * Traverses the tree and returns the last node from the word inputted
     */
    public Node getSpecifiedChildFromWord(Node root, String word) {
        Node node = root;
        List<String> nodeChildren = getChildrenLetters(node);
        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i+1);
            if (nodeChildren.contains(letter)) {
                int pos = getPositionOfSpecifiedChild(node, letter);
                node = node.child.get(pos);
                nodeChildren = getChildrenLetters(node);
            } else {
                node = null;
                break;
            }
        }

        return node;
    }

    /*
     * returns a list of all the children of a node,
     * in the format of the nodes
     */
    public List<Node> getChildrenNodes(Node parent) {
        List<Node> children = new LinkedList<>();

        if (parent == null)
            children = null;

        int size = parent.child.size();
        for (int i = 0; i < size; i++) {
            children.add(parent.child.get(i));
        }

        return children;
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

    public void LevelOrderTraversal(Node root) {
        if (root == null)
            return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty())
        {
            int n = q.size();

            while (n > 0) {
                Node p = q.peek();
                q.remove();
                System.out.println(p.letter + " ");

                for (int i = 0; i < p.child.size(); i++) {
                    q.add(p.child.get(i));
                }
                n--;
            }
            System.out.println();
        }
    }
}

/*
 * references used:
 * https://www.cs.colorado.edu/~main/docs/edu/colorado/nodes/Node.html
 * https://stackoverflow.com/questions/3522454/java-tree-data-structure
 * https://www.geeksforgeeks.org/generic-tree-level-order-traversal/
 */

/*
 * Sarah Ferguson
 * CS4050 - Assignment 2
 * A program that uses text prediction with the use of a tree,
 * each word is broken into letters, each letter being a node
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class Tree {
    private class Node {
        Character letter;
        Vector<Node> child = new Vector<>();
    }

    public Node newNode(Character letter) {
        Node temp = new Node();
        temp.letter = letter;
        return temp;
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

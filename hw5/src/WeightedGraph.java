/*
 * Sarah Ferguson
 * CS4050 - Assignment 5
 * Graph programming assignment where:
 * 1. build a directed weighted graph that represents exchange rates
 * 2. find sequences of trades that will results in a profit
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Class to represent the whole weighted graph,
 * each graph has a node, which has a set of edges to it
 */
public class WeightedGraph {
    private Set<Node> nodes;

    public WeightedGraph() {
        nodes = new HashSet<>();
    }

    /*
     * Adds a vertex to the graph,
     * it is added to the list of vertices
     */
    public boolean addVertex(Node node) {
        return nodes.add(node);
    }

    /*
     * Returns all the vertices that is in the graph
     */
    public List<Node> getVertices() {
        return new ArrayList<>(nodes);
    }

    /*
     * Class to represent a Node of the weighted graph,
     * will contain information of what the currency is,
     * and will have a list of edges of what the node connects to
     */
    static class Node {
        private String countryLabel;
        private Set<Edge> edges;

        public Node(String currencyLabel) {
            this.countryLabel = currencyLabel;
            edges = new HashSet<>();
        }

        /*
         * Returns the country that the node represents
         */
        public String getCountryLabel() {
            return countryLabel;
        }

        /*
         * Adds an edge to the node,
         * gets added to list of edges
         */
        public boolean addEdge(Edge edge) {
            return edges.add(edge);
        }

        /*
         * Returns all the edges that is associated to node
         */
        public List<Edge> getEdges() {
            return new ArrayList<>(edges);
        }
    }

    /*
     * Class to represent an edge of the weighted graph,
     * will contain the currency rate,
     * and will contain what node it connects to
     */
    static class Edge {
        private Node toNode;
        private double currencyRate;

        public Edge(Node toNode, double currencyRate) {
            this.toNode = toNode;
            this.currencyRate = currencyRate;
        }

        public Node getToNode() {
            return toNode;
        }

        public double getCurrencyRate() {
            return currencyRate;
        }
    }
}

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
 * each graph has a vertex, which has a set of edges to it
 */
public class WeightedGraph {
    private Set<Vertex> vertices;

    public WeightedGraph() {
        vertices = new HashSet<>();
    }

    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices);
    }

    public boolean addVertex(Vertex vertex) {
        return vertices.add(vertex);
    }

    /*
     * Class to represent a vertex of the weighted graph,
     * will contain information of what the currency is,
     * and will have a list of edges of what the node connects to
     */
    class Vertex {
        private String currencyLabel;
        private Set<Edge> edges;

        public String getCurrencyLabel() {
            return currencyLabel;
        }

        public boolean addEdge(Edge edge) {
            return edges.add(edge);
        }

        public List<Edge> getEdges() {
            return new ArrayList<>(edges);
        }
    }

    /*
     * Class to represent an edge of the weighted graph,
     * will contain the currency rate,
     * and will contain what node it connects to
     */
    class Edge {
        private Vertex toNode;
        private double currencyRate;

        public Edge(Vertex toNode, double currencyRate) {
            this.toNode = toNode;
            this.currencyRate = currencyRate;
        }

        public Vertex getToNode() {
            return toNode;
        }

        public double getCurrencyRate() {
            return currencyRate;
        }
    }
}

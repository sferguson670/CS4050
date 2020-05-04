/*
 * Sarah Ferguson
 * CS4050 - Assignment 5
 * Graph programming assignment where:
 * 1. build a directed weighted graph that represents exchange rates
 * 2. find sequences of trades that will results in a profit
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Currency {

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

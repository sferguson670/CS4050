/*
 * Sarah Ferguson
 * CS4050 - Assignment 5
 * Graph programming assignment where:
 * 1. build a directed weighted graph that represents exchange rates
 * 2. find sequences of trades that will results in a profit
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Currency {
    private static String INPUT_FILE = "exchangeRatesF2016.txt";
    private static WeightedGraph currencyGraph = new WeightedGraph();

    private void readFile() {
        boolean startOfFile = false;
        try {
            Scanner in = new Scanner(new FileInputStream(INPUT_FILE));
            while (in.hasNextLine()) {
                String line = in.nextLine();

                if (line.length() > 0 && line.charAt(0) == '*')
                    startOfFile = true;

                if (startOfFile && line.length() > 0 && Character.isAlphabetic(line.charAt(0))) {
                    String data[] = line.split("\\s+");
                    String fromCountry = data[0];
                    String toCountry = data[1];
                    double currencyRate = Double.parseDouble(data[2]);
                    addDataToGraph(fromCountry, toCountry, currencyRate);
                }
            }
            in.close();
        }
        catch (FileNotFoundException e) {
            //ignoring ...
        }
    }

    private void addDataToGraph(String fromCountry, String toCountry, double currencyRate) {
        if (getSpecifiedNode(fromCountry) == null) {
            addNodeToGraph(fromCountry);
        }
        if (getSpecifiedNode(toCountry) == null) {
            addNodeToGraph(toCountry);
        }
        addEdgeToGraph(fromCountry, toCountry, currencyRate);
    }

    /*
     * Looks through all existing nodes in graph,
     * if exists, then it will return specified country node
     * if not, then it will return null
     */
    private WeightedGraph.Node getSpecifiedNode(String country) {
        List<WeightedGraph.Node> existingNodes = currencyGraph.getVertices();
        for (int i = 0; i < existingNodes.size(); i++) {
            if (existingNodes.get(i).getCurrencyLabel().equals(country)) {
                return existingNodes.get(i);
            }
        }
        return null;
    }

    /*
     * Checks to see if country already exists as a node,
     * if it doesn't, then it adds it to the graph
     */
    private void addNodeToGraph(String country) {
        if (getSpecifiedNode(country) == null) {
            WeightedGraph.Node temp = new WeightedGraph.Node(country);
            currencyGraph.addVertex(temp);
        }
    }

    /*
     * Checks all graph vertices to find the fromCountry node,
     * then adds a new Edge with all provided info
     * and adds the edge to each of the nodes (to show reciprocal relationship)
     * ex) fromCountry ------> toCountry
     *     toCountry   ------> fromCountry
     */
    private void addEdgeToGraph(String fromCountry, String toCountry, double currencyRate) {
        WeightedGraph.Node fromCountryNode = getSpecifiedNode(fromCountry);
        WeightedGraph.Node toCountryNode = getSpecifiedNode(toCountry);

        // creates edge: fromCountry -> toCountry
        WeightedGraph.Edge edge1 = new WeightedGraph.Edge(toCountryNode, currencyRate);
        fromCountryNode.addEdge(edge1);

        // creates edge: toCountry -> fromCountry
        WeightedGraph.Edge edge2 = new WeightedGraph.Edge(fromCountryNode, currencyRate);
        toCountryNode.addEdge(edge2);
    }

    private void getTradeSequences() {

    }

    public static void main (String[] args) {
        Currency runner = new Currency();
        runner.readFile();
        List<WeightedGraph.Node> nodes = currencyGraph.getVertices();
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println(nodes.get(i).getCurrencyLabel());
            List<WeightedGraph.Edge> edges = nodes.get(i).getEdges();
            for (int j = 0; j < edges.size(); j++) {
                System.out.println(edges.get(j).getExchangeRate());
            }
        }
    }
}

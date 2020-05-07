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
    private static WeightedGraph currencyGraph = new WeightedGraph();
    private static String INPUT_FILE = "exchangeRatesF2016.txt";

    /*
     * Reads the input file,
     * goes through line by line and extracts needed data:
     * currency1, currency2, exchangeRate
     */
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
                    String fromCurrency = data[0];
                    String toCurrency = data[1];
                    double exchangeRate = Double.parseDouble(data[2]);
                    addDataToGraph(fromCurrency, toCurrency, exchangeRate);
                }
            }
            in.close();
        }
        catch (FileNotFoundException e) {
            //ignoring ...
        }
    }

    /*
     * Gets all inputs from each line of data,
     * will create nodes for each country if necessary,
     * will add edge between each country
     */
    private void addDataToGraph(String fromCurrency, String toCurrency, double exchangeRate) {
        if (getSpecifiedNode(fromCurrency) == null) {
            addNodeToGraph(fromCurrency);
        }
        if (getSpecifiedNode(toCurrency) == null) {
            addNodeToGraph(toCurrency);
        }
        addEdgeToGraph(fromCurrency, toCurrency, exchangeRate);
    }

    /*
     * Checks to see if country already exists as a node,
     * if it doesn't, then it adds it to the graph
     */
    private void addNodeToGraph(String currency) {
        if (getSpecifiedNode(currency) == null) {
            WeightedGraph.Node temp = new WeightedGraph.Node(currency);
            currencyGraph.addVertex(temp);
        }
    }

    /*
     * Looks through all existing nodes in graph,
     * if exists, then it will return specified country node
     * if not, then it will return null
     */
    private WeightedGraph.Node getSpecifiedNode(String currency) {
        List<WeightedGraph.Node> existingNodes = currencyGraph.getVertices();
        for (int i = 0; i < existingNodes.size(); i++) {
            if (existingNodes.get(i).getCurrencyLabel().equals(currency)) {
                return existingNodes.get(i);
            }
        }
        return null;
    }

    /*
     * Checks all graph vertices to find the fromCountry node,
     * then adds a new Edge with all provided info
     * and adds the edge to each of the nodes (to show reciprocal relationship)
     * ex) fromCurrency ------> toCurrency
     *     toCurrency   ------> fromCurrency
     */
    private void addEdgeToGraph(String fromCurrency, String toCurrency, double exchangeRate) {
        WeightedGraph.Node fromCurrencyNode = getSpecifiedNode(fromCurrency);
        WeightedGraph.Node toCurrencyNode = getSpecifiedNode(toCurrency);

        // creates edge: fromCurrency -> toCurrency
        WeightedGraph.Edge edge1 = new WeightedGraph.Edge(fromCurrencyNode, toCurrencyNode, exchangeRate);
        fromCurrencyNode.addEdge(edge1);

        // creates edge: toCurrency -> fromCurrency
        WeightedGraph.Edge edge2 = new WeightedGraph.Edge(toCurrencyNode, fromCurrencyNode, exchangeRate);
        toCurrencyNode.addEdge(edge2);
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

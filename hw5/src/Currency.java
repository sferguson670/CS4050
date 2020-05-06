/*
 * Sarah Ferguson
 * CS4050 - Assignment 5
 * Graph programming assignment where:
 * 1. build a directed weighted graph that represents exchange rates
 * 2. find sequences of trades that will results in a profit
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Currency {
    private static String INPUT_FILE = "exchangeRatesF2016.txt";

    private int[] fileLines;
    private int numOfCurrencies;

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
                }
            }
            in.close();
        }
        catch (FileNotFoundException e) {
            //ignoring ...
        }
    }

    private void addVertexToGraph(String country) {

    }

    private void addEdgeToGraph(String fromCountry, String toCountry, double currencyRate) {

    }

    private void getTradeSequences() {

    }

    public static void main (String[] args) {
        Currency runner = new Currency();
        runner.readFile();
        WeightedGraph graph = new WeightedGraph();
        WeightedGraph.Node node = new WeightedGraph.Node("hi");
        WeightedGraph.Node node2 = new WeightedGraph.Node("hello");
        graph.addVertex(node);
        WeightedGraph.Edge edge = new WeightedGraph.Edge(node2, 32.3);
        node.addEdge(edge);
        System.out.println(node.getEdges());
        System.out.println(graph.getVertices());
    }
}

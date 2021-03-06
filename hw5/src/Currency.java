/*
 * Sarah Ferguson
 * CS4050 - Assignment 5
 * Graph programming assignment where:
 * 1. build a directed weighted graph that represents exchange rates
 * 2. find sequences of trades that will results in a profit
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Currency {
    private static WeightedGraph currencyGraph = new WeightedGraph();
    private static ArrayList<ArrayList<WeightedGraph.Edge>> exchanges = new ArrayList<>();
    private static String INPUT_FILE = "exchangeRatesF2016.txt";
    private static String startingCountry = "Dollar";
    private static double startingInvestment = 1000;
    private static int numOfCurrencies;

    /*
     * Reads the input file,
     * goes through line by line and extracts needed data:
     * numOfCurrencies,
     * currency1, currency2, exchangeRate
     */
    private void readFile() {
        boolean startOfFile = false;
        try {
            Scanner in = new Scanner(new FileInputStream(INPUT_FILE));
            while (in.hasNextLine()) {
                String line = in.nextLine();

                if (line.length() > 0) {
                    char charAt0 = line.charAt(0);

                    if (charAt0 == '*')
                        startOfFile = true;

                    if (startOfFile && Character.isDigit(charAt0)) {
                        String[] data = line.split("\\s+");
                        numOfCurrencies = Integer.parseInt(data[0]);
                    }

                    if (startOfFile && Character.isAlphabetic(charAt0)) {
                        String[] data = line.split("\\s+");
                        String fromCurrency = data[0];
                        String toCurrency = data[1];
                        double exchangeRate = Double.parseDouble(data[2]);
                        addDataToGraph(fromCurrency, toCurrency, exchangeRate);
                    }
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
     * will create nodes for each currency if necessary,
     * will add edge between each currency
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
     * Checks to see if currency already exists as a node,
     * if it doesn't, then it adds it to the graph
     */
    private void addNodeToGraph(String currency) {
        if (getSpecifiedNode(currency) == null) {
            WeightedGraph.Node temp = new WeightedGraph.Node(currency);
            currencyGraph.addNode(temp);
        }
    }

    /*
     * Looks through all existing nodes in graph,
     * if exists, then it will return specified currency node
     * if not, then it will return null
     */
    private WeightedGraph.Node getSpecifiedNode(String currency) {
        List<WeightedGraph.Node> existingNodes = currencyGraph.getNodes();
        for (WeightedGraph.Node existingNode : existingNodes) {
            if (existingNode.getCurrencyLabel().equals(currency)) {
                return existingNode;
            }
        }
        return null;
    }

    /*
     * Checks all graph nodes to find the fromCurrency node,
     * then adds a new Edge with all provided info
     * and adds the edge to each of the nodes (to show reciprocal relationship)
     * ex) fromCurrency ------> toCurrency
     *                  (exchangeRate)
     *     toCurrency   ------> fromCurrency
     *                  (1/exchangeRate)
     */
    private void addEdgeToGraph(String fromCurrency, String toCurrency, double exchangeRate) throws NullPointerException {
        WeightedGraph.Node fromCurrencyNode = getSpecifiedNode(fromCurrency);
        WeightedGraph.Node toCurrencyNode = getSpecifiedNode(toCurrency);

        // creates edge: fromCurrency -> toCurrency
        WeightedGraph.Edge edge1 = new WeightedGraph.Edge(fromCurrencyNode, toCurrencyNode, exchangeRate);
        fromCurrencyNode.addEdge(edge1);

        // creates edge: toCurrency -> fromCurrency
        WeightedGraph.Edge edge2 = new WeightedGraph.Edge(toCurrencyNode, fromCurrencyNode, (1/exchangeRate));
        toCurrencyNode.addEdge(edge2);
    }

    /*
     * Goes through the weighted graph, and gets all possible currency exchange paths possible,
     * will cut off traversing the tree at numOfCurrencies - 1, so no repeated currencies appear,
     * and when path stops back at starting currency, that's the end of the path
     */
    private void getAllCurrencyExchanges(ArrayList<WeightedGraph.Edge> list, WeightedGraph.Edge exchange, int level) {
        if (level > numOfCurrencies - 1)
            return;

        level++;
        list.add(exchange);
        WeightedGraph.Node toNode = exchange.getToNode();

        if (toNode.getCurrencyLabel().equals(startingCountry)) {
            exchanges.add(list);
        } else {
            List<WeightedGraph.Edge> toNodeEdges = toNode.getEdges();
            for (WeightedGraph.Edge toNodeEdge : toNodeEdges) {
                getAllCurrencyExchanges(new ArrayList(list), toNodeEdge, level);
            }
        }
    }

    /*
     * Goes through the paths of each currency exchanges,
     * will calculate the end product of how much money is left,
     * returns this amount
     */
    private double calculateCurrencyExchanges(ArrayList<WeightedGraph.Edge> list, double startingInvestment) {
        double amount = startingInvestment;
        for (WeightedGraph.Edge edge : list) {
            double exchangeRate = edge.getExchangeRate();
            amount = amount * exchangeRate;
        }
        return amount;
    }

    /*
     * Goes through each list in the path and computes the value gained through exchanges,
     * if greater than the initial investment, then the profit and path is printed out
     */
    private void printsOutResults() {
        exchanges.forEach(exchange -> {
            double amount = calculateCurrencyExchanges(exchange, startingInvestment);
            double profit = amount - startingInvestment;
            if (profit > 0) {
                System.out.println("Profit earned: " + profit);
                System.out.println(exchange);
                System.out.println("*************************");
            }
        });
    }

    /*
     * After building the weighted graph from file,
     * looks for specified starting currency node,
     * will get each of its edge to look for all exchange paths
     */
    public static void main (String[] args) throws NullPointerException {
        Currency runner = new Currency();
        runner.readFile();

        WeightedGraph.Node start = runner.getSpecifiedNode(startingCountry);
        for (int i = 0; i < start.getEdges().size(); i++) {
            ArrayList<WeightedGraph.Edge> temp = new ArrayList<>();
            runner.getAllCurrencyExchanges(temp, start.getEdges().get(i), 0);
        }

        runner.printsOutResults();
    }
}

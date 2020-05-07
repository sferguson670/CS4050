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
    private static Map<ArrayList<WeightedGraph.Edge>, Double> exchanges = new HashMap<>();
    private static String INPUT_FILE = "exchangeRatesTest.txt";

    private static String startingCountry = "Canada";
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
            currencyGraph.addNode(temp);
        }
    }

    /*
     * Looks through all existing nodes in graph,
     * if exists, then it will return specified country node
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
     * Checks all graph vertices to find the fromCountry node,
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

    private void getAllCurrencyExchanges(ArrayList<WeightedGraph.Edge> list, WeightedGraph.Edge exchange, double investment, int level) {
        level++;
        list.add(exchange);
        //System.out.println(level);
        WeightedGraph.Node toNode = exchange.getToNode();
        //System.out.println(exchange.toString());

        if (level > numOfCurrencies - 1 || toNode.getCurrencyLabel().equals(startingCountry)) {
            if (toNode.getCurrencyLabel().equals(startingCountry)) {
                exchanges.put(list, investment);
                double exchangeAmount = getExchangeAmount(investment, exchange.getExchangeRate());
                //System.out.println(exchange.toString());
                //System.out.println("final currency: " + exchangeAmount + " at " + toNode.toString());
                //System.out.println("gained this amount: " + (exchangeAmount - startingInvestment));
                //System.out.println("**********************");
                /*
                if (exchangeAmount > startingInvestment) {
                    System.out.println(exchange.toString());
                    System.out.println("final currency: " + exchangeAmount + " at " + toNode.toString());
                    System.out.println("gained this amount: " + (exchangeAmount - startingInvestment));
                    System.out.println("**********************");
                }

                 */
            }
        } else {
            List<WeightedGraph.Edge> toNodeEdges = toNode.getEdges();
            for (int i = 0; i < toNodeEdges.size(); i++) {
                //list.add(toNodeEdges.get(i));
                double newInvestment = getExchangeAmount(investment, toNodeEdges.get(i).getExchangeRate());
                getAllCurrencyExchanges(list, toNodeEdges.get(i), newInvestment, level);
            }
        }
    }

    /*
     * For the start currency, will go through each of its edges and calculate exchange amount,
     * will return the a HashMap where corresponding edge and its currency exchange is represented
     */
    private Map<WeightedGraph.Edge, Double> getCurrencyExchanges(WeightedGraph.Node startCurrency, double investment) {
        Map<WeightedGraph.Edge, Double> currencyExchanges = new HashMap<>();

        List<WeightedGraph.Edge> edges = startCurrency.getEdges();
        for (WeightedGraph.Edge edge : edges) {
            currencyExchanges.put(edge, getExchangeAmount(investment, edge.getExchangeRate()));
        }

        return currencyExchanges;
    }

    /*
     * Returns the exchange amount: (investment * exchange rate)
     */
    private double getExchangeAmount(double investment, double exchangeRate) {
        return investment * exchangeRate;
    }

    public static void main (String[] args) {
        Currency runner = new Currency();
        runner.readFile();
        WeightedGraph.Node start = runner.getSpecifiedNode(startingCountry);
        //runner.getAllCurrencyExchanges(start.getEdges().get(0), startingInvestment, 1);
        for (int i = 0; i < start.getEdges().size(); i++) {
            ArrayList<WeightedGraph.Edge> temp = new ArrayList<>();
            runner.getAllCurrencyExchanges(temp, start.getEdges().get(i), startingInvestment, 0);
            //System.out.println(start.getEdges().get(i).toString());
        }

        Iterator iterator = exchanges.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey().toString());
            System.out.println(entry.getValue().toString());
            System.out.println("***************************");
        }
        /*
        Map<WeightedGraph.Edge, Double> currencyExchanges = runner.getCurrencyExchanges(currencyGraph.getNodes().get(0), 1000);
        Iterator iterator = currencyExchanges.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey().toString());
            System.out.println(entry.getValue().toString());
        }
         */
    }
}

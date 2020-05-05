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
        try {
            Scanner in = new Scanner(new FileInputStream(INPUT_FILE));
            while (in.hasNextLine()) {
                String line = in.nextLine();

                if (line.length() > 0 && line.charAt(0) == '*')
                    continue;

                System.out.println(line);
                /*
                String data[] = line.split(",");
                String fromCountry = data[0];
                String toCountry = data[1];
                double currencyRate = Double.parseDouble(data[2]);
                 */
            }
            in.close();
        }
        catch (FileNotFoundException e) {
            //ignoring ...
        }
    }

    private void buildWeightedGraph() {

    }

    private void getTradeSequences() {

    }

    public static void main (String[] args) {
        Currency runner = new Currency();
        runner.readFile();
    }
}

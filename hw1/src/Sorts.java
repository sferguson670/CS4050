import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Sorts {
    private static int ARRAY_SIZE = 100;
    private static int[] originalArray;
    private static int LARGE_REGION = 11;

    private static String originalArrayFile = "original-array-file.txt";
    private static String quickSortArrayFile = "quicksorted-array-file.txt";
    private static String medianQuickSortedArrayFile = "median-quicksorted-array-file.txt";

    private static void createRandomizedArray() {
        Random rd = new Random();
        originalArray = new int[ARRAY_SIZE];

        for (int i = 0; i < originalArray.length; i++) {
            originalArray[i] = rd.nextInt();
        }
    }

    private static int[] copyArray(int[] originalArray) {
        int size = originalArray.length;
        int[] copiedArray = new int[size];
        System.arraycopy(originalArray, 0, copiedArray, 0, size);

        return copiedArray;
    }

    /*
     * prints out array to specified file name
     */
    private static void printArrayToFile(int array[], String fileName) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(fileName));

        for (int i = 0; i < array.length; i++) {
            outputWriter.write(Integer.toString(array[i]));
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }

    public static void main(String[] args) throws Exception {
        createRandomizedArray();

        int[] copiedArray = copyArray(originalArray);
        QuickSort ob = new QuickSort();
        ob.quickSort(copiedArray, 0, copiedArray.length-1);
        printArrayToFile(originalArray, originalArrayFile);
        printArrayToFile(copiedArray, quickSortArrayFile);

        int[] medianArray = copyArray(originalArray);
        ob.medianQuickSort(medianArray, 0, medianArray.length-1);
        printArrayToFile(medianArray, medianQuickSortedArrayFile);

        int[] quicksortAndInsertionArray = copyArray(originalArray);
        ob.quickSortAndInsertionSort(quicksortAndInsertionArray, LARGE_REGION);
        printArrayToFile(quicksortAndInsertionArray, "quicksort-and-insertion-array-file.txt");

        int[] lomutoArray = copyArray(originalArray);
        ob.quickSortWithLomutos(lomutoArray, 0, lomutoArray.length-1);
        printArrayToFile(lomutoArray, "lomuto-array-file.txt");

        int[] hoareArray = copyArray(originalArray);
        ob.quickSortWithHoares(hoareArray, 0, hoareArray.length-1);
        printArrayToFile(hoareArray, "hoare-array-file.txt");
    }
}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class QuickSort {

    private static int ARRAY_SIZE = 100;
    private static int[] originalArray;

    private static String originalArrayFile = "original-array-file.txt";
    private static String sortedArrayFile = "sorted-array-file.txt";

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

    // Quicksort method
    public void quickSort(int array[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);

            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    // Partitioning method, uses last element as pivot
    private int partition(int array[], int begin, int end) {
        int pivot = array[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            // if current element is smaller than pivot
            if (array[j] <= pivot) {
                i++;

                // swap array[i] and array[j]
                int swapTemp = array[i];
                array[i] = array[j];
                array[j] = swapTemp;
            }
        }
        // swap array[i+1] and array[end] (or pivot)
        int swapTemp = array[i+1];
        array[i+1] = array[end];
        array[end] = swapTemp;

        return i+1;
    }

    // prints out array to specified file name
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
        //run the class
        createRandomizedArray();
        int[] copiedArray = copyArray(originalArray);

        QuickSort ob = new QuickSort();
        ob.quickSort(copiedArray, 0, copiedArray.length-1);
        printArrayToFile(originalArray, originalArrayFile);
        printArrayToFile(copiedArray, sortedArrayFile);
    }
}

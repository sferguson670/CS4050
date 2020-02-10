import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Sorts {
    private static int[] originalArray;

    private static int ARRAY_SIZE = 1000;
    private static int LARGE_REGION = 11;

    private static String originalArrayFile = "original-unsorted-array.txt";
    private static String quickSortArrayFile = "quicksorted-array-file.txt";
    private static String medianQuickSortedArrayFile = "median-quicksorted-array-file.txt";
    private static String quickSortAndInsertionArrayFile = "quicksorted-insertion-array-file.txt";
    private static String quickSortLomutosPartitionArrayFile = "quicksorted-lomutos-array-file.txt";
    private static String quickSortHoarePartitionArrayFile = "quicksorted-hoare-array-file.txt";

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
        outputWriter = new BufferedWriter(new FileWriter("./array-files/" + fileName));

        for (int i = 0; i < array.length; i++) {
            outputWriter.write(Integer.toString(array[i]));
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }

    private static Long getSystemTime() {
        return System.nanoTime();
    }

    private static Long timeTaken (Long first, Long end) {
        return end - first;
    }

    public static void main(String[] args) throws Exception {
        createRandomizedArray();
        printArrayToFile(originalArray, originalArrayFile);
        QuickSort ob = new QuickSort();

        int[] quicksortArray = copyArray(originalArray);
        Long start = getSystemTime();
        ob.quickSort(quicksortArray, 0, quicksortArray.length-1);
        Long end = getSystemTime();
        System.out.println("time taken: " + timeTaken(start, end) + " nanoseconds");
        printArrayToFile(quicksortArray, quickSortArrayFile);

        int[] medianArray = copyArray(originalArray);
        Long start2 = getSystemTime();
        ob.medianQuickSort(medianArray, 0, medianArray.length-1);
        Long end2 = getSystemTime();
        System.out.println("time taken: " + timeTaken(start2, end2) + " nanoseconds");
        printArrayToFile(medianArray, medianQuickSortedArrayFile);

        int[] quicksortInsertionArray = copyArray(originalArray);
        Long start3 = getSystemTime();
        ob.quickSortAndInsertionSort(quicksortInsertionArray, LARGE_REGION);
        Long end3 = getSystemTime();
        System.out.println("time taken: " + timeTaken(start3, end3) + " nanoseconds");
        printArrayToFile(quicksortInsertionArray, quickSortAndInsertionArrayFile);

        int[] lomutoArray = copyArray(originalArray);
        Long start4 = getSystemTime();
        ob.quickSortWithLomutos(lomutoArray, 0, lomutoArray.length-1);
        Long end4 = getSystemTime();
        System.out.println("time taken: " + timeTaken(start4, end4) + " nanoseconds");
        printArrayToFile(lomutoArray, quickSortLomutosPartitionArrayFile);

        int[] hoareArray = copyArray(originalArray);
        Long start5 = getSystemTime();
        ob.quickSortWithHoares(hoareArray, 0, hoareArray.length-1);
        Long end5 = getSystemTime();
        System.out.println("time taken: " + timeTaken(start5, end5) + " nanoseconds");
        printArrayToFile(hoareArray, quickSortHoarePartitionArrayFile);
    }
}

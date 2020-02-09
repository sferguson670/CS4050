import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class QuickSort {

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
     * "Original" quick sort method
     */
    public void quickSort(int array[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);

            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    /*
     * Partitioning method, uses last element as pivot
     */
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

    /*
     * Quicksort with median of 3
     */
    public void medianQuickSort(int array[], int low, int high) {
        if (low >= high)
            return ;
        if (low < high) {
            medianPivot(array, low, high);
            quickSort(array, low, high);
        }
    }

    /*
     * create subarray with low, high, and middle elements in the array,
     * sort the subarray and use index 1 as the median of 3
     */
    public int medianPivot(int array[], int low, int high) {
        int first = array[low];
        int last = array[array.length - 1];
        int mid = (high) / 2;

        int[] sortingArray = {array[low], array[mid], array[high]};
        Arrays.sort(sortingArray);
        int middleValue = sortingArray[1];

        // swap with the last to serve as pivot
        int temp = array[high];
        array[high] = middleValue;
        if (middleValue == array[low]) {
            array[low] = temp;
        } else if (middleValue == array[mid]) {
            array[mid] = temp;
        }

        return partition(array, low, high);
    }

    public void insertionSort(int array[]) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            /*
             * move elements of array[0..i-1], that are greater than key,
             * to one position ahead of their current position
             */
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public void quickSortAndInsertionSort(int array[]) {
        quickSort(array, 0, LARGE_REGION);
        insertionSort(array);
    }

    private static void swap (int array[], int position1, int position2) {
        int temp = array[position1];
        array[position1] = array[position2];
        array[position2] = temp;
    }

    /*
     * takes last element as pivot, places the pivot element in correct position in sorted array,
     * places all smaller than pivot to left of pivot
     * and all greater elements than pivot to right of pivot
     */
    private int lomutosPartition(int array[], int low, int high) {
        int pivot = array[high];

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            // if current element is <= to pivot
            if (array[j] <= pivot)
            {
                i++;
                swap(array, i, j);
            }
        }
        swap (array, i+1, high);
        return (i+1);
    }

    public void quickSortWithLomutos(int array[], int low, int high) {
        if (low < high) {
            int partitionIndex = lomutosPartition(array, low, high);

            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    private int hoaresPartition(int array[], int low, int high) {
        int pivot = array[low];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            // find leftmost element greater than or equal to pivot
            do {
                i++;
            } while (array[i] < pivot);

            // find rightmost element smaller than or equal to pivot
            do {
                j--;
            } while (array[j] > pivot);

            // if 2 pointers meet
            if (i >= j) {
                return j;
            }
            swap(array, i, j);
        }
    }

    public void quickSortWithHoares(int array[], int low, int high) {
        if (low < high) {
            int partitionIndex = hoaresPartition(array, low, high);

            quickSort(array, low, partitionIndex);
            quickSort(array, partitionIndex + 1, high);
        }
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
        //run the class
        createRandomizedArray();

        int[] copiedArray = copyArray(originalArray);
        QuickSort ob = new QuickSort();
        ob.quickSort(copiedArray, 0, copiedArray.length-1);
        printArrayToFile(originalArray, originalArrayFile);
        printArrayToFile(copiedArray, quickSortArrayFile);

        int[] medianArray = copyArray(originalArray);
        ob.medianQuickSort(medianArray, 0, medianArray.length-1);
        printArrayToFile(medianArray, medianQuickSortedArrayFile);

        int[] insertionArray = copyArray(originalArray);
        ob.insertionSort(insertionArray);
        printArrayToFile(insertionArray, "insertion-array-file.txt");

        int[] quicksortAndInsertionArray = copyArray(originalArray);
        ob.quickSortAndInsertionSort(quicksortAndInsertionArray);
        printArrayToFile(quicksortAndInsertionArray, "quicksort-and-insertion-array-file.txt");

        int[] lomutoArray = copyArray(originalArray);
        ob.quickSortWithLomutos(lomutoArray, 0, lomutoArray.length-1);
        printArrayToFile(lomutoArray, "lomuto-array-file.txt");

        int[] hoareArray = copyArray(originalArray);
        ob.quickSortWithHoares(hoareArray, 0, hoareArray.length-1);
        printArrayToFile(hoareArray, "hoare-array-file.txt");
    }
}

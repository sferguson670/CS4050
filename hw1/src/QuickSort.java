import java.util.Arrays;

public class QuickSort {

    private static void swap (int array[], int position1, int position2) {
        int temp = array[position1];
        array[position1] = array[position2];
        array[position2] = temp;
    }

    /*
     * "Original" quick sort method
     */
    public void quickSort(int array[], int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);

            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
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
                swap(array, i, j);
            }
        }
        // swap array[i+1] and array[end] (or pivot)
        swap(array, i+1, end);

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

    public void quickSortAndInsertionSort(int array[], int largeRegion) {
        quickSort(array, 0, largeRegion);
        insertionSort(array);
    }

    private void insertionSort(int array[]) {
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

    public void quickSortWithLomutos(int array[], int low, int high) {
        if (low < high) {
            int partitionIndex = lomutosPartition(array, low, high);

            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
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

    public void quickSortWithHoares(int array[], int low, int high) {
        if (low < high) {
            int partitionIndex = hoaresPartition(array, low, high);

            quickSort(array, low, partitionIndex);
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
}

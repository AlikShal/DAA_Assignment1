package alikhan;

public class MergeSort {
    private static final int CUTOFF = 24;

    // Method to sort the array and track performance metrics
    public static AlgorithmMetricsTracker sort(int[] arr) {
        AlgorithmMetricsTracker metrics = new AlgorithmMetricsTracker();
        if (arr == null || arr.length < 2) return new AlgorithmMetricsTracker();

        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1, metrics);
        metrics.incrementMemoryAllocationCount(); // Account for final allocation after sorting

        return metrics;
    }

    // Recursive mergeSort method
    private static void mergeSort(int[] arr, int[] buffer, int left, int right, AlgorithmMetricsTracker metrics) {
        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right, metrics);
            return;
        }

        int mid = left + (right - left) / 2;

        metrics.increaseRecursionDepth();  // Increase recursion depth

        mergeSort(arr, buffer, left, mid, metrics);  // Sort left half
        mergeSort(arr, buffer, mid + 1, right, metrics);  // Sort right half
        metrics.decreaseRecursionDepth();  // Decrease recursion depth after sorting both halves

        merge(arr, buffer, left, mid, right, metrics);  // Merge the two halves
    }

    // Merge two sorted halves of the array
    private static void merge(int[] arr, int[] buffer, int left, int mid, int right, AlgorithmMetricsTracker metrics) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);  // Copy the current subarray to buffer

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            metrics.incrementComparisonCount();  // Increment comparison count
            if (buffer[i] <= buffer[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = buffer[j++];
            }
        }

        // Copy remaining elements from left half if any
        while (i <= mid) {
            arr[k++] = buffer[i++];
        }

        // Copy remaining elements from right half if any
        while (j <= right) {
            arr[k++] = buffer[j++];
        }
    }

    // Insertion sort for smaller subarrays
    private static void insertionSort(int[] arr, int left, int right, AlgorithmMetricsTracker metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[lb..i-1] that are greater than key to the right
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                metrics.incrementComparisonCount();  // Track comparison count
            }
            arr[j + 1] = key;  // Insert the key into the correct position
        }
    }
}


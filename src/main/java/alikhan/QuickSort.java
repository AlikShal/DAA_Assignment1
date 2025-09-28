package alikhan;
import java.util.Random;

public class QuickSort {

    private static final Random RANDOM = new Random();

    // Method to sort the array and track performance metrics
    public static AlgorithmMetricsTracker sort(int[] arr) {
        AlgorithmMetricsTracker metrics = new AlgorithmMetricsTracker();
        if (arr != null && arr.length >= 2) {
            quickSortLoop(arr, 0, arr.length - 1, metrics);
        }
        // Ensure usage of the metrics object
        System.out.println("Comparisons: " + metrics.getTotalComparisons());
        System.out.println("Memory Allocations: " + metrics.getTotalMemoryAllocations());
        System.out.println("Max Recursion Depth: " + metrics.getPeakRecursionDepth());
        return metrics;  // Return the tracked metrics
    }

    // Main quickSort loop
    private static void quickSortLoop(int[] arr, int low, int high, AlgorithmMetricsTracker metrics) {
        while (low < high) {
            int pivotIndex = partition(arr, low, high, metrics);
            if (pivotIndex - low < high - pivotIndex) {
                quickSortLoop(arr, low, pivotIndex - 1, metrics);
                low = pivotIndex + 1;
            } else {
                quickSortLoop(arr, pivotIndex + 1, high, metrics);
                high = pivotIndex - 1;
            }
        }
    }

    // Partition method using a random pivot
    private static int partition(int[] arr, int low, int high, AlgorithmMetricsTracker metrics) {
        int randomIndex = low + RANDOM.nextInt(high - low + 1);
        swap(arr, randomIndex, high);

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            metrics.incrementComparisonCount();  // Track comparisons
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
                metrics.incrementMemoryAllocationCount();  // Track memory allocations (swaps)
            }
        }
        swap(arr, i + 1, high);
        metrics.incrementMemoryAllocationCount();  // Track memory allocation for final swap
        return i + 1;
    }

    // Helper method to swap elements
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

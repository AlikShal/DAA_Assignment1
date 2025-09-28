package alikhan;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

public class AlgorithmMetricsTracker {
    private final AtomicInteger totalComparisons = new AtomicInteger(0);
    private final AtomicInteger totalMemoryAllocations = new AtomicInteger(0);
    private final AtomicInteger currentDepth = new AtomicInteger(0);
    private final AtomicInteger peakRecursionDepth = new AtomicInteger(0);

    // Increment the count of comparisons
    public void incrementComparisonCount() {
        totalComparisons.incrementAndGet();
    }

    // Increment the count of memory allocations
    public void incrementMemoryAllocationCount() {
        totalMemoryAllocations.incrementAndGet();
    }

    // Increase the current recursion depth and track peak depth
    public void increaseRecursionDepth() {
        int depth = currentDepth.incrementAndGet();
        peakRecursionDepth.updateAndGet(max -> Math.max(max, depth));
    }

    // Decrease the current recursion depth
    public void decreaseRecursionDepth() {
        currentDepth.decrementAndGet();
    }

    // Reset all metrics counters
    public void resetMetrics() {
        totalComparisons.set(0);
        totalMemoryAllocations.set(0);
        currentDepth.set(0);
        peakRecursionDepth.set(0);
    }

    // Write the tracked metrics to a CSV file
    public void saveMetricsToCSV(long elapsedTime, String algorithmName) throws IOException {
        try (FileWriter fileWriter = new FileWriter("target/algorithm_metrics.csv", true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.printf("%s, %d, %d, %d, %d\n",
                    algorithmName,
                    elapsedTime,
                    totalComparisons.get(),
                    totalMemoryAllocations.get(),
                    peakRecursionDepth.get());
        }
    }

    // Getter methods to access the metrics if needed
    public int getTotalComparisons() {
        return totalComparisons.get();
    }

    public int getTotalMemoryAllocations() {
        return totalMemoryAllocations.get();
    }

    public int getPeakRecursionDepth() {
        return peakRecursionDepth.get();
    }
}





# Assignment 1: Divide-and-Conquer

This project provides implementations of classic divide-and-conquer algorithms in **Java 17**, focusing on safe recursion, performance measurement, and validation against theoretical expectations.

## 1. Implementation Overview

The algorithms employ standard divide-and-conquer strategies with optimizations to manage recursion depth and memory usage:

### MergeSort

* Implements divide-and-conquer with recursion depth approximately **log₂(n)**.
* Uses a **reusable buffer array** to minimize memory allocations.
* Switches to **Insertion Sort** for very small arrays (n < 10) to reduce overhead.

### QuickSort

* Selects the pivot randomly.
* Recurses on the **smaller subarray first** to control stack depth.
* Recursion depth is typically bounded by **2·log₂(n)** on average.

### Deterministic Select (Median-of-Medians)

* Groups elements in sets of 5 to compute the pivot.
* Recurses only into the partition containing the **k-th element**.
* Recursion depth is approximately **log₂(n)**.

### Closest Pair of Points

* Divides points based on **x-coordinates**.
* The strip check examines **up to 7 neighboring points** to find the closest pair.
* Recursion depth remains roughly **log₂(n)**.



## 2. Recurrence and Complexity Analysis

The expected behavior of each algorithm is summarized below:

### MergeSort

* Recurrence: **T(n) = 2T(n/2) + Θ(n)**
* Analysis via the Master Theorem, Case 2 → **Θ(n log n)**
* Recursion depth is consistent with **log₂(n)**, and runtime aligns with theoretical predictions.

### QuickSort

* Recurrence: **T(n) = T(U) + T(n-U-1) + Θ(n)**, where U is the pivot index
* Randomized pivot ensures expected runtime **Θ(n log n)**; worst-case is **Θ(n²)**
* Recursing on the smaller partition keeps recursion depth ≤ **2·log₂(n)**.

### Deterministic Select (MoM5)

* Recurrence: **T(n) = T(n/5) + T(≤7n/10) + Θ(n)**
* Solved using Akra–Bazzi → **Θ(n)**
* Recursion depth is approximately **log₂(n)**.

### Closest Pair of Points (2D)

* Recurrence: **T(n) = 2T(n/2) + Θ(n)**
* Master Theorem, Case 2 → **Θ(n log n)**
* Strip check adds a constant factor; recursion depth remains around **log₂(n)**.


## 3. Performance Visualization

Graphs included in the project illustrate how execution time and recursion depth vary with input size, providing a clear view of each algorithm’s practical performance characteristics.




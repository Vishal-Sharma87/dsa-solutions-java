package heap.hard;

// Created at: 04-May-2026
// Last revised at: 04-May-2026
// Link: https://www.geeksforgeeks.org/problems/maximum-sum-combination/1
// Time Complexity: O(n² log k)
// Space Complexity: O(k)

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/*
 * Problem Description:
 *   Given two integer arrays a[] and b[] of equal size n, and an integer k,
 *   return the top k maximum possible sums formed by picking one element
 *   from each array (a[i] + b[j]), in descending order.
 *
 *   Example:
 *     Input:  a = [1, 4, 2, 3], b = [2, 5, 1, 6], k = 3
 *     Output: [10, 9, 9]
 *     Explanation: 4+6=10, 3+6=9, 4+5=9
 *
 *   Constraints:
 *     1 <= n <= 10^3
 *     1 <= k <= n
 *     1 <= a[i], b[i] <= 10^4
 *
 * Approaches:
 *
 *   Approach 1 — Brute Force:
 *     Idea: Generate all n² sums, sort descending, return first k.
 *     Time: O(n² log n²)  Space: O(n²)  Drawbacks: Stores all pairs; wastes memory.
 *
 *   Approach 2 — Min-Heap with Boundary Pruning ★:
 *     Key Insight: Maintain a min-heap of size k. Track the current minimum
 *     in the heap as `boundary`. Any new combination sum <= boundary can never
 *     displace an existing top-k entry, so skip it entirely. When the heap
 *     hits size k+1, evict the smallest and raise the boundary — this prunes
 *     an increasing fraction of pairs as the heap fills.
 *     Time: O(n² log k)  Space: O(k)
 *
 *   Approach 3 — Sort + Max-Heap (Optimal):
 *     Key Insight: Sort both arrays descending. The globally largest sum is
 *     always a[0]+b[0]. Use a max-heap seeded with that pair; on each pop
 *     push neighbours (i+1,j) and (i,j+1) with a visited set. Extracts
 *     exactly k sums without scanning all n² pairs.
 *     Time: O(n log n + k log k)  Space: O(k)
 */

class GFG_MaximumSumCombination {

    /**
     * Returns the top k maximum sums, each formed by one element from a and one
     * from b.
     *
     * @param a first integer array
     * @param b second integer array
     * @param k number of top sums to return
     * @return list of top k sums in descending order
     */
    public ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
        int aLen = a.length;
        int bLen = b.length;

        if (aLen * bLen < k)
            return new ArrayList<>();

        int minHeapSize = k + 1; // eviction trigger, same pattern as LC703
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(minHeapSize);
        int boundary = -1; // minimum sum currently in top-k; skip anything <= this

        for (int i = 0; i < aLen; i++) {
            for (int j = 0; j < bLen; j++) {
                int combinationSum = a[i] + b[j];

                if (combinationSum > boundary) {
                    minHeap.offer(combinationSum);
                    // heap full — evict smallest and raise the bar
                    if (minHeap.size() == minHeapSize)
                        boundary = Math.max(boundary, minHeap.poll());
                }
            }
        }

        ArrayList<Integer> topKSum = new ArrayList<>();
        while (!minHeap.isEmpty())
            topKSum.add(minHeap.poll()); // polls ascending

        Collections.reverse(topKSum); // flip to descending
        return topKSum;
    }
}

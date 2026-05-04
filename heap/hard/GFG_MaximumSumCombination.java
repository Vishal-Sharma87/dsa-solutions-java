package heap.hard;
// Created at: 04-May-2026

// Last revised at: 04-May-2026
// Link: https://www.geeksforgeeks.org/problems/maximum-sum-combination/1
// Time Complexity: O(n log n + k log k)
// Space Complexity: O(k)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

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
 *   Approach 2 — Min-Heap with Boundary Pruning:
 *     Idea: Maintain a min-heap of size k. Skip any sum <= current heap minimum.
 *     Time: O(n² log k)  Space: O(k)  Drawbacks: Still scans all n² pairs.
 *
 *   Approach 3 — Sort + Max-Heap + Visited Set ★:
 *     Key Insight: Sort both arrays descending. The globally largest sum is
 *     always a[0]+b[0]. Model the pair grid as a graph — when you pop (i,j),
 *     push only neighbours (i+1,j) and (i,j+1). This guarantees candidates
 *     are explored in non-increasing sum order without scanning all n² pairs.
 *     A visited set (encoded as i*n+j) prevents duplicate pushes since
 *     (i+1,j) and (i,j+1) can both later push (i+1,j+1).
 *     Time: O(n log n + k log k)  Space: O(k)
 */

class GFG_MaximumSumCombination {

    class Pair {
        int sum, i, j;

        Pair(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
    }

    /**
     * Checks whether (i, j) is a valid index pair within the array bounds.
     *
     * @param i   row index (into array a)
     * @param j   col index (into array b)
     * @param len length of both arrays
     * @return true if both indices are within [0, len)
     */
    private boolean isValid(int i, int j, int len) {
        return i < len && j < len; // no lower-bound check needed; i,j always >= 0 here
    }

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
        int n = a.length;

        if ((long) n * n < k)
            return new ArrayList<>();

        // sort both ascending then reverse in-place — avoids boxing overhead
        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
            int u = b[i];
            b[i] = b[j];
            b[j] = u;
        }

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((x, y) -> y.sum - x.sum);
        Set<Integer> visited = new HashSet<>();

        maxHeap.offer(new Pair(a[0] + b[0], 0, 0));
        visited.add(0); // encodes (0,0) as 0*n+0 = 0

        ArrayList<Integer> ans = new ArrayList<>();

        while (k-- > 0) {
            Pair p = maxHeap.poll();
            ans.add(p.sum);

            // neighbour: move down in a
            int ni = p.i + 1, nj = p.j;
            if (isValid(ni, nj, n) && visited.add(ni * n + nj))
                maxHeap.offer(new Pair(a[ni] + b[nj], ni, nj));

            // neighbour: move right in b
            ni = p.i;
            nj = p.j + 1;
            if (isValid(ni, nj, n) && visited.add(ni * n + nj))
                maxHeap.offer(new Pair(a[ni] + b[nj], ni, nj));
        }

        return ans;
    }
}
package heap.hard;

// Created at: 04-May-2026
// Last revised at: 04-May-2026
// Link: https://leetcode.com/problems/top-k-frequent-elements/
// Time Complexity: O(n log k)
// Space Complexity: O(n)

import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * Problem Description:
 *   Given an integer array nums and an integer k, return the k most
 *   frequent elements in any order.
 *
 *   Example:
 *     Input:  nums = [1,1,1,2,2,3], k = 2
 *     Output: [1, 2]
 *
 *   Constraints:
 *     1 <= nums.length <= 10^5
 *     -10^4 <= nums[i] <= 10^4
 *     k is in range [1, number of unique elements]
 *     Answer is guaranteed to be unique.
 *
 * Approaches:
 *
 *   Approach 1 — Sort by frequency:
 *     Idea: Build frequency map, sort entries descending by frequency, take first k.
 *     Time: O(n log n)  Space: O(n)  Drawbacks: Sorts all unique elements unnecessarily.
 *
 *   Approach 2 — Min-Heap of size k ★ (implemented below):
 *     Key Insight: Same eviction pattern as LC703 — maintain top-k by frequency.
 *     Min-heap root is always the weakest in the current top-k; evict when size
 *     hits k+1. One pass over the frequency map suffices.
 *     Time: O(n log k)  Space: O(n)
 *
 *   Approach 3 — Bucket Sort (Optimal):
 *     Key Insight: Frequency of any element is bounded by n. Create n+1 buckets
 *     indexed by frequency; bucket[f] holds all elements with exactly f occurrences.
 *     Scan buckets right-to-left and collect k elements — no heap, no sorting.
 *     Time: O(n)  Space: O(n)
 */

class LC347TopKFrequentElements {

    class Pair {
        int val, frequency;

        Pair(int val, int frequency) {
            this.val = val;
            this.frequency = frequency;
        }
    }

    /**
     * Returns the k most frequent elements from nums.
     *
     * @param nums input integer array
     * @param k    number of top frequent elements to return
     * @return array of k most frequent elements
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> valToFrequency = new HashMap<>();
        for (int num : nums)
            valToFrequency.put(num, 1 + valToFrequency.getOrDefault(num, 0));

        int size = k + 1; // eviction trigger
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(size, (x, y) -> x.frequency - y.frequency);

        valToFrequency.forEach((val, freq) -> {
            minHeap.offer(new Pair(val, freq));
            if (minHeap.size() == size)
                minHeap.poll(); // evict least frequent
        });

        int[] ans = new int[k];
        for (int i = 0; i < k; i++)
            ans[i] = minHeap.poll().val;

        return ans;
    }
}
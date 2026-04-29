package heap.medium;

// Created at: 30-Apr-2025
// Last revised at: 30-Apr-2025
// Link: https://leetcode.com/problems/kth-largest-element-in-an-array/
// Time Complexity: O(n log k)
// Space Complexity: O(k)

import java.util.PriorityQueue;

/*
 * Problem Description:
 * Given an integer array nums and an integer k, return the kth largest element
 * in the array. Note that it is the kth largest in sorted order, not the kth
 * distinct element.
 *
 * Example:
 *   Input : nums = [3,2,1,5,6,4], k = 2
 *   Output: 5
 *
 *   Input : nums = [3,2,3,1,2,4,5,5,6], k = 4
 *   Output: 4
 *
 * Constraints:
 *   1 <= k <= nums.length <= 10^5
 *   -10^4 <= nums[i] <= 10^4
 *
 * ---
 *
 * Approaches:
 *
 * 1. Sort + Index
 *    Idea    : Sort descending, return nums[k-1].
 *    Time    : O(n log n)
 *    Space   : O(1) or O(n) depending on sort
 *    Drawback: Sorts the entire array even though we only need the kth element.
 *
 * 2. Min-Heap of size k (used below)
 *    Idea      : Keep a min-heap of the k largest elements seen so far.
 *                Whenever the heap exceeds size k, evict the smallest.
 *                At the end, the heap root is the kth largest.
 *    Time      : O(n log k) — each insert/evict costs log k
 *    Space     : O(k)
 *    Key Insight: A min-heap of size k naturally holds the k largest values,
 *                 with the kth largest sitting at the root (minimum of the group).
 *
 * Method to solve:
 *   1. Edge-guard: return -1 if array is empty or k exceeds length.
 *   2. Initialize a min-heap (PriorityQueue defaults to min-heap in Java).
 *   3. Iterate through nums:
 *      a. Add nums[i] to the heap.
 *      b. If heap size hits k+1, poll — this evicts the current smallest,
 *         keeping only the k largest candidates.
 *   4. After the loop, heap has exactly k elements. Poll returns the kth largest.
 */

class LC215FindKthLargest {

    /**
     * Finds the kth largest element in an unsorted array using a min-heap.
     *
     * @param nums the input integer array
     * @param k    the rank from the largest end (1-indexed)
     * @return the kth largest element, or -1 if input is invalid
     */
    public int findKthLargest(int[] nums, int k) {

        int len = nums.length;
        if (len == 0 || len < k)
            return -1;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k + 1);

        for (int i = 0; i < len; i++) {
            minHeap.offer(nums[i]);
            // evict the smallest once we exceed k; keeps only the k largest
            if (minHeap.size() == k + 1) {
                minHeap.poll();
            }
        }

        // root of the min-heap is the kth largest
        return minHeap.poll();
    }
}
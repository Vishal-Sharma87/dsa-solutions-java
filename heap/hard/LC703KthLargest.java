package heap.hard;

// Created at: 04-May-2026
// Last revised at: 04-May-2026
// Link: https://leetcode.com/problems/kth-largest-element-in-a-stream/
// Time Complexity: O(n log k) — constructor; O(log k) — add()
// Space Complexity: O(k)

import java.util.PriorityQueue;

/*
 * Problem Description:
 *   Design a class that finds the k-th largest element in a stream.
 *   "k-th largest" means the k-th largest in sorted order, not the k-th distinct element.
 *   Implement KthLargest(int k, int[] nums) and int add(int val).
 *
 *   Example:
 *     KthLargest obj = new KthLargest(3, [4, 5, 8, 2]);
 *     obj.add(3) → 4   // stream: [2,3,4,5,8], 3rd largest = 4
 *     obj.add(5) → 5   // stream: [2,3,4,5,5,8], 3rd largest = 5
 *     obj.add(10) → 5
 *     obj.add(9)  → 8
 *     obj.add(4)  → 8
 *
 *   Constraints:
 *     1 <= k <= 10^4
 *     0 <= nums.length <= 10^4
 *     -10^4 <= nums[i], val <= 10^4
 *     At most 10^4 calls to add().
 *     It is guaranteed that there will be at least k elements in the array
 *     when you search for the k-th element.
 *
 * Approaches:
 *
 *   Approach 1 — Sort on every add():
 *     Idea: Maintain a list, sort it, return element at index (size - k).
 *     Time: O(n log n) per add()  Space: O(n)  Drawbacks: Way too slow for a stream.
 *
 *   Approach 2 — Min-Heap of size k ★:
 *     Key Insight: Keep only the top-k largest elements in a min-heap.
 *     The heap root is always the k-th largest — anything smaller than
 *     the root can never be the answer, so evict it immediately.
 *     Use capacity = k+1 as the eviction trigger: offer first, then poll
 *     if size hits k+1. This keeps the heap at exactly k elements.
 *     Time: O(n log k) constructor, O(log k) add()  Space: O(k)
 */

class LC703KthLargest {

    private final PriorityQueue<Integer> minHeap;
    private final int capacity; // eviction trigger = k+1

    /**
     * Initialises the stream with existing elements and target rank k.
     *
     * @param k    the rank to track (k-th largest)
     * @param nums pre-existing elements in the stream
     */
    public LC703KthLargest(int k, int[] nums) {
        capacity = k + 1;
        minHeap = new PriorityQueue<>(capacity);

        for (int val : nums) {
            minHeap.offer(val);
            if (minHeap.size() == capacity)
                minHeap.poll(); // evict smallest beyond top-k
        }
    }

    /**
     * Adds a new value to the stream and returns the current k-th largest.
     *
     * @param val the new element arriving in the stream
     * @return the k-th largest element after insertion
     */
    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() == capacity)
            minHeap.poll(); // same eviction logic as constructor
        return minHeap.peek(); // root of min-heap = k-th largest
    }
}
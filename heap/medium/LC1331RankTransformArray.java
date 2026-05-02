package heap.medium;

import java.util.PriorityQueue;

public class LC1331RankTransformArray {
    // Created at: 03-May-2026
    // Last revised at: 03-May-2026
    // Link: https://leetcode.com/problems/rank-transform-of-an-array/
    // Time Complexity: O(N log N) — each element pushed/popped from heap once
    // Space Complexity: O(N) — heap + answer array

    /*
     * Problem Description:
     * Given an integer array nums, replace each element with its rank.
     * Rank represents how large the element is relative to all others.
     * Rules: rank starts at 1, is an integer, larger element → larger rank,
     * equal elements have the same rank, rank should be as small as possible.
     *
     * Example:
     * Input: nums = [40, 10, 20, 30]
     * Output: [4, 1, 2, 3]
     *
     * Constraints:
     * - 0 <= nums.length <= 10^5
     * - -10^9 <= nums[i] <= 10^9
     *
     * Approaches:
     *
     * 1. Sort + HashMap
     * Idea: Sort a copy, deduplicate, assign ranks via map, then fill answer.
     * Time: O(N log N) Space: O(N)
     * Drawback: Extra map + dedup step; slightly more code.
     *
     * ★ 2. Min-Heap with (value, originalIndex) pairs (Optimal in simplicity)
     * Key Insight: Poll elements in ascending order of value; increment rank
     * only when the value actually changes (handles ties). Write directly to
     * ans[originalIndex] — no map needed.
     * Time: O(N log N) Space: O(N)
     */

    class Pair {
        public int data;
        public int index;

        public Pair(int d, int i) {
            data = d;
            index = i;
        }

    }

    /**
     * Assigns rank to each element in the array using a min-heap.
     * Equal values receive the same rank; rank increments only on value change.
     *
     * @param nums input array of integers
     * @return array where each element is replaced by its rank
     */
    public int[] arrayRankTransform(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return new int[len];
        if (len == 1)
            return new int[] { 1 };

        // heap ordered by value; carries original index for direct placement
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> a.data - b.data);

        for (int i = 0; i < len; i++)
            minHeap.offer(new Pair(nums[i], i));

        int cnt = 1;
        int[] ans = new int[len];

        // seed with global minimum
        Pair min = minHeap.poll();
        ans[min.index] = cnt;
        int last = min.data;

        while (!minHeap.isEmpty()) {
            Pair mini = minHeap.poll();

            if (mini.data != last) // new distinct value → bump rank
                cnt++;

            ans[mini.index] = cnt;
            last = mini.data;
        }

        return ans;
    }
}

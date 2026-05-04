package heap.hard;

// Created at: 04-May-2026
// Last revised at: 04-May-2026
// Link: https://www.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1
// Time Complexity: O(n log n)
// Space Complexity: O(n)

import java.util.PriorityQueue;

/*
 * Problem Description:
 *   Given an array of rope lengths, connect all ropes into a single rope.
 *   The cost of connecting two ropes equals the sum of their lengths.
 *   Return the minimum total cost to connect all ropes.
 *
 *   Example:
 *     Input:  nums = [4, 3, 2, 6]
 *     Output: 29
 *     Explanation:
 *       Connect 2+3=5  → cost=5,  heap: [4, 5, 6]
 *       Connect 4+5=9  → cost=14, heap: [6, 9]
 *       Connect 6+9=15 → cost=29, heap: [15]
 *
 *   Constraints:
 *     1 <= nums.length <= 10^5
 *     1 <= nums[i]    <= 10^6
 *
 * Approaches:
 *
 *   Approach 1 — Brute Force (try every merge order):
 *     Idea: Recursively try every pair to merge and track minimum cost.
 *     Time: O(n!)  Space: O(n)  Drawbacks: Completely infeasible beyond ~10 elements.
 *
 *   Approach 2 — Greedy with Min-Heap ★:
 *     Key Insight: Each intermediate rope gets re-added to future merges,
 *     so merging the two shortest ropes at every step minimises the
 *     cumulative cost (Huffman-style argument). A min-heap gives us the
 *     two cheapest ropes in O(log n) per merge.
 *     Time: O(n log n)  Space: O(n)
 */

class GFG_MinCostToConnectRopes {

    /**
     * Returns the minimum cost to connect all ropes into one.
     *
     * @param nums array of individual rope lengths
     * @return minimum total connection cost
     */
    public int minCost(int[] nums) {
        int len = nums.length;

        if (len <= 1) return 0;
        if (len == 2) return nums[0] + nums[1];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int ropeLen : nums) minHeap.offer(ropeLen);

        int cost = 0;

        while (minHeap.size() > 1) {
            int first  = minHeap.poll();
            int second = minHeap.poll();

            int merged = first + second;  // cost of this merge
            cost += merged;

            minHeap.offer(merged);  // merged rope re-enters the pool
        }

        return cost;
    }
}
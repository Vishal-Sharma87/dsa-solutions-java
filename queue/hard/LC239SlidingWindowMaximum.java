package queue.hard;

import java.util.ArrayDeque;
import java.util.Deque;

// Created at: 19-April-2025
// Last revised at: 19-April-2025
// https://leetcode.com/problems/sliding-window-maximum/

/*
 * Problem Description:
 *
 * Given an integer array nums and a sliding window of size k moving from left
 * to right, return the maximum value in each window position.
 *
 * Example:
 *   Input : nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
 *   Output: [3, 3, 5, 5, 6, 7]
 *
 *   Window [1,3,-1]  -> max 3
 *   Window [3,-1,-3] -> max 3
 *   Window [-1,-3,5] -> max 5
 *   Window [-3,5,3]  -> max 5
 *   Window [5,3,6]   -> max 6
 *   Window [3,6,7]   -> max 7
 *
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   -10^4 <= nums[i] <= 10^4
 *   1 <= k <= nums.length
 */

/*
 * Approaches:
 *
 * 1. Brute Force
 *    Idea     : For each window of size k, scan all k elements and track the max.
 *    Time     : O(n * k)
 *    Space    : O(1) auxiliary
 *    Drawback : Re-scans overlap — the same element is compared in up to k windows.
 *
 * ★ 2. Monotonic Deque
 *    Idea      : Maintain a deque of indices in decreasing order of their values.
 *                The front always holds the index of the current window's maximum.
 *                Before adding a new element, pop from the back any index whose
 *                value is <= current (they can never be the max while current exists).
 *                Pop from the front if that index has slid out of the current window.
 *    Time      : O(n) — each index is pushed and popped at most once.
 *    Space     : O(k) — at most k indices live in the deque at a time.
 *    Key Insight: The deque is a "candidates" list. An element is a candidate only
 *                 if no larger element to its right exists within the same window.
 */

/*
 * Method to solve (Monotonic Deque):
 *
 * 1. Allocate result array of size (n - k + 1).
 * 2. Process the first window (i = 0 to k-1):
 *    a. Pop from the back while the back's value <= nums[i].
 *    b. Push i to the back.
 *    c. After the loop, front of deque = index of the max; record it in maxi[0].
 * 3. Slide the window (i = k to n-1):
 *    a. If front index == i - k, it's expired — pop from front.
 *    b. Pop from the back while the back's value <= nums[i].
 *    c. Push i to the back.
 *    d. Front of deque is current window max; record it in maxi[i - k + 1].
 * 4. Return maxi.
 */

public class LC239SlidingWindowMaximum {

    /**
     * Returns the maximum value in each sliding window of size k.
     *
     * @param nums the input array of integers
     * @param k    the window size
     * @return an array where each element is the max of a window, or null if input
     *         is invalid
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;

        if (len == 0 || k > len)
            return null; // defensive, not mandatory for LC

        int[] maxi = new int[len - k + 1];

        Deque<Integer> dq = new ArrayDeque<>(k); // stores indices, not values

        // --- first window ---
        for (int i = 0; i < k; i++) {
            // evict smaller elements from the back — they're useless
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i])
                dq.removeLast();

            dq.addLast(i);
        }
        maxi[0] = nums[dq.peekFirst()]; // front is always the window max

        // --- remaining windows ---
        for (int i = k; i < len; i++) {
            // front index no longer in window, evict it
            if (i - k == dq.peekFirst())
                dq.removeFirst();

            // evict smaller elements from the back
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i])
                dq.removeLast();

            dq.addLast(i);

            maxi[i - k + 1] = nums[dq.peekFirst()];
        }

        return maxi;
    }
}

// Time Complexity : O(n) — each index is enqueued and dequeued at most once
// Space Complexity : O(k) — deque holds at most k indices at any point

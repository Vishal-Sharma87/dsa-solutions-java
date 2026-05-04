package heap.hard;

// Created at: 04-May-2026
// Last revised at: 04-May-2026
// Link: https://leetcode.com/problems/find-k-closest-elements/
// Time Complexity: O(log n + k log k)
// Space Complexity: O(k)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Problem Description:
 *   Given a sorted integer array nums, two integers k and x, return the k
 *   closest integers to x in the array, sorted in ascending order.
 *   If two numbers are equally close, prefer the smaller one.
 *
 *   Example:
 *     Input:  nums = [1,2,3,4,5], k = 4, x = 3
 *     Output: [1,2,3,4]
 *
 *   Constraints:
 *     1 <= k <= nums.length <= 10^4
 *     nums is sorted ascending
 *     -10^4 <= nums[i], x <= 10^4
 *
 * Approaches:
 *
 *   Approach 1 — Max-Heap over candidate window ★ (implemented below):
 *     Key Insight: Binary search for the floor index of x (largest element <= x).
 *     The k closest elements must lie within [floorIndex-k, floorIndex+k] — a
 *     2k+1 window. Run a max-heap of size k over this window, ordered by diff
 *     descending (ties broken by value descending) to evict the worst candidate.
 *     Sort the result since heap order is not guaranteed.
 *     Time: O(log n + k log k)  Space: O(k)
 *
 *   Approach 2 — Binary Search on Left Boundary (Optimal):
 *     Key Insight: The answer is always a contiguous window of size k in a sorted
 *     array. Binary search for the optimal left boundary `l` of that window.
 *     At each mid, compare x - nums[mid] vs nums[mid+k] - x:
 *       - If x - nums[mid] > nums[mid+k] - x → left boundary is too far left → l = mid+1
 *       - Otherwise → r = mid
 *     Loop exits with l = optimal left boundary. Slice [l, l+k) directly.
 *     No heap, no sort — window is already sorted since input is sorted.
 *     Time: O(log(n-k) + k)  Space: O(1)
 */

class LC658FindKClosestElements {

    /**
     * Returns index of the largest element <= target (floor index).
     *
     * @param nums   sorted input array
     * @param target value to find the floor of
     * @return floor index of target in nums
     */
    private int findLowerBound(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        if (target >= nums[r])
            return r;
        if (target <= nums[l])
            return l;

        int lb = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] < target) {
                lb = mid;
                l = mid + 1;
            } else
                r = mid - 1;
        }
        return lb;
    }

    class Pair {
        int data, diff;

        Pair(int data, int diff) {
            this.data = data;
            this.diff = diff;
        }
    }

    /**
     * Returns the k closest elements to x from nums, sorted ascending.
     *
     * @param nums sorted integer array
     * @param k    number of closest elements to return
     * @param x    target value
     * @return k closest elements in ascending order
     */
    public List<Integer> findClosestElements(int[] nums, int k, int x) {
        int len = nums.length;
        List<Integer> ans = new ArrayList<>();
        if (len < k)
            return ans;

        int lbIndex = findLowerBound(nums, x);

        // candidate window — k closest can't be farther than k positions from floor
        int left = Math.max(0, lbIndex - k);
        int right = Math.min(len - 1, lbIndex + k);

        // max-heap: worst candidate (largest diff, largest value on tie) sits at root
        int size = k + 1;
        PriorityQueue<Pair> heap = new PriorityQueue<>(size, (a, b) -> {
            if (a.diff == b.diff)
                return b.data - a.data;
            return b.diff - a.diff;
        });

        for (int i = left; i <= right; i++) {
            heap.offer(new Pair(nums[i], Math.abs(x - nums[i])));
            if (heap.size() == size)
                heap.poll(); // evict worst
        }

        while (!heap.isEmpty())
            ans.add(heap.poll().data);

        Collections.sort(ans); // heap gives no order guarantee
        return ans;
    }
}

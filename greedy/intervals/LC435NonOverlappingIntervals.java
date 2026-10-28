package greedy.intervals;

// Created at: 28-October-2026
// Last revised at: 28-October-2026
// Link: https://leetcode.com/problems/non-overlapping-intervals/

/*
Problem Description:
--------------------
Statement:
Given an array of intervals, return the minimum number of intervals
that must be removed to make the remaining intervals non-overlapping.

Intervals sharing an endpoint are considered non-overlapping.

Example 1:
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: Remove [1,3] to make the remaining intervals non-overlapping.

Example 2:
Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: Remove two intervals to keep one.

Example 3:
Input: intervals = [[1,2],[2,3]]
Output: 0

Constraints:
- 1 <= intervals.length <= 10^5
- intervals[i].length == 2
- -5 * 10^4 <= starti < endi <= 5 * 10^4
*/

/*
Approach 1: Brute Force

Idea:
Consider possible subsets of intervals and find the largest subset
whose intervals do not overlap. The answer is the total number of
intervals minus the size of that subset.

Time Complexity:
O(2^n * n) or worse, depending on the implementation.

Space Complexity:
O(n) for tracking a candidate subset.

Drawbacks:
Enumerating subsets is computationally expensive and impractical
for the given constraints.
*/

/*
Approach 2: Greedy — Earliest Finishing Interval

Idea:
1. Sort intervals by their ending points in ascending order.
2. Select the first interval, which finishes earliest.
3. Traverse the remaining intervals:
   - If the current interval starts before the selected interval ends,
     remove the current interval.
   - Otherwise, keep it and update the selected ending point.
4. Return the removal count.

Why it works:
Choosing the interval that finishes earliest leaves as much room as
possible for subsequent intervals. When an overlap occurs, the current
interval cannot improve upon the previously selected interval's ending
time because the intervals are sorted by end time.

Time Complexity:
O(n log n), dominated by sorting.

Space Complexity:
O(n) auxiliary space for Java's object-array sorting implementation.

Key Insight:
Sort by ending time, not starting time. When two intervals overlap,
keeping the one that ends earlier maximizes the room available for
future intervals.
*/

import java.util.Arrays;

public class LC435NonOverlappingIntervals {

    /**
     * Finds the minimum number of intervals to remove so that the
     * remaining intervals do not overlap.
     *
     * @param intervals array of intervals represented by start and end
     * @return minimum number of intervals to remove
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int length = intervals.length;

        Arrays.sort(intervals, (row1, row2) -> {
            if (row1[1] == row2[1])
                return Integer.compare(row1[0], row2[0]);

            return Integer.compare(row1[1], row2[1]);
        });

        int end = intervals[0][1];
        int removeCnt = 0;

        for (int i = 1; i < length; i++) {
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            if (currStart < end) {
                removeCnt++;
            } else {
                end = currEnd;
            }
        }

        return removeCnt;
    }
}

// Time Complexity: O(n log n)
// Space Complexity: O(n) auxiliary space for sorting

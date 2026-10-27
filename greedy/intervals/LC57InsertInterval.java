package greedy.intervals;

// Created at: 27-October-2026
// Last revised at: 27-October-2026
// Link: https://leetcode.com/problems/insert-interval/

public class LC57InsertInterval {

    /*
     * Problem Description:
     * --------------------
     * Statement:
     * Given a sorted array of non-overlapping intervals and a new interval,
     * insert the new interval and merge any overlapping intervals.
     * 
     * The resulting intervals must remain sorted and non-overlapping.
     * 
     * Example:
     * Input:
     * intervals = [[1,3],[6,9]]
     * newInterval = [2,5]
     * 
     * Output:
     * [[1,5],[6,9]]
     * 
     * Constraints:
     * - 0 <= intervals.length <= 10^4
     * - intervals[i].length == 2
     * - 0 <= start <= end <= 10^5
     * - Existing intervals are sorted by start time.
     * - Existing intervals do not overlap.
     */

    /*
     * Approach 1: Brute Force
     * 
     * Idea:
     * Insert the new interval into the existing collection, sort all
     * intervals by their start values, and merge overlapping intervals.
     * 
     * Time Complexity:
     * O(n log n)
     * 
     * Space Complexity:
     * O(n)
     * 
     * Drawbacks:
     * Sorting is unnecessary because the existing intervals are already
     * sorted and non-overlapping.
     */

    /*
     * Approach 2: Greedy - Two Boundary Identification
     * 
     * Idea:
     * 1. Find the first interval whose end is greater than or equal to
     * the new interval's start.
     * 2. Find the last interval whose start is less than or equal to
     * the new interval's end.
     * 3. Expand the new interval to cover the identified overlapping range.
     * 4. Copy the untouched intervals before the overlapping range.
     * 5. Insert the merged interval.
     * 6. Copy the untouched intervals after the overlapping range.
     * 
     * Time Complexity:
     * O(n)
     * 
     * Space Complexity:
     * O(n), including the output array.
     * 
     * Key Insight:
     * Sorted, non-overlapping intervals allow the algorithm to identify
     * the affected range using two scans without sorting or merging
     * every interval individually.
     * 
     * Drawbacks:
     * The implementation relies on the input intervals already being
     * sorted and non-overlapping.
     */

    /*
     * Method to Solve:
     * ----------------
     * 1. Handle the empty input by returning the new interval directly.
     * 2. Initialize i at the beginning and j at the end of the array.
     * 3. Move i past intervals ending before the new interval starts.
     * 4. Move j backward past intervals starting after the new interval ends.
     * 5. Expand the new interval using the identified boundaries.
     * 6. Allocate the result array using the number of retained intervals.
     * 7. Copy the intervals before i.
     * 8. Insert the merged interval.
     * 9. Copy the intervals after j.
     * 10. Return the resulting array.
     */

    /**
     * Inserts a new interval and merges overlapping intervals.
     *
     * @param intervals   sorted, non-overlapping intervals
     * @param newInterval interval to insert
     * @return sorted intervals after insertion and merging
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {

        int length = intervals.length;

        if (length == 0)
            return new int[][] { newInterval };

        int i = 0;
        int j = length - 1;

        int start = newInterval[0];
        int end = newInterval[1];

        // Find the first interval that may overlap
        while (i < length && intervals[i][1] < start)
            i++;

        if (i < length)
            start = Math.min(intervals[i][0], start);

        // Find the last interval that may overlap
        while (j >= 0 && intervals[j][0] > end)
            j--;

        if (j >= 0)
            end = Math.max(intervals[j][1], end);

        int[][] inserted = new int[i + length - j][2];

        int it = 0;

        // Copy the smaller portion
        while (it < i) {
            int s = intervals[it][0];
            int e = intervals[it][1];

            inserted[it++] = new int[] { s, e };
        }

        // Insert the merged interval
        inserted[it++] = new int[] { start, end };

        // Copy the larger portion
        j++;

        while (j < length) {
            int s = intervals[j][0];
            int e = intervals[j][1];

            inserted[it++] = new int[] { s, e };
            j++;
        }

        return inserted;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n), including the output array.
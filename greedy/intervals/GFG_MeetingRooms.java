package greedy.intervals;

// Created at: 28-October-2026
// Last revised at: 28-October-2026
// Link: https://www.geeksforgeeks.org/problems/attend-all-meetings/1

import java.util.Arrays;

public class GFG_MeetingRooms {

    /*
     * Problem Description:
     * --------------------
     * Statement:
     * Given an array of intervals representing meeting start and end times,
     * determine whether a person can attend all meetings without any overlap.
     * 
     * If any two meetings overlap, return false. Otherwise, return true.
     * 
     * Example 1:
     * Input:
     * intervals = [[1, 3], [4, 6], [7, 9]]
     * 
     * Output:
     * true
     * 
     * Example 2:
     * Input:
     * intervals = [[1, 5], [2, 6]]
     * 
     * Output:
     * false
     * 
     * Example 3:
     * Input:
     * intervals = [[1, 3], [3, 5]]
     * 
     * Output:
     * true
     * 
     * Constraints:
     * - 1 <= intervals.length <= 10^5
     * - intervals[i].length == 2
     * - start time < end time
     */

    /*
     * Approach 1: Brute Force
     * 
     * Idea:
     * Compare every pair of meetings to check whether their time intervals
     * overlap. Return false as soon as an overlapping pair is found.
     * 
     * Time Complexity:
     * O(n^2)
     * 
     * Space Complexity:
     * O(1), excluding the input.
     * 
     * Drawbacks:
     * Checking every pair is inefficient for large inputs.
     */

    /*
     * Approach 2: Greedy - Sorting and Overlap Detection
     * 
     * Idea:
     * 1. Sort meetings by their starting times.
     * 2. Initialize previousEndsAt with the first meeting's end time.
     * 3. Traverse the remaining meetings.
     * 4. If the current meeting starts before previousEndsAt, return false.
     * 5. Otherwise, update previousEndsAt to the current meeting's end.
     * 6. Return true if every meeting can be attended.
     * 
     * Time Complexity:
     * O(n log n)
     * 
     * Space Complexity:
     * O(1) auxiliary space for the scan, excluding sorting overhead.
     * 
     * Key Insight:
     * Sorting by start time makes it sufficient to compare each meeting
     * with the immediately preceding meeting.
     * 
     * Since the algorithm returns immediately on detecting an overlap,
     * every meeting processed after the first starts at or after the
     * previous meeting's end.
     * 
     * Drawbacks:
     * Sorting modifies the original array.
     */

    /*
     * Method to Solve:
     * ----------------
     * 1. Sort intervals by their starting times.
     * 2. Store the first meeting's end time in previousEndsAt.
     * 3. Traverse the remaining intervals.
     * 4. If currStart < previousEndsAt, an overlap exists.
     * 5. Otherwise, update previousEndsAt to currEnd.
     * 6. Return true if no overlap is detected.
     */

    /**
     * Checks whether all meetings can be attended without overlap.
     *
     * @param intervals array containing meeting start and end times
     * @return true if no meetings overlap, otherwise false
     */
    public boolean canAttend(int[][] intervals) {

        int length = intervals.length;

        Arrays.sort(intervals,
                (a, b) -> Integer.compare(a[0], b[0]));

        int previousEndsAt = intervals[0][1];

        for (int i = 1; i < length; i++) {

            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            if (currStart < previousEndsAt)
                return false;

            previousEndsAt = currEnd;
        }

        return true;
    }
}

// Time Complexity: O(n log n)
// Auxiliary Space Complexity: O(1) for the scan, excluding sorting overhead.
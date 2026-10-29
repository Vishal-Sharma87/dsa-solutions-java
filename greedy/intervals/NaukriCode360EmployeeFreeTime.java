package greedy.intervals;

// Created at: 29-October-2026
// Last revised at: 29-October-2026
// Link: https://www.naukri.com/code360/problems/employee-free-time_1171181?leftPanelTabValue=PROBLEM

import java.util.ArrayList;
import java.util.List;

/*
Problem Description:
--------------------
There are N problem setters, each working during multiple
non-overlapping time intervals.

Given N sorted schedules, find the sorted list of intervals
during which all problem setters are free.

Each interval [start, end] includes both endpoints.
The day begins at time 0 and ends at time 10^8, inclusive.

The output is represented as a flat list where every two
consecutive values denote a free interval.

Example:
--------
Input:
[[1, 2, 5, 6], [1, 2], [5, 10]]

Output:
[0, 0, 3, 4, 11, 100000000]

Constraints:
-----------
1 <= T <= 10
1 <= N <= 1000
1 <= K <= 1000

Each schedule contains sorted, non-overlapping intervals.
At least one interval exists during which all setters are free.
*/

/*
Approach 1: Brute Force

Idea:
-----
Check each time unit from 0 to 10^8 and determine whether
any problem setter is working at that time.

Group consecutive free time units into intervals.

Time Complexity:
O(D * M), where D = 10^8 + 1 and M is the total
number of busy intervals, using a straightforward scan.

Space Complexity:
O(F), where F is the number of output intervals.

Drawbacks:
Checking every time unit is expensive and unnecessary.
The input already provides intervals, allowing us to process
only their boundaries.
*/

/*
Approach 2: Sort and Find Gaps

Idea:
-----
1. Flatten all schedules into a single list of intervals.
2. Sort the intervals by their starting times.
3. Initialize start = 0 to track the earliest time not yet
   covered by the processed busy intervals.
4. For each sorted interval:
   - If its start is greater than start, record the free
     interval [start, interval.start - 1].
   - Advance start beyond the interval's end.
   - For overlapping intervals, retain the furthest covered
     boundary by taking the maximum of the current start
     and interval.end + 1.
5. After processing all intervals, record the remaining
   free interval if start <= 10^8.

Time Complexity:
O(M log M), where M is the total number of busy intervals.

Space Complexity:
O(M), including the flattened interval list and output.

Key Insight:
Sorting allows all busy intervals to be processed from
left to right. Maintaining the earliest uncovered time
makes it possible to identify free gaps without checking
every individual time unit.

The +1 and -1 adjustments are necessary because interval
endpoints are inclusive.
*/

/*
Method to Solve:
----------------
1. Convert every pair of schedule values into an Interval.
2. Sort all intervals by their starting times.
3. Traverse the sorted intervals while maintaining start,
   the earliest time not yet covered by processed intervals.
4. Whenever an interval starts after start, add the free
   interval [start, interval.start - 1].
5. Advance start beyond the interval's end, accounting
   for overlaps using Math.max().
6. Add the remaining free interval through 10^8, if any.
*/

public class NaukriCode360EmployeeFreeTime {

    private static final int MAX_TIME = 100000000;

    private record Interval(int start, int end) {
    }

    /**
     * Finds all intervals during which every problem setter
     * is free.
     *
     * @param schedules sorted schedules of all problem setters;
     *                  every pair represents a busy interval
     * @return sorted free intervals in flat start-end format
     */
    public ArrayList<Integer> findFreeIntervals(
            ArrayList<ArrayList<Integer>> schedules) {

        List<Interval> intervals = new ArrayList<>();

        for (ArrayList<Integer> schedule : schedules) {
            for (int i = 0; i < schedule.size() - 1; i += 2) {
                intervals.add(
                        new Interval(schedule.get(i), schedule.get(i + 1)));
            }
        }

        intervals.sort((a, b) -> Integer.compare(a.start(), b.start()));

        ArrayList<Integer> freeTime = new ArrayList<>();
        int start = 0;

        for (Interval interval : intervals) {

            if (interval.start() > start) {
                freeTime.addAll(
                        List.of(start, interval.start() - 1));
                start = interval.end() + 1;

            } else if (interval.start() < start) {
                start = Math.max(start, interval.end() + 1);

            } else {
                start = interval.end() + 1;
            }
        }

        if (start <= MAX_TIME) {
            freeTime.addAll(List.of(start, MAX_TIME));
        }

        return freeTime;
    }
}

// Time Complexity: O(M log M)
// Space Complexity: O(M)
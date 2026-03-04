package binarysearch.onAnswers;

import java.util.Arrays;

public class GFG_AggressiveCows {

    // Created at: 5 march 2026
    // Last revised at: 5 march 2026

    // link : https://www.geeksforgeeks.org/problems/aggressive-cows/1

    /*
     * Problem Description -> statement, example, constraints
     * Aggressive Cows
     * Difficulty: MediumAccuracy: 59.57%Submissions: 201K+Points: 4Average Time:
     * 30m
     * You are given an array with unique elements of stalls[], which denote the
     * positions of stalls. You are also given an integer k which denotes the number
     * of aggressive cows. The task is to assign stalls to k cows such that the
     * minimum distance between any two of them is the maximum possible.
     * 
     * Examples:
     * 
     * Input: stalls[] = [1, 2, 4, 8, 9], k = 3
     * Output: 3
     * Explanation: The first cow can be placed at stalls[0],
     * the second cow can be placed at stalls[2] and
     * the third cow can be placed at stalls[3].
     * The minimum distance between cows in this case is 3, which is the largest
     * among all possible ways.
     * Input: stalls[] = [10, 1, 2, 7, 5], k = 3
     * Output: 4
     * Explanation: The first cow can be placed at stalls[0],
     * the second cow can be placed at stalls[1] and
     * the third cow can be placed at stalls[4].
     * The minimum distance between cows in this case is 4, which is the largest
     * among all possible ways.
     * Input: stalls[] = [2, 12, 11, 3, 26, 7], k = 5
     * Output: 1
     * Explanation: There are 6 stalls and only 5 cows, we try to place the cows
     * such that the minimum distance between any two cows is as large as possible.
     * The minimum distance between cows in this case is 1, which is the largest
     * among all possible ways.
     * Constraints:
     * 2 ≤ stalls.size() ≤ 106
     * 0 ≤ stalls[i] ≤ 108
     * 2 ≤ k ≤ stalls.size()
     */

    // Method to solve the Problem
    public boolean canAssign(int distance, int[] stalls, int k) {

        // empty arrays or more cows to assign than available stall position
        if (stalls.length == 0 || k > stalls.length)
            return false;

        // since the array is sorted we can assign the first cow at satlls[0] as it will
        // be smallest
        int cowAssigned = 1;

        int lastCowPos = stalls[0];

        for (int val : stalls) {
            if (val - lastCowPos >= distance) {
                // the distance between current stall and last assigned stall is equal or
                // greater to the required so asiigned the cow at position 'val'
                cowAssigned++;

                // we have assigned K cows return no need to assign more
                if (cowAssigned == k)
                    return true;

                lastCowPos = val;
            }
        }

        // If we are reaching here that means K cows cannot be assigned with given
        // distance
        return false;
    }

    public int aggressiveCows(int[] stalls, int k) {
        int len = stalls.length;
        int minDist = -1;
        if (k > len)
            return minDist;

        // sort the stalls arrays as they are independent and sorting them will enable
        // monotonic behaviour in elements
        Arrays.sort(stalls);

        // minimum distance can be 1 as all values are distinct
        int low = 1;
        int high = stalls[len - 1] - stalls[0];

        int mid;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if (canAssign(mid, stalls, k)) {
                // k cows can be assigned if the minimum distance among them is 'mid', search
                // for larger distnace
                low = mid + 1;

                // store the minDistance
                minDist = mid;
            } else {
                // k cows can't be assigned if the minimum distance among them is 'mid', search
                // for samller distnace
                high = mid - 1;
            }
        }
        return minDist;
    }

    // Time Complexity: O(n * log(max(stalls) - min(stalls)))
    // Space Complexity: O(n) for sorting
}

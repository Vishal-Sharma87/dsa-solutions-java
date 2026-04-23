package slidingwindow.medium;

// Created at: 24-April-2026
// Last revised at: 24-April-2026
// Link: https://leetcode.com/problems/fruit-into-baskets/
// Time Complexity: O(N)
// Space Complexity: O(1)

/*
 * Problem Description:
 * You have two baskets, each capable of holding only one type of fruit.
 * Given an integer array fruits[] where fruits[i] is the type of fruit at tree i,
 * return the maximum number of fruits you can pick from a contiguous subarray
 * using at most 2 distinct fruit types.
 *
 * Example:
 *   Input:  fruits = [1, 2, 1, 2, 3]
 *   Output: 4   // subarray [1, 2, 1, 2] — two types only
 *
 *   Input:  fruits = [0, 1, 2, 2]
 *   Output: 3   // subarray [1, 2, 2]
 *
 * Constraints:
 *   - 1 <= fruits.length <= 10^5
 *   - 0 <= fruits[i] < fruits.length
 */

/*
 * Approaches:
 *
 * 1. Brute Force
 *    Idea    : Check every subarray, count distinct types; track max length where distinct <= 2
 *    Time    : O(N^2)
 *    Space   : O(1)
 *    Drawback: Too slow for N = 10^5
 *
 * 2. Sliding Window + HashMap
 *    Idea    : Expand r; maintain a map of {fruitType → count} in the window.
 *              When map.size() > 2, shrink from left until one type is fully evicted.
 *    Time    : O(N)
 *    Space   : O(1) — map holds at most 3 entries at any point
 *    Drawback: HashMap overhead (boxing, hashing) for a problem that only ever needs 2 slots
 *
 * 3. ★ Sliding Window + two explicit variables
 *    Idea    : Track the two active fruit types (t1, t2) and their counts (b1, b2) directly.
 *              On a third type, shrink from left until one basket empties, then reassign.
 *              Requires a two-phase init to populate t2 before the main loop.
 *    Time    : O(N) — l only moves forward; inner shrink loop is amortized O(N) total
 *    Space   : O(1) — four scalar variables, no auxiliary structure
 *    Key Insight: Replacing HashMap with two int variables eliminates hashing entirely,
 *                 at the cost of slightly more verbose initialization logic.
 */

public class LC904FruitIntoBaskets {

    /*
     * Method to solve:
     * Phase 1 — scan from index 1 until a second fruit type appears, populate t2/b2
     * Phase 2 — main sliding window:
     * - If fruits[r] matches t1 or t2, increment the matching count
     * - If it's a third type: update maxi, shrink from left until one basket hits
     * 0,
     * reassign that slot to fruits[r] with count 1
     * Return max of running maxi and final b1 + b2
     */

    /**
     * Returns the maximum number of fruits pickable using at most two basket types.
     *
     * @param fruits array of fruit types at each tree position
     * @return length of the longest subarray with at most 2 distinct values
     */
    public int totalFruit(int[] fruits) {

        int len = fruits.length;

        if (len <= 0)
            return len;

        int t1 = fruits[0], t2 = -1;
        int b1 = 1, b2 = 0;

        int l = 0;
        int r = 1;

        // phase 1 — advance r until we find a second distinct type
        while (r < len) {
            if (fruits[r] == t1) {
                b1++;
                r++;
            } else {
                t2 = fruits[r];
                b2 = 1;
                r++;
                break;
            }
        }

        int maxi = b1 + b2;
        if (r == len)
            return maxi; // edge case: only one distinct type in entire array

        // phase 2 — main window; t1 and t2 are both initialized here
        while (r < len) {

            if (fruits[r] == t1)
                b1++;
            else if (fruits[r] == t2)
                b2++;
            else {
                // third type encountered — snapshot current window before evicting
                maxi = Math.max(maxi, b1 + b2);

                // shrink left until one basket is empty
                while (l <= r - 1 && b1 > 0 && b2 > 0) {
                    if (fruits[l] == t1)
                        b1--;
                    else
                        b2--;
                    l++;
                }

                // reassign the emptied slot to the new fruit type
                if (b1 == 0) {
                    t1 = fruits[r];
                    b1 = 1;
                } else {
                    t2 = fruits[r];
                    b2 = 1;
                }
            }

            r++;
        }

        return Math.max(maxi, b1 + b2); // last window never hits the else branch
    }
}
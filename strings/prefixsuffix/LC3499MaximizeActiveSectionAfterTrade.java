package strings.prefixsuffix;

// Created at: 22-July-2026
// Last revised at: 22-July-2026
// Link: https://leetcode.com/problems/maximize-active-section-after-trade/

/*
Problem Description:
--------------------
Statement:
You are given a binary string where:
- '1' represents an active section.
- '0' represents an inactive section.

You may perform at most one trade that merges two inactive blocks separated by
exactly one active block. Determine the maximum number of active sections
possible after the trade.

Example:
Input:
s = "00100100"

Output:
6

Constraints:
- 1 <= s.length() <= 10^5
- s consists only of '0' and '1'.
*/

/*
Approach 1: Brute Force

Idea:
Try every possible active block as the traded section and count the resulting
active sections after rebuilding the string.

Time Complexity:
O(n²)

Space Complexity:
O(1)

Drawbacks:
Repeatedly scanning the string makes it too slow for large inputs.
*/

/*
Approach 2: Prefix & Suffix Consecutive Zero Counting (Optimized)

Idea:
1. Count the initial number of active sections.
2. Traverse from right to left and store the consecutive zeros immediately
   after every active section.
3. Traverse from left to right while maintaining the consecutive zeros
   immediately before every active section.
4. Whenever an active section has zero blocks on both sides, merge both blocks
   through the trade and update the answer.

Time Complexity:
O(n)

Space Complexity:
O(n)

Key Insight:
Only the zero blocks directly adjacent to an active section contribute to a
valid trade, so precomputing the right block and maintaining the left block
during traversal is sufficient.
*/

/*
Method to Solve:
----------------
1. Count the initially active sections.
2. Precompute consecutive right-side zero counts.
3. Maintain consecutive left-side zero counts.
4. Evaluate every eligible active section.
5. Return the maximum active sections obtained.
*/

class LC3499MaximizeActiveSectionAfterTrade {

    /**
     * Returns the maximum active sections obtainable after one trade.
     *
     * @param s binary string representing active and inactive sections
     * @return maximum active sections after the trade
     */
    public int maxActiveSectionsAfterTrade(String s) {

        int len = s.length();

        int[] rightZeros = new int[len];

        int consecutiveZeros = 0;
        int lastZeroBlock = 0;
        int initialActive = 0;

        // store consecutive zeros on the right of every active section
        for (int i = len - 1; i >= 0; i--) {

            if (s.charAt(i) == '0') {
                consecutiveZeros++;
                lastZeroBlock = consecutiveZeros;
            } else {
                initialActive++;
                rightZeros[i] = lastZeroBlock;
                consecutiveZeros = 0;
            }
        }

        consecutiveZeros = 0;
        lastZeroBlock = 0;

        int answer = initialActive;

        // evaluate every active section
        for (int i = 0; i < len; i++) {

            if (s.charAt(i) == '0') {
                consecutiveZeros++;
                lastZeroBlock = consecutiveZeros;
            } else if (rightZeros[i] != 0 && lastZeroBlock != 0) {

                int current = initialActive + lastZeroBlock + rightZeros[i];
                answer = Math.max(answer, current);

                consecutiveZeros = 0;
            }
        }

        return answer;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n)
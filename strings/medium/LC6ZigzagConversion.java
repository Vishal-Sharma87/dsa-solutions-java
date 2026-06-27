package strings.medium;

// Created at: 28-June-2026
// Last revised at: 28-June-2026
// Link: https://leetcode.com/problems/zigzag-conversion/

/*
Problem Description:
--------------------
Statement:
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"

Given a string s and number of rows numRows, convert the string in zigzag order and return it read line by line.

Example:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"

Constraints:
- 1 <= s.length <= 1000
- s consists of English letters (lower-case and upper-case), ',' and '.'
- 1 <= numRows <= 1000
*/

/*
Approach 1: Row Simulation ★
Idea:
Simulate the zigzag movement using a direction flag (top-to-bottom / bottom-to-top).
Maintain a StringBuilder per row. Traverse the string, append each character to the
current row's builder, then move the row pointer up or down. At boundaries, flip direction.
Finally, concatenate all rows.

Key insight: When moving top-to-bottom, hitting row (numRows) means next valid index is
(numRows - 2) — we've already appended to the last row, so bounce back one step.
Similarly, hitting row -1 means next valid index is 1.

Time Complexity: O(n) — single pass over the string
Space Complexity: O(n) — StringBuilder per row, total characters = n

Drawbacks:
None for this problem size. Math-based approaches exist but are harder to derive under pressure.
*/

/*
Method to Solve:
----------------
1. Handle edge case: if numRows == 1, return s as-is (no zigzag possible).
2. Create a StringBuilder array of size numRows — one per row.
   NOTE: Use a loop to initialize, NOT Arrays.fill() — fill puts the same reference
   in every slot, causing all rows to share one StringBuilder.
3. Traverse the string with a row counter (cnt) and direction flag (topToBottom).
4. Append current character to parts[cnt].
5. Move cnt in the current direction. On boundary hit, flip direction and adjust cnt.
6. After traversal, concatenate all row builders and return.
*/

// Time Complexity: O(n)
// Space Complexity: O(n)

public class LC6ZigzagConversion {

    /**
     * Converts a string written in zigzag pattern across numRows rows,
     * then reads and returns it line by line.
     *
     * @param s       input string to zigzag
     * @param numRows number of rows in the zigzag pattern
     * @return the string read line by line after zigzag arrangement
     */
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        int len = s.length();
        StringBuilder[] parts = new StringBuilder[numRows];

        // separate object per row — Arrays.fill() would share one reference across all
        // slots
        for (int i = 0; i < numRows; i++) {
            parts[i] = new StringBuilder();
        }

        int cnt = 0;
        boolean topToBottom = true;

        for (int i = 0; i < len; i++) {
            parts[cnt].append(s.charAt(i));

            if (topToBottom) {
                cnt++;
                if (cnt == numRows) {
                    // hit bottom boundary — bounce back, skip the last row (already appended)
                    topToBottom = false;
                    cnt = numRows - 2;
                }
            } else {
                cnt--;
                if (cnt < 0) {
                    // hit top boundary — bounce forward, skip row 0 (already appended)
                    topToBottom = true;
                    cnt = 1;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            result.append(parts[i]);
        }

        return result.toString();
    }
}
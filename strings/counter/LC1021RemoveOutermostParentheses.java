package strings.counter;

// Created at: 08-October-2026
// Last revised at: 08-October-2026
// Link: https://leetcode.com/problems/remove-outermost-parentheses/

/*
Problem Description:
--------------------
Statement:
Given a valid parentheses string, decompose it into primitive valid
parentheses strings and remove the outermost pair of parentheses from
every primitive string.

Example:
Input:  "(()())(())"
Output: "()()()"

Constraints:
1 <= s.length <= 10^5
s contains only '(' and ')'.
s is a valid parentheses string.
*/

/*
Approach 1: String Counter

Idea:
Track the current nesting depth while scanning the string.

For '(':
- Increase the depth first.
- Append it only if it is not the outermost '('.

For ')':
- Decrease the depth first.
- Append it only if it does not close the outermost ')'.

The depth therefore tells us whether the current parenthesis belongs
to the outer layer or the inner content.

Time Complexity:
O(n)

Space Complexity:
O(n) for the output StringBuilder.
The enhanced for-loop over s.toCharArray() also creates a temporary
character array.

Drawbacks:
The algorithm is optimal in time, but toCharArray() creates an
additional O(n) temporary allocation.

Key Insight:
A parenthesis is outermost exactly when the nesting depth is
transitioning between 0 and 1.
*/

/*
Approach 2: Primitive Boundary Scan

Idea:
Scan one primitive at a time.

Start with an opening parenthesis and initialize its balance to 1.
Continue scanning until the balance becomes 0. That identifies the
complete primitive.

Instead of storing the outermost parentheses, append only the
characters encountered before the primitive closes.

Time Complexity:
O(n)

Space Complexity:
O(n) for the output StringBuilder.

Drawbacks:
The code is slightly more involved because it explicitly tracks
primitive boundaries.

Key Insight:
A primitive ends exactly when its balance returns to zero.
Using charAt() avoids the temporary char[] created by toCharArray().
*/

/*
Approach 3: Primitive Boundary + Substring Extraction

Idea:
First locate the boundaries of each primitive.

For every primitive:
- Start at i.
- Scan until the balance becomes zero.
- The primitive occupies [i, tail).
- Its outermost parentheses are at i and tail - 1.
- Append only the inner portion using substring(i + 1, tail - 1).

Time Complexity:
O(n)

Space Complexity:
O(n) for the output plus temporary substring allocations.

Drawbacks:
substring() creates additional String objects in modern Java,
so although the asymptotic complexity remains O(n), it can create
more temporary allocations than Approach 2.

Key Insight:
Once the primitive boundaries are known, removing its outermost
parentheses becomes a simple range extraction.
*/

/*
Method to Solve:
----------------
1. Track the nesting depth or balance of parentheses.
2. A primitive ends when the balance returns to zero.
3. Ignore the first and last parenthesis of every primitive.
4. Append the remaining inner content to the result.
*/

public class LC1021RemoveOutermostParentheses {

    /**
     * Removes outermost parentheses using a direct nesting-depth counter.
     *
     * @param s valid parentheses string
     * @return string with the outermost parentheses removed from each primitive
     */
    public String removeOuterParenthesesUsingDepthCounter(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;

        for (char curr : s.toCharArray()) {
            if (curr == '(') {
                depth++;

                // Keep only non-outermost opening parentheses.
                if (depth > 1) {
                    result.append(curr);
                }
            } else {
                depth--;

                // Keep only non-outermost closing parentheses.
                if (depth > 0) {
                    result.append(curr);
                }
            }
        }

        return result.toString();
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)

    /**
     * Removes outermost parentheses by scanning one primitive at a time.
     *
     * @param s valid parentheses string
     * @return string with the outermost parentheses removed from each primitive
     */
    public String removeOuterParenthesesUsingPrimitiveBoundary(String s) {
        int length = s.length();
        StringBuilder result = new StringBuilder();

        int i = 0;

        while (i < length) {
            int depth = 1;
            int tail = i + 1;

            // Find the end of the current primitive.
            while (tail < length && depth > 0) {
                depth += s.charAt(tail) == '(' ? 1 : -1;
                tail++;
            }

            // Append everything except the outer pair.
            for (int j = i + 1; j < tail - 1; j++) {
                result.append(s.charAt(j));
            }

            i = tail;
        }

        return result.toString();
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)

    /**
     * Removes outermost parentheses by locating primitive boundaries
     * and extracting their inner portions with substring().
     *
     * @param s valid parentheses string
     * @return string with the outermost parentheses removed from each primitive
     */
    public String removeOuterParenthesesUsingSubstring(String s) {
        int length = s.length();
        StringBuilder result = new StringBuilder();

        int i = 0;

        while (i < length) {
            int depth = 1;
            int tail = i + 1;

            // Find the end of the current primitive.
            while (tail < length && depth > 0) {
                depth += s.charAt(tail) == '(' ? 1 : -1;
                tail++;
            }

            // Skip the outermost pair.
            result.append(s.substring(i + 1, tail - 1));

            i = tail;
        }

        return result.toString();
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
}

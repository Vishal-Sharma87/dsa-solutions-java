package recursion.subsequencepatterns;

import java.util.ArrayList;
import java.util.List;

public class Code360_GenerateBinaryStringWithoutConsecutive1 {
    // Created at: 29-March-2025
    // Last revised at: 29-March-2025
    // Link:
    // https://www.geeksforgeeks.org/generate-binary-strings-without-consecutive-1s/
    // Time Complexity: O(F(N+2) * N) — F(N+2) strings, each of length N
    // Space Complexity: O(N) — recursion depth

    /*
     * Problem Description:
     * Given a positive integer N, generate all binary strings of length N
     * that do not contain two consecutive 1s.
     *
     * Example:
     * Input: N = 3
     * Output: ["000", "001", "010", "100", "101"]
     *
     * Constraints:
     * 1 <= N <= 20
     */

    /*
     * Approach: Recursion / Backtracking
     *
     * Idea:
     * Build the string character by character from index 1 to N.
     * At each position, '0' is always a valid choice.
     * '1' is only valid if the previous character was not '1'.
     * Track this with a boolean flag (prevIsOne).
     *
     * Time: O(F(N+2) * N) — the count of valid strings follows Fibonacci,
     * and each string takes O(N) to build
     * Space: O(N) — max recursion depth is N
     *
     * Key Insight:
     * No visited array or set needed — every root-to-leaf path in the
     * recursion tree produces a unique string by construction.
     */

    /**
     * Recursively builds all binary strings of the given length
     * without consecutive 1s.
     *
     * @param ans   list collecting valid strings
     * @param len   target length of each binary string
     * @param i     current position being filled (1-indexed)
     * @param curr  string built so far
     * @param taken true if the previous character placed was '1'
     */
    private void generate(List<String> ans, int len, int i, String curr, boolean taken) {

        if (i > len) {
            ans.add(curr);
            return;
        }

        if (!taken) {
            generate(ans, len, i + 1, curr + "0", false);
            generate(ans, len, i + 1, curr + "1", true); // '1' only when prev was '0'
        } else {
            generate(ans, len, i + 1, curr + "0", false); // prev was '1', force '0'
        }
    }

    /**
     * Returns all binary strings of length N with no two consecutive 1s.
     *
     * @param N length of binary strings to generate
     * @return list of valid binary strings, or empty list if N <= 0
     */
    public List<String> generateString(int N) {
        if (N <= 0)
            return new ArrayList<>();

        List<String> strs = new ArrayList<>();
        generate(strs, N, 1, "", false);
        return strs;
    }
}

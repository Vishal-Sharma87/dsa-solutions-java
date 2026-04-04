package recursion.hard;

import java.util.ArrayList;
import java.util.List;

public class LC131PalindromePartiotioning {
    // Created at: 05-May-2025
    // Last revised at: 05-May-2025
    // https://leetcode.com/problems/palindrome-partitioning/

    /*
     * Problem Description:
     * Given a string s, partition it such that every substring in the partition
     * is a palindrome. Return all possible palindrome partitioning.
     *
     * Example:
     * Input: s = "aab"
     * Output: [["a","a","b"],["aa","b"]]
     *
     * Constraints:
     * 1 <= s.length() <= 16
     * s contains only lowercase English letters
     */

    /*
     * Approaches:
     *
     * Approach 1: Backtracking with substring slicing
     * Idea: At each step, try every prefix of the remaining string. If the
     * prefix is a palindrome, take it, recurse on the suffix, then backtrack.
     * Time: O(n · 2^n) — 2^n subsets, palindrome check up to O(n) each
     * Space: O(n) recursion depth + O(n) for partitions list at each frame
     * Key Insight: Cutting only on palindromic prefixes naturally prunes the
     * search tree — no invalid partitions are ever explored fully.
     */

    /*
     * Method to solve (Approach 1):
     * 1. Call getPartitions(s, empty list, ans).
     * 2. Base case: s is empty → all characters are consumed, snapshot partitions.
     * 3. For i in [0, s.length()):
     * a. Take prefix s[0..i].
     * b. If it's a palindrome: add to partitions, recurse on s[i+1..], remove last.
     * 4. isPalin uses two-pointer O(n) check.
     */

    // Time Complexity: O(n · 2^n)
    // Space Complexity: O(n)
    /**
     * Checks if the given string is a palindrome using two pointers.
     *
     * @param s input string
     * @return true if s reads the same forwards and backwards
     */
    private boolean isPalin(String s) {
        if (s.length() <= 1)
            return true;

        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    /**
     * Backtracking helper — tries every palindromic prefix cut at each step.
     *
     * @param s          remaining string to partition
     * @param partitions current list of chosen palindromic substrings
     * @param ans        accumulator for complete valid partitions
     */
    private void getPartitions(String s, List<String> partitions, List<List<String>> ans) {
        if (s.length() == 0) {
            ans.add(new ArrayList<>(partitions));
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            String part = s.substring(0, i + 1);
            if (isPalin(part)) {
                partitions.add(part);
                getPartitions(s.substring(i + 1), partitions, ans);
                partitions.remove(partitions.size() - 1); // backtrack
            }
        }
    }

    /**
     * Returns all ways to partition s so that every part is a palindrome.
     *
     * @param s the input string to partition
     * @return list of all valid palindrome partitions
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s.length() != 0) {
            getPartitions(s, new ArrayList<>(), ans);
        }
        return ans;
    }
}

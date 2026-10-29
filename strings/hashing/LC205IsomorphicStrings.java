package strings.hashing;

// Created at: 29-October-2026
// Last revised at: 29-October-2026
// Link: https://leetcode.com/problems/isomorphic-strings/

import java.util.Arrays;

/*
Problem Description:
--------------------
Given two strings s and t, determine whether they are isomorphic.

Two strings are isomorphic if each character in s can be replaced by
a character in t while preserving the order of characters.

Every occurrence of a character must map to the same character.
Different characters must map to different characters.

Example 1:
----------
s = "egg", t = "add"
Output: true

Example 2:
----------
s = "foo", t = "bar"
Output: false

Example 3:
----------
s = "paper", t = "title"
Output: true

Constraints:
------------
1 <= s.length <= 5 * 10^4
t.length == s.length
s and t consist of valid ASCII characters.
*/

/*
Approach 1: Brute Force

Idea:
-----
Compare the characters at corresponding positions and verify that
their equality relationships match.

For every pair of positions i and j, check whether:

s.charAt(i) == s.charAt(j)

is equivalent to:

t.charAt(i) == t.charAt(j)

If any pair violates this condition, the strings are not isomorphic.

Time Complexity:
O(n^2)

Space Complexity:
O(1)

Drawbacks:
Comparing every pair of positions is unnecessary when mappings can
be tracked directly.
*/

/*
Approach 2: Bidirectional Character Mapping

Idea:
-----
Maintain two arrays to track mappings in both directions.

1. sMap[c] stores the character in t mapped to character c in s.
2. tMap[c] stores the character in s mapped to character c in t.
3. Initialize both arrays with -1 to represent unmapped characters.
4. Process corresponding characters from both strings.
5. If both characters are unmapped, establish the mapping.
6. Otherwise, verify that both existing mappings remain consistent.
7. Return false if either mapping conflicts.

Time Complexity:
O(n)

Space Complexity:
O(1) for two fixed-size arrays.

Key Insight:
Bidirectional mapping enforces both requirements of isomorphism:
consistent mappings and one-to-one correspondence.
*/

/*
Method to Solve:
----------------
1. Initialize two character-mapping arrays with -1.
2. Traverse both strings simultaneously.
3. If both characters are unmapped, record their mappings.
4. If either existing mapping conflicts, return false.
5. Return true if every position satisfies both mapping rules.
*/

public class LC205IsomorphicStrings {

    /**
     * Determines whether two strings are isomorphic.
     *
     * @param s source string
     * @param t target string
     * @return true if the strings are isomorphic, otherwise false
     */
    public boolean isIsomorphic(String s, String t) {
        int[] sMap = new int[256];
        int[] tMap = new int[256];

        Arrays.fill(sMap, -1);
        Arrays.fill(tMap, -1);

        for (int i = 0; i < s.length(); i++) {
            int inS = s.charAt(i);
            int inT = t.charAt(i);

            int sMappedWith = sMap[inS];
            int tMappedWith = tMap[inT];

            if (sMappedWith == -1 && tMappedWith == -1) {
                sMap[inS] = inT;
                tMap[inT] = inS;
            } else if (sMappedWith != inT || tMappedWith != inS) {
                return false;
            }
        }

        return true;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)

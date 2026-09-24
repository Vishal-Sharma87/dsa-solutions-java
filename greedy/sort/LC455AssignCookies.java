package greedy.sort;

// Created at: 25-September-2026
// Last revised at: 25-September-2026
// Link: https://leetcode.com/problems/assign-cookies/

/*
Problem Description:
--------------------
Statement:
Assume you are a great parent who wants to give cookies to each child.
Each child has a greed factor g[i], which is the minimum cookie size
that the child will be content with.

Each cookie has a size s[j]. If s[j] >= g[i], the child can be satisfied
with that cookie. Each child can receive at most one cookie and each cookie
can be assigned to at most one child.

Return the maximum number of children that can be satisfied.

Example:
Input:
g = [1,2,3]
s = [1,1]

Output:
1

Explanation:
Only the child with greed factor 1 can be satisfied.

Constraints:
- 1 <= g.length <= 3 * 10^4
- 0 <= s.length <= 3 * 10^4
- 1 <= g[i], s[j] <= 2^31 - 1
*/

/*
Approach 1: Brute Force

Idea:
For every child, search for an unused cookie that can satisfy the child.
Mark the selected cookie as used.

Time Complexity:
O(n * m)

Space Complexity:
O(m)

Drawbacks:
Repeatedly searching for a suitable cookie is inefficient.
Sorting allows us to make the assignment greedily in one pass.
*/

/*
Approach 2: Greedy + Sorting

Idea:
Sort both greed factors and cookie sizes.

Start with the least greedy child and the smallest cookie.
- If the cookie can satisfy the child, assign it and move both pointers.
- Otherwise, the cookie is too small, so skip it and try the next cookie.

Using the smallest possible cookie for each child preserves larger cookies
for children with higher greed factors.

Time Complexity:
O(n log n + m log m)

Space Complexity:
O(1) auxiliary space, excluding the sorting implementation.

Drawbacks:
Requires sorting both arrays.

Key Insight:
After sorting, always try to satisfy the least greedy remaining child
with the smallest cookie that can satisfy them.
*/

/*
Method to Solve:
----------------
1. Sort the greed factors.
2. Sort the cookie sizes.
3. Use two pointers for children and cookies.
4. If the current cookie satisfies the current child, assign it.
5. Otherwise, skip the current cookie.
6. Return the number of satisfied children.
*/

// Time Complexity: O(n log n + m log m)
// Space Complexity: O(1) auxiliary space

import java.util.Arrays;

public class LC455AssignCookies {

    /**
     * Finds the maximum number of children that can be satisfied.
     *
     * @param g greed factor of each child
     * @param s size of each cookie
     * @return maximum number of satisfied children
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int satisfiedChildCount = 0;
        int i = 0;
        int j = 0;

        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                satisfiedChildCount++;
                i++;
                j++;
            } else {
                j++;
            }
        }

        return satisfiedChildCount;
    }
}
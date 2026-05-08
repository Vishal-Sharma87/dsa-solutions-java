package tree.medium;

import tree.TreeNode;

// Created at: 09-May-2026
// Last revised at: 09-May-2026
// Link: https://leetcode.com/problems/same-tree/

/*
Problem Description:
--------------------
Statement:
Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Example:
Input: p = [1,2,3], q = [1,2,3]
Output: true

Input: p = [1,2], q = [1,null,2]
Output: false

Constraints:
- The number of nodes in both trees is in the range [0, 100].
- -10^4 <= Node.val <= 10^4
*/

/*
Approach 1: DFS Recursion ★
Idea:
At every node, check three things — both are null (match), exactly one is null (mismatch),
values differ (mismatch). If all pass, recurse on both subtrees. The tree is same only if
every corresponding node pair satisfies all three checks.

Time Complexity: O(n) — every node visited once
Space Complexity: O(h) — recursion stack; O(log n) balanced, O(n) skewed

Key Insight:
Base case order matters. Both-null must be checked before either-null,
otherwise a valid (null, null) pair falls through to the single-null check and returns false.
*/

/*
Method to Solve:
----------------
1. If both nodes are null → trees match at this position, return true.
2. If only one is null → structural mismatch, return false.
3. If values differ → return false.
4. Recurse on left subtrees AND right subtrees — both must match.
*/

// Time Complexity: O(n)
// Space Complexity: O(h)

class LC100SameTree {

    /**
     * Checks if two binary trees are structurally identical with equal node values.
     *
     * @param p root of the first tree
     * @param q root of the second tree
     * @return true if both trees are identical, false otherwise
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // both sides exhausted — valid match
        if (p == null && q == null)
            return true;

        // structure mismatch
        if (p == null || q == null)
            return false;

        // value mismatch — prune early
        if (p.val != q.val)
            return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

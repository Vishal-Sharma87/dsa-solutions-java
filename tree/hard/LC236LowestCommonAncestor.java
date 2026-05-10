package tree.hard;

import tree.TreeNode;

// Created at: 11-May-2026
// Last revised at: 11-May-2026
// Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

/*
Problem Description:
--------------------
Statement:
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes p and q.
The LCA is defined as the lowest node in the tree that has both p and q as descendants
(a node is allowed to be a descendant of itself).

Example:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3  (3 is the lowest node with both 5 and 1 as descendants)

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5  (5 is an ancestor of 4, so LCA is 5 itself)

Constraints:
- The number of nodes is in the range [2, 10^5].
- -10^9 <= Node.val <= 10^9
- All Node.val are unique.
- p != q
- Both p and q exist in the tree.
*/

/*
Approach 1: Postorder DFS (Bubble-up)
--------------------------------------
Idea:
Recurse into both subtrees. Each call returns either:
  - null  → neither p nor q found in this subtree
  - p/q   → one of the targets found here
  - root  → this node is the LCA (split point)

At any node, if both left and right return non-null, the current node
is exactly where p and q diverge — it's the LCA. If only one side is
non-null, bubble that result up (the other target is either not present
or is a descendant of what we already found).

Time Complexity: O(N)
- Every node is visited exactly once.

Space Complexity: O(H)
- Recursion stack depth equals tree height.
- O(log N) for balanced, O(N) for skewed.

Key Insight:
The base case handles the ancestor-of-itself scenario: when we reach p,
we stop and return p immediately — if q lives below p, p is still the LCA,
and returning p bubbles the correct answer up without needing to explore further.
*/

/*
Method to Solve:
----------------
1. If root is null, p, or q — return root immediately.
2. Recurse left and right subtrees independently.
3. If both return non-null → current root is the LCA, return root.
4. If one side is null → return the non-null side (LCA lies there).
5. If both null → neither target found, return null.
*/

// Time Complexity: O(N)
// Space Complexity: O(H)

public class LC236LowestCommonAncestor {

    /**
     * Finds the lowest common ancestor of nodes p and q in a binary tree.
     *
     * @param root the root of the binary tree (or current subtree during recursion)
     * @param p    first target node
     * @param q    second target node
     * @return the LCA node if found in this subtree, null otherwise
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // found a target or exhausted the subtree
        if (root == null || root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null && right == null)
            return null;
        if (left == null)
            return right;
        if (right == null)
            return left;
        return root;
    }
}
package bst.medium;

import tree.TreeNode;

// Created at: 17-May-2026
// Last revised at: 17-May-2026
// Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

/*
Problem Description:
--------------------
Statement:
Given a Binary Search Tree (BST), find the Lowest Common Ancestor (LCA) of two given nodes p and q.
The LCA is defined as the lowest node in the tree that has both p and q as descendants
(a node is allowed to be a descendant of itself).

Example:
Input: root = [6, 2, 8, 0, 4, 7, 9, null, null, 3, 5], p = 2, q = 8
Output: 6

Input: root = [6, 2, 8, 0, 4, 7, 9, null, null, 3, 5], p = 2, q = 4
Output: 2
Explanation: Node 2 is an ancestor of 4, so LCA is 2 itself.

Constraints:
- The number of nodes is in the range [2, 10^5].
- -10^9 <= Node.val <= 10^9
- All Node.val are unique.
- p != q; both p and q exist in the BST.
*/

/*
Approach 1: General LCA (ignoring BST property)
Idea:
    Treat the tree as a plain binary tree. Recurse both subtrees and use the presence
    of p or q in each half to determine the LCA.

Time Complexity: O(n) — must visit every node in the worst case.

Space Complexity: O(h) — recursion stack.

Drawbacks:
    Wastes the BST ordering property. O(n) is unnecessary when O(h) is achievable.

---

★ Approach 2: BST-aware Three-way Split
Idea:
    Normalize p and q so that p.val ≤ q.val. Then at each node, exactly one of three
    cases applies:

    1. root splits p and q  → p.val < root.val < q.val → root IS the LCA.
    2. root is below both   → root.val ≤ p.val          → both nodes are in right subtree.
    3. root is above both   → root.val ≥ q.val          → both nodes are in left subtree.

    Because only one branch is ever taken, this is tail-recursive (no backtracking).

Time Complexity: O(h) — h is the height of the BST; O(log n) for balanced, O(n) worst case.

Space Complexity: O(h) — recursion stack; O(1) if rewritten iteratively.

Key Insight:
    The BST ordering guarantees that value comparisons alone determine which subtree
    a node lives in. No need to explore both branches.
*/

// TreeNode definition assumed from LeetCode environment
// class TreeNode { int val; TreeNode left, right; }

/*
Method to Solve:
----------------
1. Entry point swaps p and q if p.val > q.val, ensuring p.val ≤ q.val throughout.
2. Base case: null root → return null. root == p or root == q → return root (ancestor case).
3. If p.val < root.val < q.val → root is the split point, return root.
4. If root.val < q.val → since separation failed, root ≤ p ≤ q → recurse right.
5. Otherwise root ≥ q ≥ p → recurse left.
*/

// Time Complexity: O(h)
// Space Complexity: O(h)

public class LC235LowestCommonAncestorOfBST {

    /**
     * Core recursive search. Assumes p.val ≤ q.val (enforced by caller).
     * Uses BST ordering to navigate directly toward the LCA without exploring both
     * subtrees.
     *
     * @param root current node
     * @param p    the smaller-valued target node
     * @param q    the larger-valued target node
     * @return the LCA node
     */
    private TreeNode findLca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;

        // root sits between p and q — it's the split point, so it's the LCA
        if (p.val < root.val && root.val < q.val)
            return root;

        // root ≤ p ≤ q — both targets are in the right subtree
        if (root.val < q.val)
            return findLca(root.right, p, q);

        // root ≥ q ≥ p — both targets are in the left subtree
        return findLca(root.left, p, q);
    }

    /**
     * Finds the lowest common ancestor of nodes p and q in a BST.
     * Normalizes ordering so that p.val ≤ q.val before delegating to findLca.
     *
     * @param root root of the BST
     * @param p    first target node
     * @param q    second target node
     * @return the LCA node of p and q
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // normalize: ensure p is the smaller node
        if (p.val > q.val)
            return lowestCommonAncestor(root, q, p);
        return findLca(root, p, q);
    }
}

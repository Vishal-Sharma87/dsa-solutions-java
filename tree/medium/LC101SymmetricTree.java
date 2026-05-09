package tree.medium;

import tree.TreeNode;

// Created at: 10-May-2026
// Last revised at: 10-May-2026
// Link: https://leetcode.com/problems/symmetric-tree/

/*
Problem Description:
--------------------
Statement:
    Given the root of a binary tree, check whether it is a mirror of itself
    (i.e., symmetric around its center).

Example 1 — symmetric:
         1
       /   \
      2     2
     / \   / \
    3   4 4   3

    Output: true

Example 2 — not symmetric:
         1
       /   \
      2     2
       \     \
        3     3

    Output: false  (left inner child missing, right inner child present)

Constraints:
    - Number of nodes: [1, 1000]
    - -100 ≤ Node.val ≤ 100
*/

/*
Approaches:
-----------
Approach 1: Recursive mirror check   ← implemented

Idea:
    A tree is symmetric if its left and right subtrees are mirrors of each other.
    Two trees are mirrors if:
      (a) both are null, OR
      (b) both are non-null, their root values are equal, AND
          left.left mirrors right.right  (outer pair)
          left.right mirrors right.left  (inner pair)
    Unpack the root into its two children and delegate to isMirror().

Time Complexity: O(N) — every node is visited exactly once
Space Complexity: O(H) — recursion stack equals tree height
                  O(log N) balanced, O(N) skewed

Key Insight:
    Symmetry is not about comparing a subtree to itself — it is about comparing
    the left subtree to a *flipped* version of the right subtree. The cross-pairing
    `(a.left, b.right)` and `(a.right, b.left)` encodes that flip.

---

Approach 2: Iterative using a queue

Idea:
    Use a queue initialised with (root.left, root.right). Each iteration dequeues
    two nodes and checks the mirror condition. Enqueue the next two cross-pairs:
    (a.left, b.right) and (a.right, b.left).

Time Complexity: O(N)
Space Complexity: O(N) — queue holds O(W) nodes (W = max width, up to N/2)

Drawbacks:
    More code, higher space for wide trees. Recursive version is cleaner and
    equally readable.
*/

/*
Method to Solve:
----------------
1. If root is null, return true — empty tree is symmetric by definition.
2. Call isMirror(root.left, root.right).
3. In isMirror(a, b):
   a. Both null  → true  (matched empty subtrees)
   b. One null   → false (asymmetric branch)
   c. Values differ → false
   d. Recurse: isMirror(a.left, b.right) AND isMirror(a.right, b.left)
      — outer pair and inner pair must both mirror.
*/

// Time Complexity: O(N)
// Space Complexity: O(H)

public class LC101SymmetricTree {

    /**
     * Checks whether two subtrees are mirrors of each other.
     * Two subtrees mirror if their roots match and their children
     * cross-mirror: outer pair (a.left ↔ b.right) and inner pair (a.right ↔
     * b.left).
     *
     * @param a root of the left subtree
     * @param b root of the right subtree
     * @return true if a and b are perfect mirrors of each other
     */
    private boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null)
            return true; // both empty — matched
        if (a == null || b == null)
            return false; // one-sided — asymmetric
        if (a.val != b.val)
            return false; // value mismatch

        // outer pair mirrors, inner pair mirrors
        return isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    /**
     * Returns true if the binary tree is symmetric around its center.
     *
     * @param root root of the binary tree
     * @return true if the tree is a mirror of itself
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isMirror(root.left, root.right);
    }
}
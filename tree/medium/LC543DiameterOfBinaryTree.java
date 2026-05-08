package tree.medium;

import tree.TreeNode;

// Created at: 08-May-2026
// Last revised at: 08-May-2026
// Link: https://leetcode.com/problems/diameter-of-binary-tree/

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, return the length of the diameter of the tree.
The diameter is the length of the longest path between any two nodes.
This path may or may not pass through the root.
Length of a path = number of edges between the two nodes.

Example:
Input: root = [1,2,3,4,5]
Output: 3 (path: 4 → 2 → 1 → 3)

Input: root = [1,2]
Output: 1

Constraints:
- The number of nodes in the tree is in the range [1, 10^4].
- -100 <= Node.val <= 100
*/

/*
Approach 1: Brute Force
Idea:
For every node, compute left height and right height separately.
The diameter through that node = leftHeight + rightHeight.
Track the global max across all nodes.

Time Complexity: O(n^2) — height recomputed from scratch at every node
Space Complexity: O(h)

Drawbacks:
Same redundant recomputation problem as LC110. Every node's height
is recalculated for each ancestor above it.

Approach 2: Single-pass DFS with Dimension carrier object ★
Idea:
Return both the height and the best diameter seen so far from each
subtree in a single DFS pass. A Dimension object carries both values
up the call stack so nothing is recomputed.

At each node:
- height = 1 + max(left.height, right.height)
- diameter = max(left.diameter, right.diameter, left.height + right.height)
  → the third term is the diameter of the path passing through this node

Time Complexity: O(n) — single DFS, every node visited once
Space Complexity: O(h) — recursion stack + one Dimension object per frame

Key Insight:
The longest path doesn't have to pass through the root. Carrying the
running max diameter up with the height avoids a second traversal or
a mutable global variable.
*/

/*
Method to Solve:
----------------
1. Base case: null node → return Dimension(height=0, diameter=0).
2. Recurse left → leftDim. Recurse right → rightDim.
3. height = 1 + max(leftDim.height, rightDim.height).
4. diameter = max(leftDim.diameter, rightDim.diameter, leftDim.height + rightDim.height).
5. Return new Dimension(height, diameter).
6. Answer = diameter(root).diameter.
*/

// Time Complexity: O(n)
// Space Complexity: O(h)

public class LC543DiameterOfBinaryTree {

    /**
     * Carries the height and best diameter seen so far
     * for a given subtree during the DFS traversal.
     */
    private static class Dimension {
        int height;
        int diameter;

        Dimension(int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }

    /**
     * Returns a Dimension object containing the height of this subtree
     * and the longest path (diameter) seen within it.
     *
     * @param root current tree node
     * @return Dimension with height and best diameter for this subtree
     */
    private Dimension computeDimension(TreeNode root) {
        if (root == null)
            return new Dimension(0, 0);

        Dimension left = computeDimension(root.left);
        Dimension right = computeDimension(root.right);

        int height = 1 + Math.max(left.height, right.height);

        // longest path through this node vs best seen in either subtree
        int diameter = Math.max(
                Math.max(left.diameter, right.diameter),
                left.height + right.height);

        return new Dimension(height, diameter);
    }

    /**
     * Returns the diameter of the binary tree — the number of edges
     * on the longest path between any two nodes.
     *
     * @param root root of the binary tree
     * @return diameter of the tree
     */
    public int diameterOfBinaryTree(TreeNode root) {
        return computeDimension(root).diameter;
    }
}
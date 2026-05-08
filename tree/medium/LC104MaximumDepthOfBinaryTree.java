package tree.medium;

// Created at: 08-May-2026
// Last revised at: 08-May-2026
// Link: https://leetcode.com/problems/maximum-depth-of-binary-tree/

import java.util.LinkedList;
import java.util.Queue;

import tree.TreeNode;

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, return its maximum depth.
Maximum depth is the number of nodes along the longest path
from the root node down to the farthest leaf node.

Example:
Input: root = [3,9,20,null,null,15,7]
Output: 3

Input: root = [1,null,2]
Output: 2

Constraints:
- The number of nodes in the tree is in the range [0, 10^4].
- -100 <= Node.val <= 100
*/

/*
Approach 1: Recursive DFS ★
Idea:
Height of a node = 1 + max(height of left subtree, height of right subtree).
Base case: null node contributes 0.

Time Complexity: O(n) — visits every node once
Space Complexity: O(h) — recursion stack, h = height of tree
                  O(log n) avg for balanced, O(n) worst for skewed

Key Insight:
Depth and height mean the same thing here — both count nodes on
the longest root-to-leaf path. Recursion maps directly to the definition.

Approach 2: Iterative BFS (Level Order)
Idea:
Each level traversal = one unit of depth. Count levels using a queue.
Process all nodes at the current level before moving to the next.

Time Complexity: O(n) — every node enqueued and dequeued once
Space Complexity: O(w) — queue holds at most one full level at a time,
                  w = max width. O(n/2) = O(n) worst case for complete tree.

Drawbacks:
More verbose than DFS. Space is proportional to tree width, not height —
worse than DFS for wide, shallow trees but same asymptotic complexity.
*/

/*
Method to Solve (Recursive DFS):
---------------------------------
1. Base case: root is null → return 0.
2. Recurse on left subtree → leftHeight.
3. Recurse on right subtree → rightHeight.
4. Return 1 + max(leftHeight, rightHeight).
*/

// Time Complexity: O(n)
// Space Complexity: O(h)

public class LC104MaximumDepthOfBinaryTree {

    /**
     * Computes the height of the tree rooted at the given node.
     * Helper for the recursive DFS approach.
     *
     * @param root current tree node
     * @return height of the subtree rooted at root
     */
    private int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        // go deeper on both sides, take the longer path
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    /**
     * Returns the maximum depth of the binary tree using recursive DFS.
     *
     * @param root root of the binary tree
     * @return maximum depth (number of nodes on longest root-to-leaf path)
     */
    public int maxDepth(TreeNode root) {
        return getHeight(root);
    }

    /**
     * Returns the maximum depth of the binary tree using iterative BFS (level
     * order).
     * Each completed level increments the depth counter by 1.
     *
     * @param root root of the binary tree
     * @return maximum depth of the tree
     */
    public int maxDepthBFS(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> q = new LinkedList<>();
        int depth = 0;

        q.offer(root);

        while (!q.isEmpty()) {
            depth++;
            int size = q.size(); // snapshot of current level size

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
            }
        }

        return depth;
    }
}
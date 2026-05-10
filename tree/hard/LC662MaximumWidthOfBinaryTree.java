package tree.hard;

// Created at: 11-May-2026
// Last revised at: 11-May-2026
// Link: https://leetcode.com/problems/maximum-width-of-binary-tree/

import java.util.Deque;
import java.util.LinkedList;

import tree.TreeNode;

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, return the maximum width of the tree.
The width of one level is the number of nodes between the leftmost and rightmost
non-null nodes (including the nulls in between). The answer is guaranteed to fit
in a 32-bit integer.

Example:
Input: root = [1,3,2,5,3,null,9]
Output: 4
(Level 3: nodes 5, 3, null, 9 — width = 4)

Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7

Constraints:
- The number of nodes is in the range [1, 3000].
- -100 <= Node.val <= 100
*/

/*
Approach 1: BFS with Level-Normalized Positions
-------------------------------------------------
Idea:
Assign each node an index as if the tree were a complete binary tree:
  root → 0, left child → 2*pos+1, right child → 2*pos+2

Width at any level = rightmost_pos - leftmost_pos + 1.

Without normalization, positions grow as 2^depth and overflow long
for trees deeper than ~63. To fix this, subtract the leftmost position
of each level (the offset) before enqueuing children. This resets the
base to 0 every level, keeping values bounded to [0, 2 * levelWidth]
regardless of depth.

Time Complexity: O(N)
- Every node is enqueued and dequeued exactly once.

Space Complexity: O(W)
- Queue holds at most one full level at a time; W = maximum width.

Key Insight:
Width only depends on relative positions within a level, not absolute ones.
Normalizing by the level's leftmost position at each step keeps numbers small
without changing any width calculation.
*/

/*
Method to Solve:
----------------
1. Enqueue root with position 0.
2. At each level, snapshot the offset (leftmost node's pos).
3. Compute levelMax = last.pos - first.pos + 1 before processing.
4. For each node polled, compute relativePos = pos - offset.
5. Enqueue children with positions 2*relativePos+1 and 2*relativePos+2.
6. Track the running max across all levels.
*/

// Time Complexity: O(N)
// Space Complexity: O(W)

public class LC662MaximumWidthOfBinaryTree {

    private static class Pair {
        TreeNode node;
        long pos;

        Pair(TreeNode node, long pos) {
            this.node = node;
            this.pos = pos;
        }
    }

    /**
     * Returns the maximum width of the binary tree across all levels.
     * Width includes null gaps between the leftmost and rightmost nodes.
     *
     * @param root root of the binary tree
     * @return maximum width as an integer
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        Deque<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        long maxi = 0;

        while (!q.isEmpty()) {
            long offset = q.peekFirst().pos; // normalize this level to base 0

            long levelMax = q.peekLast().pos - q.peekFirst().pos + 1;
            maxi = Math.max(maxi, levelMax);

            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair pair = q.poll();
                long relPos = pair.pos - offset; // relative, not absolute

                if (pair.node.left != null)
                    q.offer(new Pair(pair.node.left, 2 * relPos + 1));
                if (pair.node.right != null)
                    q.offer(new Pair(pair.node.right, 2 * relPos + 2));
            }
        }

        return (int) maxi;
    }
}
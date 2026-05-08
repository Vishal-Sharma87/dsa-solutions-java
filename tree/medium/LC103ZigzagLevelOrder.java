package tree.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tree.TreeNode;

// Created at: 09-May-2026
// Last revised at: 09-May-2026
// Link: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values
(i.e., from left to right, then right to left for the next level and so on).

Example:
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]

Constraints:
- The number of nodes in the tree is in the range [0, 2000].
- -100 <= Node.val <= 100
*/

/*
Approach 1: BFS with Index-Controlled Array ★
Idea:
Standard BFS level-by-level. For each level, allocate a fixed-size Integer array.
If left-to-right, fill from index 0 → size-1. If right-to-left, fill from index size-1 → 0.
Queue is always polled in natural BFS order — only the write index changes direction.
No reversal step needed.

Time Complexity: O(n) — every node visited once
Space Complexity: O(n) — queue holds at most one full level (~n/2 nodes at last level)

Key Insight:
Flipping the write index is cheaper than reversing the list after collection.
BFS order stays consistent — direction is purely a placement decision.
*/

/*
Method to Solve:
----------------
1. Seed the queue with root. Set isLeftToRight = true.
2. Each iteration: capture current level size, allocate Integer[] of that size.
3. If left-to-right: poll nodes, fill array 0 → size-1, enqueue children.
4. If right-to-left: poll nodes, fill array size-1 → 0, enqueue children.
5. Convert array to List, add to result. Toggle direction.
6. Repeat until queue is empty.
*/

// Time Complexity: O(n)
// Space Complexity: O(n)

class LC103ZigzagLevelOrder {

    /**
     * Returns zigzag level order traversal of a binary tree.
     *
     * @param root root of the binary tree
     * @return list of levels, alternating left-to-right and right-to-left
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigzag = new ArrayList<>();
        if (root == null)
            return zigzag;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean isLeftToRight = true;

        while (!q.isEmpty()) {
            int size = q.size();
            Integer[] oneLevel = new Integer[size];
            TreeNode node;

            if (isLeftToRight) {
                for (int i = 0; i < size; i++) {
                    node = q.poll();
                    oneLevel[i] = node.val;
                    if (node.left != null)
                        q.offer(node.left);
                    if (node.right != null)
                        q.offer(node.right);
                }
            } else {
                // fill backwards — no reversal needed
                for (int i = size - 1; i >= 0; i--) {
                    node = q.poll();
                    oneLevel[i] = node.val;
                    if (node.left != null)
                        q.offer(node.left);
                    if (node.right != null)
                        q.offer(node.right);
                }
            }

            zigzag.add(new ArrayList<>(Arrays.asList(oneLevel)));
            isLeftToRight = !isLeftToRight;
        }

        return zigzag;
    }
}
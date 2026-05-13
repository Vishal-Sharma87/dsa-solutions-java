package tree.hard;

// Created at: 14-May-2026
// Last revised at: 14-May-2026
// Link: https://leetcode.com/problems/binary-tree-inorder-traversal/

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, return the inorder traversal
of its nodes' values (Left → Root → Right).

Example:
Input:
    1
     \
      2
     /
    3

Output: [1, 3, 2]

Constraints:
- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100
*/

/*
Approach 1: Recursive
Idea:
Traverse left subtree recursively, collect root, traverse right subtree.
Standard DFS with call stack doing the bookkeeping.

Time Complexity: O(n)
Space Complexity: O(h) — implicit call stack, h = tree height.
                  O(log n) balanced, O(n) skewed.

Drawbacks:
Stack overflow risk on deeply skewed trees.
Hidden O(h) space that's easy to overlook.
*/

/*
Approach 2: Iterative with Explicit Stack
Idea:
Simulate the recursion manually using a stack.
Push nodes going left, pop and collect when no left child remains,
then move to right.

Time Complexity: O(n)
Space Complexity: O(h) — stack holds at most h nodes at any time.

Drawbacks:
Still O(h) space. Slightly more code than recursive.
*/

/*
Approach 3: Morris Traversal ★
Idea:
Eliminate the stack entirely by temporarily wiring each node's
inorder predecessor (rightmost node of its left subtree) back
to the node itself using its unused right pointer (a "thread").

Two visits per node with a left child:
  - First visit  → thread predecessor.right = node, descend left.
  - Second visit → thread found (predecessor.right == node),
                   collect node, remove thread, move right.

Nodes with no left child are collected immediately and we move right.

After traversal, all threads are cleaned up — tree structure is preserved.

Time Complexity: O(n) — each node visited at most twice.
                 Predecessor search across all nodes is O(n) total (amortized).
Space Complexity: O(1) — no stack, no recursion. Output list excluded.

Key Insight:
The "wasted" right pointer of the inorder predecessor is the only
bookkeeping needed. The thread itself IS the return address.
*/

/*
Method to Solve (Morris Traversal):
------------------------------------
1. Start mover at root.
2. While mover is not null:
   a. If mover has no left child:
      - Collect mover.val (nothing left to process on the left).
      - Move right.
   b. If mover has a left child:
      - Save mover as `node` (current node we'll eventually collect).
      - Find inorder predecessor: rightmost node of left subtree
        that doesn't already point back to node.
      - If predecessor.right == null (first visit):
          Thread: predecessor.right = node.
          Descend: mover = node.left.
      - If predecessor.right == node (second visit):
          Collect node.val.
          Remove thread: predecessor.right = null.
          Move right: mover = node.right.
3. Return collected list.
*/

import java.util.*;

import tree.TreeNode;

class LC94BinaryTreeInorderMorrisTraversal {

    /**
     * Returns the inorder traversal of a binary tree using Morris Traversal.
     * Achieves O(1) space by threading inorder predecessors temporarily.
     * Tree structure is fully restored after traversal.
     *
     * @param root root of the binary tree
     * @return list of node values in inorder (Left → Root → Right)
     */
    // Time Complexity: O(n)
    // Space Complexity: O(1) — excluding output list
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> elements = new ArrayList<>();

        TreeNode mover = root;
        while (mover != null) {
            if (mover.left == null) {
                // no left subtree — collect and move right
                elements.add(mover.val);
                mover = mover.right;
            } else {
                TreeNode node = mover;
                // move to left subtree to find inorder predecessor
                TreeNode pred = mover.left;
                while (pred.right != null && pred.right != node)
                    pred = pred.right;

                if (pred.right == null) {
                    // first visit — thread predecessor back to node
                    pred.right = node;
                    mover = node.left;
                } else {
                    // second visit — predecessor already threaded
                    elements.add(node.val);
                    pred.right = null; // remove thread, restore tree
                    mover = node.right;
                }
            }
        }

        return elements;
    }
}
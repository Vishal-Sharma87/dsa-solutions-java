package tree.hard;

// Created at: 14-May-2026
// Last revised at: 14-May-2026
// Link: https://leetcode.com/problems/binary-tree-preorder-traversal/

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, return the preorder traversal
of its nodes' values (Root → Left → Right).

Example:
Input:
    1
     \
      2
     /
    3

Output: [1, 2, 3]

Constraints:
- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100
*/

/*
Approach 1: Recursive
Idea:
Collect root, recurse left, recurse right.

Time Complexity: O(n)
Space Complexity: O(h) — implicit call stack.

Drawbacks:
Stack overflow risk on deeply skewed trees.
*/

/*
Approach 2: Iterative with Explicit Stack
Idea:
Push root, pop and collect, push right then left (so left is processed first).

Time Complexity: O(n)
Space Complexity: O(h)

Drawbacks:
Still O(h) space.
*/

/*
Approach 3: Morris Traversal ★
Idea:
Same threading mechanism as Morris Inorder — wire each node's inorder
predecessor (rightmost of left subtree) back to the node using its
spare right pointer.

The only difference from inorder: collect the node on the FIRST visit
(before threading and descending left), not the second.

  - First visit  → collect node.val, thread predecessor.right = node, go left.
  - Second visit → unthread, move right. (no collect — already done)
  - No left child → collect immediately, move right.

Time Complexity: O(n) — each node visited at most twice;
                 predecessor search is O(n) total across all steps.
Space Complexity: O(1) — no stack, no recursion. Output list excluded.

Key Insight:
Inorder   = collect on 2nd visit (Left done, now Root).
Preorder  = collect on 1st visit (Root before Left).
One-line change separates the two.
*/

/*
Method to Solve (Morris Preorder):
-----------------------------------
1. Start mover at root.
2. While mover is not null:
   a. If mover has no left child:
      - Collect mover.val.
      - Move right.
   b. If mover has a left child:
      - Save mover as node.
      - Find inorder predecessor: rightmost of left subtree
        that doesn't already point back to node.
      - If pred.right == null (first visit):
          Collect node.val  ← root collected before descending left
          Thread: pred.right = node.
          Descend: mover = node.left.
      - If pred.right == node (second visit):
          Remove thread: pred.right = null.
          Move right: mover = node.right.
3. Return collected list.
*/

import java.util.*;

import tree.TreeNode;

public class LC144BinaryTreeMorrisPreorderTraversal {

    /**
     * Returns the preorder traversal of a binary tree using Morris Traversal.
     * Collects each node on first visit — before descending into left subtree.
     * Tree structure is fully restored after traversal.
     *
     * @param root root of the binary tree
     * @return list of node values in preorder (Root → Left → Right)
     */
    // Time Complexity: O(n)
    // Space Complexity: O(1) — excluding output list
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> elements = new ArrayList<>();

        TreeNode mover = root;
        while (mover != null) {
            if (mover.left == null) {
                // no left subtree — collect and move right
                elements.add(mover.val);
                mover = mover.right;
            } else {
                TreeNode node = mover;
                // find inorder predecessor of node
                TreeNode pred = mover.left;
                while (pred.right != null && pred.right != node)
                    pred = pred.right;

                if (pred.right == null) {
                    // first visit — collect root before going left
                    elements.add(node.val);
                    pred.right = node; // thread back
                    mover = node.left;
                } else {
                    // second visit — left subtree done, move right
                    pred.right = null; // remove thread, restore tree
                    mover = node.right;
                }
            }
        }

        return elements;
    }
}
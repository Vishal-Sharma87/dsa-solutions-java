package tree.traversal;

import java.util.*;

import tree.TreeNode;

public class InorderTraversal {

    /*
     * Approach 1: Recursive
     * Idea:
     * Recursively visit left subtree, process root, then visit right subtree.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h) — implicit call stack, h = tree height
     * 
     * Drawbacks:
     * Risk of stack overflow on deep/skewed trees. Each call frame carries
     * local variables and return address — heavier than an explicit stack entry.
     */

    /*
     * ★ Approach 2: Iterative using explicit stack
     * Idea:
     * Simulate recursion with an explicit stack. Go left pushing every node.
     * When stuck (left is null), pop and process immediately, then move right.
     * Unlike postorder, no need to check right subtree before processing —
     * inorder processes the node as soon as it is popped.
     * 
     * Time Complexity: O(n) — every node pushed and popped exactly once
     * Space Complexity: O(h) — stack holds at most h nodes at any time
     * 
     * Key Insight:
     * By the time a node is popped, its entire left subtree is already processed.
     * Inorder rule says process root before right — so process immediately on pop,
     * then treat right child as the new current node and repeat.
     */

    /*
     * Method to Solve:
     * ----------------
     * 1. Start mover at root.
     * 2. If mover is not null: push it, move left.
     * 3. If mover is null: pop from stack, process it, move right.
     * 4. Repeat until mover is null and stack is empty.
     */

    // Time Complexity: O(n)
    // Space Complexity: O(h)

    /**
     * Returns inorder traversal of a binary tree iteratively.
     *
     * @param root root of the binary tree
     * @return list of node values in inorder (Left → Root → Right)
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> elements = new ArrayList<>();
        if (root == null)
            return elements;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode mover = root;

        while (mover != null || !stack.isEmpty()) {
            if (mover != null) {
                stack.push(mover);
                mover = mover.left;
            } else {
                // left subtree done — process and move right
                mover = stack.pop();
                elements.add(mover.val);
                mover = mover.right;
            }
        }

        return elements;
    }

    /**
     * Recursive helper: left → root → right.
     *
     * @param root     current node
     * @param elements accumulator list
     */
    private void helper(TreeNode root, List<Integer> elements) {
        if (root == null)
            return;

        helper(root.left, elements);
        elements.add(root.val);
        helper(root.right, elements);
    }

    /**
     * Returns inorder traversal of the binary tree (left → root → right).
     *
     * @param root root of the binary tree
     * @return list of node values in inorder
     */
    public List<Integer> traverse(TreeNode root) {
        List<Integer> elements = new ArrayList<>();
        helper(root, elements);
        return elements;
    }
}
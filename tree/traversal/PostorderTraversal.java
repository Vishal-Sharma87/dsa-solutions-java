package tree.traversal;

import java.util.*;

import tree.TreeNode;

public class PostorderTraversal {

    /*
     * Approach 1: Recursive
     * Idea:
     * Recursively visit left subtree, right subtree, then process root.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h) — implicit call stack, h = tree height
     * 
     * Drawbacks:
     * Risk of stack overflow on deep/skewed trees. Call stack frames are heavier
     * than explicit stack entries.
     */

    /*
     * ★ Approach 2: Iterative using explicit stack + lastVisited pointer
     * Idea:
     * Simulate recursion using an explicit stack. Go left pushing nodes.
     * On backtrack (pop), check if right subtree is done before processing.
     * Track lastVisited to know if right subtree was already processed.
     * 
     * Time Complexity: O(n) — every node pushed and popped exactly once
     * Space Complexity: O(h) — stack holds at most h nodes at any time
     * 
     * Key Insight:
     * When a node is popped, its left subtree is guaranteed to be done
     * (same as inorder). We only need to check the right subtree via lastVisited.
     * Process the node only if:
     * 1. It has no right child, OR
     * 2. lastVisited == node.right (right subtree just finished)
     * Otherwise push it back and explore right first.
     */

    /*
     * Method to Solve:
     * ----------------
     * 1. Use a mover pointer starting at root and a lastVisited pointer (null).
     * 2. Push mover and go left until mover is null.
     * 3. Pop from stack.
     * 4. If popped.right == null or lastVisited == popped.right:
     * process the node, set lastVisited = popped, set mover = null.
     * 5. Else: push it back, set mover = popped.right (explore right subtree
     * first).
     * 6. Repeat until mover is null and stack is empty.
     */

    // Time Complexity: O(n)
    // Space Complexity: O(h)

    /**
     * Returns postorder traversal of a binary tree iteratively.
     *
     * @param root root of the binary tree
     * @return list of node values in postorder (Left → Right → Root)
     */
    public List<Integer> postorderTraversalIterative(TreeNode root) {
        List<Integer> elements = new ArrayList<>();
        if (root == null)
            return elements;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode mover = root;
        TreeNode last = null;

        while (mover != null || !stack.isEmpty()) {
            if (mover != null) {
                stack.push(mover);
                mover = mover.left;
            } else {
                mover = stack.pop();
                if (mover.right == null || last == mover.right) {
                    // right subtree done — safe to process
                    elements.add(mover.val);
                    last = mover;
                    mover = null;
                } else {
                    // right subtree pending — come back after it
                    stack.push(mover);
                    mover = mover.right;
                }
            }
        }

        return elements;
    }

    /**
     * Recursive helper: left → right → root.
     *
     * @param root     current node
     * @param elements accumulator list
     */
    private void helper(TreeNode root, List<Integer> elements) {
        if (root == null)
            return;

        helper(root.left, elements);
        helper(root.right, elements);
        elements.add(root.val);
    }

    /**
     * Returns postorder traversal of the binary tree (left → right → root).
     *
     * @param root root of the binary tree
     * @return list of node values in postorder
     */
    public List<Integer> traverse(TreeNode root) {
        List<Integer> elements = new ArrayList<>();
        helper(root, elements);
        return elements;
    }
}
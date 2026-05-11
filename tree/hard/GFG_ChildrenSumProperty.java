package tree.hard;

import tree.TreeNode;

// Created at: 12-May-2026
// Last revised at: 12-May-2026
// Link: https://www.geeksforgeeks.org/problems/children-sum-parent/1

/*
Problem Description:
--------------------
Statement:
Given a binary tree, check whether every non-leaf TreeNode's data equals the
sum of its children's data values. Leaf TreeNodes trivially satisfy the property.

Example:
        10
       /  \
      3    7      → valid (3+7=10, both leaves)

        10
       /  \
      3    6      → invalid (3+6=9 ≠ 10)

Constraints:
1 ≤ N ≤ 10^5
0 ≤ TreeNode.val ≤ 10^4
*/

/*
Approach 1: Brute Force — Check each TreeNode independently
Idea:
    For every TreeNode, look up its children and verify TreeNode.val == left + right.
    Repeat for all TreeNodes via a separate traversal.

Time Complexity:  O(n)
Space Complexity: O(h) — recursion stack

Drawbacks:
    Needs null-child handling at every call site.
    Tempting to write it cleanly but the null-check noise adds up.
    No fundamentally worse than optimal but less elegant.

★ Approach 2: DFS with Sentinel Return Value
Idea:
    Return the TreeNode's data if its subtree is valid, or -1 if not.
    Null TreeNodes return 0 (safe fill-in for missing children).
    Leaf TreeNodes return their own data — trivially valid.
    Internal TreeNodes check: root.val == left + right, return root.val or -1.

    The -1 sentinel is safe because TreeNode.val ∈ [0, 10^4] — no real
    TreeNode value collides with -1.

Time Complexity:  O(n) — every TreeNode visited once
Space Complexity: O(h) — recursion depth; O(log n) balanced, O(n) skewed
*/

/*
Method to Solve:
----------------
1. Base case: null → return 0 (missing child contributes nothing to sum).
2. Leaf TreeNode → return root.val (trivially valid, no children to check).
3. Recurse left; if -1 is returned, propagate -1 immediately.
4. Recurse right; if -1 is returned, propagate -1 immediately.
5. Check root.val == left + right.
   - Null children naturally contribute 0, so single-child TreeNodes work without
     any extra null check.
6. Return root.val if satisfied, -1 otherwise.
7. In the public method, the tree is valid iff validate(root) != -1.
*/

class GFG_ChildrenSumProperty {

    /**
     * Validates the children sum property for the subtree rooted at {@code root}.
     * Returns the TreeNode's data on success, or -1 if the property is violated
     * anywhere in the subtree.
     *
     * <p>
     * Null TreeNodes return 0 so that missing children contribute nothing to the
     * parent's expected sum without requiring a separate null check at the call
     * site.
     *
     * @param root root of the subtree being validated
     * @return root.val if the entire subtree satisfies the property; -1 otherwise
     */
    private int validate(TreeNode root) {
        if (root == null)
            return 0; // safe fill-in — null child adds 0 to parent's sum

        // leaf: no children to verify, always valid
        if (root.left == null && root.right == null)
            return root.val;

        int left = validate(root.left);
        if (left == -1)
            return -1; // short-circuit — no point checking right

        int right = validate(root.right);
        if (right == -1)
            return -1;

        // left + right handles one-null-child case via the 0 fill-in above
        return root.val == left + right ? root.val : -1;
    }

    /**
     * Returns true if every non-leaf TreeNode in the tree satisfies the children
     * sum property (TreeNode.val == left.val + right.val).
     *
     * @param root root of the binary tree
     * @return true if the property holds for the entire tree, false otherwise
     */
    public boolean isSumProperty(TreeNode root) {
        return validate(root) != -1;
    }
}
package tree.medium;

import tree.TreeNode;

// Created at: 08-May-2026
// Last revised at: 08-May-2026
// Link: https://leetcode.com/problems/binary-tree-maximum-path-sum/

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, return the maximum path sum of any non-empty path.
A path is a sequence of nodes where each pair of adjacent nodes has an edge.
A node can only appear once in the path. The path does not need to pass through the root.

Example:
Input: root = [1,2,3]
Output: 6 (path: 2 → 1 → 3)

Input: root = [-10,9,20,null,null,15,7]
Output: 42 (path: 15 → 20 → 7)

Constraints:
- The number of nodes in the tree is in the range [1, 3 * 10^4].
- -1000 <= Node.val <= 1000
*/

/*
Approach 1: DFS with PathSum Carrier Object
Idea:
At each node, track two values:
- include: best path sum starting at this node going downward (can be extended by parent)
- max: best path sum seen anywhere in this subtree (may span both children, parent can't extend it)

Handle 4 cases explicitly: leaf, only left child, only right child, both children.
The path through both children is a valid candidate for max but NOT for include,
since a parent can only extend one side.

Time Complexity: O(n) — single DFS pass
Space Complexity: O(h) — recursion stack + one PathSum object per frame

Key Insight:
Separating "include" (extendable upward) from "max" (best seen so far, not extendable)
is the core of this approach. A path through both children closes at this node.

Approach 2: DFS with Max Gain + Global Tracker ★
Idea:
Each node contributes its value plus at most one downward path (left or right, whichever is better).
Negative contributions are dropped by clamping to 0.
At each node, the candidate answer = node.val + max(0, leftGain) + max(0, rightGain).
Update a global max with this candidate, but return only node.val + max(0, leftGain, rightGain)
to the parent so it can extend at most one side.

Time Complexity: O(n)
Space Complexity: O(h)

Key Insight:
Clamping negative gains to 0 eliminates all the null/case handling.
The logic collapses to 3 lines per node versus 4 explicit branches.
*/

/*
Method to Solve (Approach 1 - PathSum carrier):
------------------------------------------------
1. Base case: null → return null.
2. Recurse left and right → leftSum, rightSum.
3. Delegate to buildPathSum() with left result, right result, root.val.
4. Handle 4 cases: leaf / only left / only right / both children.
5. include = best single downward path from this node.
6. max = best path anywhere in subtree, including paths through both children.

Method to Solve (Approach 2 - Max Gain):
-----------------------------------------
1. Keep a globalMax tracker (int[1] to mutate across frames).
2. At each node: leftGain = max(0, maxGain(left)), rightGain = max(0, maxGain(right)).
3. Update globalMax with node.val + leftGain + rightGain.
4. Return node.val + max(leftGain, rightGain) to parent (only one side extendable).
*/

// Time Complexity: O(n)
// Space Complexity: O(h)

public class LC124BinaryTreeMaximumPathSum {

    // ─── Approach 1: PathSum Carrier Object ───────────────────────────────────

    /**
     * Carries two values up the DFS call stack per subtree:
     * the best extendable downward path from this node (include),
     * and the best path seen anywhere inside this subtree (max).
     */
    private static class PathSum {
        int max;
        int include;

        PathSum(int max, int include) {
            this.max = max;
            this.include = include;
        }
    }

    /**
     * Combines left and right subtree results with the current root value
     * to produce the PathSum for this node. Handles all child-presence cases.
     *
     * @param left      PathSum from left subtree, or null if no left child
     * @param right     PathSum from right subtree, or null if no right child
     * @param rootValue value of the current node
     * @return PathSum containing max and include for this subtree
     */
    private PathSum buildPathSum(PathSum left, PathSum right, int rootValue) {

        // leaf node
        if (left == null && right == null)
            return new PathSum(rootValue, rootValue);

        int maxSum;
        int includeSum;

        // only left child exists
        if (right == null) {
            maxSum = Math.max(rootValue, Math.max(left.max, left.include + rootValue));
            includeSum = Math.max(rootValue, left.include + rootValue);
            return new PathSum(maxSum, includeSum);
        }

        // only right child exists
        if (left == null) {
            maxSum = Math.max(rootValue, Math.max(right.max, right.include + rootValue));
            includeSum = Math.max(rootValue, right.include + rootValue);
            return new PathSum(maxSum, includeSum);
        }

        // both children exist
        int throughRootBothSides = left.include + right.include + rootValue;
        int throughRootOneSide = Math.max(left.include, right.include) + rootValue;
        int withRoot = Math.max(rootValue, Math.max(throughRootOneSide, throughRootBothSides));

        maxSum = Math.max(withRoot, Math.max(left.max, right.max));
        includeSum = Math.max(rootValue, throughRootOneSide); // can't take both sides for parent

        return new PathSum(maxSum, includeSum);
    }

    /**
     * Recursively computes the PathSum for the subtree rooted at root.
     *
     * @param root current tree node
     * @return PathSum for this subtree, or null if root is null
     */
    private PathSum calculate(TreeNode root) {
        if (root == null)
            return null;

        PathSum left = calculate(root.left);
        PathSum right = calculate(root.right);

        return buildPathSum(left, right, root.val);
    }

    /**
     * Returns the maximum path sum in the binary tree using the PathSum carrier
     * approach.
     *
     * @param root root of the binary tree
     * @return maximum path sum of any non-empty path
     */
    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return root.val;
        return calculate(root).max;
    }

    // ─── Approach 2: Max Gain with Global Tracker ─────────────────────────────

    /**
     * Computes the maximum gain this node can contribute to its parent
     * (single downward path only). Updates the global max as a side effect.
     * Negative gains are clamped to 0 — no point extending a losing path.
     *
     * @param root      current tree node
     * @param globalMax int[1] array used to track the global maximum path sum
     * @return best gain this node can offer to its parent
     */
    private int maxGain(TreeNode root, int[] globalMax) {
        if (root == null)
            return 0;

        // drop negative contributions — they only hurt the sum
        int leftGain = Math.max(0, maxGain(root.left, globalMax));
        int rightGain = Math.max(0, maxGain(root.right, globalMax));

        // candidate: path through this node spanning both sides
        globalMax[0] = Math.max(globalMax[0], root.val + leftGain + rightGain);

        // parent can only extend one side
        return root.val + Math.max(leftGain, rightGain);
    }

    /**
     * Returns the maximum path sum in the binary tree using the max gain approach.
     *
     * @param root root of the binary tree
     * @return maximum path sum of any non-empty path
     */
    public int maxPathSumOptimal(TreeNode root) {
        int[] globalMax = { Integer.MIN_VALUE };
        maxGain(root, globalMax);
        return globalMax[0];
    }
}
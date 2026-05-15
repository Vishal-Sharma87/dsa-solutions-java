package bst.medium;

import tree.TreeNode;

// Created at: 16-May-2026
// Last revised at: 16-May-2026
// Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/

/*
Problem Description:
--------------------
Statement:
Given the root of a BST and an integer k, return the kth smallest
value (1-indexed) among all node values in the tree.

Example:
Input:  root = [3,1,4,null,2], k = 1
Output: 1

Input:  root = [5,3,6,2,4,null,null,1], k = 3
Output: 3

Constraints:
- Number of nodes: [1, 10^4]
- 0 <= Node.val <= 10^4
- 1 <= k <= n (k is always valid)
*/

/*
Approaches:
-----------
Approach 1: Inorder into list, index directly
Idea:
Run full inorder traversal, collect all values into a list,
return list.get(k - 1).

Time Complexity: O(n)
Space Complexity: O(n) — list stores all node values

Drawbacks:
Visits every node even if the answer is near the front.
Wastes O(n) space storing the entire sorted sequence.

---

Approach 2: Inorder with early exit ★
Idea:
Run inorder traversal but stop the moment the kth node is visited.
Use a shared mutable counter (int[]) decremented on each visit.
Return the value immediately when counter hits 0, propagating it
up through all recursive frames via a non-null Integer return.

Time Complexity: O(h + k) — h to reach leftmost, k steps to count up
Space Complexity: O(h) — recursive call stack

Key Insight:
Integer return type with null as sentinel cleanly separates
"answer found" from "still searching" without conflicting with
any valid node value. The early-exit propagation avoids visiting
the remaining (n - k) nodes entirely.
*/

/*
Method to Solve:
----------------
1. Recurse left first (smallest values).
2. If left subtree returned a non-null result, propagate it up immediately.
3. Decrement k. If k == 0, current node is the kth smallest — return its value.
4. Otherwise recurse right and return whatever it finds.
*/

class LC230KthSmallestElementInBST {

    /**
     * Inorder traversal with early exit once the kth node is visited.
     * Uses Integer return type — null means "not found yet",
     * non-null means the answer has been found and should propagate up.
     *
     * @param root current node in the traversal
     * @param k    single-element array acting as a shared mutable counter
     * @return kth smallest value if found in this subtree, null otherwise
     */
    private Integer inorder(TreeNode root, int[] k) {
        if (root == null)
            return null;

        // left subtree first — smallest values
        Integer found = inorder(root.left, k);
        if (found != null)
            return found; // answer already found, skip the rest

        // visit current node
        k[0]--;
        if (k[0] == 0)
            return root.val;

        // answer not yet found, try right subtree
        return inorder(root.right, k);
    }

    /**
     * Returns the kth smallest value in the BST.
     *
     * @param root root of the BST
     * @param k    1-indexed rank of the target value
     * @return kth smallest node value
     */
    public int kthSmallest(TreeNode root, int k) {
        return inorder(root, new int[] { k });
    }
}

// Time Complexity: O(h + k) — O(log n + k) balanced, O(n) skewed
// Space Complexity: O(h) — recursive call stack
package tree.dfs;

import tree.TreeNode;

// Created at: 11-September-2026
// Last revised at: 11-September-2026
// Link: https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, count the number of nodes where the
average of all nodes in its subtree is equal to the node's value.

The average is calculated using integer division.

Example:
Input: root = [4,8,5,0,1,null,6]

Output: 5

Explanation:
For each node, calculate the sum and number of nodes in its subtree.
Count the node if its value equals the integer average of that subtree.

Constraints:
- The number of nodes is within the problem constraints.
- Node values are positive integers.
- The tree may be empty.
*/

/*
Approach 1: Brute Force

Idea:
For every node, independently traverse its subtree to calculate the
sum and size, then compare the integer average with the node value.

Time Complexity:
O(n^2) in the worst case.

Space Complexity:
O(h) for recursive traversal.

Drawbacks:
Repeatedly traverses the same subtrees.

*/

/*
Approach 2: Postorder DFS

Idea:
Use postorder DFS so that each node receives the sum and size of its
left and right subtrees.

For the current node:
1. Combine the left and right subtree sums.
2. Combine their sizes.
3. Calculate the integer average.
4. Count the current node if the average equals root.val.
5. Return the sum, size, and valid-node count to the parent.

Time Complexity:
O(n)

Space Complexity:
O(h), where h is the height of the tree.

Key Insight:
A subtree's sum and size can be computed once and reused by its parent,
avoiding repeated traversal.
*/

/*
Method to Solve:
----------------
1. Perform a postorder DFS traversal.
2. Return {sum, size, validNodeCnt} for every subtree.
3. Combine the results from the left and right children.
4. Check whether sum / size equals the current node's value.
5. Increment the valid-node count when the condition holds.
6. Return the total count from the root.
*/

// Time Complexity: O(n)
// Space Complexity: O(h)

class LC2265CountNodesEqualToAverageOfSubtree {

    private record Data(int sum, int size, int validNodeCnt) {
    }

    /**
     * Calculates subtree information using postorder DFS.
     *
     * @param root root of the current subtree
     * @return sum, size, and valid-node count for the subtree
     */
    private Data calculate(TreeNode root) {
        if (root == null) {
            return new Data(0, 0, 0);
        }

        Data left = calculate(root.left);
        Data right = calculate(root.right);

        int sum = left.sum() + right.sum() + root.val;
        int size = left.size() + right.size() + 1;
        int validNodeCnt = left.validNodeCnt() + right.validNodeCnt();

        if (sum / size == root.val) {
            validNodeCnt++;
        }

        return new Data(sum, size, validNodeCnt);
    }

    /**
     * Counts nodes whose value equals the integer average of their subtree.
     *
     * @param root root of the binary tree
     * @return number of nodes satisfying the subtree-average condition
     */
    public int averageOfSubtree(TreeNode root) {
        return calculate(root).validNodeCnt();
    }
}
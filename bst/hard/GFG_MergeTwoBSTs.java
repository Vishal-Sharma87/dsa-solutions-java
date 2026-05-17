package bst.hard;

// Created at: 18-May-2026
// Last revised at: 18-May-2026
// Link: https://www.geeksforgeeks.org/problems/merge-two-bst-s/1

/*
Problem Description:
--------------------
Statement:
Given two BSTs, return a list containing the inorder traversal of both trees merged
and sorted.

Example:
Input:
  BST1:       BST2:
    5            3
   / \          / \
  3   6        1   4

Output: [1, 3, 3, 4, 5, 6]

Constraints:
- 1 <= Number of nodes <= 10^5
- 1 <= Node values <= 10^5
*/

/*
Approach 1: Brute Force — collect then sort
-------------------------------------------
Idea:
Do inorder traversal of both BSTs independently into two lists.
Merge the two sorted lists using the standard two-pointer technique.

Time Complexity: O((m + n) log(m + n)) if using sort; O(m + n) with two-pointer merge
Space Complexity: O(m + n) — storing both traversals explicitly

Drawbacks:
Stores all nodes in memory before merging. The two-pointer merge is fine,
but materializing both lists first wastes space when only one element
needs to be compared at a time.

★ Approach 2: Two-Stack Iterative Inorder Merge
------------------------------------------------
Idea:
Simulate iterative inorder traversal of both BSTs simultaneously using two stacks,
one per tree. This mirrors the merge step in merge sort — at each step, pick
the smaller "next element" from either traversal.

Each stack holds the path of nodes descended into but not yet visited.
The stack top is always the current minimum of that BST's remaining traversal.

When both current pointers are null:
  - If one stack is empty, drain the other.
  - If both are non-empty, compare tops and pop the smaller.
    Popping a node means: visit it (add to result), then activate its right subtree.

Why this is better:
No need to materialize full traversals. Stack depth is O(h) per tree,
not O(n). Processes nodes lazily — only what's needed at each step.

Time Complexity: O(m + n) — each node pushed and popped exactly once
Space Complexity: O(h1 + h2) — stack depth bounded by tree heights
*/

/*
Method to Solve:
----------------
1. Use two stacks (st1, st2) and two current pointers (cur1, cur2).
2. Loop while any pointer is non-null or any stack is non-empty.
3. Push left spine: if cur1 != null, push and go left. Else if cur2 != null, do the same.
4. Once both pointers are null, at least one stack is non-empty:
   - If st1 empty → pop from st2, add to result, move cur2 to its right child.
   - If st2 empty → pop from st1, add to result, move cur1 to its right child.
   - Both non-empty → compare tops, pop the smaller, add to result, move its
     current pointer to the popped node's right child.
5. Return the merged list.
*/

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import tree.TreeNode;

// Time Complexity: O(m + n)
// Space Complexity: O(h1 + h2)
public class GFG_MergeTwoBSTs {

    /**
     * Merges two BSTs and returns their combined inorder traversal (sorted).
     * Uses two stacks to interleave both traversals without materializing them.
     *
     * @param root1 root of the first BST
     * @param root2 root of the second BST
     * @return merged sorted list of all node values
     */
    public ArrayList<Integer> merge(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> result = new ArrayList<>();

        Deque<TreeNode> st1 = new LinkedList<>();
        Deque<TreeNode> st2 = new LinkedList<>();

        TreeNode cur1 = root1;
        TreeNode cur2 = root2;

        while (cur1 != null || cur2 != null || !st1.isEmpty() || !st2.isEmpty()) {

            // push left spine of cur1
            if (cur1 != null) {
                st1.push(cur1);
                cur1 = cur1.left;
            }
            // push left spine of cur2
            else if (cur2 != null) {
                st2.push(cur2);
                cur2 = cur2.left;
            }
            // both pointers exhausted — compare stack tops
            else {
                if (st1.isEmpty()) {
                    // only BST2 has nodes left
                    cur2 = st2.pop();
                    result.add(cur2.val);
                    cur2 = cur2.right;
                } else if (st2.isEmpty()) {
                    // only BST1 has nodes left
                    cur1 = st1.pop();
                    result.add(cur1.val);
                    cur1 = cur1.right;
                } else {
                    // pick smaller of the two current minimums
                    if (st1.peek().val <= st2.peek().val) {
                        cur1 = st1.pop();
                        result.add(cur1.val);
                        cur1 = cur1.right;
                    } else {
                        cur2 = st2.pop();
                        result.add(cur2.val);
                        cur2 = cur2.right;
                    }
                }
            }
        }

        return result;
    }
}
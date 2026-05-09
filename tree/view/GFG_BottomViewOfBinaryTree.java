package tree.view;

// Created at: 10-May-2026
// Last revised at: 10-May-2026
// Link: https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1

import java.util.*;

import tree.TreeNode;

/*
Problem Description:
--------------------
Statement:
    Given the root of a binary tree, return the bottom view of the tree — the set of
    nodes visible when the tree is viewed from directly below. Nodes are returned
    left to right.

    When two nodes share the same horizontal distance and the same depth (i.e., tie),
    the node in the right subtree is preferred (it overwrites the left subtree's node).

Example:
           1
         /   \
        2     3
       / \     \
      4   5     6
           \
            7

    Bottom view: [4, 2, 7, 3, 6]

Constraints:
    1 ≤ number of nodes ≤ 10^5
    1 ≤ node value ≤ 10^5
*/

/*
Approaches:
-----------
Approach 1: DFS with (depth, horizontal-distance) map   ← implemented

Idea:
    Assign each node a horizontal distance (hd) from root (left → hd-1, right → hd+1)
    and a depth (row from root, increasing downward). Use a HashMap<hd, NodeInfo>.
    For each column hd, keep only the node with the greatest depth — that's the one
    visible from the bottom. On a depth tie, the right subtree overwrites the left
    because DFS visits right after left and the condition is `<=` (not `<`).
    Sort the map keys at the end for left-to-right output.

Time Complexity:
    O(N log N) — O(N) DFS + O(K log K) sort of at most K ≤ N column keys

Space Complexity:
    O(N) — recursion stack O(H) + HashMap O(N)

Key Insight:
    Top view keeps the shallowest node per column (condition: stored.depth > current.depth).
    Bottom view is the exact mirror — keep the deepest (condition: stored.depth <= current.depth).
    BFS is arguably more natural here (the last node processed at each hd is automatically
    the deepest), but DFS with explicit depth comparison is equally correct.
*/

/*
Method to Solve:
----------------
1. Assign each node a horizontal distance (hd): root = 0, left child = hd-1, right child = hd+1.
2. Track depth alongside each node. The bottom-view node for column hd is the one
   with the maximum depth (latest overwrite wins on ties → right subtree preferred).
3. DFS: for each node, if its column has no entry yet, or its depth is ≥ the stored
   depth, update the map with NodeInfo(val, depth).
4. Collect all hd keys, sort them, and pull values from the map in that order.
*/

// Time Complexity: O(N log N)
// Space Complexity: O(N)

public class GFG_BottomViewOfBinaryTree {

    /** Stores node value alongside its depth (row from root). */
    private static class NodeInfo {
        int val;
        int depth;

        NodeInfo(int val, int depth) {
            this.val = val;
            this.depth = depth;
        }
    }

    /**
     * Recursively fills the column → NodeInfo map, keeping only the
     * deepest node for each horizontal distance.
     * On depth ties, the right subtree's node overwrites the left (via <=).
     *
     * @param root  current node being visited
     * @param depth row distance from the root (increases downward)
     * @param hd    horizontal distance from the root (negative = left, positive =
     *              right)
     * @param map   column → deepest NodeInfo seen so far
     */
    private void fillBottomView(TreeNode root, int depth, int hd, HashMap<Integer, NodeInfo> map) {
        if (root == null)
            return;

        NodeInfo existing = map.get(hd);
        // first node at this column, or this node is deeper (or same depth — right
        // wins)
        if (existing == null || existing.depth <= depth) {
            map.put(hd, new NodeInfo(root.val, depth));
        }

        fillBottomView(root.left, depth + 1, hd - 1, map);
        fillBottomView(root.right, depth + 1, hd + 1, map);
    }

    /**
     * Returns the bottom view of a binary tree from left to right.
     *
     * @param root root of the binary tree
     * @return list of node values visible from the bottom, ordered left to right
     */
    public ArrayList<Integer> bottomView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        HashMap<Integer, NodeInfo> map = new HashMap<>();
        fillBottomView(root, 0, 0, map);

        // sort columns left to right
        List<Integer> columns = new ArrayList<>(map.keySet());
        Collections.sort(columns);

        for (int col : columns) {
            result.add(map.get(col).val);
        }

        return result;
    }
}
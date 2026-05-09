package tree.view;
// Created at: 10-May-2026

// Last revised at: 10-May-2026
// Link: https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1

import java.util.*;

import tree.TreeNode;

/*
Problem Description:
--------------------
Statement:
    Given the root of a binary tree, return the top view of the tree — the set of
    TreeNodes visible when the tree is viewed from directly above. TreeNodes are returned
    left to right.

Example:
           1
         /   \
        2     3
         \
          4
           \
            5
            \
             6

    Top view: [2, 1, 3, 6]

Constraints:
    1 ≤ number of TreeNodes ≤ 10^5
    1 ≤ TreeNode data ≤ 10^5
*/

/*
Approaches:
-----------
Approach 1: DFS with (depth, horizontal-distance) map   ← implemented

Idea:
    Assign each TreeNode a horizontal distance (hd) from root (left → hd-1, right → hd+1)
    and a depth (level from root). Use a HashMap<hd, Pair(data, depth)>. During DFS,
    for each column hd, keep only the TreeNode with the smallest depth — that's the one
    visible from the top. Sort the map keys at the end to get left-to-right order.

Time Complexity:
    O(N log N) — O(N) DFS traversal + O(K log K) sort of at most K ≤ N column keys

Space Complexity:
    O(N) — recursion stack (O(H)) + HashMap (O(N))

Key Insight:
    BFS is the more common approach (processes TreeNodes level by level so the first
    occurrence at each hd is automatically the topmost). DFS works equally well
    because we explicitly track and compare depths — same result, slightly more
    bookkeeping.
*/

/*
Method to Solve:
----------------
1. Assign each TreeNode a horizontal distance (hd): root = 0, left child = hd-1, right child = hd+1.
2. Track depth (row) alongside each TreeNode. The top-view TreeNode for column hd is the one
   with the minimum depth.
3. DFS: for each TreeNode, if its column has no entry yet, or its depth is less than the
   stored depth, update the map with Pair(data, depth).
4. Collect all hd keys, sort them, and pull data from the map in that order.
*/

// Time Complexity: O(N log N)
// Space Complexity: O(N)

public class GFG_TopViewOfBinaryTree {

    /** Stores TreeNode data alongside its depth (row from root). */
    private static class TreeNodeInfo {
        int data;
        int depth;

        TreeNodeInfo(int data, int depth) {
            this.data = data;
            this.depth = depth;
        }
    }

    /**
     * Recursively fills the column → TreeNodeInfo map, keeping only the
     * shallowest TreeNode for each horizontal distance.
     *
     * @param root  current TreeNode being visited
     * @param depth row distance from the root (increases downward)
     * @param hd    horizontal distance from the root (negative = left, positive =
     *              right)
     * @param map   column → shallowest TreeNodeInfo seen so far
     */
    private void fillTopView(TreeNode root, int depth, int hd, HashMap<Integer, TreeNodeInfo> map) {
        if (root == null)
            return;

        TreeNodeInfo existing = map.get(hd);
        // first TreeNode at this column, or this TreeNode is higher up
        if (existing == null || existing.depth > depth) {
            map.put(hd, new TreeNodeInfo(root.val, depth));
        }

        fillTopView(root.left, depth + 1, hd - 1, map);
        fillTopView(root.right, depth + 1, hd + 1, map);
    }

    /**
     * Returns the top view of a binary tree from left to right.
     *
     * @param root root of the binary tree
     * @return list of TreeNode values visible from the top, ordered left to right
     */
    public ArrayList<Integer> topView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        HashMap<Integer, TreeNodeInfo> map = new HashMap<>();
        fillTopView(root, 0, 0, map);

        // sort columns left to right
        List<Integer> columns = new ArrayList<>(map.keySet());
        Collections.sort(columns);

        for (int col : columns) {
            result.add(map.get(col).data);
        }

        return result;
    }
}
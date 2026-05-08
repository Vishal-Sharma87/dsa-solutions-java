package tree.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import tree.TreeNode;

// Created at: 09-May-2026
// Last revised at: 09-May-2026
// Link: https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, calculate the vertical order traversal.
For each node at position (row, col), its left child is at (row+1, col-1) and right child at (row+1, col+1).
Root starts at (0, 0). Group nodes by column, sorted left to right. Within each column, sort by row,
then by value if row is the same.

Example:
Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]

Constraints:
- The number of nodes in the tree is in the range [1, 1000].
- 0 <= Node.val <= 1000
*/

/*
Approach 1: DFS + Column Bucketing + Sort ★
Idea:
Assign (row, col) coordinates to every node via DFS. Group nodes by column using a HashMap.
After traversal, sort column keys to get left-to-right order. Within each column, sort nodes
by row first, then by value if rows are equal. Extract values and merge into result.

The col field in Cordinates is kept intentionally — it makes the coordinate system self-documenting
and reflects how the problem was originally modeled (2D grid positions).

Time Complexity: O(n log n) — sorting dominates; each node sorted within its column bucket
Space Complexity: O(n) — HashMap stores all n nodes across all columns

Key Insight:
Column key in the map handles horizontal grouping. Sorting within each bucket handles
the row + value ordering. The two concerns are cleanly separated.
*/

/*
Method to Solve:
----------------
1. DFS the tree, assigning row (depth) and col (horizontal offset) to each node.
2. Group nodes into HashMap<col, List<Coordinates>>.
3. Extract and sort column keys → left-to-right column order.
4. For each column, sort its nodes by row, then by value on row tie.
5. Extract values from each sorted column and add to result.
*/

// Time Complexity: O(n log n)
// Space Complexity: O(n)

class LC987VerticalOrderTraversal {

    static class Cordinates {
        public int data;
        public int row;
        public int col;

        public Cordinates(int d, int r, int c) {
            data = d;
            row = r;
            col = c;
        }
    }

    /**
     * DFS to assign coordinates and bucket nodes by column.
     *
     * @param root     current node
     * @param x        current row (depth)
     * @param y        current column (horizontal offset)
     * @param oneLevel map of column → list of nodes in that column
     */
    private void verticalOrder(TreeNode root, int x, int y, HashMap<Integer, ArrayList<Cordinates>> oneLevel) {
        if (root == null)
            return;

        ArrayList<Cordinates> elements = oneLevel.getOrDefault(y, new ArrayList<>());
        elements.add(new Cordinates(root.val, x, y));
        oneLevel.put(y, elements);

        verticalOrder(root.left, x + 1, y - 1, oneLevel);
        verticalOrder(root.right, x + 1, y + 1, oneLevel);
    }

    /**
     * Sorts nodes in a column by row, then by value on tie.
     *
     * @param y        column key to sort
     * @param oneLevel map of column → node list
     */
    private void sort(int y, HashMap<Integer, ArrayList<Cordinates>> oneLevel) {
        ArrayList<Cordinates> elements = oneLevel.get(y);
        Collections.sort(elements, (a, b) -> {
            if (a.row == b.row)
                return a.data - b.data;
            return a.row - b.row;
        });
    }

    /**
     * Extracts values from a sorted column and appends to result.
     *
     * @param level    column key to merge
     * @param ans      final result list
     * @param oneLevel map of column → node list
     */
    private void merge(int level, List<List<Integer>> ans, HashMap<Integer, ArrayList<Cordinates>> oneLevel) {
        ArrayList<Cordinates> elements = oneLevel.get(level);
        ArrayList<Integer> values = new ArrayList<>();
        elements.forEach(e -> values.add(e.data));
        ans.add(values);
    }

    /**
     * Returns vertical order traversal of a binary tree.
     *
     * @param root root of the binary tree
     * @return list of columns, each sorted by row then value
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        HashMap<Integer, ArrayList<Cordinates>> oneLevel = new HashMap<>();

        verticalOrder(root, 0, 0, oneLevel);

        List<Integer> sortedLevels = new ArrayList<>(oneLevel.keySet());

        // left-to-right column order
        Collections.sort(sortedLevels);

        // sort nodes within each column
        sortedLevels.forEach(l -> sort(l, oneLevel));

        // collect values column by column
        sortedLevels.forEach(l -> merge(l, ans, oneLevel));

        return ans;
    }
}

package graph.shortestpath;

// Created at: 02-July-2026
// Last revised at: 02-July-2026
// Link: https://leetcode.com/problems/find-the-safest-path-in-a-grid/

/*
Problem Description:
--------------------
Statement:
You are given an n x n grid where 1 represents a thief and 0 represents an empty cell.
The safeness factor of a path is the minimum Manhattan distance from any cell in the
path to the nearest thief.

Return the maximum possible safeness factor among all valid paths from
(0,0) to (n-1,n-1).

Example:
Input:
[[1,0,0],
 [0,0,0],
 [0,0,1]]

Output:
0

Constraints:
- 1 <= n <= 400
- grid[i][j] is either 0 or 1
- There is at least one thief.
*/

/*
Approach 1: Brute Force

Idea:
Generate every possible path from source to destination and compute the minimum
distance to the nearest thief for each path.

Time Complexity:
Exponential

Space Complexity:
Exponential

Drawbacks:
Impossible for the given constraints.
*/

/*
Approach 2: Multi-Source BFS + Maximum Bottleneck Path (Optimal)

Idea:
1. Run a multi-source BFS from every thief simultaneously.
2. Compute the distance of every cell from its nearest thief.
3. Treat each cell's distance as its safeness value.
4. Use a max-heap (similar to Dijkstra) where the path value is the minimum
   safeness encountered so far.
5. Always expand the currently safest path first. The first time the destination
   is removed from the heap, its safeness factor is the answer.

Time Complexity:
O(n² log n)

Space Complexity:
O(n²)

Key Insight:
The second phase is a maximum bottleneck path problem instead of a shortest path.
*/

/*
Method to Solve:
----------------
1. Perform multi-source BFS starting from all thief cells.
2. Compute minimum distance of every cell to the nearest thief.
3. Start from (0,0) using a max-heap.
4. Propagate the minimum safeness seen along each path.
5. Return the value when destination is reached.
*/

import java.util.*;

public class LC2812FindTheSafestPathInAGrid {

    private static class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class State {
        int row;
        int col;
        int safeness;

        State(int row, int col, int safeness) {
            this.row = row;
            this.col = col;
            this.safeness = safeness;
        }
    }

    private static final int[] DR = { 0, 0, -1, 1 };
    private static final int[] DC = { -1, 1, 0, 0 };

    /**
     * Returns the maximum safeness factor from the top-left to bottom-right cell.
     *
     * @param grid input grid
     * @return maximum possible safeness factor
     */
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        int[][] safeness = buildSafenessGrid(grid);

        PriorityQueue<State> maxHeap = new PriorityQueue<>((a, b) -> b.safeness - a.safeness);

        maxHeap.offer(new State(0, 0, safeness[0][0]));
        safeness[0][0] *= -1;

        while (!maxHeap.isEmpty()) {

            State current = maxHeap.poll();

            if (current.row == n - 1 && current.col == n - 1) {
                return current.safeness;
            }

            for (int k = 0; k < 4; k++) {

                int nextRow = current.row + DR[k];
                int nextCol = current.col + DC[k];

                if (isValid(nextRow, nextCol, n) && safeness[nextRow][nextCol] > 0) {

                    maxHeap.offer(
                            new State(
                                    nextRow,
                                    nextCol,
                                    Math.min(current.safeness, safeness[nextRow][nextCol])));

                    // mark visited
                    safeness[nextRow][nextCol] *= -1;
                }
            }
        }

        return 0;
    }

    /**
     * Computes minimum distance from every cell to the nearest thief.
     *
     * @param grid input grid
     * @return safeness matrix
     */
    private int[][] buildSafenessGrid(List<List<Integer>> grid) {

        int n = grid.size();

        int[][] safeness = new int[n][n];
        Deque<Pair> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            Arrays.fill(safeness[i], -1);

            for (int j = 0; j < n; j++) {

                if (grid.get(i).get(j) == 1) {
                    safeness[i][j] = 0;
                    queue.offer(new Pair(i, j));
                }
            }
        }

        while (!queue.isEmpty()) {

            Pair current = queue.poll();

            for (int k = 0; k < 4; k++) {

                int nextRow = current.row + DR[k];
                int nextCol = current.col + DC[k];

                if (isValid(nextRow, nextCol, n) && safeness[nextRow][nextCol] == -1) {

                    safeness[nextRow][nextCol] = safeness[current.row][current.col] + 1;

                    queue.offer(new Pair(nextRow, nextCol));
                }
            }
        }

        return safeness;
    }

    /**
     * Checks whether the cell lies inside the grid.
     *
     * @param row row index
     * @param col column index
     * @param n   grid size
     * @return true if valid, otherwise false
     */
    private boolean isValid(int row, int col, int n) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    // Time Complexity: O(n² log n)
    // Space Complexity: O(n²)
}
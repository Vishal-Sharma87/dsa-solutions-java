package graph.dfs;

// Created at: 22-May-2026
// Last revised at: 22-May-2026
// Link: https://leetcode.com/problems/number-of-islands/

/*
Problem Description:
--------------------
Statement:
    Given an m x n 2D binary grid of '1's (land) and '0's (water),
    return the number of islands. An island is surrounded by water and is
    formed by connecting adjacent lands horizontally or vertically.

Example:
    Input:  grid = [["1","1","0","0","0"],
                    ["1","1","0","0","0"],
                    ["0","0","1","0","0"],
                    ["0","0","0","1","1"]]
    Output: 3

Constraints:
    - m == grid.length, n == grid[i].length
    - 1 <= m, n <= 300
    - grid[i][j] is '0' or '1'
*/

/*
Approach 1: DFS Flood Fill (Used here)
Idea:
    Iterate every cell. When an unvisited land cell ('1') is found, increment count
    and trigger a DFS that marks all reachable land cells as visited ('2').
    This "sinks" the entire island so it isn't counted again.

Time Complexity: O(m * n) — every cell visited at most once
Space Complexity: O(m * n) — recursive call stack in worst case (all land, one island)

Key Insight:
    Mutating the grid in-place (marking visited as '2') avoids a separate boolean
    visited array. If the grid must be preserved, use a copy or a separate array.

Approach 2: BFS Flood Fill
Idea:
    Same logic but iterative using a queue. Avoids potential stack overflow on very
    large grids where DFS stack depth could hit m * n.

Time Complexity: O(m * n)
Space Complexity: O(min(m, n)) — BFS queue at worst holds the width of the wavefront

Drawbacks vs DFS:
    Slightly more code. Stack overflow risk with DFS is only a concern for grids
    approaching 300 * 300 = 90000 depth — typically fine within LC constraints.
*/

/*
Method to Solve:
----------------
1. Iterate every cell (i, j).
2. If grid[i][j] == '1': increment island count, then call dfs(i, j).
3. DFS: mark current cell as '2' (visited), then recurse into all 4 neighbours
   that are within bounds and still '1'.
4. Return final count after full grid scan.
*/

// Time Complexity: O(m * n)
// Space Complexity: O(m * n)

public class LC200NumberOfIslands {

    private final int[] di = { 0, 0, 1, -1 };
    private final int[] dj = { 1, -1, 0, 0 };

    /**
     * Checks whether (i, j) is a valid grid position.
     *
     * @param i row index
     * @param j column index
     * @param m total rows
     * @param n total columns
     * @return true if within grid bounds
     */
    private boolean withinBound(int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    /**
     * DFS flood-fill from (i, j), sinking all connected land cells by marking them
     * '2'.
     *
     * @param i    starting row
     * @param j    starting column
     * @param grid the character grid being traversed
     */
    private void dfs(int i, int j, char[][] grid) {
        grid[i][j] = '2'; // mark visited

        for (int k = 0; k < 4; k++) {
            int nI = i + di[k];
            int nJ = j + dj[k];
            if (withinBound(nI, nJ, grid.length, grid[0].length) && grid[nI][nJ] == '1') {
                dfs(nI, nJ, grid);
            }
        }
    }

    /**
     * Counts the number of distinct islands in the grid.
     * An island is a group of '1's connected horizontally or vertically.
     *
     * @param grid 2D character grid of '0's and '1's
     * @return number of islands
     */
    public int numIslands(char[][] grid) {
        int cnt = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    dfs(i, j, grid); // sink entire island
                }
            }
        }

        return cnt;
    }
}

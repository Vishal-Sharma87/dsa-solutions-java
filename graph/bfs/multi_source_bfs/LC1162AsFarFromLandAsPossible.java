package graph.bfs.multi_source_bfs;

// Created at: 19-November-2026
// Last revised at: 19-November-2026
// Link: https://leetcode.com/problems/as-far-from-land-as-possible/

/*
Problem Description:
--------------------
Statement:
Given an n x n grid containing 0s (water) and 1s (land),
find a water cell such that its distance to the nearest
land cell is maximized.

Return that maximum distance.

Distance is measured using Manhattan distance:
|r1 - r2| + |c1 - c2|

If the grid contains only land or only water, return -1.

Example 1:
Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2

Example 2:
Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4

Constraints:
1 <= n <= 100
grid[i][j] is either 0 or 1.
*/

/*
Approach 1: Brute Force

Idea:
For every water cell, calculate its distance from every
land cell and find the minimum distance.

Track the maximum of these minimum distances.

Time Complexity: O(N^4)
Space Complexity: O(1)

Drawbacks:
Repeatedly calculates distances between water and land cells.
Does not utilize the fact that multiple sources can be
processed simultaneously.
*/

/*
Approach 2: Multi-Source BFS (Optimal)

Idea:
Instead of finding the nearest land cell separately for
every water cell, start BFS simultaneously from all land cells.

1. Add every land cell to the initial queue.
2. Process all cells level by level.
3. Expand into adjacent unvisited water cells.
4. Mark water cells as visited when enqueuing them.
5. Each BFS level represents the distance from the nearest land.
6. The last reachable BFS level gives the maximum distance.

Time Complexity: O(N^2)
Space Complexity: O(N^2)

Key Insight:
Multi-Source BFS guarantees that a cell is visited first
through its nearest source.

Since all land cells are initial sources, the BFS level
at which a water cell is discovered represents its minimum
distance from any land cell.

Drawbacks:
The input grid is modified to track visited cells.
*/

/*
Method to Solve:
----------------
1. Traverse the grid and enqueue all land coordinates.
2. If the grid contains only land or only water, return -1.
3. Initialize distance = 0.
4. Process the queue level by level.
5. For every cell, explore its four adjacent directions.
6. Enqueue valid water cells and mark them as visited.
7. Increment distance only if another BFS level exists.
8. Return the maximum distance.
*/

import java.util.Deque;
import java.util.LinkedList;

class LC1162AsFarFromLandAsPossible {

    private record Coordinates(int row, int col) {
    }

    private static final int[] DR = { 0, 0, 1, -1 };
    private static final int[] DC = { 1, -1, 0, 0 };

    /**
     * Checks whether a coordinate lies within the grid.
     *
     * @param row current row
     * @param col current column
     * @param n   grid dimension
     * @return true if the coordinate is valid
     */
    private boolean withinBounds(int row, int col, int n) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    /**
     * Finds the maximum distance of a water cell from its nearest land.
     *
     * @param grid square grid containing land (1) and water (0)
     * @return maximum distance, or -1 if no valid water-land combination exists
     */
    public int maxDistance(int[][] grid) {

        int n = grid.length;

        Deque<Coordinates> queue = new LinkedList<>();

        // Initialize BFS with all land cells
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) {
                    queue.offer(new Coordinates(row, col));
                }
            }
        }

        // All land or all water
        if (queue.isEmpty() || queue.size() == n * n) {
            return -1;
        }

        int distance = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                Coordinates current = queue.poll();

                for (int k = 0; k < 4; k++) {

                    int nextRow = current.row() + DR[k];
                    int nextCol = current.col() + DC[k];

                    if (withinBounds(nextRow, nextCol, n)
                            && grid[nextRow][nextCol] == 0) {

                        queue.offer(new Coordinates(nextRow, nextCol));

                        // Mark visited
                        grid[nextRow][nextCol] = 1;
                    }
                }
            }

            if (!queue.isEmpty()) {
                distance++;
            }
        }

        return distance;
    }
}

// Time Complexity: O(N^2)
// Space Complexity: O(N^2)
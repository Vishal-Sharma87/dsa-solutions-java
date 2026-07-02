package graph.shortestpath;

// Created at: 03-July-2026
// Last revised at: 03-July-2026
// Link: https://leetcode.com/problems/find-a-safe-walk-through-a-grid/

/*
Problem Description:
--------------------
Statement:
You are given an m x n grid where each cell is either safe (0) or unsafe (1).
Moving onto an unsafe cell decreases your health by 1.
You start from the top-left cell with a given amount of health and may move
in four directions (up, down, left, right).

Return true if it is possible to reach the bottom-right cell while keeping
the remaining health strictly greater than 0.

Example:
Input:
grid = [[0,1],[0,0]]
health = 2

Output:
true

Constraints:
- 1 <= m, n <= 50
- grid[i][j] is either 0 or 1
- 1 <= health <= 10^9
*/

/*
Approach 1: BFS with Health State

Idea:
Treat every cell along with its remaining health as a state.
Whenever a better health value reaches a cell, explore it again.

Time Complexity:
O(m * n * log(m * n))

Space Complexity:
O(m * n)

Drawbacks:
The same cell can be inserted multiple times before weaker states are discarded.
*/

/*
Approach 2: Dijkstra-style Best Remaining Health (Optimal)

Idea:
Always process the path having the maximum remaining health first using a
max-heap. Maintain the best remaining health for every cell.
Ignore any state that is worse than the already discovered one.

Time Complexity:
O(m * n * log(m * n))

Space Complexity:
O(m * n)

Key Insight:
Instead of minimizing cost, maximize the remaining health. Once a cell has
already been reached with greater health, every weaker arrival can be skipped.
*/

/*
Method to Solve:
----------------
1. Deduct health if the starting cell is unsafe.
2. Store the best remaining health for every cell.
3. Push the starting state into a max-heap.
4. Always expand the state with maximum remaining health.
5. Update neighbours only if they can be reached with better health.
6. Return true once the destination is reached with health greater than zero.
*/

import java.util.*;

public class LC3286FindASafeWalkThroughAGrid {

    /**
     * Stores a grid state.
     */
    private static class Info {
        int row;
        int col;
        int health;

        Info(int row, int col, int health) {
            this.row = row;
            this.col = col;
            this.health = health;
        }
    }

    private static final int[] DR = { 0, 0, 1, -1 };
    private static final int[] DC = { 1, -1, 0, 0 };

    /**
     * Checks whether a cell lies inside the grid.
     *
     * @param row  current row
     * @param col  current column
     * @param rows total rows
     * @param cols total columns
     * @return true if valid
     */
    private boolean withinBound(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Determines whether a safe walk exists.
     *
     * @param grid   input grid
     * @param health initial health
     * @return true if destination can be reached safely
     */
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int rows = grid.size();
        int cols = grid.get(0).size();

        // starting cell damage
        if (grid.get(0).get(0) == 1) {
            health--;
        }

        int[][] bestHealth = new int[rows][cols];
        Arrays.stream(bestHealth).forEach(row -> Arrays.fill(row, -1));

        PriorityQueue<Info> maxHeap = new PriorityQueue<>((a, b) -> b.health - a.health);

        maxHeap.offer(new Info(0, 0, health));
        bestHealth[0][0] = health;

        while (!maxHeap.isEmpty()) {

            Info current = maxHeap.poll();

            // destination reached
            if (current.row == rows - 1 && current.col == cols - 1) {
                return current.health > 0;
            }

            // skip outdated state
            if (bestHealth[current.row][current.col] > current.health) {
                continue;
            }

            for (int direction = 0; direction < 4; direction++) {

                int nextRow = current.row + DR[direction];
                int nextCol = current.col + DC[direction];

                if (!withinBound(nextRow, nextCol, rows, cols)) {
                    continue;
                }

                int remainingHealth = grid.get(nextRow).get(nextCol) == 1
                        ? current.health - 1
                        : current.health;

                // found a better path
                if (bestHealth[nextRow][nextCol] < remainingHealth) {
                    bestHealth[nextRow][nextCol] = remainingHealth;
                    maxHeap.offer(new Info(nextRow, nextCol, remainingHealth));
                }
            }
        }

        return false;
    }
}

// Time Complexity: O(m * n * log(m * n))
// Space Complexity: O(m * n)
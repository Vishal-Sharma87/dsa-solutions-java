package graph.topologicalsort;

// Created at: 06-July-2026
// Last revised at: 06-July-2026
// Link: https://leetcode.com/problems/number-of-paths-with-max-score/

/*
Problem Description:
--------------------

Statement:
You are given an n x n board containing:

* 'E' : starting cell
* 'S' : destination cell
* 'X' : blocked cell
* '1' to '9' : score of the cell

Starting from 'S', move only Up, Left, or Up-Left until reaching 'E'.
Return:

1. Maximum score obtainable.
2. Number of paths producing that maximum score modulo 1e9+7.

If no valid path exists, return {0, 0}.

Example:
Input:
["E23",
"2X2",
"12S"]

Output:
[7,1]

Constraints:

* 2 <= n <= 100
* Board is square.
  */

/*
Approach 1: DFS + Memoization

Idea:
Explore every possible path recursively while memoizing states.

Time Complexity:
O(n²)

Space Complexity:
O(n²)

Drawbacks:
Managing both maximum score and number of optimal paths makes recursion more involved.
*/

/*
Approach 2: Topological DP (Implemented)

Idea:
Treat every valid move as a directed edge from the current cell to one of its predecessors
(Up, Left, Up-Left). Since every move strictly decreases row and/or column, the graph is a DAG.

1. Build indegree for every reachable cell.
2. Perform topological traversal beginning from 'S'.
3. Maintain:

   * cost[r][c] = maximum score reaching the cell.
   * ways[r][c] = number of maximum-score paths.
4. Whenever a better score is found, replace both score and path count.
5. Whenever an equal score is found, add the path counts.

Time Complexity:
O(n²)

Space Complexity:
O(n²)

Key Insight:
Topological order guarantees every predecessor is processed before the current node, allowing
dynamic programming without recursion.
*/

/*
Method to Solve:
----------------

1. Build indegree of every reachable node in the DAG.
2. Start topological traversal from 'S'.
3. Relax all outgoing transitions.
4. Update maximum score and path count.
5. Return answer when 'E' is processed.
   */

class LC1301NumberOfPathsWithMaxScore {

    private static final int MOD = 1_000_000_007;

    private static class State {
        int row;
        int col;
        int score;

        State(int row, int col, int score) {
            this.row = row;
            this.col = col;
            this.score = score;
        }
    }

    private static class Cell {
        int row;
        int col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private final int[] dr = { -1, -1, 0 };
    private final int[] dc = { 0, -1, -1 };

    /**
     * Checks whether a cell lies inside the board.
     *
     * @param row  row index
     * @param col  column index
     * @param rows total rows
     * @param cols total columns
     * @return true if valid
     */
    private boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Returns maximum obtainable score and number of optimal paths.
     *
     * @param board game board
     * @return {maximumScore, numberOfPaths}
     */
    public int[] pathsWithMaxScore(java.util.List<String> board) {

        int rows = board.size();
        int cols = board.get(0).length();

        int[][] indegree = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];

        java.util.Deque<Cell> queue = new java.util.LinkedList<>();
        queue.offer(new Cell(rows - 1, cols - 1));

        // Build indegree graph
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();

            for (int k = 0; k < 3; k++) {
                int nr = cell.row + dr[k];
                int nc = cell.col + dc[k];

                if (!isValid(nr, nc, rows, cols)) {
                    continue;
                }

                if (board.get(nr).charAt(nc) == 'X') {
                    continue;
                }

                indegree[nr][nc]++;

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new Cell(nr, nc));
                }
            }
        }

        int[][] bestScore = new int[rows][cols];
        int[][] ways = new int[rows][cols];

        java.util.Deque<State> topo = new java.util.LinkedList<>();

        topo.offer(new State(rows - 1, cols - 1, 0));
        ways[rows - 1][cols - 1] = 1;

        while (!topo.isEmpty()) {

            State current = topo.poll();

            if (current.row == 0 && current.col == 0) {
                return new int[] { bestScore[0][0], ways[0][0] };
            }

            if (bestScore[current.row][current.col] > current.score) {
                continue;
            }

            for (int k = 0; k < 3; k++) {

                int nr = current.row + dr[k];
                int nc = current.col + dc[k];

                if (!isValid(nr, nc, rows, cols)) {
                    continue;
                }

                if (board.get(nr).charAt(nc) == 'X') {
                    continue;
                }

                int nextScore = current.score;

                if (board.get(nr).charAt(nc) != 'E') {
                    nextScore += board.get(nr).charAt(nc) - '0';
                }

                if (nextScore == bestScore[nr][nc]) {
                    ways[nr][nc] = (ways[nr][nc] + ways[current.row][current.col]) % MOD;
                } else if (nextScore > bestScore[nr][nc]) {
                    bestScore[nr][nc] = nextScore;
                    ways[nr][nc] = ways[current.row][current.col];
                }

                indegree[nr][nc]--;

                if (indegree[nr][nc] == 0) {
                    topo.offer(new State(nr, nc, bestScore[nr][nc]));
                }
            }
        }

        return new int[] { 0, 0 };
    }

}

// Time Complexity: O(n²)
// Space Complexity: O(n²)

package graph.dfs;

// Created at: 20-May-2026
// Last revised at: 20-May-2026
// Link: https://leetcode.com/problems/number-of-provinces/

/*
Problem Description:
--------------------
Statement:
There are n cities. Some of them are connected, and some are not. If city a is
connected directly to city b, and city b is connected directly to city c, then
city a is connected to city c indirectly. A province is a group of directly or
indirectly connected cities with no other cities outside the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the
i-th city and the j-th city are directly connected, and isConnected[i][j] = 0
otherwise. Return the total number of provinces.

Example:
Input:  isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

Input:  isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3

Constraints:
- 1 <= n <= 200
- n == isConnected.length == isConnected[i].length
- isConnected[i][j] is 1 or 0
- isConnected[i][i] == 1
- isConnected[i][j] == isConnected[j][i]
*/

/*
Approach 1: DFS — Connected Component Count ★
---------------------------------------------
Idea:
Model cities as graph nodes with the adjacency matrix as edges. Run DFS from
each unvisited node, marking all reachable nodes as visited. Each DFS call
from the outer loop covers exactly one province.

Time Complexity:  O(n²) — every cell in the adjacency matrix is visited once.
Space Complexity: O(n)  — visited array + recursion stack depth up to n.

Key Insight:
Counting connected components in an undirected graph directly maps to counting
provinces. The adjacency matrix makes neighbor iteration O(n) per node, so the
total traversal is O(n²).
*/

/*
Method to Solve:
----------------
1. Initialize a visited[] array of size n (number of cities).
2. Iterate over each node 0..n-1.
3. If the node is unvisited, trigger DFS — this marks the entire component.
4. Increment province count after each DFS call.
5. Inside DFS, iterate all neighbors; recurse on unvisited neighbors where
   isConnected[node][nbr] == 1 and nbr != node.
6. Return the province count.
*/

// Time Complexity: O(n²)
// Space Complexity: O(n)

public class LC547NumberOfProvinces {

    /**
     * Performs DFS from the given node, marking all reachable cities as visited.
     *
     * @param node        current city being visited
     * @param isConnected adjacency matrix representing city connections
     * @param visited     tracks which cities have been visited
     */
    private void dfs(int node, int[][] isConnected, boolean[] visited) {
        visited[node] = true;

        for (int nbr = 0; nbr < isConnected.length; nbr++) {
            // skip self and already-visited neighbors
            if (nbr != node && isConnected[node][nbr] == 1 && !visited[nbr]) {
                dfs(nbr, isConnected, visited);
            }
        }
    }

    /**
     * Counts the number of provinces (connected components) in the city graph.
     *
     * @param isConnected n x n adjacency matrix where isConnected[i][j] = 1
     *                    means city i and city j are directly connected
     * @return total number of provinces
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        if (n <= 1)
            return n;

        boolean[] visited = new boolean[n];
        int provincesCnt = 0;

        for (int node = 0; node < n; node++) {
            if (!visited[node]) {
                dfs(node, isConnected, visited);
                provincesCnt++;
            }
        }

        return provincesCnt;
    }
}
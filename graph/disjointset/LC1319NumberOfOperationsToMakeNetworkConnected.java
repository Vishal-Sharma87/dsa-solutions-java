package graph.disjointset;

// Created at: 01-June-2025
// Last revised at: 01-June-2025
// Link: https://leetcode.com/problems/number-of-operations-to-make-network-connected/

import java.util.*;

/*
Problem Description:
--------------------
Statement:
There are n computers numbered 0 to n-1 connected by ethernet cables. You can extract
a cable between two directly connected computers and use it to connect two unconnected computers.
Return the minimum number of operations to connect all computers, or -1 if it's not possible.

Example:
Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove the extra cable between 0-1-2 triangle, use it to connect computer 3.

Constraints:
- 1 <= n <= 10^5
- 1 <= connections.length <= min(n*(n-1)/2, 10^5)
- connections[i].length == 2
- 0 <= connections[i][0], connections[i][1] < n
- No repeated connections, no self-loops.
*/

/*
Approach 1: Brute Force (BFS/DFS component count)
Idea:
Build adjacency list, run BFS/DFS to count connected components.
Answer = components - 1, if cables >= n - 1.

Time Complexity: O(n + e)
Space Complexity: O(n + e)

Drawbacks:
Extra overhead of building adjacency list. DSU is cleaner and faster in practice.

★ Approach 2: DSU (Optimal)
Idea:
To connect k components, we need exactly k-1 cables. We need at least n-1 cables total
to connect n computers — if we have fewer, return -1 immediately.
Otherwise, union all connections via DSU, count remaining components (roots),
and return components - 1.

Time Complexity: O(e * α(n)) ≈ O(e)
Space Complexity: O(n)

Key Insight:
We don't care about redundant cables explicitly — any graph with n nodes and
at least n-1 edges that has k components can always be reconnected in k-1 moves,
because redundant edges inside components free up cables for cross-component wiring.
*/

/*
Method to Solve:
----------------
1. If connections.length < n - 1, return -1 (not enough cables).
2. Init DSU with n nodes.
3. Union every connection pair.
4. Count roots in parent array (par == -1) → these are connected components.
5. Return groupCount - 1.
*/

// Time Complexity: O(e * α(n)) ≈ O(e)
// Space Complexity: O(n)

public class LC1319NumberOfOperationsToMakeNetworkConnected {

    private class DSU {
        int[] parent;
        int[] size;

        /**
         * Initialises DSU with n nodes, each as its own root.
         *
         * @param nodes total number of computers
         */
        public DSU(int nodes) {
            parent = new int[nodes];
            size = new int[nodes];
            Arrays.fill(parent, -1);
            Arrays.fill(size, 1);
        }

        /**
         * Finds root of node with path compression.
         *
         * @param node the node to find root for
         * @return root of the component
         */
        public int findParent(int node) {
            if (parent[node] == -1)
                return node;
            return parent[node] = findParent(parent[node]);
        }

        /**
         * Unions two nodes by size.
         *
         * @param a first node
         * @param b second node
         */
        public void unionBySize(int a, int b) {
            int parA = findParent(a);
            int parB = findParent(b);

            if (parA == parB)
                return;

            if (size[parA] > size[parB]) {
                parent[parB] = parA;
                size[parA] += size[parB];
            } else {
                parent[parA] = parB;
                size[parB] += size[parA];
            }
        }
    }

    /**
     * Returns minimum cable moves to connect all computers, or -1 if impossible.
     *
     * @param n           total number of computers
     * @param connections array of existing cable connections
     * @return minimum operations or -1
     */
    public int makeConnected(int n, int[][] connections) {
        // need at least n-1 cables to connect n nodes
        if (connections.length < n - 1)
            return -1;

        DSU dsu = new DSU(n);

        for (int[] cable : connections) {
            dsu.unionBySize(cable[0], cable[1]);
        }

        // count components
        int groupCount = 0;
        for (int par : dsu.parent) {
            if (par == -1)
                groupCount++;
        }

        return groupCount - 1;
    }
}

package graph.shortestpath;

// Created at: 29-May-2026
// Last revised at: 29-May-2026
// Link: https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/

/*
Problem Description:
--------------------
Statement:
You are given an undirected weighted graph with n intersections numbered from 0 to n - 1.

Each road is represented as:
roads[i] = [u, v, time]

You need to find:
- the number of different shortest paths
from node 0 to node n - 1.

Return the total number of shortest paths modulo 10^9 + 7.

Example:
Input:
n = 7
roads = [
    [0,6,7],
    [0,1,2],
    [1,2,3],
    [1,3,3],
    [6,3,3],
    [3,5,1],
    [6,5,1],
    [2,5,1],
    [0,4,5],
    [4,6,2]
]

Output:
4

Explanation:
There are 4 shortest paths from node 0 to node 6.

Constraints:
- 1 <= n <= 200
- roads[i].length == 3
- 0 <= ui, vi < n
- 1 <= timei <= 10^9
*/

/*
Approach 1: Dijkstra with Path Counting
Idea:
- Use Dijkstra's algorithm to compute shortest distance.
- Maintain:
  1. shortest cost to each node
  2. number of shortest paths to each node
- When a shorter path is found:
  - update cost
  - copy path count
- When another shortest path is found:
  - add path counts

Time Complexity:
O((V + E) log V)

Space Complexity:
O(V + E)

Key Insight:
Dijkstra guarantees the first minimum distance discovered is optimal.
We can simultaneously track how many ways achieve that distance.
*/

/*
Method to Solve:
----------------
1. Build adjacency list for undirected graph.
2. Initialize:
   - costs[] for shortest distance
   - ways[] for number of shortest paths
3. Start Dijkstra traversal from node 0.
4. For every neighbor:
   - relax shorter path
   - accumulate equal shortest paths
5. Return ways[n - 1].
*/

import java.util.*;

public class LC1976NumberOfWaysToArriveAtDestination {

    /**
     * Stores current node and path cost.
     */
    private static class Info {

        int node;
        int costUsed;

        public Info(int node, int costUsed) {
            this.node = node;
            this.costUsed = costUsed;
        }
    }

    /**
     * Stores adjacent node and edge cost.
     */
    private static class EdgeInfo {

        int node;
        int cost;

        public EdgeInfo(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    /**
     * Counts total shortest paths from node 0
     * to node n - 1.
     *
     * @param n     total nodes
     * @param roads graph edges
     * @return number of shortest paths
     */
    public int countPaths(int n, int[][] roads) {

        Map<Integer, List<EdgeInfo>> adjList = buildGraph(roads);

        int[] costs = initializeCosts(n);

        int[] ways = new int[n];

        PriorityQueue<Info> minHeap = new PriorityQueue<>((x, y) -> x.costUsed - y.costUsed);

        int mod = 1000000007;

        // start from source
        minHeap.offer(new Info(0, 0));

        costs[0] = 0;
        ways[0] = 1;

        List<EdgeInfo> emptyList = new ArrayList<>();

        while (!minHeap.isEmpty()) {

            Info info = minHeap.poll();

            // skip outdated state
            if (info.costUsed > costs[info.node]) {
                continue;
            }

            for (EdgeInfo edge : adjList.getOrDefault(info.node, emptyList)) {

                int newCost = info.costUsed + edge.cost;

                // found shorter path
                if (costs[edge.node] > newCost) {

                    costs[edge.node] = newCost;

                    // inherit ways from parent
                    ways[edge.node] = ways[info.node];

                    minHeap.offer(
                            new Info(edge.node, newCost));
                }

                // found another shortest path
                else if (costs[edge.node] == newCost) {

                    int totalWays = (ways[edge.node] + ways[info.node]) % mod;

                    ways[edge.node] = totalWays;
                }
            }
        }

        return ways[n - 1];
    }

    /**
     * Builds adjacency list for graph.
     *
     * @param roads graph edges
     * @return adjacency list
     */
    private Map<Integer, List<EdgeInfo>> buildGraph(int[][] roads) {

        Map<Integer, List<EdgeInfo>> adjList = new HashMap<>();

        for (int[] edge : roads) {

            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            // u ↔ v
            adjList
                    .computeIfAbsent(u, list -> new ArrayList<>())
                    .add(new EdgeInfo(v, cost));

            adjList
                    .computeIfAbsent(v, list -> new ArrayList<>())
                    .add(new EdgeInfo(u, cost));
        }

        return adjList;
    }

    /**
     * Initializes shortest distance array.
     *
     * @param n total nodes
     * @return initialized distance array
     */
    private int[] initializeCosts(int n) {

        int[] costs = new int[n];

        Arrays.fill(costs, Integer.MAX_VALUE);

        return costs;
    }
}

// Time Complexity: O((V + E) log V)
// Space Complexity: O(V + E)

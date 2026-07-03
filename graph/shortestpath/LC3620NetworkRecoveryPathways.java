
package graph.shortestpath;

// Created at: 04-July-2026
// Last revised at: 04-July-2026
// Link: https://leetcode.com/problems/network-recovery-pathways/

/*
Problem Description:
--------------------
Statement:
You are given a directed acyclic graph where each edge has a recovery cost.
Some nodes are offline except the source and destination.

A path from node 0 to node n - 1 is valid if:
1. Every intermediate node is online.
2. The total recovery cost of the path does not exceed k.

The score of a path is defined as the minimum edge cost present on that path.

Return the maximum possible score among all valid paths.
If no valid path exists, return -1.

Example:
Input:
edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]]
online = [true,true,true,true]
k = 10

Output:
3

Constraints:
- 2 <= n <= 5 * 10^4
- 0 <= m <= 10^5
- 0 <= cost <= 10^9
- 0 <= k <= 5 * 10^13
- The graph is a Directed Acyclic Graph.
*/

/*
Approach 1: DFS / BFS Enumeration

Idea:
Enumerate every possible path from source to destination,
calculate its total cost and minimum edge cost,
and keep the best valid answer.

Time Complexity:
Exponential

Space Complexity:
O(n)

Drawbacks:
Impossible for the given constraints because the number of paths in a DAG
can be exponential.
*/

/*
Approach 2: Binary Search + Dijkstra (Optimal)

Idea:
Suppose every edge on the chosen path must have weight at least X.

If a valid path exists for X,
then every smaller threshold is also feasible.

This monotonic property allows binary searching on the answer.

For every candidate threshold:
- Ignore edges having weight smaller than the threshold.
- Ignore offline intermediate nodes.
- Run Dijkstra to compute the minimum total recovery cost.
- If the destination is reachable within cost k,
  the threshold is feasible.

Time Complexity:
O(E log E + log(E) × (V + E) log V)

Space Complexity:
O(V + E)

Key Insight:
Instead of directly maximizing the minimum edge,
binary search the minimum allowed edge weight and verify feasibility
using a shortest path algorithm.
*/

/*
Method to Solve:
----------------
1. Build the adjacency list.
2. Collect all distinct edge weights.
3. Sort the weights.
4. Binary search on the candidate minimum edge weight.
5. During each iteration:
   - Ignore edges having smaller weight.
   - Skip offline intermediate nodes.
   - Run Dijkstra to find the minimum path cost.
6. If the destination is reachable within k,
   search for a larger threshold.
7. Return the maximum feasible threshold.
*/

import java.util.*;

public class LC3620NetworkRecoveryPathways {

    /**
     * Represents an outgoing edge.
     */
    private static class EdgeDetail {

        int node;
        int cost;

        EdgeDetail(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    /**
     * Represents a Dijkstra state.
     */
    private static class Info {

        int node;
        long cost;

        Info(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    /**
     * Determines whether a valid path exists using only edges
     * whose weight is at least the given threshold.
     *
     * @param graph   adjacency list
     * @param online  node availability
     * @param minEdge minimum allowed edge weight
     * @param limit   maximum allowed recovery cost
     * @return true if a valid path exists
     */
    private boolean canReach(Map<Integer, List<EdgeDetail>> graph,
            boolean[] online,
            int minEdge,
            long limit) {

        int nodes = online.length;

        PriorityQueue<Info> minHeap = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));

        long[] best = new long[nodes];
        Arrays.fill(best, Long.MAX_VALUE);

        minHeap.offer(new Info(0, 0));
        best[0] = 0;

        List<EdgeDetail> empty = Collections.emptyList();

        while (!minHeap.isEmpty()) {

            Info current = minHeap.poll();

            if (current.node == nodes - 1) {
                return current.cost <= limit;
            }

            // skip outdated state
            if (best[current.node] < current.cost) {
                continue;
            }

            for (EdgeDetail edge : graph.getOrDefault(current.node, empty)) {

                // offline intermediate node
                if (!online[edge.node] && edge.node != nodes - 1) {
                    continue;
                }

                // edge does not satisfy threshold
                if (edge.cost < minEdge) {
                    continue;
                }

                long nextCost = current.cost + edge.cost;

                // exceeds allowed recovery cost
                if (nextCost > limit) {
                    continue;
                }

                if (nextCost < best[edge.node]) {

                    best[edge.node] = nextCost;
                    minHeap.offer(new Info(edge.node, nextCost));
                }
            }
        }

        return false;
    }

    /**
     * Finds the maximum possible path score.
     *
     * @param edges  graph edges
     * @param online node availability
     * @param k      maximum recovery cost
     * @return maximum path score
     */
    public int findMaxPathScore(int[][] edges,
            boolean[] online,
            long k) {

        Map<Integer, List<EdgeDetail>> graph = new HashMap<>();
        TreeSet<Integer> weights = new TreeSet<>();

        for (int[] edge : edges) {

            graph.computeIfAbsent(edge[0], key -> new ArrayList<>())
                    .add(new EdgeDetail(edge[1], edge[2]));

            weights.add(edge[2]);
        }

        List<Integer> values = new ArrayList<>(weights);

        int left = 0;
        int right = values.size() - 1;

        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;
            int threshold = values.get(mid);

            if (canReach(graph, online, threshold, k)) {

                answer = threshold;
                left = mid + 1;

            } else {

                right = mid - 1;
            }
        }

        return answer;
    }
}

// Time Complexity: O(E log E + log(E) × (V + E) log V)
// Space Complexity: O(V + E)
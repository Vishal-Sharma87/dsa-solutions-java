package graph.shortestpath;

// Created at: 29-May-2026
// Last revised at: 29-May-2026
// Link: https://leetcode.com/problems/cheapest-flights-within-k-stops/

/*
Problem Description:
--------------------
Statement:
There are n cities connected by directed flights where flights[i] = [from, to, price].

You are given:
- source city src
- destination city dst
- maximum allowed stops k

Return the cheapest price from src to dst with at most k stops.
If no such route exists, return -1.

Example:
Input:
n = 4
flights = [[0,1,100],[1,2,100],[2,3,100],[0,3,500]]
src = 0
dst = 3
k = 1

Output:
500

Explanation:
0 → 3 directly costs 500.
0 → 1 → 2 → 3 requires 2 stops, which exceeds k.

Constraints:
- 1 <= n <= 100
- 0 <= flights.length <= (n * (n - 1)) / 2
- 0 <= src, dst < n
- src != dst
*/

/*
Approach 1: BFS with Cost Relaxation
Idea:
- Build an adjacency list from flights.
- Use BFS traversal where each queue state stores:
  1. current node
  2. current cost
  3. number of stops used
- Only continue traversal if stops are within limit.
- Relax edges similar to Bellman-Ford whenever a cheaper path is found.

Time Complexity:
O(E + V + E * K)

Space Complexity:
O(E + V)

Key Insight:
Since we only care about paths with at most k stops,
BFS level traversal naturally fits the problem.
*/

/*
Method to Solve:
----------------
1. Build adjacency list from flight data.
2. Maintain minimum cost array for each city.
3. Push source node into queue with:
   - cost = 0
   - stops = 0
4. Process queue:
   - skip states exceeding k stops
   - try all outgoing flights
   - relax cheaper paths
5. Return cheapest cost for destination.
*/

import java.util.*;

public class LC787CheapestFlightsWithinKStops {

    /**
     * Stores BFS traversal state.
     */
    private static class Info {
        int node;
        int cost;
        int stops;

        public Info(int node, int cost, int stops) {
            this.node = node;
            this.cost = cost;
            this.stops = stops;
        }
    }

    /**
     * Stores adjacent node and flight cost.
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
     * Finds the cheapest flight cost within at most k stops.
     *
     * @param n       total number of cities
     * @param flights flight connections
     * @param src     source city
     * @param dst     destination city
     * @param k       maximum allowed stops
     * @return minimum cost to reach destination
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        Map<Integer, List<EdgeInfo>> adjList = buildGraph(flights);

        int[] costs = initializeCosts(n, src);

        Deque<Info> queue = new LinkedList<>();
        queue.offer(new Info(src, 0, 0));

        List<EdgeInfo> emptyList = new ArrayList<>();

        while (!queue.isEmpty()) {

            Info top = queue.poll();

            int node = top.node;
            int nodeCost = top.cost;
            int nodeStops = top.stops;

            // skip paths exceeding stop limit
            if (nodeStops > k) {
                continue;
            }

            for (EdgeInfo edge : adjList.getOrDefault(node, emptyList)) {

                int nbr = edge.node;
                int edgeCost = edge.cost;

                int newCost = nodeCost + edgeCost;

                // relax cheaper path
                if (costs[nbr] > newCost) {
                    costs[nbr] = newCost;

                    queue.offer(
                            new Info(nbr, newCost, nodeStops + 1));
                }
            }
        }

        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }

    /**
     * Builds adjacency list representation of graph.
     *
     * @param flights flight connections
     * @return adjacency list
     */
    private Map<Integer, List<EdgeInfo>> buildGraph(int[][] flights) {

        Map<Integer, List<EdgeInfo>> adjList = new LinkedHashMap<>();

        for (int[] edge : flights) {

            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            // u → v
            adjList
                    .computeIfAbsent(u, list -> new ArrayList<>())
                    .add(new EdgeInfo(v, cost));
        }

        return adjList;
    }

    /**
     * Initializes minimum cost array.
     *
     * @param n   total cities
     * @param src source city
     * @return initialized cost array
     */
    private int[] initializeCosts(int n, int src) {

        int[] costs = new int[n];

        Arrays.fill(costs, Integer.MAX_VALUE);

        costs[src] = 0;

        return costs;
    }
}

// Time Complexity: O(E + V + E * K)
// Space Complexity: O(E + V)

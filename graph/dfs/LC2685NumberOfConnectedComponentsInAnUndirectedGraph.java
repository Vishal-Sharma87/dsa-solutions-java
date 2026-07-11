package graph.dfs;

// Created at: 12-July-2026
// Last revised at: 12-July-2026
// Link: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/

import java.util.*;

import graph.disjointset.DisjointSet;

/*
Problem Description:
--------------------
Statement:
You are given an integer n. There are n nodes labeled from 0 to n - 1.
You are given a 2D integer array edges where edges[i] = [ai, bi] denotes
that there exists an undirected edge connecting nodes ai and bi.

Return the number of complete connected components of the graph.
A connected component is complete if there exists an edge between every
pair of its nodes.

Example:
n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
Output: 2
Explanation: {0,1,2} is complete (triangle), {3,4} is complete (single edge),
{5} is complete (isolated node).

Constraints:
1 <= n <= 50
0 <= edges.length <= n * (n - 1) / 2
edges[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
No repeated edges.
*/

/*
Approach: DFS validation with DSU-assisted component size

Idea:
A component with size k is complete only if every node inside it has
degree exactly k - 1 (connected to all other nodes in the component).
DSU is used purely to precompute each component's size in O(1) per query
(after path compression). DFS then walks each unvisited component and
validates every node's degree against that required "must" value.

Key insight:
Marking a node visited only means it has been reached — it says nothing
about whether it passed its own degree check. So the degree check must be
applied to every neighbor regardless of visited state, not just the ones
we're about to recurse into.

Time Complexity: O(n + e) — each node/edge processed once, DSU operations
near O(1) due to path compression.

Space Complexity: O(n + e) — adjacency list, DSU arrays, visited array,
recursion stack.
*/

public class LC2685NumberOfConnectedComponentsInAnUndirectedGraph {

    private static final List<Integer> EMPTY = Collections.emptyList();

    /**
     * Counts the number of complete connected components in the graph.
     *
     * @param n     number of nodes
     * @param edges list of undirected edges
     * @return count of complete connected components
     */
    public int countCompleteComponents(int n, int[][] edges) {
        DisjointSet dsu = new DisjointSet(n);
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int[] e : edges) {
            dsu.unionBySize(e[0], e[1]);
            adj.computeIfAbsent(e[0], k -> new ArrayList<>()).add(e[1]);
            adj.computeIfAbsent(e[1], k -> new ArrayList<>()).add(e[0]);
        }

        boolean[] vis = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i] && isCompleteComponent(adj, i, dsu.getSize(dsu.find(i)) - 1, vis)) {
                count++;
            }
        }

        return count;
    }

    /*
     * Method to Solve:
     * ----------------
     * 1. Union all edges via DSU to determine each component's size.
     * 2. For each unvisited node, run DFS with must = componentSize - 1
     * (required degree for completeness).
     * 3. Inside DFS: mark node visited, fail fast if its own degree != must.
     * 4. For every neighbor — whether visited or not — check its degree
     * against must. Recurse only into unvisited neighbors.
     * 5. Component is complete only if every node in it satisfies the degree check.
     */
    private boolean isCompleteComponent(Map<Integer, List<Integer>> adj, int node, int must, boolean[] vis) {
        vis[node] = true;

        if (adj.getOrDefault(node, EMPTY).size() != must) {
            return false;
        }

        for (int nbr : adj.getOrDefault(node, EMPTY)) {
            if (!vis[nbr]) {
                // recurse first, degree of nbr gets checked as soon as its own dfs starts
                if (!isCompleteComponent(adj, nbr, must, vis))
                    return false;
            } else if (adj.getOrDefault(nbr, EMPTY).size() != must) {
                // nbr already visited elsewhere, so its degree was never checked — check it
                // here
                return false;
            }
        }

        return true;
    }
}
package graph.disjointset;

import java.util.Arrays;
// Created at: 31-May-2026
// Last revised at: 31-May-2026
// Link: DSA Utility - Disjoint Set Union (Union Find)

/*
Problem Description:
--------------------
Statement:
Disjoint Set Union (DSU), also known as Union Find, is a data structure
used to efficiently maintain a collection of disjoint sets.

It supports two primary operations:

1. Find:
   Determine the representative (ultimate parent) of a set.

2. Union:
   Merge two sets into a single set.

This implementation uses:
- Path Compression
- Union by Size

to achieve near constant-time operations.

Applications:
- Kruskal's Minimum Spanning Tree
- Number of Connected Components
- Network Connectivity Problems
- Dynamic Graph Connectivity
- Accounts Merge
- Redundant Connection
*/

/*
Approach 1: Naive Union Find
Idea:
- Store parent relationships.
- Traverse parent links until the root is reached.

Time Complexity:
Find  : O(N)
Union : O(N)

Space Complexity:
O(N)

Drawbacks:
Tall trees can form, making operations expensive.
*/

/*
Approach 2: Union by Rank
Idea:
- Always attach the shorter tree under the taller tree.
- Limits tree height growth.

Time Complexity:
O(α(N))

Space Complexity:
O(N)

Key Insight:
Tree height remains very small.
*/

/*
Approach 3: Union by Size + Path Compression [Implemented]
Idea:
- Attach the smaller component under the larger component.
- Compress paths during find operations.

Time Complexity:
O(α(N))

Space Complexity:
O(N)

Key Insight:
Both optimizations together make DSU operations
effectively constant time for practical inputs.
*/

/*
Method to Solve:
----------------
1. Initialize every node as an independent component.
2. Store component sizes.
3. Use find() to locate the ultimate parent.
4. Compress paths while finding parents.
5. During union:
   - Find both parents.
   - Skip if already in the same component.
   - Attach smaller component to larger component.
6. Update component size after merging.
*/

public class DisjointSet {

    private int[] parent;
    private int[] size;

    /**
     * Initializes DSU with the given number of nodes.
     *
     * @param totalGroups total elements in DSU
     */
    public DisjointSet(int totalGroups) {
        parent = new int[totalGroups];
        size = new int[totalGroups];

        Arrays.fill(parent, -1);
        Arrays.fill(size, 1);
    }

    /**
     * Finds the ultimate parent of a node.
     * Applies path compression.
     *
     * @param node current node
     * @return representative parent
     */
    public int find(int node) {

        if (parent[node] == -1) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }

    /**
     * Merges two components using union by size.
     *
     * @param a first node
     * @param b second node
     * @return void
     */
    public void unionBySize(int a, int b) {

        int parentA = find(a);
        int parentB = find(b);

        // already connected
        if (parentA == parentB) {
            return;
        }

        // attach smaller component
        if (size[parentA] < size[parentB]) {
            parent[parentA] = parentB;
            size[parentB] += size[parentA];
            return;
        }

        parent[parentB] = parentA;
        size[parentA] += size[parentB];
    }
}

// Time Complexity: O(α(N))
// Space Complexity: O(N)
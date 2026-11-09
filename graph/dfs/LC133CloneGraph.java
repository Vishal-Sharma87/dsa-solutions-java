package graph.dfs;

// Created at: 09-November-2026
// Last revised at: 09-November-2026
// Link: https://leetcode.com/problems/clone-graph/

/*
Problem Description:
--------------------
Statement:
Given a reference to a node in a connected undirected graph,
return a deep copy of the graph.

Each node contains:
- An integer value.
- A list of its neighboring nodes.

The cloned graph must contain entirely new nodes while
preserving the original graph's adjacency relationships.

Example:
Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]

Constraints:
- 0 <= number of nodes <= 100
- 1 <= Node.val <= 100
- Node values are unique.
- No repeated edges or self-loops are present in the
  standard problem constraints.
- The graph is connected.
*/

/*
Approach 1: DFS + HashMap

Idea:
1. Traverse the original graph using DFS.
2. Create a clone of the current node.
3. Store the original node's value and its clone in a HashMap.
4. Traverse all neighbors of the original node.
5. If a neighbor's clone already exists, reuse it.
6. Otherwise, recursively create its clone.
7. Add the cloned neighbor to the current clone's
   neighbor list.

Time Complexity:
O(V + E), where V is the number of vertices and E
is the number of edges.

Each vertex is cloned once and each adjacency entry
is processed once.

Space Complexity:
O(V) auxiliary space for the HashMap and recursion stack.

The cloned graph itself requires O(V + E) space.

Key Insight:
Store the clone in the HashMap immediately after creating it,
before traversing its neighbors.

This prevents infinite recursion when DFS encounters
a previously visited node through a cycle.

It also ensures that every original node has exactly
one corresponding cloned node.
*/

/*
Method to Solve:
----------------
1. Handle the null input case.
2. Initialize a HashMap to maintain original-to-clone mapping.
3. Start DFS from the given node.
4. Create and register the current node's clone.
5. Traverse its neighbors and reuse existing clones
   or recursively create missing ones.
6. Assign the cloned neighbors and return the clone.
*/

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class LC133CloneGraph {

    /**
     * Creates a deep copy of the connected graph.
     *
     * @param node reference to the starting node
     * @return cloned graph's starting node, or null
     *         if the input is null
     */
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        Map<Integer, Node> created = new HashMap<>();

        return dfs(node, created);
    }

    /**
     * Clones the current node and recursively clones
     * its neighboring nodes.
     *
     * @param root    current original graph node
     * @param created mapping of original node values to clones
     * @return cloned version of the current node
     */
    private Node dfs(Node root, Map<Integer, Node> created) {
        if (root == null)
            return null;

        Node newNode = new Node(root.val);
        created.put(root.val, newNode);

        List<Node> nbrsOfClone = new ArrayList<>();

        for (Node nbrInOriginal : root.neighbors) {
            Node nbr = created.get(nbrInOriginal.val);

            if (nbr == null) {
                nbr = dfs(nbrInOriginal, created);
            }

            nbrsOfClone.add(nbr);
        }

        newNode.neighbors = nbrsOfClone;

        return newNode;
    }
}

// Time Complexity: O(V + E)
// Auxiliary Space Complexity: O(V)
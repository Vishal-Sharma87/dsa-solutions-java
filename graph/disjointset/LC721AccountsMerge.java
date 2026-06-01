package graph.disjointset;

// Created at: 02-June-2026
// Last revised at: 02-June-2026
// Link: https://leetcode.com/problems/accounts-merge/

import java.util.*;

/*
Problem Description:
--------------------
Statement:
Given a list of accounts where each element is a list of strings — the first element is a name,
and the rest are emails — merge accounts that belong to the same person. Two accounts belong to
the same person if they share at least one common email. After merging, return the accounts in
any order. Each merged account should have the name as the first element followed by the emails
sorted in lexicographical order. No two accounts of different people share an email.

Example:
Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],
                   ["John","johnsmith@mail.com","john00@mail.com"],
                   ["Mary","mary@mail.com"],
                   ["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
         ["Mary","mary@mail.com"],
         ["John","johnnybravo@mail.com"]]

Constraints:
- 1 <= accounts.length <= 1000
- 2 <= accounts[i].length <= 10
- 1 <= accounts[i][j].length <= 30
- accounts[i][0] is a name; accounts[i][j] (j >= 1) is a valid email
- Emails are guaranteed to be unique across different people
*/

/*
Approach 1: Brute Force (Repeated Merging)
Idea:
For each account, scan all other accounts to find shared emails and merge them.
Repeat until no more merges are possible.

Time Complexity: O(n^2 * k) per pass, multiple passes needed — effectively O(n^3 * k)
Space Complexity: O(n * k)

Drawbacks:
Expensive repeated scanning. No clean termination condition.

★ Approach 2: DSU on Account Indices
Idea:
Treat each account as a DSU node. For every email, if it's been seen before,
union the current account with the one that first registered the email.
After all unions, group emails by their component root and sort them.

The key insight: we union accounts (not emails) — so DSU size is O(n), not O(n * k).
The emailMap stores email → first-account-index, giving O(1) lookup per email.

Time Complexity: O(n * k * α(n)) — near-linear due to path compression
Space Complexity: O(n * k) for the email map and merged output
*/

/*
Method to Solve:
----------------
1. Build a DSU of size = number of accounts.
2. Iterate over every email in every account. Maintain a map: email → account index.
   - If email is new, register it.
   - If email was seen before, union current account with the one that registered it.
3. After all unions, iterate over emailMap. For each email, find its root account index.
   Group emails under their root. Pre-initialise each root's list with the account name.
4. For each root's list, sort emails (subList from index 1 onwards) lexicographically.
5. Return all lists.
*/

// Time Complexity: O(n * k * α(n))
// Space Complexity: O(n * k)

public class LC721AccountsMerge {

    private class DSU {
        int[] parent;
        int[] size;

        public DSU(int n) {
            parent = new int[n];
            size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         * Finds root of node with path compression.
         *
         * @param node the node to find root for
         * @return root of the component
         */
        public int findParent(int node) {
            if (parent[node] == node)
                return node;
            return parent[node] = findParent(parent[node]); // path compression
        }

        /**
         * Unions two components by size.
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
     * Merges accounts that share at least one common email using DSU.
     *
     * @param accounts list of accounts, each starting with a name followed by
     *                 emails
     * @return merged accounts with sorted emails, name as first element
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int len = accounts.size();
        DSU dsu = new DSU(len);

        // email → first account index that registered it
        Map<String, Integer> emailMap = new HashMap<>();

        for (int i = 0; i < len; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                Integer existing = emailMap.get(email);
                if (existing != null) {
                    // shared email — same person
                    dsu.unionBySize(existing, i);
                } else {
                    emailMap.put(email, i);
                }
            }
        }

        // root account index → [name, email1, email2, ...]
        Map<Integer, List<String>> merged = new HashMap<>();
        emailMap.forEach((email, owner) -> {
            int root = dsu.findParent(owner);
            // name is fetched from root account — safe because all merged accounts share
            // the same name
            merged.computeIfAbsent(root, r -> new ArrayList<>(List.of(accounts.get(r).get(0)))).add(email);
        });

        List<List<String>> ans = new ArrayList<>();
        merged.forEach((root, emails) -> {
            // sort emails only, skip index 0 (name)
            Collections.sort(emails.subList(1, emails.size()));
            ans.add(emails);
        });

        return ans;
    }
}
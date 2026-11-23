package design;

// Created at: 23-November-2026
// Last revised at: 23-November-2026
// Link: https://leetcode.com/problems/map-sum-pairs/

import java.util.HashMap;
import java.util.Map;

/*
Problem Description:
--------------------
Design a MapSum class that supports:

1. insert(key, val): Insert a key-value pair.
   If the key already exists, update its value.

2. sum(prefix): Return the sum of values associated
   with keys sharing the given prefix.

Example:
--------
insert("apple", 3)
sum("ap") -> 3

insert("app", 2)
sum("ap") -> 5

Constraints:
-----------
- 1 <= key.length, prefix.length <= 50
- key and prefix consist of lowercase English letters.
- 1 <= val <= 1000
- At most 50 calls to insert and sum.
*/

/*
Approach 1: Brute Force (HashMap)

Idea:
-----
1. Maintain all key-value pairs in a HashMap.
2. For sum(prefix), iterate over every stored entry.
3. Skip keys shorter than the prefix.
4. Compare characters to check whether the key
   starts with the given prefix.
5. Accumulate values of matching keys.

Time Complexity:
----------------
insert(): O(1) average
sum(): O(N * P)

N = number of distinct keys
P = prefix length

Space Complexity:
-----------------
O(N * L)

L = average key length

Drawbacks / Key Insight:
-----------------------
Every sum operation scans all stored keys,
even when most keys do not share the prefix.

A Trie can optimize prefix-based lookups.
*/

public class LC677MapSumPairs {

    private Map<String, Integer> map;

    public LC677MapSumPairs() {
        map = new HashMap<>();
    }

    /**
     * Inserts a key-value pair or updates an existing key.
     *
     * @param key key to insert
     * @param val value associated with the key
     * @return void
     */
    public void insert(String key, int val) {
        map.put(key, val);
    }

    /**
     * Calculates the sum of values for keys sharing a prefix.
     *
     * @param prefix prefix to search for
     * @return sum of matching key values
     */
    public int sum(String prefix) {
        int sum = 0;

        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            String key = pair.getKey();
            int value = pair.getValue();

            if (key.length() < prefix.length())
                continue;

            boolean isPrefix = true;

            for (int i = 0; i < prefix.length(); i++) {
                if (key.charAt(i) != prefix.charAt(i)) {
                    isPrefix = false;
                    break;
                }
            }

            if (isPrefix)
                sum += value;
        }

        return sum;
    }
}

// Time Complexity: O(N * P) for sum()
// Space Complexity: O(N * L)
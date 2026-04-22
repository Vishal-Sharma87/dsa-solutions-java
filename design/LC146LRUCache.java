package design;
// Created at: 23-04-2026

import java.util.HashMap;

// Last revised at: 23-04-2026
// Link: https://leetcode.com/problems/lru-cache/
// Time Complexity: O(1) — get and put
// Space Complexity: O(capacity) — map + DLL

/*
    Problem Description:
    Design a data structure that follows Least Recently Used (LRU) cache eviction.
    Implement get(key) and put(key, value), both in O(1).
    When capacity is exceeded, evict the least recently used key before inserting.

    Approach: HashMap + Doubly Linked List
    - HashMap gives O(1) key → node lookup.
    - DLL maintains recency order: head = most recent, tail = least recent.
    - On any access (get or put of existing key), move that node to head.
    - On eviction, remove from tail and delete from map.
    - Two helpers keep node manipulation clean:
        joinNeighbour(node) — unlinks node from its current position.
        updateHead(node)    — inserts node at the front.
*/

class Dll {
    int data;
    int key;
    Dll prev;
    Dll next;

    public Dll(int k, int d) {
        data = d;
        key = k;
        next = null;
        prev = null;
    }
}

class LC146LRUCache {

    private Dll head;
    private Dll tail;
    private int capacity;
    private HashMap<Integer, Dll> map;

    /**
     * Initialises the LRU cache with a fixed capacity.
     *
     * @param c maximum number of key-value pairs the cache can hold
     */
    public LC146LRUCache(int c) {
        capacity = c;
        map = new HashMap<>();
        head = null;
        tail = head;
    }

    /**
     * Returns the value for the key if present, moving it to MRU position.
     *
     * @param key the key to look up
     * @return cached value, or -1 if key does not exist
     */
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        Dll node = map.get(key);
        int value = node.data;

        if (head == node)
            return value; // already MRU, nothing to do

        if (tail == node)
            tail = tail.prev; // shrink tail before detaching

        joinNeighbour(node);
        updateHead(node);
        return value;
    }

    /**
     * Inserts or updates a key-value pair, evicting the LRU entry if needed.
     * Updated or inserted key becomes the most recently used.
     *
     * @param key   the key to insert or update
     * @param value the value to associate with the key
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Dll node = map.get(key);
            node.data = value; // update value in-place

            if (head == node)
                return; // already MRU

            if (tail == node)
                tail = tail.prev;

            joinNeighbour(node);
            updateHead(node);
            return;
        }

        // Evict LRU (tail) if at capacity
        if (map.size() == capacity) {
            int keyToDelete = tail.key;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.prev;
            }
            map.remove(keyToDelete);
        }

        Dll newNode = new Dll(key, value);
        map.put(key, newNode);

        if (head == null) {
            head = newNode; // first ever insert
            tail = newNode;
        } else {
            updateHead(newNode);
        }
    }

    /**
     * Moves the given node to the head (MRU position) of the DLL.
     * Caller must have already unlinked the node via joinNeighbour if needed.
     *
     * @param node the node to place at the front
     */
    private void updateHead(Dll node) {
        node.prev = null;
        node.next = head;
        head.prev = node;
        head = node;
    }

    /**
     * Unlinks a node from its current position by connecting its neighbours
     * directly.
     * Does not update head or tail — caller is responsible for that.
     *
     * @param node the node to detach
     */
    private void joinNeighbour(Dll node) {
        Dll left = node.prev;
        Dll right = node.next;

        if (left != null)
            left.next = right;
        if (right != null)
            right.prev = left;
    }
}

/*
 * Your LC146LRUCache object will be instantiated and called as such:
 * LC146LRUCache obj = new LC146LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key, value);
 */
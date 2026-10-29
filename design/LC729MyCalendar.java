package design;

// Created at: 29-October-2026

// Last revised at: 29-October-2026
// Link: https://leetcode.com/problems/my-calendar/

/*
Problem Description:
--------------------
Implement a calendar that stores non-overlapping bookings.

Each booking is represented by a half-open interval [startTime, endTime).
A booking is accepted only if it does not overlap any existing booking.

Return true when a booking is accepted and false when it conflicts
with an existing booking.

Example:
--------
book(10, 20) -> true
book(15, 25) -> false
book(20, 30) -> true

Constraints:
------------
0 <= startTime < endTime <= 10^9
At most 1000 calls to book().
*/

/*
Approach 1: Brute Force

Idea:
-----
Maintain all bookings and check the new interval against every existing
booking. Reject it if any overlap is found; otherwise, store it.

Time Complexity:
O(n) per booking.

Space Complexity:
O(n) for storing bookings.

Drawbacks:
Every booking requires checking the existing intervals until a conflict
is found or all bookings have been examined.
*/

/*
Approach 2: Sorted Doubly Linked List

Idea:
-----
Maintain bookings in ascending order of start time using a doubly linked
list. Keep references to both head and tail.

For a new booking:
1. Find the first existing interval whose end exceeds startTime.
2. If no such interval exists, append the booking at the tail.
3. If the new interval overlaps the candidate, reject the booking.
4. Otherwise, insert before the candidate.

The existing intervals are already sorted and non-overlapping, so only
the first potentially conflicting interval needs to be checked.

Time Complexity:
O(n) worst case per booking.
O(1) best case when the insertion position is at the head or the
calendar is empty.

Space Complexity:
O(n) for storing bookings.

Key Insight:
The chronological ordering allows conflict detection using only the
first existing interval whose end is greater than the requested start.
The doubly linked list supports insertion in O(1) once the position
has been located.
*/

public class LC729MyCalendar {

    private static class ListNode {
        int start;
        int end;
        ListNode prev;
        ListNode next;

        /**
         * Creates a node representing a booking interval.
         *
         * @param start booking start time
         * @param end   booking end time
         */
        ListNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int length;

    /**
     * Initializes an empty calendar.
     */
    public LC729MyCalendar() {
        length = 0;
    }

    /**
     * Inserts a booking at the head of the list.
     *
     * @param start booking start time
     * @param end   booking end time
     */
    private void insertAtHead(int start, int end) {
        ListNode newNode = new ListNode(start, end);

        if (length == 0) {
            head = newNode;
            tail = newNode;
            length++;
            return;
        }

        head.prev = newNode;
        newNode.next = head;
        head = newNode;

        length++;
    }

    /**
     * Inserts a booking at the tail of the list.
     *
     * @param start booking start time
     * @param end   booking end time
     */
    private void insertAtTail(int start, int end) {
        if (length == 0) {
            insertAtHead(start, end);
            return;
        }

        ListNode newNode = new ListNode(start, end);

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;

        length++;
    }

    /**
     * Finds the first booking whose end time exceeds the given time.
     *
     * @param currentTime start time of the requested booking
     * @return first potentially conflicting node, or null if none exists
     */
    private ListNode getFirstPotentialConflict(int currentTime) {
        ListNode mover = head;

        while (mover != null && mover.end <= currentTime)
            mover = mover.next;

        return mover;
    }

    /**
     * Checks whether the calendar contains no bookings.
     *
     * @return true if the calendar is empty, otherwise false
     */
    private boolean isEmpty() {
        return length == 0;
    }

    /**
     * Books an interval if it does not overlap an existing booking.
     *
     * @param startTime requested booking start time
     * @param endTime   requested booking end time
     * @return true if the booking is accepted, otherwise false
     */
    public boolean book(int startTime, int endTime) {
        if (isEmpty()) {
            insertAtHead(startTime, endTime);
            return true;
        }

        ListNode current = getFirstPotentialConflict(startTime);

        if (current == null) {
            insertAtTail(startTime, endTime);
            return true;
        }

        if (current.start < startTime)
            return false;

        if (endTime > current.start)
            return false;

        ListNode previous = current.prev;

        if (previous == null) {
            insertAtHead(startTime, endTime);
            return true;
        }

        ListNode newNode = new ListNode(startTime, endTime);

        previous.next = newNode;
        newNode.prev = previous;

        current.prev = newNode;
        newNode.next = current;

        length++;

        return true;
    }
}

// Time Complexity: O(n) worst case per booking
// Space Complexity: O(n) for n accepted bookings

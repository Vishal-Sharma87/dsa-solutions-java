package linkedlist.singly;

import linkedlist.ListNode;

public class DeleteInSinglyLinkedList {

    public static ListNode deleteAtHead(ListNode head) {

        if (head != null)
            head = head.next;
        return head;
    }

}

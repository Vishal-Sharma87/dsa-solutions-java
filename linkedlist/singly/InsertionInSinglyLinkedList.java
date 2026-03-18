package linkedlist.singly;

public class InsertionInSinglyLinkedList {

    public static ListNode insertAtHead(ListNode head, int data) {

        ListNode temp = new ListNode(data);
        temp.next = head;
        head = temp;
        return head;
    }

}

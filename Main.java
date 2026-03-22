import linkedlist.singlylinkedlist.ArrayToLinkedList;
import linkedlist.singlylinkedlist.ListNode;

public class Main {
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // find middle node
        ListNode fast = head.next;
        ListNode mid = head;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                mid = mid.next;
            }
        }
        // slow pointer is at middle node

        ListNode nextNode = mid.next;
        mid.next = null;

        // split left half
        ListNode left = sortList(head);
        head = left;

        // slplit right half
        ListNode right = sortList(nextNode);
        nextNode = right;

        if (left == null)
            return right;
        if (right == null)
            return left;

        ListNode mergedHead = new ListNode(-1);
        ListNode tail = mergedHead;

        ListNode l = left;
        ListNode r = right;

        while (l != null && r != null) {
            ListNode temp;
            if (l.data < r.data) {
                temp = l;
                l = l.next;
            } else {
                temp = r;
                r = r.next;
            }
            temp.next = null;
            tail.next = temp;
            tail = tail.next;
        }
        if (l != null)
            tail.next = l;
        if (r != null)
            tail.next = r;

        return mergedHead.next;
    }

    public static void main(String[] args) {

        int[] a = new int[] { 4,2,1,3 };
        ListNode head = ArrayToLinkedList.convertArray2LinkedList(a);

        sortList(head);

    }
}

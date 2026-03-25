import linkedlist.singlylinkedlist.ArrayToLinkedList;
import linkedlist.singlylinkedlist.ListNode;

public class Main {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;

        ListNode lastNode = head;
        int cnt = 0;

        while(lastNode != null && cnt < k - 1) lastNode = lastNode.next;

        if(lastNode == null) return head;

        ListNode nextHead = lastNode.next;
        lastNode.next = null;

        ListNode left = null; 
        ListNode curr = head; 
        ListNode right; 
        while (curr != null) {
            right = curr.next;
            curr.next = left;
            left = curr;
            curr = right;
        }
        

        head.next = reverseKGroup(nextHead, k);

        return left;
    }

    public static void main(String[] args) {

        int[] a = new int[] { 4,2,1,3 };
        ListNode head = ArrayToLinkedList.convertArray2LinkedList(a);

        ListNode temp = reverseKGroup(head, 2);

    }
}

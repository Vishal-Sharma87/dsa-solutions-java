
import linkedlist.ArrayToLinkedList;
import linkedlist.ListNode;
import linkedlist.PrintLinkedList;
import linkedlist.singly.DeleteInSinglyLinkedList;
import linkedlist.singly.InsertionInSinglyLinkedList;

public class Main {
    public static void main(String[] args) {

        int[] a = new int[] {13};

        ListNode head = ArrayToLinkedList.convertArray2LinkedList(a);

        PrintLinkedList.singlyLinkedList(head = InsertionInSinglyLinkedList.insertAtHead(head, 15));
        PrintLinkedList.singlyLinkedList(head = DeleteInSinglyLinkedList.deleteAtHead(head));

    }
}

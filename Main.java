
import linkedlist.singlylinkedlist.ArrayToLinkedList;
import linkedlist.singlylinkedlist.DeleteInSinglyLinkedList;
import linkedlist.singlylinkedlist.InsertionInSinglyLinkedList;
import linkedlist.singlylinkedlist.ListNode;
import linkedlist.singlylinkedlist.PrintLinkedList;

public class Main {
    public static void main(String[] args) {

        int[] a = new int[] {13};

        ListNode head = ArrayToLinkedList.convertArray2LinkedList(a);

        PrintLinkedList.singlyLinkedList(head = InsertionInSinglyLinkedList.insertAtHead(head, 15));
        PrintLinkedList.singlyLinkedList(head = DeleteInSinglyLinkedList.deleteAtHead(head));

    }
}

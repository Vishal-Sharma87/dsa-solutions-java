
import linkedlist.ArrayToLinkedList;
import linkedlist.CheckIfTargetExistsInLL;
import linkedlist.LinkedListLength;
import linkedlist.ListNode;
import linkedlist.PrintLinkedList;

public class Main {
    public static void main(String[] args) {

        int[] a = new int[] { 18 };

        ListNode head = ArrayToLinkedList.convertArray2LinkedList(a);

        int len = new LinkedListLength().getLengthOfSinglyLL(head);

        System.out.println("Length: " + len);
        PrintLinkedList.singlyLinkedList(head);

        if (CheckIfTargetExistsInLL.isExists(head, 18)) {
            System.out.println("Exists");
        }

    }
}

package linkedlist.doublylinkedlist;

public class PrintDoublyLL {

    public static void print(ListNode head) {

        // Print header message indicating the operation
        System.out.println("Printing Doubly LinkedList");

        // Initialize temp pointer to traverse from head
        ListNode temp = head;

        // Traverse the entire list until we reach the end (temp becomes null)
        while (temp != null) {
            // Print current node's data followed by a space
            System.out.print(temp.data + " ");
            // Move pointer to the next node
            temp = temp.next;
        }

        // Print newline after all nodes to separate output
        System.out.println();
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import linkedlist.singlylinkedlist.ArrayToLinkedList;
import linkedlist.singlylinkedlist.ListNode;

public class Main {
    private static void generate(Set<String> ans, int len, int i, String curr, boolean taken) {

        if (i > len) {
            ans.add(curr);
            return;
        }

        if (!taken) {
            generate(ans, len, i + 1, curr + "0", false);
            generate(ans, len, i + 1, curr + "1", true);
        } else {
            generate(ans, len, i + 1, curr + "0", false);
        }
    }

    public static List<String> generateString(int N) {
        // Write your code here.

        if (N <= 0)
            return null; // empty list

        Set<String> strs = new TreeSet<>();
        String curr = "";

        boolean taken = false;

        int i = 1;

        generate(strs, N, i, curr, taken);

        return new ArrayList<>(strs);
    }

    public static void main(String[] args) {

        int[] a = new int[] { 4, 2, 1, 3 };
        ListNode head = ArrayToLinkedList.convertArray2LinkedList(a);

        List<String> ans = generateString(4);

        ans.forEach((s) -> {System.out.println(s);});

    }
}

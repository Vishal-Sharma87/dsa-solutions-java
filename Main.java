
import recursion.subsequencepatterns.CountSubsequenceWithSumK;

public class Main {

    public static void main(String[] args) {

        int[] a = new int[] {5, 10, 7, 3, 1, 6, 9};
        
        int cnt = CountSubsequenceWithSumK.getSubSequenceCountWithSumTarget(a, 15);

        System.out.println(cnt);

    }
}

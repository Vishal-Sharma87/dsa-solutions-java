import java.util.Arrays;

public class TestingCode {

    public static boolean rotateString(String s, String goal) {
        int len = goal.length();
        int sLen = s.length();
        if (len > sLen)
            return false;

        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(s);

        int i = 0;
        while (i < sb.length()) {
            if (sb.charAt(i) == goal.charAt(0)) {
                int j = 0;
                while (j < Math.min(len, sb.length())) {
                    if (sb.charAt(j + 1) != goal.charAt(j))
                        break;
                    j++;
                }
                if (j == len)
                    return true;
                i = j + 1;
            } else {
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        if (rotateString("abcde", "bcdea")) {
            System.out.println("True");
        }
    }
}

import java.lang.Math;

/**
 * LongestPalindrome
 */
public class LongestPalindrome {

    private static final String STR = "abacdfgdcaba";

    public static void main (String[] args) {
        System.out.println("The longest palindrome of " + STR + " is " + longestPalindrome(STR));
    }

    private static String longestPalindrome(String s) {

        if (s == null || s.isEmpty()) return "";

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int lenOne = expandAround(s, i, i);
            int lenTwo = expandAround(s, i, i + 1);
            int len = Math.max(lenOne, lenTwo);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expandAround(String s, int left, int rigth) {
       int lInd = left;
       int rInd = rigth;

       while (lInd >= 0 && rInd < s.length()
               && s.charAt(lInd) == s.charAt(rInd)) {
           lInd--;
           rInd++;
       }

       return rInd - lInd - 1;
    }
}

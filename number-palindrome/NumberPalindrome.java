
/**
 * NumberPalindrome
 */
public class NumberPalindrome {

    private static final int NUM = 224313422;

    public static void main (String[] args) {
        System.out.println("Number " + NUM + " is palindrome: " + isNumberPalindrome(NUM));
    }

    public static boolean isNumberPalindrome(int number) {

        if (number < 0) return false;

        int curNum = number;
        int reverse = 0;
        while (curNum != 0) {
            int pop = curNum % 10;
            curNum /= 10;

            if (reverse > Integer.MAX_VALUE / 10 || (reverse == Integer.MAX_VALUE / 10 && reverse > 7))
                return false;

            reverse = reverse * 10 + pop;
        }

        return reverse == number;
    }
}

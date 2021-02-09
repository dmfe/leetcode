/**
 * Solution
 */
public class Solution {

    public int reverse(int x) {
        int rev = 0;

        while(x != 0) {
            int pop = x % 10;
            x /= 10;

            if (isPositiveOverflow(rev, pop) || isNegativeOverflow(rev, pop))
                return 0;

            rev = rev * 10 + pop;
        }

        return rev;
    }

    private boolean isPositiveOverflow(int num, int digit) {
        return (num > Integer.MAX_VALUE / 10)
            || (num == Integer.MAX_VALUE / 10 && digit > 7);
    }

    private boolean isNegativeOverflow(int num, int digit) {
        return (num < Integer.MIN_VALUE / 10)
            || (num == Integer.MIN_VALUE / 10 && digit < -8);
    }
}

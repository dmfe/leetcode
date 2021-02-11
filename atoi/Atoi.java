
/**
 * Atoi
 */
public class Atoi {

    private static final String NUMBER = "2147483646";

    public static void main (String[] args) {
        System.out.println("String is : " + NUMBER);
        System.out.println("Converted number is : " + atoi(NUMBER));
    }

    public static int atoi(String numberStr) {

        int i = 0;
        int sign = 1;
        int result = 0;

        if (numberStr.length() == 0) return 0;

        while (i < numberStr.length() && numberStr.charAt(i) == ' ') i++;
        if (isSign(i, numberStr)) {
            sign = getSign(i, numberStr);
            i++;
        }

        while (i < numberStr.length()
                && numberStr.charAt(i) >= '0'
                && numberStr.charAt(i) <= '9') {

            if (result > Integer.MAX_VALUE / 10
                    || (result == Integer.MAX_VALUE / 10 && numberStr.charAt(i) - '0' > Integer.MAX_VALUE % 10)) {

                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + (numberStr.charAt(i++) - '0');
        }

        return result * sign;
    }

    private static int getSign(int curPos, String numberStr) {
        int sign = 1;
        sign = (numberStr.charAt(curPos) == '-') ? -1 : 1;

        return sign;
    }

    private static boolean isSign(int curPos, String numberStr) {
        return (curPos < numberStr.length()) &&
            (numberStr.charAt(curPos) == '-' || numberStr.charAt(curPos) == '+');
    }
}


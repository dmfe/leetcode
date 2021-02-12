
/**
 * IntToRoman
 */
public class IntToRoman {

    private static final int NUM = 1999;

    enum Roman {
        M(1000), CM(900), D(500), CD(400),
        C(100), XC(90), L(50), XL(40),
        X(10), IX(9), V(5), IV(4), I(1);

        private int val;

        Roman(int val) {
            this.val = val;
        }

        int getVal() {
            return val;
        }
    }

    public static void main (String[] args) {
        System.out.println("Roman equivalent of num " + NUM
                + " is " + toRoman(NUM));
    }

    static String toRoman(int num) {
        StringBuilder result = new StringBuilder();

        for (Roman roman : Roman.values()) {
            int count = num / roman.getVal();
            for (int i = 0; i < count; i++)
                result.append(roman);

            num %= roman.getVal();
            if (num == 0) break;
        }

        return result.toString();
    }
}

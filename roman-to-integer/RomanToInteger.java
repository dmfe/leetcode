import java.util.stream.Stream;

/**
 * RomanToInteger
 */
public class RomanToInteger {

    private static final String ROMAN_NUM = "MCMXCIV";

    enum Roman {
        M("M", 1000), CM("CM", 900), D("D", 500), CD("CD", 400),
        C("C", 100), XC("XC", 90), L("L", 50), XL("XL", 40),
        X("X", 10), IX("IX",9), V("V", 5), IV("IV", 4), I("I", 1);

        private String strVal;
        private int numVal;

        Roman(String strVal, int numVal) {
            this.strVal = strVal;
            this.numVal = numVal;
        }

        String getStrVal() {
            return strVal;
        }

        int getNumVal() {
            return numVal;
        }

        static Roman fromStr(String strNum) {
            return Stream.of(values())
                .filter(roman -> roman.strVal.equals(strNum))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "There is no such roman number symbol: " + strNum)
                );
        }
    }

    public static void main (String[] args) {
        RomanToInteger rti = new RomanToInteger();

        System.out.println(rti.convert(ROMAN_NUM));
    }

    public int convert(String roman) {
       int result = 0;
       StringBuilder romanNumSymbol = new StringBuilder();

       for (int i = 0; i < roman.length(); i++) {
           char romanChar = roman.charAt(i);
           romanNumSymbol.append(romanChar);

           if (i + 1 < roman.length()) {
               char nextRomanChar = roman.charAt(i + 1);

               if (isSubtraction(romanChar, nextRomanChar)) {
                   romanNumSymbol.append(nextRomanChar);
                   i++;
               }
           }

           result += Roman.fromStr(romanNumSymbol.toString()).getNumVal();
           romanNumSymbol.setLength(0);
       }

       return result;
    }

    private boolean isSubtraction(char cur, char next) {
        String sub = cur + "" + next;

        return sub.equals("IV") || sub.equals("IX") || sub.equals("XL")
            || sub.equals("XC") || sub.equals("CD") || sub.equals("CM");
    }
}


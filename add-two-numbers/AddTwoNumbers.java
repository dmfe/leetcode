import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * AddTwoNumbers
 */
public class AddTwoNumbers {

    public static void main (String[] args) {

        ListNumber one = new ListNumber(Arrays.asList(9,9,9,9,9,9,9));
        ListNumber two = new ListNumber(Arrays.asList(9,9,9,9));

        System.out.println("Sum of two is: " + one.add(two));
    }
}

class ListNumber {
    private List<Integer> value;

    ListNumber(List<Integer> value) {
       this.value = new LinkedList<>(value);
    }

    private static class Digit {
        private int digit;
        private int carry;

        Digit(int digit, int carry) {
            this.digit = digit;
            this.carry = carry;
        }

        int getDigit() {
            return digit;
        }

        int getCarry() {
            return carry;
        }
    }

    private Digit calculateDigit(Iterator<Integer> one,
                                 Iterator<Integer> two,
                                 int carry) {

        int oneVal = one.hasNext() ? one.next() : 0;
        int twoVal = two.hasNext() ? two.next() : 0;
        int sum = oneVal + twoVal + carry;

        return new Digit(sum % 10, sum / 10);
    }

    ListNumber add(ListNumber other) {
        List<Integer> result = new LinkedList<>();

        Iterator<Integer> one = this.value.iterator();
        Iterator<Integer> two = other.value.iterator();

        Digit currentDigit = calculateDigit(one, two, 0);
        do {
            result.add(currentDigit.getDigit());
            currentDigit = calculateDigit(
                    one, two, currentDigit.getCarry());
        } while (currentDigit.getDigit() != 0
                || currentDigit.getCarry() != 0);

        return new ListNumber(result);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}



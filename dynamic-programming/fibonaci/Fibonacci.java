import java.util.Map;
import java.util.HashMap;
import java.math.BigInteger;

// 1  2  3  4  5  6  7  8  9  10
// 1  1  2  3  5  8  13 21 34 55
//
// Fib memoized complexity
// O(n) time
// O(n) space

class Fibonacci {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Please enter the number of fibonacci sequence!");
      System.exit(1);
    }

    int number = Integer.valueOf(args[0]);

    System.out.println("Fibonacci value for number " + number + ": " +
        new Fibonacci().countFib(number));
  }

  public BigInteger countFib(int number) {
    return countFib(number, new HashMap<>());
  }

  public BigInteger countFib(int number, Map<Integer, BigInteger> memo) {
    if (memo.containsKey(number)) {
      return memo.get(number);
    }

    if (number <= 2) {
      return BigInteger.valueOf(1L);
    }
    
    memo.put(number, countFib(number - 1, memo).add(countFib(number - 2, memo)));

    return memo.get(number);
  }
}

import java.util.Arrays;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;

// Write a function `canSum(targetSum, numbers)` that takes in a
// targetSum and an array of numbers as arguments.
//
// The function should return a boolean indicating whether or not it
// is possible to generate the targetSum using numbers from the array.
//
// You may use an element of the array as many times as needed.
//
// You may assume that all input numbers are nonegative.
// canSum(7, [5, 3, 4, 7]) -> true
// canSum(7, [2, 4]) -> false
// canSum(7, [2, 3]) -> true
// canSum(8, [2, 3, 5]) -> true
// canSum(300, [7, 14]) -> false
//
// brute force
// O(n^m) time
// O(m) space
//
// memoized
// O(m*n) time
// O(m) space

class CanSum {

  public static void main(String[] args) {
    if(args.length < 2) {
      System.err.println("Please specify target sum and numbers!");
      System.exit(1);
    }

    BigInteger target = new BigInteger(args[0]);
    BigInteger[] numbers = Arrays.stream(args)
      .skip(1)
      .map(BigInteger::new)
      .toArray(BigInteger[]::new);

    System.out.println("Can sum numbers - " + Arrays.asList(numbers) +
        " to get " + target + ": " + new CanSum().canSum(target, numbers));
  }

  public boolean canSum(BigInteger target, BigInteger[] numbers) {
    return canSum(target, numbers, new HashMap<>());
  }

  public boolean canSum(BigInteger target, BigInteger[] numbers,
                        Map<BigInteger, Boolean> memo) {
    if (memo.containsKey(target)) {
      return memo.get(target);
    }

    if (target.equals(BigInteger.valueOf(0))) {
      return true;
    }

    if (target.signum() == -1) {
      return false;
    }

    for (BigInteger num : numbers) {
      if (canSum(target.subtract(num), numbers, memo)) {
        memo.put(target, true);
        return true;
      }
    }

    memo.put(target, false);
    return false;
  }
}

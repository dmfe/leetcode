import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

// howSum(7, [2, 3]) -> [3, 2, 2]
// howSum(7, [5, 3, 4, 7]) -> [4, 3]
// howSum(7, [2, 4]) -> null
// howSum(8, [2, 3, 5]) -> [2, 2, 2, 2]
// howSum(300, [7, 14]) -> null
//
// m = target sum
// n = numbers.length
//
// Brute force
// O(n^m * m) time
// O(m) space
//
// Memoized
// O(n * m^2) time (polynomial time complexity)
// O(m^2) space

class HowSum {

  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("Please specify target sum and numbers!");
      System.exit(1);
    }

    long target = Long.valueOf(args[0]);
    long[] numbers = Arrays.stream(args)
      .skip(1)
      .mapToLong(Long::parseLong)
      .toArray();

    System.out.println("target: " + target +
        "\nnumbers: " + Arrays.toString(numbers) +
        "\nnumbers adds up to target: " +
        Arrays.toString(new HowSum().howSum(target, numbers)));
  }

  public long[] howSum(long target, long[] numbers) {
    return howSum(target, numbers, new HashMap<>());
  }

  public long[] howSum(long target, long[] numbers,
                       Map<Long, long[]> memo) {
    if (memo.containsKey(target)) {
      return memo.get(target);
    }

    if (target == 0) {
      return new long[] {};
    }

    if (target < 0) return null;

    for (long number : numbers) {
      if (number < 1) {
        return null;
      }

      long[] result = howSum(target - number, numbers, memo);
      if (result != null) {
        long[] curResult = new long[result.length + 1];
        System.arraycopy(result, 0, curResult, 0, result.length);
        curResult[curResult.length - 1] = number;

        memo.put(target, curResult);
        return curResult;
      }
    }

    memo.put(target, null);
    return null;
  }
}

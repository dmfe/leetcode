import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

// Examples:
// bestSum(7, [5, 3, 4, 7]) -> [7]
// bestSum(8, [2, 3, 5]) -> [3, 5]
// bestSum(8, [1, 4, 5]) -> [4, 4]
// bestSum(100, [1, 2, 5, 25]) -> [25, 25, 25, 25]
//
// m = target sum
// n = numbers.length
//
// Brute force:
// time: O(n^m * m)
// space: O(m^2)
//
// Memoized:
// time: O(m^2 * n)
// space: O(m^2)

public class BestSum {

  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("Please specify target sum and numbers!");
    }

    long target = Long.parseLong(args[0]);
    long[] numbers = Arrays.stream(args)
      .skip(1)
      .mapToLong(Long::parseLong)
      .toArray();

    System.out.println("target: " + target +
        "\nnumbers: " + Arrays.toString(numbers) +
        "\nbest numbers adds up to target: " +
        Arrays.toString(new BestSum().bestSum(target, numbers)));
  }

  public long[] bestSum(long target, long[] numbers) {
    return bestSum(target, numbers, new HashMap<>());
  }

  public long[] bestSum(long target, long[] numbers,
                        Map<Long, long[]> memo) {
    if (memo.containsKey(target)) {
      return memo.get(target);
    }

    if (target == 0) {
      return new long[] {};
    }

    if (target < 0) {
      return null;
    }

    long[] bestResult = null;

    for (long number : numbers) {
      if (number < 1) {
        continue;
      }

      long[] remainderResult = bestSum(target - number, numbers, memo);
      if (remainderResult != null && (bestResult == null ||
            remainderResult.length + 1 < bestResult.length)) {
        bestResult = new long[remainderResult.length + 1];
        System.arraycopy(remainderResult, 0, bestResult, 0, remainderResult.length);
        bestResult[bestResult.length - 1] = number;
      }
    }

    memo.put(target, bestResult);
    return bestResult;
  }
}

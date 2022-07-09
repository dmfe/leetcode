import java.util.Map;
import java.util.HashMap;
import java.math.BigInteger;
import java.util.function.BiFunction;

// 1x1 = 1
// 2x3 = 3
// 3x2 = 3
// 3x3 = 6
// 18x18 = 2333606220

class GridTraveler {

  private class Pair<T> {
    private T one;
    private T two;

    public Pair(T one, T two) {
      this.one = one;
      this.two = two;
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
        return true;
      }

      if (object == null || object.getClass() != this.getClass()) {
        return false;
      }

      Pair other = (Pair) object;

      if (other.one.getClass() != one.getClass() ||
          other.two.getClass() != two.getClass()) {
        return false;
      }

      return one.equals(other.one) && two.equals(other.two) ||
        one.equals(other.two) && two.equals(other.one);
    }

    @Override
    public int hashCode() {
      int prime = 31;
      int result = 1;

      result = prime * result + ((one == null) ? 0 : one.hashCode());
      result = prime * result + ((two == null) ? 0 : two.hashCode());

      return result;
    }
  }

  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("Please specify size of the grid!");
      System.exit(1);
    }

    int m = Integer.valueOf(args[0]);
    int n = Integer.valueOf(args[1]);

    GridTraveler gridTraveler = new GridTraveler();

    System.out.println("Grid " + m + "x" + n +
        " possible paths count(with custom pair class as key): " +
        gridTraveler.withExecTime(
          (one, two) -> gridTraveler.calculatePathsCount(one, two),
          m, n
        ));

    System.out.println("Grid " + m + "x" + n +
        " possible paths count(with string as key): " +
        gridTraveler.withExecTime(
          (one, two) -> gridTraveler.calculatePathsCountStr(one, two),
          m, n
        ));
  }

  public <T,R> R withExecTime(BiFunction<T,T,R> function, T one, T two) {
    long startTime = System.nanoTime();
    R result = function.apply(one, two);
    long elapsedTime = System.nanoTime() - startTime;

    System.out.println("Execution time ms: " + elapsedTime/1_000_000);

    return result;
  }

  public BigInteger calculatePathsCount(int m, int n) {
    return calculatePathsCount(m, n, new HashMap<>());
  }

  public BigInteger calculatePathsCount(int m, int n,
                                        Map<Pair<Integer>, BigInteger> memo) {
    Pair<Integer> key = new Pair(m, n);
    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    if (m == 1 && n == 1) {
      return BigInteger.valueOf(1);
    }

    if (m == 0 || n == 0) {
      return BigInteger.valueOf(0);
    }

    memo.put(key, calculatePathsCount(m - 1, n, memo)
      .add(calculatePathsCount(m, n - 1, memo)));

    return memo.get(key);
  }

  public BigInteger calculatePathsCountStr(int m, int n) {
    return calculatePathsCountStr(m, n, new HashMap<>());
  }

  public BigInteger calculatePathsCountStr(int m, int n,
                                           Map<String, BigInteger> memo) {
    String key = m + "," + n;
    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    if (m == 1 && n == 1) {
      return BigInteger.valueOf(1);
    }

    if (m == 0 || n == 0) {
      return BigInteger.valueOf(0);
    }

    memo.put(key, calculatePathsCountStr(m - 1, n, memo)
      .add(calculatePathsCountStr(m, n - 1, memo)));

    return memo.get(key);
  }
}


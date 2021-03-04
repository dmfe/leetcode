import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ThreeSum
 */
public class ThreeSum {

    private static final List<Integer> NUMS = List.of(0, 1, -1, -1, -2, 3);

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();

        System.out.println("Input nums are: \n" + NUMS);
        System.out.println("Unique triplets that gives 0 sum: \n" + ts.findAllZeroSumTriplets(NUMS));
    }

    List<List<Integer>> findAllZeroSumTriplets(List<Integer> inputNums) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> sortedNums = inputNums.stream()
            .sorted()
            .collect(Collectors.toList());

        for (int i = 0; i < sortedNums.size(); i++) {
            if (i > 0 && sortedNums.get(i) == sortedNums.get(i - 1)) {
                continue;
            }

            int l = i + 1, r = sortedNums.size() - 1;
            while (l < r) {
                int tripleSum = sortedNums.get(i) +
                    sortedNums.get(l) +
                    sortedNums.get(r);

                if (tripleSum > 0) {
                    r--;
                } else if (tripleSum < 0) {
                    l++;
                } else {
                    result.add(List.of(
                                sortedNums.get(i),
                                sortedNums.get(l),
                                sortedNums.get(r)
                               ));
                    l++;
                    while (sortedNums.get(l) == sortedNums.get(l - 1) &&
                            l < r) {
                        l++;
                    }
                }
            }
        }

        return result;
    }
}

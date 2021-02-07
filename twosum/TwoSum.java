/*
 * Given an array of integers `nums` and an integer `target`, return
 * indices of the two numbers such that they add up to `target`.
 *
 * You may assume that each input would have exactly one solution, and
 * you may not use the same element twice.
 *
 * You can return the answer in any order.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class TwoSum {

    private static final int[] NUMS = {
        2, 7, 11, 15, 10, 20, 30, 40, 50, 60,
        4, 8, 20, 14, 33, 21, 34, 44, 76, 111,
        -1, 0, 3,  6, 7, 9, 345, 34, 231, 56,
        32, 65, 324, 645, 55, 77, 21, 657, 4,
        11, 111, 1000, 2000, 4, 6, 3, 6, -37,
        10000
    };
    private static final int TARGET = 9963;

    public static void main (String[] args) {

        List<TwoSumFinder> finders = new ArrayList<>();
        finders.add(new BrutForce());
        finders.add(new TwoPassHashTable());
        finders.add(new OnePassHashTable());

        for (TwoSumFinder finder : finders) {
            int [] result = finder.find(NUMS, TARGET);

            System.out.println(finder.getName() + " soulution:");
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            System.out.println("Execution time: " +
                    finder.profileFind(NUMS, TARGET) + " nanoseconds.");
        }
    }
}

/**
 * InnerTwoSum
 */
interface TwoSumFinder {
    int[] find(int[] nums, int target);
    String getName();

    default long profileFind(int[] nums, int target) {
        long startTime = System.nanoTime();

        find(nums, target);

        long endTime = System.nanoTime();

        return endTime - startTime;
    }
}

/*
 * Time complexity O(n2). For each element, we try to find its
 * complement by looping through the rest of array which takes
 * O(n) time. Therefore, the time complexity is O(n2).
 *
 * Space complexity : O(1).
 */
class BrutForce implements TwoSumFinder {
    public int[] find(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == target - nums[j]) {
                    return new int[] { i, j };
                }
            }
        }

        throw new IllegalArgumentException("Unable to find two numbers which " +
                "can be added up to the target.");
    }

    public String getName() {
        return "Brut Force";
    }
}

/*
 * Time complexity : O(n). We traverse the list containing n elements
 * exactly twice. Since the hash table reduces the look up time to O(1),
 * the time complexity is O(n).
 *
 * Space complexity : O(n). The extra space required depends on the number
 * of items stored in the hash table, which stores exactly n elements.
 */
class TwoPassHashTable implements TwoSumFinder {

    public int[] find(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }

        throw new IllegalArgumentException("Unable to find two numbers which " +
                "can be added up to the target.");
    }

    public String getName() {
       return "Two-pass Hash Table";
    }
}

/*
 * Time complexity : O(n). We traverse the list containing n elements only once.
 * Each look up in the table costs only O(1) time.
 *
 * Space complexity: O(n). The extra space required depends on the number of
 * items stored in the hash table, which stores at most n elements.
 */
class OnePassHashTable implements TwoSumFinder {

    public int[] find(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("Unable to find two numbers which " +
                "can be added up to the target.");
    }

    public String getName() {
        return "One-pass Hash Table";
    }
}

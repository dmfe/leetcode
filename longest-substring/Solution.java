import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solution
 */
public class Solution {

    public static void main (String[] args) {
        String longestSubstring = findLongestSubstring("abcabcbb");
        System.out.println("Longest substring: " + longestSubstring
                + " with length: " + longestSubstring.length());
    }

    private static String findLongestSubstring(String s) {
        int maxLength = 0;
        char[] chars = s.toCharArray();
        char[] result = new char[chars.length];
        List<Character> buffer = new ArrayList<>();

        for (int i = 0; i < chars.length; i++) {
            buffer.clear();
            buffer.add(chars[i]);

            for (int j = i + 1; j < chars.length; j++) {
                if (!buffer.contains(chars[j])) {
                    buffer.add(chars[j]);
                } else {
                    if (buffer.size() > maxLength) {
                        maxLength = buffer.size();
                        System.arraycopy(chars, i, result, 0, maxLength);
                    }
                    break;
                }
            }
        }

        return String.valueOf(result).trim();
    }
}

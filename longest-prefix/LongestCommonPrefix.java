import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LongestCommonPrefix
 */
public class LongestCommonPrefix {

    public static void main (String[] args) {
        List<String> inputStrings = List.of(
                "mnf", "mr", "abff", "abks", "abkc", "acc", "lms"
        );
        LongestCommonPrefix lcp = new LongestCommonPrefix();

        System.out.println("Longest common prefix is: "
                + lcp.findLongestPrefix(inputStrings));
    }

    public String findLongestPrefix(List<String> strings) {
        return findGroupPrefix(strings, 1);
    }

    private String findGroupPrefix(List<String> group, int level) {
        String longestPrefix = "";

        List<String> groupCopy = new ArrayList<>(group);
        groupCopy.removeIf(str -> str.length() < level);

        for (int i = 0; i < groupCopy.size(); i++) {
           String currentString = groupCopy.remove(i);
           List<String> matchedGroup = groupCopy.stream()
               .filter(str -> str.charAt(level - 1) == currentString.charAt(level - 1))
               .collect(Collectors.toCollection(ArrayList::new));
           groupCopy.removeAll(matchedGroup);
           matchedGroup.add(currentString);

           String prefix = "";

           if (matchedGroup.size() > 1)
               prefix = currentString.charAt(level - 1)
                   + findGroupPrefix(matchedGroup, level + 1);

           if (prefix.length() > longestPrefix.length())
               longestPrefix = prefix;
        }

        return longestPrefix;
    }
}


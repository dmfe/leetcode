import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LongestCommonPrefix
 */
public class LongestCommonPrefix {

    public static void main (String[] args) {
        List<String> inputStrings = List.of(
                "mnf", "mnr", "mnabff", "mnabks", "mnabkc", "mnacc", "mnlms"
        );
        LongestCommonPrefix lcp = new LongestCommonPrefix();

        System.out.println("Longest common prefix is: "
                + lcp.findLongestPrefix(inputStrings));
        System.out.println("(Recursive search) Longest common prefix is: "
                + lcp.findRecLongestPrefix(inputStrings));
    }

    public String findLongestPrefix(List<String> strings) {
        StringBuilder longestPrefix = new StringBuilder();

        int level = 1;
        while (haveCommonCharAtLength(strings, level)) {
            longestPrefix.append(strings.get(0).charAt(level - 1));
            level++;
        }

        return longestPrefix.toString();
    }

    private boolean haveCommonCharAtLength(List<String> strs, int len)  {
        List<String> fitLengthStrs = strs.stream()
            .filter(str -> str.length() >= len)
            .collect(Collectors.toList());

        if (fitLengthStrs.size() != strs.size()) return false;

        char expectedChar = strs.get(0).charAt(len - 1);

        return strs.stream()
            .filter(str -> str.charAt(len - 1) == expectedChar)
            .collect(Collectors.toList()).size() == strs.size();
    }

    public String findRecLongestPrefix(List<String> strings) {
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


import java.util.ArrayList;
import java.util.List;

/**
 * SortByRow
 */
public class SortByRow implements Solution {

    public String solve(String source, int rowsCount) {

        if (rowsCount == 1) return source;

        List<StringBuilder> rows = new ArrayList<>();
        int reqRows = Math.min(rowsCount, source.length());
        for (int i = 0; i < reqRows; i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goDown = false;

        for (char ch : source.toCharArray()) {
            rows.get(curRow).append(ch);
            if (curRow == 0 || curRow == reqRows - 1)
                goDown = !goDown;
            curRow += goDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows)
            result.append(row);

        return result.toString();
    }
}

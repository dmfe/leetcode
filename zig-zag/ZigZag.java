
/**
 * ZigZag
 */
public class ZigZag {

    private static final String STR = "PAYPALISHIRING";
    private static final int ROWS_COUNT = 4;

    public static void main (String[] args) {
        ZigZagSolver solver = new ZigZagSolver(new SortByRow());
        System.out.println("\"Sort by row\" solution: "
                + solver.solve(STR, ROWS_COUNT));
    }
}

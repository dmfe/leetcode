
/**
 * ZigZagSolver
 */
public class ZigZagSolver {

    private Solution solution;

    public ZigZagSolver(Solution solution) {
        this.solution = solution;
    }

    public void setSolution(Solution solution) {
       this.solution = solution;
    }

    public String solve(String str, int rowsCount) {
       return solution.solve(str, rowsCount);
    }
}

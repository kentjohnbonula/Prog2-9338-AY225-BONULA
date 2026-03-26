/**
 * =====================================================
 * Student Name    : BOÑULA, KENT JOHN C.
 * Course          : BSCSIT 9338 Programming 2
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 24, 2026
 * GitHub Repo     : https://github.com/kentjohnbonula/Prog2-9338-AY225-BONULA
 *
 * Description:
 * This program calculates the determinant of a specific 3x3 matrix
 * using the cofactor expansion method along the first row. It
 * displays the mathematical breakdown of each minor and cofactor.
 * =====================================================
 */

public class DeterminantSolver {

    // SECTION 1: Matrix Declaration
    // Hardcoded matrix assigned to Boñula, Kent John C.
    static int[][] matrix = {
        { 3, 1, 2 },   // Row 1
        { 2, 4, 1 },   // Row 2
        { 5, 2, 3 }    // Row 3
    };

    // SECTION 2: 2×2 Determinant Helper
    // Calculates (a*d) - (b*c) for the sub-matrices
    static int computeMinor(int a, int b, int c, int d) {
        return (a * d) - (b * c);
    }

    // SECTION 3: Matrix Printer
    // Visualizes the 3x3 grid in the console
    static void printMatrix(int[][] m) {
        System.out.println("┌               ┐");
        for (int[] row : m) {
            System.out.printf("│  %2d  %2d  %2d  │%n", row[0], row[1], row[2]);
        }
        System.out.println("└               ┘");
    }

    // SECTION 4: Step-by-Step Determinant Solver
    static void solveDeterminant(int[][] m) {
        System.out.println("====================================================");
        System.out.println("  3x3 MATRIX DETERMINANT SOLVER");
        System.out.println("  Student: BOÑULA, KENT JOHN C.");
        System.out.println("  Assigned Matrix:");
        System.out.println("====================================================");
        printMatrix(m);
        System.out.println("====================================================");
        System.out.println("\nExpanding along Row 1 (cofactor expansion):\n");

        // Step 1: Minor M11 (Using elements from Row 2 and 3, Col 2 and 3)
        int minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
        System.out.printf("  Step 1 — Minor M₁₁: det([%d,%d],[%d,%d]) = (%d×%d)-(%d×%d) = %d%n",
            m[1][1], m[1][2], m[2][1], m[2][2], m[1][1], m[2][2], m[1][2], m[2][1], minor11);

        // Step 2: Minor M12 (Using elements from Row 2 and 3, Col 1 and 3)
        int minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
        System.out.printf("  Step 2 — Minor M₁₂: det([%d,%d],[%d,%d]) = (%d×%d)-(%d×%d) = %d%n",
            m[1][0], m[1][2], m[2][0], m[2][2], m[1][0], m[2][2], m[1][2], m[2][0], minor12);

        // Step 3: Minor M13 (Using elements from Row 2 and 3, Col 1 and 2)
        int minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);
        System.out.printf("  Step 3 — Minor M₁₃: det([%d,%d],[%d,%d]) = (%d×%d)-(%d×%d) = %d%n",
            m[1][0], m[1][1], m[2][0], m[2][1], m[1][0], m[2][1], m[1][1], m[2][0], minor13);

        // Calculate signed Cofactors
        int c11 =  m[0][0] * minor11;
        int c12 = -m[0][1] * minor12;
        int c13 =  m[0][2] * minor13;

        System.out.println();
        System.out.printf("  Cofactor C₁₁ = (+1) × %d × %d = %d%n", m[0][0], minor11, c11);
        System.out.printf("  Cofactor C₁₂ = (-1) × %d × %d = %d%n", m[0][1], minor12, c12);
        System.out.printf("  Cofactor C₁₃ = (+1) × %d × %d = %d%n", m[0][2], minor13, c13);

        // Final Result
        int det = c11 + c12 + c13;
        System.out.printf("%n  det(M) = %d + (%d) + %d%n", c11, c12, c13);
        System.out.println("====================================================");
        System.out.printf("  ✓  DETERMINANT = %d%n", det);

        if (det == 0) {
            System.out.println("  ⚠ The matrix is SINGULAR — it has no inverse.");
        }
        System.out.println("====================================================");
    }

    public static void main(String[] args) {
        solveDeterminant(matrix);
    }
}
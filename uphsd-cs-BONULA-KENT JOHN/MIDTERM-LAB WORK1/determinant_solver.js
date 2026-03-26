/**
 * =====================================================
 * Student Name    : BOÑULA, KENT JOHN C.
 * Course          : BSCSIT 9338 Programming 2
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 24, 2026
 * GitHub Repo     : https://github.com/kentjohnbonula/Prog2-9338-AY225-BONULA
 * Runtime         : Node.js
 * * Description:
 * Node.js script to solve a 3x3 determinant via cofactor expansion.
 * This mirrors the logic used in the Java implementation for consistency.
 * =====================================================
 */

// SECTION 1: Matrix Declaration
const matrix = [
    [3, 1, 2],
    [2, 4, 1],
    [5, 2, 3]
];

// SECTION 2: Matrix Printer
function printMatrix(m) {
    console.log(`┌               ┐`);
    m.forEach(row => {
        const fmt = row.map(v => v.toString().padStart(3)).join("  ");
        console.log(`│ ${fmt}  │`);
    });
    console.log(`└               ┘`);
}

// SECTION 3: 2×2 Determinant Helper
function computeMinor(a, b, c, d) {
    return (a * d) - (b * c);
}

// SECTION 4: Solver Logic
function solveDeterminant(m) {
    const line = "=".repeat(52);

    console.log(line);
    console.log("  3x3 MATRIX DETERMINANT SOLVER");
    console.log("  Student: BOÑULA, KENT JOHN C.");
    console.log("  Assigned Matrix:");
    console.log(line);
    printMatrix(m);
    console.log(line);
    console.log("\nExpanding along Row 1 (cofactor expansion):\n");

    // Logic for the three 2x2 minors
    const minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
    console.log(`  Step 1 — Minor M₁₁: det([${m[1][1]},${m[1][2]}],[${m[2][1]},${m[2][2]}]) = (${m[1][1]}×${m[2][2]}) - (${m[1][2]}×${m[2][1]}) = ${minor11}`);

    const minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
    console.log(`  Step 2 — Minor M₁₂: det([${m[1][0]},${m[1][2]}],[${m[2][0]},${m[2][2]}]) = (${m[1][0]}×${m[2][2]}) - (${m[1][2]}×${m[2][0]}) = ${minor12}`);

    const minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);
    console.log(`  Step 3 — Minor M₁₃: det([${m[1][0]},${m[1][1]}],[${m[2][0]},${m[2][1]}]) = (${m[1][0]}×${m[2][1]}) - (${m[1][1]}×${m[2][0]}) = ${minor13}`);

    // Cofactor calculations with alternating signs
    const c11 = m[0][0] * minor11;
    const c12 = -m[0][1] * minor12;
    const c13 = m[0][2] * minor13;

    console.log(`\n  Cofactor C₁₁ = (+1) × ${m[0][0]} × ${minor11} = ${c11}`);
    console.log(`  Cofactor C₁₂ = (-1) × ${m[0][1]} × ${minor12} = ${c12}`);
    console.log(`  Cofactor C₁₃ = (+1) × ${m[0][2]} × ${minor13} = ${c13}`);

    const det = c11 + c12 + c13;
    console.log(`\n  det(M) = ${c11} + (${c12}) + ${c13}`);
    console.log(line);
    console.log(`  ✓  DETERMINANT = ${det}`);

    if (det === 0) {
        console.log("  ⚠ The matrix is SINGULAR — it has no inverse.");
    }
    console.log(line);
}

solveDeterminant(matrix);
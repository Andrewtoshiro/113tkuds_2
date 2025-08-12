import java.util.Arrays;

/**
 * Implements a set of basic matrix operations using 2D arrays.
 * [span_0](start_span)This implementation is based on the requirements of Exercise 1.2 from the provided document.[span_0](end_span)
 */
public class MatrixCalculator {

    public static void main(String[] args) {
        // --- Sample Matrices ---
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] matrixC = {
            {1, 2, 3},
            {4, 5, 6}
        }; // 2x3

        int[][] matrixD = {
            {7, 8},
            {9, 10},
            {11, 12}
        }; // 3x2

        System.out.println("====== Matrix Calculator Demo ======");
        System.out.println("Matrix A:");
        printMatrix(matrixA);
        System.out.println("Matrix B:");
        printMatrix(matrixB);

        // 1. Matrix Addition
        System.out.println("--- 1. Matrix Addition (A + B) ---");
        try {
            int[][] sum = add(matrixA, matrixB);
            printMatrix(sum);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 2. Matrix Multiplication
        System.out.println("\n--- 2. Matrix Multiplication (C * D) ---");
        System.out.println("Matrix C (2x3):");
        printMatrix(matrixC);
        System.out.println("Matrix D (3x2):");
        printMatrix(matrixD);
        try {
            int[][] product = multiply(matrixC, matrixD);
            System.out.println("Result (2x2):");
            printMatrix(product);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 3. Matrix Transpose
        System.out.println("\n--- 3. Matrix Transpose (A^T) ---");
        int[][] transposed = transpose(matrixA);
        printMatrix(transposed);

        // 4. Find Max and Min in a Matrix
        System.out.println("\n--- 4. Find Max/Min in Matrix A ---");
        int max = findMax(matrixA);
        int min = findMin(matrixA);
        System.out.println("Maximum value: " + max);
        System.out.println("Minimum value: " + min);
    }

    /**
     * Adds two matrices.
     * Throws an exception if matrices have different dimensions.
     */
    public static int[][] add(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for addition.");
        }
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    /**
     * Multiplies two matrices.
     * Throws an exception if the matrices are not compatible for multiplication.
     */
    public static int[][] multiply(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int rowsB = b.length;
        int colsB = b[0].length;

        if (colsA != rowsB) {
            throw new IllegalArgumentException("Matrix A's columns must equal Matrix B's rows for multiplication.");
        }

        int[][] result = new int[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Transposes a matrix (swaps its rows and columns).
     */
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposedMatrix = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        return transposedMatrix;
    }

    /**
     * Finds the maximum value in a matrix.
     */
    public static int findMax(int[][] matrix) {
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }

    /**
     * Finds the minimum value in a matrix.
     */
    public static int findMin(int[][] matrix) {
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int value : row) {
                if (value < min) {
                    min = value;
                }
            }
        }
        return min;
    }

    /**
     * A utility method to print a matrix to the console.
     */
    public static void printMatrix(int[][] matrix) {
        if (matrix == null) {
            System.out.println("null");
            return;
        }
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}

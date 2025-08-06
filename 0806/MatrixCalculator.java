import java.util.Arrays;

public class MatrixCalculator {

    /**
     * 1. Adds two matrices of the same dimensions.
     * Returns null if dimensions do not match.
     */
    public static int[][] add(int[][] matrixA, int[][] matrixB) {
        // Check if dimensions are compatible for addition
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            System.out.println("Error: Matrices must have the same dimensions for addition.");
            return null;
        }

        int rows = matrixA.length;
        int cols = matrixA[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return result;
    }

    /**
     * 2. Multiplies two matrices.
     * The number of columns in the first matrix must equal the number of rows in the second.
     * Returns null if matrices are not compatible for multiplication.
     */
    public static int[][] multiply(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int rowsB = matrixB.length;
        int colsB = matrixB[0].length;

        // Check if dimensions are compatible for multiplication
        if (colsA != rowsB) {
            System.out.println("Error: The number of columns in the first matrix must equal the number of rows in the second.");
            return null;
        }

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return result;
    }

    /**
     * 3. Transposes a matrix (swaps rows and columns).
     */
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    /**
     * 4. Finds the maximum value in a matrix.
     */
    public static int findMax(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be empty.");
        }

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
     * 4. Finds the minimum value in a matrix.
     */
    public static int findMin(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be empty.");
        }

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

    // Helper method to print a matrix to the console
    public static void printMatrix(String title, int[][] matrix) {
        System.out.println(title);
        if (matrix == null) {
            System.out.println("null");
            return;
        }
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }


    public static void main(String[] args) {
        // Sample Matrices
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };
        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };
        int[][] matrixC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("========= Matrix Calculator Demo =========");
        printMatrix("\nMatrix A:", matrixA);
        printMatrix("\nMatrix B:", matrixB);
        printMatrix("\nMatrix C:", matrixC);
        System.out.println("------------------------------------------");

        // 1. Test Matrix Addition
        int[][] sum = add(matrixA, matrixB);
        printMatrix("\nResult of A + B:", sum);

        // 2. Test Matrix Multiplication
        int[][] product = multiply(matrixA, matrixC);
        printMatrix("\nResult of A * C:", product);

        // 3. Test Matrix Transpose
        int[][] transposedA = transpose(matrixA);
        printMatrix("\nTranspose of A:", transposedA);

        // 4. Test Find Max and Min
        System.out.println("\n------------------------------------------");
        System.out.println("Finding Max and Min in Matrix A:");
        System.out.println("Maximum value: " + findMax(matrixA));
        System.out.println("Minimum value: " + findMin(matrixA));
        System.out.println("==========================================");
    }
}

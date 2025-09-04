import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Use HashSets to track seen numbers in rows, columns, and 3x3 sub-boxes
        Set<Character> rowSet = new HashSet<>();
        Set<Character> colSet = new HashSet<>();
        Set<Character> boxSet = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            rowSet.clear();
            colSet.clear();
            boxSet.clear();

            for (int j = 0; j < 9; j++) {
                // Check row
                if (board[i][j] != '.') {
                    if (rowSet.add(board[i][j]) == false) {
                        return false; // Duplicate found in row
                    }
                }

                // Check column
                if (board[j][i] != '.') {
                    if (colSet.add(board[j][i]) == false) {
                        return false; // Duplicate found in column
                    }
                }

                // Check 3x3 sub-box
                int boxRow = 3 * (i / 3);
                int boxCol = 3 * (i % 3);
                char charInBox = board[boxRow + j / 3][boxCol + j % 3];

                if (charInBox != '.') {
                    if (boxSet.add(charInBox) == false) {
                        return false; // Duplicate found in 3x3 sub-box
                    }
                }
            }
        }

        return true; // All checks passed, the Sudoku board is valid
    }
}

// This solution cleverly uses a single main loop (indexed by 'i') to check the
// i-th row, i-th column, and i-th 3x3 box simultaneously.
// The HashSet data structure is key here, as its `add` method returns false if the
// element already exists, providing an efficient O(1) check for duplicates.
// The logic for mapping the linear index 'i' (0-8) to the top-left corner of a 3x3
// sub-box is done using integer division and modulo: `boxRow = 3 * (i / 3)` and
// `boxCol = 3 * (i % 3)`.

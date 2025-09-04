class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') { // Found an empty cell
                    for (char num = '1'; num <= '9'; num++) { // Try numbers 1-9
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num; // Place the number
                            if (solve(board)) { // Recurse
                                return true; // Solution found
                            } else {
                                board[row][col] = '.'; // Backtrack
                            }
                        }
                    }
                    return false; // No valid number for this cell
                }
            }
        }
        return true; // All cells filled, solution found
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        // Check row
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == num) {
                return false;
            }
        }
        // Check column
        for (int r = 0; r < 9; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }
        // Check 3x3 sub-box
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }
        return true; // Number is valid
    }
}

/*
1. This solution employs a recursive backtracking algorithm, a common strategy for solving constraint satisfaction problems like Sudoku.
2. The `isValid` method is a crucial helper function that checks if placing a number in a specific cell violates any of the three fundamental Sudoku rules: uniqueness in its row, column, and 3x3 sub-box.
3. The core of the backtracking is the `else { board[row][col] = '.'; }` block. If a recursive path fails to find a solution, it "backtracks" by undoing the last move and trying the next available number.
*/

import java.util.Scanner;

public class TicTacToeBoard {

    private final char[][] board;
    private char currentPlayer;
    private boolean isGameOver;
    private int movesCount;

    /**
     * 1. Initializes a 3x3 game board.
     * Sets the starting player to 'X'.
     */
    public TicTacToeBoard() {
        this.board = new char[3][3];
        this.currentPlayer = 'X';
        this.isGameOver = false;
        this.movesCount = 0;
        initializeBoard();
    }

    // Helper method to fill the board with empty spaces.
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Prints the current state of the board to the console.
     */
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    /**
     * 2. Implements placing a piece on the board.
     * Checks if the move is valid (within bounds and on an empty spot).
     * @param row The row to place the piece in (0-2).
     * @param col The column to place the piece in (0-2).
     * @return true if the move was successful, false otherwise.
     */
    public boolean placePiece(int row, int col) {
        // Check if the game is already over
        if (isGameOver) {
            System.out.println("Game is already over.");
            return false;
        }

        // Check if the coordinates are valid
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Invalid input: Position is out of bounds.");
            return false;
        }

        // Check if the spot is already taken
        if (board[row][col] != ' ') {
            System.out.println("Invalid input: This spot is already taken.");
            return false;
        }

        // Place the piece
        board[row][col] = currentPlayer;
        movesCount++;

        // 3 & 4. Check for a win or a draw, and update game state.
        if (checkWin(row, col)) {
            System.out.println("Player " + currentPlayer + " wins!");
            isGameOver = true;
        } else if (isBoardFull()) {
            System.out.println("The game is a draw!");
            isGameOver = true;
        } else {
            // Switch to the other player if the game is not over
            switchPlayer();
        }

        return true;
    }

    // Switches the current player from 'X' to 'O' and vice-versa.
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    /**
     * 3. Checks for a winning condition on the board.
     * This is an optimized check that only looks at lines related to the last move.
     * @param row The row of the last move.
     * @param col The column of the last move.
     * @return true if the current player has won, false otherwise.
     */
    private boolean checkWin(int row, int col) {
        // Check row
        if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
            return true;
        }
        // Check column
        if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
            return true;
        }
        // Check diagonals if the move was on a diagonal
        if (row == col) { // On the main diagonal
            if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
                return true;
            }
        }
        if (row + col == 2) { // On the anti-diagonal
            if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    /**
     * 4. Determines if the game is a draw by checking if the board is full.
     * @return true if all spots are taken, false otherwise.
     */
    private boolean isBoardFull() {
        return movesCount == 9;
    }

    // Public method to check game status from outside the class
    public boolean isGameOver() {
        return isGameOver;
    }
    
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    // Main method to run a playable game.
    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tic-Tac-Toe Game Started!");
        System.out.println("Player X goes first. Enter row and column (0-2).");

        while (!game.isGameOver()) {
            game.printBoard();
            System.out.println("Player " + game.getCurrentPlayer() + "'s turn.");
            System.out.print("Enter row: ");
            int row = scanner.nextInt();
            System.out.print("Enter column: ");
            int col = scanner.nextInt();

            if (!game.placePiece(row, col)) {
                // If the move was invalid, the loop will continue and prompt the same player again.
                System.out.println("Please try again.");
            }
        }
        
        System.out.println("\nFinal Board:");
        game.printBoard();
        System.out.println("Game Over.");
        
        scanner.close();
    }
}

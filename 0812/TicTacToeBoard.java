import java.util.Scanner;

/**
 * A class that represents and manages a Tic-Tac-Toe game board.
 * [span_0](start_span)This implementation is based on the requirements of Exercise 1.4 from the provided document.[span_0](end_span)
 */
public class TicTacToeBoard {

    private final char[][] board;
    private char currentPlayer;
    private static final char EMPTY_CELL = ' ';

    /**
     * [span_1](start_span)Constructor to initialize the game.[span_1](end_span)
     */
    public TicTacToeBoard() {
        board = new char[3][3];
        initializeBoard();
    }

    /**
     * Resets the board to an empty state and sets the starting player to 'X'.
     */
    public final void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
        currentPlayer = 'X';
    }

    /**
     * Prints the current board to the console.
     */
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    /**
     * [span_2](start_span)Places a piece on the board if the move is valid.[span_2](end_span)
     * @param row The row to place the piece in (0-2).
     * @param col The column to place the piece in (0-2).
     * @return true if the move was successful, false otherwise.
     */
    public boolean placePiece(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY_CELL) {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    /**
     * [span_3](start_span)Checks for a win condition on the board.[span_3](end_span)
     * @return true if the current player has won, false otherwise.
     */
    public boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the board is full, indicating a potential draw.
     * @return true if no empty cells are left, false otherwise.
     */
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Switches the current player from 'X' to 'O' and vice-versa.
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // --- Main Game Loop ---
    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();
        Scanner scanner = new Scanner(System.in);
        boolean gameEnded = false;

        System.out.println("--- Welcome to Tic-Tac-Toe! ---");
        System.out.println("Player 'X' starts. Enter row and column (0-2).");

        while (!gameEnded) {
            game.printBoard();
            System.out.println("Player " + game.currentPlayer + ", enter your move (row col):");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (game.placePiece(row, col)) {
                if (game.checkWin()) {
                    game.printBoard();
                    System.out.println("🎉 Player " + game.currentPlayer + " wins! 🎉");
                    gameEnded = true;
                } else if (game.isBoardFull()) {
                    game.printBoard();
                    System.out.println("🤝 The game is a draw! 🤝");
                    gameEnded = true;
                } else {
                    game.switchPlayer();
                }
            } else {
                System.out.println("Invalid move! That spot is taken or out of bounds. Try again.");
            }
        }
        scanner.close();
    }
}

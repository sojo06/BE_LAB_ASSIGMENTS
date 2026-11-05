package a4;
public class NQueens {

    static int N = 4;

    static void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isSafe(int[][] board, int row, int col) {

        // Check column above
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1)
                return false;
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    static boolean solveNQueens(int[][] board, int row) {

        // All queens placed successfully
        if (row == N)
            return true;

        // Try each column in the current row
        for (int col = 0; col < N; col++) {

            if (isSafe(board, row, col)) {
                board[row][col] = 1;

                if (solveNQueens(board, row + 1))
                    return true;

                // Backtrack
                board[row][col] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int[][] board = new int[N][N];

        // Place the first queen at (0, 0)
        board[0][1] = 1;

        System.out.println("Initial Board:");
        printBoard(board);

        // Solve for remaining queens, starting from row 1
        if (solveNQueens(board, 0)) {
            System.out.println("\nSolution:");
            printBoard(board);
        } else {
            System.out.println("\nNo solution exists with the first queen fixed at (0, 0).");
        }
    }
}

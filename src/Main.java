import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Knight's Tour problem with Warnsdorff's rule and backtracking

public class Main {

    private static int[][] moveCountBoard;

    private static final int BOARD_SIZE = 8;
    private static int[][] board;
    private static int moveCount = 1;

    private static int[] knightXMoves = {2, 1, -1, -2, -2, -1, 1, 2};
    private static int[] knightYMoves = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String args[]) {
        board = new int[BOARD_SIZE][BOARD_SIZE];

        moveCountBoard = new int[BOARD_SIZE][BOARD_SIZE];

        Gui gui = new Gui();

        // Wait until the user clicks to set the starting coordinates
        while (gui.getStartRow() == -1 && gui.getStartCol() == -1) {
            try {
                Thread.sleep(100); // Sleep to avoid busy waiting
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int startRow = gui.getStartRow();
        int startCol = gui.getStartCol();

        solveKnightTour(startRow, startCol, gui);
        printBoard();
    }

    private static void solveKnightTour(int row, int col, Gui gui) {
        board[row][col] = moveCount;
        moveCountBoard[row][col] = 0; // Mark the current square as visited
        gui.updateBoardGUI(board);

        if (moveCount == BOARD_SIZE * BOARD_SIZE) {
            return; // Knight's Tour is complete
        }

        List<Move> nextMoves = getNextMoves(row, col); // Use Warnsdorff's rule

        for (Move move : nextMoves) {
            int newRow = move.row;
            int newCol = move.col;
            moveCount++;

            try {
                Thread.sleep(100); // Delay between iterations to help with visualization
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            solveKnightTour(newRow, newCol, gui);

            if (moveCount != BOARD_SIZE * BOARD_SIZE) {
                moveCount--;
                board[newRow][newCol] = 0; // Backtrack
            }
            else {
                break;
            }
        }
    }

    private static List<Move> getNextMoves(int row, int col) {
        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int newRow = row + knightXMoves[i];
            int newCol = col + knightYMoves[i];

            if (isValidMove(newRow, newCol)) {
                int numAvailableMoves = countAvailableMoves(newRow, newCol);
                moves.add(new Move(newRow, newCol, numAvailableMoves));
            }
        }

        Collections.sort(moves); // sort based on available moves (Warnsdorff's rule)
        return moves;
    }

    private static int countAvailableMoves(int row, int col) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int newRow = row + knightXMoves[i];
            int newCol = col + knightYMoves[i];

            if (isValidMove(newRow, newCol)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == 0);
    }

    private static void printBoard() {
        for (int[] row : board) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

    }

    private static class Move implements Comparable<Move> {
        int row;
        int col;
        int availableMoves;

        Move(int row, int col, int availableMoves) {
            this.row = row;
            this.col = col;
            this.availableMoves = availableMoves;
        }
        public int compareTo(Move other) {
            return Integer.compare(this.availableMoves, other.availableMoves);
        }
    }   
}
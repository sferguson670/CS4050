/*
 * Sarah Ferguson
 * CS4050 - Assignment 3
 * Glue k random queens to the board and place other 8-k queens using backtracking
 * What value of k gives the best result (on average)?
 * How does the running time compare to traditional backtracking algorithm?
 */

import java.util.Random;

public class EightQueens {
    int[][] chessboard = new int[8][8];
    int k_queens = 1;
    int bt_queens = 8 - k_queens;

    /*
     * Returns a randomized int between 0 - 8
     */
    private int getRandomNum() {
        Random num = new Random();
        return num.nextInt(8);
    }

    /*
     * Column by column, glues a queen in a random row,
     * checks if it is in a valid spot first,
     * once validated, puts a 1 to mark a queen is there
     */
    private void glueRandomQueens(int k) {
        int row;
        int column = 0;
        for (int i = 0; i < k; i++) {
            do {
                row = getRandomNum();
                column = i;
            } while (!validateQueen(row, column));
            chessboard[row][column] = 1;
        }
    }

    /*
     * After randomly generating all queens,
     * if board leads to impossible state,
     * board is erased and places random queens again
     */
    private void checkRandomlyPlacedQueens(int k) {
        // k represents last column
        if ( k <= 8 && !isBoardPossible(k)) {
            // clears the board
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    chessboard[r][c] = 0;
                }
            }
            glueRandomQueens(k);
        }
    }

    /*
     * When randomly placed queens are finished,
     * check if the board is in a possible state,
     * goes through column we are on,
     * and checks if any of its rows are safe to place another queen
     * returns FALSE is board is in impossible state
     */
    private boolean isBoardPossible(int column) {
        for (int r = 0; r < 8; r++) {
            if (!validateQueen(r, column)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Recursively checks and backtracks to find where to place the
     * back track queens, begins from specified row and checks each column
     */
    private boolean checkBackTrackQueens(int numOfQueens, int row) {
        if (row == numOfQueens) {
            return true;
        } else {
            for (int c = 0; c < numOfQueens; c++) {
                chessboard[row][c] = 1;
                if (validateQueen(row, c)) {
                    checkBackTrackQueens(numOfQueens, row + 1);
                }
                chessboard[row][c] = 0;
            }
        }
        return false;
    }

    /*
     * Checks just placed queen and checks if its in valid queen space,
     * returns TRUE if in VALID space
     * return FALSE if in INVALID space
     */
    private boolean validateQueen(int r, int c) {
        return ( checkRow(r,c) && checkColumn(r,c)
                && checkUpLeftDiagonal(r,c) && checkDownRightDiagonal(r,c)
                && checkDownLeftDiagonal(r,c) && checkUpRightDiagonal(r,c) );
    }

    /*
     * Looks at the same spot as queen piece,
     * has to be empty, no duplicates
     * returns TRUE if VALID queen space
     */
    private boolean checkSameSpot(int row, int column) {
        return chessboard[row][column] != 1;
    }

    /*
     * Looks at the same row as queen piece,
     * returns TRUE if VALID queen space
     */
    private boolean checkRow(int row, int column) {
        for (int c = 0; c < 8; c++) {
            if (c != column) {
                if (chessboard[row][c] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Looks at the same column as queen piece,
     * returns TRUE if VALID queen space
     */
    private boolean checkColumn(int row, int column) {
        for (int r = 0; r < 8; r++) {
            if (r != row) {
                if (chessboard[r][column] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Looks at the same diagonal (up left) as queen piece,
     * returns TRUE if VALID queen space
     */
    private boolean checkUpLeftDiagonal(int row, int column) {
        int r = row - 1;
        int c = column - 1;
        while (r >= 0 && c >= 0) {
            if (chessboard[r][c] == 1) {
                return false;
            }
            r -= 1;
            c -= 1;
        }
        return true;
    }

    /*
     * Looks at the same diagonal (down right) as queen piece,
     * returns TRUE if VALID queen space
     */
    private boolean checkDownRightDiagonal(int row, int column) {
        int r = row + 1;
        int c = column + 1;
        while (r < 8 && c < 8) {
            if (chessboard[r][c] == 1) {
                return false;
            }
            r += 1;
            c += 1;
        }
        return true;
    }

    /*
     * Looks at the same diagonal (down left) as queen piece,
     * returns TRUE if VALID queen space
     */
    private boolean checkDownLeftDiagonal(int row, int column) {
        int r = row + 1;
        int c = column - 1;
        while (r < 8 && c >= 0) {
            if (chessboard[r][c] == 1) {
                return false;
            }
            r += 1;
            c -= 1;
        }
        return true;
    }

    /*
     * Looks at the same diagonal (up right) as queen piece,
     * returns TRUE if VALID queen space
     */
    private boolean checkUpRightDiagonal(int row, int column) {
        int r = row - 1;
        int c = column + 1;
        while (r >= 0 && c < 8) {
            if (chessboard[r][c] == 1) {
                return false;
            }
            r -= 1;
            c += 1;
        }
        return true;
    }

    /*
     * Prints out the board, 1's are where queens are placed
     */
    private void printOutBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(chessboard[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        EightQueens runner = new EightQueens();
        runner.glueRandomQueens(5);
        runner.printOutBoard();
        //runner.checkBackTrackQueens(8, 0);
    }
}

/*
 * References used:
 * https://en.wikipedia.org/wiki/Queen_(chess)
 * https://www.programiz.com/java-programming/multidimensional-array
 * https://www.tutorialgateway.org/two-dimensional-array-in-java/
 */
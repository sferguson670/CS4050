/*
 * Sarah Ferguson
 * CS4050 - Assignment 3
 * Glue k random queens to the board and place other 8-k queens using backtracking
 * What value of k gives the best result (on average)?
 * How does the running time compare to traditional backtracking algorithm?
 */

import java.util.Random;

public class EightQueens {
    private int[][] chessboard = new int[8][8];

    private static int k_queens = 7;
    private static int bt_queens = 8 - k_queens;

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
        for (int column = 0; column < k; column++) {
            do {
                row = getRandomNum();
            } while (!validateQueen(row, column));
            chessboard[row][column] = 1;
        }
        // after filling up the board, check if solvable, reset if so
        for (int column = k; column >= 0; column--) {
            checkBackTrackQueens(8, column);
        }
    }

    /*
     * Clears the board
     */
    private void clearBoard() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                chessboard[r][c] = 0;
            }
        }
    }

    /*
     * Recursively checks and backtracks to find where to place the
     * back track queens, begins from specified column and checks each row
     */
    private boolean checkBackTrackQueens(int numOfQueens, int column) {
        if (column == numOfQueens) {
            printOutBoard();
        } else {
            for (int r = 0; r < numOfQueens; r++) {
                chessboard[r][column] = 1;
                if (validateQueen(r, column)) {
                    checkBackTrackQueens(numOfQueens, column + 1);
                }
                chessboard[r][column] = 0;
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
        //runner.checkBackTrackQueens(8, 5);
        //runner.glueRandomQueens(7);
        //runner.printOutBoard();
    }
}

/*
 * References used:
 * https://en.wikipedia.org/wiki/Queen_(chess)
 * https://www.programiz.com/java-programming/multidimensional-array
 * https://www.tutorialgateway.org/two-dimensional-array-in-java/
 */
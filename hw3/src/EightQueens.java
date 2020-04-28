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
     * Finds a random spot in the board, glues a queen here
     * by marking this spot in 2d array as a 1,
     * checks if it is in a valid spot first
     */
    private void glueRandomQueens(int k) {
        int row;
        int column;
        for (int i = 0; i < k; i++) {
            do {
                row = getRandomNum();
                column = getRandomNum();
            } while (!validateQueen(row, column));
            chessboard[row][column] = 1;
        }
    }

    /*
     * Recursively checks and backtracks to find where to place the
     * back track queens, begins from first row and checks each column
     */
    private boolean checkBackTrackQueens(int queens, int row) {

        return false;
    }

    /*
     * Checks just placed queen and checks if its in valid queen space,
     * returns TRUE if in VALID space
     * return FALSE if in INVALID space
     */
    private boolean validateQueen(int r, int c) {
        return ( checkRow(r,c) && checkColumn(r,c) && checkUpLeftDiagonal(r,c) && checkDownRightDiagonal(r,c)
                && checkDownLeftDiagonal(r,c) && checkUpRightDiagonal(r,c) );
    }

    /*
     * Looks at the same row as just placed queen piece,
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
     * Looks at the same column as just placed queen piece,
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
     * Looks at the same diagonal (up left) as just placed queen piece,
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
     * Looks at the same diagonal (down right) as just placed queen piece,
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
     * Looks at the same diagonal (down left) as just placed queen piece,
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
     * Looks at the same diagonal (up right) as just placed queen piece,
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
    }
}

/*
 * References used:
 * https://en.wikipedia.org/wiki/Queen_(chess)
 * https://www.programiz.com/java-programming/multidimensional-array
 */
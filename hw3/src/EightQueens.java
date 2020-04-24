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
        int x;
        int y;
        for (int i = 0; i < k; i++) {
            do {
                x = getRandomNum();
                y = getRandomNum();
            } while (!validateQueen(x, y));
            chessboard[y][x] = 1;
            printOutBoard();
            System.out.println(validateQueen(x, y));
        }
    }

    /*
     * Recursively checks and backtracks to find where to place the
     * back track queens
     */
    private boolean checkBackTrackQueens(int queens, int column) {
        if (column >= queens)
            return true;

        for (int i = 0; i < queens; i++) {
            if (validateQueen(i, column)) {
                chessboard[i][column] = 1;

                if (checkBackTrackQueens(queens, column + 1))
                    return true;

                chessboard[i][column] = 0;
            }
        }
        return false;
    }

    /*
     * Checks just placed queen and checks if its in valid queen space,
     * returns true if in valid space
     * return false if in invalid space
     */
    private boolean validateQueen(int x, int y) {
        return !( checkRow(x,y) || checkColumn(x,y)
                || checkUpLeftDiagonal(x,y) || checkDownRightDiagonal(x,y)
                || checkDownLeftDiagonal(x,y) || checkUpRightDiagonal(x,y) );
    }

    /*
     * Checks if another queen exists in the same spot,
     * returns true if invalid queen space
     */
    private boolean checkSameSpot(int x, int y) {
        return chessboard[y][x] == 1;
    }

    /*
     * Looks at the same row as just placed queen piece,
     * returns true if invalid queen space
     */
    private boolean checkRow(int x, int y) {
        for (int i = 0; i < 8; i++) {
            if (i != x) {
                if (chessboard[y][i] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Looks at the same column as just placed queen piece,
     * returns true if invalid queen space
     */
    private boolean checkColumn(int x, int y) {
        for (int i = 0; i < 8; i++) {
            if (i != y) {
                if (chessboard[i][x] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Looks at the same diagonal (up left) as just placed queen piece,
     * returns true if invalid queen space
     */
    private boolean checkUpLeftDiagonal(int x, int y) {
        int column = x - 1;
        int row = y - 1;
        while (column >= 0 && row >=0) {
            if (chessboard[row][column] == 1) {
                return true;
            }
            column -= 1;
            row -= 1;
        }
        return false;
    }

    /*
     * Looks at the same diagonal (down right) as just placed queen piece,
     * returns true if invalid queen space
     */
    private boolean checkDownRightDiagonal(int x, int y) {
        int column = x + 1;
        int row = y + 1;
        while (column < 8 && row < 8) {
            if (chessboard[row][column] == 1) {
                return true;
            }
            column += 1;
            row += 1;
        }
        return false;
    }

    /*
     * Looks at the same diagonal (down left) as just placed queen piece,
     * returns true if invalid queen space
     */
    private boolean checkDownLeftDiagonal(int x, int y) {
        int column = x - 1;
        int row = y + 1;
        while (column >= 0 && row < 8) {
            if (chessboard[row][column] == 1) {
                return true;
            }
            column -= 1;
            row += 1;
        }
        return false;
    }

    /*
     * Looks at the same diagonal (up right) as just placed queen piece,
     * returns true if invalid queen space
     */
    private boolean checkUpRightDiagonal(int x, int y) {
        int column = x + 1;
        int row = y + 1;
        while (column < 8 && row < 8) {
            if (chessboard[row][column] == 1) {
                return true;
            }
            column += 1;
            row += 1;
        }
        return false;
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
        runner.checkBackTrackQueens(8, 0);
        System.out.println("***********");
        runner.printOutBoard();
    }
}

/*
 * References used:
 * https://en.wikipedia.org/wiki/Queen_(chess)
 * https://www.programiz.com/java-programming/multidimensional-array
 * https://iq.opengenus.org/8-queens-problem-backtracking/
 * https://www.geeksforgeeks.org/java-program-for-n-queen-problem-backtracking-3/
 */
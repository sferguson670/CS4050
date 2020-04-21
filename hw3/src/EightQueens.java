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
    int backtrack_queens = 8 - k_queens;

    /*
     * Returns a randomized int between 0 - 8
     */
    private int getRandomNum() {
        Random num = new Random();
        return num.nextInt(8);
    }

    /*
     * Finds a random spot in the board, glues a queen here
     * by marking this spot in 2d array as a 1
     */
    private void glueRandomQueens(int k) {
        for (int i = 0; i < k; i++) {
            int x = getRandomNum();
            int y = getRandomNum();
            chessboard[y][x] = 1;
            printOutBoard();
            System.out.println(validateQueen(x, y));
        }
    }

    /*
     * Checks just placed queen and checks if its in valid queen space,
     * returns true if in valid space
     * return false if in invalid space
     */
    private boolean validateQueen(int x, int y) {
        return !( checkRow(x,y) || checkColumn(x,y) || checkUpLeftDiagonal(x,y)
        || checkDownRightDiagonal(x,y) || checkDownLeftDiagonal(x,y)
        || checkUpRightDiagonal(x,y) );
    }

    /*
     * Looks at the same row as just placed queen piece,
     * returns true if invalid queen space
     */
    private boolean checkRow(int x, int y) {
        boolean repeat = false;
        for (int i = 0; i < 8; i++) {
            if (i == x) {

            } else {
                if (chessboard[y][i] == 1) {
                    repeat = true;
                    break;
                }
            }
        }
        return repeat;
    }

    /*
     * Looks at the same column as just placed queen piece,
     * returns true if invalid queen space
     */
    private boolean checkColumn(int x, int y) {
        boolean repeat = false;
        for (int i = 0; i < 8; i++) {
            if (i == y) {

            } else {
                if (chessboard[i][x] == 1) {
                    repeat = true;
                    break;
                }
            }
        }
        return repeat;
    }

    /*
     * Looks at the same diagonal (up left) as just placed queen piece,
     * returns true if invalid queen space
     */
    private boolean checkUpLeftDiagonal(int x, int y) {
        boolean repeat = false;
        int column = x - 1;
        int row = y - 1;
        while (column >= 0 && row >=0) {
            if (chessboard[row][column] == 1) {
                repeat = true;
                break;
            }
            column -= 1;
            row -= 1;
        }
        return repeat;
    }

    /*
     * Looks at the same diagonal (down right) as just placed queen piece,
     * returns true if invalid queen space
     */
    private boolean checkDownRightDiagonal(int x, int y) {
        boolean repeat = false;
        int column = x + 1;
        int row = y + 1;
        while (column < 8 && row < 8) {
            if (chessboard[row][column] == 1) {
                repeat = true;
                break;
            }
            column += 1;
            row += 1;
        }
        return repeat;
    }

    /*
     * Looks at the same diagonal (down left) as just placed queen piece,
     * returns true if invalid queen space
     */
    private boolean checkDownLeftDiagonal(int x, int y) {
        boolean repeat = false;
        int column = x - 1;
        int row = y + 1;
        while (column >= 0 && row < 8) {
            if (chessboard[row][column] == 1) {
                repeat = true;
                break;
            }
            column -= 1;
            row += 1;
        }
        return repeat;
    }

    /*
     * Looks at the same diagonal (up right) as just placed queen piece,
     * returns true if invalid queen space
     */
    private boolean checkUpRightDiagonal(int x, int y) {
        boolean repeat = false;
        int column = x + 1;
        int row = y + 1;
        while (column < 8 && row < 8) {
            if (chessboard[row][column] == 1) {
                repeat = true;
                break;
            }
            column += 1;
            row += 1;
        }
        return repeat;
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
        runner.glueRandomQueens(3);
    }
}

/*
 * References used:
 * https://en.wikipedia.org/wiki/Queen_(chess)
 * https://www.programiz.com/java-programming/multidimensional-array
 */
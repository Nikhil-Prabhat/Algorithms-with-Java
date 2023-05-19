package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *  This Algorithm uses first filling of rows for a queen
 * */
public class NQueensProblem {

    private static int CHESS_BOARD_SIZE = 4;

    public static void main(String[] args) {
        List<List<String>> chessBoardWithQueens = new ArrayList<>();
        List<Character[]> chessBoard = new ArrayList<>();

        // Fill the chessboard with initial '.' while initialisation
        for (int index = 0; index < CHESS_BOARD_SIZE; index++) {
            Character[] eachRowOfChessBoard = new Character[CHESS_BOARD_SIZE];
            Arrays.fill(eachRowOfChessBoard, '*');
            chessBoard.add(eachRowOfChessBoard);
        }

        solveNQueens(0, chessBoard, chessBoardWithQueens, CHESS_BOARD_SIZE);
        printNQueens(chessBoardWithQueens);

    }

    private static void solveNQueens(int column, List<Character[]> chessBoard, List<List<String>> chessBoardWithQueens, int chessBoardSize) {

        if (column == chessBoardSize) {
            List<String> eachRowOfChessBoard = new ArrayList<>();
            for (Character[] row : chessBoard) {
                String eachColumnOfChessBoard = "";
                for (int i = 0; i < row.length; i++)
                    eachColumnOfChessBoard += row[i];

                eachRowOfChessBoard.add(eachColumnOfChessBoard);
            }

            chessBoardWithQueens.add(eachRowOfChessBoard);
            return;
        }

        // First, we are placing the queen in the first row, first column
        // Each row is iterated over here for each combination of the queen
        for (int row = 0; row < chessBoardSize; row++) {
            if (canQueenBePlaced(row, column, chessBoard, chessBoardSize)) {
                // Update the Queen in the chessBoard and iterate for the next column
                // Once the next column is iterated, then omit the previous change so as to backtrack
                //chessBoard.get(row).set(column, 'Q');
                chessBoard.get(row)[column] = 'Q';
                solveNQueens(column + 1, chessBoard, chessBoardWithQueens, chessBoardSize);
                chessBoard.get(row)[column] = '*';

            }
        }
    }

    private static boolean canQueenBePlaced(int row, int column, List<Character[]> chessBoard, int chessBoardSize) {
        /*
         *  Since all the queens are placed towards left only, so we don't need to check for any condition towards right, upside or downward
         * */

        // Check left upper diagonal
        int tempRow = row;
        int tempColumn = column;

        while (tempRow >= 0 && tempColumn >= 0) {
            if (Character.compare(chessBoard.get(tempRow)[tempColumn], 'Q') == 0)
                return false;
            tempRow--;
            tempColumn--;
        }

        // Check Left
        tempRow = row;
        tempColumn = column;

        while (tempColumn >= 0) {
            if (Character.compare(chessBoard.get(tempRow)[tempColumn], 'Q') == 0)
                return false;
            tempColumn--;
        }

        // Check left lower diagonal
        tempRow = row;
        tempColumn = column;

        while (tempRow < chessBoardSize && tempColumn >= 0) {
            if (Character.compare(chessBoard.get(tempRow)[tempColumn], 'Q') == 0)
                return false;
            tempRow++;
            tempColumn--;
        }

        return true;
    }

    private static void printNQueens(List<List<String>> chessBoardWithQueens)
    {
        for(List<String> eachQueenSet : chessBoardWithQueens)
        {
            String finalString = "";
            for(String eachRowOfEachQueenSet : eachQueenSet){
                finalString += eachRowOfEachQueenSet;
                finalString += '\n';
            }

            System.out.println(finalString);
        }
    }
}

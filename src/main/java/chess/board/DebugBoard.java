package chess.board;

import chess.moves.PossibleMoves;

import java.util.Arrays;

// Credit: Logic Crazy Chess
public class DebugBoard {

    public static void main(String[] args) {
        long wp = 0L, wn = 0L, wb = 0L, wr = 0L, wq = 0L, wk = 0L, bp = 0L, bn = 0L, bb = 0L, br = 0L, bq = 0L, bk = 0L;
        String[][] stringBoard = get2dStringBoard();
        long[] boardArray = arrayToBitboards(stringBoard);
        drawArray(boardArray);

        Position position = Position.startingPosition();
        System.out.println(position);
    }

    public static String[][] get2dStringBoard() {
        // edit this method to change the starting board
        return new String[][]{
                {"r", "n", "b", "q", "k", "b", "n", "r"},
                {"p", "p", "p", "p", "p", "p", "p", "p"},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {"P", "P", "P", "P", "P", "P", "P", "P"},
                {"R", "N", "B", "Q", "K", "B", "N", "R"}};
    }

    public static void drawArray(long[] boardArray) {
        drawBoard(boardArray[0], boardArray[1], boardArray[2], boardArray[3], boardArray[4], boardArray[5],
                boardArray[6], boardArray[7], boardArray[8], boardArray[9], boardArray[10], boardArray[11]);
    }

    /**
     * Draws the board on terminal.
     * Mostly for debugging purposes, but can be used for manual testing.
     * Takes in bitboards for all pieces and prints them out as a chess board.
     */
    public static void drawBoard(long wp, long wn, long wb, long wr, long wq, long wk,
                                 long bp, long bn, long bb, long br, long bq, long bk) {
        /*
         * Convert the bitboards to a 2D array of 1-char strings.
         * Add pieces using bit comparisons.
         * And print it to the terminal.
         */
        String[][] chessBoard = new String[8][8];
        for (int i = 0; i < 64; i++) {
            chessBoard[i / 8][i % 8] = " ";
        }
        for (int i = 0; i < 64; i++) {
            if (((wp >> i) & 1) == 1) chessBoard[i / 8][i % 8] = "P";
            if (((wn >> i) & 1) == 1) chessBoard[i / 8][i % 8] = "N";
            if (((wb >> i) & 1) == 1) chessBoard[i / 8][i % 8] = "B";
            if (((wr >> i) & 1) == 1) chessBoard[i / 8][i % 8] = "R";
            if (((wq >> i) & 1) == 1) chessBoard[i / 8][i % 8] = "Q";
            if (((wk >> i) & 1) == 1) chessBoard[i / 8][i % 8] = "K";
            if (((bp >> i) & 1) == 1) chessBoard[i / 8][i % 8] = "p";
            if (((bn >> i) & 1) == 1) chessBoard[i / 8][i % 8] = "n";
            if (((bb >> i) & 1) == 1) chessBoard[i / 8][i % 8] = "b";
            if (((br >> i) & 1) == 1) chessBoard[i / 8][i % 8] = "r";
            if (((bq >> i) & 1) == 1) chessBoard[i / 8][i % 8] = "q";
            if (((bk >> i) & 1) == 1) chessBoard[i / 8][i % 8] = "k";
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
    }

    public static long[] arrayToBitboards(String[][] chessBoard) {
        long wp = 0L, wn = 0L, wb = 0L, wr = 0L, wq = 0L, wk = 0L,
                bp = 0L, bn = 0L, bb = 0L, br = 0L, bq = 0L, bk = 0L;
        String binaryString;
        for (int i = 0; i < 64; i++) {
            binaryString = "0000000000000000000000000000000000000000000000000000000000000000";
            binaryString = binaryString.substring(i + 1) + "1" + binaryString.substring(0, i);
            switch (chessBoard[i / 8][i % 8]) {
                case "P" -> wp += convertStringToBitboard(binaryString);
                case "N" -> wn += convertStringToBitboard(binaryString);
                case "B" -> wb += convertStringToBitboard(binaryString);
                case "R" -> wr += convertStringToBitboard(binaryString);
                case "Q" -> wq += convertStringToBitboard(binaryString);
                case "K" -> wk += convertStringToBitboard(binaryString);
                case "p" -> bp += convertStringToBitboard(binaryString);
                case "n" -> bn += convertStringToBitboard(binaryString);
                case "b" -> bb += convertStringToBitboard(binaryString);
                case "r" -> br += convertStringToBitboard(binaryString);
                case "q" -> bq += convertStringToBitboard(binaryString);
                case "k" -> bk += convertStringToBitboard(binaryString);
            }
        }
        return new long[]{wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk};
    }

    public static long convertStringToBitboard(String binary) {
        if (binary.charAt(0) == '0') {
            //not going to be a negative number
            return Long.parseLong(binary, 2);
        } else {
            return Long.parseLong("1" + binary.substring(2), 2) * 2;
        }
    }
}

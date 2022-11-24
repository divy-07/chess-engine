package chess.board;

import chess.moves.PossibleMoves;

// Credit: Logic Crazy Chess
public class DebugBoard {

    public static void main(String[] args) {
        long wp = 0L, wn = 0L, wb = 0L, wr = 0L, wq = 0L, wk = 0L, bp = 0L, bn = 0L, bb = 0L, br = 0L, bq = 0L, bk = 0L;
        String[][] stringBoard = get2dStringBoard();
        long[] boardArray = arrayToBitboards(stringBoard);
        drayArray(boardArray);

        String possMoves = PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        System.out.println("WP moves:" + possMoves);
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

    public static void drayArray(long[] boardArray) {
        Board.drawBoard(boardArray[0], boardArray[1], boardArray[2], boardArray[3], boardArray[4], boardArray[5],
                boardArray[6], boardArray[7], boardArray[8], boardArray[9], boardArray[10], boardArray[11]);
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

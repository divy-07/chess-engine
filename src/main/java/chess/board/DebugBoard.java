package chess.board;

import chess.moves.MoveGeneration;

// Credit: Logic Crazy Chess
public class DebugBoard {

    public static void main(String[] args) {
        long WP=0L,WN=0L,WB=0L,WR=0L,WQ=0L,WK=0L,BP=0L,BN=0L,BB=0L,BR=0L,BQ=0L,BK=0L;
        String[][] stringBoard = get2dStringBoard();
        long[] boardArray = arrayToBitboards(stringBoard, WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);
        drayArray(boardArray);

        String possMoves = MoveGeneration.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3], boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9], boardArray[10], boardArray[11], 0L, false, false, false, false);
        System.out.println("WP moves:" + possMoves);
    }

    // edit this method to change the starting board
    public static String[][] get2dStringBoard() {
        String chessBoard[][]={
                {"r","n","b","q","k","b","n","r"},
                {"p","p","p","p","p","p","p","p"},
                {" "," "," "," "," "," "," "," "},
                {" "," "," "," "," "," "," "," "},
                {" "," "," "," "," "," "," "," "},
                {" "," "," "," "," "," "," "," "},
                {"P","P","P","P","P","P","P","P"},
                {"R","N","B","Q","K","B","N","R"}};
        return chessBoard;
    }

    public static void drayArray(long[] boardArray) {
        Board.drawBoard(boardArray[0], boardArray[1], boardArray[2], boardArray[3], boardArray[4], boardArray[5],
                boardArray[6], boardArray[7], boardArray[8], boardArray[9], boardArray[10], boardArray[11]);
    }

    public static long[] arrayToBitboards(String[][] chessBoard,long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK) {
        String Binary;
        for (int i=0;i<64;i++) {
            Binary="0000000000000000000000000000000000000000000000000000000000000000";
            Binary=Binary.substring(i+1)+"1"+Binary.substring(0, i);
            switch (chessBoard[i/8][i%8]) {
                case "P": WP+=convertStringToBitboard(Binary);
                    break;
                case "N": WN+=convertStringToBitboard(Binary);
                    break;
                case "B": WB+=convertStringToBitboard(Binary);
                    break;
                case "R": WR+=convertStringToBitboard(Binary);
                    break;
                case "Q": WQ+=convertStringToBitboard(Binary);
                    break;
                case "K": WK+=convertStringToBitboard(Binary);
                    break;
                case "p": BP+=convertStringToBitboard(Binary);
                    break;
                case "n": BN+=convertStringToBitboard(Binary);
                    break;
                case "b": BB+=convertStringToBitboard(Binary);
                    break;
                case "r": BR+=convertStringToBitboard(Binary);
                    break;
                case "q": BQ+=convertStringToBitboard(Binary);
                    break;
                case "k": BK+=convertStringToBitboard(Binary);
                    break;
            }
        }
        return new long[]{WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK};
    }

    public static long convertStringToBitboard(String Binary) {
        if (Binary.charAt(0)=='0') {//not going to be a negative number
            return Long.parseLong(Binary, 2);
        } else {
            return Long.parseLong("1"+Binary.substring(2), 2)*2;
        }
    }
}

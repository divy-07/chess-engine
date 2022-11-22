package chess.board;

import java.util.*;

public class Board {
    /*
     * UPPERCASE = WHITE, lowercase = black
     * pawn --> P/p
     * knight --> N/n
     * bishop --> B/b
     * rook --> R/r
     * Queen --> Q/q
     * King --> K/k
     */

    // starting position bitboards
    public static long startWP = 71776119061217280L;
    public static long startWN = 4755801206503243776L;
    public static long startWB = 2594073385365405696L;
    public static long startWR = -9151314442816847872L;
    public static long startWQ = 576460752303423488L;
    public static long startWK = 1152921504606846976L;
    public static long startBP = 65280L;
    public static long startBN = 66L;
    public static long startBB = 36L;
    public static long startBR = 129L;
    public static long startBQ = 8L;
    public static long startBK = 16L;

    /**
     * Updates the engine with the new board state
     *
     * @param fenString the new board state in FEN notation
     *                  (<a href="https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation">format specifications</a>)
     *                  // TODO: add impacts on the engine/frame conditions
     */
    public static void importFEN(String fenString) {
        return; // TODO: implement this method
    }

    /**
     * Draws the board on terminal.
     * Mostly for debugging purposes, but can be used for manual testing.
     *
     * @param wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk bitboards of the chess pieces.
     *            Must be valid bitboards.
     */
    public static void drawBoard(long wp, long wn, long wb, long wr, long wq, long wk, long bp, long bn, long bb, long br, long bq, long bk) {
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
}

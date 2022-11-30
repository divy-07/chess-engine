package chess.board;

import chess.engine.Hari;

import static chess.Constants.*;

import java.util.Arrays;

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
    /*
     * Bitboards:
     * the first bit of the long is the h1 square and last is the a8 square.
     * So the long 64b0000000000000000000000000000000000000000000000000000000000000001
     * would correspond to the board:
     *          a     b     c     d     e     f     g     h
     *       |-----|-----|-----|-----|-----|-----|-----|-----|
     *    8  |  P  |     |     |     |     |     |     |     |
     *       |-----|-----|-----|-----|-----|-----|-----|-----|
     *    7  |     |     |     |     |     |     |     |     |
     *       |-----|-----|-----|-----|-----|-----|-----|-----|
     *    6  |     |     |     |     |     |     |     |     |
     *       |-----|-----|-----|-----|-----|-----|-----|-----|
     *    5  |     |     |     |     |     |     |     |     |
     *       |-----|-----|-----|-----|-----|-----|-----|-----|
     *    4  |     |     |     |     |     |     |     |     |
     *       |-----|-----|-----|-----|-----|-----|-----|-----|
     *    3  |     |     |     |     |     |     |     |     |
     *       |-----|-----|-----|-----|-----|-----|-----|-----|
     *    2  |     |     |     |     |     |     |     |     |
     *       |-----|-----|-----|-----|-----|-----|-----|-----|
     *    1  |     |     |     |     |     |     |     |     |
     *       |-----|-----|-----|-----|-----|-----|-----|-----|
     */

    /**
     * Updates the engine with the new board state
     *
     * @param fenString the new board state in FEN notation
     *                  (<a href="https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation">
     *                  format specifications</a>)<p>
     *                  Changes the state of the engine to the new given board state.
     */
    public static void importFEN(String fenString) {
        Hari.WP = 0L;
        Hari.WN = 0L;
        Hari.WB = 0L;
        Hari.WR = 0L;
        Hari.WQ = 0L;
        Hari.WK = 0L;
        Hari.BP = 0L;
        Hari.BN = 0L;
        Hari.BB = 0L;
        Hari.BR = 0L;
        Hari.BQ = 0L;
        Hari.BK = 0L;
        Hari.CWK = false;
        Hari.CWQ = false;
        Hari.CBK = false;
        Hari.CBQ = false;

        int charIndex = 0;
        int boardIndex = 0;

        // parse the board state
        while (fenString.charAt(charIndex) != ' ') {
            switch (fenString.charAt(charIndex++)) {
                case 'P' -> Hari.WP |= (1L << boardIndex++);
                case 'p' -> Hari.BP |= (1L << boardIndex++);
                case 'N' -> Hari.WN |= (1L << boardIndex++);
                case 'n' -> Hari.BN |= (1L << boardIndex++);
                case 'B' -> Hari.WB |= (1L << boardIndex++);
                case 'b' -> Hari.BB |= (1L << boardIndex++);
                case 'R' -> Hari.WR |= (1L << boardIndex++);
                case 'r' -> Hari.BR |= (1L << boardIndex++);
                case 'Q' -> Hari.WQ |= (1L << boardIndex++);
                case 'q' -> Hari.BQ |= (1L << boardIndex++);
                case 'K' -> Hari.WK |= (1L << boardIndex++);
                case 'k' -> Hari.BK |= (1L << boardIndex++);
                case '1' -> boardIndex++;
                case '2' -> boardIndex += 2;
                case '3' -> boardIndex += 3;
                case '4' -> boardIndex += 4;
                case '5' -> boardIndex += 5;
                case '6' -> boardIndex += 6;
                case '7' -> boardIndex += 7;
                case '8' -> boardIndex += 8;
            }
        }

        // decide whose turn it is
        Hari.whiteToMove = (fenString.charAt(++charIndex) == 'w');

        // set castling rights
        charIndex += 2;
        while (fenString.charAt(charIndex) != ' ') {
            switch (fenString.charAt(charIndex++)) {
                case 'K' -> Hari.CWK = true;
                case 'Q' -> Hari.CWQ = true;
                case 'k' -> Hari.CBK = true;
                case 'q' -> Hari.CBQ = true;
            }
        }

        // set en passant square
        if (fenString.charAt(++charIndex) != '-') {
            Hari.EP = files[fenString.charAt(charIndex) - 'a'];
        }

        // half-move
        charIndex += 3;
        Hari.halfMoveCount = Integer.parseInt(fenString.substring(charIndex, fenString.indexOf(' ', charIndex)));

        // full move
        charIndex = fenString.indexOf(' ', charIndex) + 1;
        Hari.fullMoveCount = Integer.parseInt(fenString.substring(charIndex));
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

package chess.moves;

import static chess.Constants.*;

public class PossibleMoves {

    // useful trackers
    private static long notMyPieces;
    private static long myPieces;
    private static long occupiedSquares;
    private static long emptySquares;

    /**
     * Returns a string of all possible moves by white pieces
     *
     * @param wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk bitboards of all pieces
     * @param ep  bitboard of en passant square
     * @param cwk true if castle white king side available
     * @param cwq true if castle white queen side available
     * @param cbk true if castle black king side available
     * @param cbq true if castle black queen side available
     * @return string of all possible moves by white pieces
     * The order of moves is: pawn, knight, bishop, rook, queen, king, castle
     * Each move is 4 characters long, so the length of the string is a multiple of 4
     */
    public static String possibleMovesW(long wp, long wn, long wb, long wr, long wq, long wk,
                                        long bp, long bn, long bb, long br, long bq, long bk,
                                        long ep, boolean cwk, boolean cwq, boolean cbk, boolean cbq) {
        notMyPieces = ~(wp | wn | wb | wr | wq | wk | bk); //added bk to avoid illegal capture
        myPieces = wp | wn | wb | wr | wq; //omitted wk to avoid illegal capture
        occupiedSquares = wp | wn | wb | wr | wq | wk | bp | bn | bb | br | bq | bk;
        emptySquares = ~occupiedSquares;
        // TODO: optimize so that better moves are appended first
        return possibleWP(wp, bp, ep) +
                possibleN(wn) +
                possibleB(wb) +
                possibleR(wr) +
                possibleQ(wq) +
                possibleK(wk) +
                possibleCW(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, cwk, cwq);
    }

    /**
     * Returns a string of all possible moves by white pieces
     *
     * @param wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk bitboards of all pieces
     * @param ep  bitboard of en passant square
     * @param cwk true if castle white king side available
     * @param cwq true if castle white queen side available
     * @param cbk true if castle black king side available
     * @param cbq true if castle black queen side available
     * @return string of all possible moves by white pieces
     * The order of moves is: pawn, knight, bishop, rook, queen, king, castle
     * Each move is 4 characters long, so the length of the string is a multiple of 4
     */
    public static String possibleMovesB(long wp, long wn, long wb, long wr, long wq, long wk,
                                        long bp, long bn, long bb, long br, long bq, long bk,
                                        long ep, boolean cwk, boolean cwq, boolean cbk, boolean cbq) {
        notMyPieces = ~(bp | bn | bb | br | bq | bk | wk);
        myPieces = bp | bn | bb | br | bq;
        occupiedSquares = wp | wn | wb | wr | wq | wk | bp | bn | bb | br | bq | bk;
        emptySquares = ~occupiedSquares;
        return possibleBP(bp, wp, ep) +
                possibleN(bn) +
                possibleB(bb) +
                possibleR(br) +
                possibleQ(bq) +
                possibleK(bk) +
                possibleCB(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, cbk, cbq);
    }

    /**
     * Returns all possible pawn moves for white.
     *
     * @param wp white pawn bitboard
     * @param bp black pawn bitboard
     * @param ep en passant bitboard - has 1 for pawn that was moved 2 squares
     * @return a String with all possible pawn moves.<br>
     * - The order of moves is as follows: single pushes, double pushes, right captures, left captures, promotions, en passant captures.
     * - Further, the moves are sorted from top to bottom and left to right.
     * - Promotion moves are sorted by piece type: queen, rook, bishop, knight.<br>
     * - The string is filled with 4 character moves, so the length of the string is always a multiple of 4.<br><br>
     * - If the move is push/capture the characters are: destination rank, destination file, origin rank, origin file.<br>
     * - If the move is a promotion, the characters are: origin file, destination file, promotion type("Q", "R", "B", "N"), "P".<br>
     * - If the move is an en passant capture, the character are: origin file, destination file, "W", "E".<br>
     */
    protected static String possibleWP(long wp, long bp, long ep) {
        /*
         * moves: stores all moves to be returned
         *
         * "this condition": noted by "// condition"  (e.g, // Capture right)
         *
         * pawnMoves represents all possible pawns tha can move according to this condition
         * possibility is a bitboard that represents all possible moves for this condition
         * possibility = 0, when there are no possible moves for this condition
         */

        StringBuilder moves = new StringBuilder();
        long pawnMoves, possibility;

        // Single push
        pawnMoves = (wp >> 8) & emptySquares & ~RANK_8;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos / 8 + 1).append(currPos % 8).append(currPos / 8).append(currPos % 8);
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Double push
        pawnMoves = (wp >> 16) & emptySquares & (emptySquares >> 8) & RANK_4;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos / 8 + 2).append(currPos % 8).append(currPos / 8).append(currPos % 8);
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Capture right
        pawnMoves = (wp >> 7) & notMyPieces & occupiedSquares & ~RANK_8 & ~FILE_A;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos / 8 + 1).append(currPos % 8 - 1).append(currPos / 8).append(currPos % 8);
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Capture left
        pawnMoves = (wp >> 9) & notMyPieces & occupiedSquares & ~RANK_8 & ~FILE_H;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos / 8 + 1).append(currPos % 8 + 1).append(currPos / 8).append(currPos % 8);
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Promotion straight
        pawnMoves = (wp >> 8) & emptySquares & RANK_8;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos % 8).append(currPos % 8).append("QP").append(currPos % 8).append(currPos % 8).append("RP")
                    .append(currPos % 8).append(currPos % 8).append("BP").append(currPos % 8).append(currPos % 8).append("NP");
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Promotion capture right
        pawnMoves = (wp >> 7) & notMyPieces & occupiedSquares & RANK_8 & ~FILE_A;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos % 8 - 1).append(currPos % 8).append("QP").append(currPos % 8 - 1).append(currPos % 8).append("RP")
                    .append(currPos % 8 - 1).append(currPos % 8).append("BP").append(currPos % 8 - 1).append(currPos % 8).append("NP");
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Promotion capture left
        pawnMoves = (wp >> 9) & notMyPieces & occupiedSquares & RANK_8 & ~FILE_H;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos % 8 + 1).append(currPos % 8).append("QP").append(currPos % 8 + 1).append(currPos % 8).append("RP")
                    .append(currPos % 8 + 1).append(currPos % 8).append("BP").append(currPos % 8 + 1).append(currPos % 8).append("NP");
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // En passant capture right
        possibility = (wp << 1) & bp & RANK_5 & ~FILE_A & ep;
        if (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos % 8 - 1).append(currPos % 8).append("WE");
        }

        // En passant capture left
        possibility = (wp >> 1) & bp & RANK_5 & ~FILE_H & ep;
        if (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos % 8 + 1).append(currPos % 8).append("WE");
        }

        return moves.toString();
    }


    /**
     * Returns all possible pawn moves for black.
     *
     * @param bp black pawn bitboard
     * @param wp white pawn bitboard
     * @param ep en passant bitboard - has 1 for pawn that was moved 2 squares
     * @return a String with all possible pawn moves.<br>
     * - The order of moves is as follows: single pushes, double pushes, right captures, left captures, promotions, en passant captures.
     * - Further, the moves are sorted from top to bottom and left to right.
     * - Promotion moves are sorted by piece type: queen, rook, bishop, knight.<br>
     * - The string is filled with 4 character moves, so the length of the string is always a multiple of 4.<br><br>
     * - If the move is push/capture the characters are: destination rank, destination file, origin rank, origin file.<br>
     * - If the move is a promotion, the characters are: origin file, destination file, promotion type("q", "r", "b", "n"), "P".<br>
     * - If the move is an en passant capture, the character are: origin file, destination file, "B", "E".<br>
     */
    protected static String possibleBP(long bp, long wp, long ep) {
        // similar to possibleWP, but with black pieces

        StringBuilder moves = new StringBuilder();
        long pawnMoves, possibility;

        // Single push
        pawnMoves = (bp << 8) & emptySquares & ~RANK_1;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos / 8 - 1).append(currPos % 8).append(currPos / 8).append(currPos % 8);
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Double push
        pawnMoves = (bp << 16) & emptySquares & (emptySquares << 8) & RANK_5;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos / 8 - 2).append(currPos % 8).append(currPos / 8).append(currPos % 8);
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Capture right
        pawnMoves = (bp << 7) & notMyPieces & occupiedSquares & ~RANK_1 & ~FILE_H;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos / 8 - 1).append(currPos % 8 + 1).append(currPos / 8).append(currPos % 8);
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Capture left
        pawnMoves = (bp << 9) & notMyPieces & occupiedSquares & ~RANK_1 & ~FILE_A;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos / 8 - 1).append(currPos % 8 - 1).append(currPos / 8).append(currPos % 8);
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Promotion straight
        pawnMoves = (bp << 8) & emptySquares & RANK_1;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos % 8).append(currPos % 8).append("qP").append(currPos % 8).append(currPos % 8).append("rP").append(currPos % 8).append(currPos % 8).append("bP").append(currPos % 8).append(currPos % 8).append("nP");
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Promotion capture right
        pawnMoves = (bp << 7) & notMyPieces & occupiedSquares & RANK_1 & ~FILE_H;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos % 8 + 1).append(currPos % 8).append("qP").append(currPos % 8 + 1).append(currPos % 8).append("rP").append(currPos % 8 + 1).append(currPos % 8).append("bP").append(currPos % 8 + 1).append(currPos % 8).append("nP");
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Promotion capture left
        pawnMoves = (bp << 9) & notMyPieces & occupiedSquares & RANK_1 & ~FILE_A;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos % 8 - 1).append(currPos % 8).append("qP").append(currPos % 8 - 1).append(currPos % 8).append("rP").append(currPos % 8 - 1).append(currPos % 8).append("bP").append(currPos % 8 - 1).append(currPos % 8).append("nP");
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // En passant capture right
        possibility = (bp >> 1) & wp & RANK_4 & ~FILE_H & ep;
        if (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos % 8 + 1).append(currPos % 8).append("BE");
        }

        // En passant capture left
        possibility = (bp << 1) & wp & RANK_4 & ~FILE_A & ep;
        if (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.append(currPos % 8 - 1).append(currPos % 8).append("BE");
        }

        return moves.toString();
    }

    protected static String possibleN(long n) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }

    /**
     * Returns all possible bishop moves.
     *
     * @param bishop the rook bitboard
     * @return a String with all possible bishop moves.<br>
     * - The moves are sorted from top to bottom and left to right.<br>
     * - The string is filled with 4 character moves, so the length of the string is always a multiple of 4.<br>
     * - The characters are: origin rank, origin file, destination rank, destination file.<br>
     * - Capturing moves are included with similar format.<br>
     */
    protected static String possibleB(long bishop) {
        StringBuilder moves = new StringBuilder();
        long i = bishop & -bishop;
        long possibility;

        while (i != 0) {
            int iLocation = Long.numberOfTrailingZeros(i);
            possibility = diagonalMoves(iLocation) & notMyPieces;
            long j = possibility & -possibility;
            while (j != 0) {
                int index = Long.numberOfTrailingZeros(j);
                moves.append(iLocation / 8).append(iLocation % 8).append(index / 8).append(index % 8);
                possibility &= ~j;
                j = possibility & -possibility;
            }
            bishop &= ~i;
            i = bishop & -bishop;
        }

        return moves.toString();
    }

    /**
     * Returns all possible rook moves.
     *
     * @param rook the rook bitboard
     * @return a String with all possible rook moves.<br>
     * - The moves are sorted from top to bottom and left to right.<br>
     * - The string is filled with 4 character moves, so the length of the string is always a multiple of 4.<br>
     * - The characters are: origin rank, origin file, destination rank, destination file.<br>
     * - Capturing moves are included with similar format.<br>
     */
    protected static String possibleR(long rook) {
        StringBuilder moves = new StringBuilder();
        long i = rook & -rook;
        long possibility;

        while (i != 0) {
            int iLocation = Long.numberOfTrailingZeros(i);
            possibility = horizontalAndVerticalMoves(iLocation) & notMyPieces;
            long j = possibility & -possibility;
            while (j != 0) {
                int index = Long.numberOfTrailingZeros(j);
                moves.append(iLocation / 8).append(iLocation % 8).append(index / 8).append(index % 8);
                possibility &= ~j;
                j = possibility & -possibility;
            }
            rook &= ~i;
            i = rook & -rook;
        }

        return moves.toString();
    }

    protected static String possibleQ(long q) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }

    protected static String possibleK(long k) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }

    protected static String possibleCW(long wp, long wn, long wb, long wr, long wq, long wk,
                                       long bp, long bn, long bb, long br, long bq, long bk,
                                       boolean cwk, boolean cwq) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }

    protected static String possibleCB(long wp, long wn, long wb, long wr, long wq, long wk,
                                       long bp, long bn, long bb, long br, long bq, long bk,
                                       boolean cwk, boolean cwq) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }

    /**
     * Calculates the horizontal and vertical moves for a given square
     *
     * @param s the square to move from
     * @return bitboard of all possible vertical and horizontal moves from the square,
     * the destination squares are marked with a 1
     * <p>
     * Credit: Logic Crazy Chess
     */
    private static long horizontalAndVerticalMoves(int s) {
        long binaryS = 1L << s;
        long possibilitiesHorizontal = (occupiedSquares - 2 * binaryS) ^
                Long.reverse(Long.reverse(occupiedSquares) - 2 * Long.reverse(binaryS));
        long possibilitiesVertical = ((occupiedSquares & files[s % 8]) - (2 * binaryS)) ^
                Long.reverse(Long.reverse(occupiedSquares & files[s % 8]) - (2 * Long.reverse(binaryS)));
        return (possibilitiesHorizontal & ranks[s / 8]) | (possibilitiesVertical & files[s % 8]);
    }

    /**
     * Calculates the diagonal moves for a given square
     *
     * @param s the square to move from
     * @return bitboard of all possible diagonal moves from the square, the destination squares are marked with a 1
     * <p>
     * Credit: Logic Crazy Chess
     */
    static long diagonalMoves(int s) {
        long binaryS = 1L << s;
        long possibilitiesDiagonal = ((occupiedSquares & DiagonalMasks8[(s / 8) + (s % 8)]) - (2 * binaryS)) ^
                Long.reverse(Long.reverse(occupiedSquares & DiagonalMasks8[(s / 8) + (s % 8)]) - (2 * Long.reverse(binaryS)));
        long possibilitiesAntiDiagonal = ((occupiedSquares & AntiDiagonalMasks8[(s / 8) + 7 - (s % 8)]) - (2 * binaryS)) ^
                Long.reverse(Long.reverse(occupiedSquares & AntiDiagonalMasks8[(s / 8) + 7 - (s % 8)]) - (2 * Long.reverse(binaryS)));
        return (possibilitiesDiagonal & DiagonalMasks8[(s / 8) + (s % 8)]) | (possibilitiesAntiDiagonal & AntiDiagonalMasks8[(s / 8) + 7 - (s % 8)]);
    }

}

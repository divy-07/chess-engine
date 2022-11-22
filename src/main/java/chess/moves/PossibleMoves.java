package chess.moves;

import static chess.Constant.*;

public class PossibleMoves {

    // useful trackers
    static long notMyPieces;
    static long myPieces;
    static long occupiedSquares;
    static long emptySquares;

    public static String possibleMovesW(long WP, long WN, long WB, long WR, long WQ, long WK, long BP, long BN, long BB, long BR, long BQ, long BK, long EP, boolean CWK, boolean CWQ, boolean CBK, boolean CBQ) {
        notMyPieces = ~(WP | WN | WB | WR | WQ | WK | BK); //added BK to avoid illegal capture
        myPieces = WP | WN | WB | WR | WQ; //omitted WK to avoid illegal capture
        occupiedSquares = WP | WN | WB | WR | WQ | WK | BP | BN | BB | BR | BQ | BK;
        emptySquares = ~occupiedSquares;
        return possibleWP(WP, BP, EP) +
                possibleN(WN) +
                possibleB(WB) +
                possibleR(WR) +
                possibleQ(WQ) +
                possibleK(WK) +
                possibleCW(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK, CWK, CWQ);
    }

    public static String possibleMovesB(long WP, long WN, long WB, long WR, long WQ, long WK, long BP, long BN, long BB, long BR, long BQ, long BK, long EP, boolean CWK, boolean CWQ, boolean CBK, boolean CBQ) {
        notMyPieces = ~(BP | BN | BB | BR | BQ | BK | WK);
        myPieces = BP | BN | BB | BR | BQ;
        occupiedSquares = WP | WN | WB | WR | WQ | WK | BP | BN | BB | BR | BQ | BK;
        emptySquares = ~occupiedSquares;
        return possibleBP(BP, WP, EP) +
                possibleN(BN) +
                possibleB(BB) +
                possibleR(BR) +
                possibleQ(BQ) +
                possibleK(BK) +
                possibleCB(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK, CBK, CBQ);
    }

    /**
     * Returns possible pawn moves for white.
     *
     * @param WP white pawn bitboard
     * @param BP black pawn bitboard
     * @param EP en passant bitboard
     * @return a String with all possible pawn moves.<br>
     *         The string is filled with 4 character moves, so the length of the string is always a multiple of 4.<br><br>
     *         The characters are:<br>
     *         <ol>
     *             <li> The destination rank </li>
     *             <li> The destination file </li>
     *             <li> The origin rank </li>
     *             <li> The origin file </li>
     *         </ol>
     */
    public static String possibleWP(long WP, long BP, long EP) {
        /*
         * moves: stores all moves to be returned
         *
         * "this condition": noted by "// condition"  (e.g, Capture right)
         *
         * pawnMoves represents all possible pawns tha can move according to this condition
         *
         * possibility is a bitboard that represents all possible moves for this condition
         * possibility = 0, when there are no possible moves for this condition
         */

        StringBuilder moves = new StringBuilder();

        // Capture right
        long pawnMoves = (WP >> 7) & notMyPieces & occupiedSquares & ~RANK_8 & ~FILE_A;
        long possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int index = Long.numberOfTrailingZeros(possibility);
            moves.append(index / 8 + 1).append(index % 8 - 1).append(index / 8).append(index % 8);
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        return moves.toString();
    }

    public static String possibleBP(long BP, long WP, long EP) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }

    private static String possibleN(long wn) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }

    private static String possibleB(long wb) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }

    private static String possibleR(long wr) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }

    private static String possibleQ(long wq) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }

    private static String possibleK(long wk) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }

    private static String possibleCW(long wp, long wn, long wb, long wr, long wq, long wk, long bp, long bn, long bb, long br, long bq, long bk, boolean cwk, boolean cwq) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }

    public static String possibleCB(long WP, long WN, long WB, long WR, long WQ, long WK, long BP, long BN, long BB, long BR, long BQ, long BK, boolean CBK, boolean CBQ) {
        StringBuilder moves = new StringBuilder();
        return moves.toString();
    }
}

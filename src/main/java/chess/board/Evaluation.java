package chess.board;

import static chess.Constants.*;

public class Evaluation {

    /**
     * Evaluates the current position.
     *
     * @return the evaluation of the current position.
     *         Positive values are good for white, negative values are good for black.<br>
     *         Integer.MIN_VALUE < return value < Integer.MAX_VALUE
     */
    public static int evaluate(long wp, long wn, long wb, long wr, long wq, long wk,
                               long bp, long bn, long bb, long br, long bq, long bk,
                               long ep, boolean cwk, boolean cwq, boolean cbk, boolean cbq) {
        return evaluateMaterial(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk);
    }

    /**
     * Evaluates the current position based on number of pieces.
     *
     * @return the material evaluation of the current position.
     */
    private static int evaluateMaterial(long wp, long wn, long wb, long wr, long wq, long wk,
                                        long bp, long bn, long bb, long br, long bq, long bk) {
        // add up the number of pieces * their value
        // positive for white, negative for black
        return Long.bitCount(wp) * PAWN_VALUE
                + Long.bitCount(wn) * KNIGHT_VALUE
                + Long.bitCount(wb) * BISHOP_VALUE
                + Long.bitCount(wr) * ROOK_VALUE
                + Long.bitCount(wq) * QUEEN_VALUE
                - Long.bitCount(bp) * PAWN_VALUE
                - Long.bitCount(wn) * KNIGHT_VALUE
                - Long.bitCount(wb) * BISHOP_VALUE
                - Long.bitCount(wr) * ROOK_VALUE
                - Long.bitCount(wq) * QUEEN_VALUE;
    }

}

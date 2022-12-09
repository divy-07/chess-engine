package chess.board;

import static chess.Constants.*;

public class Evaluation {

    /**
     * Evaluates the current position.
     *
     * @return the evaluation of the current position.
     * Positive values are good for white, negative values are good for black.<br>
     * Integer.MIN_VALUE < return value < Integer.MAX_VALUE
     */
    public static int evaluate(Position position) {
        return evaluateMaterial(position);
    }

    /**
     * Evaluates the current position based on number of pieces.
     *
     * @return the material evaluation of the current position.
     */
    private static int evaluateMaterial(Position position) {
        // add up the number of pieces * their value
        // positive for white, negative for black
        return Long.bitCount(position.wp) * PAWN_VALUE
                + Long.bitCount(position.wn) * KNIGHT_VALUE
                + Long.bitCount(position.wb) * BISHOP_VALUE
                + Long.bitCount(position.wr) * ROOK_VALUE
                + Long.bitCount(position.wq) * QUEEN_VALUE
                + Long.bitCount(position.wk) * KING_VALUE
                - Long.bitCount(position.bp) * PAWN_VALUE
                - Long.bitCount(position.bn) * KNIGHT_VALUE
                - Long.bitCount(position.bb) * BISHOP_VALUE
                - Long.bitCount(position.br) * ROOK_VALUE
                - Long.bitCount(position.bq) * QUEEN_VALUE
                - Long.bitCount(position.bk) * KING_VALUE;
    }

}

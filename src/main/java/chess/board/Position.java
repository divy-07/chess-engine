package chess.board;

import chess.moves.Move;

import java.util.List;

/**
 * An immutable class that represents a position on the chess board.
 * The position is represented by a series of bitboards.
 */
public class Position {

    // bitboards
    public final long wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep;

    // castling rights
    public final boolean cwk, cwq, cbk, cbq;

    // side to move
    public final boolean whiteToMove;

    // half and full move counters
    public int halfMoveCount;
    public int fullMoveCount;

    /**
     * Creates a new position with bitboards and castling rights.
     */
    public Position(long wp, long wn, long wb, long wr, long wq, long wk,
                    long bp, long bn, long bb, long br, long bq, long bk,
                    long ep, boolean cwk, boolean cwq, boolean cbk, boolean cbq,
                    boolean whiteToMove) {
        this.wp = wp;
        this.wn = wn;
        this.wb = wb;
        this.wr = wr;
        this.wq = wq;
        this.wk = wk;
        this.bp = bp;
        this.bn = bn;
        this.bb = bb;
        this.br = br;
        this.bq = bq;
        this.bk = bk;
        this.ep = ep;
        this.cwk = cwk;
        this.cwq = cwq;
        this.cbk = cbk;
        this.cbq = cbq;
        this.whiteToMove = whiteToMove;
    }

    /**
     * Creates a new position with bitboards, castling rights, and move counters.
     */
    public Position(long wp, long wn, long wb, long wr, long wq, long wk,
                    long bp, long bn, long bb, long br, long bq, long bk,
                    long ep, boolean cwk, boolean cwq, boolean cbk, boolean cbq,
                    boolean whiteToMove, int halfMoveCount, int fullMoveCount) {
        this(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq, whiteToMove);
        this.halfMoveCount = halfMoveCount;
        this.fullMoveCount = fullMoveCount;
    }

    /**
     * Calculates the evaluation of the current position.
     *
     * @return the evaluation of the current position.
     */
    public int getEvaluation() {
        return Evaluation.evaluate(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
    }

    /**
     * Makes a move on the current position.
     * Does not change the current position.
     *
     * @param move the move to make.
     * @return the new position after the move.
     */
    public Position makeMove(Move move) {
        // TODO: implement
        return null;
    }

    /**
     * Finds the best move for this position.
     *
     * @return the best move for this position.
     */
    public Move getBestMove() {
        // TODO: implement
        return null;
    }

    /**
     * Finds all legal moves for this position.
     *
     * @return a list of all legal moves for this position.
     */
    public List<Move> getLegalMoves() {
        // TODO: implement
        return null;
    }

    public static Position fenToPosition(String fen) {
        // TODO: implement
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position p = (Position) o;
        return wp == p.wp && wn == p.wn && wb == p.wb && wr == p.wr && wq == p.wq && wk == p.wk
                && bp == p.bp && bn == p.bn && bb == p.bb && br == p.br && bq == p.bq && bk == p.bk
                && ep == p.ep && cwk == p.cwk && cwq == p.cwq && cbk == p.cbk && cbq == p.cbq
                && whiteToMove == p.whiteToMove;
    }

    @Override
    public int hashCode() {
        return (int) (wp ^ wn ^ wb ^ wr ^ wq ^ wk ^ bp ^ bn ^ bb ^ br ^ bq ^ bk ^ ep
                ^ (cwk ? 1 : 0) ^ (cwq ? 2 : 0) ^ (cbk ? 4 : 0) ^ (cbq ? 8 : 0)
                ^ (whiteToMove ? 16 : 0));
    }

}

package chess.board;

import chess.moves.Move;
import chess.moves.MoveGeneration;

import java.util.List;

import static chess.Constants.files;

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
     * Creates a new position with empty bitboards.
     * Castling rights are set to true and it is white's turn to move.
     *
     * @return a new empty position
     */
    public static Position emptyPosition() {
        return new Position(0L, 0L, 0L, 0L, 0L, 0L,
                0L, 0L, 0L, 0L, 0L, 0L, 0L,
                true, true, true, true, true);
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
     * Does not change this position object.
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
        return MoveGeneration.getBestMove(this);
    }

    /**
     * Finds all legal moves for this position.
     *
     * @return a list of all legal moves for this position.
     */
    public List<Move> getLegalMoves() {
        if (whiteToMove) {
            return getLegalWhiteMoves();
        } else {
            return getLegalBlackMoves();
        }
    }

    /**
     * Returns all possible white moves for this position.
     * Ignores this.whiteToMove.
     *
     * @return a list of all possible white moves for this position.
     */
    public List<Move> getLegalWhiteMoves() {
        // TODO: implement
        return null;
    }

    /**
     * Returns all possible black moves for this position.
     * Ignores this.whiteToMove.
     *
     * @return a list of all possible black moves for this position.
     */
    public List<Move> getLegalBlackMoves() {
        // TODO: implement
        return null;
    }

    /**
     * Applies all moves in a list to the current position.
     * Does not change this position object.
     *
     * @param moves the list of moves to apply.
     * @return the new position after applying all moves.
     */
    public Position applyMoves(List<Move> moves) {
        // TODO: implement
        return null;
    }

    /**
     * Converts a FEN string to a position.
     *
     * @param fenString the new board state in FEN notation
     *                  (<a href="https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation">
     *                  format specifications</a>)<p>
     *                  Changes the state of the engine to the new given board state.
     * @return a new Position object with the new board state
     */
    public static Position fenToPosition(String fenString) {
        long wp = 0L;
        long wn = 0L;
        long wb = 0L;
        long wr = 0L;
        long wq = 0L;
        long wk = 0L;
        long bp = 0L;
        long bn = 0L;
        long bb = 0L;
        long br = 0L;
        long bq = 0L;
        long bk = 0L;
        long ep = 0L;
        boolean cwk = false;
        boolean cwq = false;
        boolean cbk = false;
        boolean cbq = false;

        int charIndex = 0;
        int boardIndex = 0;

        // parse the board state
        while (fenString.charAt(charIndex) != ' ') {
            switch (fenString.charAt(charIndex++)) {
                case 'P' -> wp |= (1L << boardIndex++);
                case 'p' -> bp |= (1L << boardIndex++);
                case 'N' -> wn |= (1L << boardIndex++);
                case 'n' -> bn |= (1L << boardIndex++);
                case 'B' -> wb |= (1L << boardIndex++);
                case 'b' -> bb |= (1L << boardIndex++);
                case 'R' -> wr |= (1L << boardIndex++);
                case 'r' -> br |= (1L << boardIndex++);
                case 'Q' -> wq |= (1L << boardIndex++);
                case 'q' -> bq |= (1L << boardIndex++);
                case 'K' -> wk |= (1L << boardIndex++);
                case 'k' -> bk |= (1L << boardIndex++);
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
        boolean whiteToMove = (fenString.charAt(++charIndex) == 'w');

        // set castling rights
        charIndex += 2;
        while (fenString.charAt(charIndex) != ' ') {
            switch (fenString.charAt(charIndex++)) {
                case 'K' -> cwk = true;
                case 'Q' -> cwq = true;
                case 'k' -> cbk = true;
                case 'q' -> cbq = true;
            }
        }

        // set en passant square
        if (fenString.charAt(++charIndex) != '-') {
            ep = files[fenString.charAt(charIndex) - 'a'];
        }

        // TODO: debug and fix setting half move and full move numbers
        // half-move
        // charIndex += 3;
        // int halfMoveCount = Integer.parseInt(fenString.substring(charIndex, fenString.indexOf(' ', charIndex)));

        // full move
        // charIndex = fenString.indexOf(' ', charIndex) + 1;
        // int fullMoveCount = Integer.parseInt(fenString.substring(charIndex));

        return new Position(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep,
                cwk, cwq, cbk, cbq, whiteToMove);
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

    @Override
    public Position clone() {
        return new Position(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep,
                cwk, cwq, cbk, cbq, whiteToMove);
    }

}

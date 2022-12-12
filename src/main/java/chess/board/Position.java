package chess.board;

import chess.engine.MakeMove;
import chess.moves.Move;
import chess.moves.MoveConversion;
import chess.moves.MoveGeneration;
import chess.moves.PossibleMoves;

import java.util.*;

import static chess.Constants.*;

/**
 * An immutable class that represents a position on the chess board.
 * The position is represented by a series of bitboards.
 */
public class Position {
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


    // keeping the fields public should not be a problem since
    // they are all primitive type and final
    // except the move counts, which are private and only accessible through getters

    // bitboards
    public final long wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep;

    // castling rights
    public final boolean cwk, cwq, cbk, cbq;

    // side to move
    public final boolean whiteToMove;

    // half and full move counters
    public int halfMoveCount;
    public int fullMoveCount;

    // useful bitboards
    private final long notMyPieces; // every square except my pieces + opponent's king
    private final long myPieces; // squares with my pieces - my king
    private final long occupiedSquares; // squares with pieces
    private final long emptySquares; // squares without any pieces

    // possible move object
    public final PossibleMoves possibleMoves;

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
        this.fullMoveCount = 0;
        this.halfMoveCount = 1;

        // set useful bitboards
        if (whiteToMove) {
            notMyPieces = ~(wp | wn | wb | wr | wq | wk | bk);
            myPieces = wp | wn | wb | wr | wq;
        } else {
            notMyPieces = ~(bp | bn | bb | br | bq | bk | wk);
            myPieces = bp | bn | bb | br | bq;
        }
        occupiedSquares = wp | wn | wb | wr | wq | wk | bp | bn | bb | br | bq | bk;
        emptySquares = ~occupiedSquares;

        possibleMoves = new PossibleMoves(notMyPieces, myPieces, occupiedSquares, emptySquares);
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
        return Evaluation.evaluate(this);
    }

    /**
     * Makes a move on the current position.
     * Does not change this position object.
     *
     * @param move the move to make.
     * @return the new position after the move.
     */
    public Position makeMove(Move move) {
        long new_wp = MakeMove.makeMove(wp, move, 'P');
        long new_wn = MakeMove.makeMove(wn, move, 'N');
        long new_wb = MakeMove.makeMove(wb, move, 'B');
        long new_wr = MakeMove.makeMove(wr, move, 'R');
        long new_wq = MakeMove.makeMove(wq, move, 'Q');
        long new_wk = MakeMove.makeMove(wk, move, 'K');
        long new_bp = MakeMove.makeMove(bp, move, 'p');
        long new_bn = MakeMove.makeMove(bn, move, 'n');
        long new_bb = MakeMove.makeMove(bb, move, 'b');
        long new_br = MakeMove.makeMove(br, move, 'r');
        long new_bq = MakeMove.makeMove(bq, move, 'q');
        long new_bk = MakeMove.makeMove(bk, move, 'k');
        long new_ep = MakeMove.makeMoveEP(wp | bp, move);

        return new Position(new_wp, new_wn, new_wb, new_wr, new_wq, new_wk,
                            new_bp, new_bn, new_bb, new_br, new_bq, new_bk,
                            new_ep, cwk, cwq, cbk, cbq, !whiteToMove,
                            halfMoveCount + 1, fullMoveCount + 1);
    }

    /**
     * Finds the best move for this position.
     * Time spent might vary depending on the position and algorithm used.
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
        return whiteToMove ? getLegalWhiteMoves() : getLegalBlackMoves();
    }

    /**
     * Returns all possible white moves for this position.
     * Ignores this.whiteToMove.
     *
     * @return a list of all possible white moves for this position.
     * The order of moves is: pawn, knight, bishop, rook, queen, king, castle
     */
    public List<Move> getLegalWhiteMoves() {
        List<Move> move = new ArrayList<>();
        move.addAll(possibleMoves.possibleWP(wp, bp, ep));
        move.addAll(possibleMoves.possibleN(wn));
        move.addAll(possibleMoves.possibleB(wb));
        move.addAll(possibleMoves.possibleR(wr));
        move.addAll(possibleMoves.possibleQ(wq));
        move.addAll(possibleMoves.possibleK(wk));
        move.addAll(possibleMoves.possibleCW(this));
        return move;
    }

    /**
     * Returns all possible black moves for this position.
     * Ignores this.whiteToMove.
     *
     * @return a list of all possible black moves for this position.
     * The order of moves is: pawn, knight, bishop, rook, queen, king, castle
     */
    public List<Move> getLegalBlackMoves() {
        List<Move> move = new ArrayList<>();
        move.addAll(possibleMoves.possibleBP(bp, wp, ep));
        move.addAll(possibleMoves.possibleN(bn));
        move.addAll(possibleMoves.possibleB(bb));
        move.addAll(possibleMoves.possibleR(br));
        move.addAll(possibleMoves.possibleQ(bq));
        move.addAll(possibleMoves.possibleK(bk));
        move.addAll(possibleMoves.possibleCB(this));
        return move;
    }

    /**
     * Applies all moves in a list to the current position.
     * Does not change this position object.
     *
     * @param moves the list of moves to apply.
     * @return the new position after applying all moves.
     */
    public Position applyMoves(List<Move> moves) {
        // convert moves to algebraic notation
        StringBuilder sb = new StringBuilder();
        for (Move move : moves) {
            sb.append(move.toAlgebraicNotation());
        }

        // apply moves and return new position
        return MoveConversion.applyAlgebraMoves(sb.toString(), getLegalMoves(), this);
    }

    /**
     * Returns the starting position.
     * Castling rights are set to true, and it is white's turn to move.
     *
     * @return the starting position.
     */
    public static Position startingPosition() {
        return new Position(startWP, startWN, startWB, startWR, startWQ, startWK,
                startBP, startBN, startBB, startBR, startBQ, startBK, 0,
                true, true, true, true, true);
    }

    /**
     * Creates a new position with empty bitboards.
     * Castling rights are set to true, and it is white's turn to move.
     *
     * @return a new empty position
     */
    public static Position emptyPosition() {
        return new Position(0L, 0L, 0L, 0L, 0L, 0L,
                0L, 0L, 0L, 0L, 0L, 0L, 0L,
                true, true, true, true, true);
    }

    /**
     * Converts a FEN string to a position.
     *
     * @param fenString the new board state in FEN notation
     *                  (<a href="https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation">
     *                  format specifications</a>)<p>
     *                  Changes the state of the engine to the new given board state.
     * @return a new Position object with the new board state
     * @credit Jonathan Warkentin
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
        int halfMoveCount = 1;
        int fullMoveCount = 0;

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
        // halfMoveCount = Integer.parseInt(fenString.substring(charIndex, fenString.indexOf(' ', charIndex)));

        // full move
        // charIndex = fenString.indexOf(' ', charIndex) + 1;
        // fullMoveCount = Integer.parseInt(fenString.substring(charIndex));

        return new Position(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep,
                cwk, cwq, cbk, cbq, whiteToMove, halfMoveCount, fullMoveCount);
    }

    /**
     * @return half move count
     */
    public int getHalfMoveCount() {
        return halfMoveCount;
    }

    /**
     * @return full move count
     */
    public int getFullMoveCount() {
        return fullMoveCount;
    }

    /**
     * Compares this position to o.
     * If o is of type Position, then the comparison is based on these criteria:
     * all 12 bitboards, en passant square, castling rights, and whose turn it is.
     *
     * @param o the object to compare to
     * @return true if the objects are equal as stated, false otherwise
     */
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

    /**
     * @return a hash code for this position based on:
     * all 12 bitboards, en passant square, castling rights, and whose turn it is
     */
    @Override
    public int hashCode() {
        return (int) (wp ^ wn ^ wb ^ wr ^ wq ^ wk ^ bp ^ bn ^ bb ^ br ^ bq ^ bk ^ ep
                ^ (cwk ? 1 : 0) ^ (cwq ? 2 : 0) ^ (cbk ? 4 : 0) ^ (cbq ? 8 : 0)
                ^ (whiteToMove ? 16 : 0));
    }

    /**
     * @return a new Position object with the same board state as this one
     */
    @Override
    public Position clone() {
        return new Position(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep,
                cwk, cwq, cbk, cbq, whiteToMove);
    }

    /**
     * @return a "print-friendly" string representation of this position
     */
    @Override
    public String toString() {
        /*
         * Convert the bitboards to a 2D array of 1-char strings.
         * Add pieces using bit comparisons.
         * Convert to a string using StringBuilder.
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(Arrays.toString(chessBoard[i]));
            sb.append("\n");
        }
        return sb.toString();
    }

}

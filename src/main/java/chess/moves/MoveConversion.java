package chess.moves;

import chess.board.Position;
import chess.engine.MakeMove;

import java.util.List;

import static chess.Constants.files;
import static chess.Constants.ranks;

public class MoveConversion {

    /**
     * Converts a move from the format used by engine to algebraic notation.
     *
     * @param move the move to be converted
     * @return the move in algebraic notation
     * @credit Logic Crazy Chess
     */
    public static String moveToAlgebra(String move) {
        String append = "";
        int start = 0, end = 0;
        if (Character.isDigit(move.charAt(3))) {
            // normal move
            start = (Character.getNumericValue(move.charAt(0)) * 8) + (Character.getNumericValue(move.charAt(1)));
            end = (Character.getNumericValue(move.charAt(2)) * 8) + (Character.getNumericValue(move.charAt(3)));
        } else if (move.charAt(3) == 'P') {
            // pawn promotion
            if (Character.isUpperCase(move.charAt(2))) {
                start = Long.numberOfTrailingZeros(files[move.charAt(0) - '0'] & ranks[1]);
                end = Long.numberOfTrailingZeros(files[move.charAt(1) - '0'] & ranks[0]);
            } else {
                start = Long.numberOfTrailingZeros(files[move.charAt(0) - '0'] & ranks[6]);
                end = Long.numberOfTrailingZeros(files[move.charAt(1) - '0'] & ranks[7]);
            }
            append = "" + Character.toLowerCase(move.charAt(2));
        } else if (move.charAt(3) == 'E') {
            // en passant
            if (move.charAt(2) == 'W') {
                start = Long.numberOfTrailingZeros(files[move.charAt(0) - '0'] & ranks[3]);
                end = Long.numberOfTrailingZeros(files[move.charAt(1) - '0'] & ranks[2]);
            } else {
                start = Long.numberOfTrailingZeros(files[move.charAt(0) - '0'] & ranks[4]);
                end = Long.numberOfTrailingZeros(files[move.charAt(1) - '0'] & ranks[5]);
            }
        }
        String returnMove = "";
        returnMove += (char) ('a' + (start % 8));
        returnMove += (char) ('8' - (start / 8));
        returnMove += (char) ('a' + (end % 8));
        returnMove += (char) ('8' - (end / 8));
        returnMove += append;
        return returnMove;
    }

    /**
     * Parses algebraic moves and makes it on the engine.
     *
     * @param input the input string from the GUI; the moves to be made
     * @param possibleMoves the list of possible moves from the current position
     * @return the position after the moves have been applied to the position to Hari.position
     */
    public static Position applyAlgebraMoves(String input, List<Move> possibleMoves, Position position) {
        // copy position values from given position
        long wp = position.wp;
        long wn = position.wn;
        long wb = position.wb;
        long wr = position.wr;
        long wq = position.wq;
        long wk = position.wk;
        long bp = position.bp;
        long bn = position.bn;
        long bb = position.bb;
        long br = position.br;
        long bq = position.bq;
        long bk = position.bk;
        long ep = position.ep;
        boolean cwk = position.cwk;
        boolean cwq = position.cwq;
        boolean cbk = position.cbk;
        boolean cbq = position.cbq;
        boolean whiteToMove = position.whiteToMove;
        int halfMoveCount = position.halfMoveCount;
        int fullMoveCount = position.fullMoveCount;

        // cells for input
        int sourceFile = input.charAt(0) - 'a';
        int sourceRank = '8' - input.charAt(1);
        int destFile = input.charAt(2) - 'a';
        int destRank = '8' - input.charAt(3);

        int start = 0, end = 0;
        int from = (input.charAt(0) - 'a') + (8 * ('8' - input.charAt(1)));
        int to = (input.charAt(2) - 'a') + (8 * ('8' - input.charAt(3)));
        
        // check if the given move is valid, if so make it
        for (Move move : possibleMoves) {
            if (!move.isEnPassant && !move.isPromotion) {
                // normal move
                start = move.sourceRank * 8 + move.sourceFile;
                end = move.destRank * 8 + move.destFile;
            } else if (move.isPromotion) {
                // pawn promotion
                if (Character.isUpperCase(move.promotionPiece)) {
                    start = Long.numberOfTrailingZeros(files[move.sourceFile] & ranks[1]);
                    end = Long.numberOfTrailingZeros(files[move.destFile] & ranks[0]);
                } else {
                    start = Long.numberOfTrailingZeros(files[move.sourceFile] & ranks[6]);
                    end = Long.numberOfTrailingZeros(files[move.destFile] & ranks[7]);
                }
            } else if (move.isEnPassant) {
                // en passant
                if (move.sourceRank == 5) {
                    // white
                    start = Long.numberOfTrailingZeros(files[move.sourceFile] & ranks[3]);
                    end = Long.numberOfTrailingZeros(files[move.destFile] & ranks[2]);
                } else {
                    start = Long.numberOfTrailingZeros(files[move.sourceFile] & ranks[4]);
                    end = Long.numberOfTrailingZeros(files[move.destFile] & ranks[5]);
                }
            }
            if ((start == from) && (end == to)) {
                if ((input.charAt(4) == ' ') || (Character.toUpperCase(input.charAt(4)) == Character.toUpperCase(move.promotionPiece))) {
                    if (!move.isEnPassant && !move.isPromotion) {
                        // if normal move
                        start = (move.sourceRank * 8) + (move.sourceFile);
                        if (((1L << start) & wk) != 0) {
                            cwk = false;
                            cwq = false;
                        } else if (((1L << start) & bk) != 0) {
                            cbk = false;
                            cbq = false;
                        } else if (((1L << start) & wr & (1L << 63)) != 0) {
                            cwk = false;
                        } else if (((1L << start) & wr & (1L << 56)) != 0) {
                            cwq = false;
                        } else if (((1L << start) & br & (1L << 7)) != 0) {
                            cbk = false;
                        } else if (((1L << start) & br & 1L) != 0) {
                            cbq = false;
                        }
                    }
                    ep = MakeMove.makeMoveEP(wp | bp, move);
                    wp = MakeMove.makeMove(wp, move, 'P');
                    wn = MakeMove.makeMove(wn, move, 'N');
                    wb = MakeMove.makeMove(wb, move, 'B');
                    wr = MakeMove.makeMove(wr, move, 'R');
                    wq = MakeMove.makeMove(wq, move, 'Q');
                    wk = MakeMove.makeMove(wk, move, 'K');
                    bp = MakeMove.makeMove(bp, move, 'p');
                    bn = MakeMove.makeMove(bn, move, 'n');
                    bb = MakeMove.makeMove(bb, move, 'b');
                    br = MakeMove.makeMove(br, move, 'r');
                    bq = MakeMove.makeMove(bq, move, 'q');
                    bk = MakeMove.makeMove(bk, move, 'k');
                    whiteToMove = !whiteToMove;
                    break;
                }
            }
        }

        return new Position(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep,
                cwk, cwq, cbk, cbq, whiteToMove, halfMoveCount, fullMoveCount);
    }

}

package chess.board;

import chess.moves.Move;

import static chess.Constants.files;
import static chess.Constants.ranks;

public class MakeMove {

    /**
     * Makes a move on given bitboard and returns the new bitboard.
     *
     * @param board the bitboard to make the move on
     * @param move  the move to make
     * @param type  the character of type of piece being moved
     * @return the new bitboard after the move has been made
     * @credit Logic Crazy Chess
     */
    public static long makeMove(long board, Move move, char type) {
        if (!move.isPromotion && !move.isEnPassant) {
            // normal move
            int start = move.sourceRank * 8 + move.sourceFile;
            int end = move.destRank * 8 + move.destFile;
            if (((board >>> start) & 1) == 1) {
                board &= ~(1L << start);
                board |= (1L << end);
            } else {
                board &= ~(1L << end);
            }

            // castling rook move
            if ((type == 'R' || type == 'r') && (move.isKingSideCastling() || move.isQueenSideCastling())) {
                board = makeRookCastlingMove(board, move);
            }

        } else if (move.isPromotion) {
            // pawn promotion
            int start, end;
            if (Character.isUpperCase(move.promotionPiece)) {
                start = Long.numberOfTrailingZeros(files[move.sourceFile] & ranks[1]);
                end = Long.numberOfTrailingZeros(files[move.destFile] & ranks[0]);
            } else {
                start = Long.numberOfTrailingZeros(files[move.sourceFile] & ranks[6]);
                end = Long.numberOfTrailingZeros(files[move.destFile] & ranks[7]);
            }
            if (type == move.promotionPiece) {
                board |= (1L << end);
            } else {
                board &= ~(1L << start);
                board &= ~(1L << end);
            }

        } else if (move.isEnPassant) {
            // en passant
            int start, end;
            if (move.sourceRank == 3) {
                // white
                start = Long.numberOfTrailingZeros(files[move.sourceFile] & ranks[3]);
                end = Long.numberOfTrailingZeros(files[move.destFile] & ranks[2]);
                board &= ~(files[move.destFile] & ranks[3]);
            } else {
                start = Long.numberOfTrailingZeros(files[move.sourceFile] & ranks[4]);
                end = Long.numberOfTrailingZeros(files[move.destFile] & ranks[5]);
                board &= ~(files[move.destFile] & ranks[4]);
            }
            if (((board >>> start) & 1) == 1) {
                board &= ~(1L << start);
                board |= (1L << end);
            }
        } else {
            System.out.print("ERROR: Invalid move type");
        }
        return board;
    }

    /**
     * Makes a castling move on rook bitboard and returns the new bitboard.
     *
     * @param board the rook bitboard to make the move on
     * @param move  the move to make
     * @return the new rook bitboard after the move has been made
     * @author Divy Patel
     */
    private static long makeRookCastlingMove(long board, Move move) {
        if (move.isKingSideCastling()) {
            if (move.sourceRank == 7) {
                // white king side castling
                return makeMove(board, new Move(7, 7, 7, 5), 'R');
            } else {
                // black king side castling
                return makeMove(board, new Move(0, 7, 0, 5), 'r');
            }
        } else {
            if (move.sourceRank == 7) {
                // white queen side castling
                return makeMove(board, new Move(7, 0, 7, 3), 'R');
            } else {
                // black queen side castling
                return makeMove(board, new Move(0, 0, 0, 3), 'r');
            }
        }
    }

    /**
     * Makes En Passant moves on the given en passant bitboard.
     *
     * @param board the bitboard to make the move on;
     *              should be (WP | BP)
     * @param move  the move to make
     * @return the new en passant bitboard after the move has been made
     * @credit Logic Crazy Chess
     */
    public static long makeMoveEP(long board, Move move) {
        if (!move.isEnPassant && !move.isPromotion) {
            int start = move.sourceRank * 8 + move.sourceFile;
            if ((Math.abs(move.sourceRank - move.destRank) == 2) && (((board >>> start) & 1) == 1)) {
                return files[move.sourceFile];
            }
        }
        return 0;
    }

}

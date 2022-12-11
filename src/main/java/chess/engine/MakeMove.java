package chess.engine;

import chess.moves.Move;

import static chess.Constants.files;
import static chess.Constants.ranks;

public class MakeMove {

    /**
     * Makes a move on the engine's board.
     *
     * @param board the bitboard to make the move on
     * @param move  the move to make; in the form "e2e4"
     * @param type  the type of piece being moved
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
            if (move.sourceRank == 5) {
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
     * Makes En Passant moves on the engine's en passant bitboard.
     *
     * @param board the bitboard to make the move on;
     *              should be (WP | BP)
     * @param move  the move to make; in the form "e2e4"
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

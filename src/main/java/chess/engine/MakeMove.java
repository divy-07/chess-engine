package chess.engine;

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
    public static long makeMove(long board, String move, char type) {
        if (Character.isDigit(move.charAt(3))) {//'regular' move
            int start = (Character.getNumericValue(move.charAt(0)) * 8) + (Character.getNumericValue(move.charAt(1)));
            int end = (Character.getNumericValue(move.charAt(2)) * 8) + (Character.getNumericValue(move.charAt(3)));
            if (((board >>> start) & 1) == 1) {
                board &= ~(1L << start);
                board |= (1L << end);
            } else {
                board &= ~(1L << end);
            }
        } else if (move.charAt(3) == 'P') {//pawn promotion
            int start, end;
            if (Character.isUpperCase(move.charAt(2))) {
                start = Long.numberOfTrailingZeros(files[move.charAt(0) - '0'] & ranks[1]);
                end = Long.numberOfTrailingZeros(files[move.charAt(1) - '0'] & ranks[0]);
            } else {
                start = Long.numberOfTrailingZeros(files[move.charAt(0) - '0'] & ranks[6]);
                end = Long.numberOfTrailingZeros(files[move.charAt(1) - '0'] & ranks[7]);
            }
            if (type == move.charAt(2)) {
                board |= (1L << end);
            } else {
                board &= ~(1L << start);
                board &= ~(1L << end);
            }
        } else if (move.charAt(3) == 'E') {//en passant
            int start, end;
            if (move.charAt(2) == 'W') {
                start = Long.numberOfTrailingZeros(files[move.charAt(0) - '0'] & ranks[3]);
                end = Long.numberOfTrailingZeros(files[move.charAt(1) - '0'] & ranks[2]);
                board &= ~(files[move.charAt(1) - '0'] & ranks[3]);
            } else {
                start = Long.numberOfTrailingZeros(files[move.charAt(0) - '0'] & ranks[4]);
                end = Long.numberOfTrailingZeros(files[move.charAt(1) - '0'] & ranks[5]);
                board &= ~(files[move.charAt(1) - '0'] & ranks[4]);
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
    public static long makeMoveEP(long board, String move) {
        if (Character.isDigit(move.charAt(3))) {
            int start = (Character.getNumericValue(move.charAt(0)) * 8) + (Character.getNumericValue(move.charAt(1)));
            if ((Math.abs(move.charAt(0) - move.charAt(2)) == 2) && (((board >>> start) & 1) == 1)) {
                return files[move.charAt(1) - '0'];
            }
        }
        return 0;
    }

}

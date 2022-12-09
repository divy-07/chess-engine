package chess.moves;

import chess.engine.Hari;
import chess.engine.MakeMove;

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
        if (Character.isDigit(move.charAt(3))) {//'regular' move
            start = (Character.getNumericValue(move.charAt(0)) * 8) + (Character.getNumericValue(move.charAt(1)));
            end = (Character.getNumericValue(move.charAt(2)) * 8) + (Character.getNumericValue(move.charAt(3)));
        } else if (move.charAt(3) == 'P') {//pawn promotion
            if (Character.isUpperCase(move.charAt(2))) {
                start = Long.numberOfTrailingZeros(files[move.charAt(0) - '0'] & ranks[1]);
                end = Long.numberOfTrailingZeros(files[move.charAt(1) - '0'] & ranks[0]);
            } else {
                start = Long.numberOfTrailingZeros(files[move.charAt(0) - '0'] & ranks[6]);
                end = Long.numberOfTrailingZeros(files[move.charAt(1) - '0'] & ranks[7]);
            }
            append = "" + Character.toLowerCase(move.charAt(2));
        } else if (move.charAt(3) == 'E') {//en passant
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
     * @param moves the list of possible moves from the current position
     * @credit Logic Crazy Chess
     */
    public static void algebraToMove(String input, String moves) {
        int start = 0, end = 0;
        int from = (input.charAt(0) - 'a') + (8 * ('8' - input.charAt(1)));
        int to = (input.charAt(2) - 'a') + (8 * ('8' - input.charAt(3)));
        for (int i = 0; i < moves.length(); i += 4) {
            if (Character.isDigit(moves.charAt(i + 3))) {//'regular' move
                start = (Character.getNumericValue(moves.charAt(i)) * 8) + (Character.getNumericValue(moves.charAt(i + 1)));
                end = (Character.getNumericValue(moves.charAt(i + 2)) * 8) + (Character.getNumericValue(moves.charAt(i + 3)));
            } else if (moves.charAt(i + 3) == 'P') {//pawn promotion
                if (Character.isUpperCase(moves.charAt(i + 2))) {
                    start = Long.numberOfTrailingZeros(files[moves.charAt(i) - '0'] & ranks[1]);
                    end = Long.numberOfTrailingZeros(files[moves.charAt(i + 1) - '0'] & ranks[0]);
                } else {
                    start = Long.numberOfTrailingZeros(files[moves.charAt(i) - '0'] & ranks[6]);
                    end = Long.numberOfTrailingZeros(files[moves.charAt(i + 1) - '0'] & ranks[7]);
                }
            } else if (moves.charAt(i + 3) == 'E') {//en passant
                if (moves.charAt(i + 2) == 'W') {
                    start = Long.numberOfTrailingZeros(files[moves.charAt(i) - '0'] & ranks[3]);
                    end = Long.numberOfTrailingZeros(files[moves.charAt(i + 1) - '0'] & ranks[2]);
                } else {
                    start = Long.numberOfTrailingZeros(files[moves.charAt(i) - '0'] & ranks[4]);
                    end = Long.numberOfTrailingZeros(files[moves.charAt(i + 1) - '0'] & ranks[5]);
                }
            }
            if ((start == from) && (end == to)) {
                if ((input.charAt(4) == ' ') || (Character.toUpperCase(input.charAt(4)) == Character.toUpperCase(moves.charAt(i + 2)))) {
                    if (Character.isDigit(moves.charAt(i + 3))) {//'regular' move
                        start = (Character.getNumericValue(moves.charAt(i)) * 8) + (Character.getNumericValue(moves.charAt(i + 1)));
                        if (((1L << start) & Hari.WK) != 0) {
                            Hari.CWK = false;
                            Hari.CWQ = false;
                        } else if (((1L << start) & Hari.BK) != 0) {
                            Hari.CBK = false;
                            Hari.CBQ = false;
                        } else if (((1L << start) & Hari.WR & (1L << 63)) != 0) {
                            Hari.CWK = false;
                        } else if (((1L << start) & Hari.WR & (1L << 56)) != 0) {
                            Hari.CWQ = false;
                        } else if (((1L << start) & Hari.BR & (1L << 7)) != 0) {
                            Hari.CBK = false;
                        } else if (((1L << start) & Hari.BR & 1L) != 0) {
                            Hari.CBQ = false;
                        }
                    }
                    Hari.EP = MakeMove.makeMoveEP(Hari.WP | Hari.BP, moves.substring(i, i + 4));
                    Hari.WP = MakeMove.makeMove(Hari.WP, moves.substring(i, i + 4), 'P');
                    Hari.WN = MakeMove.makeMove(Hari.WN, moves.substring(i, i + 4), 'N');
                    Hari.WB = MakeMove.makeMove(Hari.WB, moves.substring(i, i + 4), 'B');
                    Hari.WR = MakeMove.makeMove(Hari.WR, moves.substring(i, i + 4), 'R');
                    Hari.WQ = MakeMove.makeMove(Hari.WQ, moves.substring(i, i + 4), 'Q');
                    Hari.WK = MakeMove.makeMove(Hari.WK, moves.substring(i, i + 4), 'K');
                    Hari.BP = MakeMove.makeMove(Hari.BP, moves.substring(i, i + 4), 'p');
                    Hari.BN = MakeMove.makeMove(Hari.BN, moves.substring(i, i + 4), 'n');
                    Hari.BB = MakeMove.makeMove(Hari.BB, moves.substring(i, i + 4), 'b');
                    Hari.BR = MakeMove.makeMove(Hari.BR, moves.substring(i, i + 4), 'r');
                    Hari.BQ = MakeMove.makeMove(Hari.BQ, moves.substring(i, i + 4), 'q');
                    Hari.BK = MakeMove.makeMove(Hari.BK, moves.substring(i, i + 4), 'k');
                    Hari.whiteToMove = !Hari.whiteToMove;
                    break;
                }
            }
        }
    }

}

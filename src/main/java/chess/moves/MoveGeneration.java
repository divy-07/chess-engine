package chess.moves;

import chess.board.Position;

import java.util.List;

import static chess.Constants.*;

public class MoveGeneration {

    /**
     * Calculates the best move for the current position.
     * Currently using a basic <a href="https://www.chessprogramming.org/Minimax">minimax algorithm</a>
     *
     * @param position the position to calculate the best move for
     * @return the best move
     */
    public static Move getBestMove(Position position) {
        return basicPlySearch(position, MAX_DEPTH);
    }

    /**
     * Calculates the best move for given position.
     * Currently using a basic <a href="https://www.chessprogramming.org/Minimax">minimax algorithm</a>
     *
     * @param position the position to calculate the best move for.
     * @return the best move found with basic mini-max search;
     * the string is in the format "e2e4" or "e7e8q" for promotion
     * @author Divy Pratel
     */
    private static Move basicPlySearch(Position position, int depth) {
        int highestVal = Integer.MIN_VALUE;
        int lowestVal = Integer.MAX_VALUE;
        int currentVal;

        Move bestMove = null;

        // get all possible next moves
        List<Move> possible = position.getLegalMoves();

        // go through all possible moves
        for (Move move : possible) {
            // update position
            Position newPosition = position.makeMove(move);

            // min for next if white
            if (newPosition.whiteToMove) {
                currentVal = min(newPosition, depth - 1);
            } else {
                currentVal = max(newPosition, depth - 1);
            }

            // update highest/lowest value
            if (newPosition.whiteToMove) {
                if (currentVal > highestVal) {
                    highestVal = currentVal;
                    bestMove = move;
                }
            } else {
                if (currentVal < lowestVal) {
                    lowestVal = currentVal;
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }

    /**
     * Minimizer for the mini-max search algorithm.
     *
     * @param position the position to minimize the score for.
     * @param depth the depth remaining to search
     * @return the minimized value of the position
     */
    private static int min(Position position, int depth) {
        if (depth == 0) {
            return position.getEvaluation();
        }

        int lowestScore = Integer.MAX_VALUE;
        // generate all possible moves
        List<Move> possible = position.getLegalBlackMoves();

        for (Move move : possible) {
            // update position
            Position newPosition = position.makeMove(move);
            // score the new position
            int score = max(newPosition, depth - 1);
            if (score < lowestScore) {
                lowestScore = score;
            }
        }
        return lowestScore;
    }

    /**
     * Maximizer for the mini-max search algorithm.
     *
     * @param position the position to maximize the score for.
     * @param depth the depth remaining to search
     * @return the maximized value of the position
     */
    private static int max(Position position, int depth) {
        if (depth == 0) {
            return position.getEvaluation();
        }

        int highestScore = Integer.MIN_VALUE;
        // generate all possible moves
        List<Move> possible = position.getLegalWhiteMoves();

        for (Move move : possible) {
            // update position
            Position newPosition = position.makeMove(move);
            // score the new position
            int score = min(position, depth - 1);
            if (score > highestScore) {
                highestScore = score;
            }
        }
        return highestScore;
    }

}

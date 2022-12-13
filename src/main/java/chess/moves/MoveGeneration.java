package chess.moves;

import chess.board.Position;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static chess.Constants.*;

public class MoveGeneration {

    /**
     * Calculates the best move for the current position.
     *
     * @param position the position to calculate the best move for
     * @return the best move
     */
    public static Move getBestMove(Position position) {
        int depth = MAX_DEPTH;
        return simpleMiniMaxSearch(position, depth);
    }

    /**
     * Calculates the best move for given position.
     * Uses a basic <a href="https://www.chessprogramming.org/Minimax">minimax algorithm</a>
     *
     * @param position the position to calculate the best move for.
     * @return the best move found with basic mini-max search
     * @author Divy Patel
     */
    private static Move simpleMiniMaxSearch(@NotNull Position position, int depth) {
        int highestVal = Integer.MIN_VALUE;
        int lowestVal = Integer.MAX_VALUE;
        int currentVal;

        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        Move bestMove = null;

        // get all possible next moves
        List<Move> possible = position.getLegalMoves();

        // go through all possible moves
        for (Move move : possible) {
            // update position
            Position newPosition = position.makeMove(move);

            // if white minimize, if black maximize
            currentVal = position.whiteToMove ? min(newPosition, depth - 1, alpha, beta) :
                    max(newPosition, depth - 1, alpha, beta);

            // update highest/lowest value
            if (position.whiteToMove) {
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
     * @author Divy Patel
     */
    private static int min(Position position, int depth, int alpha, int beta) {
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
            int score = max(newPosition, depth - 1, alpha, beta);
            lowestScore = Math.min(lowestScore, score);
            beta = Math.min(beta, score);
            if (beta <= alpha) {
                break;
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
     * @author Divy Patel
     */
    private static int max(Position position, int depth, int alpha, int beta) {
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
            int score = min(newPosition, depth - 1, alpha, beta);
            highestScore = Math.max(highestScore, score);
            alpha = Math.max(alpha, score);
            if (beta <= alpha) {
                break;
            }
        }
        return highestScore;
    }

}

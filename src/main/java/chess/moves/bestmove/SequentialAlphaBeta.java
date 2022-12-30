package chess.moves.bestmove;

import chess.board.Position;
import chess.moves.Move;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SequentialAlphaBeta {

    /**
     * Calculates the best move for given position.
     * Uses a basic <a href="https://www.chessprogramming.org/Alpha-Beta">alpha-beta algorithm</a>
     *
     * @param position the position to calculate the best move for.
     * @return the best move found with basic mini-max search with alpha-beta pruning
     * @author Divy Patel
     */
    public static Move find(@NotNull Position position, int depth) {
        int highestVal = Integer.MIN_VALUE;
        int lowestVal = Integer.MAX_VALUE;
        int score;

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
            score = position.whiteToMove ? min(newPosition, depth - 1, alpha, beta) :
                    max(newPosition, depth - 1, alpha, beta);

            System.out.println("  " + move + " = " + score);

            // update highest/lowest and alpha/beta values
            if (position.whiteToMove) {
                if (score > highestVal) {
                    highestVal = score;
                    bestMove = move;
                }
                alpha = Math.max(alpha, score);
            } else {
                if (score < lowestVal) {
                    lowestVal = score;
                    bestMove = move;
                }
                beta = Math.min(beta, score);
            }
        }
        return bestMove;
    }

    /**
     * Minimizer for the mini-max search algorithm.
     *
     * @param position the position to minimize the score for.
     * @param depth    the depth remaining to search
     * @return the minimized value of the position
     * @author Divy Patel
     */
    protected static int min(Position position, int depth, int alpha, int beta) {
        if (depth <= 0) {
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
            // update beta
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
     * @param depth    the depth remaining to search
     * @return the maximized value of the position
     * @author Divy Patel
     */
    protected static int max(Position position, int depth, int alpha, int beta) {
        if (depth <= 0) {
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
            // update alpha
            alpha = Math.max(alpha, score);
            if (beta <= alpha) {
                break;
            }
        }
        return highestScore;
    }

}

package chess.moves.bestmove;

import chess.board.Position;
import chess.moves.Move;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MiniMax {

    /**
     * Calculates the best move for given position.
     * Uses a basic <a href="https://www.chessprogramming.org/Minimax">minimax algorithm</a>
     *
     * @param position the position to calculate the best move for.
     * @return the best move found with basic mini-max search
     * @author Divy Patel
     */
    public static Move miniMaxSearch(@NotNull Position position, int depth) {
        int highestVal = Integer.MIN_VALUE;
        int lowestVal = Integer.MAX_VALUE;
        int score;

        Move bestMove = null;

        // get all possible next moves
        List<Move> possible = position.getLegalMoves();

        // go through all possible moves
        for (Move move : possible) {
            // update position
            Position newPosition = position.makeMove(move);

            // if white minimize, if black maximize
            score = position.whiteToMove ? min(newPosition, depth - 1) : max(newPosition, depth - 1);

            // update highest/lowest value
            if (position.whiteToMove) {
                if (score > highestVal) {
                    highestVal = score;
                    bestMove = move;
                }
            } else {
                if (score < lowestVal) {
                    lowestVal = score;
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
            lowestScore = Math.min(lowestScore, score);
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
            int score = min(newPosition, depth - 1);
            highestScore = Math.max(highestScore, score);
        }
        return highestScore;
    }

}

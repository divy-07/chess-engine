package chess.moves.bestmove;

import chess.board.Position;
import chess.moves.Move;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import static chess.Constants.*;

public class ThreadedMiniMax extends  RecursiveTask<Integer> {

    public static final int SEQUENTIAL_CUTOFF = 2;

    /**
     * Calculates the best move for given position.
     * Uses a basic <a href="https://www.chessprogramming.org/Minimax">minimax algorithm</a>
     * with threading.
     *
     * @param position the position to calculate the best move for.
     * @return the best move found with basic mini-max search
     * @author Divy Patel
     */
    public static Move find(@NotNull Position position, int depth) {
        // get all legal moves the root position and makes thread for each one
        // this method is called first
        int highestVal = Integer.MIN_VALUE;
        int lowestVal = Integer.MAX_VALUE;

        Move bestMove = null;

        // get all possible next moves
        List<Move> possible = position.getLegalMoves();
        List<Integer> scores = new ArrayList<>();

        // go through all possible moves and score them
        for (Move move : possible) {
            // update position
            Position newPosition = position.makeMove(move);

            // make a thread for each move
            scores.add(commonPool.invoke(new ThreadedMiniMax(newPosition, depth - 1)));
        }

        // TODO: make sure we only get here when all threads are done (join?)
        // get best move from all scores
        int score;
        for (int i = 0; i < scores.size(); i++) {
            score = scores.get(i);
            if (position.whiteToMove) {
                if (score > highestVal) {
                    highestVal = score;
                    bestMove = possible.get(i);
                }
            } else {
                if (score < lowestVal) {
                    lowestVal = score;
                    bestMove = possible.get(i);
                }
            }
        }

        return bestMove;
    }

    private final Position position;
    private final int depth;

    /**
     * Constructor for the threaded mini-max search algorithm.
     *
     * @param position the position to evaluate the score for.
     * @param depth the depth remaining to search.
     *              Before passing depth, subtract 1 from it.
     */
    private ThreadedMiniMax(@NotNull Position position, int depth) {
        this.position = position;
        this.depth = depth;
    }

    protected Integer compute() {
        return position.whiteToMove ? max(position, depth) : min(position, depth);
    }

    /**
     * Minimizer for the mini-max search algorithm.
     * Switches to sequential mini-max when depth is 2.
     *
     * @param position the position to minimize the score for.
     * @param depth the depth remaining to search
     * @return the minimized value of the position
     * @author Divy Patel
     */
    private int min(Position position, int depth) {
        if (depth == 0) {
            return position.getEvaluation();
        } else if (depth == SEQUENTIAL_CUTOFF) {
            return SequentialMiniMax.min(position, depth);
        }

        int lowestScore = Integer.MAX_VALUE;
        // generate all possible moves
        List<Move> possible = position.getLegalBlackMoves();
        List<ThreadedMiniMax> threads = new ArrayList<>();

        for (Move move : possible) {
            // update position
            Position newPosition = position.makeMove(move);
            // make a thread for each move
            ThreadedMiniMax thread = new ThreadedMiniMax(newPosition, depth - 1);
            threads.add(thread);
            thread.fork();
        }

        // find the lowest score
        for (ThreadedMiniMax thread : threads) {
            lowestScore = Math.min(lowestScore, thread.join());
        }

        return lowestScore;
    }

    /**
     * Maximizer for the mini-max search algorithm.
     * Switches to sequential mini-max when depth is 2.
     *
     * @param position the position to maximize the score for.
     * @param depth the depth remaining to search
     * @return the maximized value of the position
     * @author Divy Patel
     */
    private int max(Position position, int depth) {
        if (depth == 0) {
            return position.getEvaluation();
        } else if (depth <= SEQUENTIAL_CUTOFF) {
            return SequentialMiniMax.max(position, depth);
        }

        int highestScore = Integer.MIN_VALUE;
        // generate all possible moves
        List<Move> possible = position.getLegalWhiteMoves();
        List<ThreadedMiniMax> threads = new ArrayList<>();

        for (Move move : possible) {
            // update position
            Position newPosition = position.makeMove(move);
            // make a thread for each move
            ThreadedMiniMax thread = new ThreadedMiniMax(newPosition, depth - 1);
            threads.add(thread);
            thread.fork();
        }

        // find the highest score
        for (ThreadedMiniMax thread : threads) {
            highestScore = Math.max(highestScore, thread.join());
        }

        return highestScore;
    }

}

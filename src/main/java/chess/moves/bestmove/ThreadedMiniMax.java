package chess.moves.bestmove;

import chess.board.Position;
import chess.moves.Move;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import static chess.Constants.*;

public class ThreadedMiniMax extends RecursiveTask<Integer> {

    public static final int SEQUENTIAL_CUTOFF = 3;

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
        int score;

        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        Move bestMove = null;

        // get all possible next moves
        List<Move> possible = position.getLegalMoves();

        // go through all possible moves and score them
        for (Move move : possible) {
            // update position
            Position newPosition = position.makeMove(move);

            // find score for each move
            score = commonPool.invoke(new ThreadedMiniMax(newPosition, depth - 1, alpha, beta));

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

            // break for alpha beta pruning
            if (beta <= alpha) {
                break;
            }
        }

        return bestMove;
    }

    private final Position position;
    private final int depth;
    private int alpha;
    private int beta;

    /**
     * Constructor for the threaded mini-max search algorithm with alpha-beta pruning.
     *
     * @param position the position to evaluate the score for.
     * @param depth    the depth remaining to search.
     *                 Before passing depth, subtract 1 from it.
     * @param alpha    the alpha value.
     * @param beta     the beta value.
     */
    private ThreadedMiniMax(@NotNull Position position, int depth, int alpha, int beta) {
        this.position = position;
        this.depth = depth;
        this.alpha = alpha;
        this.beta = beta;
    }

    /**
     * Constructor for the threaded mini-max search algorithm without alpha-beta pruning.
     * The alpha-beta pruning values are set to the default values.
     *
     * @param position the position to evaluate the score for.
     * @param depth    the depth remaining to search.
     *                 Before passing depth, subtract 1 from it.
     */
    private ThreadedMiniMax(@NotNull Position position, int depth) {
        this.position = position;
        this.depth = depth;
        this.alpha = Integer.MIN_VALUE;
        this.beta = Integer.MAX_VALUE;
    }

    protected Integer compute() {
        return position.whiteToMove ? max(position, depth, alpha, beta) :
                min(position, depth, alpha, beta);
    }

    /**
     * Minimizer for the mini-max search algorithm.
     * Switches to sequential mini-max when depth is 2.
     *
     * @param position the position to minimize the score for.
     * @param depth    the depth remaining to search
     * @return the minimized value of the position
     * @author Divy Patel
     */
    private int min(Position position, int depth, int alpha, int beta) {
        if (depth == 0) {
            return position.getEvaluation();
        } else if (depth <= SEQUENTIAL_CUTOFF) {
            return SequentialAlphaBeta.min(position, depth, alpha, beta);
        }

        int lowestScore = Integer.MAX_VALUE;
        // generate all possible moves
        List<Move> possible = position.getLegalBlackMoves();
        List<ThreadedMiniMax> threads = new ArrayList<>();

        for (Move move : possible) {
            // update position
            Position newPosition = position.makeMove(move);
            // make a thread for each move
            ThreadedMiniMax thread = new ThreadedMiniMax(newPosition, depth - 1, alpha, beta);
            threads.add(thread);
            thread.fork();
        }

        // find the lowest score
        int score;
        for (ThreadedMiniMax thread : threads) {
            score = thread.join();
            lowestScore = Math.min(lowestScore, score);
            beta = Math.min(beta, score);
        }

        return lowestScore;
    }

    /**
     * Maximizer for the mini-max search algorithm.
     * Switches to sequential mini-max when depth is 2.
     *
     * @param position the position to maximize the score for.
     * @param depth    the depth remaining to search
     * @return the maximized value of the position
     * @author Divy Patel
     */
    private int max(Position position, int depth, int alpha, int beta) {
        if (depth == 0) {
            return position.getEvaluation();
        } else if (depth <= SEQUENTIAL_CUTOFF) {
            return SequentialAlphaBeta.max(position, depth, alpha, beta);
        }

        int highestScore = Integer.MIN_VALUE;
        // generate all possible moves
        List<Move> possible = position.getLegalWhiteMoves();
        List<ThreadedMiniMax> threads = new ArrayList<>();

        for (Move move : possible) {
            // update position
            Position newPosition = position.makeMove(move);
            // make a thread for each move
            ThreadedMiniMax thread = new ThreadedMiniMax(newPosition, depth - 1, alpha, beta);
            threads.add(thread);
            thread.fork();
        }

        // find the highest score
        int score;
        for (ThreadedMiniMax thread : threads) {
            score = thread.join();
            highestScore = Math.max(highestScore, score);
            alpha = Math.max(alpha, score);
        }

        return highestScore;
    }

}

package chess.moves.bestmove;

import chess.board.Position;

import static chess.Constants.*;

import chess.moves.Move;

public class MoveGeneration {

    /**
     * Calculates the best move for the current position.
     *
     * @param position the position to calculate the best move for
     * @return the best move
     */
    public static Move getBestMove(Position position) {
        int depth = MAX_DEPTH;
        return AlphaBeta.alphaBetaSearch(position, depth);
    }

}

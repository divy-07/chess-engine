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
        /*
         * Call the find() method from any of the classes in the chess.moves.bestmove package.
         * The find() method will return the best move for the given position,
         * using the algorithm implemented in that class.
         * You can change the algorithm used by changing the class called here.
         * You can also change the depth of the search by changing the depth parameter here,
         * or in CONSTANTS.java if you want teh change to be permanent.
         * Typically, the higher the depth, the longer the search will take.
         * Do not set the depth to a value too high, as the search will take too long.
         */
        int depth = MAX_DEPTH;
        return ThreadedMiniMax.find(position, depth);
    }

}

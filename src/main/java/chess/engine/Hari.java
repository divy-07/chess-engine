package chess.engine;

import chess.board.Position;
import chess.communications.UCI;

public class Hari {

    // current state - used for calculating the best move
    public static Position position = Position.startingPosition();

    public static void main(String[] args) {
        // start communication with GUI
        UCI.uciCommunication();
    }

}

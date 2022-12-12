package chess.engine;

import chess.board.Position;
import chess.communications.UCI;

public class Hari {

    /**
     * Current state: used for calculating the best move.<br>
     * This field should only be updated by UCI.java,
     * though it can be read from anywhere.
     */
    public static Position position = Position.startingPosition();

    public static void main(String[] args) {
        // start communication with GUI
        UCI.uciCommunication();
    }

}

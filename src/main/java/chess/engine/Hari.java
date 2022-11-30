package chess.engine;

import chess.communications.UCI;

public class Hari {

    // current state - used for calculating the best move
    public static long WP = 0L, WN = 0L, WB = 0L, WR = 0L, WQ = 0L, WK = 0L,
            BP = 0L, BN = 0L, BB = 0L, BR = 0L, BQ = 0L, BK = 0L, EP = 0L;
    public static boolean CWK = true, CWQ = true, CBK = true, CBQ = true,
            whiteToMove = true;
    public static int halfMoveCount;
    public static int fullMoveCount;

    public static void main(String[] args) {
        // start communication with GUI
        UCI.uciCommunication();
    }

}

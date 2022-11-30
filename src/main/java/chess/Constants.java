package chess;

public final class Constants {

    // Engine name
    public static final String ENGINE_NAME = "Hari";

    // board constants
    public static final long FILE_A = 72340172838076673L;
    public static final long FILE_H = -9187201950435737472L;
    public static final long FILE_AB = 217020518514230019L;
    public static final long FILE_GH = -4557430888798830400L;
    public static final long RANK_1 = -72057594037927936L;
    public static final long RANK_4 = 1095216660480L;
    public static final long RANK_5 = 4278190080L;
    public static final long RANK_8 = 255L;
    public static final long CENTRE = 103481868288L;
    public static final long EXTENDED_CENTRE = 66229406269440L;
    public static final long KING_SIDE = -1085102592571150096L;
    public static final long QUEEN_SIDE = 1085102592571150095L;

    // starting position bitboards
    public static final long startWP = 71776119061217280L;
    public static final long startWN = 4755801206503243776L;
    public static final long startWB = 2594073385365405696L;
    public static final long startWR = -9151314442816847872L;
    public static final long startWQ = 576460752303423488L;
    public static final long startWK = 1152921504606846976L;
    public static final long startBP = 65280L;
    public static final long startBN = 66L;
    public static final long startBB = 36L;
    public static final long startBR = 129L;
    public static final long startBQ = 8L;
    public static final long startBK = 16L;

    public static long[] files = {
            0x101010101010101L, 0x202020202020202L, 0x404040404040404L, 0x808080808080808L,
            0x1010101010101010L, 0x2020202020202020L, 0x4040404040404040L, 0x8080808080808080L
    };
}

package chess;

public final class Constants {

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

    // destination squares for king and knight moves
    public static long KING_SPAN=460039L;
    public static long KNIGHT_SPAN=43234889994L;

    // starting position bitboards
    public static long startWP = 71776119061217280L;
    public static long startWN = 4755801206503243776L;
    public static long startWB = 2594073385365405696L;
    public static long startWR = -9151314442816847872L;
    public static long startWQ = 576460752303423488L;
    public static long startWK = 1152921504606846976L;
    public static long startBP = 65280L;
    public static long startBN = 66L;
    public static long startBB = 36L;
    public static long startBR = 129L;
    public static long startBQ = 8L;
    public static long startBK = 16L;


    /**
     * Bitboards for files from A -> H; length = 8
     */
    public static long[] files = {
            0x101010101010101L, 0x202020202020202L, 0x404040404040404L, 0x808080808080808L,
            0x1010101010101010L, 0x2020202020202020L, 0x4040404040404040L, 0x8080808080808080L
    };

    /**
     * Bitboards for ranks from 8 -> 1; length = 8
     */
    public static long[] ranks = {
            0xFFL, 0xFF00L, 0xFF0000L, 0xFF000000L, 0xFF00000000L, 0xFF0000000000L, 0xFF000000000000L, 0xFF00000000000000L
    };

    /**
     * Bitboards for top left to bottom right diagonals.<br>
     * Includes the diagonals, in order:
     * a8-a8, a7-b8, a6-c8, a5-d8, a4-e8, a3-f8, a2-g8, a1-h8, b1-h7, c1-h6, d1-h5, e1-h4, f1-h3, g1-h2, h1-h1
     */
    public static long[] DiagonalMasks8 = {
            0x1L, 0x102L, 0x10204L, 0x1020408L, 0x102040810L, 0x10204081020L, 0x1020408102040L,
            0x102040810204080L, 0x204081020408000L, 0x408102040800000L, 0x810204080000000L,
            0x1020408000000000L, 0x2040800000000000L, 0x4080000000000000L, 0x8000000000000000L
    };

    /**
     * Bitboards for top right to bottom left diagonals.<br>
     * Includes the diagonals, in order:
     * h8-h8, g8-h7, f8-h6, e8-h5, d8-h4, c8-h3, b8-h2, a8-h1, a7-g1, a6-f1, a5-e1, a4-d1, a3-c1, a2-b1, a1-a1
     */
    public static long[] AntiDiagonalMasks8 = {
            0x80L, 0x8040L, 0x804020L, 0x80402010L, 0x8040201008L, 0x804020100804L, 0x80402010080402L,
            0x8040201008040201L, 0x4020100804020100L, 0x2010080402010000L, 0x1008040201000000L,
            0x804020100000000L, 0x402010000000000L, 0x201000000000000L, 0x100000000000000L
    };

}

package chess.moves;

/**
 * An immutable class that represents a chess move.
 * A move is represented by a from square, a to square, and (optionally) a promotion piece.
 * The standard notation of files and ranks is used.
 */
public class Move {

    // representation of the move
    private final int sourceRank, sourceFile, destRank, destFile;

    // promotion move
    public boolean isPromotion;
    private char promotionPiece;

    // en passant move
    public boolean isEnPassant;

    /**
     * Creates a new move with source and destination squares.
     */
    public Move(int sourceRank, int sourceFile, int destRank, int destFile) {
        this.sourceRank = sourceRank;
        this.sourceFile = sourceFile;
        this.destRank = destRank;
        this.destFile = destFile;
        this.isPromotion = false;
        this.isEnPassant = false;
    }

    /**
     * Creates a new promotion move with source, destination, and a promotion piece.
     */
    public Move(int sourceRank, int sourceFile, int destRank, int destFile, char promotionPiece) {
        this(sourceRank, sourceFile, destRank, destFile);
        this.promotionPiece = promotionPiece;
        this.isPromotion = true;
        this.isEnPassant = false;
    }

    /**
     * Creates a new en passant move with source, destination, and a promotion piece.
     */
    public Move(int sourceRank, int sourceFile, int destRank, int destFile, boolean isEnPassant) {
        this(sourceRank, sourceFile, destRank, destFile);
        this.isPromotion = false;
        this.isEnPassant = isEnPassant;
    }

    public static Move fromAlgebraicNotation(String move) {
        // TODO: implement
        return null;
    }

    public String toEngineNotation() {
        // TODO: implement
        return null;
    }

    public String toAlgebraicNotation() {
        // TODO: implement
        return null;
    }

}

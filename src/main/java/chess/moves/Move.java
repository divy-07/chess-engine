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
        this.promotionPiece = ' ';
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
        this.promotionPiece = ' ';
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return sourceRank == move.sourceRank &&
                sourceFile == move.sourceFile &&
                destRank == move.destRank &&
                destFile == move.destFile &&
                isPromotion == move.isPromotion &&
                promotionPiece == move.promotionPiece &&
                isEnPassant == move.isEnPassant;
    }

    @Override
    public int hashCode() {
        int result = sourceRank;
        result = 31 * result + sourceFile;
        result = 31 * result + destRank;
        result = 31 * result + destFile;
        result = 31 * result + (isPromotion ? 1 : 0);
        result = 31 * result + (int) promotionPiece;
        result = 31 * result + (isEnPassant ? 1 : 0);
        return result;
    }

}

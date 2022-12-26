package chess.moves;

/**
 * An immutable class that represents a chess move.
 * A move is represented by: 1) from square, 2) to square, and 2) optional promotion piece.<p>
 * The standard notation of files and ranks is used.
 * But ranks are numbered from 0 to 7 instead of a to h.
 * And files are numbered from 0 to 7 instead of 8 to 1. (NOTE THE INVERSION)
 */
public class Move {

    // representation of the move
    public final int sourceRank, sourceFile, destRank, destFile;

    // promotion move
    public final boolean isPromotion;
    public final char promotionPiece;

    // en passant move
    public final boolean isEnPassant;

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
        this.sourceRank = sourceRank;
        this.sourceFile = sourceFile;
        this.destRank = destRank;
        this.destFile = destFile;
        this.promotionPiece = promotionPiece;
        this.isPromotion = true;
        this.isEnPassant = false;
    }

    /**
     * Creates a new en passant move with source, destination, and en passant boolean.
     */
    public Move(int sourceRank, int sourceFile, int destRank, int destFile, boolean isEnPassant) {
        this.sourceRank = sourceRank;
        this.sourceFile = sourceFile;
        this.destRank = destRank;
        this.destFile = destFile;
        this.promotionPiece = ' ';
        this.isPromotion = false;
        this.isEnPassant = isEnPassant;
    }

    /**
     * Creates a move from a string in algebraic notation
     *
     * @param move the move in algebraic notation
     * @return the Move object
     */
    public static Move fromAlgebraicNotation(String move) {
        // TODO (#20): implement
        // once this is implemented, we can make significant changes to how we parse moves from UCI
        // as well as how we make moves given from UCI
        // major changes will be in MoveConversion.java especially in the applyAlgebraMoves method
        return null;
    }

    /**
     * Converts this move into a string in engine notation.
     * See ~/docs/terms.md for more information on engine notation.
     *
     * @return the move in engine notation
     */
    public String toEngineNotation() {
        StringBuilder sb = new StringBuilder();

        if (!isPromotion && !isEnPassant) {
            // normal move
            sb.append(sourceRank);
            sb.append(sourceFile);
            sb.append(destRank);
            sb.append(destFile);
        } else if (isPromotion) {
            // promotion move
            sb.append(sourceFile);
            sb.append(destFile);
            sb.append(promotionPiece);
            sb.append(sourceRank == 1 ? 'P' : 'p');
        } else {
            // en passant move
            sb.append(sourceFile);
            sb.append(destFile);
            sb.append(sourceRank == 3 ? 'W' : 'B');
            sb.append('e');
        }
        return sb.toString();
    }

    /**
     * Converts this move into a string in algebraic notation.
     * See ~/docs/terms.md for more information on algebraic notation.
     *
     * @return the move in algebraic notation
     */
    public String toAlgebraicNotation() {
        return MoveConversion.moveToAlgebra(toEngineNotation());
    }

    /**
     * @return whether this move is king side castling
     */
    public boolean isKingSideCastling() {
        return (sourceRank == 0 && sourceFile == 4 && destRank == 0 && destFile == 6) ||
                (sourceRank == 7 && sourceFile == 4 && destRank == 7 && destFile == 6);
    }

    /**
     * @return whether this move is queen side castling
     */
    public boolean isQueenSideCastling() {
        return (sourceRank == 0 && sourceFile == 4 && destRank == 0 && destFile == 2) ||
                (sourceRank == 7 && sourceFile == 4 && destRank == 7 && destFile == 2);
    }

    /**
     * Compares this move to given object.
     * If the given object is a move, then the comparison is based on:
     * 1) source square, 2) destination square, 3) promotion piece, and 4) en passant boolean.
     *
     * @param o the object to compare to
     * @return true if the given object is a move and is equal to this move as stated, false otherwise
     */
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

    /**
     * @return a hash code for this move based on
     * the source square, destination square, promotion piece, and en passant boolean
     */
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

    /**
     * @return a string representation of this move in algebraic notation
     */
    @Override
    public String toString() {
        return toAlgebraicNotation();
    }

}

package chess.moves;

import chess.board.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.Constants.*;

public class PossibleMoves {

    // Credit to Jonathan Warkentin for most of the code in this class
    // docs are ours

    // useful trackers
    private final long notMyPieces; // every square except my pieces + opponent's king
    private final long myPieces; // squares with my pieces - my king
    private final long occupiedSquares; // squares with pieces
    private final long emptySquares; // squares without any pieces

    public PossibleMoves(long notMyPieces, long myPieces, long occupiedSquares, long emptySquares) {
        this.notMyPieces = notMyPieces;
        this.myPieces = myPieces;
        this.occupiedSquares = occupiedSquares;
        this.emptySquares = emptySquares;
    }

    /**
     * Returns all possible pawn moves for white.
     *
     * @param wp white pawn bitboard
     * @param bp black pawn bitboard
     * @param ep en passant bitboard - has 1 for pawn that was moved 2 squares
     * @return a list of all possible pawn moves.<br>
     * - The order of moves is as follows: single pushes, double pushes, right captures, left captures, promotions, en passant captures.
     * - Further, the moves are sorted from top to bottom and left to right.
     * - Promotion moves are sorted by piece type: queen, rook, bishop, knight.
     */
    public List<Move> possibleWP(long wp, long bp, long ep) {
        /*
         * moves: stores all moves to be returned
         *
         * pawnMoves represents all possible pawns tha can move according to the current condition
         * possibility is a bitboard that represents all possible moves for the current condition
         * possibility = 0, when there are no possible moves for the current condition
         */

        List<Move> moves = new ArrayList<>();
        long pawnMoves, possibility;

        // Single push
        pawnMoves = (wp >> 8) & emptySquares & ~RANK_8;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 + 1, currPos % 8, currPos / 8, currPos % 8));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Double push
        pawnMoves = (wp >> 16) & emptySquares & (emptySquares >> 8) & RANK_4;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 + 2, currPos % 8, currPos / 8, currPos % 8));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Capture right
        pawnMoves = (wp >> 7) & notMyPieces & occupiedSquares & ~RANK_8 & ~FILE_A;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 + 1, currPos % 8 - 1, currPos / 8, currPos % 8));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Capture left
        pawnMoves = (wp >> 9) & notMyPieces & occupiedSquares & ~RANK_8 & ~FILE_H;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 + 1, currPos % 8 + 1, currPos / 8, currPos % 8));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Promotion straight
        pawnMoves = (wp >> 8) & emptySquares & RANK_8;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 + 1, currPos % 8, currPos / 8, currPos % 8, 'Q'));
            moves.add(new Move(currPos / 8 + 1, currPos % 8, currPos / 8, currPos % 8, 'R'));
            moves.add(new Move(currPos / 8 + 1, currPos % 8, currPos / 8, currPos % 8, 'B'));
            moves.add(new Move(currPos / 8 + 1, currPos % 8, currPos / 8, currPos % 8, 'N'));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Promotion capture right
        pawnMoves = (wp >> 7) & notMyPieces & occupiedSquares & RANK_8 & ~FILE_A;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 + 1, currPos % 8 - 1, currPos / 8, currPos % 8, 'Q'));
            moves.add(new Move(currPos / 8 + 1, currPos % 8 - 1, currPos / 8, currPos % 8, 'R'));
            moves.add(new Move(currPos / 8 + 1, currPos % 8 - 1, currPos / 8, currPos % 8, 'B'));
            moves.add(new Move(currPos / 8 + 1, currPos % 8 - 1, currPos / 8, currPos % 8, 'N'));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Promotion capture left
        pawnMoves = (wp >> 9) & notMyPieces & occupiedSquares & RANK_8 & ~FILE_H;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 + 1, currPos % 8 + 1, currPos / 8, currPos % 8, 'Q'));
            moves.add(new Move(currPos / 8 + 1, currPos % 8 + 1, currPos / 8, currPos % 8, 'R'));
            moves.add(new Move(currPos / 8 + 1, currPos % 8 + 1, currPos / 8, currPos % 8, 'B'));
            moves.add(new Move(currPos / 8 + 1, currPos % 8 + 1, currPos / 8, currPos % 8, 'N'));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // En passant capture right
        possibility = (wp << 1) & bp & RANK_5 & ~FILE_A & ep;
        if (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8, currPos % 8 - 1, currPos / 8 - 1, currPos % 8, true));
        }

        // En passant capture left
        possibility = (wp >> 1) & bp & RANK_5 & ~FILE_H & ep;
        if (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8, currPos % 8 + 1, currPos / 8 - 1, currPos % 8, true));
        }

        return moves;
    }

    /**
     * Returns all possible pawn moves for black.
     *
     * @param bp black pawn bitboard
     * @param wp white pawn bitboard
     * @param ep en passant bitboard - has 1 for pawn that was moved 2 squares
     * @return a list with all possible pawn moves.<br>
     * - The order of moves is as follows: single pushes, double pushes, right captures, left captures, promotions, en passant captures.
     * - Further, the moves are sorted from top to bottom and left to right.
     * - Promotion moves are sorted by piece type: queen, rook, bishop, knight.<br>
     */
    public List<Move> possibleBP(long bp, long wp, long ep) {
        // similar to possibleWP, but with black pieces

        List<Move> moves = new ArrayList<>();
        long pawnMoves, possibility;

        // Single push
        pawnMoves = (bp << 8) & emptySquares & ~RANK_1;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 - 1, currPos % 8, currPos / 8, currPos % 8));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Double push
        pawnMoves = (bp << 16) & emptySquares & (emptySquares << 8) & RANK_5;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 - 2, currPos % 8, currPos / 8, currPos % 8));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Capture right
        pawnMoves = (bp << 7) & notMyPieces & occupiedSquares & ~RANK_1 & ~FILE_H;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 - 1, currPos % 8 + 1, currPos / 8, currPos % 8));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Capture left
        pawnMoves = (bp << 9) & notMyPieces & occupiedSquares & ~RANK_1 & ~FILE_A;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 - 1, currPos % 8 - 1, currPos / 8, currPos % 8));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Promotion straight
        pawnMoves = (bp << 8) & emptySquares & RANK_1;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 - 1, currPos % 8, currPos / 8, currPos % 8, 'q'));
            moves.add(new Move(currPos / 8 - 1, currPos % 8, currPos / 8, currPos % 8, 'r'));
            moves.add(new Move(currPos / 8 - 1, currPos % 8, currPos / 8, currPos % 8, 'b'));
            moves.add(new Move(currPos / 8 - 1, currPos % 8, currPos / 8, currPos % 8, 'n'));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Promotion capture right
        pawnMoves = (bp << 7) & notMyPieces & occupiedSquares & RANK_1 & ~FILE_H;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 - 1, currPos % 8 + 1, currPos / 8, currPos % 8, 'q'));
            moves.add(new Move(currPos / 8 - 1, currPos % 8 + 1, currPos / 8, currPos % 8, 'r'));
            moves.add(new Move(currPos / 8 - 1, currPos % 8 + 1, currPos / 8, currPos % 8, 'b'));
            moves.add(new Move(currPos / 8 - 1, currPos % 8 + 1, currPos / 8, currPos % 8, 'n'));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // Promotion capture left
        pawnMoves = (bp << 9) & notMyPieces & occupiedSquares & RANK_1 & ~FILE_A;
        possibility = pawnMoves & -pawnMoves;
        while (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8 - 1, currPos % 8 - 1, currPos / 8, currPos % 8, 'q'));
            moves.add(new Move(currPos / 8 - 1, currPos % 8 - 1, currPos / 8, currPos % 8, 'r'));
            moves.add(new Move(currPos / 8 - 1, currPos % 8 - 1, currPos / 8, currPos % 8, 'b'));
            moves.add(new Move(currPos / 8 - 1, currPos % 8 - 1, currPos / 8, currPos % 8, 'n'));
            pawnMoves &= ~possibility;
            possibility = pawnMoves & -pawnMoves;
        }

        // En passant capture right
        possibility = (bp << 1) & wp & RANK_4 & ~FILE_A & ep;
        if (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8, currPos % 8 - 1, currPos / 8 + 1, currPos % 8, true));
        }

        // En passant capture left
        possibility = (bp >> 1) & wp & RANK_4 & ~FILE_H & ep;
        if (possibility != 0) {
            int currPos = Long.numberOfTrailingZeros(possibility);
            moves.add(new Move(currPos / 8, currPos % 8 + 1, currPos / 8 + 1, currPos % 8, true));
        }

        return moves;
    }

    /**
     * Returns a list with all possible moves for the knights.<br>
     *
     * @param knight The knight bitboard.
     * @return A list with all possible moves for the knights.
     * The moves are sorted from top to bottom and left to right.
     */
    public List<Move> possibleN(long knight) {
        List<Move> moves = new ArrayList<>();
        long i = knight & -knight;
        long possibility;

        // accounting for all both knights on the board
        while (i != 0) {
            int currPos = Long.numberOfTrailingZeros(i);
            if (currPos > 18) {
                possibility = KNIGHT_SPAN << (currPos - 18);
            } else {
                possibility = KNIGHT_SPAN >> (18 - currPos);
            }
            if (currPos % 8 < 4) {
                possibility &= ~FILE_GH & notMyPieces;
            } else {
                possibility &= ~FILE_AB & notMyPieces;
            }

            long j = possibility & -possibility;
            while (j != 0) {
                int index = Long.numberOfTrailingZeros(j);
                moves.add(new Move(currPos / 8, currPos % 8, index / 8, index % 8));
                possibility &= ~j;
                j = possibility & -possibility;
            }
            knight &= ~i;
            i = knight & -knight;
        }

        return moves;
    }

    /**
     * Returns all possible bishop moves.
     *
     * @param bishop the rook bitboard
     * @return a list with all possible bishop moves.
     * The moves are sorted from top to bottom and left to right.
     */
    public List<Move> possibleB(long bishop) {
        List<Move> moves = new ArrayList<>();
        long i = bishop & -bishop;
        long possibility;

        while (i != 0) {
            int currPos = Long.numberOfTrailingZeros(i);
            possibility = diagonalMoves(currPos) & notMyPieces;
            long j = possibility & -possibility;
            while (j != 0) {
                int index = Long.numberOfTrailingZeros(j);
                moves.add(new Move(currPos / 8, currPos % 8, index / 8, index % 8));
                possibility &= ~j;
                j = possibility & -possibility;
            }
            bishop &= ~i;
            i = bishop & -bishop;
        }

        return moves;
    }

    /**
     * Returns all possible rook moves.
     *
     * @param rook the rook bitboard
     * @return a list with all possible rook moves.
     * The moves are sorted from top to bottom and left to right.
     */
    public List<Move> possibleR(long rook) {
        List<Move> moves = new ArrayList<>();
        long i = rook & -rook;
        long possibility;

        while (i != 0) {
            int currPos = Long.numberOfTrailingZeros(i);
            possibility = horizontalAndVerticalMoves(currPos) & notMyPieces;
            long j = possibility & -possibility;
            while (j != 0) {
                int index = Long.numberOfTrailingZeros(j);
                moves.add(new Move(currPos / 8, currPos % 8, index / 8, index % 8));
                possibility &= ~j;
                j = possibility & -possibility;
            }
            rook &= ~i;
            i = rook & -rook;
        }

        return moves;
    }

    /**
     * Returns all possible queen moves.
     *
     * @param queen the queen bitboard
     * @return a list with all possible queen moves.<br>
     * - The primary move order is: diagonal moves then horizontal/vertical moves.
     * The moves are further sorted from top to bottom and left to right.
     */
    public List<Move> possibleQ(long queen) {
        List<Move> moves = possibleB(queen);
        moves.addAll(possibleR(queen));
        return moves;
    }

    /**
     * Returns all possible non-castling king moves.
     *
     * @param position the current position
     * @return a list with all possible non-castling king moves.<br>
     * - The moves are sorted from top to bottom and left to right.<br>
     * - Castling moves are not included.
     */
    public List<Move> possibleK(Position position) {
        List<Move> moves = new ArrayList<>();
        long possibility;
        long unsafe = position.whiteToMove ? unsafeForWhite(position) : unsafeForBlack(position);

        int currPos = position.whiteToMove ? Long.numberOfTrailingZeros(position.wk) :
                Long.numberOfTrailingZeros(position.bk);
        if (currPos > 9) {
            // if not in the 8th rank
            possibility = KING_SPAN << (currPos - 9);
        } else {
            possibility = KING_SPAN >> (9 - currPos);
        }
        if (currPos % 8 < 4) {
            possibility &= ~FILE_GH & notMyPieces;
        } else {
            possibility &= ~FILE_AB & notMyPieces;
        }

        possibility &= ~unsafe;

        long j = possibility & -possibility;
        while (j != 0) {
            int index = Long.numberOfTrailingZeros(j);
            moves.add(new Move(currPos / 8, currPos % 8, index / 8, index % 8));
            possibility &= ~j;
            j = possibility & -possibility;
        }

        return moves;
    }

    /**
     * Returns all possible castling moves for white.
     *
     * @param position the current position
     * @return a list with all possible castling moves for white.
     * The king side castling is always before the queen side castling in the list, if it exists.
     * The list will at most contain two moves.
     */
    public List<Move> possibleCW(Position position) {
        // TODO: change the parameter to not take Position object
        List<Move> moves = new ArrayList<>();
        long unsafe = unsafeForWhite(position);

        if ((unsafe & position.wk) == 0) {
            // king-side castle
            if (position.cwk && (((1L << CASTLE_ROOKS[0]) & position.wr) != 0)) {
                if (((occupiedSquares | unsafe) & ((1L << 61) | (1L << 62))) == 0) {
                    moves.add(new Move(7, 4, 7, 6));
                }
            }

            // queen-side castle
            if (position.cwq && (((1L << CASTLE_ROOKS[1]) & position.wr) != 0)) {
                if (((occupiedSquares | (unsafe & ~(1L << 57))) & ((1L << 57) | (1L << 58) | (1L << 59))) == 0) {
                    moves.add(new Move(7, 4, 7, 2));
                }
            }
        }

        return moves;
    }

    /**
     * Returns all possible castling moves for black.
     *
     * @param position the current position
     * @return a list with all possible castling moves for black.
     * The king side castling is always before the queen side castling in the list, if it exists.
     * The list will at most contain two moves.
     */
    public List<Move> possibleCB(Position position) {
        // TODO: change the parameter to not take Position object
        List<Move> moves = new ArrayList<>();
        long unsafe = unsafeForBlack(position);

        if ((unsafe & position.bk) == 0) {
            // king-side castle
            if (position.cbk && (((1L << CASTLE_ROOKS[2]) & position.br) != 0)) {
                if (((occupiedSquares | unsafe) & ((1L << 5) | (1L << 6))) == 0) {
                    moves.add(new Move(0, 4, 0, 6));
                }
            }

            // queen-side castle
            if (position.cbq && (((1L << CASTLE_ROOKS[3]) & position.br) != 0)) {
                if (((occupiedSquares | (unsafe & ~(1L << 1))) & ((1L << 1) | (1L << 2) | (1L << 3))) == 0) {
                    moves.add(new Move(0, 4, 0, 2));
                }
            }
        }

        return moves;
    }

    /**
     * Calculates the horizontal and vertical moves for a given square
     *
     * @param s the square to move from
     * @return bitboard of all possible vertical and horizontal moves from the square,
     * the destination squares are marked with a 1
     * @credit Jonathan Warkentin
     */
    private long horizontalAndVerticalMoves(int s) {
        long binaryS = 1L << s;
        long possibilitiesHorizontal = (occupiedSquares - 2 * binaryS) ^
                Long.reverse(Long.reverse(occupiedSquares) - 2 * Long.reverse(binaryS));
        long possibilitiesVertical = ((occupiedSquares & files[s % 8]) - (2 * binaryS)) ^
                Long.reverse(Long.reverse(occupiedSquares & files[s % 8]) - (2 * Long.reverse(binaryS)));
        return (possibilitiesHorizontal & ranks[s / 8]) | (possibilitiesVertical & files[s % 8]);
    }

    /**
     * Calculates the diagonal moves for a given square
     *
     * @param s the square to move from
     * @return bitboard of all possible diagonal moves from the square, the destination squares are marked with a 1
     * @credit Jonathan Warkentin
     */
    private long diagonalMoves(int s) {
        long binaryS = 1L << s;
        long possibilitiesDiagonal = ((occupiedSquares & diagonals[(s / 8) + (s % 8)]) - (2 * binaryS)) ^
                Long.reverse(Long.reverse(occupiedSquares & diagonals[(s / 8) + (s % 8)]) - (2 * Long.reverse(binaryS)));
        long possibilitiesAntiDiagonal = ((occupiedSquares & antiDiagonals[(s / 8) + 7 - (s % 8)]) - (2 * binaryS)) ^
                Long.reverse(Long.reverse(occupiedSquares & antiDiagonals[(s / 8) + 7 - (s % 8)]) - (2 * Long.reverse(binaryS)));
        return (possibilitiesDiagonal & diagonals[(s / 8) + (s % 8)]) |
                (possibilitiesAntiDiagonal & antiDiagonals[(s / 8) + 7 - (s % 8)]);
    }

    /**
     * Calculates the bitboard of all squares that are unsafe for the white king.
     *
     * @param position the current position
     * @return the bitboard of all squares that are unsafe for the white king.
     * @credit Jonathan Warkentin
     */
    private long unsafeForWhite(Position position) {
        long unsafe;
        long possibility;

        // pawn
        unsafe = ((position.bp << 7) & ~FILE_H); // pawn capture right
        unsafe |= ((position.bp << 9) & ~FILE_A); // pawn capture left

        // knight
        long bn = position.bn;
        long i = bn & -bn;
        while (i != 0) {
            int index = Long.numberOfTrailingZeros(i);
            if (index > 18) {
                possibility = KNIGHT_SPAN << (index - 18);
            } else {
                possibility = KNIGHT_SPAN >> (18 - index);
            }
            if (index % 8 < 4) {
                possibility &= ~FILE_GH;
            } else {
                possibility &= ~FILE_AB;
            }
            unsafe |= possibility;
            bn &= ~i;
            i = bn & -bn;
        }

        // bishop and queen diagonal
        long qb = position.bq | position.bb;
        i = qb & -qb;
        while (i != 0) {
            int index = Long.numberOfTrailingZeros(i);
            possibility = diagonalMoves(index);
            unsafe |= possibility;
            qb &= ~i;
            i = qb & -qb;
        }

        // rook and queen h/v moves
        long qr = position.bq | position.br;
        i = qr & -qr;
        while (i != 0) {
            int index = Long.numberOfTrailingZeros(i);
            possibility = horizontalAndVerticalMoves(index);
            unsafe |= possibility;
            qr &= ~i;
            i = qr & -qr;
        }

        // king
        int index = Long.numberOfTrailingZeros(position.bk);
        if (index > 9) {
            possibility = KING_SPAN << (index - 9);
        } else {
            possibility = KING_SPAN >> (9 - index);
        }
        if (index % 8 < 4) {
            possibility &= ~FILE_GH;
        } else {
            possibility &= ~FILE_AB;
        }
        unsafe |= possibility;

        return unsafe;
    }

    /**
     * Calculates the bitboard of all squares that are unsafe for the black king.
     *
     * @param position the current position
     * @return the bitboard of all squares that are unsafe for the black king.
     * @credit Jonathan Warkentin
     */
    private long unsafeForBlack(Position position) {
        long unsafe;
        long possibility;

        // pawn
        unsafe = ((position.wp >>> 7) & ~FILE_A); // pawn capture right
        unsafe |= ((position.wp >>> 9) & ~FILE_H); // pawn capture left

        // knight
        long wn = position.wn;
        long i = wn & -wn;
        while (i != 0) {
            int index = Long.numberOfTrailingZeros(i);
            if (index > 18) {
                possibility = KNIGHT_SPAN << (index - 18);
            } else {
                possibility = KNIGHT_SPAN >> (18 - index);
            }
            if (index % 8 < 4) {
                possibility &= ~FILE_GH;
            } else {
                possibility &= ~FILE_AB;
            }
            unsafe |= possibility;
            wn &= ~i;
            i = wn & -wn;
        }

        // bishop and queen diagonal
        long qb = position.wq | position.wb;
        i = qb & -qb;
        while (i != 0) {
            int index = Long.numberOfTrailingZeros(i);
            possibility = diagonalMoves(index);
            unsafe |= possibility;
            qb &= ~i;
            i = qb & -qb;
        }

        // rook and queen h/v moves
        long qr = position.wq | position.wr;
        i = qr & -qr;
        while (i != 0) {
            int index = Long.numberOfTrailingZeros(i);
            possibility = horizontalAndVerticalMoves(index);
            unsafe |= possibility;
            qr &= ~i;
            i = qr & -qr;
        }

        // king
        int index = Long.numberOfTrailingZeros(position.wk);
        if (index > 9) {
            possibility = KING_SPAN << (index - 9);
        } else {
            possibility = KING_SPAN >> (9 - index);
        }
        if (index % 8 < 4) {
            possibility &= ~FILE_GH;
        } else {
            possibility &= ~FILE_AB;
        }
        unsafe |= possibility;

        return unsafe;
    }

}

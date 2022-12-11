package chess.moves;

import chess.board.DebugBoard;
import chess.board.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingPossibleMoveTest {

    @Test
    public void test01NoKingMoves() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", "P", "P", "R"},
                {" ", " ", " ", " ", " ", "P", "K", "P"},
                {" ", " ", " ", " ", " ", "B", "B", "Q"},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        PossibleMoves.possibleMovesW(position);
        List<Move> actual = PossibleMoves.possibleK(boardArray[5]);
        List<Move> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

    @Test
    public void test02AllFreeSquares() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", "k", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        PossibleMoves.possibleMovesB(position);
        List<Move> actual = PossibleMoves.possibleK(boardArray[11]);

        assertTrue(actual.contains(new Move(3, 1, 2, 0)));
        assertTrue(actual.contains(new Move(3, 1, 2, 1)));
        assertTrue(actual.contains(new Move(3, 1, 2, 2)));
        assertTrue(actual.contains(new Move(3, 1, 3, 0)));
        assertTrue(actual.contains(new Move(3, 1, 3, 2)));
        assertTrue(actual.contains(new Move(3, 1, 4, 0)));
        assertTrue(actual.contains(new Move(3, 1, 4, 1)));
        assertTrue(actual.contains(new Move(3, 1, 4, 2)));
        assertEquals(8, actual.size());
    }

    @Test
    public void test03Capture() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "n", " ", " ", " ", " "},
                {" ", "K", "p", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        PossibleMoves.possibleMovesW(position);
        List<Move> actual = PossibleMoves.possibleK(boardArray[5]);

        assertTrue(actual.contains(new Move(3, 1, 2, 0)));
        assertTrue(actual.contains(new Move(3, 1, 2, 1)));
        assertTrue(actual.contains(new Move(3, 1, 2, 2)));
        assertTrue(actual.contains(new Move(3, 1, 3, 0)));
        assertTrue(actual.contains(new Move(3, 1, 3, 2)));
        assertTrue(actual.contains(new Move(3, 1, 4, 0)));
        assertTrue(actual.contains(new Move(3, 1, 4, 1)));
        assertTrue(actual.contains(new Move(3, 1, 4, 2)));
        assertEquals(8, actual.size());
    }

}

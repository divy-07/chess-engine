package chess.moves;

import chess.board.DebugBoard;
import chess.board.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopPossibleMoveTest {

    @Test
    public void test01NoMoves() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", "p", " ", " ", " ", " ", " ", " "},
                {" ", "P", " ", " ", " ", " ", " ", " "},
                {"B", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        // sets up the static fields in PossibleMoves
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = PossibleMoves.possibleB(boardArray[2]);
        List<Move> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

    @Test
    public void test02topLeftToBottomRightDiagonal() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", "p", " ", " ", " "},
                {" ", " ", " ", "b", "P", " ", " ", " "},
                {" ", " ", "p", " ", " ", " ", " ", " "},
                {" ", " ", "P", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleB(boardArray[8]);

        assertTrue(actual.contains(new Move(3, 3, 0, 0)));
        assertTrue(actual.contains(new Move(3, 3, 1, 1)));
        assertTrue(actual.contains(new Move(3, 3, 2, 2)));
        assertTrue(actual.contains(new Move(3, 3, 4, 4)));
        assertTrue(actual.contains(new Move(3, 3, 5, 5)));
        assertTrue(actual.contains(new Move(3, 3, 6, 6)));
        assertTrue(actual.contains(new Move(3, 3, 7, 7)));
        assertEquals(7, actual.size());
    }

    @Test
    public void test03topRightToBottomLeftDiagonal() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", "p", " ", " ", " ", " ", " "},
                {" ", " ", "P", "b", " ", " ", " ", " "},
                {" ", " ", " ", " ", "p", " ", " ", " "},
                {" ", " ", " ", " ", "P", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleB(boardArray[8]);

        assertTrue(actual.contains(new Move(3, 3, 0, 6)));
        assertTrue(actual.contains(new Move(3, 3, 1, 5)));
        assertTrue(actual.contains(new Move(3, 3, 2, 4)));
        assertTrue(actual.contains(new Move(3, 3, 4, 2)));
        assertTrue(actual.contains(new Move(3, 3, 5, 1)));
        assertTrue(actual.contains(new Move(3, 3, 6, 0)));
        assertEquals(6, actual.size());
    }

    @Test
    public void test03diagonalBlocked() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", "p", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", "B", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = PossibleMoves.possibleB(boardArray[2]);

        assertTrue(actual.contains(new Move(6, 4, 4, 2)));
        assertTrue(actual.contains(new Move(6, 4, 5, 3)));
        assertTrue(actual.contains(new Move(6, 4, 7, 3)));
        assertTrue(actual.contains(new Move(6, 4, 7, 5)));
        assertTrue(actual.contains(new Move(6, 4, 5, 5)));
        assertTrue(actual.contains(new Move(6, 4, 4, 6)));
        assertTrue(actual.contains(new Move(6, 4, 3, 7)));
        assertEquals(7, actual.size());
    }
}

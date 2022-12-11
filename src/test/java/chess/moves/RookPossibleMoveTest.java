package chess.moves;

import chess.board.DebugBoard;
import chess.board.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookPossibleMoveTest {

    @Test
    public void test01NoMoves() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {"b", "b", "b", "b", "b", "b", "b", "b"},
                {"P", "P", "P", "P", "P", "P", "P", "P"},
                {"R", "R", "R", "R", "R", "R", "R", "R"}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        // sets up the static fields in position.possibleMoves
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleR(boardArray[3]);
        List<Move> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

    @Test
    public void test02HorizontalOnly() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", "n", " ", " ", " "},
                {" ", " ", " ", " ", "P", " ", " ", " "},
                {" ", " ", " ", " ", "R", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleR(boardArray[3]);

        assertTrue(actual.contains(new Move(7, 4, 7, 0)));
        assertTrue(actual.contains(new Move(7, 4, 7, 1)));
        assertTrue(actual.contains(new Move(7, 4, 7, 2)));
        assertTrue(actual.contains(new Move(7, 4, 7, 3)));
        assertTrue(actual.contains(new Move(7, 4, 7, 5)));
        assertTrue(actual.contains(new Move(7, 4, 7, 6)));
        assertTrue(actual.contains(new Move(7, 4, 7, 7)));
        assertEquals(7, actual.size());
    }

    @Test
    public void test03VerticalOnly() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", "q", " ", "k", " ", " ", " ", " "},
                {" ", "P", "R", "P", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleR(boardArray[3]);

        assertTrue(actual.contains(new Move(4, 2, 0, 2)));
        assertTrue(actual.contains(new Move(4, 2, 1, 2)));
        assertTrue(actual.contains(new Move(4, 2, 2, 2)));
        assertTrue(actual.contains(new Move(4, 2, 3, 2)));
        assertTrue(actual.contains(new Move(4, 2, 5, 2)));
        assertTrue(actual.contains(new Move(4, 2, 6, 2)));
        assertTrue(actual.contains(new Move(4, 2, 7, 2)));
        assertEquals(7, actual.size());
    }

    @Test
    public void test04VerticalAndHorizontal() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "P", " ", "r", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = position.possibleMoves.possibleR(boardArray[9]);

        assertTrue(actual.contains(new Move(1, 5, 1, 3)));
        assertTrue(actual.contains(new Move(1, 5, 1, 4)));
        assertTrue(actual.contains(new Move(1, 5, 1, 6)));
        assertTrue(actual.contains(new Move(1, 5, 1, 7)));
        assertTrue(actual.contains(new Move(1, 5, 0, 5)));
        assertTrue(actual.contains(new Move(1, 5, 2, 5)));
        assertTrue(actual.contains(new Move(1, 5, 3, 5)));
        assertTrue(actual.contains(new Move(1, 5, 4, 5)));
        assertTrue(actual.contains(new Move(1, 5, 5, 5)));
        assertTrue(actual.contains(new Move(1, 5, 6, 5)));
        assertTrue(actual.contains(new Move(1, 5, 7, 5)));
        assertEquals(11, actual.size());
    }
}

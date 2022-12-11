package chess.moves;

import chess.board.DebugBoard;
import chess.board.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenPossibleMoveTest {

    @Test
    public void test01NoQueenMoves() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", "P", "P", "R"},
                {" ", " ", " ", " ", " ", "P", "Q", "P"},
                {" ", " ", " ", " ", " ", "B", "B", "K"},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = PossibleMoves.possibleQ(boardArray[4]);
        List<Move> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

    @Test
    public void test02HorizontalOnly() {
        String[][] stringBoard = new String[][]{
                {" ", "r", "k", "r", " ", " ", " ", " "},
                {" ", " ", "q", " ", " ", " ", " ", " "},
                {" ", "p", "b", "b", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleQ(boardArray[10]);

        assertTrue(actual.contains("new Move(1, 2, 1, 0)"));
        assertTrue(actual.contains(new Move(1, 2, 1, 1)));
        assertTrue(actual.contains(new Move(1, 2, 1, 3)));
        assertTrue(actual.contains(new Move(1, 2, 1, 4)));
        assertTrue(actual.contains(new Move(1, 2, 1, 5)));
        assertTrue(actual.contains(new Move(1, 2, 1, 6)));
        assertTrue(actual.contains(new Move(1, 2, 1, 7)));
        assertEquals(28, actual.size());
    }

    @Test
    public void test03VerticalOnly() {
        String[][] stringBoard = new String[][]{
                {" ", "r", " ", "r", " ", " ", " ", " "},
                {" ", "p", "q", "p", " ", " ", " ", " "},
                {" ", "p", " ", "b", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleQ(boardArray[10]);

        assertTrue(actual.contains(new Move(1, 2, 0, 2)));
        assertTrue(actual.contains(new Move(1, 2, 2, 2)));
        assertTrue(actual.contains(new Move(1, 2, 3, 2)));
        assertTrue(actual.contains(new Move(1, 2, 4, 2)));
        assertTrue(actual.contains(new Move(1, 2, 5, 2)));
        assertTrue(actual.contains(new Move(1, 2, 6, 2)));
        assertTrue(actual.contains(new Move(1, 2, 7, 2)));
        assertEquals(28, actual.size());
    }

    @Test
    public void test04topLeftToBottomRightDiagonal() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "p", "p", " ", " ", " "},
                {" ", " ", "p", "q", "p", " ", " ", " "},
                {" ", " ", "p", "p", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleQ(boardArray[10]);

        assertTrue(actual.contains(new Move(3, 3, 0, 0)));
        assertTrue(actual.contains(new Move(3, 3, 1, 1)));
        assertTrue(actual.contains(new Move(3, 3, 2, 2)));
        assertTrue(actual.contains(new Move(3, 3, 4, 4)));
        assertTrue(actual.contains(new Move(3, 3, 5, 5)));
        assertTrue(actual.contains(new Move(3, 3, 6, 6)));
        assertTrue(actual.contains(new Move(3, 3, 7, 7)));
        assertEquals(28, actual.size());
    }

    @Test
    public void test05topRightToBottomLeftDiagonal() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", "p", "p", " ", " ", " ", " "},
                {" ", " ", "p", "q", "p", " ", " ", " "},
                {" ", " ", " ", "p", "p", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleQ(boardArray[10]);

        assertTrue(actual.contains(new Move(3, 3, 0, 6)));
        assertTrue(actual.contains(new Move(3, 3, 1, 5)));
        assertTrue(actual.contains(new Move(3, 3, 2, 4)));
        assertTrue(actual.contains(new Move(3, 3, 4, 2)));
        assertTrue(actual.contains(new Move(3, 3, 5, 1)));
        assertTrue(actual.contains(new Move(3, 3, 6, 0)));
        assertEquals(24, actual.size());
    }

    @Test
    public void test06AllDirections() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", "P", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", "Q", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = PossibleMoves.possibleQ(boardArray[4]);

        assertTrue(actual.contains(new Move(5, 2, 5, 0)));
        assertTrue(actual.contains(new Move(5, 2, 5, 1)));
        assertTrue(actual.contains(new Move(5, 2, 5, 3)));
        assertTrue(actual.contains(new Move(5, 2, 5, 4)));
        assertTrue(actual.contains(new Move(5, 2, 5, 5)));
        assertTrue(actual.contains(new Move(5, 2, 5, 6)));
        assertTrue(actual.contains(new Move(5, 2, 5, 7)));
        assertTrue(actual.contains(new Move(5, 2, 0, 2)));
        assertTrue(actual.contains(new Move(5, 2, 1, 2)));
        assertTrue(actual.contains(new Move(5, 2, 2, 2)));
        assertTrue(actual.contains(new Move(5, 2, 3, 2)));
        assertTrue(actual.contains(new Move(5, 2, 4, 2)));
        assertTrue(actual.contains(new Move(5, 2, 6, 2)));
        assertTrue(actual.contains(new Move(5, 2, 7, 2)));
        assertTrue(actual.contains(new Move(5, 2, 3, 0)));
        assertTrue(actual.contains(new Move(5, 2, 4, 1)));
        assertTrue(actual.contains(new Move(5, 2, 7, 0)));
        assertTrue(actual.contains(new Move(5, 2, 6, 1)));
        assertTrue(actual.contains(new Move(5, 2, 6, 3)));
        assertTrue(actual.contains(new Move(5, 2, 7, 4)));
        assertTrue(actual.contains(new Move(5, 2, 4, 3)));
        assertTrue(actual.contains(new Move(5, 2, 3, 4)));
        assertEquals(88, actual.size());
    }

    @Test
    public void test07captureAllDirections() {
        String[][] stringBoard = new String[][]{
                {"n", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", "p", " ", " "},
                {" ", " ", "n", "r", " ", " ", " ", " "},
                {" ", "p", " ", "Q", " ", " ", "r", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", "b", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "b", " ", " ", "p", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = PossibleMoves.possibleQ(boardArray[4]);

        assertTrue(actual.contains(new Move(3, 3, 2, 2)));
        assertTrue(actual.contains(new Move(3, 3, 2, 3)));
        assertTrue(actual.contains(new Move(3, 3, 2, 4)));
        assertTrue(actual.contains(new Move(3, 3, 1, 5)));
        assertTrue(actual.contains(new Move(3, 3, 3, 1)));
        assertTrue(actual.contains(new Move(3, 3, 3, 2)));
        assertTrue(actual.contains(new Move(3, 3, 3, 4)));
        assertTrue(actual.contains(new Move(3, 3, 3, 5)));
        assertTrue(actual.contains(new Move(3, 3, 3, 6)));
        assertTrue(actual.contains(new Move(3, 3, 4, 2)));
        assertTrue(actual.contains(new Move(3, 3, 4, 3)));
        assertTrue(actual.contains(new Move(3, 3, 4, 4)));
        assertTrue(actual.contains(new Move(3, 3, 5, 1)));
        assertTrue(actual.contains(new Move(3, 3, 5, 3)));
        assertTrue(actual.contains(new Move(3, 3, 5, 5)));
        assertTrue(actual.contains(new Move(3, 3, 6, 3)));
        assertTrue(actual.contains(new Move(3, 3, 6, 6)));
        assertEquals(68, actual.size());
    }

}

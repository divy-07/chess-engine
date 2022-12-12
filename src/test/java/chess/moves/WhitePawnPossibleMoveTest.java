package chess.moves;

import chess.board.DebugBoard;
import chess.board.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WhitePawnPossibleMoveTest {

    @Test
    public void test01CaptureRight() {
        String[][] stringBoard = new String[][]{
                {"r", "n", "b", "q", "k", "b", "n", "r"},
                {"p", "p", "p", "p", "p", "p", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "p", "p"},
                {" ", " ", " ", " ", " ", " ", "P", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);
        List<Move> expected = new ArrayList<>();
        expected.add(new Move(6, 6, 5, 7));

        assertEquals(expected, actual);
    }

    @Test
    public void test02CaptureLeft() {
        String[][] stringBoard = new String[][]{
                {"r", "n", "b", "q", "k", "b", "n", "r"},
                {"p", "p", "p", "p", "p", " ", " ", "p"},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", "p", "p", " "},
                {" ", " ", " ", " ", " ", " ", "P", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);
        List<Move> expected = new ArrayList<>();
        expected.add(new Move(6, 6, 5, 5));

        assertEquals(expected, actual);
    }

    @Test
    public void test03MoveOneForward() {
        String[][] stringBoard = new String[][]{
                {"r", "n", "b", "q", "k", "b", "n", "r"},
                {"p", "p", "p", "p", "p", "p", "p", "p"},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "P", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);
        List<Move> expected = new ArrayList<>();
        expected.add(new Move(5, 6, 4, 6));

        assertEquals(expected, actual);
    }

    @Test
    public void test04MoveTwoForward() {
        String[][] stringBoard = new String[][]{
                {"r", "n", "b", "q", "k", "b", "n", "r"},
                {"p", "p", "p", "p", "p", "p", "p", "p"},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "P", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);

        assertTrue(actual.contains(new Move(6, 3, 5, 3)));
        assertTrue(actual.contains(new Move(6, 3, 4, 3)));
        assertEquals(2, actual.size());
    }

    @Test
    public void test05NoMoves() {
        String[][] stringBoard = new String[][]{
                {"r", "n", "b", "q", "k", "b", " ", "r"},
                {"p", "p", "p", "p", "p", "p", " ", "p"},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "n", " ", " ", "p", " "},
                {" ", " ", " ", "P", " ", " ", "P", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);

        assertEquals(0, actual.size());
    }

    @Test
    public void test06EnPassantRight() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", "r", " ", " ", " ", " ", " "},
                {" ", " ", "P", "p", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        // make EP = BP since only one pawn on board
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], boardArray[6], false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleWP(boardArray[0], boardArray[6], boardArray[6]);
        List<Move> expected = new ArrayList<>();
        expected.add(new Move(3, 2, 2, 3, true));

        assertEquals(expected, actual);
    }

    @Test
    public void test07EnPassantLeft() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", "r", " ", " ", " "},
                {" ", " ", " ", "p", "P", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        // make EP = BP since only one pawn on board
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], boardArray[6], false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleWP(boardArray[0], boardArray[6], boardArray[6]);
        List<Move> expected = new ArrayList<>();
        expected.add(new Move(3, 4, 2, 3, true));

        assertEquals(expected, actual);
    }

    @Test
    public void test08PromoteStraight() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", "P", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);

        assertTrue(actual.contains(new Move(1, 2, 0, 2, 'Q')));
        assertTrue(actual.contains(new Move(1, 2, 0, 2, 'R')));
        assertTrue(actual.contains(new Move(1, 2, 0, 2, 'B')));
        assertTrue(actual.contains(new Move(1, 2, 0, 2, 'N')));
        assertEquals(4, actual.size());
    }

    @Test
    public void test09PromoteRight() {
        String[][] stringBoard = new String[][]{
                {" ", " ", "r", "b", " ", " ", " ", " "},
                {" ", " ", "P", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);

        assertTrue(actual.contains(new Move(1, 2, 0, 3, 'Q')));
        assertTrue(actual.contains(new Move(1, 2, 0, 3, 'R')));
        assertTrue(actual.contains(new Move(1, 2, 0, 3, 'B')));
        assertTrue(actual.contains(new Move(1, 2, 0, 3, 'N')));
        assertEquals(4, actual.size());
    }

    @Test
    public void test10PromoteLeft() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", "n", "k"},
                {" ", " ", " ", " ", " ", " ", " ", "P"},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        Position position = new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, true);
        List<Move> actual = position.possibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);

        assertTrue(actual.contains(new Move(1, 7, 0, 6, 'Q')));
        assertTrue(actual.contains(new Move(1, 7, 0, 6, 'R')));
        assertTrue(actual.contains(new Move(1, 7, 0, 6, 'B')));
        assertTrue(actual.contains(new Move(1, 7, 0, 6, 'N')));
        assertEquals(4, actual.size());
    }
}

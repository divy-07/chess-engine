package chess.moves;

import chess.board.DebugBoard;
import chess.board.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlackPawnPossibleMoveTest {

    @Test
    public void test01CaptureRight() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", "p", " ", " ", " "},
                {" ", " ", " ", " ", "B", "R", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleBP(boardArray[6], boardArray[0], 0L);
        List<Move> expected = new ArrayList<>(List.of(new Move(2, 4, 3, 5)));

        assertEquals(expected, actual);
    }

    @Test
    public void test02CaptureLeft() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", "p", " ", " ", " ", " ", " ", " "},
                {"Q", "P", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "B", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleBP(boardArray[6], boardArray[0], 0L);
        List<Move> expected = new ArrayList<>(List.of(new Move(1, 1, 2, 0)));

        assertEquals(expected, actual);
    }

    @Test
    public void test03MoveOneForward() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "p", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleBP(boardArray[6], boardArray[0], 0L);
        List<Move> expected = new ArrayList<>(List.of(new Move(3, 3, 4, 3)));

        assertEquals(expected, actual);
    }

    @Test
    public void test04MoveTwoForward() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", "p"},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleBP(boardArray[6], boardArray[0], 0L);

        assertTrue(actual.contains(new Move(1, 7, 2, 7)));
        assertTrue(actual.contains(new Move(1, 7, 3, 7)));
        assertEquals(2, actual.size());
    }

    @Test
    public void test05NoMoves() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", "p", " ", " ", " "},
                {" ", " ", " ", " ", "B", " ", " ", " "},
                {" ", " ", "p", " ", " ", " ", " ", " "},
                {" ", "p", "P", "p", " ", " ", " ", " "},
                {" ", "P", " ", "N", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleBP(boardArray[6], boardArray[0], 0L);
        List<Move> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

    @Test
    public void test06EnPassantRight() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {"p", "P", " ", " ", " ", " ", " ", " "},
                {"R", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        // make EP = WP since only one pawn on board
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], boardArray[0], false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleBP(boardArray[6], boardArray[0], boardArray[0]);
        List<Move> expected = new ArrayList<>(List.of(new Move(4, 0, 5, 1, true)));

        assertEquals(expected, actual);
    }

    @Test
    public void test07EnPassantLeft() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", "P", "p", "Q", " "},
                {" ", " ", " ", " ", " ", "B", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        // make EP = WP since only one pawn on board
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], boardArray[0], false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleBP(boardArray[6], boardArray[0], boardArray[0]);
        List<Move> expected = new ArrayList<>(List.of(new Move(4, 5, 5, 4, true)));

        assertEquals(expected, actual);
    }

    @Test
    public void test08PromoteStraight() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", "p"},
                {" ", " ", " ", " ", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleBP(boardArray[6], boardArray[0], 0L);

        assertTrue(actual.contains("77qP"));
        assertTrue(actual.contains("77rP"));
        assertTrue(actual.contains("77bP"));
        assertTrue(actual.contains("77nP"));
        assertEquals(4, actual.size());
    }

    @Test
    public void test09PromoteRight() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", "p", " ", " "},
                {" ", " ", " ", " ", " ", "R", "B", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleBP(boardArray[6], boardArray[0], 0L);

        assertTrue(actual.contains("56qP"));
        assertTrue(actual.contains("56rP"));
        assertTrue(actual.contains("56bP"));
        assertTrue(actual.contains("56nP"));
        assertEquals(4, actual.size());
    }

    @Test
    public void test10PromoteLeft() {
        String[][] stringBoard = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "p", " ", " ", " ", " "},
                {" ", " ", "Q", "K", " ", " ", " ", " "}};
        long[] boardArray = DebugBoard.arrayToBitboards(stringBoard);
        new Position(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false, false);
        List<Move> actual = PossibleMoves.possibleBP(boardArray[6], boardArray[0], 0L);

        assertTrue(actual.contains("32qP"));
        assertTrue(actual.contains("32rP"));
        assertTrue(actual.contains("32bP"));
        assertTrue(actual.contains("32nP"));
        assertEquals(4, actual.size());
    }
}

package chess.moves;

import chess.board.DebugBoard;
import org.junit.jupiter.api.Test;

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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);
        String expected = "6657";

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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);
        String expected = "6655";

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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);
        String expected = "5646";

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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);

        assertTrue(actual.contains("6353"));
        assertTrue(actual.contains("6343"));
        assertEquals(8, actual.length());
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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);
        String expected = "";

        assertEquals(expected, actual);
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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], boardArray[6], false, false, false, false);
        String actual = PossibleMoves.possibleWP(boardArray[0], boardArray[6], boardArray[6]);
        String expected = "23WE";

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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], boardArray[6], false, false, false, false);
        String actual = PossibleMoves.possibleWP(boardArray[0], boardArray[6], boardArray[6]);
        String expected = "43WE";

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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);

        assertTrue(actual.contains("22QP"));
        assertTrue(actual.contains("22RP"));
        assertTrue(actual.contains("22BP"));
        assertTrue(actual.contains("22NP"));
        assertEquals(16, actual.length());
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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);

        assertTrue(actual.contains("23QP"));
        assertTrue(actual.contains("23RP"));
        assertTrue(actual.contains("23BP"));
        assertTrue(actual.contains("23NP"));
        assertEquals(16, actual.length());
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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleWP(boardArray[0], boardArray[6], 0L);

        assertTrue(actual.contains("76QP"));
        assertTrue(actual.contains("76RP"));
        assertTrue(actual.contains("76BP"));
        assertTrue(actual.contains("76NP"));
        assertEquals(16, actual.length());
    }
}

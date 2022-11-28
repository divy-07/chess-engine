package chess.moves;

import chess.board.DebugBoard;
import org.junit.jupiter.api.Test;

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
        // sets up the static fields in PossibleMoves
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleR(boardArray[3]);
        String expected = "";

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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleR(boardArray[3]);

        assertTrue(actual.contains("7470"));
        assertTrue(actual.contains("7471"));
        assertTrue(actual.contains("7472"));
        assertTrue(actual.contains("7473"));
        assertTrue(actual.contains("7475"));
        assertTrue(actual.contains("7476"));
        assertTrue(actual.contains("7477"));
        assertEquals(28, actual.length());
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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleR(boardArray[3]);

        assertTrue(actual.contains("4202"));
        assertTrue(actual.contains("4212"));
        assertTrue(actual.contains("4222"));
        assertTrue(actual.contains("4232"));
        assertTrue(actual.contains("4252"));
        assertTrue(actual.contains("4262"));
        assertTrue(actual.contains("4272"));
        assertEquals(28, actual.length());
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
        PossibleMoves.possibleMovesB(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleR(boardArray[9]);

        assertTrue(actual.contains("1513"));
        assertTrue(actual.contains("1514"));
        assertTrue(actual.contains("1516"));
        assertTrue(actual.contains("1517"));
        assertTrue(actual.contains("1505"));
        assertTrue(actual.contains("1525"));
        assertTrue(actual.contains("1535"));
        assertTrue(actual.contains("1545"));
        assertTrue(actual.contains("1555"));
        assertTrue(actual.contains("1565"));
        assertTrue(actual.contains("1575"));
        assertEquals(44, actual.length());
    }
}

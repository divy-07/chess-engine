package chess.moves;

import chess.board.DebugBoard;
import org.junit.jupiter.api.Test;

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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleK(boardArray[5]);
        String expected = "";

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
        PossibleMoves.possibleMovesB(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleK(boardArray[11]);

        assertTrue(actual.contains("3120"));
        assertTrue(actual.contains("3121"));
        assertTrue(actual.contains("3122"));
        assertTrue(actual.contains("3130"));
        assertTrue(actual.contains("3132"));
        assertTrue(actual.contains("3140"));
        assertTrue(actual.contains("3141"));
        assertTrue(actual.contains("3142"));
        assertEquals(32, actual.length());
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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleK(boardArray[5]);

        assertTrue(actual.contains("3120"));
        assertTrue(actual.contains("3121"));
        assertTrue(actual.contains("3122"));
        assertTrue(actual.contains("3130"));
        assertTrue(actual.contains("3132"));
        assertTrue(actual.contains("3140"));
        assertTrue(actual.contains("3141"));
        assertTrue(actual.contains("3142"));
        assertEquals(32, actual.length());
    }

}

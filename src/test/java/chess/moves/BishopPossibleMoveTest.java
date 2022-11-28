package chess.moves;

import chess.board.DebugBoard;
import org.junit.jupiter.api.Test;

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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleB(boardArray[2]);
        String expected = "";

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
        PossibleMoves.possibleMovesB(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleB(boardArray[8]);

        assertTrue(actual.contains("3300"));
        assertTrue(actual.contains("3311"));
        assertTrue(actual.contains("3322"));
        assertTrue(actual.contains("3344"));
        assertTrue(actual.contains("3355"));
        assertTrue(actual.contains("3366"));
        assertTrue(actual.contains("3377"));
        assertEquals(28, actual.length());
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
        PossibleMoves.possibleMovesB(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleB(boardArray[8]);

        assertTrue(actual.contains("3306"));
        assertTrue(actual.contains("3315"));
        assertTrue(actual.contains("3324"));
        assertTrue(actual.contains("3342"));
        assertTrue(actual.contains("3351"));
        assertTrue(actual.contains("3360"));
        assertEquals(24, actual.length());
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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleB(boardArray[2]);

        assertTrue(actual.contains("6442"));
        assertTrue(actual.contains("6453"));
        assertTrue(actual.contains("6473"));
        assertTrue(actual.contains("6475"));
        assertTrue(actual.contains("6455"));
        assertTrue(actual.contains("6446"));
        assertTrue(actual.contains("6437"));
        assertEquals(28, actual.length());
    }
}

package chess.moves;

import chess.board.DebugBoard;
import org.junit.jupiter.api.Test;

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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleQ(boardArray[4]);
        String expected = "";

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
        PossibleMoves.possibleMovesB(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleQ(boardArray[10]);

        assertTrue(actual.contains("1210"));
        assertTrue(actual.contains("1211"));
        assertTrue(actual.contains("1213"));
        assertTrue(actual.contains("1214"));
        assertTrue(actual.contains("1215"));
        assertTrue(actual.contains("1216"));
        assertTrue(actual.contains("1217"));
        assertEquals(28, actual.length());
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
        PossibleMoves.possibleMovesB(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleQ(boardArray[10]);

        assertTrue(actual.contains("1202"));
        assertTrue(actual.contains("1222"));
        assertTrue(actual.contains("1232"));
        assertTrue(actual.contains("1242"));
        assertTrue(actual.contains("1252"));
        assertTrue(actual.contains("1262"));
        assertTrue(actual.contains("1272"));
        assertEquals(28, actual.length());
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
        PossibleMoves.possibleMovesB(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleQ(boardArray[10]);

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
        PossibleMoves.possibleMovesB(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleQ(boardArray[10]);

        assertTrue(actual.contains("3306"));
        assertTrue(actual.contains("3315"));
        assertTrue(actual.contains("3324"));
        assertTrue(actual.contains("3342"));
        assertTrue(actual.contains("3351"));
        assertTrue(actual.contains("3360"));
        assertEquals(24, actual.length());
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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleQ(boardArray[4]);

        assertTrue(actual.contains("5250"));
        assertTrue(actual.contains("5251"));
        assertTrue(actual.contains("5253"));
        assertTrue(actual.contains("5254"));
        assertTrue(actual.contains("5255"));
        assertTrue(actual.contains("5256"));
        assertTrue(actual.contains("5257"));
        assertTrue(actual.contains("5202"));
        assertTrue(actual.contains("5212"));
        assertTrue(actual.contains("5222"));
        assertTrue(actual.contains("5232"));
        assertTrue(actual.contains("5242"));
        assertTrue(actual.contains("5262"));
        assertTrue(actual.contains("5272"));
        assertTrue(actual.contains("5230"));
        assertTrue(actual.contains("5241"));
        assertTrue(actual.contains("5270"));
        assertTrue(actual.contains("5261"));
        assertTrue(actual.contains("5263"));
        assertTrue(actual.contains("5274"));
        assertTrue(actual.contains("5243"));
        assertTrue(actual.contains("5234"));
        assertEquals(88, actual.length());
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
        PossibleMoves.possibleMovesW(boardArray[0], boardArray[1], boardArray[2], boardArray[3],
                boardArray[4], boardArray[5], boardArray[6], boardArray[7], boardArray[8], boardArray[9],
                boardArray[10], boardArray[11], 0L, false, false, false, false);
        String actual = PossibleMoves.possibleQ(boardArray[4]);

        assertTrue(actual.contains("3322"));
        assertTrue(actual.contains("3323"));
        assertTrue(actual.contains("3324"));
        assertTrue(actual.contains("3315"));
        assertTrue(actual.contains("3331"));
        assertTrue(actual.contains("3332"));
        assertTrue(actual.contains("3334"));
        assertTrue(actual.contains("3335"));
        assertTrue(actual.contains("3336"));
        assertTrue(actual.contains("3342"));
        assertTrue(actual.contains("3343"));
        assertTrue(actual.contains("3344"));
        assertTrue(actual.contains("3351"));
        assertTrue(actual.contains("3353"));
        assertTrue(actual.contains("3355"));
        assertTrue(actual.contains("3363"));
        assertTrue(actual.contains("3366"));
        assertEquals(68, actual.length());
    }

}

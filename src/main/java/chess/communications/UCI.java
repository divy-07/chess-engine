package chess.communications;

import chess.board.Board;
import chess.engine.Hari;
import chess.moves.MoveGeneration;
import chess.moves.PossibleMoves;
import chess.moves.MoveConversion;

import java.util.Scanner;

import static chess.Constants.*;

public class UCI {

    /**
     * This method is the primary method for communicating with the GUI.
     * Follows the UCI protocol from <a href="http://wbec-ridderkerk.nl/html/UCIProtocol.html">here</a>.
     */
    public static void uciCommunication() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String thisLine = input.nextLine();
            if ("uci".equals(thisLine)) {
                inputUCI();
            } else if (thisLine.startsWith("setoption")) {
                inputSetOption(thisLine);
            } else if ("isready".equals(thisLine)) {
                inputIsReady();
            } else if ("ucinewgame".equals(thisLine)) {
                inputUCINewGame();
            } else if (thisLine.startsWith("position")) {
                inputPosition(thisLine);
            } else if (thisLine.startsWith("go")) {
                inputGo();
            } else if (thisLine.equals("quit")) {
                inputQuit();
            }
        }
    }

    /**
     * Registers the engine with the GUI.
     * Options are not currently supported.
     */
    private static void inputUCI() {
        System.out.println("id name " + ENGINE_NAME);
        System.out.println("id author Divy");
        // TODO (#5): Implement options
        System.out.println("uciok");
    }

    private static void inputSetOption(String inputString) {
        // TODO (#5): Implement options
    }

    private static void inputIsReady() {
        System.out.println("readyok");
    }

    private static void inputUCINewGame() {
        // expect a "position" command next
        // TODO: decide what to do here
        //  currently, it works fine without this,
        //  but it might be useful in future
    }

    /**
     * Sets the position of the board to the specified position.
     *
     * @param input the input string from the GUI
     */
    private static void inputPosition(String input) {
        input = input.substring(9).concat(" ");
        if (input.contains("startpos ")) {
            input = input.substring(9);
            Board.importFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        } else if (input.contains("fen")) {
            input = input.substring(4);
            Board.importFEN(input);
        }

        // apply moves
        if (input.contains("moves")) {
            input = input.substring(input.indexOf("moves") + 6);
            while (input.length() > 0) {
                // TODO: make move
                String moves;
                if (Hari.whiteToMove) {
                    moves = PossibleMoves.possibleMovesW(Hari.WP, Hari.WN, Hari.WB, Hari.WR, Hari.WQ, Hari.WK, Hari.BP, Hari.BN, Hari.BB, Hari.BR, Hari.BQ, Hari.BK, Hari.EP, Hari.CWK, Hari.CWQ, Hari.CBK, Hari.CBQ);
                } else {
                    moves = PossibleMoves.possibleMovesB(Hari.WP, Hari.WN, Hari.WB, Hari.WR, Hari.WQ, Hari.WK, Hari.BP, Hari.BN, Hari.BB, Hari.BR, Hari.BQ, Hari.BK, Hari.EP, Hari.CWK, Hari.CWQ, Hari.CBK, Hari.CBQ);
                }
                MoveConversion.algebraToMove(input, moves);
                input = input.substring(input.indexOf(' ') + 1);
            }
        }
    }

    /**
     * Starts the search for the best move.
     */
    private static void inputGo() {
        String move = MoveGeneration.getBestMove(Hari.WP, Hari.WN, Hari.WB, Hari.WR, Hari.WQ, Hari.WK,
                Hari.BP, Hari.BN, Hari.BB, Hari.BR, Hari.BQ, Hari.BK, Hari.EP, Hari.CWK, Hari.CWQ, Hari.CBK, Hari.CBQ,
                Hari.whiteToMove);
        System.out.println("bestmove " + MoveConversion.moveToAlgebra(move));
    }

    /**
     * Quits the engine.
     */
    private static void inputQuit() {
        System.exit(0);
    }

}

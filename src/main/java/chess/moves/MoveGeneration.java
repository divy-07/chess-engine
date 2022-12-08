package chess.moves;

import chess.board.Evaluation;

import chess.engine.MakeMove;

public class MoveGeneration {

    /**
     * Calculates the best move for the current position.
     * Currently using a basic <a href="https://www.chessprogramming.org/Minimax">minimax algorithm</a>
     *
     * @return the best move found with basic mini-max search;
     * the string is in the format "e2e4" or "e7e8q" for promotion
     * @author Divy Pratel
     */
    public static String basicPlySearch(long wp, long wn, long wb, long wr, long wq, long wk,
                                        long bp, long bn, long bb, long br, long bq, long bk,
                                        long ep, boolean cwk, boolean cwq, boolean cbk, boolean cbq,
                                        boolean whiteToMove, int depth) {
        int highestVal = Integer.MIN_VALUE;
        int lowestVal = Integer.MAX_VALUE;
        int currentVal;

        String bestMove = "";

        // get all possible next moves
        String possible;
        if (whiteToMove) {
            possible = PossibleMoves.possibleMovesW(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
        } else {
            possible = PossibleMoves.possibleMovesB(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
        }

        // go through all possible moves
        for (int i = 0; i < possible.length(); i += 4) {
            String move = possible.substring(i, i + 4);
            // update position
            long wp_temp = MakeMove.makeMove(wp, move, 'P');
            long wn_temp = MakeMove.makeMove(wn, move, 'N');
            long wb_temp = MakeMove.makeMove(wb, move, 'B');
            long wr_temp = MakeMove.makeMove(wr, move, 'R');
            long wq_temp = MakeMove.makeMove(wq, move, 'Q');
            long wk_temp = MakeMove.makeMove(wk, move, 'K');
            long bp_temp = MakeMove.makeMove(bp, move, 'p');
            long bn_temp = MakeMove.makeMove(bn, move, 'n');
            long bb_temp = MakeMove.makeMove(bb, move, 'b');
            long br_temp = MakeMove.makeMove(br, move, 'r');
            long bq_temp = MakeMove.makeMove(bq, move, 'q');
            long bk_temp = MakeMove.makeMove(bk, move, 'k');

            // min for next if white
            if (whiteToMove) {
                currentVal = min(wp_temp, wn_temp, wb_temp, wr_temp, wq_temp, wk_temp,
                        bp_temp, bn_temp, bb_temp, br_temp, bq_temp, bk_temp,
                        ep, cwk, cwq, cbk, cbq, false, depth - 1);
            } else {
                currentVal = max(wp_temp, wn_temp, wb_temp, wr_temp, wq_temp, wk_temp,
                        bp_temp, bn_temp, bb_temp, br_temp, bq_temp, bk_temp,
                        ep, cwk, cwq, cbk, cbq, true, depth - 1);
            }

            // update highest/lowest value
            if (whiteToMove) {
                if (currentVal > highestVal) {
                    highestVal = currentVal;
                    bestMove = move;
                }
            } else {
                if (currentVal < lowestVal) {
                    lowestVal = currentVal;
                    bestMove = move;
                }
            }
        }

        return bestMove;
    }

    /**
     * Minimizer for the mini-max search algorithm.
     *
     * @param whiteToMove whether it is white's turn
     * @param depth the depth remaining to search
     * @return the minimized value of the position
     */
    private static int min(long wp, long wn, long wb, long wr, long wq, long wk,
                           long bp, long bn, long bb, long br, long bq, long bk,
                           long ep, boolean cwk, boolean cwq, boolean cbk, boolean cbq,
                           boolean whiteToMove, int depth) {
        if (depth == 0) {
            return Evaluation.evaluate(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
        }

        int lowestScore = Integer.MAX_VALUE;
        // generate all possible moves
        String possible = PossibleMoves.possibleMovesB(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
        for (int i = 0; i < possible.length(); i += 4) {
            String move = possible.substring(i, i + 4);
            // update position
            long wp_temp = MakeMove.makeMove(wp, move, 'P');
            long wn_temp = MakeMove.makeMove(wn, move, 'N');
            long wb_temp = MakeMove.makeMove(wb, move, 'B');
            long wr_temp = MakeMove.makeMove(wr, move, 'R');
            long wq_temp = MakeMove.makeMove(wq, move, 'Q');
            long wk_temp = MakeMove.makeMove(wk, move, 'K');
            long bp_temp = MakeMove.makeMove(bp, move, 'p');
            long bn_temp = MakeMove.makeMove(bn, move, 'n');
            long bb_temp = MakeMove.makeMove(bb, move, 'b');
            long br_temp = MakeMove.makeMove(br, move, 'r');
            long bq_temp = MakeMove.makeMove(bq, move, 'q');
            long bk_temp = MakeMove.makeMove(bk, move, 'k');
            // score the new position
            int score = max(wp_temp, wn_temp, wb_temp, wr_temp, wq_temp, wk_temp,
                    bp_temp, bn_temp, bb_temp, br_temp, bq_temp, bk_temp,
                    ep, cwk, cwq, cbk, cbq, true, depth - 1);
            if (score < lowestScore) {
                lowestScore = score;
            }
        }
        return lowestScore;
    }

    /**
     * Maximizer for the mini-max search algorithm.
     *
     * @param whiteToMove whether it is white's turn
     * @param depth the depth remaining to search
     * @return the maximized value of the position
     */
    private static int max(long wp, long wn, long wb, long wr, long wq, long wk,
                           long bp, long bn, long bb, long br, long bq, long bk,
                           long ep, boolean cwk, boolean cwq, boolean cbk, boolean cbq,
                           boolean whiteToMove, int depth) {
        if (depth == 0) {
            return Evaluation.evaluate(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
        }
        int highestScore = Integer.MIN_VALUE;
        // generate all possible moves
        String possible = PossibleMoves.possibleMovesW(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
        for (int i = 0; i < possible.length(); i += 4) {
            String move = possible.substring(i, i + 4);
            // update position
            long wp_temp = MakeMove.makeMove(wp, move, 'P');
            long wn_temp = MakeMove.makeMove(wn, move, 'N');
            long wb_temp = MakeMove.makeMove(wb, move, 'B');
            long wr_temp = MakeMove.makeMove(wr, move, 'R');
            long wq_temp = MakeMove.makeMove(wq, move, 'Q');
            long wk_temp = MakeMove.makeMove(wk, move, 'K');
            long bp_temp = MakeMove.makeMove(bp, move, 'p');
            long bn_temp = MakeMove.makeMove(bn, move, 'n');
            long bb_temp = MakeMove.makeMove(bb, move, 'b');
            long br_temp = MakeMove.makeMove(br, move, 'r');
            long bq_temp = MakeMove.makeMove(bq, move, 'q');
            long bk_temp = MakeMove.makeMove(bk, move, 'k');
            // score the new position
            int score = min(wp_temp, wn_temp, wb_temp, wr_temp, wq_temp, wk_temp,
                    bp_temp, bn_temp, bb_temp, br_temp, bq_temp, bk_temp,
                    ep, cwk, cwq, cbk, cbq, false, depth - 1);
            if (score > highestScore) {
                highestScore = score;
            }
        }
        return highestScore;
    }

}

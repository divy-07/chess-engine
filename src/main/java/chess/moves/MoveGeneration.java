package chess.moves;

import chess.board.Evaluation;

import static chess.engine.MakeMove.*;

public class MoveGeneration {

    /**
     * Calculates the best move for the current position.
     *
     * @return the best move found with basic mini-max search;
     * the string is in the format "e2e4" or "e7e8q" for promotion
     * @author Divy Pratel
     */
    public static String basicPlySearch(long wp, long wn, long wb, long wr, long wq, long wk,
                                        long bp, long bn, long bb, long br, long bq, long bk,
                                        long ep, boolean cwk, boolean cwq, boolean cbk, boolean cbq,
                                        boolean whiteToMove, int depth) {
        // TODO: debug this mini-max search algorithm
        String bestMove = "";
        int bestScore;
        if (whiteToMove) {
            bestScore = Integer.MIN_VALUE;
            String possible = PossibleMoves.possibleMovesW(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
            for (int i = 0; i < possible.length(); i += 4) {
                String move = possible.substring(i, i + 4);
                // update position
                long wp_temp = makeMove(wp, move, 'P');
                long wn_temp = makeMove(wn, move, 'N');
                long wb_temp = makeMove(wb, move, 'B');
                long wr_temp = makeMove(wr, move, 'R');
                long wq_temp = makeMove(wq, move, 'Q');
                long wk_temp = makeMove(wk, move, 'K');
                long bp_temp = makeMove(bp, move, 'p');
                long bn_temp = makeMove(bn, move, 'n');
                long bb_temp = makeMove(bb, move, 'b');
                long br_temp = makeMove(br, move, 'r');
                long bq_temp = makeMove(bq, move, 'q');
                long bk_temp = makeMove(bk, move, 'k');
                // score the new position
                int score = basicScorePosition(wp_temp, wn_temp, wb_temp, wr_temp, wq_temp, wk_temp,
                        bp_temp, bn_temp, bb_temp, br_temp, bq_temp, bk_temp,
                        ep, cwk, cwq, cbk, cbq, false, depth - 1);
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            String possible = PossibleMoves.possibleMovesB(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
            for (int i = 0; i < possible.length(); i += 4) {
                String move = possible.substring(i, i + 4);
                // update position
                long wp_temp = makeMove(wp, move, 'P');
                long wn_temp = makeMove(wn, move, 'N');
                long wb_temp = makeMove(wb, move, 'B');
                long wr_temp = makeMove(wr, move, 'R');
                long wq_temp = makeMove(wq, move, 'Q');
                long wk_temp = makeMove(wk, move, 'K');
                long bp_temp = makeMove(bp, move, 'p');
                long bn_temp = makeMove(bn, move, 'n');
                long bb_temp = makeMove(bb, move, 'b');
                long br_temp = makeMove(br, move, 'r');
                long bq_temp = makeMove(bq, move, 'q');
                long bk_temp = makeMove(bk, move, 'k');
                // score the new position
                int score = basicScorePosition(wp_temp, wn_temp, wb_temp, wr_temp, wq_temp, wk_temp,
                        bp_temp, bn_temp, bb_temp, br_temp, bq_temp, bk_temp,
                        ep, cwk, cwq, cbk, cbq, true, depth - 1);
                // int score = Evaluation.evaluate(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
                if (score < bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }

    /**
     * Recursively scores the current position using mini-max search.
     *
     * @param depth the remaining depth of the search
     * @return the score of the current position based on board.Evaluation.evaluate()
     * @author Divy Pratel
     */
    private static int basicScorePosition(long wp, long wn, long wb, long wr, long wq, long wk,
                                          long bp, long bn, long bb, long br, long bq, long bk,
                                          long ep, boolean cwk, boolean cwq, boolean cbk, boolean cbq,
                                          boolean whiteToMove, int depth) {
        if (depth == 0) {
            // base case, we have reached the end of the search
            return Evaluation.evaluate(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
        } else {
            int bestScore;
            if (whiteToMove) {
                bestScore = Integer.MIN_VALUE;
                // generate all possible moves
                String possible = PossibleMoves.possibleMovesW(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
                for (int i = 0; i < possible.length(); i += 4) {
                    String move = possible.substring(i, i + 4);
                    // update position
                    long wp_temp = makeMove(wp, move, 'P');
                    long wn_temp = makeMove(wn, move, 'N');
                    long wb_temp = makeMove(wb, move, 'B');
                    long wr_temp = makeMove(wr, move, 'R');
                    long wq_temp = makeMove(wq, move, 'Q');
                    long wk_temp = makeMove(wk, move, 'K');
                    long bp_temp = makeMove(bp, move, 'p');
                    long bn_temp = makeMove(bn, move, 'n');
                    long bb_temp = makeMove(bb, move, 'b');
                    long br_temp = makeMove(br, move, 'r');
                    long bq_temp = makeMove(bq, move, 'q');
                    long bk_temp = makeMove(bk, move, 'k');
                    // score the new position
                    int score = basicScorePosition(wp_temp, wn_temp, wb_temp, wr_temp, wq_temp, wk_temp,
                            bp_temp, bn_temp, bb_temp, br_temp, bq_temp, bk_temp,
                            ep, cwk, cwq, cbk, cbq, false, depth - 1);
                    if (score > bestScore) {
                        bestScore = score;
                    }
                }
            } else {
                bestScore = Integer.MAX_VALUE;
                String possible = PossibleMoves.possibleMovesB(wp, wn, wb, wr, wq, wk, bp, bn, bb, br, bq, bk, ep, cwk, cwq, cbk, cbq);
                for (int i = 0; i < possible.length(); i += 4) {
                    String move = possible.substring(i, i + 4);
                    // update position
                    long wp_temp = makeMove(wp, move, 'P');
                    long wn_temp = makeMove(wn, move, 'N');
                    long wb_temp = makeMove(wb, move, 'B');
                    long wr_temp = makeMove(wr, move, 'R');
                    long wq_temp = makeMove(wq, move, 'Q');
                    long wk_temp = makeMove(wk, move, 'K');
                    long bp_temp = makeMove(bp, move, 'p');
                    long bn_temp = makeMove(bn, move, 'n');
                    long bb_temp = makeMove(bb, move, 'b');
                    long br_temp = makeMove(br, move, 'r');
                    long bq_temp = makeMove(bq, move, 'q');
                    long bk_temp = makeMove(bk, move, 'k');
                    // score the new position
                    int score = basicScorePosition(wp_temp, wn_temp, wb_temp, wr_temp, wq_temp, wk_temp,
                            bp_temp, bn_temp, bb_temp, br_temp, bq_temp, bk_temp,
                            ep, cwk, cwq, cbk, cbq, true, depth - 1);
                    if (score < bestScore) {
                        bestScore = score;
                    }
                }
            }
            return bestScore;
        }
    }

}

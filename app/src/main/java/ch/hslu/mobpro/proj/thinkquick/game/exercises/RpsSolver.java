package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import ch.hslu.mobpro.proj.thinkquick.game.Gesture;

/**
 * This class provides methods to solve RPS situations.
 */

public class RpsSolver {

    private RpsSolver() {
    }

    /**
     * Returns the winner gesture based on the given looser.
     *
     * @param looser Gesture.
     * @return Winner gesture.
     */
    public static Gesture getWinner(final Gesture looser) {
        Gesture winner = null;
        switch (looser) {
            case ROCK:
                winner = Gesture.PAPER;
                break;
            case PAPER:
                winner = Gesture.SCISSOR;
                break;
            case SCISSOR:
                winner = Gesture.ROCK;
                break;
        }
        return winner;
    }

    /**
     * Returns the looser gesture based on the given winner.
     *
     * @param winner Gesture.
     * @return Looser gesture.
     */
    public static Gesture getLooser(final Gesture winner) {
        Gesture looser = null;
        switch (winner) {
            case ROCK:
                looser = Gesture.SCISSOR;
                break;
            case PAPER:
                looser = Gesture.ROCK;
                break;
            case SCISSOR:
                looser = Gesture.PAPER;
                break;
        }
        return looser;
    }
}

package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import ch.hslu.mobpro.proj.thinkquick.game.helper.Gesture;

/**
 * This class provides methods to solve RPS situations.
 */

public class RpsSolver {

    private final static int MAX = Gesture.values().length;

    private RpsSolver() {
    }

    /**
     * Returns the winner gesture based on the given looser.
     *
     * @param looser Gesture.
     * @return Winner gesture.
     */
    public static Gesture getWinner(final Gesture looser) {
        return Gesture.values()[mod(looser.getValue() + 1, MAX)];
    }

    /**
     * Returns the looser gesture based on the given winner.
     *
     * @param winner Gesture.
     * @return Looser gesture.
     */
    public static Gesture getLooser(final Gesture winner) {
        return Gesture.values()[mod(winner.getValue() - 1, MAX)];
    }

    /**
     * Returns the gesture for a draw based on the given gesture.
     *
     * @param gesture Gesture.
     * @return Draw gesture.
     */
    public static Gesture getDraw(final Gesture gesture) {
        return gesture;
    }

    /**
     * Mathematical modulo operation.
     * @param m Dividend.
     * @param n divisor.
     * @return m modulo n.
     */
    public static int mod(final int m, final int n) {
        int r = m % n;
        if (r < 0) {
            r += n;
        }
        return r;
    }
}

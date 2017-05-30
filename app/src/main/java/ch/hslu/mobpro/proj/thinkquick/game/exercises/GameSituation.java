package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import ch.hslu.mobpro.proj.thinkquick.game.helper.Gesture;

/**
 * This class represents the result of a game. A result has the leftHand and rightHand of a game.
 */

public class GameSituation {
    private Gesture leftHand;
    private Gesture rightHand;
    private boolean isDraw;

    public GameSituation(Gesture leftHand, Gesture rightHand, boolean isDraw) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.isDraw = isDraw;
    }

    public Gesture getLeftHand() {
        return leftHand;
    }

    public Gesture getRightHand() {
        return rightHand;
    }

    public boolean isDraw() {
        return isDraw;
    }

    /**
     * Gets the winner of this situation. This method uses the RpsSolver class.
     *
     * @return The winner gesture or null if it is a draw.
     */
    public Gesture getWinner() {
        Gesture winner = null;
        if (RpsSolver.getWinner(leftHand) == rightHand) {
            winner = rightHand;
        } else if (RpsSolver.getWinner(rightHand) == leftHand) {
            winner = leftHand;
        }
        return winner;
    }

    /**
     * Gets the looser of this situation. This method uses the RpsSolver class.
     * @return The looser gesture or null if it is a draw.
     */
    public Gesture getLooser() {
        Gesture looser = null;
        if (RpsSolver.getLooser(leftHand) == rightHand) {
            looser = rightHand;
        } else if (RpsSolver.getLooser(rightHand) == leftHand) {
            looser = leftHand;
        }
        return looser;
    }
}

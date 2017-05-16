package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import ch.hslu.mobpro.proj.thinkquick.game.Gesture;

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

    public Gesture getWinner() {
        Gesture winner = leftHand;
        if (RpsSolver.getWinner(leftHand) == rightHand) {
            winner = rightHand;
        }
        return winner;
    }

    public Gesture getLooser() {
        Gesture looser = leftHand;
        if (getWinner() == leftHand) {
            looser = rightHand;
        }
        return looser;
    }
}

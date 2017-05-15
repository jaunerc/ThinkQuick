package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import ch.hslu.mobpro.proj.thinkquick.game.Gesture;

/**
 * This class represents the result of a game. A result has the winner and looser of a game.
 */

public class GameSituation {
    private Gesture winner;
    private Gesture looser;
    private boolean isDraw;

    public GameSituation(Gesture winner, Gesture looser, boolean isDraw) {
        this.winner = winner;
        this.looser = looser;
        this.isDraw = isDraw;
    }

    public Gesture getWinner() {
        return winner;
    }

    public Gesture getLooser() {
        return looser;
    }

    public boolean isDraw() {
        return isDraw;
    }
}

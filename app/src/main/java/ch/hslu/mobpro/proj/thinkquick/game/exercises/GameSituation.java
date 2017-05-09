package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import ch.hslu.mobpro.proj.thinkquick.game.Gesture;

/**
 * Created by Alan Meile on 08.05.2017.
 */

public class GameSituation {
    private Gesture winner;
    private Gesture looser;
    private boolean isDraw;

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

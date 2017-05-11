package ch.hslu.mobpro.proj.thinkquick.game.checker;

/**
 * Created by Alan Meile on 08.05.2017.
 */

public class ExerciseResult {
    private boolean correct;
    private int points;

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isCorrect() {
        return correct;
    }

    public int getPoints() {
        return points;
    }
}

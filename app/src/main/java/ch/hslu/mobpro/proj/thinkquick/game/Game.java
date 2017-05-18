package ch.hslu.mobpro.proj.thinkquick.game;

import android.content.Context;

import ch.hslu.mobpro.proj.thinkquick.game.checker.ExerciseResult;

/**
 * Created by Alan Meile on 08.05.2017.
 */

public interface Game {
    void start(Context context);
    void nextExercise();

    void gameOver();
    void skip();
    void pause();

    void timeUp();
    ExerciseResult solveWith(Gesture answer);

    void deductPlayerLife();

    int getPlayerLife();

    void awardPlayerPoints(int awardedPoints);

    int getPlayerPoints();
}

package ch.hslu.mobpro.proj.thinkquick.game;

import android.content.Context;

import ch.hslu.mobpro.proj.thinkquick.game.checker.ExerciseResult;

/**
 * Created by Alan Meile on 08.05.2017.
 */

public interface Game {
    void start(Context context);
    void nextExercise();
    void skip();
    void pause();
    ExerciseResult solveWith(Gesture answer);
}

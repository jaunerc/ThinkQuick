package ch.hslu.mobpro.proj.thinkquick.game.mode;

import android.content.Context;
import android.content.SharedPreferences;

import ch.hslu.mobpro.proj.thinkquick.game.RPSGame;

/**
 *
 */

public class HardcoreMode implements GameModeStrategy {
    private final static String NUM_EXERCICES_DONE = "num_exercices";
    private GameConfig gameConfig;
    private int numExercisesDone;

    public HardcoreMode(final GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        numExercisesDone = 0;
    }

    @Override
    public void init() {

    }

    @Override
    public void updateGameforNextExercise(final RPSGame rpsGame) {
        rpsGame.setMaxProgress(gameConfig.getMaxProgress());
        numExercisesDone++;
    }

    @Override
    public GameConfig getGameConfig() {
        return gameConfig;
    }

    @Override
    public void storeInPreferences(Context context) {
        final SharedPreferences preferences = getPreferencesFromContext(context);
        preferences.edit().putInt(NUM_EXERCICES_DONE, numExercisesDone).commit();
    }

    @Override
    public void restoreFromPreferences(Context context) {

    }

    @Override
    public void gameOver(Context context) {

    }

    private SharedPreferences getPreferencesFromContext(final Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }
}

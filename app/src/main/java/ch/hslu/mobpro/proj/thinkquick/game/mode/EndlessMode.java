package ch.hslu.mobpro.proj.thinkquick.game.mode;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import ch.hslu.mobpro.proj.thinkquick.R;
import ch.hslu.mobpro.proj.thinkquick.game.RPSGame;
import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceHandler;
import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceSingleton;

/**
 * This class represents the endless game mode.
 */

public class EndlessMode implements GameModeStrategy {
    private final static String NUM_EXERCICES_DONE = "num_exercices";

    private GameConfig gameConfig;
    private int numExercisesDone;

    public EndlessMode(final GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        numExercisesDone = 0;
    }

    @Override
    public void init() {
        // do nothing in this mode.
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
        final SharedPreferences preferences = getPreferencesFromContext(context);
        numExercisesDone = preferences.getInt(NUM_EXERCICES_DONE, 0);
        Log.i("EndlessMode_num_exerc", "" + numExercisesDone);
    }

    @Override
    public void gameOver(Context context) {
        final SharedPreferences preferences = getPreferencesFromContext(context);
        preferences.edit().putInt(NUM_EXERCICES_DONE, 0).commit();

        PreferenceHandler preferenceHandler = PreferenceSingleton.getHandler(context);
        String label = context.getResources().getString(R.string.game_mode_btn_endless);
        preferenceHandler.setGameModeForDb(label);
    }

    private SharedPreferences getPreferencesFromContext(final Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }
}

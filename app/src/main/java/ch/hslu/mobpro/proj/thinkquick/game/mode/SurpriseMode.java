package ch.hslu.mobpro.proj.thinkquick.game.mode;

import android.content.Context;

import ch.hslu.mobpro.proj.thinkquick.R;
import ch.hslu.mobpro.proj.thinkquick.game.RPSGame;
import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceHandler;
import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceSingleton;

/**
 *
 */

public class SurpriseMode implements GameModeStrategy {
    private GameConfig gameConfig;

    public SurpriseMode(final GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    @Override
    public void init() {

    }

    @Override
    public void updateGameforNextExercise(final RPSGame rpsGame) {

    }

    @Override
    public GameConfig getGameConfig() {
        return gameConfig;
    }

    @Override
    public void storeInPreferences(Context context) {

    }

    @Override
    public void restoreFromPreferences(Context context) {

    }

    @Override
    public void gameOver(Context context) {
        PreferenceHandler preferenceHandler = PreferenceSingleton.getHandler(context);
        String label = context.getResources().getString(R.string.game_mode_btn_surprise);
        preferenceHandler.setGameModeForDb(label);
    }
}

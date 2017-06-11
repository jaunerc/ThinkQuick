package ch.hslu.mobpro.proj.thinkquick.game.mode;

import android.content.Context;

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
    public void updateGameforNextExercise(Context context) {

    }

    @Override
    public GameConfig getGameConfig() {
        return gameConfig;
    }
}

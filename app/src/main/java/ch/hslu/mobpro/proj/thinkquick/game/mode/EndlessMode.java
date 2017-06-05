package ch.hslu.mobpro.proj.thinkquick.game.mode;

import android.content.Context;

/**
 * This class represents the endless game mode.
 */

public class EndlessMode implements GameModeStrategy {
    private GameConfig gameConfig;

    public EndlessMode(final GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    @Override
    public void init() {
        // do nothing in this mode.
    }

    @Override
    public void updateGameforNextExercise(Context context) {
        // do nothing in this mode.
    }

    @Override
    public GameConfig getGameConfig() {
        return gameConfig;
    }
}

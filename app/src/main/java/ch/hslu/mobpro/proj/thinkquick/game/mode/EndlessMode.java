package ch.hslu.mobpro.proj.thinkquick.game.mode;

import android.content.Context;

import ch.hslu.mobpro.proj.thinkquick.game.RPSGame;

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
    public void updateGameforNextExercise(final RPSGame rpsGame) {
        rpsGame.setMaxProgress(gameConfig.getMaxProgress());
    }

    @Override
    public GameConfig getGameConfig() {
        return gameConfig;
    }
}

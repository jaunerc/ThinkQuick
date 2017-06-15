package ch.hslu.mobpro.proj.thinkquick.game.mode;

import android.content.Context;

import ch.hslu.mobpro.proj.thinkquick.game.RPSGame;

/**
 *
 */

public class HardcoreMode implements GameModeStrategy {
    private GameConfig gameConfig;

    public HardcoreMode(final GameConfig gameConfig) {
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
}

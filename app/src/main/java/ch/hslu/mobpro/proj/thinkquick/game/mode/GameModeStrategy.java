package ch.hslu.mobpro.proj.thinkquick.game.mode;

import android.content.Context;

/**
 * This interface provides a game mode.
 */

public interface GameModeStrategy {

    /**
     * Initializes the game mode. This method should be invoked first.
     */
    void init();

    /**
     * Updates the game config for the next exercise.
     * @param context The application context.
     */
    void updateGameforNextExercise(Context context);

    /**
     * Returns the current game config.
     * @return Game config.
     */
    GameConfig getGameConfig();
}

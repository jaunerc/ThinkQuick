package ch.hslu.mobpro.proj.thinkquick.game.mode;

import android.content.Context;

import ch.hslu.mobpro.proj.thinkquick.game.RPSGame;

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
     * @param rpsGame The game instance.
     */
    void updateGameforNextExercise(RPSGame rpsGame);

    /**
     * Returns the current game config.
     * @return Game config.
     */
    GameConfig getGameConfig();

    /**
     * Stores all required data in app preferences.
     *
     * @param context The application context.
     */
    void storeInPreferences(Context context);

    /**
     * Loads all required data from app preferences.
     *
     * @param context The application context.
     */
    void restoreFromPreferences(Context context);

    /**
     * Handle game over.
     *
     * @param context The application context.
     */
    void gameOver(Context context);
}

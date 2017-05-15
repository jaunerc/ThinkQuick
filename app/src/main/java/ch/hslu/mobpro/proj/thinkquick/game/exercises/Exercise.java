package ch.hslu.mobpro.proj.thinkquick.game.exercises;

/**
 * This interface provides an exercise. Each exercise has a game situation and a quest.
 */
public interface Exercise {

    /**
     * Gets the game situation of this exercise.
     *
     * @return The game situation.
     */
    GameSituation getGameSituation();

    /**
     * Gets the quest of this exercise.
     *
     * @return The quest.
     */
    Quest getQuest();
}

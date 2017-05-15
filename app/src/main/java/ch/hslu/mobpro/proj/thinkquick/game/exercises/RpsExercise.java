package ch.hslu.mobpro.proj.thinkquick.game.exercises;

/**
 * This class represents an exercise implementation.
 */

public class RpsExercise implements Exercise {
    private GameSituation gameSituation;
    private Quest quest;

    public RpsExercise(GameSituation gameSituation, Quest quest) {
        this.gameSituation = gameSituation;
        this.quest = quest;
    }

    @Override
    public GameSituation getGameSituation() {
        return gameSituation;
    }

    @Override
    public Quest getQuest() {
        return quest;
    }
}

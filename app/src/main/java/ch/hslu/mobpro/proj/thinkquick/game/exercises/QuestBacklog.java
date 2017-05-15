package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import java.util.Random;

import ch.hslu.mobpro.proj.thinkquick.game.Gesture;

/**
 * This class represents a backlog for quest texts.
 */

public class QuestBacklog {
    private String[] easyQuestTexts = new String[]{
            "Win!",
            "Lose!",
            "Make a draw!"
    };

    private String[] hardQuestTexts = new String[]{
            "Win against the winner!",
            "Win against the looser!",
            "Lose against the winner!",
            "Lose against the looser!",
            "Make a draw with the winner!",
            "Make a draw with the looser!"
    };

    public QuestBacklog(final String[] easyQuestTexts, final String[] hardQuestTexts) {
        this.easyQuestTexts = easyQuestTexts;
        this.hardQuestTexts = hardQuestTexts;
    }

    /**
     * Returns a random easy quest text.
     *
     * @param random Random generator.
     * @return easy text.
     */
    public String randomEasyText(final Random random) {
        return easyQuestTexts[random.nextInt(easyQuestTexts.length)];
    }

    public Quest randomEasyQuest(final Random random, final Gesture winner) {
        return new Quest(randomEasyText(random), winner);
    }

    /**
     * Returns a random hard quest text.
     *
     * @param random Random generator.
     * @return hard text.
     */
    public String randomHardText(final Random random) {
        return hardQuestTexts[random.nextInt(hardQuestTexts.length)];
    }

    public Quest randomHardQuest(final Random random, final Gesture winner) {
        return new Quest(randomHardText(random), winner);
    }
}

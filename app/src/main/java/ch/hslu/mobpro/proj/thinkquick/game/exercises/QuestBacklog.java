package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import android.content.Context;

import java.util.List;
import java.util.Random;

import ch.hslu.mobpro.proj.thinkquick.R;

/**
 * This class represents a backlog for quest texts.
 */

public class QuestBacklog {
    private String[] easyQuestTexts;
    private String[] hardQuestTexts;
    private List<Quest> easyQuests;
    private List<Quest> hardQuests;

    public QuestBacklog(final String[] easyQuestTexts, final String[] hardQuestTexts) {
        this.easyQuestTexts = easyQuestTexts;
        this.hardQuestTexts = hardQuestTexts;
    }

    public void initQuests(final Context context) {
        easyQuests.add(new Quest(context.getResources().getString(R.string.easyWin), QuestTarget.WIN, true));
        easyQuests.add(new Quest(context.getResources().getString(R.string.easyLoose), QuestTarget.LOSE, true));
        easyQuests.add(new Quest(context.getResources().getString(R.string.easyDraw), QuestTarget.DRAW, true));

        hardQuests.add(new Quest(context.getResources().getString(R.string.hardWinWin), QuestTarget.WIN, true));
        hardQuests.add(new Quest(context.getResources().getString(R.string.hardLooseWin), QuestTarget.LOSE, true));
        hardQuests.add(new Quest(context.getResources().getString(R.string.hardDrawWin), QuestTarget.DRAW, true));
        hardQuests.add(new Quest(context.getResources().getString(R.string.hardWinLoose), QuestTarget.WIN, false));
        hardQuests.add(new Quest(context.getResources().getString(R.string.hardLooseLoose), QuestTarget.LOSE, false));
        hardQuests.add(new Quest(context.getResources().getString(R.string.hardDrawLoose), QuestTarget.DRAW, false));
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

    public Quest randomEasyQuest(final Random random) {
        return easyQuests.get(random.nextInt(easyQuests.size()));
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

    public Quest randomHardQuest(final Random random) {
        return hardQuests.get(random.nextInt(hardQuests.size()));
    }
}

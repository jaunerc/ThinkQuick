package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.hslu.mobpro.proj.thinkquick.R;

/**
 * This class represents a backlog for quest texts.
 */

public class QuestBacklog {
    private List<Quest> easyQuests;
    private List<Quest> hardQuests;

    public QuestBacklog() {
        easyQuests = new ArrayList<>();
        hardQuests = new ArrayList<>();
    }

    /**
     * Initializes all quests from the given context. This method expects quest infos in the android String resource files.
     *
     * @param context Application context.
     */
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
     * Randomly choose a easy quest and returns it.
     * @param random Random generator.
     * @return Easy quest.
     */
    public Quest randomEasyQuest(final Random random) {
        return easyQuests.get(random.nextInt(easyQuests.size()));
    }

    /**
     * Randomly choose a hard quest and returns it.
     * @param random Random generator.
     * @return Hard quest.
     */
    public Quest randomHardQuest(final Random random) {
        return hardQuests.get(random.nextInt(hardQuests.size()));
    }
}

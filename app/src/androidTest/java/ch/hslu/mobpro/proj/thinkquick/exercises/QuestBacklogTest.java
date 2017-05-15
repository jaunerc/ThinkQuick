package ch.hslu.mobpro.proj.thinkquick.exercises;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import ch.hslu.mobpro.proj.thinkquick.R;
import ch.hslu.mobpro.proj.thinkquick.game.Gesture;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.Quest;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.QuestBacklog;

import static org.junit.Assert.assertEquals;

/**
 * Test for @see ch.hslu.mobpro.proj.thinkquick.game.exercises.QuestBacklog.
 */

@RunWith(AndroidJUnit4.class)
public class QuestBacklogTest {

    @Test
    public void testRandomEasyQuest() {
        Context context = InstrumentationRegistry.getTargetContext();
        String[] easyQuests = context.getResources().getStringArray(R.array.easyQuestInfos);
        String[] hardQuests = context.getResources().getStringArray(R.array.hardQuestInfos);
        QuestBacklog backlog = new QuestBacklog(easyQuests, hardQuests);
        Quest easyQuest = backlog.randomEasyQuest(new Random(), Gesture.ROCK);
        assertEquals(Gesture.ROCK, easyQuest.getAnswer());
    }

    @Test
    public void testRandomHardQuest() {
        Context context = InstrumentationRegistry.getTargetContext();
        String[] easyQuests = context.getResources().getStringArray(R.array.easyQuestInfos);
        String[] hardQuests = context.getResources().getStringArray(R.array.hardQuestInfos);
        QuestBacklog backlog = new QuestBacklog(easyQuests, hardQuests);
        Quest hardQuest = backlog.randomEasyQuest(new Random(), Gesture.PAPER);
        assertEquals(Gesture.PAPER, hardQuest.getAnswer());
    }
}

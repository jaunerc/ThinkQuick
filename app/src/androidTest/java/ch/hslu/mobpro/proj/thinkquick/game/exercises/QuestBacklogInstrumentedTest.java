package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test for {@link ch.hslu.mobpro.proj.thinkquick.game.exercises.QuestBacklog}.
 */

@RunWith(AndroidJUnit4.class)
public class QuestBacklogInstrumentedTest {
    private Context context;
    private QuestBacklog questBacklog;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
        questBacklog = new QuestBacklog();
        questBacklog.initQuests(context);
    }

    @Test
    public void testRandomEasyQuest() {
        try {
            questBacklog.randomEasyQuest(new Random());
            assertTrue(true);
        } catch (IndexOutOfBoundsException e) {
            assertFalse(true);
        }
    }

    @Test
    public void testRandomHardQuest() {
        try {
            questBacklog.randomHardQuest(new Random());
            assertTrue(true);
        } catch (IndexOutOfBoundsException e) {
            assertFalse(true);
        }
    }
}

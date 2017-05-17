package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test for {@link ch.hslu.mobpro.proj.thinkquick.game.exercises.ExerciseFactory}.
 */
@RunWith(AndroidJUnit4.class)
public class ExerciseFactoryInstrumentedTest {
    private Context context;
    private QuestBacklog questBacklog;
    private ExerciseFactory exerciseFactory;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
        questBacklog = new QuestBacklog();
        questBacklog.initQuests(context);
        exerciseFactory = new ExerciseFactory(new Random(), questBacklog);
    }

    @Test
    public void testEasyExercise() {
        try {
            final Exercise exercise = exerciseFactory.easyExercise();
            final GameSituation gameSituation = exercise.getGameSituation();
            assertEquals(gameSituation.getLeftHand(), gameSituation.getRightHand());
        } catch (Exception e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void testHardExercise() {

    }
}

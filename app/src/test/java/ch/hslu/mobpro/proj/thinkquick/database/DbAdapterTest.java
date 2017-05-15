package ch.hslu.mobpro.proj.thinkquick.database;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;

import ch.hslu.mobpro.proj.thinkquick.BuildConfig;
import ch.hslu.mobpro.proj.thinkquick.game.checker.ExerciseResult;

import static junit.framework.Assert.assertEquals;

/**
 * Test for @see ch.hslu.mobpro.proj.thinkquick.database.DbAdapter.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DbAdapterTest {

    private DbAdapter adapter;

    @Before
    public void setup() {
        adapter = new DbAdapter(RuntimeEnvironment.application);
        adapter.open();
    }

    @Test
    public void testInsert() {
        final ExerciseResult result = new ExerciseResult();
        result.setPoints(1000);
        try {
            adapter.insert(result);
            List<ExerciseResult> results = adapter.getAllResults();
            assertEquals(1, results.size());
            assertEquals(1000, results.get(0).getPoints());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            adapter.close();
        }
    }
}

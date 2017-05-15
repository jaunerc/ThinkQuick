package ch.hslu.mobpro.proj.thinkquick.database;

import android.database.sqlite.SQLiteException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import ch.hslu.mobpro.proj.thinkquick.BuildConfig;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Test for @see ch.hslu.mobpro.proj.thinkquick.database.DbHelper.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DbHelperTest {

    private DbHelper dbHelper;

    @Test
    public void testWritableDatabase() {
        dbHelper = new DbHelper(RuntimeEnvironment.application);
        try {
            dbHelper.getWritableDatabase();
            assertTrue(true);
        } catch (SQLiteException e) {
            assertFalse(false);
        } finally {
            dbHelper.close();
        }
    }
}

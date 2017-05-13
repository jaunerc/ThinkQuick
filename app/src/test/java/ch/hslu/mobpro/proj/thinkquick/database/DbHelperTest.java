package ch.hslu.mobpro.proj.thinkquick.database;

/**
 * Created by Cyrill Jauner on 12/05/2017.
 */

import android.database.sqlite.SQLiteException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import ch.hslu.mobpro.proj.thinkquick.BuildConfig;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

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

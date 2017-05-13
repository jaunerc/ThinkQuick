package ch.hslu.mobpro.proj.thinkquick.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.hslu.mobpro.proj.thinkquick.game.checker.ExerciseResult;

/**
 * This class represents a database adapter.
 */

public class DbAdapter {

    public final static String DB_NAME = "thinkDb";
    public final static int DB_VERSION = 1;
    public final static String DB_RESULT_TABLE = "tbl_results";
    private final static int SQLITE_ERROR_CODE = -1;

    private final DbHelper dbHelper;
    private SQLiteDatabase db;

    public DbAdapter(final Context context) {
        dbHelper = new DbHelper(context);
    }

    /**
     * Opens the database with write access.
     */
    public void open() {
        if (db == null || !db.isOpen()) {
            db = dbHelper.getWritableDatabase();
        }
    }

    /**
     * Closes the database.
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * Inserts the given result into the database.
     *
     * @param result The exercise result.
     * @throws IOException If the result could not be inserted.
     */
    public void insert(final ExerciseResult result) throws IOException {
        final ContentValues values = new ContentValues();
        values.put("date", "12.05.2017");
        values.put("points", result.getPoints());
        final long id = db.insert(DB_RESULT_TABLE, null, values);
        if (id == SQLITE_ERROR_CODE) {
            throw new IOException("The result could not be inserted.");
        }
    }

    /**
     * Reads all results from the result table.
     *
     * @return A list with all results.
     * @throws IOException If the results could not be read.
     */
    public List<ExerciseResult> getAllResults() throws IOException {
        final List<ExerciseResult> results = new ArrayList<>();
        final Cursor cursor = initAllResultCursor(DB_RESULT_TABLE);
        while (!cursor.isAfterLast()) {
            results.add(fetchResultFromCursor(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return results;
    }

    /**
     * Initializes a db cursor to read all results.
     *
     * @param tableName The table with results.
     * @return Database sursor.
     * @throws IOException If the results could not be read.
     */
    private Cursor initAllResultCursor(final String tableName) throws IOException {
        final Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);
        if (!cursor.moveToFirst()) {
            throw new IOException("Could not read from table " + tableName);
        }
        return cursor;
    }

    /**
     * Reads the current table entry of the given cursor. This method creates an ExerciseResult object.
     *
     * @param cursor Database cursor.
     * @return The current result.
     */
    private ExerciseResult fetchResultFromCursor(final Cursor cursor) {
        final ExerciseResult currentResult = new ExerciseResult();
        currentResult.setPoints(cursor.getInt(1));
        return currentResult;
    }
}

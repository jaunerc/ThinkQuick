package ch.hslu.mobpro.proj.thinkquick.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Deprecated
    public void insert(final ExerciseResult result) throws IOException {
        if (result.getPoints() > 0) {
            final ContentValues values = new ContentValues();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            values.put("date", simpleDateFormat.format(new Date()));
            values.put("points", result.getPoints());
            final long id = db.insert(DB_RESULT_TABLE, null, values);
            if (id == SQLITE_ERROR_CODE) {
                throw new IOException("The result could not be inserted.");
            }
        }
    }

    public void insert(final int points, final Date date, final String mode) throws IOException {
        if (points > 0) {
            final ContentValues values = new ContentValues();
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat = getSystemDateFormat();
            values.put("date", simpleDateFormat.format(date));
            values.put("points", points);
            values.put("mode", mode);
            System.out.println("mode - "+mode);
            final long id = db.insert(DB_RESULT_TABLE, null, values);
            if (id == SQLITE_ERROR_CODE) {
                throw new IOException("The result could not be inserted.");
            }
        }
    }

    private SimpleDateFormat getSystemDateFormat() {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(dbHelper.getContext());
        return (SimpleDateFormat) dateFormat;
    }

    /**
     * Reads all results from the result table.
     *
     * @return A list with all results.
     * @throws IOException If the results could not be read.
     */
    public List<DbResultsEntry> getAllResults() throws IOException {
        final List<DbResultsEntry> results = new ArrayList<>();
        final Cursor cursor = initAllResultCursor(DB_RESULT_TABLE);
        while (!cursor.isAfterLast()) {
            results.add(fetchResultFromCursor(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return results;
    }

    /**
     * Initializes a db cursor to read all results ordered by Points.
     *
     * @param tableName The table with results.
     * @return Database sursor.
     * @throws IOException If the results could not be read.
     */
    private Cursor initAllResultCursor(final String tableName) throws IOException {
        final Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + " ORDER BY CAST(points AS INTEGER) DESC", null);
        if (!cursor.moveToFirst()) {
            throw new IOException("Could not read from table " + tableName);
        }
        return cursor;
    }

    public void clearAllContent() {
        db.execSQL("delete from " + DB_RESULT_TABLE);
    }

    /**
     * Reads the current table entry of the given cursor. This method creates an ExerciseResult object.
     *
     * @param cursor Database cursor.
     * @return The current result.
     */
    private DbResultsEntry fetchResultFromCursor(final Cursor cursor) {
        final DbResultsEntry currentResult = new DbResultsEntry();
        currentResult.setPoints(cursor.getInt(1));
        currentResult.setDate(cursor.getString(0));
        currentResult.setMode(cursor.getString(2));
        return currentResult;
    }
}
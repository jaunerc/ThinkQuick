package ch.hslu.mobpro.proj.thinkquick.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class represents a helper for SQLite databases.
 */

public class DbHelper extends SQLiteOpenHelper {
    private Context context;

    public DbHelper(final Context context) {
        super(context, DbAdapter.DB_NAME, null, DbAdapter.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("DB was created");
        db.execSQL("CREATE TABLE " + DbAdapter.DB_RESULT_TABLE + " (date not null, points int not null, mode TEXT not null, id INTEGER PRIMARY KEY);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Context getContext() {
        return context;
    }
}

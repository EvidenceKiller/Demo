package com.example.loader;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zxn on 17-4-19.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    private static final String CREATE_PERSON = PersonDataBaseUtils.OPERATION_CREATE + " " + PersonDataBaseUtils.TablePerson.TABLE_NAME
            + " (" + PersonDataBaseUtils.TablePerson.PERSON_COLUMN_ID + " INTEGER PRIMARY KEY, "
            + PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME + " VARCHAR NOT NULL, "
            + PersonDataBaseUtils.TablePerson.PERSON_COLUMN_AGE + " INTEGER)";

    private static final String DATABASE_NAME = "person";

    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "DBHelper onUpgrade() oldVersion = " + oldVersion + " newVersion = " + newVersion);
        if (oldVersion != newVersion) {
            db.beginTransaction();
            try {
                // TODO
            } catch (SQLiteException sqle) {
                sqle.printStackTrace();
            } finally {
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        }
    }
}

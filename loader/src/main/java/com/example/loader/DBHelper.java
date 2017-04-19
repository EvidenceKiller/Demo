package com.example.loader;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zxn on 17-4-19.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    private static final String CREATE_PERSON = PersonDataBaseUtils.OPERATION_CREATE + " " + PersonDataBaseUtils.TablePerson.TABLE_NAME
            + " (" + PersonDataBaseUtils.TablePerson.PERSON_COLUMN_ID + " INTEGER PRIMARY KEY, "
            + PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME + " VARCHAR NOT NULL, "
            + PersonDataBaseUtils.TablePerson.PERSON_COLUMN_AGE + " INTEGER)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

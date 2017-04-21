package com.example.loader;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by zxn on 17-4-19.
 */

public class PersonProvider extends ContentProvider {

    private static final String TAG = "PersonProvider";

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private DBHelper mDBHelper;

    private SQLiteDatabase getDatabase() {
        if (null == mDBHelper) {
            mDBHelper = new DBHelper(this.getContext());
        }
        return mDBHelper.getWritableDatabase();
    }

    @Override
    public boolean onCreate() {
        init();
        return false;
    }

    private void init() {
        synchronized (sUriMatcher) {
            sUriMatcher.addURI(PersonDataBaseUtils.AUTOR, PersonDataBaseUtils.TablePerson.TABLE_NAME, 1);
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

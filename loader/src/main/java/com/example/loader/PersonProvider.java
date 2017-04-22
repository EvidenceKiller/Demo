package com.example.loader;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

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
            sUriMatcher.addURI(PersonDataBaseUtils.AUTOR, PersonDataBaseUtils.TablePerson.TABLE_NAME,
                    PersonDataBaseUtils.TablePerson.MATCH_NUM_NAME_LIST);
            sUriMatcher.addURI(PersonDataBaseUtils.AUTOR, PersonDataBaseUtils.TablePerson.TABLE_NAME + "/*",
                    PersonDataBaseUtils.TablePerson.MATCH_NUM_NAME_ITEM);
            sUriMatcher.addURI(PersonDataBaseUtils.AUTOR, PersonDataBaseUtils.TablePerson.TABLE_NAME,
                    PersonDataBaseUtils.TablePerson.MATCH_NUM_AGE_LIST);
            sUriMatcher.addURI(PersonDataBaseUtils.AUTOR, PersonDataBaseUtils.TablePerson.TABLE_NAME + "/*",
                    PersonDataBaseUtils.TablePerson.MATCH_NUM_AGE_ITEM);
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = getDatabase();
        Cursor cursor = null;
        try {
            int type = sUriMatcher.match(uri);
            switch (type) {
                case PersonDataBaseUtils.TablePerson.MATCH_NUM_NAME_LIST:
                    cursor = db.query(PersonDataBaseUtils.TablePerson.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                    break;
                case PersonDataBaseUtils.TablePerson.MATCH_NUM_NAME_ITEM:
                    String queryName = uri.getPathSegments().get(1);
                    String query_name_where = PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME + "='" + queryName + "'";
                    if (!TextUtils.isEmpty(selection)) {
                        query_name_where = query_name_where + " and " + selection;
                    }
                    cursor = db.query(PersonDataBaseUtils.TablePerson.TABLE_NAME, projection, query_name_where, selectionArgs, null, null, sortOrder);
                    break;
                case PersonDataBaseUtils.TablePerson.MATCH_NUM_AGE_LIST:
                    cursor = db.query(PersonDataBaseUtils.TablePerson.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                    break;
                case PersonDataBaseUtils.TablePerson.MATCH_NUM_AGE_ITEM:
                    String queryAge = uri.getPathSegments().get(1);
                    String query_age_where = PersonDataBaseUtils.TablePerson.PERSON_COLUMN_AGE + "='" + queryAge + "'";
                    if (!TextUtils.isEmpty(selection)) {
                        query_age_where = query_age_where + " and " + selection;
                    }
                    cursor = db.query(PersonDataBaseUtils.TablePerson.TABLE_NAME, projection, query_age_where, selectionArgs, null, null, sortOrder);
                    break;
            }
        }
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

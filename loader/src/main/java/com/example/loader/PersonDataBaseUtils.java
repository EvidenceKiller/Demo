package com.example.loader;

import android.net.Uri;

/**
 * Created by zxn on 17-4-19.
 */

public class PersonDataBaseUtils {

    public static final String DATABASE_NAME = "person";

    public static final String OPERATION_CREATE = "CREATE TABLE IF NOT EXISTS";

    public static final String OPERATION_ADD = "ALTER TABLE";

    public static final String OPERATION_INSERT = "INSERT INTO";

    public static final String OPERATION_DELETE = "DROP TABLE";

    public static final String AUTOR = "com.example.loader.PersonProvider";

    public static final String BASE_URI = "content://" + AUTOR + "/";

    public static final class TablePerson {

        public static final String TABLE_NAME = "person";

        public static final Uri CONTENT_URI = Uri.parse(BASE_URI + TABLE_NAME);

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.example.provider." + TABLE_NAME;

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/com.example.provider." + TABLE_NAME;

        public static final String PERSON_COLUMN_ID = "_id";

        public static final String PERSON_COLUMN_NAME = "name";

        public static final String PERSON_COLUMN_AGE = "age";

    }

}

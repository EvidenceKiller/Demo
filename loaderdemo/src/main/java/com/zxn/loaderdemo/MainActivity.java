package com.zxn.loaderdemo;

import android.Manifest;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, TextWatcher {

    private static final String TAG = "ManiActivity";

    private EditText mEditText;
    private ListView mListView;

    private SimpleCursorAdapter mAdapter;

    private static final int CURSOR_LOADER_ID = 1;

    private static final String KEY_BUNDLE_FILTER = "filter";

    private static final int REQ_PERMISSION_READ_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.et_name);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return false;
            }
        });
        mEditText.addTextChangedListener(this);

        mListView = (ListView) findViewById(R.id.lv_result);
        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.CONTACT_STATUS},
                new int[]{android.R.id.text1, android.R.id.text2},
                0);
        mListView.setAdapter(mAdapter);

        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQ_PERMISSION_READ_CONTACTS);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(KEY_BUNDLE_FILTER, null);
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(CURSOR_LOADER_ID, bundle, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_PERMISSION_READ_CONTACTS) {
            if (PermissionChecker.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                Bundle bundle = new Bundle();
                bundle.putString(KEY_BUNDLE_FILTER, null);
                LoaderManager loaderManager = getLoaderManager();
                loaderManager.initLoader(CURSOR_LOADER_ID, bundle, this);
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri uri;
        String filter = bundle != null ? bundle.getString(KEY_BUNDLE_FILTER) : null;
        if (!TextUtils.isEmpty(filter)) {
            // 根据用户指定的filter过滤显示
            uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, Uri.encode(filter));
        } else {
            // 显示全部
            uri = ContactsContract.Contacts.CONTENT_URI;
        }
        Log.i(TAG, "uri : " + uri);

        String[] projection = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.CONTACT_STATUS
        };

        String selection = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND " +
                "(" + ContactsContract.Contacts.HAS_PHONE_NUMBER + " =1) AND " +
                "(" + ContactsContract.Contacts.DISPLAY_NAME + " != ''))";

        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        return new CursorLoader(this, uri, projection, selection, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String filter = mEditText.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_BUNDLE_FILTER, filter);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(CURSOR_LOADER_ID, bundle, this);
    }
}

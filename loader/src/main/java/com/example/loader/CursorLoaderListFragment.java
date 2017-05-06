package com.example.loader;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by zxn on 17-4-23.
 */

public class CursorLoaderListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>,
        SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private static final String TAG = CursorLoaderListFragment.class.getSimpleName();

    public static final String[] PERSON_PROJECTION = new String[]{PersonDataBaseUtils.TablePerson.PERSON_COLUMN_ID,
            PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME,
            PersonDataBaseUtils.TablePerson.PERSON_COLUMN_AGE};

    SimpleCursorAdapter mAdapter;

    MySearchView mSearchView;

    String mCurFilter;

    private int mCount = 0;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText("no people");
        setHasOptionsMenu(true);

        mAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_2, null,
                new String[]{PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME,
                        PersonDataBaseUtils.TablePerson.PERSON_COLUMN_AGE},
                new int[]{android.R.id.text1, android.R.id.text2}, 0);
        setListAdapter(mAdapter);
        setListShown(false);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.add("Search");
        item.setIcon(android.R.drawable.ic_menu_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        mSearchView = new MySearchView(getActivity());
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnCloseListener(this);
        mSearchView.setIconifiedByDefault(true);
        item.setActionView(mSearchView);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(), "Item clicked : " + id, Toast.LENGTH_LONG).show();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader");
        Uri baseUri;
        baseUri = PersonDataBaseUtils.TablePerson.CONTENT_URI;
        String select = "((" + PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME + " NOT NULL) AND ("
                + PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME + " != ''))";
        String order = PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME + " COLLATE LOCALIZED ASC";
        return new CursorLoader(getActivity(), baseUri, PERSON_PROJECTION, select, null, order);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.i(TAG, "onLoadFinished");
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
        mAdapter.swapCursor(data);
        mAdapter.notifyDataSetChanged();
        if (data == null) {
            mCount = 0;
            Log.i(TAG, "onLoadFinished ------ data is null");
        } else {
            mCount = data.getCount();
        }
        Log.i(TAG, "onLoadFinished ------ mCount = " + mCount);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.i(TAG, "onLoaderReset");
        mAdapter.swapCursor(null);
    }

    @Override
    public boolean onClose() {
        if (!TextUtils.isEmpty(mSearchView.getQuery())) {
            mSearchView.setQuery(null, true);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i(TAG, "onQueryTextChange ------ newText = " + newText);
        String newFilter = !TextUtils.isEmpty(newText) ? newText : null;
        if (mCurFilter == null && newFilter == null) {
            return true;
        }
        if (mCurFilter != null && mCurFilter.equals(newFilter)) {
            return true;
        }
        mCurFilter = newFilter;
        getLoaderManager().restartLoader(0, null, this);
        return false;
    }

    public void add(ContentResolver resolver) {
        if (mCount >= 0) {
            String name = Integer.toString(mCount);
            Log.i(TAG, "add ------ name : " + name);
            ContentValues contentValues = new ContentValues();
            contentValues.put(PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME, name);
            contentValues.put(PersonDataBaseUtils.TablePerson.PERSON_COLUMN_AGE, mCount);
            if (checkDataIfExist(resolver, name)) {
                resolver.update(PersonDataBaseUtils.TablePerson.CONTENT_URI,
                        contentValues, PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME + "=?",
                        new String[]{name});
            } else {
                resolver.insert(PersonDataBaseUtils.TablePerson.CONTENT_URI, contentValues);
            }
        }
    }

    public void remove(ContentResolver resolver) {
        if (mCount >= 0) {
            String name = Integer.toString(mCount - 1);
            if (checkDataIfExist(resolver, name)) {
                resolver.delete(PersonDataBaseUtils.TablePerson.CONTENT_URI,
                        PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME + "=?", new String[]{name});
            }
        }
    }

    private boolean checkDataIfExist(ContentResolver resolver, String... params) {
        int count = 0;
        Cursor cursor = null;
        try {
            cursor = resolver.query(PersonDataBaseUtils.TablePerson.CONTENT_URI,
                    new String[]{"COUNT(*)"}, PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME + "=?",
                    params, null);
            if (cursor == null) {
                return false;
            }
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            cursor.close();
        } catch (Exception e) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return count != 0;
    }

    public static class MySearchView extends SearchView {

        public MySearchView(Context context) {
            super(context);
        }

        @Override
        public void onActionViewCollapsed() {
            setQuery("", false);
            super.onActionViewCollapsed();
        }
    }
}

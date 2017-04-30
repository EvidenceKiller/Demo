package com.example.loader;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by zxn on 17-4-23.
 */

public class CursorLoaderListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String[] PERSON_PROJECTION = new String[]{PersonDataBaseUtils.TablePerson.PERSON_COLUMN_ID,
            PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME,
            PersonDataBaseUtils.TablePerson.PERSON_COLUMN_AGE};

    SimpleCursorAdapter mAdapter;

    SearchView mSearchView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText("no people");

        mAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_2, null,
                new String[]{PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME,
                        PersonDataBaseUtils.TablePerson.PERSON_COLUMN_AGE},
                new int[]{android.R.id.text1, android.R.id.text2}, 0);
        setListAdapter(mAdapter);
        setListShown(false);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(), "Item clicked : " + id, Toast.LENGTH_LONG).show();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri baseUri;
        baseUri = PersonDataBaseUtils.TablePerson.CONTENT_URI;
        String select = "((" + PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME + " NOT NULL) AND ("
                + PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME + " != ''))";
        String order = PersonDataBaseUtils.TablePerson.PERSON_COLUMN_NAME + " COLLATE LOCALIZED ASC";
        return new CursorLoader(getActivity(), baseUri, PERSON_PROJECTION, select, null, order);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}

package com.as_group.taxi_info.ui.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.as_group.taxi_info.adapters.CityCursorAdapter;
import com.as_group.taxi_info.db.SQLiteContract;

/**
 * Created with IntelliJ IDEA.
 * User: andriistakhov
 * Date: 13.07.13
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class NavigationListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private CityCursorAdapter mAdapter;
    private String[] projection;

    @Override
    public void onResume() {
        super.onResume();
        mAdapter = new CityCursorAdapter(getActivity(), null, true);
        setListAdapter(mAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        projection = new String[]{SQLiteContract.City._ID, SQLiteContract.City.COLUMN_CITY_ID,
                SQLiteContract.City.COLUMN_CITY_NAME};
        CursorLoader cursorLoader = new CursorLoader(getActivity(), SQLiteContract.City.CONTENT_URI, projection,
                null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mAdapter.swapCursor(null);
    }
}

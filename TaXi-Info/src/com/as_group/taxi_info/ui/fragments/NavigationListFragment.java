package com.as_group.taxi_info.ui.fragments;

import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.as_group.taxi_info.adapters.CityCursorAdapter;
import com.as_group.taxi_info.db.SQLiteContract;
import com.as_group.taxi_info.service.TaxiInfoService;
import com.as_group.taxi_info.utils.Constants;

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
    private ContentObserver myResolver;

    @Override
    public void onResume() {
        super.onResume();

        if (myResolver == null) {
            myResolver = new ContentObserver(new Handler()) {
                @Override
                public void onChange(boolean selfChange) {
                    getLoaderManager().restartLoader(0, null, NavigationListFragment.this);
                }
            };
        }
        getActivity().getContentResolver().registerContentObserver(SQLiteContract.TaxiService.CONTENT_URI, true, myResolver);

        getActivity().startService(prepareIntent());

        mAdapter = new CityCursorAdapter(getActivity(), null, true);
        setListAdapter(mAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getContentResolver().unregisterContentObserver(myResolver);
        myResolver = null;
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

    private Intent prepareIntent() {
        Intent intent = new Intent(getActivity(), TaxiInfoService.class);
        intent.putExtra(Constants.URL_NAME, Constants.GET_CITY_URL);
        return intent;
    }
}

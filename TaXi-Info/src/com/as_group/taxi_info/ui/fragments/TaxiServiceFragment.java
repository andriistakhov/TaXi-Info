package com.as_group.taxi_info.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.as_group.taxi_info.R;
import com.as_group.taxi_info.adapters.TaxiCursorAdapter;
import com.as_group.taxi_info.db.SQLiteContract;

/**
 * Created with IntelliJ IDEA.
 * User: andriistakhov
 * Date: 13.07.13
 * Time: 20:52
 * To change this template use File | Settings | File Templates.
 */
public class TaxiServiceFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView mListView;
    private TaxiCursorAdapter mAdapter;
    private String mSelection = SQLiteContract.TaxiService.COLUMN_CITY_ID + " = ?";
    private int mCityId;
    private ContentObserver myResolver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.listView);
        fillData();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getContentResolver().unregisterContentObserver(myResolver);
        myResolver = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TAXI_INFO", Context.MODE_PRIVATE);
        mCityId = sharedPreferences.getInt("city_id", 0);
        String mCityName = sharedPreferences.getString("city_name", "");

        if (myResolver == null) {
            myResolver = new ContentObserver(new Handler()) {
                @Override
                public void onChange(boolean selfChange) {
                    updateData(); // With appropriate loader args here
                }
            };
        }
        getActivity().getContentResolver().registerContentObserver(SQLiteContract.TaxiService.CONTENT_URI, true, myResolver);
    }

    private void updateData() {
        getLoaderManager().restartLoader(0, null, this);
    }

    private void fillData() {
        getLoaderManager().initLoader(0, null, this);
        mAdapter = new TaxiCursorAdapter(getActivity(), null, true);

        mListView.setAdapter(mAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {SQLiteContract.TaxiService._ID, SQLiteContract.TaxiService.COLUMN_TAXI_SERVICE_NAME,
                SQLiteContract.TaxiService.COLUMN_TAXI_SERVICE_ID};
        CursorLoader cursorLoader = new CursorLoader(getActivity(), SQLiteContract.TaxiService.CONTENT_URI, projection,
                mSelection, new String[]{String.valueOf(mCityId)}, null);
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

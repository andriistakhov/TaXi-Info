package com.as_group.taxi_info.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.as_group.taxi_info.db.SQLiteContract;

/**
 * Created with IntelliJ IDEA.
 * User: andriistakhov
 * Date: 13.07.13
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class CityCursorAdapter extends CursorAdapter {

    private final LayoutInflater inflator;

    public CityCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        inflator = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = inflator.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        return view;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        if (view != null && cursor != null) {
            TextView trackName = (TextView) view.findViewById(android.R.id.text1);
            trackName.setText(cursor.getString(cursor.getColumnIndex(SQLiteContract.City.COLUMN_CITY_NAME)));
        }
    }
}

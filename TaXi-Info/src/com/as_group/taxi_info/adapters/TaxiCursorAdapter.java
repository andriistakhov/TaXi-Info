package com.as_group.taxi_info.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.as_group.taxi_info.R;
import com.as_group.taxi_info.db.SQLiteContract;

/**
 * Created with IntelliJ IDEA.
 * User: andriistakhov
 * Date: 13.07.13
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class TaxiCursorAdapter extends CursorAdapter {

    private final LayoutInflater inflator;

    public TaxiCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        inflator = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = inflator.inflate(R.layout.taxi_service_item, viewGroup, false);
        return view;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        if (view != null && cursor != null) {
            TextView trackName = (TextView) view.findViewById(R.id.taxi_service_name);
            TextView trackArtistName = (TextView) view.findViewById(R.id.taxi_service_base_number);

            trackName.setText(cursor.getString(cursor.getColumnIndex(SQLiteContract.TaxiService.COLUMN_TAXI_SERVICE_NAME)));
            trackArtistName.setText(cursor.getString(cursor
                    .getColumnIndex(SQLiteContract.TaxiInfo.COLUMN_TAXI_INFO_VALUE)));
        }
    }
}

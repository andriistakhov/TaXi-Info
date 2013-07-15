package com.as_group.taxi_info;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;
import android.util.SparseArray;

import com.as_group.taxi_info.db.SQLiteContract;

/**
 * Created with IntelliJ IDEA.
 * User: andriistakhov
 * Date: 13.07.13
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public class TaxiInfoService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * <p/>
     * Used to name the worker thread, important only for debugging.
     */
    public TaxiInfoService() {
        super("TaxiInfoService");
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        getContentResolver().delete(SQLiteContract.City.CONTENT_URI, null, null);
        // TODO: Remove after test
        SparseArray<String> map = new SparseArray<String>();
        map.put(0, "Vinnitsa");
        map.put(1, "Kyiv");
        map.put(2, "Odessa");
        map.put(3, "Lviv");
        populateCity(map);
    }

    private void populateCity(SparseArray<String> map) {
        for (int i = 0; i < map.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(SQLiteContract.City.COLUMN_CITY_ID, map.keyAt(i));
            values.put(SQLiteContract.City.COLUMN_CITY_NAME, map.valueAt(i));
            ContentResolver contentResolver = getContentResolver();
            contentResolver.insert(SQLiteContract.City.CONTENT_URI, values);
        }
    }
}

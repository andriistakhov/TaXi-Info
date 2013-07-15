package com.as_group.taxi_info.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created with IntelliJ IDEA. User: andriistakhov Date: 18.05.13 Time: 06:30 To change this template use File |
 * Settings | File Templates.
 */
public class SQLiteDataProvider extends ContentProvider {
    // public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    // Used for the UriMacher
    private static final int CITY = 10;
    private static final int CITY_ID = 20;
    private static final int SERVICE = 30;
    private static final int SERVICE_ID = 40;
    private static final int INFO = 50;
    private static final int INFO_ID = 60;
    private static UriMatcher sUriMatcher;
    // database
    private SQLiteHelper mOpenHelper;

    public static synchronized UriMatcher getUriMatcher() {
        if (sUriMatcher == null) {
            sUriMatcher = buildUriMatcher();
        }
        return sUriMatcher;
    }

    private static UriMatcher buildUriMatcher() {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(SQLiteContract.CONTENT_AUTHORITY, SQLiteHelper.TB_CITY, CITY);
        sUriMatcher.addURI(SQLiteContract.CONTENT_AUTHORITY, SQLiteHelper.TB_CITY + "/*", CITY_ID);
        sUriMatcher.addURI(SQLiteContract.CONTENT_AUTHORITY, SQLiteHelper.TB_TAXI_SERVICE, SERVICE);
        sUriMatcher.addURI(SQLiteContract.CONTENT_AUTHORITY, SQLiteHelper.TB_TAXI_SERVICE + "/*", SERVICE_ID);
        sUriMatcher.addURI(SQLiteContract.CONTENT_AUTHORITY, SQLiteHelper.TB_TAXI_INFO, INFO);
        sUriMatcher.addURI(SQLiteContract.CONTENT_AUTHORITY, SQLiteHelper.TB_TAXI_INFO + "/*", INFO_ID);
        return sUriMatcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new SQLiteHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        final SQLiteQueryBuilder builder = buildSelection(uri);
        return builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);

    }

    private SQLiteQueryBuilder buildSelection(Uri uri) {
        final SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        final int match = getUriMatcher().match(uri);
        switch (match) {
            case CITY:
                builder.setTables(SQLiteHelper.TB_CITY);
                break;
            case CITY_ID:
                String cityId = SQLiteContract.City.getId(uri);
                builder.setTables(SQLiteHelper.TB_CITY);
                builder.appendWhere(String.format(SQLiteContract.City.COLUMN_CITY_ID + "='%s'", cityId));
                break;
            case SERVICE:
                builder.setTables(SQLiteHelper.TB_TAXI_SERVICE);
                break;
            case SERVICE_ID:
                String serviceId = SQLiteContract.TaxiService.getId(uri);
                builder.setTables(SQLiteHelper.TB_CITY);
                builder.appendWhere(String.format(SQLiteContract.TaxiService.COLUMN_TAXI_SERVICE_ID + "='%s'", serviceId));
                break;
            case INFO:
                builder.setTables(SQLiteHelper.TB_TAXI_INFO);
                break;
            case INFO_ID:
                String infoId = SQLiteContract.TaxiInfo.getId(uri);
                builder.setTables(SQLiteHelper.TB_TAXI_INFO);
                builder.appendWhere(String.format(SQLiteContract.TaxiInfo._ID + "='%s'", infoId));
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return builder;
    }

    @Override
    public String getType(Uri uri) {
        final int match = getUriMatcher().match(uri);
        switch (match) {
            case CITY:
                return SQLiteContract.City.CONTENT_TYPE;
            case CITY_ID:
                return SQLiteContract.City.CONTENT_ITEM_TYPE;
            case SERVICE:
                return SQLiteContract.TaxiService.CONTENT_TYPE;
            case SERVICE_ID:
                return SQLiteContract.TaxiService.CONTENT_ITEM_TYPE;
            case INFO:
                return SQLiteContract.TaxiInfo.CONTENT_TYPE;
            case INFO_ID:
                return SQLiteContract.TaxiInfo.CONTENT_ITEM_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try {
            final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            final int match = getUriMatcher().match(uri);
            if (match == CITY) {
                long cityId = db.insertWithOnConflict(SQLiteHelper.TB_CITY, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                return SQLiteContract.City.buildUri(String.valueOf(cityId));
            } else if (match == SERVICE) {
                long serviceId = db.insertWithOnConflict(SQLiteHelper.TB_TAXI_SERVICE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                return SQLiteContract.TaxiService.buildUri(String.valueOf(serviceId));
            } else if (match == INFO) {
                long serviceId = db.insertWithOnConflict(SQLiteHelper.TB_TAXI_INFO, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                return SQLiteContract.TaxiInfo.buildUri(String.valueOf(serviceId));
            }
        } finally {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        throw new UnsupportedOperationException("Unknown uri: " + uri);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        try {
            final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            final SQLiteQueryBuilder builder = buildSelection(uri);
            return db.update(builder.getTables(), values, selection, selectionArgs);
        } finally {
            getContext().getContentResolver().notifyChange(uri, null);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        try {
            final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

            final SQLiteQueryBuilder builder = buildSelection(uri);
            return db.delete(builder.getTables(), selection, selectionArgs);
        } finally {
            getContext().getContentResolver().notifyChange(uri, null);
        }

    }
}

package com.as_group.taxi_info.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created with IntelliJ IDEA. User: andriistakhov Date: 18.05.13 Time: 07:51 To change this template use File |
 * Settings | File Templates.
 */
public class SQLiteContract {
    public static final String CONTENT_AUTHORITY = "com.as_group.taxi_info.db.contentprovider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    private static final String TB_CITY = SQLiteHelper.TB_CITY;
    private static final String TB_TAXI_SERVICE = SQLiteHelper.TB_TAXI_SERVICE;
    private static final String TB_TAXI_INFO = SQLiteHelper.TB_TAXI_INFO;

    interface CityColumns {
        String COLUMN_CITY_ID = SQLiteHelper.COLUMN_CITY_ID;
        String COLUMN_CITY_NAME = SQLiteHelper.COLUMN_CITY_NAME;
    }

    interface TaxiServiceColumns {
        String COLUMN_TAXI_SERVICE_ID = SQLiteHelper.COLUMN_TAXI_SERVICE_ID;
        String COLUMN_TAXI_SERVICE_NAME = SQLiteHelper.COLUMN_TAXI_SERVICE_NAME;
        String COLUMN_TAXI_SERVICE_RATE = SQLiteHelper.COLUMN_TAXI_SERVICE_RATE;
        String COLUMN_CITY_ID = SQLiteHelper.COLUMN_CITY_ID;
    }

    interface TaxiInfoColumns {
        String COLUMN_TAXI_SERVICE_ID = SQLiteHelper.COLUMN_TAXI_SERVICE_ID;
        String COLUMN_TAXI_INFO_VALUE = SQLiteHelper.COLUMN_TAXI_INFO_VALUE;
        String COLUMN_TAXI_INFO_TYPE = SQLiteHelper.COLUMN_TAXI_INFO_TYPE;
    }

    public static class City implements CityColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TB_CITY).build();
        public static final String CONTENT_TYPE = "vnd.taxi_info.cursor.dir/vnd.taxi_info.city";
        public static final String CONTENT_ITEM_TYPE = "vnd.taxi_info.cursor.item/vnd.taxi_info.city";

        public static Uri buildUri(String projectId) {
            return CONTENT_URI.buildUpon().appendPath(projectId).build();
        }

        public static Uri buildUri(Integer projectId) {
            return buildUri(projectId.toString());
        }

        public static String getId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

    public static class TaxiService implements TaxiServiceColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TB_TAXI_SERVICE).build();
        public static final String CONTENT_TYPE = "vnd.taxi_info.cursor.dir/vnd.taxi_info.taxi_service";
        public static final String CONTENT_ITEM_TYPE = "vnd.taxi_info.cursor.item/vnd.taxi_info.taxi_service";

        public static Uri buildUri(String projectId) {
            return CONTENT_URI.buildUpon().appendPath(projectId).build();
        }

        public static Uri buildUri(Integer projectId) {
            return buildUri(projectId.toString());
        }

        public static String getId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

    public static class TaxiInfo implements TaxiInfoColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TB_TAXI_INFO).build();
        public static final String CONTENT_TYPE = "vnd.taxi_info.cursor.dir/vnd.taxi_info.taxi_info";
        public static final String CONTENT_ITEM_TYPE = "vnd.taxi_info.cursor.item/vnd.taxi_info.taxi_info";

        public static Uri buildUri(String projectId) {
            return CONTENT_URI.buildUpon().appendPath(projectId).build();
        }

        public static Uri buildUri(Integer projectId) {
            return buildUri(projectId.toString());
        }

        public static String getId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
}

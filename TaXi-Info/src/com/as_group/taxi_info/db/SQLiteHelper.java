package com.as_group.taxi_info.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: andriistakhov
 * Date: 08.07.13
 * Time: 07:09
 * To change this template use File | Settings | File Templates.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    // Tables
    public static final String TB_CITY = "tb_city";
    public static final String TB_TAXI_SERVICE = "tb_taxi_service";
    public static final String TB_TAXI_INFO = "tb_taxi_info";
    // Columns
    // Base column
    public static final String COLUMN_ID = "_id";
    // Columns for TB_CITY
    public static final String COLUMN_CITY_NAME = "city_name";
    // Columns for TB_TAXI_SERVICE
    public static final String COLUMN_CITY_ID = "city_id";
    public static final String COLUMN_TAXI_SERVICE_NAME = "taxi_service_name";
    public static final String COLUMN_TAXI_SERVICE_RATE = "taxi_service_rate";
    // Columns for TB_TAXI_INFO
    public static final String COLUMN_TAXI_SERVICE_ID = "taxi_service_id";
    public static final String COLUMN_TAXI_INFO_VALUE = "taxi_info_value";
    public static final String COLUMN_TAXI_INFO_TYPE = "taxi_info_type";
    // Base
    public static final String DATABASE_NAME = "taxiinfo.db";
    private static final int DATABASE_VERSION = 1;
    // Tables query
    private static final String CREATE_TABLE_CITY = "CREATE TABLE if not exists " + TB_CITY + " (" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUMN_CITY_NAME + " TEXT NOT NULL)";
    private static final String CREATE_TABLE_TAXI_SERVICE = "CREATE TABLE if not exists " + TB_TAXI_SERVICE + " (" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUMN_TAXI_SERVICE_NAME + " TEXT NOT NULL, " + COLUMN_CITY_ID + " INTEGER DEFAULT 0, " + COLUMN_TAXI_SERVICE_RATE + " DOUBLE DEFAULT 0.0)";
    private static final String CREATE_TABLE_TAXI_INFO = "CREATE TABLE if not exists " + TB_TAXI_INFO + " (" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUMN_TAXI_INFO_VALUE + " TEXT NOT NULL, " + COLUMN_TAXI_SERVICE_ID + " INTEGER DEFAULT 0, " + COLUMN_TAXI_INFO_TYPE + " INTEGER DEFAULT 0)";
    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CITY);
        db.execSQL(CREATE_TABLE_TAXI_SERVICE);
        db.execSQL(CREATE_TABLE_TAXI_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_IF_EXISTS + TB_CITY);
        db.execSQL(DROP_TABLE_IF_EXISTS + TB_TAXI_SERVICE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TB_TAXI_INFO);
        onCreate(db);
    }
}

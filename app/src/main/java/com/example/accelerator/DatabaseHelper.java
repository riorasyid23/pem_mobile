package com.example.accelerometer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "acm.db";

    static final String TABLE_NAME = "acm";
    static final String ACM_ID = "_ID";
    static final String SENSOR_X = "sensor_x";
    static final String SENSOR_Y = "sensor_y";
    static final String SENSOR_Z = "sensor_z";
    static final String ACM_TIME = "acm_time";

    static String SQL_CREATE_ENTRIES = "create table if not exists " + TABLE_NAME + " (" + ACM_ID +
            " integer primary key autoincrement," + SENSOR_X + " varchar(20) not null, " + SENSOR_Y +
            " varchar(20) not null," + SENSOR_Z + " varchar(20) not null," + ACM_TIME + " datetime default current_timestamp not null)";

    static String SQL_DELETE_ENTRIES = "drop table if exists " + TABLE_NAME + ";";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}

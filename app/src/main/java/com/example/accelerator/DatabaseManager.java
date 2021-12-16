package com.example.accelerometer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx) {
        context = ctx;
    }

    public DatabaseManager open() throws SQLDataException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String x, String y, String z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.SENSOR_X, x);
        contentValues.put(dbHelper.SENSOR_Y, y);
        contentValues.put(dbHelper.SENSOR_Z, z);

        database.insert(dbHelper.TABLE_NAME, null, contentValues);
//       database.execSQL("insert into " + DatabaseHelper.TABLE_NAME + " (sensor_x, sensor_y, sensor_z) values ('" + x + "','" + y + "','" + z + "');");
    }
}

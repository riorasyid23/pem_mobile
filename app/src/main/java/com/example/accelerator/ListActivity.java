package com.example.accelerometer;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ListActivity extends AppCompatActivity {
    protected TextView xL;
    protected Calendar calendar;
    protected SQLiteDatabase SQLiteDBase;
    protected DatabaseHelper DBHelper;
//    protected SQLiteList


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        xL = findViewById(R.id.xV);

        ListView lLine = (ListView)findViewById(R.id.logLine);
//
//        SimpleDateFormat cTime = new SimpleDateFormat("dd-MM-yyyy");
//        String TimeStr = cTime.format(calendar.getTime()).toString();
//


        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("x=" + MainActivity.xV + ", y=" + MainActivity.yV + ", z=" + MainActivity.zV);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lLine.setAdapter(arrayAdapter);

    }
}
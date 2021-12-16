package com.bit.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    protected SensorManager SM;
    protected Sensor acmSensor;
    protected TextView xV;
    protected TextView yV;
    protected TextView zV;
    protected TextView sV;
    protected Button startButton;
    protected Button logButton;
    protected float gX, gY, gZ;
    protected DatabaseManager dbManager;
    protected DatabaseHelper dbHelper;

//    @Override
//    protected void onPause() {
//        super.onPause();
//        SM.unregisterListener(acmListener);
//        sV.setText("Suspended!");
//
//        startButton = (Button)findViewById(R.id.btnStart);
//        startButton.setText("Resume");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xV = findViewById(R.id.xV);
        yV = findViewById(R.id.yV);
        zV = findViewById(R.id.zV);
        sV = findViewById(R.id.status);

//        dbHelper = new DatabaseHelper(this);
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }


        logButton = (Button)findViewById(R.id.dataLog);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLogNext();
            }
        });

        SM = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        acmSensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (acmSensor != null) {
            xV.setText("0.0");
            yV.setText("0.0");
            zV.setText("0.0");
            sV.setText("Sensor ready!");

            findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int delay = SensorManager.SENSOR_DELAY_NORMAL;
                    SM.registerListener(acmListener, acmSensor, delay);

                    sV.setText("Running");
                }
            });

            findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sV.setText("Stopped!");
                    SM.unregisterListener(acmListener);
                }
            });
        } else {
            xV.setText("NA");
            yV.setText("NA");
            zV.setText("NA");
            sV.setText("Sensor not found!");
        }

    }

    private SensorEventListener acmListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            gX = event.values[0];
            gY = event.values[1];
            gZ = event.values[2];

            String sX = String.valueOf(event.values[0]);
            String sY = String.valueOf(event.values[1]);
            String sZ = String.valueOf(event.values[2]);

            xV.setText(sX);
            yV.setText(sY);
            zV.setText(sZ);
            dbManager.insert(sX, sY, sZ);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    protected void DataLogNext() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
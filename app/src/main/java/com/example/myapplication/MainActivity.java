package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.DbManager.createDataBase;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        createDataBase mCreateDataBase = new createDataBase(MainActivity.this);
//        db = mCreateDataBase.getWritableDatabase();
//        db.close();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this , Home.class);
                startActivity(intent);
            }
        }, 20000);
    }
}
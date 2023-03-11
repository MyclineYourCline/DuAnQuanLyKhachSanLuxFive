package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

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
    }
}
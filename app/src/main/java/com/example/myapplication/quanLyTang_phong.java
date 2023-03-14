package com.example.myapplication;

import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.ObjectManager.tang;

public class quanLyTang_phong extends AppCompatActivity {
    private Intent mIntent;
    private  Bundle mBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quan_ly_phong_tang);
        mIntent = getIntent();
        mBundle = mIntent.getBundleExtra("bundle_senTang");
        tang items_nhan = (tang) mBundle.getSerializable("items");
        getSupportActionBar().setTitle("Quản lý phòng "+items_nhan.getTenTang());
    }
}
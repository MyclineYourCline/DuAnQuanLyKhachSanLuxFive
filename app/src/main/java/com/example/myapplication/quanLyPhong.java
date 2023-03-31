package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.AdapterManager.phongAdapter;
import com.example.myapplication.DbManager.loaiPhongDao;
import com.example.myapplication.DbManager.phongDao;
import com.example.myapplication.InterfaceManager.sendPhong;
import com.example.myapplication.ObjectManager.chiTietDichVuOBJ;
import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.dichVuObj;
import com.example.myapplication.ObjectManager.hoaDonObj;
import com.example.myapplication.ObjectManager.khachHangObj;
import com.example.myapplication.ObjectManager.loaiPhongObj;
import com.example.myapplication.ObjectManager.nhanVienObj;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.ObjectManager.tangObj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class quanLyPhong extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private phongAdapter mAdapter;
    private Intent mIntent;
    private Bundle mBundle;
    private phongDao mPhongDao;

    private loaiPhongDao mLoaiPhong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_phong);
        getSupportActionBar().setTitle("Quản lý phòng");

        mRecyclerView = findViewById(R.id.activity_quan_ly_phong_recycleView);
        mPhongDao = new phongDao(quanLyPhong.this);

        mAdapter = new phongAdapter(quanLyPhong.this, new sendPhong() {

            @Override
            public void sendPhong(phongObj items) {
                mIntent = new Intent(quanLyPhong.this, quanLyTang_phong.class);
                mBundle = new Bundle();
                mBundle.putSerializable("items", (Serializable) items);
                mIntent.putExtra("bundle_senTang", mBundle);
                startActivity(mIntent);
            }
        });
        mAdapter.setmList(getListPhong());
        mRecyclerView.setAdapter(mAdapter);

    }

    private List<phongObj> getListPhong() {
        List<phongObj> list = mPhongDao.getAll();
        return list;
    }


}


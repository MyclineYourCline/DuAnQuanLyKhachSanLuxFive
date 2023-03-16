package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.AdapterManager.tangAdapter;
import com.example.myapplication.InterfaceManager.sendData;
import com.example.myapplication.ObjectManager.chiTietDichVuOBJ;
import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.dichVuObj;
import com.example.myapplication.ObjectManager.hoaDonObj;
import com.example.myapplication.ObjectManager.khachHangObj;
import com.example.myapplication.ObjectManager.loaiPhongObj;
import com.example.myapplication.ObjectManager.nhanVienObj;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.ObjectManager.tangObj;

import java.util.ArrayList;
import java.util.List;

public class quanLyTang extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private tangAdapter mAdapter;
    private Intent mIntent;
    private Bundle mBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tang);
        getSupportActionBar().setTitle("Quản lý tầng");
        mRecyclerView = findViewById(R.id.activity_quan_ly_tang_recycleView);
        mAdapter = new tangAdapter(quanLyTang.this, new sendData() {
            @Override
            public void sendChiTietDichVu(chiTietDichVuOBJ items) {

            }

            @Override
            public void sendDatPhong(datPhongObj items) {

            }

            @Override
            public void sendDichVu(dichVuObj items) {

            }

            @Override
            public void sendHoaDon(hoaDonObj items) {

            }

            @Override
            public void sendKhachHang(khachHangObj items) {

            }

            @Override
            public void sendLoaiPhong(loaiPhongObj items) {

            }

            @Override
            public void sendNhanVien(nhanVienObj items) {

            }

            @Override
            public void sendPhong(phongObj items) {

            }

            @Override
            public void sendTang(tangObj items) {
                mIntent = new Intent(quanLyTang.this, quanLyTang_phong.class);
                mBundle = new Bundle();
                mBundle.putSerializable("items", items);
                mIntent.putExtra("bundle_senTang",mBundle);
                startActivity(mIntent);

            }
        });
        mAdapter.setmList(getListTang());
        mRecyclerView.setAdapter(mAdapter);

    }

    private List<tangObj> getListTang() {
        List<tangObj> list = new ArrayList<>();
        list.add(new tangObj("01","Tầng 1", "20"));
        list.add(new tangObj("02","Tầng 2", "20"));
        list.add(new tangObj("03","Tầng 3", "20"));
        list.add(new tangObj("04","Tầng 4", "20"));


        return list;
    }
}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.AdapterManager.phongAdapter;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class quanLyPhong extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private phongAdapter mAdapter;
    private Intent mIntent;
    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_phong);
        getSupportActionBar().setTitle("Quản lý phòng");

        mRecyclerView = findViewById(R.id.activity_quan_ly_phong_recycleView);

        mAdapter = new phongAdapter(quanLyPhong.this, new sendData() {
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
                mIntent = new Intent(quanLyPhong.this, quanLyTang_phong.class);
                mBundle = new Bundle();
                mBundle.putSerializable("items", (Serializable) items);
                mIntent.putExtra("bundle_senTang", mBundle);
                startActivity(mIntent);
            }

            @Override
            public void sendTang(tangObj items) {

            }
        });
        mAdapter.setmList(getListPhong());
        mRecyclerView.setAdapter(mAdapter);

    }

    private List<phongObj> getListPhong() {
        List<phongObj> list = new ArrayList<>();
        list.add(new phongObj("1", "1", "1", "Phòng đơn", "Trống", "101"));
        list.add(new phongObj("2", "1", "2", "Phòng đôi", "Trống", "102"));
        list.add(new phongObj("3", "2", "2", "Phòng đôi", "Trống", "201"));
        list.add(new phongObj("4", "2", "1", "Phòng đơn", "Trống", "202"));
        list.add(new phongObj("5", "3", "2", "Phòng đôi vip", "Trống", "301"));


        return list;
    }
}


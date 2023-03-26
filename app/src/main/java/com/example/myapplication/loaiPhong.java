package com.example.myapplication;

import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.AdapterManager.loaiPhongAdapter;
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

public class loaiPhong extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private loaiPhongAdapter mAdapter;
    private Intent mIntent;
    private Bundle mBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_phong);
        getSupportActionBar().setTitle("Quản lý Loại phòng");

        mRecyclerView = findViewById(R.id.activity_quan_ly_loai_phong_recycleView);

        mAdapter = new loaiPhongAdapter(loaiPhong.this, new sendData() {
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
                mIntent = new Intent(loaiPhong.this, quanLyTang_phong.class);
                mBundle = new Bundle();
                mBundle.putSerializable("items", (Serializable) items);
                mIntent.putExtra("bundle_senTang", mBundle);
                startActivity(mIntent);
            }

            @Override
            public void sendTang(tangObj items) {

            }
        });
        mAdapter.setmList(getListLoaiPhong());
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<loaiPhongObj> getListLoaiPhong() {
        List<loaiPhongObj> list = new ArrayList<>();
        list.add(new loaiPhongObj("1", "phòng đơn", "300000"));
        list.add(new loaiPhongObj("2", "phòng đôi", "400000"));
        list.add(new loaiPhongObj("3", "phòng vip", "500000"));
        list.add(new loaiPhongObj("4", "phòng vip 2", "600000"));
        return list;
    }
}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.AdapterManager.tangAdapter;
import com.example.myapplication.InterfaceManager.sendData;
import com.example.myapplication.ObjectManager.chiTietDichVu;
import com.example.myapplication.ObjectManager.datPhong;
import com.example.myapplication.ObjectManager.dichVu;
import com.example.myapplication.ObjectManager.hoaDon;
import com.example.myapplication.ObjectManager.khachHang;
import com.example.myapplication.ObjectManager.loaiPhong;
import com.example.myapplication.ObjectManager.nhanVien;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.ObjectManager.tang;

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
            public void sendChiTietDichVu(chiTietDichVu items) {

            }

            @Override
            public void sendDatPhong(datPhong items) {

            }

            @Override
            public void sendDichVu(dichVu items) {

            }

            @Override
            public void sendHoaDon(hoaDon items) {

            }

            @Override
            public void sendKhachHang(khachHang items) {

            }

            @Override
            public void sendLoaiPhong(loaiPhong items) {

            }

            @Override
            public void sendNhanVien(nhanVien items) {

            }

            @Override
            public void sendPhong(phongObj items) {

            }

            @Override
            public void sendTang(tang items) {
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

    private List<tang> getListTang() {
        List<tang> list = new ArrayList<>();
        list.add(new tang("01","Tầng 1", "20"));
        list.add(new tang("02","Tầng 2", "20"));
        list.add(new tang("03","Tầng 3", "20"));
        list.add(new tang("04","Tầng 4", "20"));


        return list;
    }
}
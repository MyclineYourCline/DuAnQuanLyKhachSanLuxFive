package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.AdapterManager.hoaDonAdapter;
import com.example.myapplication.DbManager.hoaDonDao;
import com.example.myapplication.InterfaceManager.sendHoaDon;
import com.example.myapplication.ObjectManager.hoaDonObj;

import java.util.ArrayList;
import java.util.List;

public class hoaDon extends AppCompatActivity {
       private RecyclerView recycleView_hoadon_activity;
        private hoaDonAdapter hoadon_adapter;
        private hoaDonDao mHoaDonDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        getSupportActionBar().setTitle("Quản lý Hóa đơn");
        mHoaDonDao = new hoaDonDao(hoaDon.this);
        recycleView_hoadon_activity = findViewById(R.id.recycleView_hoadon_activity);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView_hoadon_activity.setLayoutManager(layoutManager);
        hoadon_adapter = new hoaDonAdapter(hoaDon.this, new sendHoaDon() {
            @Override
            public void sendHoaDon(hoaDonObj items) {

            }
        });
        capNhapDuLieu();
        //
    }
    private List<hoaDonObj> getListData(){
        return mHoaDonDao.getAll();
    }
    private void capNhapDuLieu(){
        hoadon_adapter.setmList(getListData());
        recycleView_hoadon_activity.setAdapter(hoadon_adapter);
    }
}
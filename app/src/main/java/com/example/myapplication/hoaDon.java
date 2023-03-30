package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.AdapterManager.hoaDonAdapter;
import com.example.myapplication.ObjectManager.hoaDonObj;

import java.util.ArrayList;

public class hoaDon extends AppCompatActivity {
        RecyclerView recycleView_hoadon_activity;
        hoaDonAdapter hoadon_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        getSupportActionBar().setTitle("Quản lý Hóa đơn");
        recycleView_hoadon_activity = findViewById(R.id.recycleView_hoadon_activity);
        ArrayList<hoaDonObj> list = data_test();
        hoadon_adapter = new hoaDonAdapter(this , list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView_hoadon_activity.setLayoutManager(layoutManager);
        recycleView_hoadon_activity.setAdapter(hoadon_adapter);


    }
    private ArrayList<hoaDonObj> data_test(){
        ArrayList<hoaDonObj> arrayList = new ArrayList<>();
        arrayList.add(new hoaDonObj("sdsds" , "p3j3j" , "chưa thanh toán" ,"200000" , "20/12/2023" , "Fb88"));
        arrayList.add(new hoaDonObj("ndsjfd" , "p3j3j" , "chưa thanh toán" ,"800000" , "22/12/2023" , "Fb88"));
        arrayList.add(new hoaDonObj("dsbdbsa" , "p3j3j" , "chưa thanh toán" ,"500000" , "25/12/2023" , "Fb88"));
        arrayList.add(new hoaDonObj("dsnds" , "p3j3j" , "chưa thanh toán" ,"600000" , "10/12/2023" , "Fb88"));
        arrayList.add(new hoaDonObj("dsandsan" , "p3j3j" , "chưa thanh toán" ,"300000" , "30/12/2023" , "Fb88"));
        return arrayList;
    }
}
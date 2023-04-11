package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.myapplication.AdapterManager.datPhongAdapter;
import com.example.myapplication.DbManager.datPhongDao;
import com.example.myapplication.DbManager.phongDao;
import com.example.myapplication.InterfaceManager.sendDatPhong;
import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.phongObj;

import java.util.List;

public class quanLyPhieuDat extends AppCompatActivity {
    private datPhongAdapter adapter;
    private RecyclerView mRecyclerView;
    private datPhongDao mDatPhongDao;
    private phongDao mPhongDao;
    private phongObj mPhongObj;
     @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_phieu_dat);
        getSupportActionBar().setTitle("Quản lý phiếu đặt");
        mRecyclerView = findViewById(R.id.quanLyPhieuDat_rcv);
         mPhongDao = new phongDao(quanLyPhieuDat.this);
        mDatPhongDao = new datPhongDao(quanLyPhieuDat.this);
        adapter = new datPhongAdapter(quanLyPhieuDat.this, new sendDatPhong() {
            @Override
            public void sendDatPhong(datPhongObj items) {
                clickLenDon(items);
            }
        });
        capNhapDuLieu();

    }

    private void clickLenDon(datPhongObj items) {
        mPhongObj = mPhongDao.getByMaPhong(items.getMaPhong());
        if (mPhongObj.getTrangThai().toLowerCase().equals("đang dùng")){
            AlertDialog.Builder builder = new AlertDialog.Builder(quanLyPhieuDat.this);
            builder.setTitle("Phòng hiện tại đang được sử dụng");
            builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(quanLyPhieuDat.this);
            builder.setTitle("Bạn có muốn lên đơn không ? ");
            builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton("Lên đơn ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    items.setTrangThai("1");
                    mDatPhongDao.updateDatPhong(items);
                    mPhongObj.setTrangThai("Đang dùng");
                    mPhongDao.updatePhong(mPhongObj);
                    capNhapDuLieu();
                }
            });
            builder.show();

        }

    }

    private void capNhapDuLieu(){
        adapter.setmList(layPhieuDat());
        mRecyclerView.setAdapter(adapter);
    }
    private List<datPhongObj> layPhieuDat(){
        List<datPhongObj> list = mDatPhongDao.layPhieuDat("3");
        return list;
    }
}
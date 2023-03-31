package com.example.myapplication;

import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.AdapterManager.phongAdapter;
import com.example.myapplication.AdapterManager.spinerTenLoaiAdapter;
import com.example.myapplication.AdapterManager.tangAdapter;
import com.example.myapplication.DbManager.loaiPhongDao;
import com.example.myapplication.DbManager.phongDao;
import com.example.myapplication.DbManager.tangDao;
import com.example.myapplication.InterfaceManager.sendPhong;
import com.example.myapplication.InterfaceManager.sendTang;
import com.example.myapplication.ObjectManager.loaiPhongObj;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.ObjectManager.tangObj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class quanLyTang_phong extends AppCompatActivity {
    private Intent mIntent;
    private  Bundle mBundle;
    private RecyclerView mRecyclerView;
    private FloatingActionButton btn_add;
    private phongObj mPhongObj;
    private phongAdapter adapter;
    private phongDao mPhongDao;
    private loaiPhongDao mLoaiPhongDao;
    private String maTang;
    private spinerTenLoaiAdapter spinerLoaiPhongAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quan_ly_phong_tang);
        mIntent = getIntent();
        mBundle = mIntent.getBundleExtra("bundle_senTang");
        tangObj items_nhan = (tangObj) mBundle.getSerializable("items");
        maTang = items_nhan.getMaTang();
        getSupportActionBar().setTitle("Quản lý phòng "+items_nhan.getTenTang());
        //


        //
        mRecyclerView = findViewById(R.id.quan_ly_phong_tang_recyclereView);
        btn_add = findViewById(R.id.quan_ly_phong_tang_add);
        mLoaiPhongDao = new loaiPhongDao(quanLyTang_phong.this);
        mPhongDao = new phongDao(quanLyTang_phong.this);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            themPhong(items_nhan.getMaTang());
            }
        });
        //
        adapter = new phongAdapter(quanLyTang_phong.this, new sendPhong() {
            @Override
            public void sendPhong(phongObj items) {

            }
        });
        capNhapDuLieu();
    }
    private void capNhapDuLieu(){
        adapter.setmList(getListPhong());
        mRecyclerView.setAdapter(adapter);
    }

    private List<phongObj> getListPhong() {
        List<phongObj> list = mPhongDao.getByMaTang(maTang);
        return list;
    }
    private void themPhong(String maTang){
        Dialog dialogThemPhong = new Dialog(quanLyTang_phong.this,
                com.google.android.material.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialogThemPhong.setContentView(R.layout.dialog_add_phong);
        EditText editText_tenPhong = dialogThemPhong.findViewById(R.id.dialog_add_phong_tenPhong);
        Spinner spinner_loaiPhong = dialogThemPhong.findViewById(R.id.dialog_add_phong_spiner_loaiPhong);
        Button button_them = dialogThemPhong.findViewById(R.id.dialog_add_phong_btnAdd);
        //
        spinerLoaiPhongAdapter = new spinerTenLoaiAdapter(quanLyTang_phong.this
        ,R.layout.item_spinner_select,layLoaiPhong());
        spinner_loaiPhong.setAdapter(spinerLoaiPhongAdapter);
        //
        button_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_tenPhong.getText().toString().isEmpty()){
                    editText_tenPhong.setError("Bạn không được để trống tiên phòng");
                }
                if (editText_tenPhong.getText().toString().toLowerCase().equals(maTang.toLowerCase())){
                    editText_tenPhong.setError("Bạn chưa đặt tên phòng");
                    return;
                }
                if (editText_tenPhong.getText().toString().toLowerCase()
                        .substring(0,maTang.length()).equals(maTang.toLowerCase())){
                    loaiPhongObj itemSlect = (loaiPhongObj) spinner_loaiPhong.getSelectedItem();
                    phongObj phongObjInsert = new phongObj();
                    phongObjInsert.setTenPhong(editText_tenPhong.getText().toString().trim());
                    phongObjInsert.setMaLoai(itemSlect.getMaLoai());
                    phongObjInsert.setMaTang(maTang);
                    phongObjInsert.setTrangThai("1");
                    mPhongDao.insertPhong(phongObjInsert);
                    capNhapDuLieu();
                    dialogThemPhong.cancel();
                }
                else{
                    editText_tenPhong.setError("Tên tầng phải bắt đầu bằng mã tầng");
                }

            }
        });
        editText_tenPhong.setText(maTang);
        dialogThemPhong.show();
    }
    private List<loaiPhongObj> layLoaiPhong(){
        List<loaiPhongObj> list = mLoaiPhongDao.getAll();
        return list;
    }
}
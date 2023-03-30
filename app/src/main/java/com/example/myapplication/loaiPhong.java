package com.example.myapplication;

import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.AdapterManager.loaiPhongAdapter;
import com.example.myapplication.AdapterManager.phongAdapter;
import com.example.myapplication.DbManager.loaiPhongDao;
import com.example.myapplication.InterfaceManager.sendLoaiPhong;
import com.example.myapplication.ObjectManager.chiTietDichVuOBJ;
import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.dichVuObj;
import com.example.myapplication.ObjectManager.hoaDonObj;
import com.example.myapplication.ObjectManager.khachHangObj;
import com.example.myapplication.ObjectManager.loaiPhongObj;
import com.example.myapplication.ObjectManager.nhanVienObj;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.ObjectManager.tangObj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class loaiPhong extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private loaiPhongAdapter mAdapter;
    private Intent mIntent;
    private Bundle mBundle;
    private loaiPhongDao mLoaiPhongDao;
    private FloatingActionButton flb_AddLoaiPhong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_phong);
        getSupportActionBar().setTitle("Quản lý Loại phòng");

        mRecyclerView = findViewById(R.id.activity_quan_ly_loai_phong_recycleView);
        flb_AddLoaiPhong = findViewById(R.id.btnAddLoaiPhong);
        mLoaiPhongDao = new loaiPhongDao(loaiPhong.this);

        mAdapter = new loaiPhongAdapter(loaiPhong.this, new sendLoaiPhong() {

            @Override
            public void sendLoaiPhong(loaiPhongObj items) {
                mIntent = new Intent(loaiPhong.this, quanLyTang_phong.class);
                mBundle = new Bundle();
                mBundle.putSerializable("items", (Serializable) items);
                mIntent.putExtra("bundle_senTang", mBundle);
                startActivity(mIntent);
            }
        });
       capNhapRec();

        //Xử lý nút thêm loại phòng
        flb_AddLoaiPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThemLoaiPhongDialog(Gravity.CENTER);
            }
        });
    }
    private void openThemLoaiPhongDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_loai_phong);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity =gravity;
        window.setAttributes(windowAttribute);

        //Xử lý click ra ngoài dialog
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        Button btnHuy =dialog.findViewById(R.id.dialog_add_loaiPhong_tenLoai_btnHuy);
        Button btnThem =dialog.findViewById(R.id.dialog_add_loaiPhong_btnThem);
        EditText maLoai, tenLoai;
        maLoai = dialog.findViewById(R.id.dialog_add_loaiPhong_maLoai);
        tenLoai = dialog.findViewById(R.id.dialog_add_loaiPhong_tenLoai);

        //Xử lý nút trong Dialog
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (tenLoai.getText().toString().isEmpty()
            || maLoai.getText().toString().isEmpty()){
                if (tenLoai.getText().toString().isEmpty()){
                    tenLoai.setError("Không được để trống");
                    return;
                }
                if (maLoai.getText().toString().isEmpty()){
                    maLoai.setError("Không được để trống");
                    return;
                }
            }
            else{
                boolean checkTonTaiML = checkMaLoai(maLoai.getText().toString());
                if (checkTonTaiML){
                    loaiPhongObj itemInsert = new loaiPhongObj();
                    itemInsert.setMaLoai(maLoai.getText().toString().trim());
                    itemInsert.setTenLoaiPhong(tenLoai.getText().toString().trim());
                    mLoaiPhongDao.insertLoaiPhong(itemInsert);
                    capNhapRec();
                    dialog.cancel();
                    Toast.makeText(loaiPhong.this, "Thanh Cong", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(loaiPhong.this, "Mã loại đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });

        dialog.show();
    }
    private void capNhapRec(){
        mAdapter.setmList(getListLoaiPhong());
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<loaiPhongObj> getListLoaiPhong() {
        List<loaiPhongObj> list = mLoaiPhongDao.getAll();
        return list;
    }
    private boolean checkMaLoai(String maLoai){
        boolean check = true;
        List<loaiPhongObj> loaiPhongs = mLoaiPhongDao.getAll();
        if (loaiPhongs == null){
            check = true;
        }
        else{
            for (loaiPhongObj items : loaiPhongs){
                if (items.getMaLoai().toLowerCase().equals(maLoai)){
                    check = false;
                    break;
                }
                else{
                    check = true;
                }
            }

        }
       return check;
    }
}
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

import com.example.myapplication.AdapterManager.loaiPhongAdapter;
import com.example.myapplication.AdapterManager.phongAdapter;
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
    private FloatingActionButton flb_AddLoaiPhong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_phong);
        getSupportActionBar().setTitle("Quản lý Loại phòng");

        mRecyclerView = findViewById(R.id.activity_quan_ly_loai_phong_recycleView);
        flb_AddLoaiPhong = findViewById(R.id.btnAddLoaiPhong);

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
        mAdapter.setmList(getListLoaiPhong());
        mRecyclerView.setAdapter(mAdapter);

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
        Button btnHuy =dialog.findViewById(R.id.btnHuy);
        Button btnThem =dialog.findViewById(R.id.btnThem);

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
            //Todo.....
            }
        });

        dialog.show();
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
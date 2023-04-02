package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.DbManager.nhanVienDao;
import com.example.myapplication.ObjectManager.nhanVienObj;
import com.google.android.material.textfield.TextInputEditText;

public class manHinhDangNhap extends AppCompatActivity {
    private TextInputEditText tenDangNhap, matKhau;
    private Button btn_dangNhap, btnDangKy;
    nhanVienDao nvDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);
        tenDangNhap = findViewById(R.id.dangNhap_txt_tenDangNhap);
        matKhau = findViewById(R.id.dangNhap_txt_matKhau);
        btn_dangNhap = findViewById(R.id.dangNhap_btn_dangNhap);
        btnDangKy = findViewById(R.id.dangNhap_btn_dangKy);


        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//        if(nvDao.CheckByMaNhanVien(tenDangNhap.getText().toString(), matKhau.getText().toString())){
//            nhanVienObj obj = nvDao.getByMaNhanVien(tenDangNhap.getText().toString());

            Intent intent = new Intent(manHinhDangNhap.this,Home.class);
            startActivity(intent);

//            SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("maNv",obj.getMaNhanVien());
//
//            editor.apply();
//                startActivity(intent);
//                finish();
//        }else {
//            Toast.makeText(manHinhDangNhap.this, "sai mã nhân viên và mật khẩu ", Toast.LENGTH_SHORT).show();
//        }
//
//
//
////
          }
        });



    }



}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class manHinhDangNhap extends AppCompatActivity {
    private TextInputEditText tenDangNhap, matKhau;
    private Button btn_dangNhap, btnDangKy;
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
                Intent intent = new Intent(manHinhDangNhap.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
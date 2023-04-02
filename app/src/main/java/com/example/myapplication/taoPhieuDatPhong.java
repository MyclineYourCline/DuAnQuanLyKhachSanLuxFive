package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class taoPhieuDatPhong extends AppCompatActivity {
    private Button btn_Huy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_phieu_dat_phong);
        getSupportActionBar().setTitle("Tạo phiếu đặt phòng");
        btn_Huy = findViewById(R.id.activity_tao_phieu_dat_phong_btnHuy);
        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }
}
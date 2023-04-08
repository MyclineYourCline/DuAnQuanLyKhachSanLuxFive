package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.DbManager.createDataBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class doanhThu extends AppCompatActivity {
    createDataBase db;
    ImageView img_date_tu_ngay,img_date_den_ngay;
    TextView txt_date_tu_ngay,txt_date_den_ngay,txt_tong_tien;
    RecyclerView rcvView;
    Button btn_thong_ke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);
        getSupportActionBar().setTitle("Quản lý Doanh thu");

        db = new createDataBase(doanhThu.this);

        img_date_tu_ngay = findViewById(R.id.img_datePickerDialog_tuNgay);
        img_date_den_ngay = findViewById(R.id.img_datePickerDialog_denNgay);
        txt_date_tu_ngay = findViewById(R.id.tv_tuNgay);
        txt_date_den_ngay = findViewById(R.id.tv_denNgay);
        txt_tong_tien = findViewById(R.id.tv_tongTien);
        rcvView = findViewById(R.id.rcv_thongKe);
        btn_thong_ke = findViewById(R.id.btn_thong_ke);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvView.setLayoutManager(layoutManager);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        img_date_tu_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(doanhThu.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month,dayOfMonth);
                        txt_date_tu_ngay.setText(df.format(gregorianCalendar.getTime()));
                    }
                },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });

        img_date_den_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(doanhThu.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month,dayOfMonth);
                        txt_date_den_ngay.setText(df.format(gregorianCalendar.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });

        btn_thong_ke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double tongtien = ThongKe(txt_date_tu_ngay.getText().toString(), txt_date_den_ngay.getText().toString());
                txt_tong_tien.setText(tongtien+" VND");
            }
        });

    }
    public double ThongKe(String st, String end) {
        String sql = "SELECT SUM(tongTien) as DoanhThu FROM hoaDonThanhToan WHERE ngayThang BETWEEN ? AND ?";
        Cursor cursor = db.getReadableDatabase().rawQuery(sql, new String[]{st, end});
        List<Double> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            list.add(Double.parseDouble(cursor.getString(cursor.getColumnIndex("DoanhThu"))));
        }
        return list.get(0);
    }
}
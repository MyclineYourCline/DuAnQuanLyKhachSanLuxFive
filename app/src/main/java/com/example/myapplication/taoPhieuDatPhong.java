package com.example.myapplication;

import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.DbManager.chiTietDichVuDao;
import com.example.myapplication.DbManager.datPhongDao;
import com.example.myapplication.DbManager.khachHangDao;
import com.example.myapplication.DbManager.phongDao;
import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.khachHangObj;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.ObjectManager.tangObj;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class taoPhieuDatPhong extends AppCompatActivity {
    private Button btn_Huy;
    private String maNV;
    private Intent mIntent;
    private Bundle mBundle;
    private phongObj items_nhan;
    private String maPhong;
    private EditText maKh, maNhanVien, tenPhong, ngayDat, gioDat, thoiGianDat,giaThue;
    private Button taoKH, dichVu, huy, taoPhieu;
    private khachHangDao dao;
    private String id_phieuDatPhong;
    private chiTietDichVuDao mChiTietDichVuDao;
    private datPhongDao mDatPhongDao;
    private phongDao mPhongDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_phieu_dat_phong);
        iNitUI();
        //
        dao = new khachHangDao(taoPhieuDatPhong.this);
        mDatPhongDao = new datPhongDao(taoPhieuDatPhong.this);
        mChiTietDichVuDao = new chiTietDichVuDao(taoPhieuDatPhong.this);
        mPhongDao = new phongDao(taoPhieuDatPhong.this);
        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        maNV = sharedPreferences.getString("maNv","");
        getSupportActionBar().setTitle("Tạo phiếu đặt phòng");
        //
        mIntent = getIntent();
        mBundle = mIntent.getBundleExtra("intentSend");
        items_nhan = (phongObj) mBundle.getSerializable("itemSend");
        maPhong = items_nhan.getMaPhong();
        //
        id_phieuDatPhong = getID();
        maNhanVien.setText(maNV);
        tenPhong.setText(items_nhan.getTenPhong());
        ngayDat.setText(getDate());
        gioDat.setText(getTime());

        //
        btn_Huy = findViewById(R.id.activity_tao_phieu_dat_phong_btnHuy);
        
        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChiTietDichVuDao.deleteChiTietDichVu(id_phieuDatPhong);
               finish();
            }
        });
        taoKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taoKh();
            }
        });
        dichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTochiTietDichVu();
            }
        });
        taoPhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              taoPhieuDat();
            }
        });
        
    }

    private void taoPhieuDat() {
        khachHangDao mKhachHangDao = new khachHangDao(taoPhieuDatPhong.this);
        if (maKh.getText().toString().isEmpty()|| maNhanVien.getText().toString().isEmpty()
             || tenPhong.getText().toString().isEmpty() || ngayDat.getText().toString().isEmpty()||
              gioDat.getText().toString().isEmpty()|| thoiGianDat.getText().toString().isEmpty()){
            if (maKh.getText().toString().isEmpty()){
                maKh.setError("Chưa nhập mã Kh");
                return;
            }
             else if (maNhanVien.getText().toString().isEmpty()){
                maNhanVien.setError("Không được để trống");
                return;
            }
            else if (ngayDat.getText().toString().isEmpty()){
                ngayDat.setError("Không được để trống");
                return;
            }
             else if (gioDat.getText().toString().isEmpty()){
                gioDat.setError("Không được để trống");
                return;
            }
             else if (thoiGianDat.getText().toString().isEmpty()){
                thoiGianDat.setError("Không được để trống");
                return;
            }
             else if (giaThue.getText().toString().isEmpty()){
                giaThue.setError("Không được để trống");
                return;
            }
        }
        else{
            if (maNhanVien.getText().toString().trim() != maNV.trim() ||
                    mKhachHangDao.checkKhachHang(maKh.getText().toString().trim())){
                if (mKhachHangDao.checkKhachHang(maKh.getText().toString().trim())){
                    maKh.setError("Mã khách hàng lỗi");
                    return;
                }
                if (maNhanVien.getText().toString().trim().equals(maNV)){
                    double number = Double.parseDouble(thoiGianDat.getText().toString().trim());
                    int hour = (int) number;
                    String hourString = hour+"";
                    int minute = (int) ((number - hour) * 60);
                    String munuteString = minute+"";
                    if (hour < 10){
                        hourString = "0"+hour;
                    }
                    if (minute < 10){
                        munuteString = "0"+minute;
                    }

                    String timeString = hourString + ":" + munuteString+":00";

                    //
                    try {
                        datPhongObj itemInsert = new datPhongObj();
                        itemInsert.setMaDatPhong(id_phieuDatPhong);
                        itemInsert.setMaPhong(maPhong);
                        itemInsert.setCheckIn(ngayDat.getText().toString().trim());
                        itemInsert.setGioVao(gioDat.getText().toString().trim());
                        itemInsert.setMaKh(maKh.getText().toString().trim());
                        itemInsert.setMaNhanVien(maNV);
                        itemInsert.setSoGioDat(timeString);
                        itemInsert.setGioRa(itemInsert.checkTimeOut());
                        itemInsert.setNgayRa(itemInsert.checkDayOut());
                        itemInsert.setGiaTien(giaThue.getText().toString().trim());
                        itemInsert.setMaChiTietDV(id_phieuDatPhong);
                        mDatPhongDao.inserDatPhong(itemInsert);
                        //
                        phongObj itemPhong = mPhongDao.getByMaPhong(items_nhan.getMaPhong());
                        itemPhong.setTrangThai("đang dùng");
                        mPhongDao.updatePhong(itemPhong);
                        //
                        finish();
                        Toast.makeText(this, "Tạo phiếu đặt phòng thành công", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Toast.makeText(this, "Định dạng giờ hoặc ngày tháng bị sai", Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    maNhanVien.setError("mã nhân viên không đúng");
                    return;
                }

            }

        }
    }
    private String tinhTongGio (String thoiGianDat){
        double  totalHours = Double.parseDouble(thoiGianDat);
        int hours = (int) totalHours;
        int minutes = (int) ((totalHours - hours) * 60);
        String hourStrirg = hours+"";
        String hourminutes = minutes+"";
        if (hours< 10){
            hourStrirg = "0"+hours;
        }
        if (minutes<10){
            hourminutes = "0"+minutes;
        }
        return hourStrirg+":"+hourminutes+":00";
    }

    private void goTochiTietDichVu() {
        Intent intent = new Intent(taoPhieuDatPhong.this, chiTietDichVu.class);
        intent.putExtra("id_phieuDatPhong",id_phieuDatPhong);
        startActivity(intent);
    }

    private void taoKh() {
        Dialog dialog = new Dialog(taoPhieuDatPhong.this,
                androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_add_khach_hang);
        //
        EditText edtCMT = dialog.findViewById(R.id.dialog_add_khach_hang_edittext_cmt);
        EditText edtTen = dialog.findViewById(R.id.dialog_add_khach_hang_textEdit_nameKhach);
        EditText edtNgaySinh = dialog.findViewById(R.id.dialog_add_khach_hang_textEdit_ngaySinh);
        EditText edtSDT = dialog.findViewById(R.id.dialog_add_khach_hang_textEdit_soDt);
        Button btnThem = dialog.findViewById(R.id.dialog_add_khach_hang_btnThemKhachHang);
        ImageView imageView = dialog.findViewById(R.id.dialog_khach_hang_vien_img_back);
        //
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CMT = edtCMT.getText().toString();
                String Ten = edtTen.getText().toString();
                String ngaySinh = edtNgaySinh.getText().toString();
                String SDT = edtSDT.getText().toString();

                if (CMT.isEmpty() || Ten.isEmpty() || ngaySinh.isEmpty() || SDT.isEmpty()){
                    if (CMT.isEmpty()){
                        edtCMT.setError("Moi nhap day du thong tin");
                        return;
                    }
                    if (Ten.isEmpty()){
                        edtCMT.setError("Moi nhap day du thong tin");
                        return;
                    }
                    if (ngaySinh.isEmpty()){
                        edtNgaySinh.setError("Moi nhap day du thong tin");
                        return;
                    }
                    if (SDT.isEmpty()){
                        edtSDT.setError("Moi nhap day du thong tin");
                        return;
                    }
                }else{
                    khachHangObj khachHang = new khachHangObj();
                    khachHang.setSoCMT(CMT.trim());
                    khachHang.setTenKh(Ten.trim());
                    khachHang.setNgaySinh(ngaySinh.trim());
                    khachHang.setSoDienThoai(SDT.trim());
                    if (dao.checkKhachHang(CMT.trim())){
                        dao.insertKhachHangObj(khachHang);
                        Toast.makeText(taoPhieuDatPhong.this,
                                "Cập nhập thành công", Toast.LENGTH_SHORT).show();
                        maKh.setText(khachHang.getSoCMT());
                        dialog.cancel();
                    }
                    else{
                        edtCMT.setError("Khách hàng đã tồn tại");
                    }

                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void iNitUI(){
         maKh = findViewById(R.id.activity_tao_phieu_dat_phong_maKhachHang);
         maNhanVien = findViewById(R.id.activity_tao_phieu_dat_phong_nhanVien);
         tenPhong  = findViewById(R.id.activity_tao_phieu_dat_phong_tenPhong);
         ngayDat = findViewById(R.id.activity_tao_phieu_dat_phong_ngayDat);
         gioDat =  findViewById(R.id.activity_tao_phieu_dat_phong_gioDat);
         thoiGianDat =  findViewById(R.id.activity_tao_phieu_dat_phong_thoiGianDat);
         taoKH = findViewById(R.id.activity_tao_phieu_dat_phong_btnTaoKH);
         dichVu = findViewById(R.id.activity_tao_phieu_dat_phong_xml_btnDichVu);
         huy = findViewById(R.id.activity_tao_phieu_dat_phong_btnHuy);
         taoPhieu = findViewById(R.id.activity_tao_phieu_dat_phong_btnTaoPhieu);
         giaThue = findViewById(R.id.activity_tao_phieu_dat_phong_giaThuePhong);
    }
    private String  getDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        return dateString;
    }
    private String getTime (){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String hourString = hour+"";
        if (hour < 10){
            hourString = "0"+hour;
        }
        if (minute < 10){
            return hourString+":0"+minute+":"+"00";
        }
        else{
            return hourString+":"+minute+":"+"00";
        }
    }
    private String getID(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String dateString = sdf.format(date);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return dateString+hour+minute+second;
    }
}
package com.example.myapplication;

import static android.util.Log.d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.AdapterManager.phongAdapter;
import com.example.myapplication.AdapterManager.spinerTenLoaiAdapter;
import com.example.myapplication.AdapterManager.tangAdapter;
import com.example.myapplication.DbManager.chiTietDichVuDao;
import com.example.myapplication.DbManager.datPhongDao;
import com.example.myapplication.DbManager.hoaDonDao;
import com.example.myapplication.DbManager.khachHangDao;
import com.example.myapplication.DbManager.loaiPhongDao;
import com.example.myapplication.DbManager.phongDao;
import com.example.myapplication.DbManager.tangDao;
import com.example.myapplication.InterfaceManager.sendPhong;
import com.example.myapplication.InterfaceManager.sendTang;
import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.hoaDonObj;
import com.example.myapplication.ObjectManager.khachHangObj;
import com.example.myapplication.ObjectManager.loaiPhongObj;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.ObjectManager.tangObj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class quanLyTang_phong extends AppCompatActivity {
    private Intent mIntent;
    private  Bundle mBundle;
    private RecyclerView mRecyclerView;
    private FloatingActionButton btn_add;
    private phongAdapter adapter;
    private phongDao mPhongDao;
    private loaiPhongDao mLoaiPhongDao;
    private datPhongDao mDatPhongDao;
    private datPhongObj mDatPhongObj;
    private  phongObj mPhongObj;
    private String maTang;
    private tangObj items_nhan;
    private hoaDonDao mHoaDonDao;
    private hoaDonObj mHoaDonObj;

    private spinerTenLoaiAdapter spinerLoaiPhongAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quan_ly_phong_tang);
        d("ca" + "chung", "onCreate: chung");
        //
            mIntent = getIntent();
            mBundle = mIntent.getBundleExtra("bundle_senTang");
            items_nhan = (tangObj) mBundle.getSerializable("items");
            maTang = items_nhan.getMaTang();

        getSupportActionBar().setTitle("Quản lý phòng "+items_nhan.getTenTang());

        //
        mHoaDonDao = new hoaDonDao(quanLyTang_phong.this);
        mDatPhongDao = new datPhongDao(quanLyTang_phong.this);

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

                clickItemPhong(items);
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
        loaiPhongDao mLoaiPhongDao = new loaiPhongDao(quanLyTang_phong.this);
        List<loaiPhongObj> listLoaiPhong = mLoaiPhongDao.getAll();
        if (listLoaiPhong.size() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(quanLyTang_phong.this);
            builder.setTitle("Bạn cần thêm Loại Phòng đã");
            builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
        else {

            Dialog dialogThemPhong = new Dialog(quanLyTang_phong.this,
                    com.google.android.material.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
            dialogThemPhong.setContentView(R.layout.dialog_add_phong);
            EditText editText_tenPhong = dialogThemPhong.findViewById(R.id.dialog_add_phong_tenPhong);
            Spinner spinner_loaiPhong = dialogThemPhong.findViewById(R.id.dialog_add_phong_spiner_loaiPhong);
            Button button_them = dialogThemPhong.findViewById(R.id.dialog_add_phong_btnAdd);
            //
            spinerLoaiPhongAdapter = new spinerTenLoaiAdapter(quanLyTang_phong.this
                    , R.layout.item_spinner_select, layLoaiPhong());
            spinner_loaiPhong.setAdapter(spinerLoaiPhongAdapter);
            //
            button_them.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText_tenPhong.getText().toString().isEmpty()) {
                        editText_tenPhong.setError("Bạn không được để trống tiên phòng");
                        return;
                    }
                    if (editText_tenPhong.getText().toString().toLowerCase().equals(maTang.toLowerCase())) {
                        editText_tenPhong.setError("Bạn chưa đặt tên phòng");
                        return;
                    }
                    if (editText_tenPhong.getText().toString().toLowerCase()
                            .substring(0, maTang.length()).equals(maTang.toLowerCase())) {
                        loaiPhongObj itemSlect = (loaiPhongObj) spinner_loaiPhong.getSelectedItem();
                        phongObj phongObjInsert = new phongObj();
                        phongObjInsert.setTenPhong(editText_tenPhong.getText().toString().trim());
                        phongObjInsert.setMaLoai(itemSlect.getMaLoai());
                        phongObjInsert.setMaTang(maTang);
                        phongObjInsert.setTrangThai("Phòng trống");
                        mPhongDao.insertPhong(phongObjInsert);
                        capNhapDuLieu();
                        dialogThemPhong.cancel();
                    } else {
                        editText_tenPhong.setError("Tên tầng phải bắt đầu bằng mã tầng");
                    }

                }
            });
            editText_tenPhong.setText(maTang);
            dialogThemPhong.show();
        }
    }
    private List<loaiPhongObj> layLoaiPhong(){
        List<loaiPhongObj> list = mLoaiPhongDao.getAll();
        return list;
    }
    private void clickItemPhong(phongObj phongObj){
        if (phongObj.getTrangThai().toLowerCase().equals("phòng trống")){
            Dialog dialog = new Dialog(quanLyTang_phong.this
            , androidx.appcompat.R.style.Base_Theme_AppCompat_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_update_phong);
            EditText tenPhong = dialog.findViewById(R.id.dialog_update_phong_tenPhong);
            EditText trangThai = dialog.findViewById(R.id.dialog_update_phong_trangThai);
            Button btn_TaoPhieu = dialog.findViewById(R.id.dialog_update_phong_taoPhieu);
            Button btn_sua = dialog.findViewById(R.id.dialog_update_phong_sua);
            //
            tenPhong.setText(phongObj.getTenPhong());
            trangThai.setText(phongObj.getTrangThai());
            //
            btn_TaoPhieu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    taoPhieuThuePhong(phongObj);
                    dialog.cancel();
                }
            });
            btn_sua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    phongObj phongObj1 = phongObj;
                    phongObj1.setTenPhong(tenPhong.getText().toString().trim());
                    mPhongDao.updatePhong(phongObj1);
                    capNhapDuLieu();
                    dialog.cancel();
                }
            });
            dialog.show();

        }
        else{
            Dialog dialog = new Dialog(quanLyTang_phong.this
                    , androidx.appcompat.R.style.Base_Theme_AppCompat_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_update_phong_dang_su_dung);
            EditText tenPhong = dialog.findViewById(R.id.dialog_update_phong_dang_su_dung_tenPhong);
            EditText trangThai = dialog.findViewById(R.id.dialog_update_phong_dang_su_dung_trangThai);
            EditText nguoiThue = dialog.findViewById(R.id.dialog_update_phong_dang_su_dung_nguoiThue);
            Button thanhToan = dialog.findViewById(R.id.dialog_update_phong_dang_su_dung_thanhToan);
            Button chiTiet = dialog.findViewById(R.id.dialog_update_phong_dang_su_dung_chiTiet);
            //

            datPhongDao mDatPhongDao = new datPhongDao(quanLyTang_phong.this);
            datPhongObj mDatPhongObj = mDatPhongDao.getByMaPhong(phongObj.getMaPhong());
            d("ca" + "chung", "clickItemPhong: "+mDatPhongObj.getMaDatPhong());

            khachHangDao mKhachHangDao = new khachHangDao(quanLyTang_phong.this);
            khachHangObj mKhachHangObj = mKhachHangDao.getByMaKh(mDatPhongObj.getMaKh());

            nguoiThue.setText(mKhachHangObj.getTenKh());
            
            thanhToan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pTthanhToan(phongObj);

                    dialog.cancel();
                }
            });
            chiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pTchiTiet(mDatPhongObj.getMaDatPhong());
                }
            });
            //
            
            tenPhong.setText(phongObj.getTenPhong());
            trangThai.setText(phongObj.getTrangThai());
            dialog.show();
        }

    }

    private void pTchiTiet(String maDatPhong) {
        Intent intent = new Intent(quanLyTang_phong.this,chiTietHoaDon.class);
        intent.putExtra("maDatPhong",maDatPhong);
        d("ca" + "chung", "pTchiTiet: "+maDatPhong);
        startActivity(intent);
    }

    private void pTthanhToan(phongObj items) {
        mDatPhongObj = mDatPhongDao.getByMaPhong(items.getMaPhong());
        chiTietDichVuDao mChiTietDichVuDao = new chiTietDichVuDao(quanLyTang_phong.this);
        double tienDv = mChiTietDichVuDao.tongTienDv(mDatPhongObj.getMaDatPhong());
        mHoaDonObj = new hoaDonObj();
        mHoaDonObj.setNgayThang(getDate());
        mHoaDonObj.setMaDatPhong(mDatPhongObj.getMaDatPhong());
        mHoaDonObj.setTongTien(String.valueOf(tienDv + tinhTongTienDP()));
        mHoaDonObj.setMaChiTietDV(mDatPhongObj.getMaChiTietDV());
        mHoaDonDao.inserHoaDonThanhToan(mHoaDonObj);
        //
        mDatPhongDao.updateDatPhong(mDatPhongObj);
        items.setTrangThai("phòng trống");
        mPhongDao.updatePhong(items);
        Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_LONG).show();
        capNhapDuLieu();
    }
    private double tinhTongTienDP(){
        String timeString = mDatPhongObj.getSoGioDat();
        LocalTime time = LocalTime.parse(timeString);
        double hour = time.getHour() + (time.getMinute() / 60.0) + (time.getSecond() / 3600.0);
        double tongTienDP = Double.parseDouble(mDatPhongObj.getGiaTien())* hour;
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        String formattedNumber = decimalFormat.format(tongTienDP);
        return Double.parseDouble(formattedNumber);
    }

    private void taoPhieuThuePhong(phongObj phongObj){
      Intent intent = new Intent(quanLyTang_phong.this, taoPhieuDatPhong.class);
      Bundle bundle = new Bundle();
      bundle.putSerializable("itemSend", phongObj);
      intent.putExtra("intentSend",bundle);
      startActivity(intent);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        capNhapDuLieu();
    }
    private  String getDate(){
        LocalDate currentDate = LocalDate.now();
         return currentDate.toString();
    }
}
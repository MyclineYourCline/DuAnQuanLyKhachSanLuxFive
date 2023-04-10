package com.example.myapplication;

import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.AdapterManager.phongAdapter;
import com.example.myapplication.DbManager.chiTietDichVuDao;
import com.example.myapplication.DbManager.datPhongDao;
import com.example.myapplication.DbManager.hoaDonDao;
import com.example.myapplication.DbManager.khachHangDao;
import com.example.myapplication.DbManager.loaiPhongDao;
import com.example.myapplication.DbManager.phongDao;
import com.example.myapplication.InterfaceManager.sendPhong;
import com.example.myapplication.ObjectManager.chiTietDichVuOBJ;
import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.dichVuObj;
import com.example.myapplication.ObjectManager.hoaDonObj;
import com.example.myapplication.ObjectManager.khachHangObj;
import com.example.myapplication.ObjectManager.loaiPhongObj;
import com.example.myapplication.ObjectManager.nhanVienObj;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.ObjectManager.tangObj;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class quanLyPhong extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private phongAdapter mAdapter;
    private Intent mIntent;
    private Bundle mBundle;
    private phongDao mPhongDao;

    private loaiPhongDao mLoaiPhong;
    private  datPhongObj mDatPhongObj;
    private datPhongDao mDatPhongDao;
    private hoaDonDao mHoaDonDao;
    private hoaDonObj mHoaDonObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_phong);
        getSupportActionBar().setTitle("Quản lý phòng");

        mDatPhongDao = new datPhongDao(quanLyPhong.this);
        mHoaDonDao = new hoaDonDao(quanLyPhong.this);
        mRecyclerView = findViewById(R.id.activity_quan_ly_phong_recycleView);
        mPhongDao = new phongDao(quanLyPhong.this);

        mAdapter = new phongAdapter(quanLyPhong.this, new sendPhong() {

            @Override
            public void sendPhong(phongObj items) {
                clickItemPhong(items);
            }
        });
            capNhapDuLieu();

    }
    private void capNhapDuLieu(){
        mAdapter.setmList(getListPhong());
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<phongObj> getListPhong() {
        List<phongObj> list = mPhongDao.getAll();
        return list;
    }
    private void clickItemPhong(phongObj phongObj){
        if (phongObj.getTrangThai().toLowerCase().equals("phòng trống")){
            Dialog dialog = new Dialog(quanLyPhong.this
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
                    Intent intent = new Intent(quanLyPhong.this, taoPhieuDatPhong.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("itemSend", phongObj);
                    intent.putExtra("intentSend",bundle);
                    startActivity(intent);
                    dialog.cancel();
                }
            });
            btn_sua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    phongObj phongObj1 = phongObj;
                    phongObj1.setTenPhong(tenPhong.getText().toString().trim());
                    mPhongDao.updatePhong(phongObj1);
                    dialog.cancel();
                }
            });
            dialog.show();

        }
        else{
            Dialog dialog = new Dialog(quanLyPhong.this
                    , androidx.appcompat.R.style.Base_Theme_AppCompat_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_update_phong_dang_su_dung);
            EditText tenPhong = dialog.findViewById(R.id.dialog_update_phong_dang_su_dung_tenPhong);
            EditText trangThai = dialog.findViewById(R.id.dialog_update_phong_dang_su_dung_trangThai);
            EditText nguoiThue = dialog.findViewById(R.id.dialog_update_phong_dang_su_dung_nguoiThue);
            Button thanhToan = dialog.findViewById(R.id.dialog_update_phong_dang_su_dung_thanhToan);
            Button chiTiet = dialog.findViewById(R.id.dialog_update_phong_dang_su_dung_chiTiet);
            //

            datPhongDao mDatPhongDao = new datPhongDao(quanLyPhong.this);
            datPhongObj mDatPhongObj = mDatPhongDao.getByMaPhong(phongObj.getMaPhong());
            d("ca" + "chung", "clickItemPhong: "+mDatPhongObj.getMaDatPhong());

            khachHangDao mKhachHangDao = new khachHangDao(quanLyPhong.this);
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
                    pTchiTiet(phongObj.getMaPhong());
                }
            });
            //
            tenPhong.setText(phongObj.getTenPhong());
            trangThai.setText(phongObj.getTrangThai());
            dialog.show();
        }

    }
    private void pTthanhToan(phongObj items) {
        mDatPhongObj = mDatPhongDao.getByMaPhong(items.getMaPhong());
        chiTietDichVuDao mChiTietDichVuDao = new chiTietDichVuDao(quanLyPhong.this);
        double tienDv = mChiTietDichVuDao.tongTienDv(mDatPhongObj.getMaDatPhong());
        mHoaDonObj = new hoaDonObj();
        mHoaDonObj.setNgayThang(getDate());
        mHoaDonObj.setMaDatPhong(mDatPhongObj.getMaDatPhong());
        mHoaDonObj.setTongTien(String.valueOf(tienDv + Double.parseDouble(mDatPhongObj.getTongTien())));
        mHoaDonObj.setMaChiTietDV(mDatPhongObj.getMaChiTietDV());
        mHoaDonDao.inserHoaDonThanhToan(mHoaDonObj);
        //
        mDatPhongDao.updateDatPhong(mDatPhongObj);
        items.setTrangThai("phòng trống");
        mPhongDao.updatePhong(items);
        Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_LONG).show();
        capNhapDuLieu();
    }
    private void pTchiTiet(String maPhong) {
        mDatPhongObj = mDatPhongDao.getByMaPhong(maPhong);
        Intent intent = new Intent(quanLyPhong.this,chiTietHoaDon.class);
        intent.putExtra("maDatPhong",mDatPhongObj.getMaDatPhong());
        startActivity(intent);
    }
    private  String getDate(){
        LocalDate currentDate = LocalDate.now();
        return currentDate.toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        capNhapDuLieu();
    }
}


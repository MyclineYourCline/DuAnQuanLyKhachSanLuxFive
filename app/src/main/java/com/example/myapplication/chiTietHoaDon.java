package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.AdapterManager.chiTietDichVuAdapter;
import com.example.myapplication.AdapterManager.itemRcy_dichVu_chiTietPhieuDat;
import com.example.myapplication.DbManager.chiTietDichVuDao;
import com.example.myapplication.DbManager.datPhongDao;
import com.example.myapplication.DbManager.khachHangDao;
import com.example.myapplication.DbManager.nhanVienDao;
import com.example.myapplication.DbManager.phongDao;
import com.example.myapplication.InterfaceManager.sendChiTietDichVu;
import com.example.myapplication.ObjectManager.chiTietDichVuOBJ;
import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.khachHangObj;
import com.example.myapplication.ObjectManager.nhanVienObj;
import com.example.myapplication.ObjectManager.phongObj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class chiTietHoaDon extends AppCompatActivity {
    private Intent mIntent;
    private String maDatPhong;
    private TextView tenKH, tenNguoiTao, tenPhongThue,
    ngayDat, ngayTra,tongTien,tongSoGio;
    private FloatingActionButton btn_thanhToan;
    private datPhongDao mDatPhongDao;
    private RecyclerView mListView;
    private datPhongObj mDatPhongObj;
    private khachHangDao mKhachHangDao;
    private phongDao mPhongDao;
    private chiTietDichVuDao mChiTietDichVuDao;
    private nhanVienDao mNhanVienDao;
    private nhanVienObj mNhanVienObj;
    private itemRcy_dichVu_chiTietPhieuDat adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);
        mIntent = getIntent();
        maDatPhong = mIntent.getStringExtra("maDatPhong");
        getSupportActionBar().setTitle("Chi tiết phiếu đặt");
        InitUI();

        capNhapDuLieu();
    }
    private List<chiTietDichVuOBJ> listChiTietDV (){
        mChiTietDichVuDao = new chiTietDichVuDao(chiTietHoaDon.this);
        return mChiTietDichVuDao.getByMaChiTietDV(maDatPhong);
    }
    private void capNhapDuLieu(){
        adapter =new itemRcy_dichVu_chiTietPhieuDat(chiTietHoaDon.this);
        adapter.setmList(listChiTietDV());
        mListView.setAdapter(adapter);
    }

    private void InitUI() {
        tenKH = findViewById(R.id.activity_chi_tiet_hoa_don_tenKh);
        tenNguoiTao = findViewById(R.id.activity_chi_tiet_hoa_don_nguoiTao);
        tenPhongThue = findViewById(R.id.activity_chi_tiet_hoa_don_tenPhong);
        ngayDat = findViewById(R.id.activity_chi_tiet_hoa_don_ngayDat);
        ngayTra = findViewById(R.id.activity_chi_tiet_hoa_don_ngayTra);
        tongTien = findViewById(R.id.activity_chi_tiet_hoa_don_tongTien);
        mListView = findViewById(R.id.activity_chi_tiet_hoa_don_lv_chiTietDV);
        btn_thanhToan = findViewById(R.id.activity_chi_tiet_hoa_don_btn_sua);
        tongSoGio = findViewById(R.id.activity_chi_tiet_hoa_don_tongSoGio);
        //
        mDatPhongDao = new datPhongDao(chiTietHoaDon.this);
        mKhachHangDao = new khachHangDao(chiTietHoaDon.this);
        mPhongDao = new phongDao(chiTietHoaDon.this);
        mNhanVienDao = new nhanVienDao(chiTietHoaDon.this);
        //
        mDatPhongObj = mDatPhongDao.getBymaDatPhong(maDatPhong);
        phongObj mPhongObj = mPhongDao.getByMaPhong(mDatPhongObj.getMaPhong());
        khachHangObj mKhachHangObj = mKhachHangDao.getByMaKh(mDatPhongObj.getMaKh());
        tenKH.setText(mKhachHangObj.getTenKh());
        //
        tenPhongThue.setText(mPhongObj.getTenPhong());
        mNhanVienObj = mNhanVienDao.getByMaNhanVien(mDatPhongObj.getMaNhanVien());
        tenNguoiTao.setText(mNhanVienObj.getTenNhanVien());
        ngayDat.setText(mDatPhongObj.getGioVao()+"/"+mDatPhongObj.getCheckIn());
        ngayTra.setText(mDatPhongObj.getGioRa()+"/"+mDatPhongObj.getNgayRa());
        tongSoGio.setText(mDatPhongObj.getSoGioDat()+" ("+mDatPhongObj.getGiaTien()+"/Giờ)");




    }
}
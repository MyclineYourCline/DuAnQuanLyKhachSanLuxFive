package com.example.myapplication;

import static android.util.Log.d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import java.time.LocalTime;
import java.util.List;


public class chiTietHoaDon extends AppCompatActivity {
    private Intent mIntent;
    private String maDatPhong;
    private TextView tenKH, tenNguoiTao, tenPhongThue,
    ngayDat, ngayTra,tongTien,tongSoGio, giaThue;
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
        d("ca" + "chung", "listChiTietDV: "+maDatPhong);
        return mChiTietDichVuDao.getByMaDP(maDatPhong);
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
        giaThue = findViewById(R.id.activity_chi_tiet_hoa_don_giaThue);
        //
        mDatPhongDao = new datPhongDao(chiTietHoaDon.this);
        mKhachHangDao = new khachHangDao(chiTietHoaDon.this);
        mPhongDao = new phongDao(chiTietHoaDon.this);
        mNhanVienDao = new nhanVienDao(chiTietHoaDon.this);
        mChiTietDichVuDao = new chiTietDichVuDao(chiTietHoaDon.this);
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
        tongSoGio.setText(mDatPhongObj.getSoGioDat());
        giaThue.setText(mDatPhongObj.getGiaTien()+" VND");
        //
        float tongTienF = Float.parseFloat(mDatPhongObj.getTongTien())+   Float.parseFloat(String.valueOf(tinhTongTienDV(mDatPhongObj.getMaDatPhong())));
        String tongTienString = String.format("%.1f", tongTienF);
        tongTien.setText(tongTienString);

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
    private float tinhTongTienDV(String maDatPhong){
        d("ca" + "chung", "tinhTongTienDV: "+mChiTietDichVuDao.tongTienDv(maDatPhong));
       return mChiTietDichVuDao.tongTienDv(maDatPhong);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_back:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
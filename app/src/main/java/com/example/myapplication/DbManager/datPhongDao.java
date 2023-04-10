package com.example.myapplication.DbManager;

import static android.util.Log.d;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.phongObj;

import java.util.ArrayList;
import java.util.List;

public class datPhongDao {
    private SQLiteDatabase db;
    private phongDao mPhongDao;

    public datPhongDao(Context context) {
        createDataBase mCreateDataBase = new createDataBase(context);
        db = mCreateDataBase.getWritableDatabase();
        mPhongDao = new phongDao(context);
    }

    @SuppressLint("Range")
    public List<datPhongObj> get(String sql, String...agrs){
        List<datPhongObj> mList = new ArrayList<>();
        Cursor mCursor = db.rawQuery(sql,agrs);
        while (mCursor.moveToNext()){
            datPhongObj items = new datPhongObj();
            items.setMaDatPhong(mCursor.getString(mCursor.getColumnIndex("maDatPhong")));
            items.setMaKh(mCursor.getString(mCursor.getColumnIndex("maKh")));
            items.setMaNhanVien(mCursor.getString(mCursor.getColumnIndex("maNhanVien")));
            items.setMaPhong(mCursor.getString(mCursor.getColumnIndex("maPhong")));
            items.setCheckIn(mCursor.getString(mCursor.getColumnIndex("checkIn")));
            items.setGioVao(mCursor.getString(mCursor.getColumnIndex("gioVao")));
            items.setGiaTien(mCursor.getString(mCursor.getColumnIndex("giaThue")));
            items.setGioRa(mCursor.getString(mCursor.getColumnIndex("gioRa")));
            items.setNgayRa(mCursor.getString(mCursor.getColumnIndex("checkOut")));
            items.setSoGioDat(mCursor.getString(mCursor.getColumnIndex("soGioThue")));
            items.setMaChiTietDV(mCursor.getString(mCursor.getColumnIndex("maChiTietDV")));
            items.setTongTien(mCursor.getString(mCursor.getColumnIndex("tongTien")));
            mList.add(items);
        }
        return mList;
    }
    public Long inserDatPhong(datPhongObj items){
        ContentValues values = new ContentValues();
        values.put("maDatPhong",items.getMaDatPhong());
        values.put("maKh",items.getMaKh());
        values.put("maNhanVien",items.getMaNhanVien());
        values.put("maPhong",items.getMaPhong());
        values.put("checkIn",items.getCheckIn());
        values.put("gioVao",items.getGioVao());
        values.put("checkOut",items.getNgayRa());
        values.put("gioRa",items.getGioRa());
        values.put("giaThue", items.getGiaTien());
        values.put("soGioThue",items.getSoGioDat());
        values.put("maChiTietDV",items.getMaChiTietDV());
        values.put("trangThai", "1");
        values.put("tongTien", items.getTongTien());
        values.put("YDMin",items.getYDMint());
        values.put("YDMout",items.getYDMout());
        return db.insert("datPhong",null,values);
    }
    public Long inserDatPhongPhieuCho(datPhongObj items){
        ContentValues values = new ContentValues();
        values.put("maDatPhong",items.getMaDatPhong());
        values.put("maKh",items.getMaKh());
        values.put("maNhanVien",items.getMaNhanVien());
        values.put("maPhong",items.getMaPhong());
        values.put("checkIn",items.getCheckIn());
        values.put("gioVao",items.getGioVao());
        values.put("checkOut",items.getNgayRa());
        values.put("gioRa",items.getGioRa());
        values.put("giaThue", items.getGiaTien());
        values.put("soGioThue",items.getSoGioDat());
        values.put("maChiTietDV",items.getMaChiTietDV());
        values.put("trangThai", "3");
        values.put("tongTien", items.getTongTien());
        values.put("YDMin",items.getYDMint());
        values.put("YDMout",items.getYDMout());
        return db.insert("datPhong",null,values);
    }
    public int updateDatPhong(datPhongObj items){
        ContentValues values = new ContentValues();
        values.put("maDatPhong",items.getMaDatPhong());
        values.put("maKh",items.getMaKh());
        values.put("maNhanVien",items.getMaNhanVien());
        values.put("maPhong",items.getMaPhong());
        values.put("checkIn",items.getCheckIn());
        values.put("gioVao",items.getGioVao());
        values.put("giaThue",items.getGiaTien());
        values.put("checkOut",items.getNgayRa());
        values.put("gioRa",items.getGioRa());
        values.put("maChiTietDV",items.getMaChiTietDV());
        values.put("soGioThue",items.getSoGioDat());
        values.put("tongTien", items.getTongTien());
        values.put("trangThai", "2");
        values.put("YDMin",items.getYDMint());
        values.put("YDMout",items.getYDMout());
        return db.update("datPhong",values,"maDatPhong = ?"
                , new String[]{items.getMaDatPhong()});
    }
    public int deleteDatPhong(String maDatPhong){
        return db.delete("datPhong","maDatPhong = ?", new String[]{maDatPhong});
    }
    public datPhongObj getBymaDatPhong(String maDatPhong){
        String sql = "SELECT * FROM datPhong WHERE maDatPhong = ?";
        List<datPhongObj> mList = get(sql,maDatPhong);
        return mList.get(0);
    }
    public datPhongObj getBymaKh(String maKh){
        String sql = "SELECT * FROM datPhong WHERE maKh = ?";
        List<datPhongObj> mList = get(sql,maKh);
        return mList.get(0);
    }
    public datPhongObj getByMaNhanVien(String maNhanVien){
        String sql = "SELECT * FROM datPhong WHERE maNhanVien = ?";
        List<datPhongObj> mList = get(sql,maNhanVien);
        return mList.get(0);
    }
    public datPhongObj getByMaPhong(String maPhong){
        String sql = "SELECT * FROM datPhong WHERE maPhong = ? and trangThai = '1'";
        List<datPhongObj> mList = get(sql,maPhong);
        return mList.get(0);
    }
    @SuppressLint("Range")
    public List<phongObj> truyVanTaoPhieuCho(String ngayVao, String gioVao, String ngayRa, String gioRa){
        List<phongObj> list = new ArrayList<>();
        String sql1 = "SELECT * FROM phong where trangThai = ?";
        List<phongObj> list1 = mPhongDao.get(sql1, new String[]{"Phòng trống"});
        d("ca" + "chung", "truyVanTaoPhieuCho "+list1.size());
        for (phongObj x: list1){
            list.add(x);
        }
//        String sql2 = "SELECT phong.maPhong," +
//                "phong.tenPhong," +
//                "phong.maTang," +
//                "phong.maLoai," +
//                "phong.trangThai from phong  INNER JOIN datPhong on phong.maPhong = datPhong.maPhong \n" +
//                "WHERE  datPhong.YDMIN NOT BETWEEN ? AND ?\n" +
//                "AND datPhong.YDMOUT NOT BETWEEN ? AND ?";
//        Cursor cursor = db.rawQuery(sql2,new String[]{ngayVao,gioVao,ngayRa,gioRa});
//        while (cursor.moveToNext()){
//            phongObj item = new phongObj();
//            item.setMaPhong(cursor.getString(cursor.getColumnIndex("maPhong")));
//            item.setTenPhong(cursor.getString(cursor.getColumnIndex("tenPhong")));
//            item.setMaTang(cursor.getString(cursor.getColumnIndex("maTang")));
//            item.setMaLoai(cursor.getString(cursor.getColumnIndex("maLoai")));
//            item.setTrangThai(cursor.getString(cursor.getColumnIndex("trangThai")));
//            list.add(item);
//        }
        return list;
    }
}

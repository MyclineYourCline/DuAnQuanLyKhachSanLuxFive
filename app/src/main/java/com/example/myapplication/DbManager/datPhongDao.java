package com.example.myapplication.DbManager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.ObjectManager.datPhong;

import java.util.ArrayList;
import java.util.List;

public class datPhongDao {
    private SQLiteDatabase db;

    public datPhongDao(Context context) {
        createDataBase mCreateDataBase = new createDataBase(context);
        db = mCreateDataBase.getWritableDatabase();
    }
    //maDatPhong
    //maKh
    //maNhanVien
    //maPhong
    //
    //ghiChu
    //checkIn
    //checkOut
    //gioVao
    //gioRa
    @SuppressLint("Range")
    public List<datPhong> get(String sql, String...agrs){
        List<datPhong> mList = new ArrayList<>();
        Cursor mCursor = db.rawQuery(sql,agrs);
        while (mCursor.moveToNext()){
            datPhong items = new datPhong();
            items.setMaDatPhong(mCursor.getString(mCursor.getColumnIndex("maDatPhong")));
            items.setMaKh(mCursor.getString(mCursor.getColumnIndex("maKh")));
            items.setMaNhanVien(mCursor.getString(mCursor.getColumnIndex("maNhanVien")));
            items.setMaPhong(mCursor.getString(mCursor.getColumnIndex("maPhong")));
            items.setCheckIn(mCursor.getString(mCursor.getColumnIndex("checkIn")));
            items.setGioVao(mCursor.getString(mCursor.getColumnIndex("gioVao")));
            mList.add(items);
        }
        return mList;
    }
    public Long inserDatPhong(datPhong items){
        ContentValues values = new ContentValues();
        values.put("maDatPhong",items.getMaDatPhong());
        values.put("maKh",items.getMaKh());
        values.put("maNhanVien",items.getMaNhanVien());
        values.put("maPhong",items.getMaPhong());
        values.put("checkIn",items.getCheckIn());
        values.put("gioVao",items.getGioVao());
        values.put("checkOut",items.checkOut());
        values.put("gioRa",items.setGioRa());
        return db.insert("datPhong",null,values);
    }
    public int updateDatPhong(datPhong items){
        ContentValues values = new ContentValues();
        values.put("maDatPhong",items.getMaDatPhong());
        values.put("maKh",items.getMaKh());
        values.put("maNhanVien",items.getMaNhanVien());
        values.put("maPhong",items.getMaPhong());
        values.put("checkIn",items.getCheckIn());
        values.put("gioVao",items.getGioVao());
        values.put("checkOut",items.checkOut());
        values.put("gioRa",items.setGioRa());
        return db.update("datPhong",values,"maDatPhong = ?"
                , new String[]{items.getMaDatPhong()});
    }
    public int deleteDatPhong(String maDatPhong){
        return db.delete("datPhong","maDatPhong = ?", new String[]{maDatPhong});
    }
    public datPhong getBymaDatPhong(String maDatPhong){
        String sql = "SELECT * FROM datPhong WHERE maDatPhong = ?";
        List<datPhong> mList = get(sql,maDatPhong);
        return mList.get(0);
    }
    public datPhong getBymaKh(String maKh){
        String sql = "SELECT * FROM datPhong WHERE maKh = ?";
        List<datPhong> mList = get(sql,maKh);
        return mList.get(0);
    }
    public datPhong getByMaNhanVien(String maNhanVien){
        String sql = "SELECT * FROM datPhong WHERE maNhanVien = ?";
        List<datPhong> mList = get(sql,maNhanVien);
        return mList.get(0);
    }
    public datPhong getByMaPhong(String maPhong){
        String sql = "SELECT * FROM datPhong WHERE maPhong = ?";
        List<datPhong> mList = get(sql,maPhong);
        return mList.get(0);
    }
}

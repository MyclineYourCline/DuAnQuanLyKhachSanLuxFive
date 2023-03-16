package com.example.myapplication.DbManager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.ObjectManager.datPhongObj;

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
        values.put("checkOut",items.checkOut());
        values.put("gioRa",items.setGioRa());
        return db.insert("datPhongObj",null,values);
    }
    public int updateDatPhong(datPhongObj items){
        ContentValues values = new ContentValues();
        values.put("maDatPhong",items.getMaDatPhong());
        values.put("maKh",items.getMaKh());
        values.put("maNhanVien",items.getMaNhanVien());
        values.put("maPhong",items.getMaPhong());
        values.put("checkIn",items.getCheckIn());
        values.put("gioVao",items.getGioVao());
        values.put("checkOut",items.checkOut());
        values.put("gioRa",items.setGioRa());
        return db.update("datPhongObj",values,"maDatPhong = ?"
                , new String[]{items.getMaDatPhong()});
    }
    public int deleteDatPhong(String maDatPhong){
        return db.delete("datPhongObj","maDatPhong = ?", new String[]{maDatPhong});
    }
    public datPhongObj getBymaDatPhong(String maDatPhong){
        String sql = "SELECT * FROM datPhongObj WHERE maDatPhong = ?";
        List<datPhongObj> mList = get(sql,maDatPhong);
        return mList.get(0);
    }
    public datPhongObj getBymaKh(String maKh){
        String sql = "SELECT * FROM datPhongObj WHERE maKh = ?";
        List<datPhongObj> mList = get(sql,maKh);
        return mList.get(0);
    }
    public datPhongObj getByMaNhanVien(String maNhanVien){
        String sql = "SELECT * FROM datPhongObj WHERE maNhanVien = ?";
        List<datPhongObj> mList = get(sql,maNhanVien);
        return mList.get(0);
    }
    public datPhongObj getByMaPhong(String maPhong){
        String sql = "SELECT * FROM datPhongObj WHERE maPhong = ?";
        List<datPhongObj> mList = get(sql,maPhong);
        return mList.get(0);
    }
}

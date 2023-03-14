package com.example.myapplication.DbManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.ObjectManager.hoaDon;

import java.util.ArrayList;
import java.util.List;

public class hoaDonDao {
    SQLiteDatabase db;

    public hoaDonDao(Context context) {
        createDataBase mCreateDataBase = new createDataBase(context);
        db =  mCreateDataBase.getWritableDatabase();
    }
    // //maHoaDon
    //    //maDatPhong
    //    //trangThai
    //    //tongTien
    //    //ngayThang
    //    //maChiTietDV
    @SuppressLint("Range")
    public List<hoaDon> get(String sql, String...agrs ){
        List<hoaDon> mList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, agrs);
        while (cursor.moveToNext()){
            hoaDon hoaDon = new hoaDon();
            hoaDon.setMaHoaDon(cursor.getString(cursor.getColumnIndex("maHoaDon")));
            hoaDon.setTrangThai(cursor.getString(cursor.getColumnIndex("trangThai")));
            hoaDon.setTongTien(cursor.getString(cursor.getColumnIndex("tongTien")));
            hoaDon.setNgayThang(cursor.getString(cursor.getColumnIndex("ngayThang")));
            hoaDon.setMaDatPhong(cursor.getString(cursor.getColumnIndex("maDatPhong")));
            hoaDon.setMaChiTietDV(cursor.getString(cursor.getColumnIndex("maChiTietDV")));
            mList.add(hoaDon);
        }
        return mList;
    }
}

package com.example.myapplication.DbManager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.ObjectManager.dichVu;

import java.util.ArrayList;
import java.util.List;

public class dichVuDao {
    SQLiteDatabase db;

    public dichVuDao(Context context) {
        createDataBase mCreateDataBase = new createDataBase(context);
        db = mCreateDataBase.getWritableDatabase();
    }
    //maDichVu
    //tenDichVu
    //giaDichVu
    //
    //soLuong
    @SuppressLint("Range")
    public List<dichVu> get (String sql, String...agrs){
        List<dichVu> mList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, agrs);
        while (cursor.moveToNext()){
            dichVu dichVu = new dichVu();
            dichVu.setMaDichVu(cursor.getString(cursor.getColumnIndex("maDichVu")));
            dichVu.setTenDichVu(cursor.getString(cursor.getColumnIndex("tenDichVu")));
            dichVu.setGiaDichVu(cursor.getString(cursor.getColumnIndex("giaDichVu")));
            dichVu.setSoLuong(cursor.getString(cursor.getColumnIndex("soLuong")));
            mList.add(dichVu);
        }
        return mList;
    }
    public List<dichVu> getAll(){
        String sql = "SELECT * FROM dichVuThem";
        return get(sql);
    }
    public Long inserDichVuThem(dichVu items){
        ContentValues values = new ContentValues();
        values.put("maDichVu",items.getMaDichVu());
        values.put("tenDichVu",items.getTenDichVu());
        values.put("giaDichVu",items.getGiaDichVu());
        values.put("soLuong",items.getSoLuong());
        return db.insert("dichVuThem",null,values);
    }
    public int updateDichVuThem (dichVu items){
        ContentValues values = new ContentValues();
        values.put("maDichVu",items.getMaDichVu());
        values.put("tenDichVu",items.getTenDichVu());
        values.put("giaDichVu",items.getGiaDichVu());
        values.put("soLuong",items.getSoLuong());
        return db.update("dichVuThem",values,"maDichVu = ?"
                , new String[]{items.getMaDichVu()});
    }
    public int  deleteDichVuThem (String maDichVuThem){
        return db.delete("dichVuThem","maDichVu = ?"
                , new String[]{maDichVuThem});
    }
}

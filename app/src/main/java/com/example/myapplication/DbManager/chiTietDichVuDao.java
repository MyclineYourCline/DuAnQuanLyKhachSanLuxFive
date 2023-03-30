package com.example.myapplication.DbManager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.ObjectManager.chiTietDichVuOBJ;

import java.util.ArrayList;
import java.util.List;

public class chiTietDichVuDao {
    SQLiteDatabase db;

    public chiTietDichVuDao(Context context) {
        createDataBase mCreateDataBase = new createDataBase(context);
        db = mCreateDataBase.getWritableDatabase();
    }
    ////maChiTietDV
    //    //maDichVu
    //    //maDatPhong
    //    //giaTien
    @SuppressLint("Range")
    public List<chiTietDichVuOBJ> get(String sql, String...agrs){
        List<chiTietDichVuOBJ> mList = new ArrayList<>();
        Cursor mCursor = db.rawQuery(sql,agrs);
        while (mCursor.moveToNext()){
            chiTietDichVuOBJ itemsResult = new chiTietDichVuOBJ();
            itemsResult.setMaChiTietDV(mCursor.getString(mCursor.getColumnIndex("maChiTietDV")));
            itemsResult.setMaDichVu(mCursor.getString(mCursor.getColumnIndex("maDichVu")));
            itemsResult.setMaDatPhong(mCursor.getString(mCursor.getColumnIndex("maDatPhong")));
            itemsResult.setGiaTien(mCursor.getString(mCursor.getColumnIndex("giaTien")));
            mList.add(itemsResult);
        }
        return mList;
    }
    public List<chiTietDichVuOBJ> getAll(){
        String sql ="SELECT * FROM chiTietDichVuOBJ";
        return get(sql);
    }
    public Long inserChiTietDichVu(chiTietDichVuOBJ items){
        ContentValues values = new ContentValues();
        values.put("maChiTietDV",items.getMaChiTietDV());
        values.put("maDichVu",items.getMaDichVu());
        values.put("maDatPhong",items.getMaDatPhong());
        values.put("giaTien",items.getGiaTien());
        return db.insert("chiTietDichVuOBJ",null,values);
    }
    public int updateChiTietDichVu(chiTietDichVuOBJ items){
        ContentValues values = new ContentValues();
        values.put("maChiTietDV",items.getMaChiTietDV());
        values.put("maDichVu",items.getMaDichVu());
        values.put("maDatPhong",items.getMaDatPhong());
        values.put("giaTien",items.getGiaTien());
        return db.update("chiTietDichVuOBJ",values,"maChiTietDV = ?"
                , new String[]{items.getMaChiTietDV()});
    }
    public int deleteChiTietDichVu(String maChiTietDV){
        return db.delete("chiTietDichVuOBJ","maChiTietDV = ?",new String[]{maChiTietDV});
    }
    public chiTietDichVuOBJ getByMaDatPhong(String maDatPhong){
        String sql = "SELECT * FROM chiTietDichVuOBJ WHERE maDatPhong = ?";
       List<chiTietDichVuOBJ> mList = get(sql,maDatPhong);
       return mList.get(0);
    }
    public chiTietDichVuOBJ getByMaDichVu(String maDichVu){
        String sql = "SELECT * FROM chiTietDichVuOBJ WHERE maDichVu = ?";
        List<chiTietDichVuOBJ> mList = get(sql,maDichVu);
        return mList.get(0);
    }
    public chiTietDichVuOBJ getByMaChiTietDV(String maChiTietDV){
        String sql = "SELECT * FROM chiTietDichVuOBJ WHERE maChiTietDV = ?";
        List<chiTietDichVuOBJ> mList = get(sql,maChiTietDV);
        return mList.get(0);
    }
}

package com.example.myapplication.DbManager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.ObjectManager.chiTietDichVu;

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
    public List<chiTietDichVu> get(String sql, String...agrs){
        List<chiTietDichVu> mList = new ArrayList<>();
        Cursor mCursor = db.rawQuery(sql,agrs);
        while (mCursor.moveToNext()){
            chiTietDichVu itemsResult = new chiTietDichVu();
            itemsResult.setMaChiTietDV(mCursor.getString(mCursor.getColumnIndex("maChiTietDV")));
            itemsResult.setMaDichVu(mCursor.getString(mCursor.getColumnIndex("maDichVu")));
            itemsResult.setMaDatPhong(mCursor.getString(mCursor.getColumnIndex("maDatPhong")));
            itemsResult.setGiaTien(mCursor.getString(mCursor.getColumnIndex("giaTien")));
            mList.add(itemsResult);
        }
        return mList;
    }
    public List<chiTietDichVu> getAll(){
        String sql ="SELECT * FROM chiTietDichVu";
        return get(sql);
    }
    public Long inserChiTietDichVu(chiTietDichVu items){
        ContentValues values = new ContentValues();
        values.put("maChiTietDV",items.getMaChiTietDV());
        values.put("maDichVu",items.getMaDichVu());
        values.put("maDatPhong",items.getMaDatPhong());
        values.put("giaTien",items.getGiaTien());
        return db.insert("chiTietDichVu",null,values);
    }
    public int updateChiTietDichVu(chiTietDichVu items){
        ContentValues values = new ContentValues();
        values.put("maChiTietDV",items.getMaChiTietDV());
        values.put("maDichVu",items.getMaDichVu());
        values.put("maDatPhong",items.getMaDatPhong());
        values.put("giaTien",items.getGiaTien());
        return db.update("chiTietDichVu",values,"maChiTietDV = ?"
                , new String[]{items.getMaChiTietDV()});
    }
    public int deleteChiTietDichVu(String maChiTietDV){
        return db.delete("chiTietDichVu","maChiTietDV = ?",new String[]{maChiTietDV});
    }
    public chiTietDichVu getByMaDatPhong(String maDatPhong){
        String sql = "SELECT * FROM chiTietDichVu WHERE maDatPhong = ?";
       List<chiTietDichVu> mList = get(sql,maDatPhong);
       return mList.get(0);
    }
    public chiTietDichVu getByMaDichVu(String maDichVu){
        String sql = "SELECT * FROM chiTietDichVu WHERE maDichVu = ?";
        List<chiTietDichVu> mList = get(sql,maDichVu);
        return mList.get(0);
    }
    public chiTietDichVu getByMaChiTietDV(String maChiTietDV){
        String sql = "SELECT * FROM chiTietDichVu WHERE maChiTietDV = ?";
        List<chiTietDichVu> mList = get(sql,maChiTietDV);
        return mList.get(0);
    }
}

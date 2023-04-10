package com.example.myapplication.DbManager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.ObjectManager.hoaDonObj;

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
    public List<hoaDonObj> get(String sql, String...agrs ){
        List<hoaDonObj> mList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, agrs);
        while (cursor.moveToNext()){
            hoaDonObj hoaDonObj = new hoaDonObj();
            hoaDonObj.setMaHoaDon(cursor.getString(cursor.getColumnIndex("maHoaDon")));
            hoaDonObj.setTongTien(cursor.getString(cursor.getColumnIndex("tongTien")));
            hoaDonObj.setNgayThang(cursor.getString(cursor.getColumnIndex("ngayThang")));
            hoaDonObj.setMaDatPhong(cursor.getString(cursor.getColumnIndex("maDatPhong")));
            hoaDonObj.setMaChiTietDV(cursor.getString(cursor.getColumnIndex("maChiTietDV")));
            mList.add(hoaDonObj);
        }
        return mList;
    }
    public List<hoaDonObj> getAll(){
        String sql = "SELECT * FROM hoaDonThanhToan";
        return get(sql);
    }
    public Long inserHoaDonThanhToan(hoaDonObj items){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",items.getMaHoaDon());
        values.put("tongTien",items.getTongTien());
        values.put("ngayThang",items.getNgayThang());
        values.put("maDatPhong",items.getMaDatPhong());
        values.put("maChiTietDV",items.getMaChiTietDV());
        return db.insert("hoaDonThanhToan",null,values);

    }
    public int updateHoaDonThanhToan(hoaDonObj items){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",items.getMaHoaDon());
        values.put("tongTien",items.getTongTien());
        values.put("ngayThang",items.getNgayThang());
        values.put("maDatPhong",items.getMaDatPhong());
        values.put("maChiTietDV",items.getMaChiTietDV());
        return db.update("hoaDonThanhToan",values,"maHoaDon = ?"
                ,new String[]{items.getMaHoaDon()});

    }
    public int deleteHoaDonThanhToan(String maHoaDon){
        return db.delete("hoaDonThanhToan","maHoaDon = ?", new String[]{maHoaDon});
    }
    public hoaDonObj getByMaHoaDon(String maHoaDon){
        String sql = "SELECT * FROM hoaDonThanhToan WHERE maHoaDon  = ?";
        List<hoaDonObj> list = get(sql,maHoaDon);
        return list.get(0);
    }
    public hoaDonObj getByMaChiTietDV(String maChiTietDV){
        String sql = "SELECT * FROM hoaDonThanhToan WHERE maChiTietDV  = ?";
        List<hoaDonObj> list = get(sql,maChiTietDV);
        return list.get(0);
    }
    public hoaDonObj getByMaDatPhong(String maDatPhong){
        String sql = "SELECT * FROM hoaDonThanhToan WHERE maDatPhong  = ?";
        List<hoaDonObj> list = get(sql,maDatPhong);
        return list.get(0);
    }

}

package com.example.myapplication.DbManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class createDataBase extends SQLiteOpenHelper {
    public final static String DB_NAME = "LUX_FIVE_HOTEL";
    public final static int DB_VERSION = 2;

    public createDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    //maPhong
    //maTang
    //tenPhong
    //maLoai
    //trangThai
    //soPhong
    private final static String CREATE_TABLE_PHONG
            = "CREATE TABLE phong(" +
            "maPhong INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", maTang TEXT" +
            ", tenPhong TEXT" +
            ", maLoai INTEGER" +
            ", trangThai TEXT" +
            ",FOREIGN KEY (maTang) REFERENCES tang(maTang)" +
            ",FOREIGN KEY (maLoai) REFERENCES loaiPhong(maLoai) )";
    //maLoai
    //tenLoaiPhong
    //giaThue
    private final static String CREATE_TABLE_LOAIPHONG
           = "CREATE TABLE loaiPhong (" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", tenLoaiPhong TEXT)";
    //maNhanVien
    //tenNhanVien
    //anhNhanVien
    //soDienThoai
    //matKhau
    private final static String CREATE_TABLE_NHANVIEN
            = "CREATE TABLE nhanVien (" +
            "maNhanVien TEXT PRIMARY KEY" +
            ",tenNhanVien TEXT" +
            ",anhNhanVien INTEGER" +
            ",soDienThoai TEXT" +
            ",matKhau TEXT)";
    //soCMT
    //tenKh
    //ngaySinh
    //soDienThoai
    private final static String CREATE_TABLE_KHACHHANG
            = "CREATE TABLE khachHang  (" +
            "soCMT TEXT PRIMARY KEY" +
            ", tenKh TEXT" +
            ", ngaySinh TEXT" +
            ", soDienThoai TEXT)";
    //maTang
    //tenTang
    //soPhong
    private final static String CREATE_TABLE_QUANLYTANG
            = "CREATE TABLE tang(" +
            "maTang TEXT PRIMARY KEY" +
            ", tenTang TEXT UNIQUE" +
            ", soPhong TEXT )";
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
    private final static String CREATE_TABLE_DATPHONG
            = "CREATE TABLE datPhong (" +
            "maDatPhong TEXT PRIMARY KEY " +
            ", maKh TEXT" +
            ", maNhanVien INTEGER" +
            ", maPhong INTEGER" +
            ", checkIn TEXT" +
            ", checkOut TEXT" +
            ", gioVao TEXT" +
            ", gioRa TEXT" +
            ",giaThue TEXT"+
            ",soGioThue TEXT"+
            ",maChiTietDV TEXT" +
            ",trangThai TEXT"+
            ", tongTien TEXT"+
            ", YDMIN TEXT"+
            ", YDMout TEXT"+
            ",FOREIGN KEY (maChiTietDV) REFERENCES chiTietDichVu(maChiTietDV)"+
            ", FOREIGN KEY (maKh) REFERENCES khachHang(maKh)" +
            " ,FOREIGN KEY (maNhanVien) REFERENCES nhanVien(maNhanVien)" +
            " ,FOREIGN KEY (maPhong) REFERENCES phong(maPhong))";
    //maDichVu
    //tenDichVu
    //giaDichVu
    //
    //soLuong
    private final static String CREATE_TABLE_DICHVUTHEM
            = "CREATE TABLE dichVuThem (" +
            "maDichVu INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", tenDichVu TEXT UNIQUE)";
    //maHoaDon
    //maDatPhong
    //trangThai
    //tongTien
    //ngayThang
    //maChiTietDV
    private final static String CREATE_TABLE_HOADONTHANHTOAN
            = "CREATE TABLE hoaDonThanhToan(" +
            "maHoaDon INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", maDatPhong TEXT" +
            ", tongTien DOUBLE" +
            ", ngayThang TEXT" +
            ", maChiTietDV TEXT" +
            ", FOREIGN KEY (maDatPhong) REFERENCES datPhong(maDatPhong)" +
            ", FOREIGN KEY (maChiTietDV) REFERENCES chiTietDichVu(maChiTietDV))";
    //maChiTietDV
    //maDichVu
    //maDatPhong
    //giaTien
    // soluong
    private final static String CREATE_TABLE_CHITIETDICHVU
            = "CREATE TABLE chiTietDichVu (" +
            " maChiTietDV INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", maDichVu INTEGER" +
            ", maDatPhong TEXT" +
            ", soLuong INT"+
            ", giaTien DOUBLE"
            +",tongTien DOUBLE"+
            ", FOREIGN KEY(maDichVu) REFERENCES dichVuThem(maDichVu)" +
            ", FOREIGN KEY(maDatPhong) REFERENCES datPhong(maDatPhong))";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CHITIETDICHVU);
        db.execSQL(CREATE_TABLE_HOADONTHANHTOAN);
        db.execSQL(CREATE_TABLE_DICHVUTHEM);
        db.execSQL(CREATE_TABLE_DATPHONG);
        db.execSQL(CREATE_TABLE_QUANLYTANG);
        db.execSQL(CREATE_TABLE_KHACHHANG);
        db.execSQL(CREATE_TABLE_NHANVIEN);
        db.execSQL(CREATE_TABLE_LOAIPHONG);
        db.execSQL(CREATE_TABLE_PHONG);







    }
    public static final String DROP_TABLE_CHITIETDICHVU =
            "DROP TABLE IF EXISTS chiTietDichVu";
    public static final String DROP_TABLE_HOADONTHANHTOAN =
            "DROP TABLE IF EXISTS hoaDonThanhToan";
    public static final String DROP_TABLE_DICHVUTHEM =
            "DROP TABLE IF EXISTS dichVuThem";
    public static final String DROP_TABLE_DATPHONG =
            "DROP TABLE IF EXISTS datPhong";
    public static final String DROP_TABLE_QUANLYTANG =
            "DROP TABLE IF EXISTS tang";
    public static final String DROP_TABLE_KHACHHANG =
            "DROP TABLE IF EXISTS khacHang";
    public static final String DROP_TABLE_NHANVIEN =
            "DROP TABLE IF EXISTS nhanVien";
    public static final String DROP_TABLE_LOAIPHONG =
            "DROP TABLE IF EXISTS loaiPhong";
    public static final String DROP_TABLE_PHONG =
            "DROP TABLE IF EXISTS phong";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_CHITIETDICHVU);
        db.execSQL(DROP_TABLE_HOADONTHANHTOAN);
        db.execSQL(DROP_TABLE_DICHVUTHEM);
        db.execSQL(DROP_TABLE_DATPHONG);
        db.execSQL(DROP_TABLE_QUANLYTANG);
        db.execSQL(DROP_TABLE_KHACHHANG);
        db.execSQL(DROP_TABLE_NHANVIEN);
        db.execSQL(DROP_TABLE_LOAIPHONG);
        db.execSQL(DROP_TABLE_PHONG);
        onCreate(db);

    }
}

package com.example.myapplication.InterfaceManager;

import com.example.myapplication.ObjectManager.chiTietDichVu;
import com.example.myapplication.ObjectManager.datPhong;
import com.example.myapplication.ObjectManager.dichVu;
import com.example.myapplication.ObjectManager.hoaDon;
import com.example.myapplication.ObjectManager.khachHang;
import com.example.myapplication.ObjectManager.loaiPhong;
import com.example.myapplication.ObjectManager.nhanVien;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.ObjectManager.tang;

public interface sendData {
    void sendChiTietDichVu(chiTietDichVu items);
    void sendDatPhong(datPhong items);
    void sendDichVu(dichVu items);
    void sendHoaDon (hoaDon items);
    void sendKhachHang(khachHang items);
    void sendLoaiPhong (loaiPhong items);
    void sendNhanVien (nhanVien items);
    void sendPhong (phongObj items);
    void sendTang   (tang items);
}


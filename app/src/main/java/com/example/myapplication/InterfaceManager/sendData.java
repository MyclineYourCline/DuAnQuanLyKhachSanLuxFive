package com.example.myapplication.InterfaceManager;

import com.example.myapplication.ObjectManager.chiTietDichVuOBJ;
import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.dichVuObj;
import com.example.myapplication.ObjectManager.hoaDonObj;
import com.example.myapplication.ObjectManager.khachHangObj;
import com.example.myapplication.ObjectManager.loaiPhongObj;
import com.example.myapplication.ObjectManager.nhanVienObj;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.ObjectManager.tangObj;

public interface sendData {
    void sendChiTietDichVu(chiTietDichVuOBJ items);
    void sendDatPhong(datPhongObj items);
    void sendDichVu(dichVuObj items);
    void sendHoaDon (hoaDonObj items);
    void sendKhachHang(khachHangObj items);
    void sendLoaiPhong (loaiPhongObj items);
    void sendNhanVien (nhanVienObj items);
    void sendPhong (phongObj items);
    void sendTang   (tangObj items);
}


package com.example.myapplication.ObjectManager;

public class dichVuObj {
    //maDichVu
    //tenDichVu
    //giaDichVu
    //maChiTietDV
    //soLuong
    private String maDichVu, tenDichVu, giaDichVu , soLuong;

    public dichVuObj(String maDichVu, String tenDichVu, String giaDichVu, String soLuong) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.giaDichVu = giaDichVu;
        this.soLuong = soLuong;
    }

    public dichVuObj(String tenDichVu, String giaDichVu, String soLuong) {
        this.tenDichVu = tenDichVu;
        this.giaDichVu = giaDichVu;
        this.soLuong = soLuong;
    }

    public dichVuObj() {
    }
    public double tinhTongTien (){
        return  Double.parseDouble(giaDichVu) * Double.parseDouble(soLuong);
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public String getGiaDichVu() {
        return giaDichVu;
    }

    public void setGiaDichVu(String giaDichVu) {
        this.giaDichVu = giaDichVu;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }
}

package com.example.myapplication.ObjectManager;

public class datPhongObj {
    //maDatPhong
    //maKh
    //maNhanVien
    //maPhong
    //
    //ghiChu
    //checkIn
    //checkOut
    //maChiTietDV
    //gioVao
    //gioRa
    private String maDatPhong, maKh,maNhanVien, maPhong,checkIn,
            gioVao,ghiChu;

    public datPhongObj(String maDatPhong, String maKh, String maNhanVien
            , String maPhong, String checkIn, String gioVao, String ghiChu) {
        this.maDatPhong = maDatPhong;
        this.maKh = maKh;
        this.maNhanVien = maNhanVien;
        this.maPhong = maPhong;
        this.checkIn = checkIn;
        this.gioVao = gioVao;
        this.ghiChu = ghiChu;
    }
    public String setGioRa(){

        //todo....
        return gioVao;
    }
    public String tongGioThue(){
        String result = "";
        //todo
        return result;
    }
    public String checkOut( ){
        String result = "";
        //todo
        return result;
    }

    public datPhongObj() {
    }

    public String getMaDatPhong() {
        return maDatPhong;
    }

    public void setMaDatPhong(String maDatPhong) {
        this.maDatPhong = maDatPhong;
    }

    public String getMaKh() {
        return maKh;
    }

    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getGioVao() {
        return gioVao;
    }

    public void setGioVao(String gioVao) {
        this.gioVao = gioVao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}

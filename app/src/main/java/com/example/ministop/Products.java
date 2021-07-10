package com.example.ministop;

import java.io.Serializable;

public class Products implements Serializable {
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    String ma;
    String ten;
    String mota;
    String hinh;

    public Products(String ma, String ten, String mota, String hinh) {
        this.ma = ma;
        this.ten = ten;
        this.mota = mota;
        this.hinh = hinh;
    }
}

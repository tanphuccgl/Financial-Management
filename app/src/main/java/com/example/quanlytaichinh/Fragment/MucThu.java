package com.example.quanlytaichinh.Fragment;

public class MucThu {
    String maloaithu;
    String tenloaithu;

    public MucThu(String maloaithu, String tenloaithu) {
        this.maloaithu = maloaithu;
        this.tenloaithu = tenloaithu;
    }

    public String getMaloaithu() {
        return maloaithu;
    }

    public void setMaloaithu(String maloaithu) {
        this.maloaithu = maloaithu;
    }

    public String getTenloaithu() {
        return tenloaithu;
    }

    public void setTenloaithu(String tenloaithu) {
        this.tenloaithu = tenloaithu;
    }
}

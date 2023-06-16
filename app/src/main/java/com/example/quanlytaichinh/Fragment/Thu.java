package com.example.quanlytaichinh.Fragment;

public class Thu {
    private int makhoanthu;
    private String tenloaithu;
    private int sotienthu;
    private String ngaythu;

    public Thu(int makhoanthu, String tenloaithu, int sotienthu, String ngaythu) {
        this.makhoanthu = makhoanthu;
        this.tenloaithu = tenloaithu;
        this.sotienthu = sotienthu;
        this.ngaythu = ngaythu;
    }

    public int getMakhoanthu() {
        return makhoanthu;
    }

    public void setMakhoanthu(int makhoanthu) {
        this.makhoanthu = makhoanthu;
    }

    public String getTenloaithu() {
        return tenloaithu;
    }

    public void setTenloaithu(String tenloaithu) {
        this.tenloaithu = tenloaithu;
    }

    public int getSotienthu() {
        return sotienthu;
    }

    public void setSotienthu(int sotienthu) {
        this.sotienthu = sotienthu;
    }

    public String getNgaythu() {
        return ngaythu;
    }

    public void setNgaythu(String ngaythu) {
        this.ngaythu = ngaythu;
    }
}

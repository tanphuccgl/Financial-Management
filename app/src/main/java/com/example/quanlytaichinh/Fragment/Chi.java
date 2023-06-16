package com.example.quanlytaichinh.Fragment;

public class Chi {
    private int makhoanchi;
    private String tenloaichi;
    private int sotienchi;
    private String ngaychi;

    public Chi(int makhoanchi, String tenloaichi, int sotienchi, String ngaychi) {
        this.makhoanchi = makhoanchi;
        this.tenloaichi = tenloaichi;
        this.sotienchi = sotienchi;
        this.ngaychi = ngaychi;
    }

    public int getMakhoanchi() {
        return makhoanchi;
    }

    public void setMakhoanchi(int makhoanchi) {
        this.makhoanchi = makhoanchi;
    }

    public String getTenloaichi() {
        return tenloaichi;
    }

    public void setTenloaichi(String tenloaichi) {
        this.tenloaichi = tenloaichi;
    }

    public int getSotienchi() {
        return sotienchi;
    }

    public void setSotienchi(int sotienchi) {
        this.sotienchi = sotienchi;
    }

    public String getNgaychi() {
        return ngaychi;
    }

    public void setNgaychi(String ngaychi) {
        this.ngaychi = ngaychi;
    }
}
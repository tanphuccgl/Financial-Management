package com.example.quanlytaichinh.ThongKe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter_ThongKe extends FragmentStatePagerAdapter {
    public PagerAdapter_ThongKe(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:fragment=new Fragment_ngay_nay();
                break;
            case 1:fragment=new Fragment_thang_nay();
                break;
            case 2:fragment=new Fragment_nam_nay();
                break;
            case 3:fragment=new Fragment_tuy_chon();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Ngày này";
                break;
            case 1:
                title = "Tháng này";
                break;
            case 2:
                title = "Năm này";
                break;
            case 3:
                title = "Tùy chọn";
                break;
        }
        return title;
    }
}

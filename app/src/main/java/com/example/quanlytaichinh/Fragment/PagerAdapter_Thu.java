package com.example.quanlytaichinh.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter_Thu extends FragmentStatePagerAdapter {
    public PagerAdapter_Thu(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:fragment=new Fragment_Thu();
                break;
            case 1:fragment=new Fragment_Muc_Thu();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Quản lý Thu";
                break;
            case 1:
                title = "Danh mục thu";
                break;
        }
        return title;
    }
}

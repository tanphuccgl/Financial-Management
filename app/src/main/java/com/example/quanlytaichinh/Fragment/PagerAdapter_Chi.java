package com.example.quanlytaichinh.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter_Chi extends FragmentStatePagerAdapter {
    public PagerAdapter_Chi(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:fragment=new Fragment_Chi();
            break;
            case 1:fragment=new Fragment_Muc_Chi();
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
                title = "Quản lý chi";
                break;
            case 1:
                title = "Danh mục chi";
                break;
        }
        return title;
    }
}

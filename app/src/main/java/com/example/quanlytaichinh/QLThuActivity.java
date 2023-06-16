package com.example.quanlytaichinh;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlytaichinh.Fragment.PagerAdapter_Chi;
import com.example.quanlytaichinh.Fragment.PagerAdapter_Thu;

public class QLThuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String TenDangNhap;
    private ViewPager pager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlthu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addControlChi();

        Intent it = this.getIntent();
        TenDangNhap = it.getStringExtra("TenDangNhap");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View v = navigationView.getHeaderView(0);
        TextView tvTenDangNhap = (TextView) v.findViewById(R.id.tvTenDangNhap);
        tvTenDangNhap.setText("Xin chào : " + TenDangNhap);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qlthu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_QuanLyChi) {
            Intent it = new Intent(QLThuActivity.this,QLThuChiActivity.class);
            it.putExtra("TenDangNhap",TenDangNhap);
            startActivity(it);
            overridePendingTransition(R.anim.anim_enter,R.anim.anim_dira);

        } else if (id == R.id.nav_QuanLyThu) {

        } else if (id == R.id.nav_ThongKe) {
            Intent it = new Intent(QLThuActivity.this,ThongKeActivity.class);
            it.putExtra("TenDangNhap",TenDangNhap);
            startActivity(it);
            overridePendingTransition(R.anim.anim_enter,R.anim.anim_dira);

        } else if (id == R.id.nav_share) {
            Intent it = new Intent(QLThuActivity.this, DoiMatKhauActivity.class);
            startActivity(it);
            overridePendingTransition(R.anim.anim_enter,R.anim.anim_dira);

        } else if (id == R.id.nav_thongtin) {
            Intent it = new Intent(QLThuActivity.this, aboutActivity.class);
            startActivity(it);
            overridePendingTransition(R.anim.anim_enter,R.anim.anim_dira);

        } else if (id == R.id.nav_Thoat) {
            thoat();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void thoat() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        // Tao su kien ket thuc app
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startActivity(startMain);
        finish();
    }

    private void addControlChi() {
        pager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter_Thu adapter = new PagerAdapter_Thu(manager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);//deprecated
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
    }
}

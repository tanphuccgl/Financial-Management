package com.example.quanlytaichinh;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {
    PieChart chart;
    int tongthu;
    int tongchi;
    TextView tvTongChi,tvTongThu;
    public ImageButton imvThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Intent intent = getIntent();
        tongthu = intent.getIntExtra("TongThu",0);
        tongchi = intent.getIntExtra("TongChi",0);

        imvThoat=findViewById(R.id.imvThoat);
        tvTongChi=findViewById(R.id.tvTongchi);
        tvTongThu=findViewById(R.id.tvTongthu);

        tvTongChi.setText("Tổng Chi : "+String.valueOf(tongchi)+" VND");
        tvTongThu.setText("Tổng Thu : "+String.valueOf(tongthu)+" VND");

        imvThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ChartActivity.super.onBackPressed();
            }
        });

        chart =(PieChart) findViewById(R.id.chart);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5,5,5,5);
        chart.setDragDecelerationFrictionCoef(0.99f);

        chart.setDrawHoleEnabled(false);
        chart.setHoleColor(Color.WHITE);
        chart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yvalue = new ArrayList<>();
        yvalue.clear();
        yvalue.add(new PieEntry(tongchi,"Tổng chi"));
        yvalue.add(new PieEntry(tongthu,"Tổng thu"));

        chart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yvalue," ");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLUE);

        chart.setData(data);
    }
}

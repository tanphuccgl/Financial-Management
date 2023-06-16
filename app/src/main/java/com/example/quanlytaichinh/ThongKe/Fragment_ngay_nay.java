package com.example.quanlytaichinh.ThongKe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlytaichinh.ChartActivity;
import com.example.quanlytaichinh.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_ngay_nay extends Fragment {
    String url="http://10.0.3.2:8080/androidwebservice/getdataNgayChi.php";
    String urls ="http://10.0.3.2:8080/androidwebservice/getdataNgayThu.php";
    public String TenDangNhap;
    private TextView tvTongChi,tvTongThu;
    public ImageButton btChart;
    public int tongchi,tongthu,sodu;
    ArrayList<ttChi> arrayChi;
    ArrayList<ttThu> arrayThu;

    TTThuAdapter thuadapter;
    TTChiAdapter chiadapter;

    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_ngay_nay,null);

        final Intent intent = getActivity().getIntent();
        TenDangNhap = intent.getStringExtra("TenDangNhap");

        ListView lvChi = (ListView) view.findViewById(R.id.lv1);
        ListView lvThu = (ListView) view.findViewById(R.id.lv2);
        btChart= (ImageButton) view.findViewById(R.id.btChart);

        arrayChi = new ArrayList<>();
        arrayThu = new ArrayList<>();
        chiadapter = new TTChiAdapter(getActivity(),R.layout.custom_lv_thong_ke,arrayChi);
        thuadapter = new TTThuAdapter(getActivity(),R.layout.custom_lv_thong_ke,arrayThu);
        lvChi.setAdapter(chiadapter);
        lvThu.setAdapter(thuadapter);
        getchi(url);
        getthu(urls);


        tvTongChi=view.findViewById(R.id.tvTongChi);
        tvTongThu=view.findViewById(R.id.tvTongThu);

        btChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ichart= new Intent(getActivity(), ChartActivity.class);
                ichart.putExtra("TongThu",tongthu);
                ichart.putExtra("TongChi",tongchi);
                startActivity(ichart);
            }
        });
        return view;
    }

    private void getchi(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
//                    Toast.makeText(getActivity(),array.toString(),Toast.LENGTH_LONG).show();
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);
                        tongchi=tongchi+object.getInt("SoTienChi");
                        arrayChi.add(new ttChi(object.getString("TenLoaiChi"), object.getInt("SoTienChi")));
                    }
                    tvTongChi.setText("Tổng Chi : "+String.valueOf(tongchi)+" VND");
                    chiadapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Loi ket noi", Toast.LENGTH_SHORT).show();
            }

        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void getthu(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
//                    Toast.makeText(getActivity(),array.toString(),Toast.LENGTH_LONG).show();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        tongthu=tongthu+object.getInt("SoTienThu");
                        arrayThu.add(new ttThu(object.getString("TenLoaiThu"), object.getInt("SoTienThu")));
                    }
                    tvTongThu.setText("Tổng Thu : "+String.valueOf(tongthu)+" VND");
                    thuadapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Loi ket noi", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}

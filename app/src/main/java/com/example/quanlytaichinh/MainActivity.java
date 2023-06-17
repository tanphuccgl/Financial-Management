package com.example.quanlytaichinh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btDangKy,btDangNhap;
    EditText edtdn, edDiaChi, edht;
    Button btQuenMk;
    EditText edTenDangNhap, edMatKhau;
    public int dem = 0;

    String url = "http://192.168.1.206/androidwebservice/getdatauser.php";

    String urlmk = "http://10.0.3.2:8080/androidwebservice/laymatkhau.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        btDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KiemTraUser();
            }
        });

        edMatKhau.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    KiemTraUser();
                }
                return false;
            }
        });

        btDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, DangKyActivity.class);
                startActivity(it);
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_dira);
            }
        });

        btQuenMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

    }

    public void dialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.lay_lai_mat_khau, null);

        edtdn = alertLayout.findViewById(R.id.edTenDangNhapl);
        edht = alertLayout.findViewById(R.id.edHoTen);
        edDiaChi = alertLayout.findViewById(R.id.edDiaChi);
        Button btLayMK = alertLayout.findViewById(R.id.btLayMK);
        Button btHuy = alertLayout.findViewById(R.id.btHuy);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        final AlertDialog dialog = alert.create();
        dialog.show();

        btLayMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tdnl = edtdn.getText().toString().trim();
                String ht = edht.getText().toString().trim();
                String dc = edDiaChi.getText().toString().trim();
                if (tdnl.isEmpty() || ht.isEmpty() || dc.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Không được bỏ trống !", Toast.LENGTH_LONG).show();
                } else {
                    laymatkhau(urlmk);
                    dialog.cancel();
                }

            }
        });


        btHuy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });

    }

    private void laymatkhau(final String url) {
        final String tdnl = edtdn.getText().toString().trim();
        final String ht = edht.getText().toString().trim();
        final String dc = edDiaChi.getText().toString().trim();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    //  Toast.makeText(getActivity(),array.toString(),Toast.LENGTH_LONG).show();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        Toast.makeText(MainActivity.this, "Mật khẩu là : " + object.getString("MatKhau"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Xay Ra Loi", Toast.LENGTH_LONG).show();
                Log.d("LAYMK", "loi\n" + error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", tdnl);
                params.put("HoTen", ht);
                params.put("DiaChi", dc);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    public void KiemTraUser() {
        final String tdn = edTenDangNhap.getText().toString();
        final String mk = edMatKhau.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        if (tdn.isEmpty() || mk.isEmpty()) {
            Toast.makeText(MainActivity.this, "Các mục đang trống", Toast.LENGTH_SHORT).show();
        } else {

// Tạo Uri.Builder để xây dựng URL với các tham số
            url = url+"?TenDangNhap=" + tdn + "&MatKhau=" + mk;



            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Xử lý dữ liệu JSON nhận được từ máy chủ
                            try {

                                JSONObject jsonObject = new JSONObject(response.toString());

                                // Truy cập các thuộc tính trong đối tượng JSONObject
                                String tendangnhap = jsonObject.getString("tendangnhap");
                                String matkhau = jsonObject.getString("matkhau");

                                // In ra các giá trị
                                System.out.println("levi12: " + tendangnhap);
                                System.out.println("levi13: " + matkhau);


                                System. out. println("levi" +  response.toString());
                                String user = response.getString("tendangnhap");
                                String password = response.getString("matkhau");

                                // Hiển thị dữ liệu
                                Log.d("User", user);
                                Log.d("Password", password);

//                                    Toast.makeText(MainActivity.this,object.getString("TenDangNhap"),Toast.LENGTH_LONG).show();
                                if (tdn.equals(user) && mk.equals(password)) {
                                    dem = dem + 1;
                                    Intent it = new Intent(MainActivity.this, ThongKeActivity.class);
                                    String tdni = user;
                                    it.putExtra("tendangnhap", tdni);
                                    startActivity(it);
                                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_dira);
                                }

                            } catch (JSONException e) {
                                System. out. println("levi" +  response.toString());
                                e.printStackTrace();
                            }
                            if (dem == 0) {
                                edMatKhau.setError("Sai Tên Dăng nhâp hoặc mật khẩu");
                                //Toast.makeText(MainActivity.this, "Sai ten dang nhap hoac mat khau", Toast.LENGTH_LONG).show();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System. out. println("levi" +  error.toString());
                            // Xử lý lỗi
                            error.printStackTrace();
                        }
                    });


//            jsonArrayRequest.setRetryPolicy(new RetryPolicy() {
//                @Override
//                public int getCurrentTimeout() {
//                    return 5000;
//                }
//
//                @Override
//                public int getCurrentRetryCount() {
//                    return 5000;
//                }
//
//                @Override
//                public void retry(VolleyError error) throws VolleyError {
//
//                }
//            });
            requestQueue.add(jsonObjectRequest);
        }
    }


    public void AnhXa() {
        edTenDangNhap = (EditText) findViewById(R.id.edTenDangNhap);
        edMatKhau = (EditText) findViewById(R.id.edMatKhau);
        btDangNhap = (Button) findViewById(R.id.btDangNhap);
        btDangKy = (Button) findViewById(R.id.btDangKy);
        btQuenMk = (Button) findViewById(R.id.btQuenMk);

    }
}

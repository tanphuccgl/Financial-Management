package com.example.quanlytaichinh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {

    EditText edTenDangNhap,edMatKhau,edHoTen,edDiaChi;
    //    RadioButton rbNam,rbNu;
    Button btDangKy,btHuy;

    String url = "http://10.0.3.2:8080/androidwebservice/insertUser.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        AnhXa();

        btDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tdn = edTenDangNhap.getText().toString().trim();
                String mk = edMatKhau.getText().toString().trim();
                String ht = edHoTen.getText().toString().trim();
                String dc = edDiaChi.getText().toString().trim();
                if(tdn.isEmpty()||mk.isEmpty()||ht.isEmpty()||dc.isEmpty()){
                    Toast.makeText(DangKyActivity.this,"Khong duoc bo trong",Toast.LENGTH_LONG).show();
                }else{
                    ThemUser(url);
                }

            }
        });

        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void ThemUser(final String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")){
                    showToast("Đăng ký thành công");
//                    Toast.makeText(DangKyActivity.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DangKyActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(DangKyActivity.this,"Loi dang ky "+response,Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DangKyActivity.this,"Xay Ra Loi",Toast.LENGTH_LONG).show();
                Log.d("AAA","loi\n"+error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("TenDangNhap",edTenDangNhap.getText().toString().trim());
                params.put("MatKhau",edMatKhau.getText().toString().trim());
                params.put("HoTen",edHoTen.getText().toString().trim());
                params.put("DiaChi",edHoTen.getText().toString().trim());

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }
    public  void AnhXa(){
        edTenDangNhap= (EditText)findViewById(R.id.edTenDangNhap);
        edMatKhau= (EditText)findViewById(R.id.edMatKhau);
        edHoTen= (EditText)findViewById(R.id.edHoTen);
        edDiaChi= (EditText)findViewById(R.id.edDiaChi);
        btDangKy = (Button) findViewById(R.id.btDangKy);
        btHuy = (Button) findViewById(R.id.btHuy);
    }
    public  void  showToast(String show){
        LayoutInflater inflater1 = getLayoutInflater();
        View layout = inflater1.inflate(R.layout.them_thanh_cong, (ViewGroup)findViewById(R.id.toast_root));
        TextView text = (TextView) layout.findViewById(R.id.tvToast);
        text.setText(show);
        Toast toast = new Toast(getApplicationContext());
        //toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}

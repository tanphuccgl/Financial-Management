package com.example.quanlytaichinh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class DoiMatKhauActivity extends AppCompatActivity {
    ImageButton ibexit;
    Button btDoiMatKhau;
    EditText edTenDangNhap,edMaKhauCu,edMatKhauMoi,edMKNhapLai;
    String tdn,mkcu,mkmoi,mknhaplai;
    String url ="http://10.0.3.2:8080/androidwebservice/updateMatKhau.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        AnhXa();

        ibexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoiMatKhauActivity.super.onBackPressed();
            }
        });
        btDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tdn =edTenDangNhap.getText().toString().trim();
                mkcu =edMaKhauCu.getText().toString().trim();
                mkmoi =edMatKhauMoi.getText().toString().trim();
                mknhaplai =edMKNhapLai.getText().toString().trim();
               // Toast.makeText(DoiMatKhauActivity.this,tdn+mkcu+mkmoi+mknhaplai,Toast.LENGTH_SHORT).show();

                if (tdn.isEmpty()|mkcu.isEmpty()|mkmoi.isEmpty()|mknhaplai.isEmpty()){
                    showToast("Không được bỏ trống !",R.drawable.warning);
                }else {
                    if (mkcu.equals(mkmoi)){
                        showToast("Trùng với mật khẩu cũ !",R.drawable.warning);
                    }else {
                        if (mkcu.equals(mknhaplai)){
                            showToast("Mật khẩu mới không trùng nhau! !",R.drawable.warning);
                        }else {
                            doimatkhau(url);
                        }
                    }
                }
            }
        });

    }
    public  void AnhXa(){
        ibexit=findViewById(R.id.ibexit);
        btDoiMatKhau=findViewById(R.id.btDoiMatKhau);
        edTenDangNhap=findViewById(R.id.edTenDangNhap);
        edMaKhauCu=findViewById(R.id.edMaKhauCu);
        edMatKhauMoi=findViewById(R.id.edMatKhauMoi);
        edMKNhapLai=findViewById(R.id.edMKNhapLai);
    }
    private void doimatkhau(final String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")){
                    showToast("Đổi mật khẩu thành công",R.drawable.done);
//                    Toast.makeText(DoiMatKhauActivity.this,"Đổi mật khẩu thành công",Toast.LENGTH_LONG).show();
                    Intent ii = new Intent(DoiMatKhauActivity.this,MainActivity.class);
                    startActivity(ii);
                }else{
                    Toast.makeText(DoiMatKhauActivity.this,"Lỗi đôi mật khẩu  "+response,Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoiMatKhauActivity.this,"Xay Ra Loi",Toast.LENGTH_LONG).show();
                Log.d("AAA","loi\n"+error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("TenDangNhap",edTenDangNhap.getText().toString().trim());
                params.put("MatKhauCu",edMaKhauCu.getText().toString().trim());
                params.put("MatKhauMoi",edMatKhauMoi.getText().toString().trim());

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }
    public  void  showToast(String show,int src){
        LayoutInflater inflater1 = getLayoutInflater();
        View layout = inflater1.inflate(R.layout.them_thanh_cong, (ViewGroup) findViewById(R.id.toast_root));
        TextView text = (TextView) layout.findViewById(R.id.tvToast);
        ImageView imvToast = (ImageView) layout.findViewById(R.id.imvToast);
        text.setText(show);
        imvToast.setImageResource(src);
        Toast toast = new Toast(getApplicationContext());
        //toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

}

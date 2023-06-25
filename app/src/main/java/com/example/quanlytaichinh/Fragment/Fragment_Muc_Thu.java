package com.example.quanlytaichinh.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlytaichinh.QLThuActivity;
import com.example.quanlytaichinh.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Muc_Thu extends Fragment {

    String TenDangNhap;
    EditText edTenLoaiThu;
    Button btThemThu;
    ImageButton btReset;
    ListView lvMucThu1;
    ArrayList<MucThu> arrayLoaiThu;
    MucThuAdapter adapter;
    String urls = "http://192.168.1.206/androidwebservice/getdataLoaiThu.php";
    String url = "http://192.168.1.206/androidwebservice/insertLoaiThu.php";
    String urld = "http://192.168.1.206/androidwebservice/deleteLoaiThu.php";
    String urlsua = "http://192.168.1.206/androidwebservice/updateLoaiThu.php";


    public Fragment_Muc_Thu() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muc_thu, container, false);
        FloatingActionButton ftb = (FloatingActionButton) view.findViewById(R.id.fabMucThu);

        Intent intent = getActivity().getIntent();
        Context context = getActivity();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String myVariable = sharedPreferences.getString("myVariable", "");

        TenDangNhap = myVariable;

        lvMucThu1 = (ListView) view.findViewById(R.id.lvMucThu);
        arrayLoaiThu = new ArrayList<>();
        getloaithu(urls);
        adapter = new MucThuAdapter(getActivity(), R.layout.custom_lv_muc_thu, arrayLoaiThu);
        lvMucThu1.setAdapter(adapter);


        btReset=view.findViewById(R.id.btreset);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayLoaiThu.clear();
                lvMucThu1.setAdapter(adapter);
                getloaithu(urls);
            }
        });

        ftb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

        lvMucThu1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MucThu mucThu = arrayLoaiThu.get(position);
                final String loaithu = mucThu.getTenloaithu();


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Xóa mục thu");
                builder.setMessage("Bạn chắc chắn muốn xóa không ?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        XoaLoaiThu(urld,loaithu);
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return false;
            }
        });

        lvMucThu1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MucThu mucthu = arrayLoaiThu.get(position);
                final String mlt = mucthu.getMaloaithu();
                final String tlt = mucthu.getTenloaithu();

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Sửa loại chi");
                builder.setMessage("Bạn chắc chắn muốn sửa không ?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialogsua(mlt,tlt);
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return view;
    }

    public void dialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_muc_thu, null);
        edTenLoaiThu = (EditText) alertLayout.findViewById(R.id.edTenLoaiThu);
        btThemThu = (Button) alertLayout.findViewById(R.id.btThemThu);
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(alertLayout);
        alert.setTitle("Thêm mục thu ");
        final AlertDialog dialog = alert.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_lv);
        btThemThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), TenDangNhap + " : " + edTenLoaiChi.getText().toString(), Toast.LENGTH_LONG).show();
                ThemLoaiThu(url);
                dialog.cancel();
            }
        });


    }

    public void dialogsua(final String mlt,final String tlt) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_muc_thu, null);
        edTenLoaiThu = (EditText) alertLayout.findViewById(R.id.edTenLoaiThu);
        Button btThemthu = (Button) alertLayout.findViewById(R.id.btThemThu);
        edTenLoaiThu.setText(tlt);

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(alertLayout);
        alert.setTitle("Sửa mục thu ");
        final AlertDialog dialog = alert.create();
        dialog.show();

        btThemthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaLoaithu(urlsua,mlt,edTenLoaiThu.getText().toString().trim());
//                Toast.makeText(getActivity(), TenDangNhap + " : " + edTenLoaiChi.getText().toString(), Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

    }

    private void SuaLoaithu( String url,final String mlt,final  String tltmoi){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        url = url+"?TenDangNhap=" + TenDangNhap + "&MaLoaiThu=" + mlt+ "&TenLoaiThu=" + tltmoi;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")) {
                    showToast("Sửa thành công ");
                } else {
                    Toast.makeText(getActivity(), "Lỗi dữ liệu !" + response, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Xảy ra Lỗi", Toast.LENGTH_LONG).show();
                Log.d("AAA", "loi\n" + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());
                params.put("MaLoaiThu", mlt.trim());
                params.put("TenLoaiThu", tltmoi);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    private void ThemLoaiThu( String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        url = url+"?TenDangNhap=" + TenDangNhap + "&TenLoaiThu=" + edTenLoaiThu.getText().toString();
        System.out.println("url??? " + url.toString());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")) {
                    showToast("Thêm thành công ");

                } else {
                    Toast.makeText(getActivity(), "Dữ liệu nhập vào đã tồn tại !" + response, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Xay Ra Loi", Toast.LENGTH_LONG).show();
                Log.d("AAA", "loi\n" + error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());
                params.put("TenLoaiThu", edTenLoaiThu.getText().toString().trim());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    private void getloaithu(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        url = url+"?TenDangNhap=" + TenDangNhap ;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        arrayLoaiThu.add(new MucThu(object.getString("maloaithu"),object.getString("tenloaithu")));

                    }
                    System.out.println("urrl"+arrayLoaiThu.toString());

                    adapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    Log.d("Error quan ly thu", e.toString());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error quan ly thu api", error.toString());
                error.printStackTrace();
            }


        }


        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());

                return params;
            }
        };

        requestQueue.add(jsonArrayRequest);


    }

    private void XoaLoaiThu( String url, final String tenloaithu) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        url = url+"?TenDangNhap=" + TenDangNhap + "&TenLoaiThu=" + tenloaithu;
        System.out.println("url123??? " + url.toString());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")) {
                    showToast(" Xóa thành công ");
                } else {
                    Toast.makeText(getActivity(), "Dữ liệu xóa không hợp lê !" + response, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Xay Ra Loi", Toast.LENGTH_LONG).show();
                Log.d("AAA", "loi\n" + error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenLoaiThu", tenloaithu);
                params.put("TenDangNhap", TenDangNhap.trim());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    public  void  showToast(String show){
        LayoutInflater inflater1 = getActivity().getLayoutInflater();
        View layout = inflater1.inflate(R.layout.them_thanh_cong, (ViewGroup) getActivity().findViewById(R.id.toast_root));
        TextView text = (TextView) layout.findViewById(R.id.tvToast);
        text.setText(show);
        Toast toast = new Toast(getActivity().getApplicationContext());
        //toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


}

package com.example.quanlytaichinh.ThongKe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlytaichinh.Fragment.Chi;
import com.example.quanlytaichinh.Fragment.ChiAdapter;
import com.example.quanlytaichinh.R;

import java.util.List;

public class TTChiAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ttChi> listChi;

    public TTChiAdapter(Context context, int layout, List<ttChi> listChi) {
        this.context = context;
        this.layout = layout;
        this.listChi = listChi;
    }

    @Override
    public int getCount() {
        return listChi.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder {
        TextView tvTenLoaiChi,tvSoTienChi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new TTChiAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.tvTenLoaiChi = convertView.findViewById(R.id.tvTen);
            viewHolder.tvSoTienChi = convertView.findViewById(R.id.tvSoTien);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (TTChiAdapter.ViewHolder) convertView.getTag();
        }

        ttChi chi = listChi.get(position);
        viewHolder.tvTenLoaiChi.setText(chi.getTenloaichi());
        viewHolder.tvSoTienChi.setText(String.valueOf(chi.getSotienchi())+" VND ");

        return convertView;
    }
}

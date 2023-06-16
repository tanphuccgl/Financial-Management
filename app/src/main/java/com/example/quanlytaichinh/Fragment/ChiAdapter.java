package com.example.quanlytaichinh.Fragment;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlytaichinh.R;

import java.util.List;

public class ChiAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Chi> listChi;
    public ChiAdapter(Context context, int layout, List<Chi> listChi) {
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

    private class ViewHolder {
        TextView tvTenLoaiChi,tvNgayChi,tvSoTienChi;
        ImageView imvsua,imvxoa;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ChiAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.tvTenLoaiChi = convertView.findViewById(R.id.tvTenLoaiChi);
            viewHolder.tvNgayChi = convertView.findViewById(R.id.tvNgayChi);
            viewHolder.tvSoTienChi = convertView.findViewById(R.id.tvSoTienChi);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Chi chi = listChi.get(position);
        viewHolder.tvTenLoaiChi.setText(chi.getTenloaichi());
        viewHolder.tvSoTienChi.setText(String.valueOf(chi.getSotienchi())+" VND ");
        viewHolder.tvNgayChi.setText(chi.getNgaychi());

        return convertView;
    }
}

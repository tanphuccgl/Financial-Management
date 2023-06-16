package com.example.quanlytaichinh.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlytaichinh.R;

import java.util.List;

public class MucChiAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MucChi> listMucChi;

    public MucChiAdapter(Context context, int layout, List<MucChi> listMucChi) {
        this.context = context;
        this.layout = layout;
        this.listMucChi = listMucChi;
    }

    @Override
    public int getCount() {
        return listMucChi.size();
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
        TextView tvTenLoaiChi;
        ImageView imvxoa, imvsua;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.tvTenLoaiChi = convertView.findViewById(R.id.tvTenLoaiChi);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MucChi mucchi = listMucChi.get(position);
        viewHolder.tvTenLoaiChi.setText(mucchi.getTenloaichi());

        return convertView;
    }
}

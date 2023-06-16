package com.example.quanlytaichinh.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlytaichinh.R;

import java.util.List;

public class MucThuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MucThu> listMucThu;

    public MucThuAdapter(Context context, int layout, List<MucThu> listMucThu) {
        this.context = context;
        this.layout = layout;
        this.listMucThu = listMucThu;
    }

    @Override
    public int getCount() {
        return listMucThu.size();
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
        TextView tvTenLoaiThu;
        ImageView imvxoa, imvsua;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.tvTenLoaiThu = convertView.findViewById(R.id.tvTenLoaiThu);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MucThu mucthu = listMucThu.get(position);
        viewHolder.tvTenLoaiThu.setText(mucthu.getTenloaithu());

        return convertView;
    }
}

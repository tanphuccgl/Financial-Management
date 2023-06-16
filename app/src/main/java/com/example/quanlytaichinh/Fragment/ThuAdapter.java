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

public class ThuAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Thu> listThu;

    public ThuAdapter(Context context, int layout, List<Thu> listThu) {
        this.context = context;
        this.layout = layout;
        this.listThu = listThu;
    }

    @Override
    public int getCount() {
        return listThu.size();
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
        TextView tvTenLoaiThu,tvNgayThu,tvSoTienThu;
        ImageView imvsua,imvxoa;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ThuAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.tvTenLoaiThu = convertView.findViewById(R.id.tvTenLoaiThu);
            viewHolder.tvNgayThu = convertView.findViewById(R.id.tvNgayThu);
            viewHolder.tvSoTienThu = convertView.findViewById(R.id.tvSoTienThu);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Thu thut = listThu.get(position);
        viewHolder.tvTenLoaiThu.setText(thut.getTenloaithu());
        viewHolder.tvSoTienThu.setText(String.valueOf(thut.getSotienthu())+" VND");
        viewHolder.tvNgayThu.setText(thut.getNgaythu());

        return convertView;
    }
}

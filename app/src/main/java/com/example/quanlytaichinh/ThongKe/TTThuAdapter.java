package com.example.quanlytaichinh.ThongKe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlytaichinh.Fragment.Thu;
import com.example.quanlytaichinh.R;

import java.util.List;

public class TTThuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ttThu> listThu;

    public TTThuAdapter(Context context, int layout, List<ttThu> listThu) {
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
        TextView tvTenLoaiThu,tvSoTienThu;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new TTThuAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.tvTenLoaiThu = convertView.findViewById(R.id.tvTen);
            viewHolder.tvSoTienThu = convertView.findViewById(R.id.tvSoTien);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ttThu thu = listThu.get(position);
        viewHolder.tvTenLoaiThu.setText(thu.getTenloaithu());
        viewHolder.tvSoTienThu.setText(String.valueOf(thu.getSotienthu())+" VND ");

        return convertView;
    }
}

package com.example.poetryline.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.poetryline.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter1 extends BaseAdapter {
    private List<Gushi> gushilist;
    private int itemLayoutId;
    private Context context;
    private ViewHolder viewHolder;


    public CustomAdapter1(Context context, List<Gushi> obj, int itemLayoutId) {

        this.context = context;
        this.gushilist = obj;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        if (null != gushilist) {
            return gushilist.size();
        } else {
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        if (null != gushilist) {
            return gushilist.get(position);
        } else {
            return null;
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.gushi_item, null);
            viewHolder= new ViewHolder();
            viewHolder.gushiTitle = convertView.findViewById(R.id.item_1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.gushiTitle.setText(gushilist.get(position).getTitle());
        return convertView;
    }

    static class ViewHolder{
        TextView gushiTitle;

    }
}

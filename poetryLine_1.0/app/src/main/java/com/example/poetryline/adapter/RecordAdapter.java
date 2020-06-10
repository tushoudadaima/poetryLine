package com.example.poetryline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.poetryline.R;
import com.example.poetryline.entity.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends BaseAdapter {
    private Context context;
    private List<Record> dataSource = new ArrayList<>();
    private int item_layout_id;

    public RecordAdapter(Context context, List<Record> dataSource, int item_layout_id) {
        this.context = context;
        this.dataSource = dataSource;
        this.item_layout_id = item_layout_id;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(item_layout_id,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_time = view.findViewById(R.id.tv_time);
            viewHolder.tv_type = view.findViewById(R.id.tv_type);
            viewHolder.tv_scores = view.findViewById(R.id.tv_scores);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_time.setText(dataSource.get(position).getDate());
        viewHolder.tv_type.setText(dataSource.get(position).getType());
        viewHolder.tv_scores.setText(dataSource.get(position).getScore()+"分");
        return view;
    }

    public static class ViewHolder{
        private TextView tv_time;
        private TextView tv_type;
        private TextView tv_scores;
    }
}

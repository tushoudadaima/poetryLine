package com.example.poetryline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.poetryline.R;
import com.example.poetryline.entity.Poetry;

import java.util.ArrayList;
import java.util.List;

public class ShowAdapter extends BaseAdapter {
    private Context context;
    private List<Poetry> dataSource = new ArrayList<>();
    private int item_layout_id;

    public ShowAdapter(Context context, List<Poetry> dataSource, int item_layout_id) {
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
            viewHolder.item_title = view.findViewById(R.id.item_title);
            viewHolder.item_from = view.findViewById(R.id.item_from);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.item_title.setText(dataSource.get(position).getTitle());
        viewHolder.item_from.setText(dataSource.get(position).getDynasty()+"-"+dataSource.get(position).getAuthor());
        return view;
    }

    public static class ViewHolder{
        private TextView item_title;
        private TextView item_from;
    }
}

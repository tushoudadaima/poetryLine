package com.example.poetryline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poetryline.R;
import com.example.poetryline.entity.CommunityItem;

import java.util.ArrayList;
import java.util.List;

public class CommunityAdapter extends BaseAdapter {

    private List<CommunityItem> communityitems = new ArrayList<>();
    private int itemLayoutId;
    private Context context;


    public CommunityAdapter(Context context, List<CommunityItem> communityitems, int itemLayoutId) {
        this.context = context;
        this.communityitems = communityitems;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return communityitems.size();
    }
    @Override
    public Object getItem(int i) {
        return  communityitems.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(itemLayoutId, null);
        }

        ImageView touxiang = convertView.findViewById(R.id.community_img_touxiang);
        TextView name = convertView.findViewById(R.id.community_name);
        TextView body = convertView.findViewById(R.id.community_body);
        ImageView peitu = convertView.findViewById(R.id.community_img_peitu);

        touxiang.setImageResource(communityitems.get(position).getImgtxSource());
        name.setText(communityitems.get(position).getName());
        body.setText(communityitems.get(position).getBody());
        peitu.setImageResource(communityitems.get(position).getImgptSource());

        return convertView;
    }

}

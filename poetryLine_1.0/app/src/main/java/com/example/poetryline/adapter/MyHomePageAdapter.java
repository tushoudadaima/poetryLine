package com.example.poetryline.adapter;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poetryline.R;
import com.example.poetryline.detail.DetailActivity;
import com.example.poetryline.entity.HomePagePoetry;
import com.example.poetryline.entity.Lauar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ONE_ITEM = 0;
    private final int TWO_ITEM = 1;
    private final int THREE_ITEM = 2;
    private String[] time;
    private List<HomePagePoetry> lists;
    private List<Typeface> typefaceList;

    //构造方法
    public MyHomePageAdapter(String[] time,List<HomePagePoetry> lists,List<Typeface> typefaceList){
        this.time = time;
        this.lists = lists;
        this.typefaceList = typefaceList;
    }

    //图片
    static class ImageHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;

        public ImageHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.poems_image);
        }
    }
    //时间
    static class TimeHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private TextView textView_two;

        public TimeHolder(View view){
            super(view);
            textView = view.findViewById(R.id.text_showTime);
            textView_two = view.findViewById(R.id.text_day);
        }
    }
    //诗句
    static class PoetryHolder extends RecyclerView.ViewHolder{
        View poetryView;
        private TextView items_title;
        private TextView items_dyAndAuthor;
        private TextView items_context;

        public PoetryHolder(View view){
            super(view);
            poetryView = view;
            items_title = view.findViewById(R.id.item_title);
            items_dyAndAuthor = view.findViewById(R.id.item_dyAndAuthor);
            items_context = view.findViewById(R.id.item_context);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case ONE_ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poems_image_item,parent,false);
                ImageHolder imageHolder = new ImageHolder(view);
                return imageHolder;
            case TWO_ITEM:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.poems_data_item,parent,false);
                TimeHolder timeHolder = new TimeHolder(view1);
                return timeHolder;
            case THREE_ITEM:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.poems_item,parent,false);
                PoetryHolder poetryHolder = new PoetryHolder(view2);
                poetryHolder.poetryView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = poetryHolder.getAdapterPosition();
                        HomePagePoetry homePagePoetry = lists.get(position);
                        String context = homePagePoetry.getTitle();
                        Intent intent = new Intent(view.getContext(), DetailActivity.class);
                        intent.putExtra("name",context);
                        view.getContext().startActivity(intent);
                    }
                });
                return poetryHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ImageHolder){
            ImageHolder imageHolder = (ImageHolder) holder;
            imageHolder.imageView.setImageResource(R.drawable.haitang);
        }
        if(holder instanceof TimeHolder){
            TimeHolder timeHolder = (TimeHolder)holder;
            timeHolder.textView.setText(time[0]);
            timeHolder.textView_two.setText(Lauar.getLauar(time[1],time[2],time[3]));
        }
        if(holder instanceof PoetryHolder){
            PoetryHolder poetryHolder = (PoetryHolder)holder;
            HomePagePoetry homePagePoetry = lists.get(position);

            poetryHolder.items_title.setTypeface(typefaceList.get(2));
            poetryHolder.items_title.setText(homePagePoetry.getTitle());
            poetryHolder.items_dyAndAuthor.setTypeface(typefaceList.get(1));
            poetryHolder.items_dyAndAuthor.setText(homePagePoetry.getDynasty()+"/"+homePagePoetry.getAuthor());
            poetryHolder.items_context.setTypeface(typefaceList.get(0));
            poetryHolder.items_context.setText(homePagePoetry.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == ONE_ITEM){
            return ONE_ITEM;
        }else if(position == TWO_ITEM){
            return TWO_ITEM;
        }else if(position >= THREE_ITEM){
            return THREE_ITEM;
        }
        return 3;
    }
}

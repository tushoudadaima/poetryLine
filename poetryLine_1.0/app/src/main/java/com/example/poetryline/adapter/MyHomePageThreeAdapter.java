package com.example.poetryline.adapter;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poetryline.R;
import com.example.poetryline.detail.DetailActivity;
import com.example.poetryline.entity.HomePagePoetry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHomePageThreeAdapter extends RecyclerView.Adapter<MyHomePageThreeAdapter.ViewHolder> {

    private List<HomePagePoetry> list;
    private List<Typeface> typefaceList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView1;
        TextView textView2;
        private View nowView;

        public ViewHolder(View view){
            super(view);
            nowView = view;
            textView = view.findViewById(R.id.item_title_two);
            textView1 = view.findViewById(R.id.item_dyAndAuthor_two);
            textView2 = view.findViewById(R.id.item_context_two);
        }
    }

    public MyHomePageThreeAdapter(List<HomePagePoetry> list,List<Typeface> typefaceList){
        this.list = list;
        this.typefaceList = typefaceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poems_item_two,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.nowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                HomePagePoetry homePagePoetry = list.get(position);
                String context = homePagePoetry.getTitle();
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("name",context);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomePagePoetry homePagePoetry = list.get(position);
        String[] content = homePagePoetry.getContent().split("\\|");
        holder.textView.setTypeface(typefaceList.get(2));
        holder.textView.setText(homePagePoetry.getTitle());
        holder.textView1.setTypeface(typefaceList.get(1));
        holder.textView1.setText(homePagePoetry.getDynasty() + "/" + homePagePoetry.getAuthor());
        holder.textView2.setTypeface(typefaceList.get(0));
        holder.textView2.setText(content[0]);
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
       else {
           return 0;
        }
    }
}

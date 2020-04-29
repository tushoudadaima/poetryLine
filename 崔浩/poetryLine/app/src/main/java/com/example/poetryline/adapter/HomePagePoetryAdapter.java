package com.example.poetryline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poetryline.R;
import com.example.poetryline.entity.HomePagePoetry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomePagePoetryAdapter extends RecyclerView.Adapter<HomePagePoetryAdapter.ViewHolder> {

    private List<HomePagePoetry> poemsList;

    //内部类
    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView items_title;
        TextView items_dyAndAuthor;
        TextView items_context;

        public ViewHolder(View view){
            super(view);
            items_title = view.findViewById(R.id.item_title);
            items_dyAndAuthor = view.findViewById(R.id.item_dyAndAuthor);
            items_context = view.findViewById(R.id.item_context);
        }
    }

    //构造方法
    public HomePagePoetryAdapter(List<HomePagePoetry> poemsList){
        this.poemsList = poemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poems_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomePagePoetry homePagePoetry = poemsList.get(position);
        holder.items_title.setText(homePagePoetry.getTitle());
        holder.items_dyAndAuthor.setText(homePagePoetry.getDynasty()+"/"+homePagePoetry.getAuthor());
        holder.items_context.setText(homePagePoetry.getContent());
    }

    @Override
    public int getItemCount() {
        return poemsList.size();
    }
}

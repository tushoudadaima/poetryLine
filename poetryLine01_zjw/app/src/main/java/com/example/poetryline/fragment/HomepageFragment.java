package com.example.poetryline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.poetryline.R;
import com.example.poetryline.adapter.HomePagePoetryAdapter;
import com.example.poetryline.detail.DetailActivity;
import com.example.poetryline.entity.HomePagePoetry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomepageFragment extends Fragment {

    private TextView showTime;
    private ImageView leftMenu;
    private DrawerLayout drawerLayout;
    private SearchView searchView;
    private EditText editText;
    private Button button;
    private RecyclerView recyclerView;
    private List<HomePagePoetry> poemsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_fragment,container,false);
        showTime = view.findViewById(R.id.text_showTime);
        leftMenu = view.findViewById(R.id.image_leftmenu);
        recyclerView = view.findViewById(R.id.recycler_view);
        drawerLayout = getActivity().findViewById(R.id.left_menu);
        editText=view.findViewById(R.id.search1);

        button=view.findViewById(R.id.search2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vvv=editText.getText().toString();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("name",vvv);
                startActivity(intent);
            }
        });

        leftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        initPoemsList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        HomePagePoetryAdapter adapter = new HomePagePoetryAdapter(poemsList);
        recyclerView.setAdapter(adapter);
        showData();
        return view;
    }

    //首页展示日期
    private void showData(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int mouth = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String time = ""+year;
        if(mouth < 10){
            time += "-0"+mouth;
        }else{
            time += "-"+mouth;
        }
        if(day < 10){
            time += "-0"+day;
        }else {
            time += "-"+day;
        }
        showTime.setText(time);
    }

    //初始化数据
    private void initPoemsList(){
        HomePagePoetry one = new HomePagePoetry("静夜思","唐代","李白","床前明夜光，疑是地上霜。");
        poemsList.add(one);
        HomePagePoetry two = new HomePagePoetry("示儿","宋代","陆游","死去元知万事空，但悲不见九州同。");
        poemsList.add(two);
        HomePagePoetry three = new HomePagePoetry("对酒","清代","秋瑾","不惜千刀买宝刀，貂裘换酒也堪豪。");
        poemsList.add(three);
        HomePagePoetry four = new HomePagePoetry("过零丁洋","宋代","文天祥","辛苦遭逢起一经，干戈寥落四周星。");
        poemsList.add(four);
    }
}

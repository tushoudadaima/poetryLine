package com.example.poetryline.fragment;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poetryline.R;
import com.example.poetryline.adapter.MyHomePageAdapter;
import com.example.poetryline.entity.HomePagePoetry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomepageFragmentOne extends Fragment {

    private RecyclerView recyclerView;
    private TextView textView;
    private List<HomePagePoetry> poemsList = new ArrayList<>();
    private List<Typeface> typefaceList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_one_fragment,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);
        textView = view.findViewById(R.id.text_day);

        initPoemsList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        MyHomePageAdapter myHomePageAdapter = new MyHomePageAdapter(showData(),poemsList,typefaceList);
        recyclerView.setAdapter(myHomePageAdapter);
        return view;
    }

    //首页展示日期
    private String[] showData(){
        String[] data;
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
        data = new String[]{time,year+"",mouth+"",day+""};
        return data;
    }

    //初始化数据
    private void initPoemsList(){
        poemsList.clear();
        HomePagePoetry no_one = new HomePagePoetry();
        poemsList.add(no_one);
        HomePagePoetry no_two = new HomePagePoetry();
        poemsList.add(no_two);
        HomePagePoetry one = new HomePagePoetry("将进酒","唐代","李白","君不见黄河之水天上来，奔流到海不复还。");
        poemsList.add(one);
        HomePagePoetry two = new HomePagePoetry("示儿","宋代","陆游","死去元知万事空，但悲不见九州同。");
        poemsList.add(two);
        HomePagePoetry three = new HomePagePoetry("临江仙·送钱穆夫","宋代","苏轼","一别都门三改火，天涯踏尽红尘。");
        poemsList.add(three);
        HomePagePoetry four = new HomePagePoetry("三五七言/秋风词","唐代","李白","秋风清，秋月明，落叶聚还散，寒鸦栖复惊。");
        poemsList.add(four);
        HomePagePoetry five = new HomePagePoetry("题龙阳县青草湖","元代","唐温如","西风吹老洞庭波，一夜湘君白发多。");
        poemsList.add(five);
        HomePagePoetry six = new HomePagePoetry("鹊桥仙·纤云弄巧","宋代","秦观","纤云弄巧，飞星传恨，银汉迢迢暗度。");
        poemsList.add(six);
        HomePagePoetry seven = new HomePagePoetry("登科后","唐代","孟郊","昔日龌龊不足夸，今朝放荡思无涯。");
        poemsList.add(seven);
        HomePagePoetry eight = new HomePagePoetry("金陵晚望","唐代","高蟾","曾伴浮云归晚翠，犹陪落日泛秋声。");
        poemsList.add(eight);
        HomePagePoetry nine = new HomePagePoetry("绝句·古木阴中系短蓬","宋代","志南","古木阴中系短蓬，杖藜扶我过桥东。");
        poemsList.add(nine);
        HomePagePoetry ten = new HomePagePoetry("断句","宋代","苏麟","近水楼台先得月，向阳花木易为春。");
        poemsList.add(ten);

        AssetManager assetManager = getActivity().getAssets();
        Typeface type_one = Typeface.createFromAsset(assetManager,"fonts/xingkai.ttf");
        typefaceList.add(type_one);
        Typeface type_two = Typeface.createFromAsset(assetManager,"fonts/xihei.ttf");
        typefaceList.add(type_two);
        Typeface type_three = Typeface.createFromAsset(assetManager,"fonts/yishuhei.ttf");
        typefaceList.add(type_three);
    }
}

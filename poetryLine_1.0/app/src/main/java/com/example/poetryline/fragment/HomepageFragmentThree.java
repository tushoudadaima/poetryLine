package com.example.poetryline.fragment;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.poetryline.R;
import com.example.poetryline.adapter.MyHomePageThreeAdapter;
import com.example.poetryline.entity.HomePagePoetry;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomepageFragmentThree extends Fragment {

    private RecyclerView recyclerView;
    private List<HomePagePoetry> lists = new ArrayList<>();
    private List<Typeface> typefaceList = new ArrayList<>();

    public static HomepageFragmentThree newInstance(String data){
        Bundle bundle = new Bundle();
        bundle.putString("data",data);
        HomepageFragmentThree three = new HomepageFragmentThree();
        three.setArguments(bundle);
        return three;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().getString("data")!=null){
            lists = parseJSONWithJSONObject(getArguments().getString("data"));
        }else{
            Toast.makeText(getContext(),"未连接网络",Toast.LENGTH_SHORT).show();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_three_fragment,container,false);
        recyclerView = view.findViewById(R.id.recycler_view_two);

        AssetManager assetManager = getActivity().getAssets();
        Typeface type_one = Typeface.createFromAsset(assetManager,"fonts/xingkai.ttf");
        typefaceList.add(type_one);
        Typeface type_two = Typeface.createFromAsset(assetManager,"fonts/xihei.ttf");
        typefaceList.add(type_two);
        Typeface type_three = Typeface.createFromAsset(assetManager,"fonts/yishuhei.ttf");
        typefaceList.add(type_three);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MyHomePageThreeAdapter homePageThreeAdapter = new MyHomePageThreeAdapter(lists,typefaceList);
        recyclerView.setAdapter(homePageThreeAdapter);
        return view;
    }

    private static List<HomePagePoetry> parseJSONWithJSONObject(String jsonData){
        try {
            List<HomePagePoetry> data = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String content = jsonObject.getString("content");
                String author = jsonObject.getString("author");
                String dynasty = jsonObject.getString("dynasty");
                HomePagePoetry homePagePoetry = new HomePagePoetry();
                homePagePoetry.setTitle(title);
                homePagePoetry.setContent(content);
                homePagePoetry.setAuthor(author);
                homePagePoetry.setDynasty(dynasty);
                data.add(homePagePoetry);
            }
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

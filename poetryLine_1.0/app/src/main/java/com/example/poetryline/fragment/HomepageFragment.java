package com.example.poetryline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.poetryline.R;
import com.example.poetryline.SearchActivity;
import com.example.poetryline.adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class HomepageFragment extends Fragment {

    private ImageView leftMenu;
    private DrawerLayout drawerLayout;
    private SearchView editText;
//    private Button button;
    private List<Fragment> list;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_fragment,container,false);
        leftMenu = view.findViewById(R.id.image_leftmenu);
        drawerLayout = getActivity().findViewById(R.id.left_menu);
        editText=view.findViewById(R.id.search1);
        viewPager = view.findViewById(R.id.view_page);
        tabLayout = view.findViewById(R.id.tab_layout);

        //点击首页搜索框跳转search界面
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });


        leftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        Bundle bundle = getArguments();
        data = bundle.getString("data");
        initFragmentData();
        return view;
    }

    //嵌套fragment
    private void initFragmentData(){
        list = new ArrayList<>();
        list.add(new HomepageFragmentOne());
        list.add(new HomepageFragmentTwo());
        list.add(HomepageFragmentThree.newInstance(data));

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(),list);
        viewPager.setAdapter(fragmentAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("流传经典");
        tabLayout.getTabAt(1).setText("天时地利");
        tabLayout.getTabAt(2).setText("闻所未闻");
    }
}

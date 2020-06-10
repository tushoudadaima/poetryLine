package com.example.poetryline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.poetryline.R;
import com.example.poetryline.adapter.CommunityAdapter;
import com.example.poetryline.adapter.CustomAdapter;
import com.example.poetryline.detail.Send.MyaddActivity;
import com.example.poetryline.detail.Send.SectionsPagerAdapter;
import com.example.poetryline.entity.CommunityItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class CommunityFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab,container,false);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this.getContext(), getActivity().getSupportFragmentManager());
        ViewPager viewPager =view.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getActivity(), MyaddActivity.class);

                startActivity(intent3);
                getActivity().overridePendingTransition(R.anim.bottom_in,R.anim.bottom_silent);
            }
        });

        return view;
    }
}

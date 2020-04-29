package com.example.poetryline.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poetryline.R;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class HomepageFragment extends Fragment {

    private TextView showTime;
    private ImageView leftMenu;
    private DrawerLayout drawerLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_fragment,container,false);
        showTime = view.findViewById(R.id.text_showTime);
        leftMenu = view.findViewById(R.id.image_leftmenu);
        drawerLayout = getActivity().findViewById(R.id.left_menu);

        leftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
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
}

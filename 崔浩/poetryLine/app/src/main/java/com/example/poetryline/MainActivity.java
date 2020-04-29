package com.example.poetryline;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.poetryline.fragment.CommunityFragment;
import com.example.poetryline.fragment.GameFragment;
import com.example.poetryline.fragment.HomepageFragment;
import com.example.poetryline.fragment.SortFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HomepageFragment homepageFragment = new HomepageFragment();
    private SortFragment sortFragment;
    private GameFragment gameFragment;
    private CommunityFragment communityFragment;
    private FragmentManager fragmentManager;
    private TextView homepage;
    private TextView sort;
    private TextView game;
    private TextView community;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //安卓状态栏全透明
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_main);

        //fragment的替换
        homepage = findViewById(R.id.activity_homepage);
        sort = findViewById(R.id.activity_sort);
        game = findViewById(R.id.activity_game);
        community = findViewById(R.id.activity_community);
        homepage.setOnClickListener(this);
        sort.setOnClickListener(this);
        game.setOnClickListener(this);
        community.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.activity_fragment,homepageFragment).commit();
    }

    //使用add和hide进行fragment的替换
    @Override
    public void onClick(View view) {
        hideFragments();
        switch (view.getId()){
            case R.id.activity_homepage:
                if(homepageFragment != null){
                    fragmentManager.beginTransaction().show(homepageFragment).commit();
                    return;
                }
                fragmentManager.beginTransaction().add(R.id.activity_fragment,homepageFragment).commit();
                break;
            case R.id.activity_sort:
                if(sortFragment != null){
                    fragmentManager.beginTransaction().show(sortFragment).commit();
                    return;
                }
                sortFragment = new SortFragment();
                fragmentManager.beginTransaction().add(R.id.activity_fragment,sortFragment).commit();
                break;
            case R.id.activity_game:
                if(gameFragment != null){
                    fragmentManager.beginTransaction().show(gameFragment).commit();
                    return;
                }
                gameFragment = new GameFragment();
                fragmentManager.beginTransaction().add(R.id.activity_fragment,gameFragment).commit();
                break;
            case R.id.activity_community:
                if(communityFragment != null){
                    fragmentManager.beginTransaction().show(communityFragment).commit();
                    return;
                }
                communityFragment = new CommunityFragment();
                fragmentManager.beginTransaction().add(R.id.activity_fragment,communityFragment).commit();
                break;
        }
    }

    private void hideFragments(){
        if(homepageFragment != null){
            fragmentManager.beginTransaction().hide(homepageFragment).commit();
        }
        if(sortFragment != null){
            fragmentManager.beginTransaction().hide(sortFragment).commit();
        }
        if(gameFragment != null){
            fragmentManager.beginTransaction().hide(gameFragment).commit();
        }
        if(communityFragment != null){
            fragmentManager.beginTransaction().hide(communityFragment).commit();
        }
    }
}

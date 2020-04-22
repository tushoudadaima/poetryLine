package com.example.drawerlayouttest01;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawerlayouttest01.fragment.CommunityFragment;
import com.example.drawerlayouttest01.fragment.GameFragment;
import com.example.drawerlayouttest01.fragment.HomepageFragment;
import com.example.drawerlayouttest01.fragment.SortFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fragment
        TextView homepage = findViewById(R.id.activity_homepage);
        TextView sort = findViewById(R.id.activity_sort);
        TextView game = findViewById(R.id.activity_game);
        TextView community = findViewById(R.id.activity_community);
        homepage.setOnClickListener( this);
        sort.setOnClickListener(this);
        game.setOnClickListener(this);
        community.setOnClickListener(this);
        replaceFragment(new HomepageFragment());

        //顶部的蓝条 但是不知道怎么去
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//自带的右下角的发布内容的按钮 但是好像用不到
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //写了下面这个点击item就会跳转到相应的fragment
//        NavigationUI.setupWithNavController(navigationView, navController);

        //获取左滑窗口的item
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //在这里处理item的点击事件
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        Intent intent = new Intent(MainActivity.this, Test01.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"item1",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_gallery:
                        Intent intent2 = new Intent(MainActivity.this, Test01.class);
                        startActivity(intent2);
                        Toast.makeText(getApplicationContext(),"item2",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_slideshow:
                        Intent intent3 = new Intent(MainActivity.this, Test01.class);
                        startActivity(intent3);
                        Toast.makeText(getApplicationContext(),"item3",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        //获取左滑窗口头像控件
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ImageView head =  headerView.findViewById(R.id.left_headButton);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"head",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //fragment
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_homepage:
                replaceFragment(new HomepageFragment());
                break;
            case R.id.activity_sort:
                replaceFragment(new SortFragment());
                break;
            case R.id.activity_game:
                replaceFragment(new GameFragment());
                break;
            case R.id.activity_community:
                replaceFragment(new CommunityFragment());
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_fragment,fragment);
        transaction.commit();
    }
}

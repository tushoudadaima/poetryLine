package com.example.poetryline;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.fragment.CommunityFragment;
import com.example.poetryline.fragment.GameFragment;
import com.example.poetryline.fragment.HomepageFragment;
import com.example.poetryline.fragment.SortFragment;
import com.example.poetryline.signup.CountActivity;
import com.example.poetryline.signup.LoginActivity;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
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
    private String phone="";
    private ImageView head;

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
        SharedPreferences userSP = getSharedPreferences("userData",MODE_PRIVATE);
        phone = userSP.getString("phone","");

        //侧滑图片图片就是显示他本身的颜色
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        //获取左滑抽屉的部分控件
//        final TextView textView_left_name = findViewById(R.id.left_name);
//        final TextView textView_left_jianjie = findViewById(R.id.left_jianjie);

        //获取左滑窗口头像控件
        View headerView = navigationView.inflateHeaderView(R.layout.header_layout);
        head =  headerView.findViewById(R.id.left_headButton);


        if (phone.length()!=0){
            head.setImageResource(R.drawable.user);

        }
        @SuppressLint("SdCardPath")
        String path1 = "/data/data/com.example.poetryline/files/poetryLinePic/"+phone+".jpg";
        @SuppressLint("SdCardPath") File file = new File(path1);
        if(file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(path1);
            head.setImageBitmap(bitmap);
//            Toast.makeText(getApplicationContext(),"存在头像",Toast.LENGTH_SHORT).show();
        }else{
//            Toast.makeText(getApplicationContext(),"不存在头像",Toast.LENGTH_SHORT).show();
        }

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(MainActivity.this,"点击了headImage",Toast.LENGTH_SHORT).show();
//                textView_left_name.setText("改个名字");
//                textView_left_jianjie.setText("改个简介");
                if (phone.length()==0){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, CountActivity.class);
                    startActivity(intent);
                }

            }
        });

        //获取左滑窗口的item
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //在这里处理item的点击事件
                switch (item.getItemId())
                {
                    case R.id.item1:
                        //intent测试 intent数据传输测试 应该没啥
//                        String s="from item1";
//                        Intent intent = new Intent(MainActivity.this, Test01.class);
//                        intent.putExtra("data", s);
//                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"item1",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
//                        Intent intent2 = new Intent(MainActivity.this, Test01.class);
//                        startActivity(intent2);
                        Toast.makeText(getApplicationContext(),"item2",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item3:
//                        Intent intent3 = new Intent(MainActivity.this, Test01.class);
//                        startActivity(intent3);
                        Toast.makeText(getApplicationContext(),"item3",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item4:
                        Toast.makeText(getApplicationContext(),"item4",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item5:
//                        Toast.makeText(getApplicationContext(),"item5",Toast.LENGTH_SHORT).show();
                        Intent intent5 = new Intent(MainActivity.this,HelpActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.item6:
//                        Toast.makeText(getApplicationContext(),"item6",Toast.LENGTH_SHORT).show();
                        Intent qqIntent = new Intent(Intent.ACTION_SEND);
                        qqIntent.setPackage("com.tencent.mobileqq");
                        qqIntent.setType("text/plain");
                        qqIntent.putExtra(Intent.EXTRA_TEXT, "快来一起加入诗行吧！"+"https://github.com/tushoudadaima/poetryLine");
                        startActivity(qqIntent);

                        break;

                    case R.id.item7:
//                        Toast.makeText(getApplicationContext(),"item7",Toast.LENGTH_SHORT).show();
                        Intent intent7 = new Intent(MainActivity.this,AboutActivity.class);
                        startActivity(intent7);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

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
    protected void onResume() {
        super.onResume();
        @SuppressLint("SdCardPath")
        String path1 = "/data/data/com.example.poetryline/files/poetryLinePic/"+phone+".jpg";
        @SuppressLint("SdCardPath") File file = new File(path1);
        if(file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(path1);
            head.setImageBitmap(bitmap);
        }
    }

}

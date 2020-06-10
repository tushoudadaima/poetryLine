package com.example.poetryline;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.collect.CollectActivity;
import com.example.poetryline.drawerLayout.AboutActivity;
import com.example.poetryline.drawerLayout.HelpActivity;
import com.example.poetryline.drawerLayout.RecordActivity;
import com.example.poetryline.drawerLayout.Setting;
import com.example.poetryline.fragment.CommunityFragment;
import com.example.poetryline.fragment.GameFragment;
import com.example.poetryline.fragment.HomepageFragment;
import com.example.poetryline.fragment.SortFragment;
import com.example.poetryline.signup.CountActivity;
import com.example.poetryline.signup.LoginActivity;
import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

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
    private ImageView headImg;
    TextView textView_left_name;
    TextView textView_left_jianjie;
    private String nameString;
    private String jianjieString;
    private String wang_zhi;
    private SharedPreferences.Editor editor;
    private SharedPreferences userSP;
    private String userName;
    private String userIntroduction;
    private long exitTime = 0;
//    private RelativeLayout wode_shezhi;
    private String responseData;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //传递数据
        SharedPreferences preferences = getSharedPreferences("datas",MODE_PRIVATE);
        responseData = preferences.getString("data","");


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

        userSP = getSharedPreferences("userData",MODE_PRIVATE);


        setContentView(R.layout.activity_main);

        wang_zhi=this.getString(R.string.wang_zhi);




        //侧滑图片图片就是显示他本身的颜色
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);

        //获取左滑窗口header控件
        View headerView = navigationView.inflateHeaderView(R.layout.header_layout);
        headImg =  headerView.findViewById(R.id.left_headButton);
        textView_left_name = headerView.findViewById(R.id.left_name);
        textView_left_jianjie = headerView.findViewById(R.id.left_jianjie);


        phone = userSP.getString("phone","");
        userName = userSP.getString("name","");
        userIntroduction = userSP.getString("introduction","");
        if (phone.length()!=0){
//            Toast.makeText(getApplicationContext(),"phone存在 findname 简介 set初始头像",Toast.LENGTH_SHORT).show();
//            findName();
//            findJianjie();
//            head.setImageResource(R.drawable.user2);
//            textView_left_name.setText(findName());
//            textView_left_jianjie.setText(findJianjie());

            if(userName.length()==0){
                editor = getSharedPreferences("userData",MODE_PRIVATE).edit();
                editor.putString("name",findName());
                editor.putString("introduction",findJianjie());
                editor.apply();
//                Toast.makeText(getApplicationContext(),"main保存",Toast.LENGTH_SHORT).show();
            }

        }

        @SuppressLint("SdCardPath")
        String path1 = "/data/data/com.example.poetryline/files/poetryLinePic/"+phone+".jpg";
        @SuppressLint("SdCardPath") File file = new File(path1);
        if(file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(path1);
            headImg.setImageBitmap(bitmap);
//            Toast.makeText(getApplicationContext(),"存在头像",Toast.LENGTH_SHORT).show();
        }else{
//            Toast.makeText(getApplicationContext(),"不存在头像",Toast.LENGTH_SHORT).show();
        }

        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.length()==0){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);

                }else {
                    Intent intent = new Intent(MainActivity.this, CountActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        //侧滑设置点击事件
//        wode_shezhi = findViewById(R.id.wode_shezhi);
//        wode_shezhi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Setting.class);
//                startActivity(intent);
//            }
//        });

        //获取左滑窗口的item
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //在这里处理item的点击事件
                switch (item.getItemId())
                {
                    /*case R.id.item1:
                        Toast.makeText(getApplicationContext(),"我的信息",Toast.LENGTH_SHORT).show();
                        break;*/
                    case R.id.item2:
                        //我的收藏
                        Intent intent2 = new Intent(MainActivity.this, CollectActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.item3:
                        //游戏记录
                        Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                        startActivity(intent);
                        break;
                    /*case R.id.item4:
                        //我的动态
                        hideFragments();
                        if(communityFragment != null){
                            fragmentManager.beginTransaction().show(communityFragment).commit();
                        }
                        communityFragment = new CommunityFragment();
                        fragmentManager.beginTransaction().add(R.id.activity_fragment,communityFragment).commit();
                        break;*/
                    case R.id.item5:
                        Intent intent5 = new Intent(MainActivity.this, HelpActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.item6:
                        Intent shareIntent;
                        shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "快来一起加入诗行吧！"+"https://github.com/tushoudadaima/poetryLine");
                        Intent intent6 = Intent.createChooser(shareIntent, "分享一段文本信息");
                        if (shareIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent6);
                        }

                        break;
                    case R.id.item7:
                        Intent intent7 = new Intent(MainActivity.this, AboutActivity.class);
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
        Bundle bundle = new Bundle();
        bundle.putString("data",responseData);
        homepageFragment.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.activity_fragment,homepageFragment).commit();


    }


    private String findJianjie() {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(wang_zhi+"/FindIntroduction?phone=" + phone);
                    InputStream in = url.openStream();
                    InputStreamReader isr = new InputStreamReader(in,"UTF-8");
                    BufferedReader bf = new BufferedReader(isr);
                    String results = "";
                    String newLine = "";
                    while((newLine = bf.readLine()) != null){
                        results = newLine;

                    }
                    jianjieString=results;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return jianjieString;
    }

    private String findName() {
//        final String[] results = {""};
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(wang_zhi+"/FindName?phone=" + phone);
                    InputStream in = url.openStream();
                    InputStreamReader isr = new InputStreamReader(in,"UTF-8");
                    BufferedReader bf = new BufferedReader(isr);
                    String results = "";
                    String newLine = "";
                    while((newLine = bf.readLine()) != null){
                        results = newLine;

                    }
                    nameString= results;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return nameString;
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
//        Toast.makeText(getApplicationContext(), "OnResume", Toast.LENGTH_SHORT).show();
        @SuppressLint("SdCardPath")
        String path1 = "/data/data/com.example.poetryline/files/poetryLinePic/"+phone+".jpg";
        @SuppressLint("SdCardPath") File file = new File(path1);
        if(file.exists()){
//            Toast.makeText(getApplicationContext(),"file存在",Toast.LENGTH_SHORT).show();
            Bitmap bitmap = BitmapFactory.decodeFile(path1);
            headImg.setImageBitmap(bitmap);

            if(userName.length()==0) {
                editor = getSharedPreferences("userData", MODE_PRIVATE).edit();
                editor.putString("name", findName());
                editor.putString("introduction", findJianjie());
                editor.apply();
//                Toast.makeText(getApplicationContext(), "OnResume保存", Toast.LENGTH_SHORT).show();
            }
            userSP = getSharedPreferences("userData",MODE_PRIVATE);
            userName = userSP.getString("name","");
            userIntroduction = userSP.getString("introduction","");
            if (userName.length()!=0){
                textView_left_name.setText(userSP.getString("name",""));
            }else {
                textView_left_name.setText("未登录");
            }
            if(userIntroduction.length()!=0){
                textView_left_jianjie.setText(userIntroduction);
            }else {
                textView_left_jianjie.setText("这个人有点懒什么都没有写(´・_・｀)");
            }
//            Toast.makeText(getApplicationContext(),"name:"+userName+" introduction:"+userIntroduction.length(),Toast.LENGTH_SHORT).show();


        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}

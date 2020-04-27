package com.example.poetryline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.fragment.CommunityFragment;
import com.example.poetryline.fragment.GameFragment;
import com.example.poetryline.fragment.HomepageFragment;
import com.example.poetryline.fragment.SortFragment;
import com.google.android.material.navigation.NavigationView;

import java.security.acl.Group;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView homepage = findViewById(R.id.activity_homepage);
        TextView sort = findViewById(R.id.activity_sort);
        TextView game = findViewById(R.id.activity_game);
        TextView community = findViewById(R.id.activity_community);
        homepage.setOnClickListener(this);
        sort.setOnClickListener(this);
        game.setOnClickListener(this);
        community.setOnClickListener(this);
        replaceFragment(new HomepageFragment());

        //设置左滑抽屉的图片显示原本颜色
        NavigationView navigationView = findViewById(R.id.left_view);
        navigationView.setItemIconTintList(null);

        //左滑抽屉的头部图像点击
//        View headerView = navigationView.getHeaderView(0);
        View headerView = navigationView.inflateHeaderView(R.layout.header_layout);
        ImageView head =  headerView.findViewById(R.id.head);
        Button button1 = headerView.findViewById(R.id.button);
//        ClipData.Item group1 = headerView.findViewById(R.id.item1);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"head",Toast.LENGTH_SHORT).show();
            }
        });
//        View headerView2 = navigationView.getHeaderView(1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"button",Toast.LENGTH_SHORT).show();
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //在这里处理item的点击事件
                switch (item.getItemId())
                {
                    case R.id.item1:
                        Toast.makeText(getApplicationContext(),"item1",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
                        Toast.makeText(getApplicationContext(),"item2",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item3:
                        Toast.makeText(getApplicationContext(),"item3",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });



    }

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

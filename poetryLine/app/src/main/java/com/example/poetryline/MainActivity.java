package com.example.poetryline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.poetryline.fragment.CommunityFragment;
import com.example.poetryline.fragment.GameFragment;
import com.example.poetryline.fragment.HomepageFragment;
import com.example.poetryline.fragment.SortFragment;

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

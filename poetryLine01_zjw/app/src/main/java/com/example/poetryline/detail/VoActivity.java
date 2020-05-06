package com.example.poetryline.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.poetryline.R;

import androidx.appcompat.app.AppCompatActivity;

public class VoActivity extends AppCompatActivity {
    private TextView mAmTvBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vo);
        initView();
        initData();
        initAdapter();
        initListener();
    }

    private void initView() {
        mAmTvBtn= (TextView) findViewById(R.id.am_tv_btn);

    }

    private void initData() {

    }

    private void initAdapter() {

    }

    private void initListener() {
        mAmTvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VoActivity.this, VoiceActivity.class);
                startActivity(intent);

            }
        });
    }
}



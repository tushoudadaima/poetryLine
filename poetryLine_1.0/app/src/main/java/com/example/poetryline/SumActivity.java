package com.example.poetryline;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.util.HttpUtil;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SumActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private TextView tv_score,tv_names,tv_dates;
    private CircleImageView img_circles;
    private Button btn_save;
    private String userName,phone;
    private String wang_zhi;
    private String time;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);
        wang_zhi = this.getString(R.string.wang_zhi);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date(System.currentTimeMillis());
        initView();
        Intent intent = getIntent();
        score = intent.getIntExtra("score",0);
        sp = getSharedPreferences("userData",MODE_PRIVATE);
        userName = sp.getString("name", null);
        tv_score.setText(score+"");
        tv_names.setText(userName);
        phone = sp.getString("phone",null);
        @SuppressLint("SdCardPath")
        String path1 = "/data/data/com.example.poetryline/files/poetryLinePic/"+phone+".jpg";
        @SuppressLint("SdCardPath") File file = new File(path1);
        if(file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(path1);
            img_circles.setImageBitmap(bitmap);
        }
        time = simpleDateFormat.format(date);
        tv_dates.setText(time);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
                Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void insert() {
        String address = wang_zhi+"/record/insert/"+time+"/"+userName+"/"+phone+"/答题/"+score;
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e);
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data = response.body().string();
            }
        });
    }

    private void initView() {
        tv_score = findViewById(R.id.tv_score);
        tv_names = findViewById(R.id.tv_names);
        tv_dates = findViewById(R.id.tv_dates);
        img_circles = findViewById(R.id.img_circles);
        btn_save = findViewById(R.id.btn_save);
    }
}

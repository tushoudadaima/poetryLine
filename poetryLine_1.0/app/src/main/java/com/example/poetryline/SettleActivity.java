package com.example.poetryline;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.poetryline.entity.Poetry;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SettleActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private String userName,phone;
    private TextView tv_big,tv_name,tv_right,tv_no,tv_wrong,tv_date;
    private CircleImageView circleImageView;
    private Button btn_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle);
        initView();
        Intent intent = getIntent();
        List<Poetry> poetries = (List<Poetry>) intent.getSerializableExtra("list");
        String key = intent.getStringExtra("key");
        int right = intent.getIntExtra("right",0);
        int no = intent.getIntExtra("no",0);
        int wrong = intent.getIntExtra("wrong",0);
        sp = getSharedPreferences("userData",MODE_PRIVATE);
        userName = sp.getString("name", null);
        phone = sp.getString("phone",null);
        @SuppressLint("SdCardPath")
        String path1 = "/data/data/com.example.poetryline/files/poetryLinePic/"+phone+".jpg";
        @SuppressLint("SdCardPath") File file = new File(path1);
        if(file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(path1);
            circleImageView.setImageBitmap(bitmap);
        }
        tv_big.setText(key);
        tv_name.setText(userName);
        tv_right.setText("合格："+right+"首");
        tv_no.setText("未找到："+no+"首");
        tv_wrong.setText("不含令牌："+wrong+"首");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date(System.currentTimeMillis());
        tv_date.setText(simpleDateFormat.format(date));
        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SettleActivity.this,ShowingActivity.class);
                intent1.putExtra("list", (Serializable)poetries);
                startActivity(intent1);
            }
        });
    }

    public void initView(){
        btn_all = findViewById(R.id.btn_all);
        tv_big = findViewById(R.id.tv_big);
        tv_name = findViewById(R.id.tv_name);
        tv_right = findViewById(R.id.tv_right);
        tv_no = findViewById(R.id.tv_no);
        tv_wrong = findViewById(R.id.tv_wrong);
        tv_date = findViewById(R.id.tv_date);
        circleImageView = findViewById(R.id.img_circle);
    }
}

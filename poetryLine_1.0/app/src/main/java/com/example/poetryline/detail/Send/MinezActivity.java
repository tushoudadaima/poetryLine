package com.example.poetryline.detail.Send;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poetryline.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;


public class MinezActivity extends AppCompatActivity {
    private String ming;
    private String biao;
    private String tupian;
    private TextView biaoa;
    private TextView shijian;
    private ImageView pic;
    private TextView bibibia;
    private TextView tv;
    private ImageView shequ_head;
    private SharedPreferences userSP;
    private String userName;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinxiang);

        userSP = getSharedPreferences("userData",MODE_PRIVATE);
        userName = userSP.getString("name","");//获取用户昵称
        phone = userSP.getString("phone","");//获取用户手机号

        ming=this.getIntent().getStringExtra("mingzi");
        biao=this.getIntent().getStringExtra("biaoti");
        tupian=this.getIntent().getStringExtra("tupian");
        shequ_head = findViewById(R.id.shequ_head);
        biaoa=findViewById(R.id.shequ_name);
        shijian=findViewById(R.id.shijian);
        bibibia=findViewById(R.id.bibibi);
        tv=findViewById(R.id.tv_title);
        tv.setText("我的");
//获取用户头像
        @SuppressLint("SdCardPath")
        String path1 = "/data/data/com.example.poetryline/files/poetryLinePic/"+phone+".jpg";
        @SuppressLint("SdCardPath") File file = new File(path1);
        if(file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(path1);
            shequ_head.setImageBitmap(bitmap);
        }


        biaoa.setText(userName);
        pic=findViewById(R.id.pic);
        Uri uri= Uri.parse(tupian);
        pic.setImageURI(uri);
        pic.setVisibility(View.VISIBLE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        shijian.setText("时间:"+simpleDateFormat.format(date));
        int j = new Random().nextInt(15)%(15-0+1)+0;
        bibibia.setText(ming);
    }
}

package com.example.poetryline;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.adapter.MyPagerAdapter;
import com.example.poetryline.entity.Title;
import com.example.poetryline.util.HttpUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TitleActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private View view1,view2,view3,view4,view5,view6,view7,view8,view9, view10;
    private List<Title> titles;
    private TextView textView1,textView2,textView3,textView4,textView5,textView6,text1,text2,text3,text4,text5,text6;
    private TextView tx1,tx2,tx3,tx4,tx5,tx6,tv1,tv2,tv3,tv4,tv5,tv6,t1,t2,t3,t4,t5,t6;
    private TextView v1,v2,v3,v4,v5,v6,i1,i2,i3,i4,i5,i6,e1,e2,e3,e4,e5,e6,w1,w2,w3,w4,w5,w6,x1,x2,x3,x4,x5,x6;
    private Button textView7,text7,tx7,tv7,t7,v7,i7,e7,w7,x7;
    private List<View> viewList;
    private MyPagerAdapter myPagerAdapter;
    private String wang_zhi;
    private int score = 100;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 100:
                    String info = (String)msg.obj;
                    titles = changeWithJSONObject(info);
                    inflate();
            }
        }
    };
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        wang_zhi = this.getString(R.string.wang_zhi);
        viewPager = findViewById(R.id.pager);
        viewList = new ArrayList<>();
        LayoutInflater lf = getLayoutInflater();
        view1 = lf.inflate(R.layout.title,null,false);
        view2 = lf.inflate(R.layout.title,null,false);
        view3 = lf.inflate(R.layout.title,null,false);
        view4 = lf.inflate(R.layout.title,null,false);
        view5 = lf.inflate(R.layout.title,null,false);
        view6 = lf.inflate(R.layout.title,null,false);
        view7 = lf.inflate(R.layout.title,null,false);
        view8 = lf.inflate(R.layout.title,null,false);
        view9 = lf.inflate(R.layout.title,null,false);
        view10 = lf.inflate(R.layout.title,null,false);
        //第一页的所有控件
        textView1 = view1.findViewById(R.id.tv_sign);
        textView2 = view1.findViewById(R.id.tv_title);
        textView3 = view1.findViewById(R.id.tv_optionA);
        textView4 = view1.findViewById(R.id.tv_optionB);
        textView5 = view1.findViewById(R.id.tv_optionC);
        textView6 = view1.findViewById(R.id.tv_optionD);
        textView7 = view1.findViewById(R.id.btn_sum);
        //第二页的所有控件
        text1 = view2.findViewById(R.id.tv_sign);
        text2 = view2.findViewById(R.id.tv_title);
        text3 = view2.findViewById(R.id.tv_optionA);
        text4 = view2.findViewById(R.id.tv_optionB);
        text5 = view2.findViewById(R.id.tv_optionC);
        text6 = view2.findViewById(R.id.tv_optionD);
        text7 = view2.findViewById(R.id.btn_sum);
        //第三页的所有控件
        tx1 = view3.findViewById(R.id.tv_sign);
        tx2 = view3.findViewById(R.id.tv_title);
        tx3 = view3.findViewById(R.id.tv_optionA);
        tx4 = view3.findViewById(R.id.tv_optionB);
        tx5 = view3.findViewById(R.id.tv_optionC);
        tx6 = view3.findViewById(R.id.tv_optionD);
        tx7 = view3.findViewById(R.id.btn_sum);
        //第四页的所有控件
        tv1 = view4.findViewById(R.id.tv_sign);
        tv2 = view4.findViewById(R.id.tv_title);
        tv3 = view4.findViewById(R.id.tv_optionA);
        tv4 = view4.findViewById(R.id.tv_optionB);
        tv5 = view4.findViewById(R.id.tv_optionC);
        tv6 = view4.findViewById(R.id.tv_optionD);
        tv7 = view4.findViewById(R.id.btn_sum);
        //第五页的所有控件
        t1 = view5.findViewById(R.id.tv_sign);
        t2 = view5.findViewById(R.id.tv_title);
        t3 = view5.findViewById(R.id.tv_optionA);
        t4 = view5.findViewById(R.id.tv_optionB);
        t5 = view5.findViewById(R.id.tv_optionC);
        t6 = view5.findViewById(R.id.tv_optionD);
        t7 = view5.findViewById(R.id.btn_sum);
        //第六页的所有控件
        v1 = view6.findViewById(R.id.tv_sign);
        v2 = view6.findViewById(R.id.tv_title);
        v3 = view6.findViewById(R.id.tv_optionA);
        v4 = view6.findViewById(R.id.tv_optionB);
        v5 = view6.findViewById(R.id.tv_optionC);
        v6 = view6.findViewById(R.id.tv_optionD);
        v7 = view6.findViewById(R.id.btn_sum);
        //第七页的所有控件
        e1 = view7.findViewById(R.id.tv_sign);
        e2 = view7.findViewById(R.id.tv_title);
        e3 = view7.findViewById(R.id.tv_optionA);
        e4 = view7.findViewById(R.id.tv_optionB);
        e5 = view7.findViewById(R.id.tv_optionC);
        e6 = view7.findViewById(R.id.tv_optionD);
        e7 = view7.findViewById(R.id.btn_sum);
        //第八页的所有控件
        x1 = view8.findViewById(R.id.tv_sign);
        x2 = view8.findViewById(R.id.tv_title);
        x3 = view8.findViewById(R.id.tv_optionA);
        x4 = view8.findViewById(R.id.tv_optionB);
        x5 = view8.findViewById(R.id.tv_optionC);
        x6 = view8.findViewById(R.id.tv_optionD);
        x7 = view8.findViewById(R.id.btn_sum);
        //第九页的所有控件
        i1 = view9.findViewById(R.id.tv_sign);
        i2 = view9.findViewById(R.id.tv_title);
        i3 = view9.findViewById(R.id.tv_optionA);
        i4 = view9.findViewById(R.id.tv_optionB);
        i5 = view9.findViewById(R.id.tv_optionC);
        i6 = view9.findViewById(R.id.tv_optionD);
        i7 = view9.findViewById(R.id.btn_sum);
        //第十页的所有控件
        w1 = view10.findViewById(R.id.tv_sign);
        w2 = view10.findViewById(R.id.tv_title);
        w3 = view10.findViewById(R.id.tv_optionA);
        w4 = view10.findViewById(R.id.tv_optionB);
        w5 = view10.findViewById(R.id.tv_optionC);
        w6 = view10.findViewById(R.id.tv_optionD);
        w7 = view10.findViewById(R.id.btn_sum);
        sendOkHttp();
        CustomOnClickListener listener = new CustomOnClickListener();
        textView1.setOnClickListener(listener);
        text1.setOnClickListener(listener);
        tx1.setOnClickListener(listener);
        tv1.setOnClickListener(listener);
        t1.setOnClickListener(listener);
        v1.setOnClickListener(listener);
        e1.setOnClickListener(listener);
        i1.setOnClickListener(listener);
        x1.setOnClickListener(listener);
        w1.setOnClickListener(listener);

        textView2.setOnClickListener(listener);
        text2.setOnClickListener(listener);
        tx2.setOnClickListener(listener);
        tv2.setOnClickListener(listener);
        t2.setOnClickListener(listener);
        v2.setOnClickListener(listener);
        e2.setOnClickListener(listener);
        i2.setOnClickListener(listener);
        x2.setOnClickListener(listener);
        w2.setOnClickListener(listener);

        textView3.setOnClickListener(listener);
        text3.setOnClickListener(listener);
        tx3.setOnClickListener(listener);
        tv3.setOnClickListener(listener);
        t3.setOnClickListener(listener);
        v3.setOnClickListener(listener);
        e3.setOnClickListener(listener);
        i3.setOnClickListener(listener);
        x3.setOnClickListener(listener);
        w3.setOnClickListener(listener);

        textView4.setOnClickListener(listener);
        text4.setOnClickListener(listener);
        tx4.setOnClickListener(listener);
        tv4.setOnClickListener(listener);
        t4.setOnClickListener(listener);
        v4.setOnClickListener(listener);
        e4.setOnClickListener(listener);
        i4.setOnClickListener(listener);
        x4.setOnClickListener(listener);
        w4.setOnClickListener(listener);

        textView5.setOnClickListener(listener);
        text5.setOnClickListener(listener);
        tx5.setOnClickListener(listener);
        tv5.setOnClickListener(listener);
        t5.setOnClickListener(listener);
        v5.setOnClickListener(listener);
        e5.setOnClickListener(listener);
        i5.setOnClickListener(listener);
        x5.setOnClickListener(listener);
        w5.setOnClickListener(listener);

        textView6.setOnClickListener(listener);
        text6.setOnClickListener(listener);
        tx6.setOnClickListener(listener);
        tv6.setOnClickListener(listener);
        t6.setOnClickListener(listener);
        v6.setOnClickListener(listener);
        e6.setOnClickListener(listener);
        i6.setOnClickListener(listener);
        x6.setOnClickListener(listener);
        w6.setOnClickListener(listener);

        textView7.setOnClickListener(listener);
        text7.setOnClickListener(listener);
        tx7.setOnClickListener(listener);
        tv7.setOnClickListener(listener);
        t7.setOnClickListener(listener);
        v7.setOnClickListener(listener);
        e7.setOnClickListener(listener);
        i7.setOnClickListener(listener);
        x7.setOnClickListener(listener);
        w7.setOnClickListener(listener);
    }

    @SuppressLint("ResourceType")
    private void inflate(){

        textView1.setText("第一题");
        textView2.setText(titles.get(0).getTopic());
        textView3.setText(titles.get(0).getOptionA());
        textView4.setText(titles.get(0).getOptionB());
        textView5.setText(titles.get(0).getOptionC());
        textView6.setText(titles.get(0).getOptionD());
        if(titles.get(0).getOptionC() == "null"){
            textView5.setVisibility(View.INVISIBLE);
            textView6.setVisibility(View.INVISIBLE);
        }else if(titles.get(0).getOptionD() == "null"){
            textView6.setVisibility(View.INVISIBLE);
        }

        text1.setText("第二题");
        text2.setText(titles.get(1).getTopic());
        text3.setText(titles.get(1).getOptionA());
        text4.setText(titles.get(1).getOptionB());
        text5.setText(titles.get(1).getOptionC());
        text6.setText(titles.get(1).getOptionD());
        if(titles.get(1).getOptionC() == "null"){
            text5.setVisibility(View.INVISIBLE);
            text6.setVisibility(View.INVISIBLE);
        }else if(titles.get(1).getOptionD() == "null"){
            text6.setVisibility(View.INVISIBLE);
        }

        tx1.setText("第三题");
        tx2.setText(titles.get(2).getTopic());
        tx3.setText(titles.get(2).getOptionA());
        tx4.setText(titles.get(2).getOptionB());
        tx5.setText(titles.get(2).getOptionC());
        tx6.setText(titles.get(2).getOptionD());
        if(titles.get(2).getOptionC() == "null"){
            tx5.setVisibility(View.INVISIBLE);
            tx6.setVisibility(View.INVISIBLE);
        }else if(titles.get(2).getOptionD() == "null"){
            tx6.setVisibility(View.INVISIBLE);
        }

        tv1.setText("第四题");
        tv2.setText(titles.get(3).getTopic());
        tv3.setText(titles.get(3).getOptionA());
        tv4.setText(titles.get(3).getOptionB());
        tv5.setText(titles.get(3).getOptionC());
        tv6.setText(titles.get(3).getOptionD());
        if(titles.get(3).getOptionC() == "null"){
            tv5.setVisibility(View.INVISIBLE);
            tv6.setVisibility(View.INVISIBLE);
        }else if(titles.get(3).getOptionD() == "null"){
            tv6.setVisibility(View.INVISIBLE);
        }

        t1.setText("第五题");
        t2.setText(titles.get(4).getTopic());
        t3.setText(titles.get(4).getOptionA());
        t4.setText(titles.get(4).getOptionB());
        t5.setText(titles.get(4).getOptionC());
        t6.setText(titles.get(4).getOptionD());
        if(titles.get(4).getOptionC() == "null"){
            t5.setVisibility(View.INVISIBLE);
            t6.setVisibility(View.INVISIBLE);
        }else if(titles.get(4).getOptionD() == "null"){
            t6.setVisibility(View.INVISIBLE);
        }

        v1.setText("第六题");
        v2.setText(titles.get(5).getTopic());
        v3.setText(titles.get(5).getOptionA());
        v4.setText(titles.get(5).getOptionB());
        v5.setText(titles.get(5).getOptionC());
        v6.setText(titles.get(5).getOptionD());
        if(titles.get(5).getOptionC() == "null"){
            v5.setVisibility(View.INVISIBLE);
            v6.setVisibility(View.INVISIBLE);
        }else if(titles.get(5).getOptionD() == "null"){
            v6.setVisibility(View.INVISIBLE);
        }

        e1.setText("第七题");
        e2.setText(titles.get(6).getTopic());
        e3.setText(titles.get(6).getOptionA());
        e4.setText(titles.get(6).getOptionB());
        e5.setText(titles.get(6).getOptionC());
        e6.setText(titles.get(6).getOptionD());
        if(titles.get(6).getOptionC() == "null"){
            e5.setVisibility(View.INVISIBLE);
            e6.setVisibility(View.INVISIBLE);
        }else if(titles.get(6).getOptionD() == "null"){
            e6.setVisibility(View.INVISIBLE);
        }

        x1.setText("第八题");
        x2.setText(titles.get(7).getTopic());
        x3.setText(titles.get(7).getOptionA());
        x4.setText(titles.get(7).getOptionB());
        x5.setText(titles.get(7).getOptionC());
        x6.setText(titles.get(7).getOptionD());
        if(titles.get(7).getOptionC() == "null"){
            x5.setVisibility(View.INVISIBLE);
            x6.setVisibility(View.INVISIBLE);
        }else if(titles.get(7).getOptionD() == "null"){
            x6.setVisibility(View.INVISIBLE);
        }

        i1.setText("第九题");
        i2.setText(titles.get(8).getTopic());
        i3.setText(titles.get(8).getOptionA());
        i4.setText(titles.get(8).getOptionB());
        i5.setText(titles.get(8).getOptionC());
        i6.setText(titles.get(8).getOptionD());
        if(titles.get(8).getOptionC() == "null"){
            i5.setVisibility(View.INVISIBLE);
            i6.setVisibility(View.INVISIBLE);
        }else if(titles.get(8).getOptionD() == "null"){
            i6.setVisibility(View.INVISIBLE);
        }

        w1.setText("第十题");
        w2.setText(titles.get(9).getTopic());
        w3.setText(titles.get(9).getOptionA());
        w4.setText(titles.get(9).getOptionB());
        w5.setText(titles.get(9).getOptionC());
        w6.setText(titles.get(9).getOptionD());
        if(titles.get(9).getOptionC() == "null"){
            w5.setVisibility(View.INVISIBLE);
            w6.setVisibility(View.INVISIBLE);
        }else if(titles.get(9).getOptionD() == "null"){
            w6.setVisibility(View.INVISIBLE);
        }
        w7.setVisibility(View.VISIBLE);

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);
        viewList.add(view6);
        viewList.add(view7);
        viewList.add(view8);
        viewList.add(view9);
        viewList.add(view10);

        myPagerAdapter = new MyPagerAdapter(viewList);
        viewPager.setAdapter(myPagerAdapter);
    }

    private void sendOkHttp(){
        String address = wang_zhi+"/title/getitle";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data = response.body().string();
                wrapMessage(data);
            }
        });
    }

    private List<Title> changeWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            List<Title> titleList = new ArrayList<>();
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String topic = jsonObject.getString("topic");
                String optionA = jsonObject.getString("optionA");
                String optionB = jsonObject.getString("optionB");
                String optionC = jsonObject.getString("optionC");
                String optionD = jsonObject.getString("optionD");
                String answer = jsonObject.getString("answer");
                Title title = new Title(id,topic,optionA,optionB,optionC,optionD,answer);
                titleList.add(title);
            }
            return titleList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void wrapMessage(String info){
        Message message = new Message();
        message.what = 100;
        message.obj = info;
        handler.sendMessage(message);
    }

    class CustomOnClickListener implements View.OnClickListener{

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View view) {
            int position = viewPager.getCurrentItem();
            switch (view.getId()){
                case R.id.tv_sign:
                    Toast.makeText(getApplicationContext(),"当前是第"+position+"页",Toast.LENGTH_LONG).show();
                    break;
                case R.id.tv_title:
                    break;
                case R.id.tv_optionA:
                    if(titles.get(position).getAnswer().equals("A")){
                        //显示是正确
                        Drawable drawable = getApplicationContext().getResources().getDrawable(R.drawable.bt_shapes);
                        view.setBackground(drawable);
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.bingou);
                        mediaPlayer.start();
                    }else if(!titles.get(position).getAnswer().equals("A")){
                        //显示是错误
                        Drawable drawable = getApplicationContext().getResources().getDrawable(R.drawable.bt_shapec);
                        view.setBackground(drawable);
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.wrong);
                        mediaPlayer.start();
                        score -= 5;
                    }
                    break;
                case R.id.tv_optionB:
                    if(titles.get(position).getAnswer().equals("B")){
                        //显示是正确
                        Drawable drawable = getApplicationContext().getResources().getDrawable(R.drawable.bt_shapes);
                        view.setBackground(drawable);
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.bingou);
                        mediaPlayer.start();
                    }else if(!titles.get(position).getAnswer().equals("B")){
                        //显示是错误
                        Drawable drawable = getApplicationContext().getResources().getDrawable(R.drawable.bt_shapec);
                        view.setBackground(drawable);
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.wrong);
                        mediaPlayer.start();
                        score -= 5;
                    }
                    break;
                case R.id.tv_optionC:
                    if(titles.get(position).getAnswer().equals("C")){
                        //显示是正确
                        Drawable drawable = getApplicationContext().getResources().getDrawable(R.drawable.bt_shapes);
                        view.setBackground(drawable);
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.bingou);
                        mediaPlayer.start();
                    }else if(!titles.get(position).getAnswer().equals("C")){
                        //显示是错误
                        Drawable drawable = getApplicationContext().getResources().getDrawable(R.drawable.bt_shapec);
                        view.setBackground(drawable);
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.wrong);
                        mediaPlayer.start();
                        score -= 5;
                    }
                    break;
                case R.id.tv_optionD:
                    if (titles.get(position).getAnswer().equals("D")){
                        //显示是正确
                        Drawable drawable = getApplicationContext().getResources().getDrawable(R.drawable.bt_shapes);
                        view.setBackground(drawable);
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.bingou);
                        mediaPlayer.start();
                    }else if(!titles.get(position).getAnswer().equals("D")){
                        //显示是错误
                        Drawable drawable = getApplicationContext().getResources().getDrawable(R.drawable.bt_shapec);
                        view.setBackground(drawable);
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.wrong);
                        mediaPlayer.start();
                        score -= 5;
                    }
                    break;
                case R.id.btn_sum:
                    Intent intent = new Intent(TitleActivity.this,SumActivity.class);
                    intent.putExtra("score",score);
                    TitleActivity.this.finish();
                    startActivity(intent);
                    break;
            }
        }
    }
}

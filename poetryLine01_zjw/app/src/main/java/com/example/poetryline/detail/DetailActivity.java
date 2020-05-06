package com.example.poetryline.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poetryline.R;
import com.example.poetryline.detail.example.ExampleAdapter;
import com.example.poetryline.detail.example.Record;
import com.example.poetryline.detail.history.DBManager;
import com.example.poetryline.detail.manager.MediaManager;
import com.example.poetryline.detail.utils.PermissionHelper;
import com.example.poetryline.detail.utils.Resources;

public class DetailActivity extends AppCompatActivity {
    private TextView pin;
    private TextView zhu;
    private TextView yi;
    private TextView one;
    private TextView titlez;
    private TextView jiazai;
    private ScrollView gunlun;
    private TextView chaodaia;
    private TextView shang;
    private TextView more;
    private TextView zuozhed;
    private TextView voice;
    private TextView content;
    private ImageView back;
    private ImageView share;
    private ImageView zhankai;
    private Intent shareIntent;
    private TextView zhankai1;
    private MyScrollView contentz;
    private ListView mEmLvRecodeList;
    private String name;
    private Resources sources=new Resources();
    private RelativeLayout suofang;
    List<Record> mRecords;
    ExampleAdapter mExampleAdapter;
    PermissionHelper mHelper;
    //db
    private DBManager mgr;
    private Handler handler;
    private CustomeClickListener listener;
    private String str;
    private String str2;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        this.getSupportActionBar().hide();
        Intent intent=this.getIntent();
        sources=new Resources();
        name=(String)intent.getStringExtra("name");
        inits();
        registerListeners();
        initView();
        initData();
        initAdapter();
//        wrapMessage1(ggg);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 100:
                        sources = (Resources) msg.obj;
                        titlez.setText(sources.getTitle());
                    chaodaia.setText(sources.getChaodai());
                    one.setText(sources.getContent());
                    content.setText(sources.getPinxi());
                    zuozhed.setText(str);
                    gunlun.setVisibility(View.VISIBLE);
                    jiazai.setVisibility(View.GONE);
                        break;

                }
            }
        };

        showMeterial();


        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "快来下载app前来查看分享吧！");
        initfonts();



//        initListener();

    }
    private void initfonts(){
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/xihei.ttf");
        Typeface face1=Typeface.createFromAsset(getAssets(),"fonts/yishuhei.ttf");
        Typeface face2=Typeface.createFromAsset(getAssets(),"fonts/xingkai.ttf");
        content.setTypeface(face);
        pin.setTypeface(face1);
        zhankai1.setTypeface(face1);
        yi.setTypeface(face1);
        zhu.setTypeface(face1);
        shang.setTypeface(face1);
        one.setTypeface(face2);
//        two.setTypeface(face2);
//        three.setTypeface(face2);

    }
    private void registerListeners() {
        listener = new CustomeClickListener();
        pin.setOnClickListener(listener);
        zhu.setOnClickListener(listener);
        yi.setOnClickListener(listener);
        back.setOnClickListener(listener);
        share.setOnClickListener(listener);
        voice.setOnClickListener(listener);
        more.setOnClickListener(listener);
        shang.setOnClickListener(listener);
        contentz.setOnClickListener(listener);
        suofang.setOnClickListener(listener);


    }
    class CustomeClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.pin:
                    pin.setTextColor(Color.parseColor("#A52A2A"));
                    zhu.setTextColor(Color.parseColor("#838B8B"));
                    yi.setTextColor(Color.parseColor("#838B8B"));
                    shang.setTextColor(Color.parseColor("#838B8B"));
                    content.setText(sources.getPinxi());
                    break;
                case R.id.zhu:
                    pin.setTextColor(Color.parseColor("#838B8B"));
                    yi.setTextColor(Color.parseColor("#838B8B"));
                    shang.setTextColor(Color.parseColor("#838B8B"));
                    zhu.setTextColor(Color.parseColor("#A52A2A"));
                    content.setText(sources.getZhushi());
                    break;
                case R.id.yi:

                    pin.setTextColor(Color.parseColor("#838B8B"));
                    yi.setTextColor(Color.parseColor("#A52A2A"));
                    zhu.setTextColor(Color.parseColor("#838B8B"));
                    shang.setTextColor(Color.parseColor("#838B8B"));
                    content.setText(sources.getYiwen());
                    break;
                case R.id.shang:
                    pin.setTextColor(Color.parseColor("#838B8B"));
                    yi.setTextColor(Color.parseColor("#838B8B"));
                    zhu.setTextColor(Color.parseColor("#838B8B"));
                    shang.setTextColor(Color.parseColor("#A52A2A"));

                    content.setText(sources.getShangxi());
                    break;
                case R.id.more:
                    Intent intent = new Intent(DetailActivity.this, MoreActivity.class);
                    intent.putExtra("name",str2);
                    startActivity(intent);
//                    Intent intent = new Intent(DetailActivity.this, MoreActivity.class);
//                    intent.putExtra("name","");
//                    startActivity(intent);
                    break;
                case R.id.voice:
                    Intent intent2 = new Intent(DetailActivity.this, VoiceActivity.class);

                    startActivity(intent2);
                    break;
                case R.id.share:
                    Intent intent5 = Intent.createChooser(shareIntent, "分享一段文本信息");
                    if (shareIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent5);
                    }
                    break;
                case R.id.back:
                    finish();
                case R.id.suofang:
                    ViewGroup.LayoutParams params=contentz.getLayoutParams();
                    if(zhankai1.getText().toString().contains("展开")){
                        contentz.setScroll(false);
                        params.height=params.WRAP_CONTENT;
                        zhankai.setImageDrawable(getResources().getDrawable(R.drawable.shouhui));
                        zhankai1.setText("收起");
                    }
                    else{
                        contentz.setScroll(true);
                        params.height=550;
                        zhankai.setImageDrawable(getResources().getDrawable(R.drawable.zhankai));

                        zhankai1.setText("展开");
                    }

//                    Intent intent8 = new Intent(DetailActivity.this, MoreActivity.class);
//                    intent8.putExtra("name","李白");
//                    startActivity(intent8);
                    break;








            }
        }
    }
    private void initView() {
        mEmLvRecodeList = (ListView) findViewById(R.id.em_lv_recodeList2);
//        mEmTvBtn = (AudioRecordButton) findViewById(R.id.em_tv_btn);
        //设置不想要可见或者不想被点击
        // mEmTvBtn.setVisibility(View.GONE);//隐藏
        // mEmTvBtn.setCanRecord(false);//重写该方法，设置为不可点击
    }

    private void initData() {
        mRecords = new ArrayList<>();
        //初始化DBManager
        mgr = new DBManager(this);
    }

    private void initAdapter() {
        mExampleAdapter = new ExampleAdapter(this, mRecords);
        mEmLvRecodeList.setAdapter(mExampleAdapter);

        //开始获取数据库数据
        List<Record> records = mgr.query();
        if (records == null || records.isEmpty()) return;
        for (Record record : records) {
            Log.e("wgy", "initAdapter: " + record.toString());
        }
        mRecords.addAll(records);
        mExampleAdapter.notifyDataSetChanged();
        mEmLvRecodeList.setSelection(mRecords.size() - 1);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mHelper.handleRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onPause() {
        MediaManager.release();//保证在退出该页面时，终止语音播放
        super.onPause();
    }

    public DBManager getMgr() {
        return mgr;
    }

    public void setMgr(DBManager mgr) {
        this.mgr = mgr;
    }
    public  void inits(){
        zuozhed=findViewById(R.id.zhuozhed);
        pin=findViewById(R.id.pin);
        content=findViewById(R.id.content);
        zhu=findViewById(R.id.zhu);
        more=findViewById(R.id.more);
        share=findViewById(R.id.share);
        back=findViewById(R.id.back);
        voice=findViewById(R.id.voice);
        titlez=findViewById(R.id.titlez);
        chaodaia=findViewById(R.id.chaodai);
        yi=findViewById(R.id.yi);
        shang=findViewById(R.id.shang);
        contentz=findViewById(R.id.contentz);
       gunlun=findViewById(R.id.gunlun);
        jiazai=findViewById(R.id.jiazai);
        suofang=findViewById(R.id.suofang);
        zhankai1=findViewById(R.id.zhankai1);
        zhankai=findViewById(R.id.zhankai);
        one=findViewById(R.id.one);
//        two=findViewById(R.id.two);
//        three=findViewById(R.id.three);

    }
    private void showMeterial(){
        new Thread(){
            @Override
            public void run() {
                try {
                    //从一个URL加载一个Document对象。
                    Document doc = Jsoup.connect("https://so.gushiwen.org/search.aspx?value="+name).get();
                    //选择“美食天下”所在节点
                    Elements elements = doc.getElementsByClass("cont");
                    Elements p=elements.select("p");
                    String address=p.get(0).select("a").attr("href");
                    Document docs= Jsoup.connect("https://so.gushiwen.org"+address).get();
                    Elements elementss = docs.getElementsByClass("cont");
                    Elements elementauthor=docs.getElementsByClass("source");
                    Elements content=docs.getElementsByClass("contson");
                    Elements content1=docs.getElementsByClass("tag");
                    Elements content2=docs.getElementsByClass("contyishang");
                    Elements content3=docs.getElementsByClass("sonspic");
                    Elements h1=elementss.select("h1");
                    //分类
                    String []tag=content1.get(0).text().split("，");
                    //诗题目
                    String title=h1.get(0).text();
                    //朝代作者
                    String chaodai="["+elementauthor.select("a").get(0).text()+"]  "+elementauthor.select("a").get(1).text();
                    //诗内容
                    String []contentb=content.get(0).html().split("<br>");
                    int b=contentb.length;


                    String contenta=content.get(0).html().replace("<br>","\n").replace("<p>","").replace("</p>","");
                    //翻译
                    String yiwen=content2.get(0).select("p").get(0).text().replace("<br>","\n");
                    //注释
                    String zhushi=content2.get(0).select("p").get(content2.get(0).select("p").size()-1).html().replace("<br>","\n");
                    //赏析
                    String shangxi=content2.get(1).select("p").get(0).text();

                    String zuozhe=content3.get(0).text();
                    String zuozhe2=elementauthor.select("a").get(1).text();
                    int aaa=content2.get(1).select("p").size();
                    //评析
                    String pingxi="";
                    for(int i=1;i<aaa;i++){
                        pingxi+=content2.get(1).select("p").get(i).text()+"\n";
                    }
                    sources.setTitle(title);
                    sources.setChaodai(chaodai);
                    sources.setContent(contenta);
                    sources.setPinxi(pingxi);
                    sources.setShangxi(shangxi);
                    sources.setZhushi(zhushi);
                    sources.setYiwen(yiwen);
                    sources.setTag(tag);
                    str=zuozhe;
                    str2=zuozhe2;

//                    titlez.setText(sources.getTitle());
//                    chaodaia.setText(sources.getChaodai());
//                    one.setText(sources.getContent());
                    Message message = Message.obtain();
                    message.obj =sources;
                    message.what = 100;
                    handler.sendMessage(message);



                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }







}

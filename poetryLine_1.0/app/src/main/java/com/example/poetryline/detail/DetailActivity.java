package com.example.poetryline.detail;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.R;
import com.example.poetryline.collect.CollectActivity;
import com.example.poetryline.collect.database.MyDB;
import com.example.poetryline.collect.enity.CollectRecord;
import com.example.poetryline.detail.example.ExampleAdapter;
import com.example.poetryline.detail.example.Record;
import com.example.poetryline.detail.example.showDialog;
import com.example.poetryline.detail.history.DBManager;
import com.example.poetryline.detail.manager.MediaManager;
import com.example.poetryline.detail.utils.PermissionHelper;
import com.example.poetryline.detail.utils.Resources;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {
    private TextView pin;
    private TextView zhu;
    private ImageView iv;
    private showDialog show= new showDialog();
    private TextView yi;
    private TextView one;
    private RelativeLayout jjz;
    private AnimationDrawable animationDrawable;
    private TextView titlez;
    private RelativeLayout jiazai;
    private MyScrollView gunlun;
    private MyScrollView gunlun2;
    private TextView chaodaia;
    private TextView tv_title;
    private TextView shang;
    private TextView quanping;
    private RelativeLayout quanming;
    private LinearLayout tupiana;
    private RelativeLayout jianbian;
    private RelativeLayout trya;
    private TextView more;
    private TextView zuozhed;
    private LinearLayout tupianaa;
    private MyScrollView gunlun22;
    private TextView titlezz;
    private TextView chaodaii;
    private PopupWindow popupWindow;
    private TextView onee;
    private TextView voice;
    private TextView content;
    private LinearLayout pinpin;
    private TextView contentsss;
    private LinearLayout duba;
    private ImageView back;
    private ImageView share;
    private int heig;
    private TextView content1;
    private TextView content2;
    private TextView content3;

    private ImageView zhankai;
    private Intent shareIntent;
    private TextView zhankai1;
    private RelativeLayout contentz;
    private MyScrollView contentzz;
    private Timer timer;
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
    private int chans;
    private int widthaaa;
    private int posi;
    private String  bname;


    private String poetryname=null;
    private boolean isSetAdapter = false;

    private ImageView shoucang;

    private int recLen = 0;

    private Runnable runnable;



    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        this.getSupportActionBar().hide();
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setStatusBarTransparent();
        Intent intent=this.getIntent();
        sources=new Resources();
        name=(String)intent.getStringExtra("name");

        inits();
        registerListeners();
        initView();
        initData();

//        tututut();



//        initAdapter();
        gunlun.setScrollViewListener(new MyOnScrollChangeListener());
//        gunlun2.setScrollViewListener(new MyOnScrollChangeListener());



//        wrapMessage1(ggg);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 100:
                        sources = (Resources) msg.obj;
                        titlez.setText(sources.getTitle());
                        poetryname=sources.getTitle();
                        if (!isSetAdapter){
                            initAdapter(sources.getTitle());//加载语音listView
                        }


                        titlezz.setText(sources.getTitle());
                        chaodaia.setText(sources.getChaodai());
                        chaodaii.setText(sources.getChaodai());
                        one.setText(sources.getContent());
                        onee.setText(sources.getContent());
                        content.setText(sources.getPinxi());
                        contentsss.setText(sources.getPinxi());
                        zuozhed.setText(str);
                        show.setFff(sources.getTupian());
                        show.setAlla(sources.getAll());
//                        contentsss.setText(sources.getAll());
//                        content.setText(sources.getAll());
                        WindowManager windowManager = (WindowManager)DetailActivity.this.getSystemService(WINDOW_SERVICE);
                        //获取屏幕对象
                        Display defaultDisplay = windowManager.getDefaultDisplay();
                        //获取屏幕的宽、高，单位是像素
                        int width = defaultDisplay.getWidth();
                        int height = defaultDisplay.getHeight();
                        show.setWidth(width);
                        show.setHeight(height);
                        show.setZuozhem(sources.getChaodai());
                        gunlun.setVisibility(View.VISIBLE);
                        tv_title.setText(sources.getTitle());
                        animationDrawable.stop();
                        jjz.setVisibility(View.GONE);
//                       jiazai.setVisibility(View.GONE);
                        break;
                    case 200:
                        ViewGroup.LayoutParams params=contentz.getLayoutParams();
                        params.height=(Integer)msg.obj;
                        contentz.setLayoutParams(params);
                        break;
//                        tv_title.setText(""+msg.obj);
                    case 400:
                        pin.setText(msg.obj+"");
                        break;
                    case 500:
//                        pin.setText(msg.obj+"");
                        ViewGroup.LayoutParams paramsq=gunlun2.getLayoutParams();
                        paramsq.height=(Integer)msg.obj;
                        gunlun2.setLayoutParams(paramsq);
                        break;
                        
                }
            }
        };

        showMeterial();

        popupWindow = new PopupWindow(contentz,200,300);//参数为1.View 2.宽度 3.高度
        popupWindow.setOutsideTouchable(true);


        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "快来下载app前来查看分享吧！");
        initfonts();



//        initListener();

    }
    private class MyOnScrollChangeListener implements MyScrollView.OnScrollChangeListener {



        @Override
        public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {

            jianbian.setVisibility(View.VISIBLE);
            changeAphlaTitle(oldy, y);
        }
    }
    /**
     * 根据内容窗体的移动改变标题栏背景透明度
     *
     * @param startY scrollview开始滑动的y坐标（相对值）
     * @param endY   scrollview结束滑动的y坐标（相对值）
     */
    private void changeAphlaTitle(int startY, int endY) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tupiana.getLayoutParams();

        double num=0.0;
        num=(double)(endY)/1000;
        int gjg=(new Double(num*550)).intValue();
        int gjgg=(new Double(num*550)).intValue();
        int gjggg=(new Double(num*550)).intValue();
        if(gjg>=255){
            gjg=255;
        }
        if(gjgg>=255){
            gjgg=255;
        }
        if(gjggg>=255){
            gjggg=255;
        }
        back.setColorFilter(Color.rgb(gjg, gjgg, gjggg));
        share.setColorFilter(Color.rgb(gjg, gjgg, gjggg));
//        if(startY<50){
//            jianbian.setAlpha(0);
//        }else {
        jianbian.setAlpha((float)(num*2));
//        }
//        tv_title.setText(endY+"");

    }

    private void initfonts(){
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/xihei.ttf");
        Typeface face1=Typeface.createFromAsset(getAssets(),"fonts/yishuhei.ttf");
        Typeface face2=Typeface.createFromAsset(getAssets(),"fonts/xingkai.ttf");
        content.setTypeface(face);
        content1.setTypeface(face);
        pin.setTypeface(face1);
//        zhankai1.setTypeface(face1);
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
        quanming.setOnClickListener(listener);
        yi.setOnClickListener(listener);
        back.setOnClickListener(listener);
        share.setOnClickListener(listener);
        voice.setOnClickListener(listener);
        tv_title.setOnClickListener(listener);
        more.setOnClickListener(listener);
        titlez.setOnClickListener(listener);
        shang.setOnClickListener(listener);
        back.setOnClickListener(listener);
        contentz.setOnClickListener(listener);
        suofang.setOnClickListener(listener);

        shoucang.setOnClickListener(listener);


    }
    class CustomeClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.pin:
                    zhankai.setImageDrawable(getResources().getDrawable(R.drawable.zhankai));
                    pin.setTextColor(Color.parseColor("#0891D6"));
                    zhu.setTextColor(Color.parseColor("#838B8B"));
                    yi.setTextColor(Color.parseColor("#838B8B"));
                    shang.setTextColor(Color.parseColor("#838B8B"));
                    content1.setText(content.getText());
                    content.setText(sources.getPinxi());
                    contentsss.setText(sources.getPinxi());
                    ViewGroup.LayoutParams params=contentz.getLayoutParams();
                    params.height=550;
                    zhankai1.setText("展开");
                    contentz.setLayoutParams(params);


                    tansss(chans,0);
                    chans=0;

                    break;
                case R.id.zhu:
                    content1.setText(content.getText());
                    zhankai.setImageDrawable(getResources().getDrawable(R.drawable.zhankai));
                    zhankai1.setText("展开");
                    pin.setTextColor(Color.parseColor("#838B8B"));
                    yi.setTextColor(Color.parseColor("#838B8B"));
                    shang.setTextColor(Color.parseColor("#838B8B"));
                    zhu.setTextColor(Color.parseColor("#0891D6"));
                    content.setText(sources.getZhushi());
                    contentsss.setText(sources.getZhushi());
                    ViewGroup.LayoutParams paramss=contentz.getLayoutParams();
                    paramss.height=550;
                    contentz.setLayoutParams(paramss);
//                    contentzz.setLayoutParams(paramss);
                    tansss(chans,1);
                    chans=1;

                    break;
                case R.id.yi:
                    content1.setText(content.getText());
                    zhankai.setImageDrawable(getResources().getDrawable(R.drawable.zhankai));
                    zhankai1.setText("展开");
                    pin.setTextColor(Color.parseColor("#838B8B"));
                    yi.setTextColor(Color.parseColor("#0891D6"));
                    zhu.setTextColor(Color.parseColor("#838B8B"));
                    shang.setTextColor(Color.parseColor("#838B8B"));
                    content.setText(sources.getYiwen());
                    ViewGroup.LayoutParams paramssss=contentz.getLayoutParams();
                    paramssss.height=550;
                    contentz.setLayoutParams(paramssss);
                    contentsss.setText(sources.getYiwen());
//                    contentzz.setLayoutParams(paramssss);
                    tansss(chans,2);

                    chans=2;
                    break;
                case R.id.shang:
                    content1.setText(content.getText());
                    zhankai1.setText("展开");
                    zhankai.setImageDrawable(getResources().getDrawable(R.drawable.zhankai));
                    pin.setTextColor(Color.parseColor("#838B8B"));
                    yi.setTextColor(Color.parseColor("#838B8B"));
                    zhu.setTextColor(Color.parseColor("#838B8B"));
                    content.setText(sources.getShangxi());
                    ViewGroup.LayoutParams params1=contentz.getLayoutParams();
                    params1.height=550;
                    contentz.setLayoutParams(params1);
                    shang.setTextColor(Color.parseColor("#0891D6"));


                    contentsss.setText(sources.getShangxi());
//                    contentzz.setLayoutParams(params1);
                    tansss(chans,3);
                    chans=3;


                    break;
                case R.id.more:
//                    Intent intent = new Intent(DetailActivity.this, MoreActivity.class);
//                    intent.putExtra("name",str2);
//                    startActivity(intent);

                    show.BottomDialog(DetailActivity.this);
//                        popupWindow.showAtLocation(DetailActivity.this.getWindow(), Gravity.CENTER,0,0);
                    break;

                case R.id.voice:
                    Intent intent2 = new Intent(DetailActivity.this, VoiceActivity.class);
                    intent2.putExtra("titlez",titlez.getText());
                    intent2.putExtra("chaodaia",chaodaia.getText());
                    intent2.putExtra("one",one.getText());
//                    Toast.makeText(getApplicationContext(),"voice",Toast.LENGTH_SHORT).show();


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
                    int h1=contentz.getHeight();
                    int h2 = 0;

                    if(zhankai1.getText().toString().contains("展开")){
//                        contentz.setScroll(false);

//                        paramss.height=params.WRAP_CONTENT;
//                        contentz.setLayoutParams(params);
                        zhankaia();
//                        tv_title.setText(String.valueOf(params.height));
                        zhankai.setImageDrawable(getResources().getDrawable(R.drawable.shouhui));
                        zhankai1.setText("收起");
//                        h2=params.height;
                    }
                    else{
//                        contentz.setScroll(true);
//                        params.height=550;
//                        contentz.setLayoutParams(params);

                        zhankaiaa();
                        zhankai.setImageDrawable(getResources().getDrawable(R.drawable.zhankai));
//                        tv_title.setText(String.valueOf(params.height));
                        zhankai1.setText("展开");

//                        tv_title.setText(String.valueOf(params.height));



                    }
                    h2=contentz.getHeight();
//                    tv_title.setText(h1+""+h2+"");
//                    zhankai(h1,h2);
//                    Intent intent8 = new Intent(DetailActivity.this, MoreActivity.class);
//                    intent8.putExtra("name","李白");
//                    startActivity(intent8);
                    break;
                case R.id.quanming:
                    if(quanping.getText().toString().contains("全屏")){
                        quanmingm(1);
//                        pin.setText(tupianaa.getMeasuredHeight()+"");
                        quanping.setText("折叠");
                    }else{
                        quanmingm(2);
//                        pin.setText(tupianaa.getMeasuredHeight()+"");
                        quanping.setText("全屏");
                    }

                    break;

                case  R.id.shoucang:

                    shoucang.setColorFilter(Color.rgb(255,0,0));
                    Date now = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");//可以方便地修改日期格式
                    String createDate = dateFormat.format( now );
                    saveFunction(poetryname,createDate);
//                    if (saveFunction(poetryname,createDate)){
                        Toast.makeText(getApplicationContext(),"收藏成功",Toast.LENGTH_SHORT).show();
//                    }
//                    Toast.makeText(getApplicationContext(),"收藏成功"+poetryname,Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }

    private void saveFunction(String title,String createDate){
        MyDB myDB;
        myDB = new MyDB(this);
        boolean flag = true;
        if(flag){
            SQLiteDatabase db;
            ContentValues values;
            //  存储信息
            db = myDB.getWritableDatabase();
            values = new ContentValues();
            values.put(MyDB.RECORD_TITLE,title);
            values.put(MyDB.RECORD_TIME,createDate);
            db.insert(MyDB.TABLE_NAME_RECORD,null,values);
            db.close();
        }
//        return flag;
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

    private void initAdapter(String str) {
        mExampleAdapter = new ExampleAdapter(this, mRecords);
        mRecords.clear();
        mEmLvRecodeList.setAdapter(mExampleAdapter);

        //开始获取数据库数据

//        Toast.makeText(getApplicationContext(),"titlez:"+str,Toast.LENGTH_LONG).show();
//        List<Record> records = mgr.query(str);//要获取当前诗的数据
//        if (records == null || records.isEmpty()) return;
//        for (Record record : records) {
//            Log.e("wgy", "initAdapter: " + record.toString());
//        }

        //开始获取数据库数据 根据诗名
        List<Record> records = new ArrayList<Record>();
        MyDB myDB;
        myDB = new MyDB(this);
        SQLiteDatabase db = myDB.getReadableDatabase();
        Cursor c = db.query("Record",null,
                "poetryName=?",new String[]{str},null
                ,
                null,null
        );

        if(c.moveToFirst()){
            Record record;
            while (!c.isAfterLast()){
                record = new Record();
                record.setId(c.getString(c.getColumnIndex("id")));
                record.setPath(c.getString(c.getColumnIndex("path")));
                record.setSecond(c.getInt(c.getColumnIndex("second")));
                record.setPlayed(c.getInt(c.getColumnIndex("isPlayed"))==0?true:false);
                record.setIsZan(c.getInt(c.getColumnIndex("isZan")));
                record.setNum(c.getInt(c.getColumnIndex("num")));
                //昵称
                record.setName(c.getString(c.getColumnIndex("name")));
                //时间
                record.setDate(c.getString(c.getColumnIndex("date")));
                //头像
                record.setUserPhone(c.getString(c.getColumnIndex("userPhone")));
                //诗名
                record.setPeotryName(c.getString(c.getColumnIndex("poetryName")));

                records.add(record);
                c.moveToNext();
            }
        }
        c.close();
        db.close();

//        Toast.makeText(getApplicationContext(),str +records.toString(),Toast.LENGTH_LONG).show();
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
        WindowManager windowManager = (WindowManager)DetailActivity.this.getSystemService(WINDOW_SERVICE);
        //获取屏幕对象
        Display defaultDisplay = windowManager.getDefaultDisplay();
        //获取屏幕的宽、高，单位是像素
        widthaaa = defaultDisplay.getWidth();
        quanping=findViewById(R.id.quanping);
        tupianaa=findViewById(R.id.tupianaa);
        iv=findViewById(R.id.iv);
        jjz=findViewById(R.id.jjz);
        titlezz=findViewById(R.id.titlezz);
        chaodaii=findViewById(R.id.chaodaii);
        onee=findViewById(R.id.onee);
        gunlun22=findViewById(R.id.gunlun22);
        quanming=findViewById(R.id.quanming);
        zuozhed=findViewById(R.id.zhuozhed);
        pin=findViewById(R.id.pin);
        tupiana=findViewById(R.id.tupiana);
        content1=findViewById(R.id.content1);

        chans=0;
        posi=0;
        gunlun2=findViewById(R.id.gunlun2);
        pinpin=findViewById(R.id.pinpin);
        int bbbb=(int)(1+Math.random()*(10-1+1));
        if(bbbb==1){

            tupiana.setBackgroundResource(R.drawable.timgf2);
        }else if(bbbb==2){

            tupiana.setBackgroundResource(R.drawable.timgf12);
        }else if(bbbb==3){

            tupiana.setBackgroundResource(R.drawable.timgf3);
        }
        else if(bbbb==4){

            tupiana.setBackgroundResource(R.drawable.timgf9);
        }
        else if(bbbb==5){

            tupiana.setBackgroundResource(R.drawable.timgf8);
        }
        else if(bbbb==6){

            tupiana.setBackgroundResource(R.drawable.timgf8);
        }
        else if(bbbb==7){

            tupiana.setBackgroundResource(R.drawable.timgf9);
        }
        else if(bbbb==8){

            tupiana.setBackgroundResource(R.drawable.timgf8);
        }else if(bbbb==9){

            tupiana.setBackgroundResource(R.drawable.timgf9);
        }else{

            tupiana.setBackgroundResource(R.drawable.timgf10);
        }

        shoucang = findViewById(R.id.shoucang);

        jianbian=findViewById(R.id.jianbian);
        content=findViewById(R.id.content);
        contentsss=findViewById(R.id.contentsss);
        zhu=findViewById(R.id.zhu);
        more=findViewById(R.id.more);
        trya=findViewById(R.id.trya);
        share=findViewById(R.id.share);
        back=findViewById(R.id.back);
        voice=findViewById(R.id.voice);
        duba=findViewById(R.id.duba);
        titlez=findViewById(R.id.titlez);
        chaodaia=findViewById(R.id.chaodai);
        yi=findViewById(R.id.yi);
        shang=findViewById(R.id.shang);
        tv_title=findViewById(R.id.tv_title);
        contentz=findViewById(R.id.contentz);
        contentzz=findViewById(R.id.contentzz);
        gunlun=findViewById(R.id.gunlun);
        jiazai=findViewById(R.id.jiazai);
        suofang=findViewById(R.id.suofang);
        zhankai1=findViewById(R.id.zhankai1);
        zhankai=findViewById(R.id.zhankai);
        one=findViewById(R.id.one);

        //收藏
        RelativeLayout.LayoutParams lp3 = (RelativeLayout.LayoutParams) shoucang.getLayoutParams();
        lp3.topMargin = dip2px(DetailActivity.this,11)+ getStatusBarHeight(DetailActivity.this);
        shoucang.setLayoutParams(lp3);


        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(DetailActivity.this,56)+ getStatusBarHeight(DetailActivity.this));
        lp.setMargins(0,0, 0, 0);
        jianbian.setLayoutParams(lp);

        RelativeLayout.LayoutParams lp1 = (RelativeLayout.LayoutParams) back.getLayoutParams();
        lp1.topMargin = dip2px(DetailActivity.this,11)+ getStatusBarHeight(DetailActivity.this);
        back.setLayoutParams(lp1);
        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) share.getLayoutParams();
        lp2.topMargin = dip2px(DetailActivity.this,11)+ getStatusBarHeight(DetailActivity.this);
        share.setLayoutParams(lp2);
        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 550);
        lp4.setMargins(0,0, 0, 0);
        contentz.setLayoutParams(lp4);
        RelativeLayout.LayoutParams lpg = (RelativeLayout.LayoutParams)quanming.getLayoutParams();
        lpg.topMargin = dip2px(DetailActivity.this,70)+ getStatusBarHeight(DetailActivity.this);
        quanming.setLayoutParams(lpg);
        animationDrawable = (AnimationDrawable) iv.getBackground();
        //判断是否在运行
        if(!animationDrawable.isRunning()){
            //开启帧动画
            animationDrawable.start();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(poetryname==null){
                        Toast.makeText(getApplicationContext(),"抱歉 未找到此诗的详细信息",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }, 5000);//5秒后执行Runnable中的run方法 退出详情activity

        }





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

                    Elements zuozhej=docs.getElementsByClass("cont");
                    String zuozheurl=zuozhej.get(2).select("div").get(0).select("a").get(0).attr("href");
                    Document docss= Jsoup.connect("https://so.gushiwen.org"+zuozheurl).get();
                    Elements imgj=docss.getElementsByClass("divimg");
                    //简介
//                    String all=docss.getElementsByClass("main3").get(0).select("div").get(0).select("div").get(1).select("div").get(0).select("p").get(0).html();
                    //全部
                    String all="";
//                    int ii=537;

                    for(int i=0;i<2000;i++){



//                        String all=docss.getElementById("fanyi"+i).text();
                        try {


//                            Elements newa=docss.getElementById("fanyi"+ii).select("div");
//                            String urll=newa.get(newa.size()-1).select("a").get(0).attr("href");
//
//                            Document docsss=Jsoup.connect(urll).get();
                            all+=docss.getElementById("fanyi"+i).html().replace("<br>","\n").replace("<p>","").replace("</p>","").replaceAll("<h2>(.*)</h2>","").replaceAll("<a(.*)</a>","").replaceAll("<span(.*)</span>","").replaceAll("<div(.*)</div>","").replaceAll("<div(.*)>","").replace("</div>","").replace("\n\n\n\n","\n");
                        } catch (Exception e) {
                            continue;
                        }
//                        if(docss.getElementById("fanyi"+i).text()==null){
//                            continue;
//                        }else{
//                            all+=docss.getElementById("fanyi"+i).text()==null;
//                        }
                    }
//                    String all=docss.getElementById("fanyi536").text();
                    //图片
                    String imgurl=imgj.select("img").attr("src");
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
                    sources.setTupian(imgurl);
                    sources.setAll(all);
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
    private void zhankaia(){
        new Thread(){
            @Override
            public void run() {
                try {
                    int[] location = new int[2];
                    pinpin.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    int hh=contentzz.getHeight();
//                    tv_title.setText(""+hh);
                    for(int i=550;i<hh;i=i+2){

                        Message message = Message.obtain();
                        message.obj =i;
                        message.what = 200;
                        handler.sendMessage(message);
                        sleep(1);
                    }




//                    titlez.setText(sources.getTitle());
//                    chaodaia.setText(sources.getChaodai());
//                    one.setText(sources.getContent());




                }catch(Exception e) {
                    e.printStackTrace();
                }
            }

        }.start();


    }
    private void zhankaiaa(){
        new Thread(){
            @Override
            public void run() {
                try {

                    int hh=contentzz.getMeasuredHeight();
                    if(hh<=550){
                        hh=551;
                    }
//                    tv_title.setText(""+hh);
                    for(int i=hh;i>550;i=i-2){

                        Message message = Message.obtain();
                        message.obj =i;
                        message.what = 200;
                        handler.sendMessage(message);
                        sleep(1);
                    }




//                    titlez.setText(sources.getTitle());
//                    chaodaia.setText(sources.getChaodai());
//                    one.setText(sources.getContent());




                }catch(Exception e) {
                    e.printStackTrace();
                }
            }

        }.start();


    }
//    private void tututut(){
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//
////                    tv_title.setText(""+hh);
//
//                    for(int i=0;i<1000000000;i++){
//                        int[] location = new int[2];
//                        pinpin.getLocationOnScreen(location);
//                        int x = location[0];
//                        int y = location[1];
//                        int hh=contentzz.getHeight();
//                        if(y<1900){
//                            Message message = Message.obtain();
//                            message.obj =y;
//                            message.what = 400;
//                            handler.sendMessage(message);
//                            sleep(10000);
//                        }
//
//                        sleep(1);
//                    }




//                    titlez.setText(sources.getTitle());
//                    chaodaia.setText(sources.getChaodai());
//                    one.setText(sources.getContent());




//                }catch(Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }.start();


    //    }
    //设置状态栏透明
    private void setStatusBarTransparent(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }
    //获取状态栏高度
    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据屏幕的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    private void quanmingm(int v){
        new Thread(){
            @Override
            public void run() {
                try {
                    if(v==1){
                        gunlun2.setScroll(false);
                        int hh=tupianaa.getHeight();
//                    pin.setText(hh+"");
//                    tv_title.setText(""+hh);
                        for(int i=dip2px(DetailActivity.this,450);i<hh;i=i+12){

                            Message message = Message.obtain();
                            message.obj =i;
                            message.what = 500;
                            handler.sendMessage(message);
                            sleep(1);
                        }
                    }else{
                        gunlun2.setScroll(true);
                        int hh=tupianaa.getHeight();
//                    pin.setText(hh+"");
//                    tv_title.setText(""+hh);
                        for(int i=hh;i>dip2px(DetailActivity.this,450);i=i-12){

                            Message message = Message.obtain();
                            message.obj =i;
                            message.what = 500;
                            handler.sendMessage(message);
                            sleep(1);
                        }
                    }






//                    titlez.setText(sources.getTitle());
//                    chaodaia.setText(sources.getChaodai());
//                    one.setText(sources.getContent());




                }catch(Exception e) {
                    e.printStackTrace();
                }
            }

        }.start();


    }
    public static Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }
    private void tansss(int chans,int vnv){

//        if(vnv -chans==0){
//            vnv=0;
//        }else if(vnv-chans<0){
//            vnv=-1;
//        }else{
//            vnv=1;
//        }

        ObjectAnimator translated =
                ObjectAnimator.ofFloat(content, "translationX", widthaaa*(vnv-chans),0);
        translated.setDuration(300);
        translated.setRepeatCount(0);


        ObjectAnimator translated2 =
                ObjectAnimator.ofFloat(content1, "translationX", 0,-widthaaa*(vnv-chans));
        translated2.setDuration(300);
        translated2.setRepeatCount(0);
        translated.start();
        translated2.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (poetryname!=null){
            initAdapter(poetryname);//加载语音listView
            isSetAdapter=true;
        }
    }
}

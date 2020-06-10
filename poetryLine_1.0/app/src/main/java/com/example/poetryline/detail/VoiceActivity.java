package com.example.poetryline.detail;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.R;
import com.example.poetryline.collect.database.MyDB;
import com.example.poetryline.detail.example.ExampleAdapter;
import com.example.poetryline.detail.example.Record;
import com.example.poetryline.detail.history.DBManager;
import com.example.poetryline.detail.manager.AudioRecordButton;
import com.example.poetryline.detail.manager.MediaManager;
import com.example.poetryline.detail.utils.PermissionHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import cn.smssdk.gui.DefaultContactViewItem;


public class VoiceActivity extends AppCompatActivity {
    private ListView mEmLvRecodeList;
    private AudioRecordButton mEmTvBtn;
    List<Record> mRecords;
    private LinearLayout joins;
    private ScrollView poetry;
    private int tans;
    private TextView titlezz;
    private TextView chaodaii;
    private TextView onee;
    ExampleAdapter mExampleAdapter;
    PermissionHelper mHelper;
    private ImageView back;
    private TextView title;
    private ImageView share;
    private Intent shareIntent;
    //db
    private DBManager mgr;
    private CustomeClickListener listener;

    private String userName;
    private String userPhone;//用户手机号也是图片名称
    private SharedPreferences userSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
//        this.getSupportActionBar().hide();



        initView();
        initData();
        initAdapter();
        initListener();

        registerListeners();

//        Toast.makeText(getApplicationContext(),"接收",Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        titlezz.setText((String)intent.getStringExtra("titlez"));
        onee.setText( (String)intent.getStringExtra("one"));
        chaodaii.setText( (String)intent.getStringExtra("chaodaia"));


        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "快来下载app前来查看分享吧！");
//        initListener();

        //获取用户名来显示到语音listview上
        userSP = getSharedPreferences("userData",MODE_PRIVATE);
        userName = userSP.getString("name","");

        userPhone = userSP.getString("phone","");



    }
    class CustomeClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.joins:
                    joins.setVisibility(View.GONE);
                    mEmTvBtn.setVisibility(View.VISIBLE);
//                    poetry.setVisibility(View.VISIBLE);
                    ObjectAnimator translated =
                            ObjectAnimator.ofFloat(poetry, "scaleY", 0, 1);
                    translated.setDuration(600);
                    translated.setRepeatCount(0);
                    ObjectAnimator translatedd =
                            ObjectAnimator.ofFloat(poetry, "scaleX", 0, 1);
                    translatedd.setDuration(600);
                    translatedd.setRepeatCount(0);

                    translated.start();
                    translatedd.start();
                    poetry.setVisibility(View.VISIBLE);

                    break;
                case R.id.share:
                    Intent intent5 = Intent.createChooser(shareIntent, "分享一段文本信息");
                    if (shareIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent5);
                    }
                    break;
                case R.id.back:
                    finish();


            }
        }
    }
    private void registerListeners() {
        listener = new CustomeClickListener();
        joins.setOnClickListener(listener);
        mEmTvBtn.setOnClickListener(listener);
        poetry.setOnClickListener(listener);
        back.setOnClickListener(listener);
        share.setOnClickListener(listener);


    }

    private void initView() {
        mEmLvRecodeList = (ListView) findViewById(R.id.em_lv_recodeList);
        mEmTvBtn = (AudioRecordButton) findViewById(R.id.em_tv_btn);
        joins = findViewById(R.id.joins);
        poetry=findViewById(R.id.poetry);
        titlezz=findViewById(R.id.voice_titlezz);
        onee=findViewById(R.id.onee);
        chaodaii=findViewById(R.id.chaodaii);
        share=findViewById(R.id.share);
        back=findViewById(R.id.back);
        title=findViewById(R.id.tv_title);
        Intent intent = getIntent();
        title.setText("《"+(String)intent.getStringExtra("titlez")+"》大声读出来！");
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
//        mRecords.clear();
        mEmLvRecodeList.setAdapter(mExampleAdapter);

        //开始获取数据库数据
        Intent intent = getIntent();

//        List<Record> records = mgr.query((String)intent.getStringExtra("titlez"));
//        if (records == null || records.isEmpty()) return;
//        for (Record record : records) {
//            Log.e("wgy", "initAdapter: " + record.toString());
//        }

//        Toast.makeText(getApplicationContext(),"获取数据库中数据"+records.toString(),Toast.LENGTH_SHORT).show();

        List<Record> records = new ArrayList<Record>();
        MyDB myDB;
        myDB = new MyDB(this);
        SQLiteDatabase db = myDB.getReadableDatabase();
        Cursor c = db.query("Record",null,
                "poetryName=?",new String[]{(String)intent.getStringExtra("titlez")},null
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

        mRecords.addAll(records);
        mExampleAdapter.notifyDataSetChanged();
        mEmLvRecodeList.setSelection(mRecords.size() - 1);
    }

    private void initListener() {
        mEmTvBtn.setHasRecordPromission(false);
        //        授权处理
        mHelper = new PermissionHelper(this);

        mHelper.requestPermissions("请授予[录音]，[读写]权限，否则无法录音",
                new PermissionHelper.PermissionListener() {
                    @Override
                    public void doAfterGrand(String... permission) {
                        mEmTvBtn.setHasRecordPromission(true);
                        mEmTvBtn.setAudioFinishRecorderListener((seconds, filePath) -> {
                            Record recordModel = new Record();
                            recordModel.setSecond((int) seconds <= 0 ? 1 : (int) seconds);
                            recordModel.setPath(filePath);
                            recordModel.setNum(0);
                            recordModel.setIsZan(0);
                            recordModel.setPlayed(false);

                            //设置语音 发布者名称
                            if(userName.length()==0){
                                recordModel.setName("佚名");
                            }else {
                                recordModel.setName(userName);
                            }
                            //设置时间
                            Date now = new Date();
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                            String dateString = dateFormat.format( now );
                            recordModel.setDate(dateString);

                            //设置头像
                            if(userName.length()==0){
                                recordModel.setUserPhone("000");
                            }else {
                                recordModel.setUserPhone(userPhone);
                            }

                            //设置诗名
                            Intent intent = getIntent();
                            recordModel.setPeotryName((String)intent.getStringExtra("titlez"));

                            //添加到数据库
//                            mgr.add(recordModel);
                            recordModel.setId(UUID.randomUUID().toString());
                            MyDB myDB;
                            myDB = new MyDB(getApplicationContext());
                            SQLiteDatabase db;
                            ContentValues values;
                            //  存储信息
                            db = myDB.getWritableDatabase();
                            values = new ContentValues();
                            values.put("id",recordModel.getId());
                            values.put("path",recordModel.getPath());
                            values.put("second",recordModel.getSecond());
                            values.put("isPlayed",recordModel.isPlayed());
                            values.put("num",recordModel.getNum());
                            values.put("isZan",recordModel.getIsZan());
                            values.put("name",recordModel.getName());
                            values.put("date",recordModel.getDate());
                            values.put("userPhone",recordModel.getUserPhone());
                            values.put("poetryName",recordModel.getPeotryName());
                            db.insert("Record",null,values);
                            db.close();
                            Log.e("wgy", "添加数据库成功："+values.toString());



                            mRecords.add(recordModel);

                            mExampleAdapter.notifyDataSetChanged();
                            mEmLvRecodeList.setSelection(0);//最新添加的语音数据放在第一条

//                            Toast.makeText(getApplicationContext(),filePath,Toast.LENGTH_LONG).show();



                        });
                    }

                    @Override
                    public void doAfterDenied(String... permission) {
                        mEmTvBtn.setHasRecordPromission(false);
                        Toast.makeText(VoiceActivity.this, "请授权,否则无法录音", Toast.LENGTH_SHORT).show();
                    }
                }, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    //直接把参数交给mHelper就行了
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


}

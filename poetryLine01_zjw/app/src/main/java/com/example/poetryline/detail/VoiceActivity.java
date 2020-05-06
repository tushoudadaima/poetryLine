package com.example.poetryline.detail;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poetryline.R;
import com.example.poetryline.detail.example.ExampleAdapter;
import com.example.poetryline.detail.example.Record;
import com.example.poetryline.detail.history.DBManager;
import com.example.poetryline.detail.manager.AudioRecordButton;
import com.example.poetryline.detail.manager.MediaManager;
import com.example.poetryline.detail.utils.PermissionHelper;

public class VoiceActivity extends AppCompatActivity {
    private ListView mEmLvRecodeList;
    private AudioRecordButton mEmTvBtn;
    List<Record> mRecords;
    private LinearLayout joins;
    private LinearLayout poetry;
    ExampleAdapter mExampleAdapter;
    PermissionHelper mHelper;
    private ImageView back;
    private TextView title;
    private ImageView share;
    private Intent shareIntent;
    //db
    private DBManager mgr;
    private CustomeClickListener listener;
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
        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "快来下载app前来查看分享吧！");
//        initListener();

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
                    poetry.setVisibility(View.VISIBLE);
                    translated.start();

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
        share=findViewById(R.id.share);
        back=findViewById(R.id.back);
        title=findViewById(R.id.tv_title);
        title.setText("《春思》大声读出来！");
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
                            mRecords.add(recordModel);

                            mExampleAdapter.notifyDataSetChanged();
                            mEmLvRecodeList.setSelection(mRecords.size() - 1);

                            //添加到数据库
                            mgr.add(recordModel);
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

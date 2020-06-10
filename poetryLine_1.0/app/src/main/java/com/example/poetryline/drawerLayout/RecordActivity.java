package com.example.poetryline.drawerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.example.poetryline.R;
import com.example.poetryline.adapter.RecordAdapter;
import com.example.poetryline.entity.Record;
import com.example.poetryline.entity.Title;
import com.example.poetryline.util.HttpUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    private ListView list_record;
    private String wang_zhi;
    private List<Record> records = new ArrayList<>();
    private SharedPreferences sp;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 100:
                    String info = (String) msg.obj;
                    records = changeWithJSONObject(info);
                    RecordAdapter recordAdapter = new RecordAdapter(getApplication(),records,R.layout.record_item_layout);
                    list_record.setAdapter(recordAdapter);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        wang_zhi = this.getString(R.string.wang_zhi);
        list_record = findViewById(R.id.list_record);
        sendOkHttp();
    }

    private List<Record> changeWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            List<Record> recordList = new ArrayList<>();
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String date = jsonObject.getString("date");
                String userName = jsonObject.getString("userName");
                String phone = jsonObject.getString("phone");
                String type = jsonObject.getString("type");
                int score = jsonObject.getInt("score");
                Record record = new Record(id,date,userName,phone,type,score);
                recordList.add(record);
            }
            return recordList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void sendOkHttp(){
        sp = getSharedPreferences("userData",MODE_PRIVATE);
        String phone = sp.getString("phone",null);
        String address = wang_zhi+"/record/show/"+phone;
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

    private void wrapMessage(String info){
        Message message = new Message();
        message.what = 100;
        message.obj = info;
        handler.sendMessage(message);
    }


}

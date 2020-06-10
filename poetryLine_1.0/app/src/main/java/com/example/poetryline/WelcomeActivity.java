package com.example.poetryline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    private String wang_zhi;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 10:
                    String responseData = (String)msg.obj;
                    saveData(responseData);
                    Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
//                    intent.putExtra("data",responseData);
                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(),responseData,Toast.LENGTH_SHORT).show();
                    finish();
                    super.handleMessage(msg);
                    break;
                case 20:
                    Toast.makeText(getApplicationContext(),"服务器异常",Toast.LENGTH_SHORT).show();
//                    WelcomeActivity.this.finish();
                    Intent intent1 = new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent1);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        wang_zhi=this.getString(R.string.wang_zhi);

        initData();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(wang_zhi+"/poetry/getSome/100").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Message message = new Message();
                    message.what = 10;
                    message.obj = responseData;
                    handler.sendMessageDelayed(message,2000);
                }catch (Exception e){
                    e.printStackTrace();
                    Message message = Message.obtain();
                    message.what = 20;
                    handler.sendMessage(message);

                }
            }
        }).start();
    }

    public void saveData(String data){
        SharedPreferences.Editor editor = getSharedPreferences("datas",MODE_PRIVATE).edit();
        editor.putString("data",data);
        editor.apply();
    }
}
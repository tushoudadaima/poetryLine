package com.example.poetryline.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.MainActivity;
import com.example.poetryline.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UpdateName extends AppCompatActivity {
    private EditText et_new_name;
    private Button btn_update_name;
    private TextView tx_nameStatus;
    private String wang_zhi;
    private String phone;
    private String name;
    private Handler handler = new Handler();
//    private SharedPreferences.Editor editor;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);

        wang_zhi=this.getString(R.string.wang_zhi);
        SharedPreferences userSP = getSharedPreferences("userData",MODE_PRIVATE);
        phone = userSP.getString("phone","");

        getViews();
        et_new_name.addTextChangedListener(textWatcher);

        btn_update_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et_new_name.getText().toString().trim();
                if (name.length()==0){
                    Toast.makeText(getApplicationContext(),"字数不能为0哦",Toast.LENGTH_SHORT).show();
                }else if(name.length()>8){
                    Toast.makeText(getApplicationContext(),"字数不能超过8哦",Toast.LENGTH_SHORT).show();
                }else {
                    updateUserName();
                }

            }
        });

        handler = new Handler(){
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:

                        saveName();

                        Toast.makeText(getApplicationContext(),"昵称修改成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateName.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),"昵称修改失败",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"错误",Toast.LENGTH_SHORT).show();
                        break;
                }
            }

        };

    }

    private void saveName() {
        SharedPreferences sp = getSharedPreferences("userData", Activity.MODE_PRIVATE);
//        editor = getSharedPreferences("userData",MODE_PRIVATE).edit();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",et_new_name.getText().toString().trim());
        editor.apply();
//        Toast.makeText(getApplicationContext(),"Updatename保存",Toast.LENGTH_SHORT).show();
    }

    private void updateUserName() {
        new Thread(){
            public void run(){
                try {
                    URL url = new URL(wang_zhi+"/UpdateName?phone=" + phone+"&name="+name);
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String results = "";
                    String newLine = "";

                    while((newLine = reader.readLine()) != null){
                        results = newLine;
                    }

                    if (results.equals("1")){
                        Message msg = Message.obtain();
                        msg.what=1;//昵称修改成功
                        handler.sendMessage(msg);
                    }else {
                        Message msg = Message.obtain();
                        msg.what=2;//昵称修改失败
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void getViews() {
        et_new_name = findViewById(R.id.et_new_name);
        btn_update_name = findViewById(R.id.btn_update_name);
        tx_nameStatus = findViewById(R.id.tx_nameStatus);
    }
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @SuppressLint("SetTextI18n")
        @Override
        public void afterTextChanged(Editable s) {
            int a = 8-s.length();
            if(s.length()<=8){
                tx_nameStatus.setText(""+a);
            }else {
                tx_nameStatus.setText("不能超过8位");
            }


        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UpdateName.this, CountActivity.class);
        startActivity(intent);
    }


}

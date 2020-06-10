package com.example.poetryline.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class UpdatePassword extends AppCompatActivity {
    private EditText et_new_password;
    private EditText et_re_new_password;
    private Button btn_update_password;
    private TextView tx_pswStatus;
    private TextView tx_repswStatus;
    private String wang_zhi;
    private String phone;
    private String password;
    private String passwordMD5;
    private Handler handler = new Handler();
    private String results;
    private String newLine;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        wang_zhi=this.getString(R.string.wang_zhi);

        SharedPreferences userSP = getSharedPreferences("userData",MODE_PRIVATE);
        phone = userSP.getString("phone","");

        getViews();

        et_new_password.addTextChangedListener(textWatcher);
        et_re_new_password.addTextChangedListener(textWatcher2);
        btn_update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserPassword();
            }
        });

        handler = new Handler(){
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        SaveUserData();
                        Toast.makeText(getApplicationContext(),"密码修改成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdatePassword.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),"密码修改失败:",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"错误",Toast.LENGTH_SHORT).show();
                        break;
                }
            }

        };

    }

    private void updateUserPassword() {
        new Thread(){
            public void run(){
                try {
                    password = et_new_password.getText().toString().trim();
                    passwordMD5 =MD5.getMD5(password.getBytes());
                    URL url = new URL(wang_zhi+"/UpdatePassword?phone=" + phone+"&password="+passwordMD5);
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    results = "";
                    newLine = "";

                    while((newLine = reader.readLine()) != null){
                        results = newLine;
                    }
//                    newLine = reader.readLine();
                    if (results.equals("1")){
                        Message msg = Message.obtain();
                        msg.what=1;//密码修改成功
                        handler.sendMessage(msg);
                    }else {
                        Message msg = Message.obtain();
                        msg.what=2;//密码修改失败
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void SaveUserData() {
        SharedPreferences.Editor editor = getSharedPreferences("userData",MODE_PRIVATE).edit();
        editor.putString("phone",phone);
        editor.putString("password",MD5.getMD5(et_new_password.getText().toString().trim().getBytes()));
        editor.apply();
//        Toast.makeText(getApplicationContext(),phone+passwordMD5,Toast.LENGTH_SHORT).show();

    }


    private void getViews() {
        et_new_password = findViewById(R.id.et_new_password);
        et_re_new_password = findViewById(R.id.et_re_new_password);
        btn_update_password = findViewById(R.id.btn_update_password);
        tx_pswStatus = findViewById(R.id.tx_pswStatus);
        tx_repswStatus = findViewById(R.id.tx_repswStatus);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(s.length()>=6){
                tx_pswStatus.setText("√");
            }else {
                tx_pswStatus.setText("密码不足6位");
            }


        }
    };
    private TextWatcher textWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String password = et_new_password.getText().toString().trim();
            String repassword = et_re_new_password.getText().toString().trim();
            if(password.equals(repassword)){
                tx_repswStatus.setText("√");
            }else {
                tx_repswStatus.setText("两次密码不同");
            }

        }
    };
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UpdatePassword.this, CountActivity.class);
        startActivity(intent);
    }

}

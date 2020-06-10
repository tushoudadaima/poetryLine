package com.example.poetryline.signup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.MainActivity;
import com.example.poetryline.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText et_phone;
    private EditText et_password;
    private Button btn_to_rigestered;
    private Button btn_signin;
    private TextView btn_forget;
    private CustomeOnClickListener listener;
    private Handler handler = new Handler();
    private String wang_zhi ;
    private int a =0;

    private String phone;
    private String password;
    private SharedPreferences.Editor editor;

    private OkHttpClient okHttpClient;


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        wang_zhi=this.getString(R.string.wang_zhi);
        okHttpClient = new OkHttpClient();

        getViews();
        registListener();

        Intent intent = getIntent();
        String userPhone = intent.getStringExtra("userPhone");
        String userPassword = intent.getStringExtra("userPassword");

        if (userPhone!=null){
            et_phone.setText(userPhone);
            et_password.setText(userPassword);
//            Toast.makeText(getApplicationContext(),userPhone+userPassword,Toast.LENGTH_SHORT).show();
        }

        handler = new Handler(){
            public void handleMessage(Message msg) {
                switch (msg.what){

                    case 1:
                        Toast.makeText(getApplicationContext(),"账号或密码不正确",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        SaveUserData();

                        setUserImage();

                        Toast.makeText(getApplicationContext(),"登录成功 欢迎来到诗行！",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                    default:
                        Toast.makeText(getApplicationContext(),"错误",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    public void setUserImage() {
        @SuppressLint("SdCardPath") File file = new File("/data/data/com.example.poetryline/files/poetryLinePic/");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if(file.exists()){
            asyncDownOp();
        }else {
            file.mkdirs();
            asyncDownOp();
        }
    }

    public void asyncDownOp() {
        new Thread(){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                try {
                    downUserImg();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void downUserImg() throws IOException {
        Request request = new Request.Builder()
                .url(wang_zhi+"/ImgDown?phone="+phone)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        InputStream in = Objects.requireNonNull(response.body()).byteStream();
        OutputStream out = new FileOutputStream(
                getFilesDir().getPath()+"/poetryLinePic/"+phone+".jpg"
        );

        byte[] bytes = new byte[1024];
        int n = -1;
        while((n = in.read(bytes)) != -1){
            out.write(bytes, 0 , n);
            out.flush();
        }
        in.close();
        out.close();
    }

    private void registListener() {
        listener = new CustomeOnClickListener();
        btn_signin.setOnClickListener(listener);
        btn_to_rigestered.setOnClickListener(listener);
        btn_forget.setOnClickListener(listener);
    }

    private void getViews() {
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        btn_to_rigestered = findViewById(R.id.btn_to_registered);
        btn_signin = findViewById(R.id.btn_signin);
        btn_forget = findViewById(R.id.btn_forget);
    }


    private class CustomeOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_signin://点击登录
                    phone = et_phone.getText().toString().trim();
                    password = et_password.getText().toString().trim();
                    if(phone.equals("")){
                        Toast.makeText(getApplicationContext(),"手机号不能为空",Toast.LENGTH_SHORT).show();
                    }else if(password.equals("")){
                        Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Login();
                    }
                    break;
                case R.id.btn_to_registered://点击注册按钮
                    Intent intent1 = new Intent();
                    intent1.setClass(LoginActivity.this, SignUp.class);
                    startActivity(intent1);
                    break;
                case R.id.btn_forget:
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, ForgetPassword.class);
                    startActivity(intent);
                    break;

            }
        }
    }
    private void SaveUserData() {
        String phone = et_phone.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String passwordMD5= MD5.getMD5(password.getBytes());

        editor = getSharedPreferences("userData",MODE_PRIVATE).edit();
        editor.putString("phone",phone);
        editor.putString("password",passwordMD5);
        editor.apply();

    }


    private void Login() {
        new Thread(){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void run(){
                try {
                    phone = et_phone.getText().toString().trim();
                    String password = et_password.getText().toString().trim();
                    String passwordMD5 = MD5.getMD5(password.getBytes());
                    URL url = new URL(wang_zhi+"/UserLoginServlet?phone=" + phone+"&password="+passwordMD5);
                    InputStream in = url.openStream();
                    InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                    BufferedReader bf = new BufferedReader(isr);
                    String results = "";
                    String newLine = "";
                    while((newLine = bf.readLine()) != null){
//                        results += newLine+"\n";
                        results = newLine;

                    }
                    if (results.equals("1")){
                        Message msg = Message.obtain();
                        msg.what=2;//登录成功
                        handler.sendMessage(msg);

                    }else  {
                        Message msg = Message.obtain();
                        msg.what=1;//账号密码错误
                        handler.sendMessage(msg);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}



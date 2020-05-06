package com.example.poetryline.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText et_phone;
    private EditText et_password;
    private Button btn_to_rigestered;
    private Button btn_signin;
    private TextView btn_forget;
    //    private RadioButton buyer_res;
//    private RadioButton seller_res;
    private CustomeOnClickListener listener;
    private Handler handler = new Handler();
    //    private int radio =1;
    private String wang_zhi ;
    private int a =0;
//    private String buyerId;
//    private String sellerId;

    private String phone;
    private SharedPreferences.Editor editor;

    private OkHttpClient okHttpClient;

    private ImageView imageView;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        wang_zhi=this.getString(R.string.wang_zhi);
        okHttpClient = new OkHttpClient();

        getViews();
        registListener();
//        SharedPreferences buyerSP = getSharedPreferences("buyerData",MODE_PRIVATE);
//        SharedPreferences sellerSP = getSharedPreferences("sellerData",MODE_PRIVATE);
//        buyerId = buyerSP.getString("buyerId","");
//        sellerId = sellerSP.getString("sellerId","");

        handler = new Handler(){
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        Toast.makeText(getApplicationContext(),"验证码发送成功",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),"验证码发送失败，请重新尝试",Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(),"验证码错误 验证失败",Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        if(a==1){
                            Toast.makeText(getApplicationContext(),"该用户未注册",Toast.LENGTH_SHORT).show();
                        }else if(a==2){
//                            if(radio == 1) {
//                                BuyerLogin();
//                            }else {
//                                SellerLogin();
//                            }
                            Login();
                        }
                        break;
                    case 8:
                        Toast.makeText(getApplicationContext(),"账号或密码不正确",Toast.LENGTH_SHORT).show();
                        break;
                    case 9:
//                        MyApplication myApplication = (MyApplication) getApplication();
//                        String phone = et_phone.getText().toString().trim();
//                        myApplication.setPhone(phone);
//
                        SaveUserData();

//                        finish();
//                        try {
//                            downImg();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                        Toast.makeText(getApplicationContext(),"登录成功 欢迎您 "+phone,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("index","fs");
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 10:
                        String str = (String)msg.obj;
//                        Toast.makeText(getApplicationContext(),str+"",Toast.LENGTH_SHORT).show();
                        editor.putString("nickName",str);
                        editor.apply();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"错误",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    private void downImg() throws IOException {
        Request request = new Request.Builder()
                .url(wang_zhi+"ImgDown?phone="+phone)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        InputStream in = response.body().byteStream();
        @SuppressLint("SdCardPath") File file = new File(getFilesDir().getPath()+"/poetryLinePic/");
        if (!file.exists()){
            file.mkdirs();
        }
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
//        buyer_res.setOnClickListener(listener);
//        seller_res.setOnClickListener(listener);
    }

    private void getViews() {
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        btn_to_rigestered = findViewById(R.id.btn_to_registered);
        btn_signin = findViewById(R.id.btn_signin);
        btn_forget = findViewById(R.id.btn_forget);
//        buyer_res = findViewById(R.id.buyer_res);
//        seller_res = findViewById(R.id.seller_res);
    }

    private class CustomeOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_signin://点击登录
                    String phone = et_phone.getText().toString().trim();
                    String password = et_password.getText().toString().trim();
                    if(phone.equals("")){
                        Toast.makeText(getApplicationContext(),"手机号不能为空",Toast.LENGTH_SHORT).show();
                    }else if(password.equals("")){
                        Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    }
//                    else  if(radio==1){
//                        checkBuyerLogin();//检查该买家有无注册
//                    }else if(radio == 2){
//                        checkSellerLogin();//检查该卖家有无注册
//                    }
                    else {
                        Login();
                    }
                    break;
                case R.id.btn_to_registered://点击注册按钮
                    Intent intent1 = new Intent();
                    intent1.setClass(LoginActivity.this, SignUp.class);
//                    Bundle bundle1 = new Bundle();
//                    bundle1.putInt("radioStatus",radio);
//                    intent1.putExtras(bundle1);
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
        DateFormat df = new SimpleDateFormat("yyyy:mm:dd:HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        String buyerStr = df.format(curDate);
        String phone = et_phone.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String passwordMD5= getMD5(password.getBytes());

        editor = getSharedPreferences("userData",MODE_PRIVATE).edit();
        editor.putString("phone",phone);
        editor.putString("password",passwordMD5);
//        editor.putString("time",buyerStr);
//        findName();
        editor.apply();

    }


    private void Login() {
        new Thread(){
            public void run(){
                try {
                    phone = et_phone.getText().toString().trim();
                    String password = et_password.getText().toString().trim();
                    String passwordMD5 = getMD5(password.getBytes());
                    URL url = new URL(wang_zhi+"/UserLoginServlet?phone=" + phone+"&password="+passwordMD5);
                    InputStream in = url.openStream();
                    InputStreamReader isr = new InputStreamReader(in,"UTF-8");
                    BufferedReader bf = new BufferedReader(isr);
                    String results = "";
                    String newLine = "";
                    while((newLine = bf.readLine()) != null){
//                        results += newLine+"\n";
                        results = newLine;

                    }
                    if (results.equals("1")){
                        Message msg = Message.obtain();
                        msg.what=9;//登录成功
                        handler.sendMessage(msg);

                    }else  {
                        Message msg = Message.obtain();
                        msg.what=8;//账号密码错误
                        handler.sendMessage(msg);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }



    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            s = new String(str); // 换后的结果转换为字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}



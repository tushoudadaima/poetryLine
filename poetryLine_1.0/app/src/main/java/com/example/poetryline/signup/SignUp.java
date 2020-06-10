package com.example.poetryline.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.example.poetryline.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class SignUp extends AppCompatActivity {
    private EditText et_phone;
    private EditText et_code;
    private EditText et_password;
    private EditText et_repassword;
    private EditText et_nicheng;
    private Button btn_rigestered;
    private TextView btn_getCode;
    private TextView tx_pswStatus;
    private TextView tx_repswStatus;
    private CustomeOnClickListener listener;
    public int T = 60; //倒计时时长
    private Handler mHandler = new Handler();
    private Handler handler = new Handler();
    private String wang_zhi ;
    private int a =0;
    private String checkStr = "checkStr";
    private boolean isSignUp = false;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        wang_zhi=this.getString(R.string.wang_zhi);

        getViews();
        registListener();

        et_password.addTextChangedListener(textWatcher);
        et_repassword.addTextChangedListener(textWatcher2);

        handler = new Handler(){
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
//                        Toast.makeText(getApplicationContext(),"验证码发送成功",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),"验证码发送失败，请重新尝试",Toast.LENGTH_SHORT).show();
                        break;

                    case 4:
                        Toast.makeText(getApplicationContext(),"验证码错误 验证失败",Toast.LENGTH_SHORT).show();
                        break;

                    case 6:

                        if (!isSignUp){
                            Toast.makeText(getApplicationContext(),"该账号已经注册过了哦。。。",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"注册成功！欢迎加入诗行！请点击登录。",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this,LoginActivity.class);
                            intent.putExtra("userPhone", et_phone.getText().toString().trim());
                            intent.putExtra("userPassword",et_password.getText().toString().trim());
                            startActivity(intent);
                        }
                        break;
                    case 10:
//                        Toast.makeText(getApplicationContext(),"验证码匹配",Toast.LENGTH_SHORT).show();
                        SignUp();
                        break;
                    case 11:
                        checkExist();
                        break;
                    case 13:
                        registered();
                        break;
                    case 15:
                        getCode();
                        break;
                    case 16:
                        Toast.makeText(getApplicationContext(),"该账号已经注册过了哦。。。",Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(getApplicationContext(),"错误",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
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
            String password = et_password.getText().toString().trim();
            String repassword = et_repassword.getText().toString().trim();
            if(password.equals(repassword)){
                tx_repswStatus.setText("√");
            }else {
                tx_repswStatus.setText("两次密码不同");
            }

        }
    };

    private void registListener() {
        listener = new CustomeOnClickListener();
        btn_rigestered.setOnClickListener(listener);
        btn_getCode.setOnClickListener(listener);
    }

    private void getViews() {
        et_phone = findViewById(R.id.et_phone);
        et_code = findViewById(R.id.et_code);
        et_password = findViewById(R.id.et_password);
        et_repassword = findViewById(R.id.et_repassword);
        btn_rigestered = findViewById(R.id.btn_registered);
        btn_getCode = findViewById(R.id.btn_getCode);
        tx_pswStatus = findViewById(R.id.tx_pswStatus);
        tx_repswStatus = findViewById(R.id.tx_repswStatus);
        et_nicheng = findViewById(R.id.et_nicheng);
    }

    private class CustomeOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_getCode://发送短信
                    checkExistForCode();

                    break;
                case R.id.btn_registered://点击注册按钮

                    //检查是否注册过
                    checkExist();

//                    测试阶段 不检查是否注册 直接注册
//                    registered();
                    //最终注册
//                    SignUp();
                    break;
            }
        }
    }
    private void checkExistForCode() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String phone = et_phone.getText().toString().trim();
                    URL url = new URL(wang_zhi+"/CheckUserServlet?phone=" + phone);
                    InputStream in = url.openStream();
                    InputStreamReader isr = new InputStreamReader(in,"UTF-8");
                    BufferedReader bf = new BufferedReader(isr);
                    String results = "";
                    String newLine = "";
                    while((newLine = bf.readLine()) != null){
                        results = newLine;
                    }
                    if (results.equals("0")){
                        checkStr = results;
                        Message msg = Message.obtain();
                        msg.what=15;//没有注册 发送code;
                        handler.sendMessage(msg);
                    }else if(results.equals("1")) {
                        checkStr = results;
                        Message msg = Message.obtain();
                        msg.what=16;//提示已经注册
                        handler.sendMessage(msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void checkExist() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String phone = et_phone.getText().toString().trim();
                    URL url = new URL(wang_zhi+"/CheckUserServlet?phone=" + phone);
                    InputStream in = url.openStream();
                    InputStreamReader isr = new InputStreamReader(in,"UTF-8");
                    BufferedReader bf = new BufferedReader(isr);
                    String results = "";
                    String newLine = "";
                    while((newLine = bf.readLine()) != null){
                        results = newLine;
                    }
                    if (results.equals("0")){
                        checkStr = results;
                        Message msg = Message.obtain();
                        msg.what=13;//没有注册 调用registered();
                        handler.sendMessage(msg);
                    }else if(results.equals("1")) {
                        checkStr = results;
                        Message msg = Message.obtain();
                        msg.what=6;//提示已经注册或者注册成功跳转loginActivity
                        handler.sendMessage(msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    //注册
    private void registered() {
        String phone = et_phone.getText().toString().trim();
        String code = et_code.getText().toString().trim();
        String name = et_nicheng.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String repassword = et_repassword.getText().toString().trim();
        if(phone.equals("")||phone.length()==0){
            Toast.makeText(getApplicationContext(),"手机号不能为空", Toast.LENGTH_SHORT).show();
        }else if(phone.length() != 11){
            Toast.makeText(getApplicationContext(),"手机号格式不正确", Toast.LENGTH_SHORT).show();
        } else if(code.equals("")||code.length()==0){
            Toast.makeText(getApplicationContext(),"验证码不能为空", Toast.LENGTH_SHORT).show();
        }else if(name.equals("")||name.length()==0){
            Toast.makeText(getApplicationContext(),"昵称不能为空",Toast.LENGTH_LONG).show();
        } else if(password.length()<6){
            Toast.makeText(getApplicationContext(),"密码长度不能小于6位", Toast.LENGTH_SHORT).show();
        }else if(password.equals(repassword)){
            submitCode("+86",phone,code);
        }else {
            Toast.makeText(getApplicationContext(),"两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
        }

    }
    //发送短信验证码
    private void getCode() {
        String phone = et_phone.getText().toString().trim();
        if(phone.equals("")){
            Toast.makeText(getApplicationContext(),"手机号不能为空", Toast.LENGTH_SHORT).show();
        }else if(phone.length()!=11){
            Toast.makeText(getApplicationContext(),"手机号格式不正确", Toast.LENGTH_SHORT).show();
        } else{
            new Thread(new MyCountDownTimer()).start();//开始执行
//            btn_getCode.setBackgroundColor(Color.parseColor("#FAFFF0"));
            sendCode("+86",phone);
//            Toast.makeText(getApplicationContext(),"已向"+phone+"发送验证码", Toast.LENGTH_SHORT).show();
        }

    }

    //发送短信的具体方法
    // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
    public void sendCode(String country, final String phone) {
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理成功得到验证码的结果
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                    Message msg = Message.obtain();//验证码发送成功
                    msg.what=1;//提示验证码发送成功
                    handler.sendMessage(msg);
                } else{
                    // TODO 处理错误的结果
                    Message msg = Message.obtain();
                    msg.what=2;//提示验证码发送失败，请重新
                    handler.sendMessage(msg);
                }

            }
        });
        // 触发操作
        SMSSDK.getVerificationCode(country, phone);
    }


    //验证验证码是否正确
    // 提交验证码，其中的code表示验证码，如“1357”
    public void submitCode(String country, final String phone, String code) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理验证成功的结果
                    Message msg = Message.obtain();
                    msg.what=10;//若验证码正确 则调用具体注册函数 SignUp()
                    handler.sendMessage(msg);
                } else{
                    // TODO 处理错误的结果
                    Message msg = Message.obtain();
                    msg.what=4;//提示验证码错误 验证失败
                    handler.sendMessage(msg);
                }
            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }

    //注册具体方法
    private void SignUp() {
        new Thread() {
            public void run() {
                try {
                    String phone = et_phone.getText().toString().trim();
                    String password = et_password.getText().toString().trim();
                    String name = et_nicheng.getText().toString().trim();
//                    String phoneMD5 = getMD5(phone.getBytes());
                    String passwordMD5 = MD5.getMD5(password.getBytes());
                    URL url = new URL(wang_zhi+"/AddUserServlet?phone=" + phone+"&password="+passwordMD5+"&name="+name);
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    isSignUp=true;
                    Message msg = Message.obtain();
                    msg.what=11;//调用check 判断是否注册成功
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
//        Toast.makeText(getApplicationContext(),"phone = "+et_phone.getText().toString().trim()+" SignUp函数", Toast.LENGTH_SHORT).show();
    }



    protected void onDestroy() {
        super.onDestroy();
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
    };

    //验证码倒计时
    class MyCountDownTimer implements Runnable {
        @Override
        public void run() {
            //倒计时开始，循环
            while (T > 0) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        btn_getCode.setClickable(false);
                        btn_getCode.setText("重发"+"("+T+")");
                    }
                });
                try {
                    Thread.sleep(1000); //强制线程休眠1秒，就是设置倒计时的间隔时间为1秒。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                T--;
            }
            //倒计时结束，也就是循环结束
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    btn_getCode.setClickable(true);
                    btn_getCode.setText("获取验证码");
//                    btn_getCode.setBackgroundColor(Color.parseColor("#DCDCDC"));
                }
            });
            T = 10; //最后再恢复倒计时时长
        }
    }
}



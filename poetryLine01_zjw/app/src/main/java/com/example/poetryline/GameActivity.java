package com.example.poetryline;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.speech.JsonParser;
import com.example.poetryline.util.HttpUtil;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GameActivity extends AppCompatActivity{
    private static String TAG = GameActivity.class.getSimpleName();
    // 语音听写对象
    private SpeechRecognizer mIat;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    private ImageView img_return;
    private TextView tv_title;
    private TextView tv_from;
    private TextView tv_seconds;
    private Toast mToast;
    private Button btn_begin;
    private EditText edt_word;
    private Button btn_send;
    private ScrollView scroll_center;
    private LinearLayout liner_one;
    private CustomOnClickListener listener;
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    private String language="zh_cn";
    private String resultType = "json";
    private StringBuffer buffer = new StringBuffer();
    private static int flg = 0;
    private int index = 0;
    private String word;
    private String[] array;
    private int id;
    private int authorId;
    private String title;
    private String content;
    private String yunlvRule;
    private String author;
    private String dynasty;
    private String data;
    private String data2;
    private String answer;
    private int time = 60;
    //    private List<Map<String,Object>> list = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 100:
                    createView();
                    edt_word.setText(null);
                    systemHttp();
                    break;
                case 200:
                    createSystemView();
                    break;
                case 300:
                    time --;
                    tv_seconds.setText(time+"");
                    if (time > 0){
                        handler.sendEmptyMessageDelayed(300,1000);
                    }else {
                        GameActivity.this.finish();
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID +"=5ea1096a");
        initView();
        registerListener();
        // 初始化识别无UI识别对象
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(GameActivity.this, mInitListener);
        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mIatDialog = new RecognizerDialog(GameActivity.this, mInitListener);
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        tv_title.setText(key);
        handler.sendEmptyMessageDelayed(300,1000);
    }

    private void registerListener() {
        listener = new CustomOnClickListener();
        btn_begin.setOnClickListener(listener);
        btn_send.setOnClickListener(listener);
        img_return.setOnClickListener(listener);
    }

    private void initView() {
        tv_title = findViewById(R.id.tv_title);
        btn_begin = findViewById(R.id.btn_begin);
        edt_word = findViewById(R.id.edt_word);
        btn_send = findViewById(R.id.btn_send);
        liner_one = findViewById(R.id.liner_one);
        scroll_center = findViewById(R.id.scroll_center);
        tv_seconds = findViewById(R.id.tv_second);
        img_return = findViewById(R.id.img_return);
    }

    class CustomOnClickListener implements View.OnClickListener{

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void onClick(View v) {
            if( null == mIat ){
                // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
                showTip( "创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化" );
                return;
            }
            switch(v.getId()){
                case R.id.img_return:
                    GameActivity.this.finish();
                case R.id.btn_begin:
                    // 移动数据分析，收集开始听写事件
                    //	FlowerCollector.onEvent(IatDemo.this, "iat_recognize");
                    buffer.setLength(0);
                    edt_word.setText(null);// 清空显示内容
                    mIatResults.clear();
                    // 设置参数
                    setParam();
                    mIatDialog.setListener(mRecognizerDialogListener);
                    mIatDialog.show();
                    showTip(getString(R.string.text_begin));
                    break;
                case R.id.btn_send:
                    if(edt_word.getText().toString().equals("")){
                        Toast.makeText(GameActivity.this,"发送内容不能为空",Toast.LENGTH_LONG).show();
                    }else {
                        handler.removeMessages(300);
                        time = 60;
                        tv_seconds.setText(60+"");
                        handler.sendEmptyMessageDelayed(300,1000);
                        index ++;
                        word = edt_word.getText().toString();
                        array = word.split("，");
                        System.out.println(word);
                        String address1 = "http://192.168.101.7:8080/Poetry/poetry/getone/"+word;
                        HttpUtil.sendOkHttpRequest(address1, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                System.out.println(e);
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                data = response.body().string();
                                if(data.equals("")){
                                    wrapMessage();
                                }else {
                                    changeWithJSONObject(data);
                                    wrapMessage();
                                }
                                System.out.println("原始数据是："+data);

                            }
                        });
                    }
                    break;
            }
        }
    }

    private void systemHttp(){
        String address2 = "http://192.168.101.7:8080/Poetry/poetry/getradom/"+tv_title.getText().toString();
        HttpUtil.sendOkHttpRequest(address2, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                data2 = response.body().string();
                changeWithJSONObject(data2);
                wrapMessage2();
            }
        });
    }

    private void changeWithJSONObject(String jsonData) {
        try {
            System.out.println("我的名字是："+jsonData);
            JSONObject jsonObject=new JSONObject(jsonData);
            id = jsonObject.getInt("id");
            authorId = jsonObject.getInt("authorId");
            title = jsonObject.getString("title");
            content = jsonObject.getString("content");
            yunlvRule = jsonObject.getString("yunlvRule");
            author = jsonObject.getString("author");
            dynasty = jsonObject.getString("dynasty");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("ResourceType")
    private void createSystemView() {
        Resources r = getResources();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        int height0 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 250, r.getDisplayMetrics());
        LinearLayout.LayoutParams relativeLayoutParam =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height0);

        TextView tv2_first = new TextView(this);
        tv2_first.setTextSize(20);
        tv2_first.setId(5);
        String[] contentArray = content.split("\\|");
//        answers = contentArray[0];
        for(int i = 0;i < contentArray.length;i++){
            if(contentArray[i].contains(tv_title.getText().toString())){
                answer = contentArray[i];
                break;
            }
        }
        String[] answerArray =  answer.split("，");
        tv2_first.setText(answerArray[0]+"，");
        RelativeLayout.LayoutParams firstParam =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        firstParam.topMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 40, r.getDisplayMetrics());
        firstParam.addRule(RelativeLayout.CENTER_HORIZONTAL);

        TextView tv2_second = new TextView(this);
        tv2_second.setText(answerArray[1]);
        tv2_second.setTextSize(20);
        tv2_second.setId(6);
        RelativeLayout.LayoutParams secondParam =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        secondParam.topMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics());
        secondParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        secondParam.addRule(RelativeLayout.BELOW,5);

        TextView tv2_from = new TextView(this);
        tv2_from.setText("————"+dynasty+"·"+author+"·"+title);
        tv2_from.setTextSize(18);
        tv2_from.setId(7);
        RelativeLayout.LayoutParams fromParam =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fromParam.topMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 40, r.getDisplayMetrics());
        fromParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        fromParam.addRule(RelativeLayout.BELOW,6);

        liner_one.addView(relativeLayout,relativeLayoutParam);
        relativeLayout.addView(tv2_first,firstParam);
        relativeLayout.addView(tv2_second,secondParam);
        relativeLayout.addView(tv2_from,fromParam);

        handler.post(new Runnable() {
            @Override
            public void run() {
                scroll_center.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    @SuppressLint("ResourceType")
    private void createText(){
        tv_from = new TextView(this);
//        tv_from.setText(info2);
        if(data.equals("")){
            tv_from.setText("未找到");
        }else if(!edt_word.getText().toString().contains(tv_title.getText().toString())){
            tv_from.setText("诗句中未包含令牌");
        } else {
            tv_from.setText("————"+dynasty+"·"+author+"·"+title);
        }

        tv_from.setTextSize(18);
        tv_from.setId(4);
    }

    @SuppressLint("ResourceType")
    private void createView(){
        Resources r = getResources();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(1);
        ImageView imageView = new ImageView(this);
        TextView textView = new TextView(this);
        textView.setText("第"+index+"回合");
        textView.setTextSize(24);
        TextView tv_first = new TextView(this);
        tv_first.setText(array[0]+"，");
        tv_first.setTextSize(20);
        tv_first.setId(2);
        TextView tv_second = new TextView(this);
        tv_second.setText(array[1]);
        tv_second.setTextSize(20);
        tv_second.setId(3);
        createText();
        //最外层设置规则
        //里面的RelativeLayout设置规则
        int height0 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 250, r.getDisplayMetrics());
        LinearLayout.LayoutParams relativeLayoutParam =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height0);
        //FrameLayout设置规则
        RelativeLayout.LayoutParams frameLayoutParam =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frameLayoutParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //ImageView设置参数
        int height = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 94, r.getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 120, r.getDisplayMetrics());
        FrameLayout.LayoutParams imageParam = new FrameLayout.LayoutParams(width,height);
        imageView.setImageResource(R.mipmap.round2);
        //TextView设置规则
        FrameLayout.LayoutParams textParam =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParam.gravity = Gravity.CENTER;
        //第一句诗的设置参数
        RelativeLayout.LayoutParams firstParam =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        firstParam.topMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics());
        firstParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        firstParam.addRule(RelativeLayout.BELOW,1);
        //第二句诗的设置参数
        RelativeLayout.LayoutParams secondParam =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        secondParam.topMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics());
        secondParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        secondParam.addRule(RelativeLayout.BELOW,2);
        //诗的来源设置参数
        RelativeLayout.LayoutParams fromParam =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fromParam.topMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 40, r.getDisplayMetrics());
        fromParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        fromParam.addRule(RelativeLayout.BELOW,3);
        textView.setLayoutParams(textParam);

        liner_one.addView(relativeLayout,relativeLayoutParam);
        relativeLayout.addView(frameLayout,frameLayoutParam);
        relativeLayout.addView(tv_first,firstParam);
        relativeLayout.addView(tv_second,secondParam);
        relativeLayout.addView(tv_from,fromParam);
        frameLayout.addView(imageView,imageParam);
        frameLayout.addView(textView,textParam);

        handler.post(new Runnable() {
            @Override
            public void run() {
                scroll_center.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
        tv_seconds.setText(60+"");
    }

    private void setParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);
        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, resultType);
        mIat.setParameter(SpeechConstant.LANGUAGE, language);
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");
        mIat.setParameter(SpeechConstant.VAD_BOS, "4000");
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, "1000");
        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, "1");
    }

    public InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code+",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");
            }
        }
    };

    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            showTip("开始说话");
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            showTip(error.getPlainDescription(true));

        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            showTip("结束说话");
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            System.out.println(flg++);
            if (resultType.equals("json")) {

                printResult(results);

            }else if(resultType.equals("plain")) {
                buffer.append(results.getResultString());
                edt_word.setText(buffer.toString());
                edt_word.setSelection(edt_word.length());
            }
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            showTip("当前正在说话，音量大小：" + volume);
            Log.d(TAG, "返回音频数据："+data.length);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {

        }
    };

    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        edt_word.setText(resultBuffer.toString());
        edt_word.setSelection(edt_word.length());
    }

    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {

            printResult(results);

        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {
            showTip(error.getPlainDescription(true));

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if( null != mIat ){
            // 退出时释放连接
            mIat.cancel();
            mIat.destroy();
        }
    }

    @Override
    protected void onResume() {
        // 开放统计 移动数据统计分析
		/*FlowerCollector.onResume(IatDemo.this);
		FlowerCollector.onPageStart(TAG);*/
        super.onResume();
    }

    @Override
    protected void onPause() {
        // 开放统计 移动数据统计分析

        super.onPause();
    }

    public void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }

    private void wrapMessage(){
        Message message = new Message();
        message.what = 100;
        handler.sendMessage(message);
    }

    private void wrapMessage2(){
        Message message = new Message();
        message.what = 200;
        handler.sendMessage(message);
    }

}


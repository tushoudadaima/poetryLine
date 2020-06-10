package com.example.poetryline.detail.Send;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.poetryline.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class PinxiangActivity extends AppCompatActivity {
    private String ming;
    private String biao;
    private int tupian;
    private  int bbbc;
    private TextView biaoa;
    private TextView shijian;
    private ImageView pic;
    private TextView bibibia;
    private ScrollView gaosi;
    private TextView tv;
    private int[] imageidd={R.drawable.m1,R.drawable.m2,R.drawable.m3,R.drawable.m4,R.drawable.m5,R.drawable.m6,R.drawable.m7,R.drawable.m8,R.drawable.m9,R.drawable.m10,R.drawable.m11,R.drawable.m12,R.drawable.m13,R.drawable.m14,R.drawable.m15,R.drawable.m16,R.drawable.m17,R.drawable.m18};
    private String[] bibibi={"云在天空中摇摆着，\n" +
            "云在天空中，\n" +
            "阳柏哲在蓝天白云里如如如尘，\n" +
            "想想就怕。","如梦一世浮华生，终落六道锁魂城.\n" +
            "难得摆脱红尘恋，谁能与我共鸿蒙.","黎明圆月齐当空 \n" +
            "山巅身影孤傲立 \n" +
            "淡淡忧伤谁可解 \n" +
            "梦幻红颜已成疾","中日青草满茂都，一列彩云寻天际。\n" +
            "夏日重照无边地，黄草令人居陶醉","玉笛轻吹日已迟，\n" +
            "琼花飞绽最高枝。\n" +
            "婆娑曲径人归后，\n" +
            "寂寞空庭欲舞时。\n" +
            "林下寻踪心似梦，\n" +
            "篱边踏雪韵成诗。\n" +
            "屐痕浅淡知何处，\n" +
            "飘渺幽怀总是痴","冬日草树全被埋，\n" +
            "唯独梅花立而直。\n" +
            "堪称冬天坚强者，\n" +
            "他的名字叫梅花。","卧枝伤恨低，\n" +
            "遥问卧私谁，\n" +
            "暗似绿，\n" +
            "易头绿，\n" +
            "易透达春绿","君不见，\n" +
            "试卷之本树来上来，\n" +
            "流传下来撕源光光。\n" +
            "君不见，\n" +
            "作业笔丛水中来，\n" +
            "用完还要换一只。\n" +
            "一天用完十之笔，\n" +
            "作业2113无从下笔来。\n" +
            "我亦无他因手5261熟，\n" +
            "就要多做十张卷!\n" +
            "一年三百六十五，\n" +
            "天天都4102在做作业。\n" +
            "小学生，\n" +
            "性命残，\n" +
            "将进卷，\n" +
            "累蜷腰。\n" +
            "与君对1653答案，\n" +
            "结果反被老师骂。","做卷婆婆妈妈\n" +
            "考试心乱如麻\n" +
            "只因复习不佳\n" +
            "考卷发下\n" +
            "引来一顿臭骂","李白去到了洒店，\n" +
            "要酒十杯在桌前。\n" +
            "老板说要五十元。\n" +
            "模摸身上没有钱，\n" +
            "老板不能让他走，\n" +
            "他就呵呵变成仙。","何时众人平地上，\n" +
            "仰头看我碧霄中。\n" +
            "吃尽人间苦中苦，\n" +
            "方是登峰造极时","我是中国人，\n" +
            "我不学外语。\n" +
            "英语不及格，\n" +
            "英为我爱国。","李白来到烤鸭店，闻到烤鸭甚香甜。\n" +
            "口水直流三千尺，摸摸口袋没有钱","袋子真有趣.\n" +
            "不仅能够装东西,\n" +
            "还可以当作风筝飞来飞去","横眉冷对考试卷，\n" +
            "眼睛直看钢笔尖。\n" +
            "英雄不怕考零蛋,\n" +
            "挺起胸脯交白卷。","一天气候子\n" +
            "二天气猫子\n" +
            "三天气狗子\n" +
            "四天被气死"};
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinxiang);
        ming=this.getIntent().getStringExtra("mingzi");
        biao=this.getIntent().getStringExtra("biaoti");
        tupian= Integer.parseInt(this.getIntent().getStringExtra("tupian"));
        bbbc= Integer.parseInt(this.getIntent().getStringExtra("bbbc"));
        biaoa=findViewById(R.id.shequ_name);
        tv=findViewById(R.id.tv_title);
        tv.setText(biao);
        shijian=findViewById(R.id.shijian);
        bibibia=findViewById(R.id.bibibi);
        gaosi=findViewById(R.id.gaosi);
        biaoa.setText(ming);
//        pic=findViewById(R.id.pic);
        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(),imageidd[tupian]);
        Drawable drawable = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            drawable = new BitmapDrawable(blurBitmap(PinxiangActivity.this,bmp,25));
        }
        gaosi.setBackground(drawable);
//        pic.setImageBitmap(blurBitmap(PinxiangActivity.this,bmp,15));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        shijian.setText("时间:"+simpleDateFormat.format(date));
        int j = new Random().nextInt(15)%(15-0+1)+0;
        bibibia.setText(bibibi[bbbc]);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blurBitmap(Context context, Bitmap image, float blurRadius) {
        // 计算图片缩小后的长宽
        int width = Math.round(image.getWidth() * 0.1f);
        int height = Math.round(image.getHeight() *0.1f);

        // 将缩小后的图片做为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        // 创建一张渲染后的输出图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(blurRadius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);

        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }


}

package com.example.poetryline.detail.example;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.poetryline.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;


public class showDialog {
    private int height;
    private View view;
    private TextView zuozhes;
    private String zuozhem;
    private Button send1;
    private EditText editext;
    private ImageView showimg;
    private ImageView fangda;
    private ListView listView;
    private String fff;
    private TextView allaa;
    private String alla;
    private int width;
    private DialogAdapter dialogAdapter;
    private List<DialogM> dialogMS;
    private showBottomDialog.CustomeClickListener listener;
    public void BottomDialog(Context context) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        //2、设置布局
        view = View.inflate(context, R.layout.show_da, null);
        showimg=view.findViewById(R.id.showimg);
        fangda=view.findViewById(R.id.fangda);
        zuozhes=view.findViewById(R.id.zuozhem);
        allaa=view.findViewById(R.id.alla);
        allaa.setText(alla);
        initView(fff);

//        registerListeners();
        dialog.setContentView(view);
//        dialogMS=new ArrayList<>();
//        initAdapter();
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.CENTER);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle2);
        //设置对话框大小

        DisplayMetrics dm = new DisplayMetrics();
//        int height = dm.heightPixels/2;

        window.setLayout(width*9/11, width*9/6);
        fangda.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                allaa.setTextSize(dip2px(view.getContext(),6));
                zuozhes.setTextSize(dip2px(view.getContext(),15));
                window.setLayout(width,height);
                allaa.setLetterSpacing(0.4f);
                fangda.setVisibility(View.INVISIBLE);


            }
        });
        dialog.show();
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public String getZuozhem() {
        return zuozhem;
    }

    public void setZuozhem(String zuozhem) {
        this.zuozhem = zuozhem;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private void initAdapter() {
        DialogM dialog6=new DialogM();
        dialog6.setContent("牛逼牛逼啊，嘎嘎嘎嘎，帅哥看了都说好，啊哈哈哈，呻吟正好听，爱了了爱了呀");
        dialog6.setName("崔浩1");
        dialog6.setZannum2(0);
        dialog6.setIsZan(0);


        dialogMS.add(dialog6);


        dialogAdapter = new DialogAdapter(view.getContext(), dialogMS);
        listView.setAdapter(dialogAdapter);

        //开始获取数据库数据
        List<DialogM> records = new ArrayList<>();
        if (records == null || records.isEmpty()) return;

        dialogMS.addAll(records);
        dialogAdapter.notifyDataSetChanged();
        listView.setSelection(dialogMS.size() - 1);
    }
//    private void registerListeners() {
//        listener = new showBottomDialog.CustomeClickListener();
//        send1.setOnClickListener(listener);
//        editext.setOnClickListener(listener);;
//
//
//
//    }

    private void initView(String fdf) {
        RequestOptions options = new RequestOptions()
                .circleCrop();
        //设置不想要可见或者不想被点击

        Glide.with(view.getContext()).load(fff).apply(options).into(showimg)
        ;
        String[] tokens = zuozhem.split("]");
        zuozhes.setText(tokens[1]);

        // mEmTvBtn.setVisibility(View.GONE);//隐藏
        // mEmTvBtn.setCanRecord(false);//重写该方法，设置为不可点击
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFff() {
        return fff;
    }

    public void setFff(String fff) {
        this.fff = fff;

//        showimg.setImageURI(Uri.parse("ImageView.setImageURI(android.net.Uri)' on a null object reference"));
    }

    public String getAlla() {
        return alla;
    }

    public void setAlla(String alla) {
        this.alla = alla;
    }

    class CustomeClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.send1:
                    String content=editext.getText().toString();
                    if(content.equals("")||content.equals(null)){
                        Toast.makeText(
                                view.getContext(),
                                "消息内容为空！",
                                Toast.LENGTH_SHORT
                        ).show();
                    }else{
                        DialogM dialogM = new DialogM();

                        dialogM.setContent(content);
                        dialogM.setName("用户名");
                        dialogM.setZannum2(0);
                        dialogMS.add(dialogM);

                        dialogAdapter.notifyDataSetChanged();
                        listView.setSelection(dialogMS.size() - 1);
                        Toast.makeText(
                                view.getContext(),
                                "评论成功！",
                                Toast.LENGTH_SHORT
                        ).show();
                        editext.setText("");
                    }

                    break;










            }
        }
    }




}

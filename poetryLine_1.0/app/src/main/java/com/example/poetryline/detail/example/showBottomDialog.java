package com.example.poetryline.detail.example;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.R;

import java.util.ArrayList;
import java.util.List;


import static android.content.Context.WINDOW_SERVICE;

public class showBottomDialog{
    private View view;
    private Button send1;
    private EditText editext;
    private TextView zuozhes;
    private String zuozhem;
    private ListView listView;
    private DialogAdapter dialogAdapter;
    private List<DialogM> dialogMS;
    private CustomeClickListener listener;
    public void BottomDialog(Context context) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        //2、设置布局
        view = View.inflate(context, R.layout.dialog, null);
        initView();
        registerListeners();
        dialog.setContentView(view);
        dialogMS=new ArrayList<>();
        initAdapter();
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小

        DisplayMetrics dm = new DisplayMetrics();
//        int height = dm.heightPixels/2;
        WindowManager windowManager = (WindowManager)view.getContext().getSystemService(WINDOW_SERVICE);
        //获取屏幕对象
        Display defaultDisplay = windowManager.getDefaultDisplay();
        //获取屏幕的宽、高，单位是像素
        int height = defaultDisplay.getHeight();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, height*2/3);
        dialog.show();
    }

    public String getZuozhem() {
        return zuozhem;
    }

    public void setZuozhem(String zuozhem) {
        this.zuozhem = zuozhem;
    }

    private void initAdapter() {
        DialogM dialog6=new DialogM();
        dialog6.setContent("欢迎使用本APP！");
        dialog6.setName("诗行");
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
    private void registerListeners() {
        listener = new CustomeClickListener();
        send1.setOnClickListener(listener);
        editext.setOnClickListener(listener);;



    }

    private void initView() {
        listView=view.findViewById(R.id.listview2);
        send1=view.findViewById(R.id.send1);
        editext=view.findViewById(R.id.editext);
        //设置不想要可见或者不想被点击
        // mEmTvBtn.setVisibility(View.GONE);//隐藏
        // mEmTvBtn.setCanRecord(false);//重写该方法，设置为不可点击
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

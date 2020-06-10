package com.example.poetryline.detail.Send;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;


import com.example.poetryline.R;

import static android.content.Context.WINDOW_SERVICE;

public class SheDialog {
    private View view;
    private RelativeLayout xiangce;
    private RelativeLayout paizhao;
    private RelativeLayout quxiao;
//   private CustomeClickListener listener;

    public interface  didd{
        void picture();
        void snap();
        void cancel();
    }
    private didd fhf;

    public void BottomDialog(Context context, final didd fhf) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        //2、设置布局
        this.fhf=fhf;
        view = View.inflate(context, R.layout.she_dialog, null);
        initView();
//        registerListeners();
        dialog.setContentView(view);
        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fhf.snap();
                dialog.cancel();
            }
        });
        xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fhf.picture();
                dialog.cancel();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
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
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(context,180));
        dialog.show();
    }
    private void initView() {
        xiangce=view.findViewById(R.id.xiangce);
        paizhao=view.findViewById(R.id.paizhao);
        quxiao=view.findViewById(R.id.quxiao);
        //设置不想要可见或者不想被点击
        // mEmTvBtn.setVisibility(View.GONE);//隐藏
        // mEmTvBtn.setCanRecord(false);//重写该方法，设置为不可点击
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

//    private void registerListeners() {
//        listener = new CustomeClickListener();
//        paizhao.setOnClickListener(listener);
//        xiangce.setOnClickListener(listener);
//        quxiao.setOnClickListener(listener);
//
//
//
//    }
//
//    class CustomeClickListener implements View.OnClickListener{
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.paizhao:
//                    fhf.snap();
//
//                    break;
//                case R.id.xiangce:
//                    fhf.picture();
//
//                    break;
//                case R.id.quxiao:
//                    fhf.cancel();
//                    break;
//
//
//
//
//
//
//
//
//
//
//            }
//        }
//    }


}

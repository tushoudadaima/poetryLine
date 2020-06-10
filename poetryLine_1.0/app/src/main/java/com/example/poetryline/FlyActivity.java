package com.example.poetryline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class FlyActivity extends AppCompatActivity {
    private ImageView img_return;
    private TextView tv_time;
    private Button btn_setting;
    private EditText edt_in;
    private GridLayout grd_all;
    private Button btn_sure;
    private CustomOnClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fly);

        initView();
        registerListener();
    }

    private void registerListener() {
        listener = new CustomOnClickListener();
        btn_setting.setOnClickListener(listener);
        btn_sure.setOnClickListener(listener);
    }

    private void initView() {
        img_return = findViewById(R.id.img_return);
        tv_time = findViewById(R.id.tv_time);
        btn_setting = findViewById(R.id.btn_setting);
        edt_in = findViewById(R.id.edt_in);
        grd_all = findViewById(R.id.grd_all);
        btn_sure = findViewById(R.id.btn_sure);
    }

    public void onClick(View view) {
        String text = ((TextView) view).getText().toString();
        edt_in.setText(text);
    }

    class CustomOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.img_return:
                    break;
                case R.id.btn_setting:
                    Intent intent2 = new Intent(FlyActivity.this,RuleActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.btn_sure:
                    Intent intent = new Intent(FlyActivity.this,GameActivity.class);
                    intent.putExtra("key",edt_in.getText().toString());
                    startActivity(intent);
                    break;
            }
        }
    }
}

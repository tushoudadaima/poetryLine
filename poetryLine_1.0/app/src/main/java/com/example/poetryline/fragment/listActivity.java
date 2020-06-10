package com.example.poetryline.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.util.Log;
import com.example.poetryline.R;
import com.example.poetryline.detail.DetailActivity;

import android.widget.AdapterView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class listActivity extends AppCompatActivity {
    private String name;
    private Button img_to;
    private TextView text;
    private CustomAdapter1 customAdapter1;
    private ListView listView;
    private Handler handler;
    private List<Gushi> gushis= new ArrayList<>();
    private String name1;
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        img_to = findViewById(R.id.img_to);
        text = findViewById(R.id.text2);
        Intent intent=getIntent();
        name1=intent.getStringExtra("shi");
        Cn2Spell sye=Cn2Spell.getInstance();
        str=sye.getSelling(name1);
        gushis = new ArrayList<>();
        listView = findViewById(R.id.list);
        text.setText(name1);
        img_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listActivity.this.finish();
            }
        });

        myThread(str);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    CustomAdapter1 customAdapter1 = new CustomAdapter1(listActivity.this, gushis,R.layout.gushi_item);
                    listView.setAdapter(customAdapter1);
                }
            }
        };
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(listActivity.this, DetailActivity.class);
                String aa = gushis.get(position).getTitle();
                String[] bb = aa.split("\\(");
                String cc = bb[0];
                intent.putExtra("name",cc);
                startActivity(intent);
            }
        });
    }


    public void  myThread(final String pinyin){
        new Thread(){
            @Override
            public void run() {
                try {
                    // 网络加载HTML文档
                    String url = "https://so.gushiwen.org/gushi/"+pinyin+".aspx";
                    Document doc = Jsoup.connect(url).get();

                    Elements elements = doc.getElementsByClass("typecont");
                    Elements elements1 = elements.select("span");
                    for (int i = 0; i < elements1.size(); i++) {

                        String title = elements1.get(i).text();
                        Gushi gushiss=new Gushi(title);
                        gushis.add(gushiss);
                    }
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }
        }.start();
    }


}


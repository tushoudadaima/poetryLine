package com.example.poetryline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.poetryline.adapter.ShowAdapter;
import com.example.poetryline.detail.DetailActivity;
import com.example.poetryline.entity.Poetry;

import java.util.List;

public class ShowingActivity extends AppCompatActivity {
    private ListView listView;
    private List<Poetry> poetries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing);
        Intent intent = getIntent();
        poetries = (List<Poetry>) intent.getSerializableExtra("list");
        listView = findViewById(R.id.list_all);
        ShowAdapter showAdapter = new ShowAdapter(this,poetries,R.layout.show_item_layout);
        listView.setAdapter(showAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = view.findViewById(R.id.item_title);
                Intent intent1 = new Intent(ShowingActivity.this, DetailActivity.class);
                intent1.putExtra("name",textView.getText().toString().replaceAll("\\s","其"));
                startActivity(intent1);
            }
        });
    }
}

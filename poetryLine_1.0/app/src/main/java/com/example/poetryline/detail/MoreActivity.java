package com.example.poetryline.detail;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.poetryline.R;

import androidx.appcompat.app.AppCompatActivity;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        this.getSupportActionBar().hide();
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        webView.loadUrl("https://baike.baidu.com/item/"+name);
    }
}

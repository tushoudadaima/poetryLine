package com.example.poetryline.util;


import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequest(final String address, final Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(address);
        Request request = builder.build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}

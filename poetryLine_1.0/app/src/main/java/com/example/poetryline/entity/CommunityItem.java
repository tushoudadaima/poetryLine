package com.example.poetryline.entity;

public class CommunityItem {
    private int imgtxSource;
    private String name;
    private String body;
    private int imgptSource;

    public CommunityItem(int imgtxSource,String name,String body,int imgptSource) {
        this.imgtxSource = imgtxSource;
        this.name = name;
        this.body = body;
        this.imgptSource = imgptSource;
    }



    public void setImgtxSource(int imgtxSource) {
        this.imgtxSource = imgtxSource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setImgptSource(int imgptSource) {
        this.imgptSource = imgptSource;
    }

    public int getImgtxSource() {
        return imgtxSource;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    public int getImgptSource() {
        return imgptSource;
    }
}

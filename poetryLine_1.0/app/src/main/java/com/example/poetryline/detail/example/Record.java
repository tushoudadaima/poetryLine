package com.example.poetryline.detail.example;

import android.widget.ImageView;

/**
 * 作者：wgyscsf on 2017/1/2 18:44
 * 邮箱：wgyscsf@163.com
 * 博客：http://blog.csdn.net/wgyscsf
 */
public class Record {

    private String id;
    private int num;
    private String path;
    private int second;
    private boolean isPlayed;//是否已经播放过
    private boolean isPlaying;//是否正在播放
    private int isZan;

    private String name;
    private String date;
    private String userPhone;
    private String peotryName;

    public String getPeotryName() {
        return peotryName;
    }

    public void setPeotryName(String peotryName) {
        this.peotryName = peotryName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsZan() {
        return isZan;
    }

    public void setIsZan(int isZan) {
        this.isZan = isZan;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }


    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id='" + id + '\'' +
                ", num=" + num +
                ", path='" + path + '\'' +
                ", second=" + second +
                ", isPlayed=" + isPlayed +
                ", isPlaying=" + isPlaying +
                ", isZan=" + isZan +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", peotryName='" + peotryName + '\'' +
                '}';
    }
}

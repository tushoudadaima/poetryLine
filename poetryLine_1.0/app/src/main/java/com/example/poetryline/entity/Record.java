package com.example.poetryline.entity;

public class Record {
    private int id;
    private String date;
    private String userName;
    private String phone;
    private String type;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Record(int id, String date, String userName, String phone, String type, int score) {
        this.id = id;
        this.date = date;
        this.userName = userName;
        this.phone = phone;
        this.type = type;
        this.score = score;
    }

    public Record() {
    }
}

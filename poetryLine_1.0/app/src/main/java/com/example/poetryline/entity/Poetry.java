package com.example.poetryline.entity;

import java.io.Serializable;

public class Poetry implements Serializable {
    private int id;
    private String title;
    private String content;
    private String author;
    private String dynasty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public Poetry(String title, String content, String author, String dynasty) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.dynasty = dynasty;
    }

    public Poetry() {

    }
}

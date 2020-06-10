package com.example.poetryline.entity;

public class HomePagePoetry {

    private String title;
    private String dynasty;
    private String author;
    private String content;

    public HomePagePoetry(){

    }

    public HomePagePoetry(String title,String dynasty,String author,String content){
        this.title = title;
        this.dynasty = dynasty;
        this.author = author;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getDynasty() {
        return dynasty;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

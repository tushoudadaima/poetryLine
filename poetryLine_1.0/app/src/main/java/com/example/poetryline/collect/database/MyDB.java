package com.example.poetryline.collect.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDB extends SQLiteOpenHelper {
    public final static String TABLE_NAME_RECORD = "collect_record";

    public final static String RECORD_ID = "id";
    public final static String RECORD_TITLE = "title_name";
    public final static String RECORD_TIME = "create_time";


    public MyDB(Context context) {
        super(context, "test.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE "+TABLE_NAME_RECORD+" ("+RECORD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        RECORD_TITLE+" VARCHAR(30),"
//                +
//                        RECORD_TIME+" VARCHAR(30))"
//        );
        db.execSQL("create table Record(id varchar(225), path varchar(225), second int, isPlayed int,num int,isZan int,name varchar(255),date varchar(255),userPhone varchar(11),poetryName varchar(255))");//0:true,1：false
        db.execSQL("create table collect_record(id INTEGER PRIMARY KEY AUTOINCREMENT, title_name varchar(100), create_time varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
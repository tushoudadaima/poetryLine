package com.example.poetryline.detail.history;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.poetryline.collect.database.MyDB;
import com.example.poetryline.detail.example.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class DBManager {
//    private DBHelper helper;
    private MyDB helper;
    private SQLiteDatabase db;


    public DBManager(Context context) {
//        helper = new DBHelper(context);
        helper = new MyDB(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
    }


    public void add(Record record) {

        record.setId(UUID.randomUUID().toString());
        db = helper.getWritableDatabase();
        db.beginTransaction();  //开始事务
        try {
            db.execSQL("INSERT INTO Record VALUES(?, ?, ?, ?, ?, ?, ?, ?,?,?)", new Object[]{record.getId(), record.getPath(), record.getSecond(),record.isPlayed()? 0 : 1,record.getPath(),record.getIsZan(),record.getName(),record.getDate(),record.getUserPhone(),record.getPeotryName()});
            db.setTransactionSuccessful();  //设置事务成功完成
            Log.e("wgy", "添加数据库成功："+record.toString());
        } finally {
            db.endTransaction();    //结束事务
            db.close();
        }



    }


    public void updateRecord(Record record) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        ContentValues cv1 = new ContentValues();
        cv.put("isPlayed", record.isPlayed() ? 0 : 1);
        cv1.put("num",record.getNum());
        cv1.put("isZan",record.getIsZan());
        db.update("Record", cv, "id = ?", new String[]{record.getId()});
        db.update("Record", cv1, "id = ?", new String[]{record.getId()});
        db.close();
        Log.e("wgy", "添加数据库成功："+record.toString());

    }


    public void deleteRecord(Record record) {
        db = helper.getWritableDatabase();
        db.delete("Record", "id = ?", new String[]{record.getId()});
        db.close();

    }

    /**
     * query all persons, return list
     *
     * @return List<Person>
     */
    public List<Record> query(String str) {
        db = helper.getWritableDatabase();
        ArrayList<Record> records = new ArrayList<Record>();
        ArrayList<Record> records2 = new ArrayList<Record>();
        Cursor c = queryTheCursor(str);
        while (c.moveToNext()) {
            Record record = new Record();
            record.setId(c.getString(c.getColumnIndex("id")));
            record.setPath(c.getString(c.getColumnIndex("path")));
            record.setSecond(c.getInt(c.getColumnIndex("second")));
            record.setPlayed(c.getInt(c.getColumnIndex("isPlayed"))==0?true:false);
            record.setIsZan(c.getInt(c.getColumnIndex("isZan")));
            record.setNum(c.getInt(c.getColumnIndex("num")));
            //昵称
//            if (c.getString(c.getColumnIndex("name"))!=null){
                record.setName(c.getString(c.getColumnIndex("name")));
//            }
            //时间
            record.setDate(c.getString(c.getColumnIndex("date")));
            //头像
            record.setUserPhone(c.getString(c.getColumnIndex("userPhone")));
            //诗名
            record.setPeotryName(c.getString(c.getColumnIndex("poetryName")));

            records.add(record);
        }
        c.close();
        //颠倒顺序 将最新添加的语音放入第一行
        for (int i = records.size()-1 ;i>0;i--){
            records2.add(records.get(i));
        }

//        db.close();
        return records2;
    }

    /**
     * query all persons, return cursor
     *
     * @return Cursor
     */
    //根据诗的名称查找
    public Cursor queryTheCursor(String str) {
        Cursor c = db.rawQuery("SELECT * FROM Record WHERE poetryName = ?", new String[]{str});
//        Cursor c = db.rawQuery("SELECT * FROM Record", null);
//        Cursor c = db.query("Record",null,
//                "poetryName = ?",new String[]{str},null
//                ,
//                null,null
//        );


        Log.e("wgy", "根据诗名查找 SELECT * FROM Record WHERE poetryName = "+str);

        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}

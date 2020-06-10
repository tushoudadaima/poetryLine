package com.example.poetryline.collect;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poetryline.R;
import com.example.poetryline.collect.database.MyDB;
import com.example.poetryline.collect.enity.CollectRecord;
import com.example.poetryline.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    MyDB myDB;
    private ListView myListView;
//    private Button createButton;
    private MyBaseAdapter myBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        init();
    }

    //初始化控件
    private void init(){
        myListView = findViewById(R.id.list_view);

        List<CollectRecord> recordList = new ArrayList<>();
        myDB = new MyDB(this);
        SQLiteDatabase db = myDB.getReadableDatabase();
        Cursor cursor = db.query(MyDB.TABLE_NAME_RECORD,null,
                null,null,null
                ,
                null,null
                        +","+MyDB.RECORD_TIME+" DESC"

        );
        if(cursor.moveToFirst()){
            CollectRecord record;
            while (!cursor.isAfterLast()){
                record = new CollectRecord();
                record.setId(
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex(MyDB.RECORD_ID))));
                record.setTitleName(
                        cursor.getString(cursor.getColumnIndex(MyDB.RECORD_TITLE))
                );

                record.setCreateTime(
                        cursor.getString(cursor.getColumnIndex(MyDB.RECORD_TIME)));

                recordList.add(record);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        // 创建一个Adapter的实例
        myBaseAdapter = new MyBaseAdapter(this,recordList, R.layout.collect_list_item);
        myListView.setAdapter(myBaseAdapter);
        // 设置点击监听
        myListView.setOnItemClickListener(this);
        myListView.setOnItemLongClickListener(this);
    }



    public boolean saveFunction(String title, String createDate){

        boolean flag = true;
        if(flag){
            SQLiteDatabase db;
            ContentValues values;
            //  存储信息

            db = myDB.getWritableDatabase();
            values = new ContentValues();
            values.put(MyDB.RECORD_TITLE,title);
            values.put(MyDB.RECORD_TIME,createDate);
            db.insert(MyDB.TABLE_NAME_RECORD,null,values);
//            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();

            db.close();
        }
        return flag;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        CollectRecord record = (CollectRecord) myListView.getItemAtPosition(position);
        Intent intent = new Intent(CollectActivity.this, DetailActivity.class);
        intent.putExtra("name",record.getTitleName().trim());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        CollectRecord record = (CollectRecord) myListView.getItemAtPosition(position);
        showDialog(record,position);
        return true;
    }

    void showDialog(final CollectRecord record, final int position){

        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(CollectActivity.this);
        dialog.setTitle("是否删除此收藏？");
        dialog.setPositiveButton("删除",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = myDB.getWritableDatabase();
                        db.delete(MyDB.TABLE_NAME_RECORD,
                                MyDB.RECORD_ID +"=?",
                                new String[]{String.valueOf(record.getId())});
                        db.close();
                        myBaseAdapter.removeItem(position);
                        myListView.post(new Runnable() {
                            @Override
                            public void run() {
                                myBaseAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dialog.show();
    }



    /**
     * ListView展示的适配器类
     */
    class MyBaseAdapter extends BaseAdapter {
        private List<CollectRecord> recordList;//数据集合
        private Context context;
        private int layoutId;

        public MyBaseAdapter(Context context, List<CollectRecord> recordList, int layoutId){
            this.context = context;
            this.recordList = recordList;
            this.layoutId = layoutId;
        }

        @Override
        public int getCount() {
            if (recordList!=null&&recordList.size()>0)
                return recordList.size();
            else
                return 0;
        }

        @Override
        public Object getItem(int position) {
            if (recordList!=null&&recordList.size()>0)
                return recordList.get(position);
            else
                return null;
        }

        public void removeItem(int position){
            this.recordList.remove(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(
                        getApplicationContext()).inflate(R.layout.collect_list_item, parent,
                        false);
                viewHolder  = new ViewHolder();
                viewHolder.titleView = convertView.findViewById(R.id.list_item_title);
                viewHolder.timeView = convertView.findViewById(R.id.list_item_time);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            CollectRecord record = recordList.get(position);
            String tile = record.getTitleName();
            viewHolder.titleView.setText((position+1)+"."+(tile.length()>10?tile.substring(0,10)+"...":tile));
            viewHolder.timeView.setText(record.getCreateTime());
            return convertView;
        }
    }

    /**
     * ListView里的组件包装类
     */
    class ViewHolder{
        TextView titleView;
        TextView timeView;
    }

}



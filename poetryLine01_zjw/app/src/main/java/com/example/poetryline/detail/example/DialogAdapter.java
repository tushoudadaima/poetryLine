package com.example.poetryline.detail.example;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.R;

import java.util.List;



public class DialogAdapter extends BaseAdapter {
    Context mContext;
    List<DialogM> dialogMS;

    public DialogAdapter(Context context, List<DialogM> dialogMs) {
        this.mContext = context;
        this.dialogMS=dialogMs;

    }
    @Override
    public int getCount() {
        return dialogMS.size();
    }

    @Override
    public Object getItem(int position) {
        return dialogMS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.dialog_item, null);
            viewHolder = new ViewHolder();
            viewHolder.content = (TextView) convertView.findViewById(R.id.content22);
            viewHolder.name=(TextView)convertView.findViewById(R.id.name22) ;
            viewHolder.zan2=(ImageView)convertView.findViewById(R.id.zan2) ;
            viewHolder.zannum2=(TextView)convertView.findViewById(R.id.zannum2) ;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final DialogM dialogM = dialogMS.get(position);
        viewHolder.content.setText(dialogM.getContent()+"");
        viewHolder.name.setText(dialogM.getName());
        viewHolder.zannum2.setText(dialogM.getZannum2()+"");
        if((Integer)dialogM.getIsZan()==0){
                    viewHolder.zan2.setImageDrawable(mContext.getResources().getDrawable(R.drawable.weizan2));
        }else{
            viewHolder.zan2.setImageDrawable(mContext.getResources().getDrawable(R.drawable.yizan));
        }

        viewHolder.zan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Integer)dialogM.getIsZan()==0){
                    viewHolder.zan2.setImageDrawable(mContext.getResources().getDrawable(R.drawable.yizan));
                    int i=Integer.parseInt((String) viewHolder.zannum2.getText())+1;

                    viewHolder.zannum2.setText(i+"");
                    ObjectAnimator translated =
                            ObjectAnimator.ofFloat(viewHolder.zan2, "scaleY",1,2,1);
                    translated.setDuration(600);
                    translated.setRepeatCount(0);
                    translated.start();
                    ObjectAnimator translated1 =
                            ObjectAnimator.ofFloat(viewHolder.zan2, "scaleX",1, 2,1);
                    translated1.setDuration(600);
                    translated1.setRepeatCount(0);
                    translated1.start();
                    notifyDataSetChanged();
                    dialogM.setZannum2(i);
                    dialogM.setIsZan(1);
                    viewHolder.zannum2.setText(i+"");
                }else{
                    Toast.makeText(
                            view.getContext(),
                            "您已经赞过啦！",
                            Toast.LENGTH_SHORT
                    ).show();
                }


            }

        });

        return convertView;
    }
    class ViewHolder {
        TextView name;
        TextView content;
        TextView zannum2;
        ImageView zan2;
    }
}

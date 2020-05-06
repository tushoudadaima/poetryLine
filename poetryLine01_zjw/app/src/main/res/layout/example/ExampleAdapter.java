package yl.net.jay.myapplication.example;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yl.net.jay.myapplication.DetailActivity;
import yl.net.jay.myapplication.R;
import yl.net.jay.myapplication.VoiceActivity;
import yl.net.jay.myapplication.manager.MediaManager;
import yl.net.jay.myapplication.utils.CommonsUtils;

/**
 * 作者：wgyscsf on 2017/1/2 18:46
 * 邮箱：wgyscsf@163.com
 * 博客：http://blog.csdn.net/wgyscsf
 */
public class ExampleAdapter extends BaseAdapter {
    List<Record> mRecords;
    private showBottomDialog show= new showBottomDialog();
    Context mContext;
    List<AnimationDrawable> mAnimationDrawables;
    int pos = -1;//标记当前录音索引，默认没有播放任何一个

    public ExampleAdapter(Context context, List<Record> records) {
        this.mContext = context;
        this.mRecords = records;
        mAnimationDrawables = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return mRecords.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_example_activity, null);
            viewHolder = new ViewHolder();
            viewHolder.ieaHeadImg = (ImageView) convertView.findViewById(R.id.iea_headImg);
            viewHolder.pinlun=(ImageView)convertView.findViewById(R.id.pinlun) ;
            viewHolder.zannum=(TextView)convertView.findViewById(R.id.zannum);
            viewHolder.zan=(ImageView)convertView.findViewById(R.id.zan) ;
            viewHolder.ieaIvVoiceLine = (ImageView) convertView.findViewById(R.id.iea_iv_voiceLine);
            viewHolder.ieaLlSinger = (LinearLayout) convertView.findViewById(R.id.iea_ll_singer);
            viewHolder.ieaTvVoicetime1 = (TextView) convertView.findViewById(R.id.iea_tv_voicetime1);
            viewHolder.ieaIvRed = (ImageView) convertView.findViewById(R.id.iea_iv_red);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        final Record record = mRecords.get(position);
        //设置显示时长
        viewHolder.ieaTvVoicetime1.setText(record.getSecond() <= 0 ? 1 + "''" : record.getSecond() + "''");
        viewHolder.zannum.setText(record.getNum()+"");
        if((Integer)record.getIsZan()==0){
            viewHolder.zan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.weizan2));
        }else{
            viewHolder.zan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.yizan));
        }
        if (!record.isPlayed()) {
            viewHolder.ieaIvRed.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ieaIvRed.setVisibility(View.GONE);
        }
        //更改并显示录音条长度
        RelativeLayout.LayoutParams ps = (RelativeLayout.LayoutParams) viewHolder.ieaIvVoiceLine.getLayoutParams();
        ps.width = CommonsUtils.getVoiceLineWight(mContext, record.getSecond());
        viewHolder.ieaIvVoiceLine.setLayoutParams(ps); //更改语音长条长度

        //开始设置监听
        final LinearLayout ieaLlSinger = viewHolder.ieaLlSinger;
        viewHolder.pinlun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
//                //2、设置布局
//                View views = View.inflate(mContext, R.layout.dialog, null);
//                dialog.setContentView(views);
//
//                Window window = dialog.getWindow();
//                //设置弹出位置
//                window.setGravity(Gravity.BOTTOM);
//                //设置弹出动画
//                window.setWindowAnimations(R.style.main_menu_animStyle);
//                //设置对话框大小
//                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                dialog.show();
//
//                dialog.findViewById(R.id.tv_take_photo).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//                dialog.findViewById(R.id.tv_take_pic).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//                dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
                show.BottomDialog(mContext);
            }
        });
        viewHolder.zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Integer)record.getIsZan()==0){
                    viewHolder.zan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.yizan));
                    int i=Integer.parseInt((String) viewHolder.zannum.getText())+1;

                    viewHolder.zannum.setText(i+"");
                    ObjectAnimator translated =
                            ObjectAnimator.ofFloat(viewHolder.zan, "scaleY",1,2,1);
                    translated.setDuration(600);
                    translated.setRepeatCount(0);
                    translated.start();
                    ObjectAnimator translated1 =
                            ObjectAnimator.ofFloat(viewHolder.zan, "scaleX",1, 2,1);
                    translated1.setDuration(600);
                    translated1.setRepeatCount(0);
                    translated1.start();
                    record.setNum(i);
                    record.setIsZan(1);
                    notifyDataSetChanged();
                    try {
                        ((VoiceActivity) mContext).getMgr().updateRecord(record);
                    } catch (Exception e) {
                        ((DetailActivity) mContext).getMgr().updateRecord(record);
                    }
                }else{
                    Toast.makeText(
                            view.getContext(),
                            "您已经赞过啦！",
                            Toast.LENGTH_SHORT
                    ).show();
                }

            }
        });
        viewHolder.ieaIvVoiceLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //只要点击就设置为已播放状态（隐藏小红点）
                record.setPlayed(true);
                notifyDataSetChanged();
                try {
                    ((VoiceActivity) mContext).getMgr().updateRecord(record);
                } catch (Exception e) {
                    ((DetailActivity) mContext).getMgr().updateRecord(record);
                }
                //这里更新数据库小红点。这里不知道为什么可以强转建议复习复习基础~


                final AnimationDrawable animationDrawable = (AnimationDrawable) ieaLlSinger.getBackground();
                //重置动画
                resetAnim(animationDrawable);
                animationDrawable.start();

                //处理点击正在播放的语音时，可以停止；再次点击时重新播放。
                if (pos == position) {
                    if (record.isPlaying()) {
                        record.setPlaying(false);
                        MediaManager.release();
                        animationDrawable.stop();
                        animationDrawable.selectDrawable(0);//reset
                        return;
                    } else {
                        record.setPlaying(true);
                    }
                }
                //记录当前位置正在播放。
                pos = position;
                record.setPlaying(true);

                //播放前重置。
                MediaManager.release();
                //开始实质播放
                MediaManager.playSound(record.getPath(),
                        new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                animationDrawable.selectDrawable(0);//显示动画第一帧
                                animationDrawable.stop();

                                //播放完毕，当前播放索引置为-1。
                                pos = -1;
                            }
                        });
            }
        });
        return convertView;
    }

    private void resetData() {
        for (Record record : mRecords) {
            record.setPlaying(false);//保证在第二次点击该语音栏时当作没有“不是在播放状态”。
        }
    }

    private void resetAnim(AnimationDrawable animationDrawable) {
        if (!mAnimationDrawables.contains(animationDrawable)) {
            mAnimationDrawables.add(animationDrawable);
        }
        for (AnimationDrawable ad : mAnimationDrawables) {
            ad.selectDrawable(0);
            ad.stop();
        }
    }

    class ViewHolder {
        ImageView ieaHeadImg;
        ImageView ieaIvVoiceLine;
        LinearLayout ieaLlSinger;
        TextView ieaTvVoicetime1;
        ImageView pinlun;
        TextView zannum;
        ImageView zan;
        ImageView ieaIvRed;
    }
}

package com.example.poetryline.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.poetryline.R;
import com.example.poetryline.adapter.CustomAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SortFragment extends Fragment {
    EditText etSearch;
    TextView btSearch;
    String Gname;
    String Sname;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sort_fragment,container,false);

        return view;
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btSearch = getActivity().findViewById(R.id.class_btn_search);
        etSearch = getActivity().findViewById(R.id.class_et_search);
        getActivity().findViewById(R.id.class_btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //创建listView
        final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>() {
        };
        ListView listView = getActivity().findViewById(R.id.lv_list);
        final Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "诗人");
        list.add(item1);
        Map<String, Object> item2 = new HashMap<>();
        item2.put("name", "节日");
        list.add(item2);
        Map<String, Object> item3 = new HashMap<>();
        item3.put("name", "词牌");
        list.add(item3);
        Map<String, Object> item4 = new HashMap<>();
        item4.put("name", "课本");
        list.add(item4);
        Map<String, Object> item5 = new HashMap<>();
        item5.put("name", "地理");
        list.add(item5);
        Map<String, Object> item6 = new HashMap<>();
        item6.put("name", "选集");
        list.add(item6);
        Map<String, Object> item7 = new HashMap<>();
        item7.put("name", "主题");
        list.add(item7);
        CustomAdapter customAdapter = new CustomAdapter(getContext(), list, R.layout.list_item);
        listView.setAdapter(customAdapter);
        //创建GridView
        final GridView gridView = getActivity().findViewById(R.id.gv_list);
        //gridview点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        //菜式
        final List<Map<String, Object>> gvlistcaixi = new ArrayList<>();
        Map<String, Object> gvitem11 = new HashMap<>();
        gvitem11.put("name", "李白");
        gvitem11.put("image", R.drawable.libai);
        gvlistcaixi.add(gvitem11);
        Map<String, Object> gvitem12 = new HashMap<>();
        gvitem12.put("name", "杜甫");
        gvitem12.put("image", R.drawable.dufu);
        gvlistcaixi.add(gvitem12);
        Map<String, Object> gvitem13 = new HashMap<>();
        gvitem13.put("name", "白居易");
        gvitem13.put("image", R.drawable.baujuyi);
        gvlistcaixi.add(gvitem13);
        Map<String, Object> gvitem14 = new HashMap<>();
        gvitem14.put("name", "李商隐");
        gvitem14.put("image", R.drawable.lishangyin);
        gvlistcaixi.add(gvitem14);
        Map<String, Object> gvitem15 = new HashMap<>();
        gvitem15.put("name", "王维");
        gvitem15.put("image", R.drawable.wangwei);
        gvlistcaixi.add(gvitem15);
        Map<String, Object> gvitem16 = new HashMap<>();
        gvitem16.put("name", "刘禹锡");
        gvitem16.put("image", R.drawable.liuyuxi);
        gvlistcaixi.add(gvitem16);
        Map<String, Object> gvitem17 = new HashMap<>();
        gvitem17.put("name", "贺知章");
        gvitem17.put("image", R.drawable.hezhizhang);
        gvlistcaixi.add(gvitem17);
        Map<String, Object> gvitem18 = new HashMap<>();
        gvitem18.put("name", "王安石");
        gvitem18.put("image", R.drawable.wanganshi);
        gvlistcaixi.add(gvitem18);
        Map<String, Object> gvitem19 = new HashMap<>();
        gvitem19.put("name", "辛弃疾");
        gvitem19.put("image", R.drawable.xinqiji);
        gvlistcaixi.add(gvitem19);
        Map<String, Object> gvitem20 = new HashMap<>();
        gvitem20.put("name", "孟浩然");
        gvitem20.put("image", R.drawable.menghaoran);
        gvlistcaixi.add(gvitem20);
        Map<String, Object> gvitem21 = new HashMap<>();
        gvitem21.put("name", "苏轼");
        gvitem21.put("image", R.drawable.sushi);
        gvlistcaixi.add(gvitem21);
        Map<String, Object> gvitem22 = new HashMap<>();
        gvitem22.put("name", "李煜");
        gvitem22.put("image", R.drawable.liyu);
        gvlistcaixi.add(gvitem22);
        Map<String, Object> gvitem23 = new HashMap<>();
        gvitem23.put("name", "李清照");
        gvitem23.put("image", R.drawable.liqingzhao);
        gvlistcaixi.add(gvitem23);
        Map<String, Object> gvitem24 = new HashMap<>();
        gvitem24.put("name", "柳永");
        gvitem24.put("image", R.drawable.liuyong);
        gvlistcaixi.add(gvitem24);
        Map<String, Object> gvitem25 = new HashMap<>();
        gvitem25.put("name", "纳兰性德");
        gvitem25.put("image", R.drawable.nalanxingde);
        gvlistcaixi.add(gvitem25);
        Map<String, Object> gvitem26 = new HashMap<>();
        gvitem26.put("name", "袁枚");
        gvitem26.put("image", R.drawable.yuanmei);
        gvlistcaixi.add(gvitem26);
        Map<String, Object> gvitem27 = new HashMap<>();
        gvitem27.put("name", "屈原");
        gvitem27.put("image", R.drawable.quyuan);
        gvlistcaixi.add(gvitem27);
        //菜系
        final List<Map<String, Object>> gvlistshicai = new ArrayList<>();
        Map<String, Object> gvitem28 = new HashMap<>();
        gvitem28.put("name", "春节");
        gvitem28.put("image", R.drawable.chunjie);
        gvlistshicai.add(gvitem28);
        Map<String, Object> gvitem29 = new HashMap<>();
        gvitem29.put("name", "元宵");
        gvitem29.put("image", R.drawable.yuanxiao);
        gvlistshicai.add(gvitem29);
        Map<String, Object> gvitem30 = new HashMap<>();
        gvitem30.put("name", "寒食");
        gvitem30.put("image", R.drawable.hanshi);
        gvlistshicai.add(gvitem30);
        Map<String, Object> gvitem31 = new HashMap<>();
        gvitem31.put("name", "清明");
        gvitem31.put("image", R.drawable.qingming);
        gvlistshicai.add(gvitem31);
        Map<String, Object> gvitem32 = new HashMap<>();
        gvitem32.put("name", "端午");
        gvitem32.put("image", R.drawable.duanwu);
        gvlistshicai.add(gvitem32);
        Map<String, Object> gvitem33 = new HashMap<>();
        gvitem33.put("name", "七夕");
        gvitem33.put("image", R.drawable.qixi);
        gvlistshicai.add(gvitem33);
        Map<String, Object> gvitem34 = new HashMap<>();
        gvitem34.put("name", "中秋");
        gvitem34.put("image", R.drawable.zhongqiu);
        gvlistshicai.add(gvitem34);
        Map<String, Object> gvitem35 = new HashMap<>();
        gvitem35.put("name", "重阳");
        gvitem35.put("image", R.drawable.chongyang);
        gvlistshicai.add(gvitem35);
        //食材
        final List<Map<String, Object>> gvlistweidao = new ArrayList<>();
        Map<String, Object> gvitem36 = new HashMap<>();
        gvitem36.put("name", "如梦令");
        gvitem36.put("image", R.drawable.rumengling);
        gvlistweidao.add(gvitem36);
        Map<String, Object> gvitem37 = new HashMap<>();
        gvitem37.put("name", "醉花阴");
        gvitem37.put("image", R.drawable.zuihuayin);
        gvlistweidao.add(gvitem37);
        Map<String, Object> gvitem38 = new HashMap<>();
        gvitem38.put("name", "虞美人");
        gvitem38.put("image", R.drawable.yumeiren);
        gvlistweidao.add(gvitem38);
        Map<String, Object> gvitem39 = new HashMap<>();
        gvitem39.put("name", "定风波");
        gvitem39.put("image", R.drawable.dingfengbo);
        gvlistweidao.add(gvitem39);
        Map<String, Object> gvitem40 = new HashMap<>();
        gvitem40.put("name", "雨霖铃");
        gvitem40.put("image", R.drawable.yulinling);
        gvlistweidao.add(gvitem40);
        Map<String, Object> gvitem41 = new HashMap<>();
        gvitem41.put("name", "长相思");
        gvitem41.put("image", R.drawable.changxiangsi);
        gvlistweidao.add(gvitem41);
        Map<String, Object> gvitem42 = new HashMap<>();
        gvitem42.put("name", "念奴娇");
        gvitem42.put("image", R.drawable.niannujiao);
        gvlistweidao.add(gvitem42);
        Map<String, Object> gvitem43 = new HashMap<>();
        gvitem43.put("name", "六州歌头");
        gvitem43.put("image", R.drawable.liuzhougetou);
        gvlistweidao.add(gvitem43);
        Map<String, Object> gvitem44 = new HashMap<>();
        gvitem44.put("name", "忆江南");
        gvitem44.put("image", R.drawable.yijiangnan);
        gvlistweidao.add(gvitem44);
        Map<String, Object> gvitem45 = new HashMap<>();
        gvitem45.put("name", "鹤冲天");
        gvitem45.put("image", R.drawable.hechongtian);
        gvlistweidao.add(gvitem45);
        Map<String, Object> gvitem46 = new HashMap<>();
        gvitem46.put("name", "临江仙");
        gvitem46.put("image", R.drawable.linjiangxian);
        gvlistweidao.add(gvitem46);
        //烹饪方法
        final List<Map<String, Object>> gvlistpengren = new ArrayList<>();
        Map<String, Object> gvitem47 = new HashMap<>();
        gvitem47.put("name", "小学古诗词");
        gvitem47.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem47);
        Map<String, Object> gvitem48 = new HashMap<>();
        gvitem48.put("name", "初中古诗词");
        gvitem48.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem48);
        Map<String, Object> gvitem49 = new HashMap<>();
        gvitem49.put("name", "高中古诗词");
        gvitem49.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem49);
        Map<String, Object> gvitem50 = new HashMap<>();
        gvitem50.put("name", "小学人教版");
        gvitem50.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem50);
        Map<String, Object> gvitem51 = new HashMap<>();
        gvitem51.put("name", "初中人教版");
        gvitem51.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem51);
        Map<String, Object> gvitem52 = new HashMap<>();
        gvitem52.put("name", "高中人教版");
        gvitem52.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem52);
        Map<String, Object> gvitem53 = new HashMap<>();
        gvitem53.put("name", "学前古诗词");
        gvitem53.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem53);
        Map<String, Object> gvitem54 = new HashMap<>();
        gvitem54.put("name", "幼儿古诗词");
        gvitem54.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem54);
        //口味
        final List<Map<String, Object>> gvlistrenqun = new ArrayList<>();
        Map<String, Object> gvitem56 = new HashMap<>();
        gvitem56.put("name", "黄鹤楼");
        gvitem56.put("image", R.drawable.huanghelou);
        gvlistrenqun.add(gvitem56);
        Map<String, Object> gvitem57 = new HashMap<>();
        gvitem57.put("name", "西湖");
        gvitem57.put("image", R.drawable.xihu);
        gvlistrenqun.add(gvitem57);
        Map<String, Object> gvitem58 = new HashMap<>();
        gvitem58.put("name", "洞庭湖");
        gvitem58.put("image", R.drawable.dongtinghu);
        gvlistrenqun.add(gvitem58);
        Map<String, Object> gvitem59 = new HashMap<>();
        gvitem59.put("name", "泰山");
        gvitem59.put("image", R.drawable.taishan);
        gvlistrenqun.add(gvitem59);
        Map<String, Object> gvitem60 = new HashMap<>();
        gvitem60.put("name", "黄河");
        gvitem60.put("image", R.drawable.huanghe);
        gvlistrenqun.add(gvitem60);
        Map<String, Object> gvitem61 = new HashMap<>();
        gvitem61.put("name", "平山堂");
        gvitem61.put("image", R.drawable.pingshantang);
        gvlistrenqun.add(gvitem61);
        Map<String, Object> gvitem62 = new HashMap<>();
        gvitem62.put("name", "长城");
        gvitem62.put("image", R.drawable.changcheng);
        gvlistrenqun.add(gvitem62);
        Map<String, Object> gvitem63 = new HashMap<>();
        gvitem63.put("name", "长江");
        gvitem63.put("image", R.drawable.changjiang);
        gvlistrenqun.add(gvitem63);
        Map<String, Object> gvitem64 = new HashMap<>();
        gvitem64.put("name", "长安");
        gvitem64.put("image", R.drawable.changan);
        gvlistrenqun.add(gvitem64);
        Map<String, Object> gvitem65 = new HashMap<>();
        gvitem65.put("name", "寒山寺");
        gvitem65.put("image", R.drawable.hanshansi);
        gvlistrenqun.add(gvitem65);
        Map<String, Object> gvitem66 = new HashMap<>();
        gvitem66.put("name", "庐山");
        gvitem66.put("image", R.drawable.lushan);
        gvlistrenqun.add(gvitem66);
        Map<String, Object> gvitem67 = new HashMap<>();
        gvitem67.put("name", "鹳雀楼");
        gvitem67.put("image", R.drawable.guanquelou);
        gvlistrenqun.add(gvitem67);

        final List<Map<String, Object>> gvlistshiji = new ArrayList<>();
        Map<String, Object> gvitem68 = new HashMap<>();
        gvitem68.put("name", "诗经全集");
        gvitem68.put("image", R.drawable.shijing);
        gvlistshiji.add(gvitem68);
        Map<String, Object> gvitem69 = new HashMap<>();
        gvitem69.put("name", "楚辞全集");
        gvitem69.put("image", R.drawable.chuci);
        gvlistshiji.add(gvitem69);
        Map<String, Object> gvitem70 = new HashMap<>();
        gvitem70.put("name", "道德经");
        gvitem70.put("image", R.drawable.daodejing);
        gvlistshiji.add(gvitem70);
        Map<String, Object> gvitem71 = new HashMap<>();
        gvitem71.put("name", "千家诗");
        gvitem71.put("image", R.drawable.qianjiashi);
        gvlistshiji.add(gvitem71);
        Map<String, Object> gvitem72 = new HashMap<>();
        gvitem72.put("name", "唐诗三百首");
        gvitem72.put("image", R.drawable.tangshisanbaishou);
        gvlistshiji.add(gvitem72);
        Map<String, Object> gvitem73 = new HashMap<>();
        gvitem73.put("name", "宋词三百首");
        gvitem73.put("image", R.drawable.songcisanbaishou);
        gvlistshiji.add(gvitem73);
        Map<String, Object> gvitem74 = new HashMap<>();
        gvitem74.put("name", "元曲三百首");
        gvitem74.put("image", R.drawable.yuanqusanbaishou);
        gvlistshiji.add(gvitem74);
        Map<String, Object> gvitem75 = new HashMap<>();
        gvitem75.put("name", "乐府诗集");
        gvitem75.put("image", R.drawable.yuefushiji);
        gvlistshiji.add(gvitem75);

        final List<Map<String, Object>> gvlistzhuti = new ArrayList<>();
        Map<String, Object> gvitem76 = new HashMap<>();
        gvitem76.put("name", "战争");
        gvitem76.put("image", R.drawable.zhanzheng);
        gvlistzhuti.add(gvitem76);
        Map<String, Object> gvitem77 = new HashMap<>();
        gvitem77.put("name", "离别");
        gvitem77.put("image", R.drawable.libie);
        gvlistzhuti.add(gvitem77);
        Map<String, Object> gvitem78 = new HashMap<>();
        gvitem78.put("name", "悼亡");
        gvitem78.put("image", R.drawable.daowang);
        gvlistzhuti.add(gvitem78);
        Map<String, Object> gvitem79 = new HashMap<>();
        gvitem79.put("name", "思乡");
        gvitem79.put("image", R.drawable.sixiang);
        gvlistzhuti.add(gvitem79);
        Map<String, Object> gvitem80 = new HashMap<>();
        gvitem80.put("name", "孤独");
        gvitem80.put("image", R.drawable.gudu);
        gvlistzhuti.add(gvitem80);
        Map<String, Object> gvitem81 = new HashMap<>();
        gvitem81.put("name", "壮志");
        gvitem81.put("image", R.drawable.zhuangzhi);
        gvlistzhuti.add(gvitem81);
        Map<String, Object> gvitem82 = new HashMap<>();
        gvitem82.put("name", "田园");
        gvitem82.put("image", R.drawable.tianyuan);
        gvlistzhuti.add(gvitem82);
        Map<String, Object> gvitem83 = new HashMap<>();
        gvitem83.put("name", "边塞");
        gvitem83.put("image", R.drawable.biansai);
        gvlistzhuti.add(gvitem83);
        CustomAdapter customAdaptercaixi = new CustomAdapter(getContext(), gvlistcaixi, R.layout.gridview_item);
        gridView.setAdapter(customAdaptercaixi);

        //list点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //点击菜式
                    case 0:
                        CustomAdapter customAdaptercaixi = new CustomAdapter(getContext(), gvlistcaixi, R.layout.gridview_item);
                        gridView.setAdapter(customAdaptercaixi);
                        break;
                    //点击菜系
                    case 1:
                        CustomAdapter customAdaptershicai = new CustomAdapter(getContext(), gvlistshicai, R.layout.gridview_item);
                        gridView.setAdapter(customAdaptershicai);
                        break;
                    //点击食材
                    case 2:
                        CustomAdapter customAdapterweidai = new CustomAdapter(getContext(), gvlistweidao, R.layout.gridview_item);
                        gridView.setAdapter(customAdapterweidai);
                        break;
                    //点击烹饪方法
                    case 3:
                        CustomAdapter customAdapterpengren = new CustomAdapter(getContext(), gvlistpengren, R.layout.gridview_item);
                        gridView.setAdapter(customAdapterpengren);
                        break;
                    //点击口味
                    case 4:
                        CustomAdapter customAdapterrenqun = new CustomAdapter(getContext(), gvlistrenqun, R.layout.gridview_item);
                        gridView.setAdapter(customAdapterrenqun);
                        break;
                    case 5:
                        CustomAdapter customAdaptershiji = new CustomAdapter(getContext(), gvlistshiji, R.layout.gridview_item);
                        gridView.setAdapter(customAdaptershiji);
                        break;
                    case 6:
                        CustomAdapter customAdapterzhuti = new CustomAdapter(getContext(), gvlistzhuti, R.layout.gridview_item);
                        gridView.setAdapter(customAdapterzhuti);
                        break;
                }
            }
        });
    }

}

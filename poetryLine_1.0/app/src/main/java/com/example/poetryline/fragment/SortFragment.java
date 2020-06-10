package com.example.poetryline.fragment;


import android.content.Intent;
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
    private TextView gv_name;
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
        item1.put("name", "情感");
        list.add(item1);
        Map<String, Object> item2 = new HashMap<>();
        item2.put("name", "节日");
        list.add(item2);
        Map<String, Object> item3 = new HashMap<>();
        item3.put("name", "写景");
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
                gv_name = view.findViewById(R.id.gv_name);
                Intent intent = new Intent(getActivity(), listActivity.class);
                intent.putExtra("shi",gv_name.getText().toString());
                startActivity(intent);
            }
        });
        //菜式
        final List<Map<String, Object>> gvlistcaixi = new ArrayList<>();
        Map<String, Object> gvitem11 = new HashMap<>();
        gvitem11.put("name", "抒情");
        gvitem11.put("image", R.drawable.shuqing);
        gvlistcaixi.add(gvitem11);
        Map<String, Object> gvitem12 = new HashMap<>();
        gvitem12.put("name", "怀古");
        gvitem12.put("image", R.drawable.huaigu);
        gvlistcaixi.add(gvitem12);
        Map<String, Object> gvitem13 = new HashMap<>();
        gvitem13.put("name", "爱国");
        gvitem13.put("image", R.drawable.aiguo);
        gvlistcaixi.add(gvitem13);
        Map<String, Object> gvitem14 = new HashMap<>();
        gvitem14.put("name", "送别");
        gvitem14.put("image", R.drawable.songbie);
        gvlistcaixi.add(gvitem14);
        Map<String, Object> gvitem15 = new HashMap<>();
        gvitem15.put("name", "思念");
        gvitem15.put("image", R.drawable.sinian);
        gvlistcaixi.add(gvitem15);
        Map<String, Object> gvitem16 = new HashMap<>();
        gvitem16.put("name", "爱情");
        gvitem16.put("image", R.drawable.aiqing);
        gvlistcaixi.add(gvitem16);
        Map<String, Object> gvitem17 = new HashMap<>();
        gvitem17.put("name", "老师");
        gvitem17.put("image", R.drawable.laoshi);
        gvlistcaixi.add(gvitem17);
        Map<String, Object> gvitem18 = new HashMap<>();
        gvitem18.put("name", "母亲");
        gvitem18.put("image", R.drawable.muqin);
        gvlistcaixi.add(gvitem18);
        Map<String, Object> gvitem19 = new HashMap<>();
        gvitem19.put("name", "友情");
        gvitem19.put("image", R.drawable.youqing);
        gvlistcaixi.add(gvitem19);
        Map<String, Object> gvitem20 = new HashMap<>();
        gvitem20.put("name", "惜时");
        gvitem20.put("image", R.drawable.xishi);
        gvlistcaixi.add(gvitem20);
        Map<String, Object> gvitem21 = new HashMap<>();
        gvitem21.put("name", "忧民");
        gvitem21.put("image", R.drawable.youmin);
        gvlistcaixi.add(gvitem21);
        Map<String, Object> gvitem22 = new HashMap<>();
        gvitem22.put("name", "读书");
        gvitem22.put("image", R.drawable.dushu);
        gvlistcaixi.add(gvitem22);
        Map<String, Object> gvitem23 = new HashMap<>();
        gvitem23.put("name", "闺怨");
        gvitem23.put("image", R.drawable.guiyuan);
        gvlistcaixi.add(gvitem23);
        Map<String, Object> gvitem24 = new HashMap<>();
        gvitem24.put("name", "哲理");
        gvitem24.put("image", R.drawable.zheli);
        gvlistcaixi.add(gvitem24);
        Map<String, Object> gvitem25 = new HashMap<>();
        gvitem25.put("name", "人物");
        gvitem25.put("image", R.drawable.renwu);
        gvlistcaixi.add(gvitem25);
        Map<String, Object> gvitem26 = new HashMap<>();
        gvitem26.put("name", "婉约");
        gvitem26.put("image", R.drawable.wanyue);
        gvlistcaixi.add(gvitem26);
        Map<String, Object> gvitem27 = new HashMap<>();
        gvitem27.put("name", "豪放");
        gvitem27.put("image", R.drawable.haofang);
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
        gvitem36.put("name", "春天");
        gvitem36.put("image", R.drawable.rumengling);
        gvlistweidao.add(gvitem36);
        Map<String, Object> gvitem37 = new HashMap<>();
        gvitem37.put("name", "夏天");
        gvitem37.put("image", R.drawable.zuihuayin);
        gvlistweidao.add(gvitem37);
        Map<String, Object> gvitem38 = new HashMap<>();
        gvitem38.put("name", "秋天");
        gvitem38.put("image", R.drawable.yumeiren);
        gvlistweidao.add(gvitem38);
        Map<String, Object> gvitem39 = new HashMap<>();
        gvitem39.put("name", "冬天");
        gvitem39.put("image", R.drawable.dingfengbo);
        gvlistweidao.add(gvitem39);
        Map<String, Object> gvitem40 = new HashMap<>();
        gvitem40.put("name", "雨景");
        gvitem40.put("image", R.drawable.yulinling);
        gvlistweidao.add(gvitem40);
        Map<String, Object> gvitem41 = new HashMap<>();
        gvitem41.put("name", "雪景");
        gvitem41.put("image", R.drawable.changxiangsi);
        gvlistweidao.add(gvitem41);
        Map<String, Object> gvitem42 = new HashMap<>();
        gvitem42.put("name", "风");
        gvitem42.put("image", R.drawable.niannujiao);
        gvlistweidao.add(gvitem42);
        Map<String, Object> gvitem43 = new HashMap<>();
        gvitem43.put("name", "荷花");
        gvitem43.put("image", R.drawable.liuzhougetou);
        gvlistweidao.add(gvitem43);
        Map<String, Object> gvitem44 = new HashMap<>();
        gvitem44.put("name", "梅花");
        gvitem44.put("image", R.drawable.yijiangnan);
        gvlistweidao.add(gvitem44);
        Map<String, Object> gvitem45 = new HashMap<>();
        gvitem45.put("name", "菊花");
        gvitem45.put("image", R.drawable.hechongtian);
        gvlistweidao.add(gvitem45);
        Map<String, Object> gvitem46 = new HashMap<>();
        gvitem46.put("name", "咏物");
        gvitem46.put("image", R.drawable.linjiangxian);
        gvlistweidao.add(gvitem46);
        //烹饪方法
        final List<Map<String, Object>> gvlistpengren = new ArrayList<>();
        Map<String, Object> gvitem47 = new HashMap<>();
        gvitem47.put("name", "小学");
        gvitem47.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem47);
        Map<String, Object> gvitem48 = new HashMap<>();
        gvitem48.put("name", "初中");
        gvitem48.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem48);
        Map<String, Object> gvitem49 = new HashMap<>();
        gvitem49.put("name", "高中");
        gvitem49.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem49);
        Map<String, Object> gvitem50 = new HashMap<>();
        gvitem50.put("name", "小学文言文");
        gvitem50.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem50);
        Map<String, Object> gvitem51 = new HashMap<>();
        gvitem51.put("name", "初中文言文");
        gvitem51.put("image", R.drawable.gushu);
        gvlistpengren.add(gvitem51);
        Map<String, Object> gvitem52 = new HashMap<>();
        gvitem52.put("name", "高中文言文");
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
        gvitem56.put("name", "山水");
        gvitem56.put("image", R.drawable.huanghelou);
        gvlistrenqun.add(gvitem56);
        Map<String, Object> gvitem57 = new HashMap<>();
        gvitem57.put("name", "山景");
        gvitem57.put("image", R.drawable.xihu);
        gvlistrenqun.add(gvitem57);
        Map<String, Object> gvitem58 = new HashMap<>();
        gvitem58.put("name", "水景");
        gvitem58.put("image", R.drawable.dongtinghu);
        gvlistrenqun.add(gvitem58);
        Map<String, Object> gvitem59 = new HashMap<>();
        gvitem59.put("name", "长江");
        gvitem59.put("image", R.drawable.taishan);
        gvlistrenqun.add(gvitem59);
        Map<String, Object> gvitem60 = new HashMap<>();
        gvitem60.put("name", "黄河");
        gvitem60.put("image", R.drawable.huanghe);
        gvlistrenqun.add(gvitem60);
        Map<String, Object> gvitem61 = new HashMap<>();
        gvitem61.put("name", "柳树");
        gvitem61.put("image", R.drawable.pingshantang);
        gvlistrenqun.add(gvitem61);
        Map<String, Object> gvitem62 = new HashMap<>();
        gvitem62.put("name", "月亮");
        gvitem62.put("image", R.drawable.changcheng);
        gvlistrenqun.add(gvitem62);
        Map<String, Object> gvitem63 = new HashMap<>();
        gvitem63.put("name", "地名");
        gvitem63.put("image", R.drawable.changjiang);
        gvlistrenqun.add(gvitem63);
        Map<String, Object> gvitem64 = new HashMap<>();
        gvitem64.put("name", "写鸟");
        gvitem64.put("image", R.drawable.changan);
        gvlistrenqun.add(gvitem64);
        Map<String, Object> gvitem65 = new HashMap<>();
        gvitem65.put("name", "写马");
        gvitem65.put("image", R.drawable.hanshansi);
        gvlistrenqun.add(gvitem65);
        Map<String, Object> gvitem66 = new HashMap<>();
        gvitem66.put("name", "儿童");
        gvitem66.put("image", R.drawable.lushan);
        gvlistrenqun.add(gvitem66);
        Map<String, Object> gvitem67 = new HashMap<>();
        gvitem67.put("name", "鹳雀楼");
        gvitem67.put("image", R.drawable.guanquelou);
        gvlistrenqun.add(gvitem67);

        final List<Map<String, Object>> gvlistshiji = new ArrayList<>();
        Map<String, Object> gvitem68 = new HashMap<>();
        gvitem68.put("name", "诗经");
        gvitem68.put("image", R.drawable.shijing);
        gvlistshiji.add(gvitem68);
        Map<String, Object> gvitem69 = new HashMap<>();
        gvitem69.put("name", "楚辞");
        gvitem69.put("image", R.drawable.chuci);
        gvlistshiji.add(gvitem69);
        Map<String, Object> gvitem70 = new HashMap<>();
        gvitem70.put("name", "宋词");
        gvitem70.put("image", R.drawable.daodejing);
        gvlistshiji.add(gvitem70);
        Map<String, Object> gvitem71 = new HashMap<>();
        gvitem71.put("name", "观止");
        gvitem71.put("image", R.drawable.qianjiashi);
        gvlistshiji.add(gvitem71);
        Map<String, Object> gvitem72 = new HashMap<>();
        gvitem72.put("name", "唐诗");
        gvitem72.put("image", R.drawable.tangshisanbaishou);
        gvlistshiji.add(gvitem72);
        Map<String, Object> gvitem73 = new HashMap<>();
        gvitem73.put("name", "十九");
        gvitem73.put("image", R.drawable.songcisanbaishou);
        gvlistshiji.add(gvitem73);
        Map<String, Object> gvitem74 = new HashMap<>();
        gvitem74.put("name", "民谣");
        gvitem74.put("image", R.drawable.yuanqusanbaishou);
        gvlistshiji.add(gvitem74);
        Map<String, Object> gvitem75 = new HashMap<>();
        gvitem75.put("name", "乐府");
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

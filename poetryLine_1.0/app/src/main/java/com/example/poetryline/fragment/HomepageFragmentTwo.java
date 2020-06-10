package com.example.poetryline.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetryline.LauarCalendar.Festivals;
import com.example.poetryline.LauarCalendar.Lauar;
import com.example.poetryline.LauarCalendar.SolarTermsFor24;
import com.example.poetryline.MainActivity;
import com.example.poetryline.R;
import com.example.poetryline.locationAndWeather.StreamTool;
import com.example.poetryline.signup.CountActivity;
import com.example.poetryline.signup.LoginActivity;
import com.mjzuo.location.GeocodingManager;
import com.mjzuo.location.ReverseGeocodingManager;
import com.mjzuo.location.bean.Latlng;
import com.mjzuo.location.constant.Constant;
import com.mjzuo.location.location.GoogleGeocoding;
import com.mjzuo.location.location.IGeocoding;
import com.mjzuo.location.regelocation.IReGe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static android.content.Context.MODE_PRIVATE;

public class HomepageFragmentTwo extends Fragment  implements EasyPermissions.PermissionCallbacks{
    //背景图
    private RelativeLayout tianshidili_beijing;

    //农历控件
    private TextView tv_lauar;
    private TextView tv_festivals;
    private TextView tv_solarTerms;

    private String nowSolarTerms = null;
    private String nextSolarTerms= null;
    private int leftDaySolarTerms= 0;

    //定位控件
    private static final String LOG_TAG = "tag_sl";

    private SharedPreferences userLocation;
    private SharedPreferences.Editor editor;
    private TextView tvSimpleAd;
    //    private TextView tvSimpleLo;
    private Button btn_location;
    private String city = "null";
    private String sublocality = "null";

    /** 所要申请的权限*/
    String[] perms = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE
    };

    /** 定位获取经纬度，包括LocationManager、基站地位*/
    GeocodingManager siLoManager;
    /** 反地理编码的manager，包括google反地理、高德反地理、百度反地理、腾讯反地理*/
    ReverseGeocodingManager reGeManager;


    //天气控件
    private TextView city_result1;
    private String weather;
    private int code =0;

    private int year ;
    private int month ;
    private int date ;
    private String yearString ;
    private String monthString;
    private  String dateString;
    private String mounthAndDateString;

    private TextView item_title;
    private TextView item_dyAndAuthor;
    private TextView item_context;


    private int LICHUN,YUSHUI,JINGZHE,CHUNFEN,QINGMING,GUYU,LIXIA,XIAOMAN,MANGZHONG,XIAZHI,XIAOSHU,DASHU,LIQIU,CHUSHU,BAILU,QIUFEN,HANLU,SHUANGJIANG,LIDONG,DAXUE,XIAOXUE,DONGZHI,XIAOHAN,DAHAN= 0;

    private int YinTian,YuTian,XueTian = 0;


//    //阴天
//    private String[] titlesYinTian = {
//            "诉衷情·柳腰空舞翠裙烟",
//            "即事",
//            "铁山祠成二首"
//    };
//    private String[] dyAndAuthorsYinTian = {
//            "[宋] 吴文英",
//            "[唐] 韦庄",
//            "[宋] 李新"
//    };
//    private String[] contextYinTian = {
//            "柳腰空舞翠裙烟。尽日不成眠。\n" +
//                    "花尘浪卷清昼，渐变晚阴天。\n" +
//                    "吴社水，系游船。又轻年。\n" +
//                    "东风不管，燕子初来，一夜春寒。",
//            "乱世时偏促，阴天日易昏。\n无言搔白首，憔悴倚东门。",
//            "缥缈团空翠一围，\n丛神社鼓日相依。\n" +
//                    "阴天几处看云阵，\n夜雪何时化铁衣。\n" +
//                    "鸟鼠莫栖新蕊殿，\n风尘从染旧龙旗。\n" +
//                    "门前汗马蹄翻玉，\n犹似当年力战归。"
//    };
//    //雨天
//    private String[] titlesYuTian  = {
//            "1",
//            "2"
//    };
//    private String[] dyAndAuthorsYuTian = {
//            "11",
//            "22"
//    };
//    private String[] contextYuTian = {
//            "a\nb\nc\nd",
//    };
//    //雪天
//    private String[] titlesXueTian = {
//            "1",
//            "2"
//    };
//    private String[] dyAndAuthorsXueTian = {
//            "11",
//            "22"
//    };
//    private String[] contextXueTian = {
//            "a\nb\nc\nd",
//    };



// --立春
    private String[] titlesLICHUN = {
            "奉和立春日侍宴内出剪彩花应制",
            "立春",
            "立春日晨起对积雪"
    };
    private String[] dyAndAuthorsLICHUN = {
            "唐：宋之问",
            "宋：王镃",
            "唐：张九龄"
    };
    private String[] contextLICHUN = {
            "金阁妆新杏，琼筵弄绮梅。\n" +
                    "\n" +
                    "人间都未识，天上忽先开。\n" +
                    "\n" +
                    "蝶绕香丝住，蜂怜艳粉回。\n" +
                    "\n" +
                    "今年春色早，应为剪刀催。",
            "泥牛鞭散六街尘，\n生菜挑来叶叶春。\n" +
                    "\n" +
                    "从此雪消风自软，\n梅花合让柳条新。",
            "忽对林亭雪，瑶华处处开。\n" +
                    "\n" +
                    "今年迎气始，昨夜伴春回。\n" +
                    "\n" +
                    "玉润窗前竹，花繁院里梅。\n" +
                    "\n" +
                    "东郊斋祭所，应见五神来。"
    };

      // --雨水
    private String[] titlesYUSHUI = {
            "春夜喜雨",
            "初春小雨"
    };
    private String[] dyAndAuthorsYUSHUI = {
            "杜甫",
            "韩愈"
    };
    private String[] contextYUSHUI = {
            "好雨知时节，当春乃发生。\n" +
                    "\n" +
                    "随风潜入夜，润物细无声。\n" +
                    "\n" +
                    "野径云俱黑，江船火独明。\n" +
                    "\n" +
                    "晓看红湿处，花重锦官城。",
            "天街小雨润如酥，草色遥看近却无。\n" +
                    "\n" +
                    "最是一年春好处，绝胜烟柳满皇都。"
    };
      // --惊蛰
    private String[] titlesJINGZHE = {
            "燕居十六首",
            "清溪松阴图"
    };
    private String[] dyAndAuthorsJINGZHE = {
            "[宋]彭龟年",
            "[明]唐寅"
    };
    private String[] contextJINGZHE = {
            "冬月不启壁，深虑惊蛰虫。\n" +
                    "\n" +
                    "物方全性命，如我爱吾宫。",
            "长松百尺荫清溪，\n倒影波间势转低。\n" +
                    "\n" +
                    "恰似春雷未惊蛰，\n髯龙头角暂皤泥。"
    };
      // 春分
    private String[] titlesCHUNFEN = {
            "春分",
            "七绝·春分"
    };
    private String[] dyAndAuthorsCHUNFEN = {
            "长卿",
            "左河水"
    };
    private String[] contextCHUNFEN = {
            "日月阳阴两均天，玄鸟不辞桃花寒。\n" +
                    "\n" +
                    "从来今日竖鸡子，川上良人放纸鸢。",
            "温风雷鼓入中春，\n柳絮桃枝日日新。\n" +
                    "\n" +
                    "赤道金阳一照面，\n白天黑夜两均分。"
    };
      // 清明
    private String[] titlesQINGMING = {
            "清明",
            "清明"
    };
    private String[] dyAndAuthorsQINGMING = {
            "杜牧",
            "王禹偁"
    };
    private String[] contextQINGMING = {
            "清明时节雨纷纷，\n路上行人欲断魂。\n" +
                    "\n" +
                    "借问酒家何处有，\n牧童遥指杏花村。",
            "无花无酒过清明，\n兴味萧然似野僧。\n" +
                    "\n" +
                    "昨日邻家乞新火，\n晓窗分与读书灯。"
    };
      // 谷雨
    private String[] titlesGUYU = {
            "谷雨",
            "芍药"
    };
    private String[] dyAndAuthorsGUYU = {
            "宋 朱槔",
            "王贞白[唐]"
    };
    private String[] contextGUYU = {
            "天点纷林际,虚檐写梦中。\n明朝知谷雨,无策禁花风。\n石渚收机巧,烟蓑建事功。\n越禽牢闭口,吾道寄天公。",
            "芍药承春宠，何曾羡牡丹。\n" +
                    "\n" +
                    "麦秋能几日，谷雨只微寒。\n" +
                    "\n" +
                    "妒态风频起，娇妆露欲残。\n" +
                    "\n" +
                    "芙蓉浣纱伴，长恨隔波澜。"
    };
      // 立夏
    private String[] titlesLIXIA = {
            "立夏",
            "立夏",
              "立夏"
    };
    private String[] dyAndAuthorsLIXIA = {
            "左河水",
            "长卿",
            "陆游"
    };
    private String[] contextLIXIA = {
            "南国似暑北国春，\n绿秀江淮万木荫。\n" +
                    "\n" +
                    "时病时虫人撒药，\n忽寒忽热药搪人。",
            "南疆日长北国春，\n蝼蛄聒噪王瓜茵。\n" +
                    "\n" +
                    "新尝九荤十三素，\n谁家村西不称人。",
            "赤帜插城扉，东君整驾归。\n" +
                    "\n" +
                    "泥新巢燕闹，花尽蜜蜂稀。\n" +
                    "\n" +
                    "槐柳阴初密，帘栊暑尚微。\n" +
                    "\n" +
                    "日斜汤沐罢，熟练试单衣。"
    };
      // 小满
    private String[] titlesXIAOMAN = {
            "五绝·小满",
            "遣兴"
    };
    private String[] dyAndAuthorsXIAOMAN = {
            "宋·欧阳修",
            "宋·王之道"
    };
    private String[] contextXIAOMAN = {
            "夜莺啼绿柳，皓月醒长空。\n" +
                    "\n" +
                    "最爱垄头麦，迎风笑落红。",
            "步屧随儿辈，临池得凭栏。\n久阴东虹断，小满北风寒。\n" +
                    "\n" +
                    "点水荷三叠，依墙竹数竿。\n乍晴何所喜，云际远山攒。"
    };
      // 芒种
    private String[] titlesMANGZHONG = {
            "北固晚眺",
            "龙华山寺寓居十首",
              "梅雨五绝",
              "耕图二十一首·拔秧"
    };
    private String[] dyAndAuthorsMANGZHONG = {
            "唐 窦常",
            "宋 王之望",
            "宋·范成大",
            "宋·楼璹"
    };
    private String[] contextMANGZHONG = {
            "水国芒种后,梅天风雨凉。\n" +
                    "\n" +
                    "露蚕开晚簇,江燕绕危樯。\n" +
                    "\n" +
                    "山趾北来固,潮头西去长。\n" +
                    "\n" +
                    "年年此登眺,人事几销亡。",
            "水乡经月雨,潮海暮春天。\n" +
                    "\n" +
                    "芒种嗟无日,来牟失有年。\n" +
                    "\n" +
                    "人多蓬菜色,村或断炊烟。\n" +
                    "\n" +
                    "谁谓山中乐,忧来百虑煎。",
            "乙酉甲申雷雨惊,\n" +
                    "\n" +
                    "乘除却贺芒种晴。\n" +
                    "\n" +
                    "插秧先插蚤籼稻,\n" +
                    "\n" +
                    "少忍数旬蒸米成。",
            "新秧初出水,渺渺翠毯齐。\n" +
                    "\n" +
                    "清晨且拔擢,父子争提携。\n" +
                    "\n" +
                    "既沐青满握,再栉根无泥。\n" +
                    "\n" +
                    "及时趁芒种,散着畦东西。"
    };
      // 夏至
    private String[] titlesXIAZHI = {
            "夏至日作",
            "夏至"
    };
    private String[] dyAndAuthorsXIAZHI = {
            "唐·权德舆",
            "(宋)范成大"
    };
    private String[] contextXIAZHI = {
            "璿枢无停运，四序相错行。\n" +
                    "\n" +
                    "寄言赫曦景，今日一阴生。",
            "石鼎声中朝暮，\n" +
                    "\n" +
                    "纸窗影下寒温。\n" +
                    "\n" +
                    "踰年不与庙祭，\n" +
                    "\n" +
                    "敢云孝子慈孙。\n" +
                    "\n"
    };
      // 小暑
    private String[] titlesXIAOSHU = {
            "小暑六月节",
            "秋夜宿重本上人院"
    };
    private String[] dyAndAuthorsXIAOSHU = {
            "唐·元稹",
            "唐·李频"
    };
    private String[] contextXIAOSHU = {
            "倏忽温风至，因循小暑来。\n" +
                    "\n" +
                    "竹喧先觉雨，山暗已闻雷。\n" +
                    "\n" +
                    "户牖深青霭，阶庭长绿苔。\n" +
                    "\n" +
                    "鹰鹯新习学，蟋蟀莫相催。",
            "却忆凉堂坐，明河几度流。\n" +
                    "\n" +
                    "安禅逢小暑，抱疾入高秋。\n" +
                    "\n" +
                    "水国曾重讲，云林半旧游。\n" +
                    "\n" +
                    "此来看月落，还似道相求。"
    };
      // 大暑
    private String[] titlesDASHU = {
            "销夏",
            "大暑水阁听晋卿家昭华吹笛"
    };
    private String[] dyAndAuthorsDASHU = {
            "唐·白居易",
            "北宋·黄庭坚"
    };
    private String[] contextDASHU = {
            "何以销烦暑，端居一院中。\n" +
                    "\n" +
                    "眼前无长物，窗下有清风。\n" +
                    "\n" +
                    "热散由心静，凉生为室空。\n" +
                    "\n" +
                    "此时身自得，难更与人同。",
            "蕲竹能吟水底龙，\n玉人应在月明中。\n" +
                    "\n" +
                    "何时为洗秋空热，\n散作霜天落叶风。"
    };
      // 立秋
    private String[] titlesLIQIU = {
            "立秋",
            "立秋"
    };
    private String[] dyAndAuthorsLIQIU = {
            "[宋]刘翰",
            "左河水"
    };
    private String[] contextLIQIU = {
            "乳鸦啼散玉屏空，\n一枕新凉一扇风。\n" +
                    "\n" +
                    "睡起秋声无觅处，\n满阶梧桐月明中。",
            "一叶梧桐一报秋，\n稻花田里话丰收。\n" +
                    "\n" +
                    "虽非盛夏还伏虎，\n更有寒蝉唱不休。"
    };
      // 处暑
    private String[] titlesCHUSHU = {
            "闲适",
            "悯农"
    };
    private String[] dyAndAuthorsCHUSHU = {
            "宋•陆游",
            "唐•李绅"
    };
    private String[] contextCHUSHU = {
            "四时俱可喜，最好新秋时，\n" +
                    "\n" +
                    "柴门傍野水，邻叟闲相期。",
            "春种一粒粟，秋收万颗子。\n" +
                    "\n" +
                    "四海无闲田，农夫犹饿死。"
    };
      // 白露
    private String[] titlesBAILU = {
            "白露",
            "秋露"
    };
    private String[] dyAndAuthorsBAILU = {
            "唐：杜甫",
            "唐·雍陶"
    };
    private String[] contextBAILU = {
            "白露团甘子，清晨散马蹄。\n" +
                    "\n" +
                    "圃开连石树，船渡入江溪。\n" +
                    "\n" +
                    "凭几看鱼乐，回鞭急鸟栖。\n" +
                    "\n" +
                    "渐知秋实美，幽径恐多蹊。",
            "白露暧秋色，月明清漏中。\n" +
                    "\n" +
                    "痕沾珠箔重，点落玉盘空。\n" +
                    "\n" +
                    "竹动时惊鸟，莎寒暗滴虫。\n" +
                    "\n" +
                    "满园生永夜，渐欲与霜同。"
    };
      // 秋分
    private String[] titlesQIUFEN = {
            "中秋对月",
            "秋分"
    };
    private String[] dyAndAuthorsQIUFEN = {
            "（唐）李频",
            "左河水"
    };
    private String[] contextQIUFEN = {
            "秋分一夜停，阴魄最晶荧。\n" +
                    "\n" +
                    "好是生沧海，徐看历杳冥。\n" +
                    "\n" +
                    "层空疑洗色，万怪想潜形。\n" +
                    "\n" +
                    "他夕无相类，晨鸡不可听。",
            "暑退秋澄气转凉，日光夜色两均长。\n" +
                    "\n" +
                    "银棉金稻千重秀，丹桂小菊万径香。"
    };
      // 寒露
    private String[] titlesHANLU = {
            "三生石",
            "木芙蓉"
    };
    private String[] dyAndAuthorsHANLU = {
            "修睦",
            "韩愈"
    };
    private String[] contextHANLU = {
            "圣迹谁会得，每到亦徘徊。\n一尚不可得，三从何处来。\n" +
                    "清宵寒露滴，白昼野云隈。\n应是表灵异，凡情安可猜。",
            "新开寒露丛，远比水间红。艳色宁相妒，嘉名偶自同。\n" +
                    "采江官渡晚，搴木古祠空。愿得勤来看，无令便逐风。"
    };
     // 霜降
    private String[] titlesSHUANGJIANG  = {
            "霜降",
            "泊舟盱眙"
    };
    private String[] dyAndAuthorsSHUANGJIANG  = {
            "左河水",
            "韦建"
    };
    private String[] contextSHUANGJIANG  = {
            "时逢秋暮露成霜，\n几份凝结几份阳。\n" +
                    "\n" +
                    "荷败千池萧瑟岸，\n棉白万顷采收忙。",
            "泊舟淮水次，霜降夕流清。\n" +
                    "\n" +
                    "夜久潮侵岸，天寒月近城。\n" +
                    "\n" +
                    "平沙依雁宿，候馆听鸡鸣。\n" +
                    "\n" +
                    "乡国云霄外，谁堪羁旅情。"
    };
    // 立冬
    private String[] titlesLIDONG = {
            "立冬",
            "立冬即事二首"
    };
    private String[] dyAndAuthorsLIDONG = {
            "左河水",
            "宋·仇远"
    };
    private String[] contextLIDONG = {
            "北风往复几寒凉，\n疏木摇空半绿黄。\n" +
                    "\n" +
                    "四野修堤防旱涝，\n万家晒物备收藏。",
            "细雨生寒未有霜，庭前木叶半青黄。\n" +
                    "\n" +
                    "小春此去无多日，何处梅花一绽香。"
    };
    // 小雪
    private String[] titlesXIAOXUE = {
            "小雪",
            "小雪"
    };
    private String[] dyAndAuthorsXIAOXUE = {
            "左河水",
            "戴叔伦"
    };
    private String[] contextXIAOXUE = {
            "太行初雪带寒风，\n一路凋零下赣中。\n" +
                    "\n" +
                    "菊萎东篱梅暗动，\n方知大地转阳升。",
            "花雪随风不厌看，\n更多还肯失林峦。\n" +
                    "\n" +
                    "愁人正在书窗下，\n一片飞来一片寒。"
    };
    // 大雪
    private String[] titlesDAXUE  = {
            "江雪",
            "夜雪"
    };
    private String[] dyAndAuthorsDAXUE  = {
            "柳宗元",
            "白居易"
    };
    private String[] contextDAXUE  = {
            "千山鸟飞绝，\n" +
                    "\n" +
                    "万径人踪灭。\n" +
                    "\n" +
                    "孤舟蓑笠翁，\n" +
                    "\n" +
                    "独钓寒江雪。",
            "已讶衾枕冷，复见窗户明。\n" +
                    "\n" +
                    "夜深知雪重，时闻折竹声。"
    };
    // 冬至
    private String[] titlesDONGZHI = {
            "邯郸冬至夜思家",
            "冬至"
    };
    private String[] dyAndAuthorsDONGZHI = {
            "白居易",
            "贵谷子"
    };
    private String[] contextDONGZHI = {
            "邯郸驿里逢冬至，\n" +
                    "\n" +
                    "抱膝灯前影伴身。\n" +
                    "\n" +
                    "想得家中夜深坐，\n" +
                    "\n" +
                    "还应说着远行人。",
            "日照数九冬至天，\n" +
                    "\n" +
                    "清霜风高未辞岁。\n" +
                    "\n" +
                    "又是一个平衡日，\n" +
                    "\n" +
                    "子线从南向北回。"
                };
     // 小寒
    private String[] titlesXIAOHAN = {
            "小寒",
            "窗前木芙蓉"
    };
    private String[] dyAndAuthorsXIAOHAN = {
            "元稹",
            "范成大"
    };
    private String[] contextXIAOHAN = {
            "小寒连大吕，欢鹊垒新巢。\n" +
                    "\n" +
                    "拾食寻河曲，衔紫绕树梢。\n" +
                    "\n" +
                    "霜鹰近北首，雊雉隐丛茅。\n" +
                    "\n" +
                    "莫怪严凝切，春冬正月交。",
            "辛苦孤花破小寒，\n" +
                    "\n" +
                    "花心应似客心酸。\n" +
                    "\n" +
                    "更凭青女留连得，\n" +
                    "\n" +
                    "未作愁红怨绿看。"
    };
    // 大寒
    private String[] titlesDAHAN = {
            "大寒吟",
            "苦寒吟"
    };
    private String[] dyAndAuthorsDAHAN = {
            "宋·邵雍",
            "【唐代】孟郊"
    };
    private String[] contextDAHAN = {
            "旧雪未及消，新雪又拥户。\n阶前冻银床，檐头冰钟乳。\n" +

                    "清日无光辉，烈风正号怒。\n人口各有舌，言语不能吐。",
            "天寒色青苍，北风叫枯桑。\n" +
                    "\n" +
                    "厚冰无裂文，短日有冷光。\n" +
                    "\n" +
                    "敲石不得火，壮阴正夺阳。\n" +
                    "\n" +
                    "调苦竟何言，冻吟成此章。"
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_two_fragment,container,false);


        //背景图设置
        tianshidili_beijing= view.findViewById(R.id.tianshidili_beijing);
//        Resources resources = getContext().getResources();
//        Drawable btnDrawable = resources.getDrawable(R.drawable.tianshidili_mangzhong);
//        tianshidili_beijing.setBackgroundDrawable(btnDrawable);


        tvSimpleAd = view.findViewById(R.id.tv_simple_address_txt);


        userLocation = getActivity().getSharedPreferences("userLocation",MODE_PRIVATE);
        city = userLocation.getString("City","null");
        sublocality =userLocation.getString("Sublocality","null");
        if (city.equals("null")){
            tvSimpleAd.setText("请点击定位按钮获取地址");
        }else if(sublocality.equals("null")){
            tvSimpleAd.setText("当前位置："+city);
            findWeather(city);
        }else {
            tvSimpleAd.setText("当前位置："+city+" "+sublocality);
            findWeather(sublocality);
        }


        city_result1=(TextView) view.findViewById(R.id.city_result1);

        btn_location=view.findViewById(R.id.btn_location);

        //农历控件
        tv_lauar= view.findViewById(R.id.tv_lauar);
        tv_festivals = view.findViewById(R.id.tv_festivals);
        tv_solarTerms = view.findViewById(R.id.tv_solarTerms);
        //获取日期
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        date = c.get(Calendar.DATE);
        yearString = year+"";

//        System.out.println(year + "/" + month + "/" + date);

        if (month<10) {
            monthString="0"+month;
        }else {
            monthString = ""+month;
        }

        dateString= ""+date;

        mounthAndDateString = monthString+dateString;
//        Lauar lauar = new Lauar();
        Festivals festivals = new Festivals();
//        SolarTermsFor24 solarTermsFor24 = new SolarTermsFor24();
        tv_lauar.setText(Lauar.getLunar(yearString,monthString,dateString));

        String nowFestival = festivals.getLunarDate(year,month,date,false);
        if (nowSolarTerms==null){
            tv_festivals.setText("今日节日：无 "+ findFestivalAndDay(year,month,date));
        }else {
            tv_festivals.setText("今日节日："+nowFestival);
        }

        nowSolarTerms = SolarTermsFor24.getSolatName(year,mounthAndDateString);
        if (nowSolarTerms==null){
//            findSolarTerms();
            tv_solarTerms.setText("今日节气：无 "+findSolarTermsAndDay(year,month,date));
        }else {
            tv_solarTerms.setText("今日节气："+nowSolarTerms);
        }


        //获取定位
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!EasyPermissions.hasPermissions(getContext(), perms)) {
                    EasyPermissions.requestPermissions(HomepageFragmentTwo.this
                            , "我们需要位置权限以便获得您的大概位置"
                            , 0
                            , perms);
                }

                ReverseGeocodingManager.ReGeOption reGeOption = new ReverseGeocodingManager.ReGeOption()
                        .setReGeType(Constant.BAIDU_API)// 百度api返地理编码
                        .setSn(true)// sn 签名校验方式
                        .setIslog(true);// 打印log
                reGeManager = new ReverseGeocodingManager(getContext(), reGeOption);
                reGeManager.addReGeListener(new IReGe.IReGeListener() {
                    @Override
                    public void onSuccess(int state, Latlng latlng) {
                        if(latlng.getSublocality().length()!=0){
                            tvSimpleAd.setText("当前位置："+latlng.getCity()+" "+latlng.getSublocality());
                            city= latlng.getCity();
                            sublocality = latlng.getSublocality();
                            findWeather(sublocality);
                            editor = getActivity().getSharedPreferences("userLocation",MODE_PRIVATE).edit();
                            editor.putString("City",city);
                            editor.putString("Sublocality",sublocality);
                            editor.apply();

                        }else {
                            tvSimpleAd.setText("当前位置："+latlng.getCity());
                            city= latlng.getCity();
                            findWeather(city);
                            editor = getActivity().getSharedPreferences("useLocation",MODE_PRIVATE).edit();
                            editor.putString("City",city);
                            editor.apply();

                        }

                    }

                    @Override
                    public void onFail(int errorCode, String error) {
                        Log.e(LOG_TAG,"error:" + error);
                        tvSimpleAd.setText("errorCode:"+errorCode+", error:" + error);
                    }
                });


                GeocodingManager.GeoOption option = new GeocodingManager.GeoOption()
                        .setGeoType(Constant.LM_API) // 使用openCellid服务器的基站地位
                        .setOption(new GoogleGeocoding.SiLoOption()
                                .setGpsFirst(false));// locationManager定位方式时，网络定位优先(速度更快)
                siLoManager = new GeocodingManager(getContext(), option);
                siLoManager.start(new IGeocoding.ISiLoResponseListener() {
                    @Override
                    public void onSuccess(Latlng latlng) {
                        Log.e(LOG_TAG,"siLoManager onSuccess:" + latlng);
//                        tvSimpleLo.setText("latlng:" + latlng.getLatitude()
//                                + "\n,long:" + latlng.getLongitude()
//                                + "\n,provider:" + latlng.getProvider());
                        reGeManager.reGeToAddress(latlng);
                    }

                    @Override
                    public void onFail(String msg) {
                        Log.e(LOG_TAG,"error:" + msg);
                        Toast.makeText(getContext(),"请打开定位和网络，否则无法获取大概位置",Toast.LENGTH_SHORT).show();
//                        tvSimpleAd.setText("error:" + msg);
                        tvSimpleAd.setText("没有打开定位或者网络");
                    }
                });
            }
        });

        //推荐诗获取控件
        //推荐诗控件
        TextView changePoem = view.findViewById(R.id.changePoem);
        item_title = view.findViewById(R.id.item_title);
        item_dyAndAuthor = view.findViewById(R.id.item_dyAndAuthor);
        item_context = view.findViewById(R.id.item_context);

        AssetManager assetManager = getActivity().getAssets();
        Typeface type_one = Typeface.createFromAsset(assetManager,"fonts/xingkai.ttf");
        Typeface type_two = Typeface.createFromAsset(assetManager,"fonts/xihei.ttf");
        Typeface type_three = Typeface.createFromAsset(assetManager,"fonts/yishuhei.ttf");
        item_title.setTypeface(type_three);
        item_dyAndAuthor.setTypeface(type_two);
        item_context.setTypeface(type_one);

        //设置诗
        setPoem();

        changePoem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPoem();
//                Toast.makeText(getContext(),"setPoem",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private String findFestivalAndDay(int year,int month ,int day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sdf.parse(year+"-"+month+"-"+day);
            int year2 = year+1;//12月之后 跨年
            int month2 = month+1;//12月之前 加1月
            int day2 = day-3;//如果是31号 而二月可能只有28  所以-3
            Date end;
            if (month<12){
               end = sdf.parse(year+"-"+month2+"-"+day2);
            }else {
                end = sdf.parse(year2+"-"+month+"-"+day2);
            }
            List<Date> lists = dateSplit(start, end);
            int i = 0;
            if (!lists.isEmpty()) {
                for (Date date : lists) {
                    String string = sdf.format(date);
                    String[] strs=string.split("-");
                    for(int b=0,len=strs.length;b<len;b++){
                        if(strs[b].length()<4) {
                            Festivals festivals = new Festivals();
                            String festivalString = festivals.getLunarDate(year,Integer.parseInt(strs[b]),Integer.parseInt(strs[b+1]),false);
                            b++;
                            if (festivalString==null){
                                i++;
                            }else {
                                return "距离"+festivalString +"还有"+ i +"天";
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            return "";
        }
        return "";

    }

    private String findSolarTermsAndDay(int year,int month ,int day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sdf.parse(year+"-"+month+"-"+day);
            int year2 = year+1;//12月之后 跨年
            int month2 = month+1;//12月之前 加1月
            int day2 = day-3;//如果是31号 而二月可能只有28  所以-3
            Date end;
            if (month<12){
                end = sdf.parse(year+"-"+month2+"-"+day2);
            }else {
                end = sdf.parse(year2+"-"+month+"-"+day2);
            }
            List<Date> lists = dateSplit(start, end);
            int i = 0;
            if (!lists.isEmpty()) {
                for (Date date : lists) {
                    String string = sdf.format(date);
                    String[]  strs=string.split("-");
                    for(int b=0,len=strs.length;b<len;b++){
                        if(strs[b].length()<4) {
                            String string2 = strs[b+1];
                            if (strs[b+1].contains("0")&&!strs[b+1].equals("10")&&!strs[b+1].equals("20")&&!strs[b+1].equals("30")) {
                                string2=strs[b+1].substring(1);
                            }
                            String solarTermsFor24String = SolarTermsFor24.getSolatName(year,strs[b]+string2);
                            b++;
                            if (solarTermsFor24String==null){
                                i++;
                            }else {
                                nextSolarTerms = solarTermsFor24String;
                                leftDaySolarTerms = i;
                                return "距离"+solarTermsFor24String +"还有"+ i +"天";
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            return "";
        }
        return "";
    }
    private static List<Date> dateSplit(Date startDate, Date endDate)
            throws Exception {
        if (!startDate.before(endDate))
            throw new Exception("开始时间应该在结束时间之后");
        Long spi = endDate.getTime() - startDate.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

        List<Date> dateList = new ArrayList<Date>();
        dateList.add(endDate);
        for (int i = 1; i <= step; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime()
                    - (24 * 60 * 60 * 1000)));// 比上一天减一
        }
        Collections.reverse(dateList);
        return dateList;
    }

    private void setPoem() {
//        Toast.makeText(getContext(),nowSolarTerms+nextSolarTerms+leftDaySolarTerms,Toast.LENGTH_SHORT).show();
        if(nowSolarTerms=="立春"||nextSolarTerms=="立春"){
            item_title.setText(titlesLICHUN[LICHUN]);
            item_dyAndAuthor.setText(dyAndAuthorsLICHUN[LICHUN]);
            item_context.setText(contextLICHUN[LICHUN]);
            if (LICHUN<titlesLICHUN.length-1){
                LICHUN++;
            }else {
                LICHUN=0;
            }
        }else
        if(nowSolarTerms=="雨水"||nextSolarTerms=="雨水"){
            item_title.setText(titlesYUSHUI[YUSHUI]);
            item_dyAndAuthor.setText(dyAndAuthorsYUSHUI[YUSHUI]);
            item_context.setText(contextYUSHUI[YUSHUI]);
            if (YUSHUI<titlesYUSHUI.length-1){
                YUSHUI++;
            }else {
                YUSHUI=0;
            }
        }else
        if(nowSolarTerms=="惊蛰"||nextSolarTerms=="惊蛰"){
            item_title.setText(titlesJINGZHE[JINGZHE]);
            item_dyAndAuthor.setText(dyAndAuthorsJINGZHE[JINGZHE]);
            item_context.setText(contextJINGZHE[JINGZHE]);
            if (JINGZHE<titlesJINGZHE.length-1){
                JINGZHE++;
            }else {
                JINGZHE=0;
            }
        }else
        if(nowSolarTerms=="春分"||nextSolarTerms=="春分"){
            item_title.setText(titlesCHUNFEN[CHUNFEN]);
            item_dyAndAuthor.setText(dyAndAuthorsCHUNFEN[CHUNFEN]);
            item_context.setText(contextCHUNFEN[CHUNFEN]);
            if (CHUNFEN<titlesCHUNFEN.length-1){
                CHUNFEN++;
            }else {
                CHUNFEN=0;
            }
        }else
        if(nowSolarTerms=="清明"||nextSolarTerms=="清明"){
            item_title.setText(titlesQINGMING[QINGMING]);
            item_dyAndAuthor.setText(dyAndAuthorsQINGMING[QINGMING]);
            item_context.setText(contextQINGMING[QINGMING]);
            if (QINGMING<titlesQINGMING.length-1){
                QINGMING++;
            }else {
                QINGMING=0;
            }
        }else
        if(nowSolarTerms=="谷雨"||nextSolarTerms=="谷雨"){
            item_title.setText(titlesGUYU[GUYU]);
            item_dyAndAuthor.setText(dyAndAuthorsGUYU[GUYU]);
            item_context.setText(contextGUYU[GUYU]);
            if (GUYU<titlesGUYU.length-1){
                GUYU++;
            }else {
                GUYU=0;
            }
        }else
        if(nowSolarTerms=="立夏"||nextSolarTerms=="立夏"){
            item_title.setText(titlesLIXIA[LIXIA]);
            item_dyAndAuthor.setText(dyAndAuthorsLIXIA[LIXIA]);
            item_context.setText(contextLIXIA[LIXIA]);
            if (LIXIA<titlesLIXIA.length-1){
                LIXIA++;
            }else {
                LIXIA=0;
            }
        }else
        if(nowSolarTerms=="小满"||nextSolarTerms=="小满"){
            item_title.setText(titlesXIAOMAN[XIAOMAN]);
            item_dyAndAuthor.setText(dyAndAuthorsXIAOMAN[XIAOMAN]);
            item_context.setText(contextXIAOMAN[XIAOMAN]);
            if (XIAOMAN<titlesXIAOMAN.length-1){
                XIAOMAN++;
            }else {
                XIAOMAN=0;
            }
        }else
        if(nowSolarTerms=="芒种"||nextSolarTerms=="芒种"){
            item_title.setText(titlesMANGZHONG[MANGZHONG]);
            item_dyAndAuthor.setText(dyAndAuthorsMANGZHONG[MANGZHONG]);
            item_context.setText(contextMANGZHONG[MANGZHONG]);
            Resources resources = getContext().getResources();
            Drawable btnDrawable = resources.getDrawable(R.drawable.tianshidili_mangzhong);
            tianshidili_beijing.setBackgroundDrawable(btnDrawable);
            if (MANGZHONG<titlesMANGZHONG.length-1){
                MANGZHONG++;
            }else {
                Drawable btnDrawable2 = resources.getDrawable(R.drawable.tianshidili_mangzhong2);
                tianshidili_beijing.setBackgroundDrawable(btnDrawable2);
                MANGZHONG=0;
            }
        }else
        if(nowSolarTerms=="夏至"||nextSolarTerms=="夏至"){
            item_title.setText(titlesXIAZHI[XIAZHI]);
            item_dyAndAuthor.setText(dyAndAuthorsXIAZHI[XIAZHI]);
            item_context.setText(contextXIAZHI[XIAZHI]);
            if (XIAZHI<titlesXIAZHI.length-1){
                XIAZHI++;
            }else {
                XIAZHI=0;
            }
        }else
        if(nowSolarTerms=="小暑"||nextSolarTerms=="小暑"){
            item_title.setText(titlesXIAOSHU[XIAOSHU]);
            item_dyAndAuthor.setText(dyAndAuthorsXIAOSHU[XIAOSHU]);
            item_context.setText(contextXIAOSHU[XIAOSHU]);
            if (XIAOSHU<titlesXIAOSHU.length-1){
                XIAOSHU++;
            }else {
                XIAOSHU=0;
            }
        }else
        if(nowSolarTerms=="大暑"||nextSolarTerms=="大暑"){
            item_title.setText(titlesDASHU[DASHU]);
            item_dyAndAuthor.setText(dyAndAuthorsDASHU[DASHU]);
            item_context.setText(contextDASHU[DASHU]);
            if (DASHU<titlesDASHU.length-1){
                DASHU++;
            }else {
                DASHU=0;
            }
        }else
        if(nowSolarTerms=="立秋"||nextSolarTerms=="立秋"){
            item_title.setText(titlesLIQIU[LIQIU]);
            item_dyAndAuthor.setText(dyAndAuthorsLIQIU[LIQIU]);
            item_context.setText(contextLIQIU[LIQIU]);
            if (LIQIU<titlesLIQIU.length-1){
                LIQIU++;
            }else {
                LIQIU=0;
            }
        }else
        if(nowSolarTerms=="处暑"||nextSolarTerms=="处暑"){
            item_title.setText(titlesCHUSHU[CHUSHU]);
            item_dyAndAuthor.setText(dyAndAuthorsCHUSHU[CHUSHU]);
            item_context.setText(contextCHUSHU[CHUSHU]);
            if (CHUSHU<titlesCHUSHU.length-1){
                CHUSHU++;
            }else {
                CHUSHU=0;
            }
        }else
        if(nowSolarTerms=="白露"||nextSolarTerms=="白露"){
            item_title.setText(titlesBAILU[BAILU]);
            item_dyAndAuthor.setText(dyAndAuthorsBAILU[BAILU]);
            item_context.setText(contextBAILU[BAILU]);
            if (BAILU<titlesBAILU.length-1){
                BAILU++;
            }else {
                BAILU=0;
            }
        }else
        if(nowSolarTerms=="秋分"||nextSolarTerms=="秋分"){
            item_title.setText(titlesQIUFEN[QIUFEN]);
            item_dyAndAuthor.setText(dyAndAuthorsQIUFEN[QIUFEN]);
            item_context.setText(contextQIUFEN[QIUFEN]);
            if (QIUFEN<titlesQIUFEN.length-1){
                QIUFEN++;
            }else {
                QIUFEN=0;
            }
        }else
        if(nowSolarTerms=="寒露"||nextSolarTerms=="寒露"){
            item_title.setText(titlesHANLU[HANLU]);
            item_dyAndAuthor.setText(dyAndAuthorsHANLU[HANLU]);
            item_context.setText(contextHANLU[HANLU]);
            if (HANLU<titlesHANLU.length-1){
                HANLU++;
            }else {
                HANLU=0;
            }
        }else
        if(nowSolarTerms=="霜降"||nextSolarTerms=="霜降"){
            item_title.setText(titlesSHUANGJIANG[SHUANGJIANG]);
            item_dyAndAuthor.setText(dyAndAuthorsSHUANGJIANG[SHUANGJIANG]);
            item_context.setText(contextSHUANGJIANG[SHUANGJIANG]);
            if (SHUANGJIANG<titlesSHUANGJIANG.length-1){
                SHUANGJIANG++;
            }else {
                SHUANGJIANG=0;
            }
        }else
        if(nowSolarTerms=="立冬"||nextSolarTerms=="立冬"){
            item_title.setText(titlesLIDONG[LIDONG]);
            item_dyAndAuthor.setText(dyAndAuthorsLIDONG[LIDONG]);
            item_context.setText(contextLIDONG[LIDONG]);
            if (LIDONG<titlesLIDONG.length-1){
                LIDONG++;
            }else {
                LIDONG=0;
            }
        }else
        if(nowSolarTerms=="小雪"||nextSolarTerms=="小雪"){
            item_title.setText(titlesXIAOXUE[XIAOXUE]);
            item_dyAndAuthor.setText(dyAndAuthorsXIAOXUE[XIAOXUE]);
            item_context.setText(contextXIAOXUE[XIAOXUE]);
            if (XIAOXUE<titlesXIAOXUE.length-1){
                XIAOXUE++;
            }else {
                XIAOXUE=0;
            }
        }else
        if(nowSolarTerms=="大雪"||nextSolarTerms=="大雪"){
            item_title.setText(titlesDAXUE[DAXUE]);
            item_dyAndAuthor.setText(dyAndAuthorsDAXUE[DAXUE]);
            item_context.setText(contextDAXUE[DAXUE]);
            if (DAXUE<titlesDAXUE.length-1){
                DAXUE++;
            }else {
                DAXUE=0;
            }
        }else
        if(nowSolarTerms=="冬至"||nextSolarTerms=="冬至"){
            item_title.setText(titlesDONGZHI[DONGZHI]);
            item_dyAndAuthor.setText(dyAndAuthorsDONGZHI[DONGZHI]);
            item_context.setText(contextDONGZHI[DONGZHI]);
            if (DONGZHI<titlesDONGZHI.length-1){
                DONGZHI++;
            }else {
                DONGZHI=0;
            }
        }else
        if(nowSolarTerms=="小寒"||nextSolarTerms=="小寒"){
            item_title.setText(titlesXIAOHAN[XIAOHAN]);
            item_dyAndAuthor.setText(dyAndAuthorsXIAOHAN[XIAOHAN]);
            item_context.setText(contextXIAOHAN[XIAOHAN]);
            if (XIAOHAN<titlesXIAOHAN.length-1){
                XIAOHAN++;
            }else {
                XIAOHAN=0;
            }
        }else
        if(nowSolarTerms=="大寒"||nextSolarTerms=="大寒"){
            item_title.setText(titlesDAHAN[DAHAN]);
            item_dyAndAuthor.setText(dyAndAuthorsDAHAN[DAHAN]);
            item_context.setText(contextDAHAN[DAHAN]);
            if (DAHAN<titlesDAHAN.length-1){
                DAHAN++;
            }else {
                DAHAN=0;
            }
        }
        //天气
//        else if(weather=="阴"){
//            item_title.setText(titlesYinTian[YinTian]);
//            item_dyAndAuthor.setText(dyAndAuthorsYinTian[YinTian]);
//            item_context.setText(contextYinTian[YinTian]);
//            if (YinTian<titlesYinTian.length-1){
//                YinTian++;
//            }else {
//                YinTian=0;
//            }
//        }
//        else if(weather.contains("雨")){
//            item_title.setText(titlesYuTian[YuTian]);
//            item_dyAndAuthor.setText(dyAndAuthorsYuTian[YuTian]);
//            item_context.setText(contextYuTian[YuTian]);
//            if (YuTian<titlesYuTian.length-1){
//                YuTian++;
//            }else {
//                YuTian=0;
//            }
//        }
//        else if(weather.contains("雪")){
//            item_title.setText(titlesXueTian[XueTian]);
//            item_dyAndAuthor.setText(dyAndAuthorsXueTian[XueTian]);
//            item_context.setText(contextXueTian[XueTian]);
//            if (XueTian<titlesXueTian.length-1){
//                XueTian++;
//            }else {
//                XueTian=0;
//            }
//        }
        else {
            item_title.setText("藏头诗");
            item_dyAndAuthor.setText("未知");
            item_context.setText("你登竹楼听春雨，"+"\n"+"若有所思望西都，"+"\n"+"安能拨得云烟开，"+"\n"+"好让艳阳映明湖，"+"\n"+"便道新草掩湿泥，"+"\n"+"是日廊檐燕踌躇，"+"\n"+"晴雨自有天道定，"+"\n"+"天地之间唯太虚。");
        }

//            switch (nowSolarTerms){
//                case "立春":
//
//                    break;
//                case "雨水":
//                    break;
//            }
    }


    //天气
    private final static String PATH="http://wthrcdn.etouch.cn/weather_mini?city=";

    String ul;
//    ProgressDialog dialog=null;


    @SuppressLint("HandlerLeak")
    private Handler mhandler=new Handler(){
        public void handleMessage(android.os.Message msg) {
//            dialog.dismiss();
            switch (msg.what) {
                case 1:
                    JSONArray data=(JSONArray) msg.obj;
                    try {
                        String day01= data.getString(0);

                        weather = day01.substring(day01.indexOf("type")+7,day01.length()-2);
                        String high = day01.substring(day01.indexOf("高温")+2,day01.indexOf("fengli")-3);
                        String low = day01.substring(day01.indexOf("低温")+2,day01.indexOf("fengxiang")-3);
//                        Toast.makeText(getContext(),string3,Toast.LENGTH_SHORT).show();
                        city_result1.setText("今天天气："+weather+"  最高："+high+" 最低："+low);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    Toast.makeText(getContext(), "城市无效",Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getContext(), "网络无效",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        };
    };


    public void findWeather(String string) {
        // TODO Auto-generated method stub
//        dialog=new ProgressDialog(getContext());
//        dialog.setMessage("正在玩命加载中...");
//        dialog.show();

        //发起请求给那个网站
        new Thread(){
            public void run() {
                try {
                    ul=PATH+ URLEncoder.encode(string,"UTF-8");
//                    ul ="http://wthrcdn.etouch.cn/weather_mini?city=北京";
                    URL url=new URL(ul);

                    //设置必要的参数信息
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);

                    //判断响应码
                    code = conn.getResponseCode();
                    if(code==200){
                        //连接网络成功
                        InputStream in = conn.getInputStream();
//                        InputStream in = url.openStream();
                        String data = StreamTool.decodeStream(in);


                        //解析json格式的数据
                        JSONObject jsonObj=new JSONObject(data);
                        //获得desc的值
                        String result = jsonObj.getString("desc");
                        if("OK".equals(result)){
                            //城市有效，返回了需要的数据
                            JSONObject dataObj = jsonObj.getJSONObject("data");

                            JSONArray jsonArray = dataObj.getJSONArray("forecast");
                            //通知更新ui
                            Message msg = Message.obtain();
                            msg.obj=jsonArray;
                            msg.what=1;
                            mhandler.sendMessage(msg);
                        }else{
                            //城市无效
                            Message msg=Message.obtain();
                            msg.what=2;
                            mhandler.sendMessage(msg);
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what=3;
                    mhandler.sendMessage(msg);
                }
            };
        }.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(siLoManager != null)
            siLoManager.stop();
        if(reGeManager != null){
            reGeManager.stop();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if(requestCode == 0 && siLoManager != null)
            siLoManager.reStart();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }
}

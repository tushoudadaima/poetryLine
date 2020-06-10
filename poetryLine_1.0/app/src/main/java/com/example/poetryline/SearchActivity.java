package com.example.poetryline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.poetryline.detail.DetailActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class SearchActivity extends AppCompatActivity {
    private SearchView searchView;
    private ListView poetryListView;
    private RelativeLayout linearLayout_history;
    private ListView listView_history;
    private TextView history_search;
    private ImageView back;

    private TextView clear_search_history;
    private TextView textView_noSearch;


    //选自  https://www.52tourism.com/yuwen/29783.html  https://wenku.baidu.com/view/57256f19a76e58fafab003e0.html

    private String[] Strs = {"李白", "床前明月光", "鹅鹅鹅", "啊啊啊","李黑","李蓝","a","aa","ab","ac","ad","b","bb","bc","cc"
            ,"春色满园关不住，一枝红杏出墙来","南朝四百八十寺，多少楼台烟雨中","碧玉妆成一树高，万条垂下绿丝绦","不知细叶谁裁出，二月春风似剪刀","天街小雨润如酥，草色遥看近却无","最是一年春好处，绝胜烟柳满皇都"
            ,"泉眼无声惜细流，树阴照水爱晴柔","小荷才露尖尖角，早有蜻蜓立上头","毕竟西湖六月中，风光不与四时同","接天莲叶无穷碧，映日荷花别样红","梅子黄时日日晴，小溪泛尽却山行","荷尽已无擎雨盖，菊残犹有傲霜枝"
            ,"月落乌啼霜满天，江枫渔火对愁眠","停车坐爱枫林晚，霜叶红于二月花","一道残阳铺水中，半江瑟瑟半江红","可怜九月初三夜，露似真珠月似弓","千山鸟飞绝，万径人踪灭","孤舟蓑笠翁，独钓寒江雪"
            ,"日暮苍山远，天寒白屋贫","柴门闻犬吠，风雪夜归人","忽如一夜春风来，千树万树梨花开","吹灯窗更明，月照一天雪","人生自古谁无死，留取丹心照汗青","三万里河东入海，五千仞岳上摩天"
            ,"遗民泪尽胡尘里，南望王师又一年","死去元知万事空，但悲不见九州同","国破山河在，城春草木深","感时花溅泪，恨别鸟惊心","烽火连三月，家书抵万金","白头搔更短，浑欲不胜簪"
            ,"黄沙百战穿金甲，不破楼兰终不还","生当作人杰，死亦为鬼雄","四百万人同一哭，去年今日割台湾","众鸟高飞尽，孤云独去闲","相看两不厌，只有敬亭山","横看成岭侧成峰，远近高低各不同"
            ,"会当凌绝顶，一览众山小","半亩方塘一鉴开，天光云影共徘徊","一道残阳铺水中，半江瑟瑟半江红","湖光秋月两相和，潭面无风镜未磨","日照香生紫烟，遥看瀑布挂前川","飞流直下三千尺，疑是银河落九天"
            ,"天门中断楚江开，碧水东流至此回","两岸青山相对出，孤帆一片日边来","水光潋滟晴方好，山色空蒙雨亦奇","昼出耘田夜积麻，村庄儿女各当家","童孙未解供耕织，也傍桑荫学种瓜","故人具鸡黍，邀我至田家"
            ,"绿树村边合，青山郭外斜","开轩面场圃，把酒话桑麻","待到重阳日，还来就菊花","人闲桂花落，夜静春山空","月出惊山鸟，时鸣春涧中","葡萄美酒夜光杯，欲饮琵琶马上催"
            ,"醉卧沙场君莫笑，古来征战几人回","秦时明月汉时关，万里长征人未还","但使龙城飞将在，不教胡马度阴山","黄河远上白云间，一片孤城万仞山","羌笛何须怨杨柳，春风不度玉门关","林暗草惊风，将军夜引弓"
            ,"平明寻白羽，没在石棱中","千里黄云白日曛，北风吹雁雪纷纷","莫愁前路无知己，天下谁人不识君","渭城朝雨邑轻尘，客舍青青柳色新","劝君更尽一杯酒，西出阳关无故人","寒雨连江夜入吴，平明送客楚山孤"
            ,"洛阳亲友如相问，一片冰心在玉壶","长安陌上无穷树，唯有垂柳管别离","又送王孙去，萋萋满别情","渭城朝雨邑轻尘，客舍青青柳色新","劝君更尽一杯酒，西出阳关无故人","洛阳亲友如相问，一片冰心在玉壶"
            ,"海内存知己，天涯若比邻","李白乘舟将欲行，忽闻岸上踏歌声","桃花潭水深千尺，不及汪伦送我情","床前明月光，疑是地上霜","举头望明月，低头思故乡","独在异乡为异客，每逢佳节倍思亲"
            ,"遥知兄弟登高处，遍插茱萸少一人","春风又绿江南岸，明月何时照我还","君自故乡来，应知故乡事","来日绮窗前，寒梅著花未","百川东到海，何时复西归","少壮不努力，老大徒伤悲"
            ,"明日复明日，明日何其多","我生待明日，万事成蹉跎","莫等闲，白了少年头，空悲切","我家洗砚池边树，朵朵花开淡墨痕","不要人夸好颜色，只留清气满乾坤","咬定青山不放松，立根原在破岩中"
            ,"千磨万击还坚劲，任尔东西南北风","千锤万击出深山，烈火焚烧若等闲","粉骨碎身全不怕，要留清白在人间","春眠不觉晓，处处闻啼鸟","夜来风雨声，花落知多少"
            ,""
            ,"举世皆浊我独清，众人皆醉我独醒","老骥伏枥，志在千里；烈士暮年，壮心不已","山不厌高，海不厌深；周公吐哺，天下归心","采菊东篱下，悠然见南山","山气日夕佳，飞鸟相与还","羁鸟恋旧林，池鱼思故渊"
            ,"蝉噪林逾静，鸟鸣山更幽","相顾无相识，长歌怀采薇","前不见古人，后不见来者。念天地之悠悠，独怆然而涕下","春江潮水连海平，海上明月共潮生","海上生明月，天涯共此时","羌笛何须怨杨柳，春风不度玉门关"
            ,"野旷天低树，江清月近人","黄沙百战穿金甲，不破楼兰终不还","秦时明月汉时关，万里长征人未还","洛阳亲友如相问，一片冰心在玉壶","明月松间照，清泉石上流","行到水穷处，坐看云起时"
            ,"月出惊山鸟，时鸣春涧中","蜀道之难，难于上青天","清水出芙蓉，天然去雕饰","浮云游子意，落日故人情","相看两不厌，只有敬亭山","秋风吹不尽，总是玉关情"
            ,"举杯邀明月，对影成三人","俱怀逸兴壮思飞，欲上青天揽明月","仰天大笑出门去，我辈岂是蓬蒿人","抽刀断水水更流，举杯消愁愁更愁","天生我材必有用，千金散尽还复来","长风破浪会有时，直挂云帆济沧海",
            "欲渡黄河冰塞川，将登太行雪满山",
            "我寄愁心与明月，随风直到夜郎西",
            "请君试问东流水，别意与之谁短长",
            "此夜曲中闻折柳，何人不起故园情",
            "云想衣裳花想容，春风拂槛露华浓",
            "君不见黄河之水天上来，奔流到海不复回",
            "安能摧眉折腰事权贵，使我不得开心颜",
            "行人刁斗风沙暗，公主琵琶幽怨多",
            "年年战骨埋荒外，空见蒲萄入汉家",
            "潮平两岸阔，风正一帆悬",
            "晴川历历汉阳树，芳草萋萋鹦鹉洲",
            "莫愁前路无知己，天下谁人不识君",
            "战士军前半死生，美人帐下犹歌舞",
            "今夜偏知春意暖，虫声新透绿窗纱",
            "朱门酒肉臭，路有冻死骨",
            "射人先射马，擒贼先擒王",
            "细雨鱼儿出，微风燕子斜",
            "读书破万卷，下笔如有神",
            "随风潜入夜，润物细无声",
            "感时花溅泪，恨别鸟惊心",
            "烽火连三月，家书抵万金",
            "会当凌绝顶，一览众山小",
            "露从今夜白，月是故乡明",
            "笔落惊风雨，诗成泣鬼神",
            "文章千古事，得失寸心知",
            "星随平野阔，月涌大江流",
            "新松恨不高千尺，恶竹应须斩万竿",
            "万里悲秋常作客，百年多病独登台",
            "无边落木萧萧下，不尽长江滚滚来",
            "为人性僻耽佳句，语不惊人死不休",
            "尔曹身与名俱灭，不废江河万古流",
            "此曲只应天上有，人间那得几回闻",
            "出师未捷身先死，长使英雄泪沾襟",
            "酒债寻常行处有，人生七十古来稀",
            "白日放歌须纵酒，青春作伴好还乡",
            "花径不曾缘客扫，蓬门今始为君开",
            "忽如一夜春风来，千树万树梨花开",
            "今夜偏知春气暖，虫声新透绿窗纱",
            "春潮带雨晚来急，野渡无人舟自横",
            "春风得意马蹄疾，一日看尽长安花",
            "若待上林花似锦，出门俱是看花人",
            "人面不知何处去，桃花依旧笑春风",
            "今夜月明人尽望，不知愁思落谁家",
            "蚍蜉撼大树，可笑不自量",
            "晴空一鹤排云上，便引诗情到碧霄",
            "旧时王谢堂前燕，飞入寻常百姓家",
            "东边日出西边雨，道是无晴却有晴",
            "千呼万唤始出来，犹抱琵琶半遮面",
            "同是天涯沦落人，相逢何必曾相识",
            "回眸一笑百媚生，六宫粉黛无颜色",
            "在天愿作比翼鸟，在地愿为连理枝",
            "试玉要烧三日满，辨材须待七年期",
            "曾经沧海难为水，除却巫山不是云",
            "年年岁岁花相似，岁岁年年人不同",
            "黑发不知勤学早，白首方悔读书迟",
            "采得百花成蜜后，为谁辛苦为谁甜",
            "十年磨一剑，霜刃未曾试",
            "两句三年得，一吟双泪流",
            "男儿何不带吴钩，收取关山五十州",
            "昆山玉碎凤凰叫，芙蓉泣露香兰笑",
            "我有迷魂招不得，雄鸡一声天下白",
            "二十四桥明月夜，玉人何处教吹箫",
            "一骑红尘妃子笑，无人知是荔枝来",
            "天阶夜色凉如水，卧看牵牛织女星",
            "停车坐爱枫林晚，霜叶红于二月花",
            "东风不与周郎便，铜雀春深锁二乔",
            "可怜无定河边骨，犹是春闺梦里人",
            "春蚕到死丝方尽，蜡炬成灰泪始干",
            "身无彩凤双飞翼，心有灵犀一点通",
            "相见时难别亦难，东风无力百花残",
            "历鉴前朝国与家，成由勤俭败由奢",
            "可怜夜半虚前席，不问苍生问鬼神",
            "秋阴不散霜飞晚，留得枯荷听雨声",
            "夕阳无限好，只是近黄昏",
            "天意怜幽草，人间重晚晴",
            "风暖鸟声碎，日高花影重",
            "吟安一个字，捻断数茎须",
            "多情只有春庭月，犹为离人照落花",
            "凭君莫话封侯事，一将功成万骨枯",
            "苦恨年年压金线，为他人作嫁衣裳",
            "海阔凭鱼跃，天高任鸟飞",
            "有花堪折直须折，莫待无花空折枝",
            "问君能有几多愁，恰似一江春水向东流",
            "剪不断，理还乱，是离愁，别是一番滋味在心头",
            "昨夜西风凋碧树，独上高楼，望尽天涯路",
            "无可奈何花落去，似曾相识燕归来",
            "疏影横斜水清浅，暗香浮动月黄昏",
            "沙上并禽池上暝，云破月来花弄影",
            "泪眼问花花不语，乱红飞过秋千去",
            "月上柳梢头，人约黄昏后",
            "衣带渐宽终不悔，为伊消得人憔悴",
            "多情自古伤离别，更那堪冷落清秋节",
            "今宵酒醒何处，杨柳岸晓风残月",
            "问渠哪得清如许，为有源头活水来",
            "看似寻常最奇崛，成如容易却艰辛",
            "枝上柳绵吹又少，天涯何处无芳草",
            "欲把西湖比西子，淡妆浓抹总相宜",
            "荷尽已无擎雨盖，菊残犹有傲霜枝",
            "旧书不厌百回读，熟读深思子自知",
            "但愿人长久，千里共婵娟",
            "人有悲欢离合，月有阴晴圆缺",
            "大江东去，浪淘尽，千古风流人物",
            "生当作人杰，死亦为鬼雄",
            "物是人非事事休，欲语泪先流",
            "寻寻觅觅，冷冷清清，凄凄惨惨戚戚",
            "莫道不销魂，帘卷西风，人比黄花瘦",
            "花自飘零水自流，一种相思，两处闲愁",
            "两情若是久长时，又岂在朝朝暮暮",
            "文章本天成，妙手偶得之",
            "位卑未敢忘忧国，事定犹须待阖棺",
            "纸上得来终觉浅，绝知此事要躬行",
            "小楼一夜听风雨，深巷明朝卖杏花",
            "出师一表真名世，千载谁堪伯仲间",
            "接天莲叶无穷碧，映日荷花别样红",
            "月子弯弯照九州，几家欢乐几家愁",
            "春色满园关不住，一枝红杏出墙来",
            "绿杨烟外晓寒轻，红杏枝头春意闹",
            "臣心一片磁针石，不指南方不肯休",
            "沾衣欲湿杏花雨，吹面不寒杨柳风",
            "梅须逊雪三分白，雪却输梅一段香",
            "青山遮不住，毕竟东流去",
            "众里寻他千百度，蓦然回首，那人却在灯火阑珊处",
            "想当年，金戈铁马，气吞万里如虎",
            "千古兴亡多少事，悠悠",
            "不尽长江滚滚流",
            "二十四桥仍在，波心荡，冷月无声",
            "莫等闲，白了少年头，空悲切",
            "三十功名尘与土，八千里路云和月",
            "着意栽花花不发，无意插柳柳成阴",
            "晓来谁染霜林醉？总是离人泪",
            "花落水流红，闲愁万种，无语怨东风",
            "不是一番寒彻骨，怎得梅花扑鼻香",
            "十年窗下无人问，一举成名天下知",
            "渺沧海之一粟",
            "羡长江之无穷",
            "仰观宇宙之大",
            "所以游目骋怀",
            "巫山巫峡气萧森",
            "塞上风云接地阴",
            "不畏浮云遮望眼",
            "只缘身在最高层",
            "抱明月而长终",
            "后人哀之而不鉴之",
            "亦使后人而复哀后人也",
            "安能摧眉折腰事权贵",
            "使我不得开心颜",
            "不义而富且贵",
            "一尊还酹江月",
            "开琼筵以坐花",
            "三顾频烦天下计",
            "长使英雄泪满襟",
            "洞庭波兮木叶下",
            "岩扉松径长寂寥",
            "惟有幽人自来去",
            "申之以孝悌之义",
            "举一隅不以三隅反",
            "悟言一室之内",
            "放浪形骸之外",
            "别有幽愁暗恨生",
            "以手抚膺坐长叹",
            "爽籁发而清风生",
            "然后知松柏之后凋也",
            "别时容易见时难",
            "流水落花春去也",
            "策扶老以流憩",
            "云无心以出岫",
            "矗不知其几千万落",
            "皎皎空中孤月轮",
            "江月何年初照人",
            "云霞明灭或可睹",
            "大块假我以文章",
            "会桃花之芳园",
            "天涯霜雪霁寒宵",
            "三峡星河影动摇",
            "似诉平生不得志",
            "低眉信手续续弹",
            "万里悲秋常作客",
            "百年多病独登台",
            "仁义不施而攻守之势异也",
            "躬自厚而薄责于人",
            "捐余袂兮江中",
            "银瓶乍破水浆迸",
            "曲终收拨当心画",
            "猿猱欲度愁攀援",
            "百步九折萦岩峦",
            "又以悲夫古书之不存",
            "何可胜道也哉",
            "擗蕙櫋兮既张",
            "士皆垂泪涕泣",
            "契阔谈沧海月明珠有泪",
            "只是当时已惘然",
            "熊咆龙吟殷岩泉",
            "栗深林兮惊层巅",
            "奈何取之尽锱铢",
            "梧桐更兼细雨",
            "烟光凝而暮山紫",
            "幽咽泉流冰下难",
            "凝绝不通声暂歇",
            "苟非吾之所有",
            "虽一毫而莫取",
            "穷岛屿之萦回",
            "即冈峦之体势",
            "将以遗兮运者",
            "时不可兮骤得",
            "多于南亩之农夫",
            "既自以心为形役",
            "悟已往之不谏",
            "隔叶黄鹂空好音",
            "俯察品类之盛",
            "足以极视听之娱",
            "莫听穿林打叶声",
            "何妨吟啸且徐行",
            "东篱把酒黄昏后",
            "狗彘食人食而不知检",
            "涂有饿莩而不知发",
            "与佳期兮夕张",
            "乐琴书以消忧",
            "将有事于西畴",
            "塞上风云接地阴",
            "孤舟一系故园心",
            "履至尊而制六合",
            "执敲扑而鞭笞天下",
            "沧海月明珠有泪",
            "蓝田日暖玉生烟",
            "君子博学而日参省乎己",
            "则知明而行无过矣",
            "申之以孝悌之义",
            "大珠小珠落玉盘",
            "间关莺语花底滑",
            "策扶老以流憩",
            "云无心以出岫",
            "畏途巉岩不可攀",
            "飞湍瀑流争喧豗",
            "以先国家之急而后私仇也",
            "侣鱼虾而友麋鹿",
            "举匏樽以相属",
            "渚清沙白鸟飞回",
            "不尽长江滚滚来",
            "如何四纪为天子",
            "不及卢家有莫愁",
            "聊乘化以归尽",
            "乐夫天命复奚疑",
            "冰泉冷涩弦凝绝",
            "别有幽愁暗恨生",
            "吞声踯躅不敢言",
            "芙蓉泣露香兰笑",
            "忽到庞公栖隐处",
            "镜中衰鬓已先斑",
            "地崩山摧壮士死",
            "然后天梯石栈相钩连",
            "心非木石无感",
            "在地愿为连理枝",
            "天长地久有时尽",
            "分明怨恨曲中论",
            "玉露凋伤枫树林",
            "江间波浪兼天涌",
            "水随天去秋无际",
            "见小利则大事不成",
            "庄生晓梦迷蝴蝶",
            "蓝田日暖玉生烟",
            "同是天涯沦落人",
            "相逢何必曾相识",
            "暮霭沉沉楚天阔",
            "宁移白首之心",
            "不坠青云之志",
            "臣所以去亲戚而事君者",
            "此人皆意有所郁结",
            "梧桐更兼细雨",
            "谁悲失路之人",
            "尽是他乡之客",
            "雕栏玉砌应犹在",
            "恰似一江春水向东流",
            "不知老之将至云尔",
            "不如乡人之善者好之",
            "其不善者恶之",
            "数口之家可以无饥矣",
            "颁白者不负戴于道路矣",
            "飞湍瀑流争喧豗",
            "砯崖转石万壑雷",
            "便纵有千种风情",
            "落霞与孤鹜齐飞",
            "秋水共长天一色",
            "渚清沙白鸟飞回",
            "无边落木萧萧下",
            "申之以孝悌之义",
            "虽九死其犹未悔",
            "路曼曼其修远兮",
            "","","",""
            ,"","","","","",""
            ,"","","","","",""
            ,"","","","","",""
            ,"","","","","",""
            ,"","","","","",""
            ,"","","","","",""

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView=findViewById(R.id.search_view);
        searchView.setFocusable(true);//设置焦点 但是没用。。。？？
        searchView.setSubmitButtonEnabled(true);//提交按钮
        poetryListView = findViewById(R.id.listView);

        linearLayout_history = findViewById(R.id.linearLayout_history);
        listView_history = findViewById(R.id.listView_history);

//        String[] history_strings = getHistorySearch(getApplicationContext());
//        listView_history.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, history_strings));

        history_search = findViewById(R.id.history_search);
        back=findViewById(R.id.back);

        clear_search_history = findViewById(R.id.clear_search_history);
        textView_noSearch = findViewById(R.id.textView_noSearch);

        //写入SharedPreferences全null
        clear_search_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] stringsAllNull = new String[10];
                Arrays.fill(stringsAllNull, "null");
                saveHistorySearch(getApplicationContext(),stringsAllNull);

                String[] history_strings = getHistorySearch(getApplicationContext());
                ArrayList<String> history_strings2 = new ArrayList<>();
                for (String history_string : history_strings) {
                    if (!history_string.equals("null")) {
                        history_strings2.add(history_string);
                    }
                }

                listView_history.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, history_strings2));

                if (history_strings2.size()==0){
                    textView_noSearch.setVisibility(View.VISIBLE);
                }else {
                    textView_noSearch.setVisibility(View.GONE);
                }

            }
        });


//        数组去重 并且排序
        TreeSet<String> staffsSet = new TreeSet<>(Arrays.asList(Strs));
        List<String> resultList = new ArrayList<>(staffsSet);




        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getContext(),query,Toast.LENGTH_SHORT).show();
                //获取SharedPreferences中的值
                String[] strings1 = getHistorySearch(getApplicationContext());
                String[] strings2 =new String[10];
                Arrays.fill(strings2, "null");

                if (strings1[9].equals("null")){
                    for (int i = 0;i<10;i++){
                        if(!strings1[i].equals("null")){
                            strings2[i]=strings1[i];
                        }else {
                            strings2[i]=query;
                            break;
                        }
                    }
                }else {
                    strings2[9]=query;
                    for (int i =0;i<9;i++){
                        strings2[i]=strings1[i+1];

                    }
                }
                String[] strings3 = array_unique(strings2);
                //保存新输入的值  并且去重
                saveHistorySearch(getApplicationContext(),strings3);

                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra("name",query);
                startActivity(intent);
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
//                mListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, resultList));
                poetryListView.setTextFilterEnabled(true);

                String[] newStrs = {};
                ArrayList<String> stringArrayList = new ArrayList<String>();
//                for (int i = 0; i<mStrs.length;i++){
//                    if (mStrs[i].contains(newText)){
//                        stringArrayList.add(mStrs[i]);
//                    }
//                }

                //将包含关键字的string 重新放到newStrs数组中
                for (int i = 0; i<resultList.size();i++){
                    if (resultList.get(i).contains(newText)){
                        stringArrayList.add(resultList.get(i));
                    }
                }
                newStrs = stringArrayList.toArray(new String[stringArrayList.size()]);

                //输入框不为空
                if (!TextUtils.isEmpty(newText)){
//                    mListView.setFilterText(newText);
//                    mListView.clearTextFilter();
                    poetryListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,newStrs));

                    //历史搜索
                    linearLayout_history.setVisibility(View.GONE);//历史搜索控件隐藏 不可见
                }else{
//                    mListView.clearTextFilter();
//                    editText.clearFocus();//输入框清除焦点
                    poetryListView.setAdapter(null);

                    linearLayout_history.setVisibility(View.VISIBLE);//历史搜索控件显示
                }

                //listView的点击事件
                poetryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Toast.makeText(getApplicationContext(),""+adapterView.getAdapter().getItem(i),Toast.LENGTH_LONG).show();
                        String string = (String) adapterView.getAdapter().getItem(i);

                        //获取SharedPreferences中的值
                        String[] strings1 = getHistorySearch(getApplicationContext());
                        String[] strings2 =new String[10];
//                        Set[] strings2 = new Set[10];
                        Arrays.fill(strings2, "null");

                        if (strings1[9].equals("null")){
                            for (int a = 0;a<10;a++){
                                if(!strings1[a].equals("null")){
                                    strings2[a]=strings1[a];
                                }else {
                                    strings2[a]=string;
                                    break;
                                }
                            }
                        }else {
                            strings2[9]=string;
                            for (int b =0;b<9;b++){
                                strings2[b]=strings1[b+1];

                            }
                        }
                        String[] strings3 = array_unique(strings2);
                        //保存新输入的值  并且去重
                        saveHistorySearch(getApplicationContext(),strings3);

                        Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                        intent.putExtra("name",string);
                        startActivity(intent);
                    }
                });
                return false;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
    public static void saveHistorySearch(Context context, String[] stringArray){
        SharedPreferences prefs = context.getSharedPreferences("History_search", Context.MODE_PRIVATE);
        JSONArray jsonArray = new JSONArray();
        for (String b : stringArray) {
            jsonArray.put(b);
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("History_search", jsonArray.toString());
        editor.apply();
    }

    public static String[] getHistorySearch(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences("History_search", Context.MODE_PRIVATE);
//        String[] resArray=new String[arrayLength];
        String[] resArray=new String[10];
        Arrays.fill(resArray, "null");
        try {
            JSONArray jsonArray = new JSONArray(prefs.getString("History_search", "[]"));
            for (int i = 0; i < jsonArray.length(); i++) {
                resArray[i] = jsonArray.getString(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resArray;
    }
    protected void onResume() {

        super.onResume();
        String[] history_strings = getHistorySearch(getApplicationContext());
//        String[] history_strings2 = new String[10];
//        Arrays.fill(history_strings2, "");
        ArrayList<String> history_strings2 = new ArrayList<>();
        for (int i=0;i<history_strings.length;i++){
            if(!history_strings[i].equals("null")){
//                history_strings2[i]=history_strings[i];
                history_strings2.add(history_strings[i]);
            }
        }

        listView_history.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, history_strings2));
        listView_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Toast.makeText(getApplicationContext(),""+adapterView.getAdapter().getItem(i),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra("name",(String) adapterView.getAdapter().getItem(i));
                startActivity(intent);
            }
        });
//        Toast.makeText(getApplicationContext(),history_strings2.size()+"",Toast.LENGTH_LONG).show();
        if (history_strings2.size()==0){
            textView_noSearch.setVisibility(View.VISIBLE);
        }else {
            textView_noSearch.setVisibility(View.GONE);
        }

    }

    //string数组去重
    public static String[] array_unique(String[] a) {
        // array_unique
        List<String> list = new LinkedList<String>();
        for(int i = 0; i < a.length; i++) {
            if(!list.contains(a[i])) {
                list.add(a[i]);
            }
        }
        return (String[])list.toArray(new String[list.size()]);
    }
}

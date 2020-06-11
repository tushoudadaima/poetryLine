package com.example.poetryline.fragment;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.poetryline.R;
import com.example.poetryline.adapter.MyHomePageThreeAdapter;
import com.example.poetryline.entity.HomePagePoetry;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomepageFragmentThree extends Fragment {

    private RecyclerView recyclerView;
    private List<HomePagePoetry> lists = new ArrayList<>();
    private List<Typeface> typefaceList = new ArrayList<>();

    public static HomepageFragmentThree newInstance(String data){
        Bundle bundle = new Bundle();
        bundle.putString("data",data);
        HomepageFragmentThree three = new HomepageFragmentThree();
        three.setArguments(bundle);
        return three;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().getString("data")!=null){
            lists = parseJSONWithJSONObject(getArguments().getString("data"));
        }else{
            Toast.makeText(getContext(),"未连接网络",Toast.LENGTH_SHORT).show();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_three_fragment,container,false);
        recyclerView = view.findViewById(R.id.recycler_view_two);

        AssetManager assetManager = getActivity().getAssets();
        Typeface type_one = Typeface.createFromAsset(assetManager,"fonts/xingkai.ttf");
        typefaceList.add(type_one);
        Typeface type_two = Typeface.createFromAsset(assetManager,"fonts/xihei.ttf");
        typefaceList.add(type_two);
        Typeface type_three = Typeface.createFromAsset(assetManager,"fonts/yishuhei.ttf");
        typefaceList.add(type_three);

        if (lists==null){
            String str = "[{\"id\":100,\"title\":\"句\",\"content\":\"雪耻酬百王，除凶报千古。|昔乘匹马去，今驱万乘来。|近日毛虽暖闻弦心已惊。\",\"author\":\"太宗皇帝\",\"dynasty\":\"唐\"},{\"id\":10100,\"title\":\"酬顾况见寄\",\"content\":\"于越城边枫叶高，楚人书里寄离骚。|寒江鸂鶒思俦侣，岁岁临流刷羽毛。\",\"author\":\"包佶\",\"dynasty\":\"唐\"},{\"id\":20100,\"title\":\"感别送从叔校书简再登科东归\",\"content\":\"长安车马道，高槐结浮阴。|下有名利人，一人千万心。|黄鹄多远势，沧溟无近浔。|怡怡静退姿，泠泠思归吟。|菱唱忽生听，芸书回望深。|清风散言笑，余花缀衣襟。|独恨鱼鸟别，一飞将一沈。\",\"author\":\"孟郊\",\"dynasty\":\"唐\"},{\"id\":30100,\"title\":\"初夏题段郎中修竹里南园\",\"content\":\"高人游息处，与此曲池连。|密树纔春后，深山在目前。|远峰初绝雨，片石欲生烟。|数有僧来宿，应缘静好禅。\",\"author\":\"刘得仁\",\"dynasty\":\"唐\"},{\"id\":40100,\"title\":\"依韵荅黄校书\",\"content\":\"慈恩雁塔参差榜，杏苑莺花次第游。|白日有愁犹可散，青山高卧况无愁。\",\"author\":\"徐夤\",\"dynasty\":\"唐\"},{\"id\":50100,\"title\":\"海阳湖\",\"content\":\"闲游爱湖广，湖广丛怪石。|回合万里势，□□□□□。|绿动若无底，波澄涵云碧。|镕水复何如，昆池吾不易。|兹境多所尚，亲邻道与释。|外望虽异门，中间不相隔。|开凿尽天然，智者留奇迹。|我愿长此游，谁言一朝夕。\",\"author\":\"无名氏\",\"dynasty\":\"唐\"},{\"id\":60100,\"title\":\"残春有感  其一\",\"content\":\"阴阴垂柳转黄鹂，肠断残春欲去时。|白雪乱堆飞絮地，红须半在落花枝。|园林向晚深藏郭，野水新晴入池。|最是牡丹堪痛惜，风吹雨打渐离枝。\",\"author\":\"李至\",\"dynasty\":\"宋\"},{\"id\":70100,\"title\":\"千叶牡丹\",\"content\":\"濯水锦窠艳，颓云仙髻繁。|全欹碧槲叶，独占紫球栏。|谁谓萼华极，芳心多隠桓。\",\"author\":\"宋祁\",\"dynasty\":\"宋\"},{\"id\":80100,\"title\":\"竹洞\",\"content\":\"阴森过百步，屈曲锁莓苔。|翠色寒无改，清风时自来。|叶笼苍玉干，路入碧云堆。|吏散无公事，支筇到几回。\",\"author\":\"吴中复\",\"dynasty\":\"宋\"},{\"id\":90100,\"title\":\"草堂一上人\",\"content\":\"一公持一钵，想复度遥岑。|地瘦无黄犊，春来草更深。\",\"author\":\"王安石\",\"dynasty\":\"宋\"},{\"id\":100100,\"title\":\"诗一首\",\"content\":\"山川图写几时休，再过萧山岁已周。|客枕梦中南又北，此身大似一虚舟。\",\"author\":\"林希\",\"dynasty\":\"宋\"},{\"id\":110100,\"title\":\"题招提院静照堂\",\"content\":\"闲房增缔构，宴坐隔嚣埃。|屋木不加饰，庭花时自开。|虚明殊止水，寂绝等寒灰。|斋外无余事，欣逢高士来。\",\"author\":\"陆伸\",\"dynasty\":\"宋\"},{\"id\":120100,\"title\":\"湖上晚归寄诗友  其一\",\"content\":\"髭发难藏老，湖山稳寄身。|却寻方外士，招作社中人。|霜叶深于染，秋花晚自春。|无人还有碍，诗卷莫辞频。\",\"author\":\"陈师道\",\"dynasty\":\"宋\"},{\"id\":130100,\"title\":\"高宗皇帝挽词  其八\",\"content\":\"北内神灵地，期期亿万年。|岩廊明圣日，宇宙戴尧天。|乐事湖山在，英徽雅颂传。|那知开玉锁，不恋地行仙。\",\"author\":\"洪刍\",\"dynasty\":\"宋\"},{\"id\":140100,\"title\":\"比阅藏经偶成短偈仍寄同志者\",\"content\":\"君看故教定何缘，岂为多闻与福田。|念念会融归自已，言言当处便忘筌。|无钱空数他人宝，遮眼那令兕革穿。|鸟度虚空风过穴，不妨开卷亦安禅。\",\"author\":\"程俱\",\"dynasty\":\"宋\"},{\"id\":150100,\"title\":\"端午日北还至斛岭寄连州诸公\",\"content\":\"岭上逢端午，随家更北征。|隔村闻贼鬬，通夕畏蛇行。|厌病初辞瘴，冲泥却胜晴。|犹怜昌歜酒，不与故人倾。\",\"author\":\"吕本中\",\"dynasty\":\"宋\"},{\"id\":160100,\"title\":\"禅人并化主写真求赞  其二二三\",\"content\":\"眉目岩岩，身心静恬。|万机自息，一默谁参。|虚空兮霁云卷谷，清白兮夜月濯潭。|春入丛林兮谩道化工百亿，神游浩劫兮底意真超二三。\",\"author\":\"释正觉\",\"dynasty\":\"宋\"},{\"id\":170100,\"title\":\"石孝立挽词\",\"content\":\"斯人已矣舟移壑，惆怅九原谁可作。|灵光长与孤月孤，幻化偶随落叶落。|人间好事平生有，留得芬香传万口。|杨侯宅畔蜀江边，霜立梅花醉哀酒。|伯仲如林吾所爱，俯仰十年无一在。|鬓边日月去如飞，老怀不待秋风悲。\",\"author\":\"冯时行\",\"dynasty\":\"宋\"},{\"id\":180100,\"title\":\"同谊夫国才饯季然于普门院取壁间五字诗各探一句为韵赋五诗某得共饮碧苔畔  其四\",\"content\":\"尘榻为子下，柴扉为子开。|从今断还往，一室长莓苔。\",\"author\":\"王灼\",\"dynasty\":\"宋\"},{\"id\":190100,\"title\":\"阿姥\",\"content\":\"城南倒社下湖忙，阿姥龙钟七十强。|犹有尘埃嫁时镜，东涂西抹不成妆。\",\"author\":\"陆游\",\"dynasty\":\"宋\"}]";
            lists = parseJSONWithJSONObject(str);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MyHomePageThreeAdapter homePageThreeAdapter = new MyHomePageThreeAdapter(lists,typefaceList);
        recyclerView.setAdapter(homePageThreeAdapter);
        return view;
    }

    private static List<HomePagePoetry> parseJSONWithJSONObject(String jsonData){
        try {
            List<HomePagePoetry> data = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String content = jsonObject.getString("content");
                String author = jsonObject.getString("author");
                String dynasty = jsonObject.getString("dynasty");
                HomePagePoetry homePagePoetry = new HomePagePoetry();
                homePagePoetry.setTitle(title);
                homePagePoetry.setContent(content);
                homePagePoetry.setAuthor(author);
                homePagePoetry.setDynasty(dynasty);
                data.add(homePagePoetry);
            }
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

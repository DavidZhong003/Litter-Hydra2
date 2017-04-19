package com.doive.nameless.litter_hydra.ui.news.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.utils.GlideCircleTransform;
import com.doive.nameless.litter_hydra.utils.GlideRoundTransform;
import com.doive.nameless.litter_hydra.utils.HtmlFormatUtils;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import static android.R.attr.data;

/**
 * Created by Administrator on 2017/4/19.
 */

public class NewsDocDetailActivity
        extends BaseMvpActivity<NewsDocDetailPresenter> {
    public TextView               mTvNewsTitle;
    public ImageView              mIvCateLogo;
    public TextView               mTvCateName;
    public TextView               mTvEditTime;
    public Toolbar                mToolbar;
    public WebView                mWvNewsDetails;
    public RecyclerView           mRvLinkComment;
    public TwinklingRefreshLayout mTrlContent;

    @Override
    protected void initView(Bundle savedInstanceState) {
        this.mTvNewsTitle = getViewbyId(R.id.tv_news_title);
        this.mIvCateLogo = getViewbyId(R.id.iv_cate_logo);
        this.mTvCateName = getViewbyId(R.id.tv_cate_name);
        this.mTvEditTime = getViewbyId(R.id.tv_edit_time);
        this.mToolbar = getViewbyId(R.id.toolbar);
        this.mWvNewsDetails = getViewbyId(R.id.wv_news_details);
        this.mRvLinkComment = getViewbyId(R.id.rv_link_comment);
        this.mTrlContent = getViewbyId(R.id.trl_content);
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
        }

        initData();
        initListener();
    }

    private void initListener() {
    }

    private void initData() {

        Intent intent = getIntent();
        String url = intent.getStringExtra("111");
        mWvNewsDetails.loadUrl(url);
        // TODO: 2017/4/19
        mTvNewsTitle.setText("为什么大家越来越同情祁同伟 却不喜欢侯亮平？");
        mTvCateName.setText("沪法网");
        mTvEditTime.setText("2017/04/19 11:08:10");
        Glide.with(this)
             .load("http://p0.ifengimg.com/a/2016_53/ecefd96d57faabc.jpg")
             .diskCacheStrategy(DiskCacheStrategy.RESULT)
             .transform(new GlideCircleTransform(this))
             .into(mIvCateLogo);
        String data = "<p>原标题：为什么大家越来越同情祁同伟，却不喜欢侯亮平</p><p>\n" +
                "祁同伟是《人民的名义》中的反派角色。</p><p>\n" +
                "他工于心计、投机钻营，同时又左摇右摆、患得患失，着实是一位令人厌恶的角色形象，与拥有众多粉丝的“达康书记”形成鲜明对比。</p><!-- IFENG_DOC_ADVERT --><p>\n" +
                "但祁同伟也是剧中最具深意的反面角色。</p><p>\n" +
                "世上没有纯粹的好人或坏人，所谓的“好”与“坏”并非与生俱来的标签，而是在一次又一次的选择中，浸染和改变了之前的生命底色，回头是岸的机会并不多，只能硬着头皮走下去，一遍又一遍质疑或诠释着“生存还是毁灭”的人生命题。</p><p style=\"text-align:center\"><img data-ratio=\"0.7770961145194274\" data-type=\"jpeg\" data-w=\"489\" src=\"http://d.ifengimg.com/mw640_q75/p3.ifengimg.com/fck/2017_16/59fd682828ccae4_w489_h380.jpg\" longdesc=\"\" class=\" lazys\" width=\"\" height=\"\" /></p><p>\n" +
                "对祁同伟的厌恶情绪或有先入为主之嫌，但是在听了梁璐和吴惠芬的对话之后，对祁同伟忽然产生了一丝同情和怜悯。</p><p>\n" +
                "他出身贫寒，却渴望出人头地；学业出众，却遭遇不公待遇；攀附豪门，却无奈仰人鼻息；爬上高位，却无法遏制贪欲；最后玩火自焚，落得悲惨结局。祁同伟的前半生是一段草根逆袭的传奇奋斗史。</p><p>\n" +
                "相比陈岩石、侯亮平等人的脸谱化形象，祁同伟的形象要更真实、更普遍、更接地气一些。</p><p>\n" +
                "现实当中有千千万万个“祁同伟”，不断地重复“起高楼-宴宾客-楼塌了”的生命怪圈和因果轮回。时耶？命耶？</p><p>\n" +
                "一个穷小子，通过自己的努力考上了汉大，当上了学生会主席。他也曾意气风发，想有所作为。</p><p>\n" +
                "当年，作为与侯亮平，陈海齐名的“三杰”，他也曾对未来有过美好的向往。</p><p>\n" +
                "只是这一切对未来的期待，从他遇到梁璐的那一刻，就注定会走上一条完全不同的方向。</p><p>\n" +
                "与侯亮平和钟小艾对梁璐的评价不同，笔者对梁璐的评价只有一句话，no zuo no die，活该！</p><p>\n" +
                "梁璐追求祁同伟并非源于爱情，而是被无良男人抛弃之后转移阵地的补救措施，本质上出于一种找回面子的自私心理。</p><p>\n" +
                "祁同伟从内心里是瞧不上梁璐的，何况他已经心有所属。</p><p>\n" +
                "梁璐作为高干之女，予取予夺惯了，哪里受过这份委屈？被祁同伟拒绝之后，难免心生恨意，于是在毕业分配时做了手脚，借助“权力的一次小小任性”，把祁同伟发配到了山区乡镇的司法所，把陈阳分配到了北京。</p><p>\n" +
                "这种棒打鸳鸯的行为其实出于一种变态心理，我得不到的也不能让别人得到，而且还厚颜无耻地美其名曰“替他们两个考验了爱情”，人家两个人的爱情用得着你梁璐来考验吗？</p><p>\n" +
                "梁璐出于对男性的报复，追求他，三年而不可得。于是，“一件东西，我得不到，就毁掉它”。</p><p>\n" +
                "梁璐动用了她老爸的权力，陈阳到了北京。而那些不及祁同伟的同学们，也纷纷进了省市的单位，只有祁同伟，被发配到了偏远山村的司法所。</p><p>\n" +
                "梁璐的行为，对于祁同伟而言，和渣男求爱不成向女孩泼硫酸，又有什么本质的不同？</p><p>\n" +
                "祁同伟对自己的学业和才能是自信甚至自负的，也只有如此才能掩盖和平衡他在家庭背景方面的自卑。</p><p>\n" +
                "陈海和侯亮平一毕业就进了省级机关，而祁同伟却被发配到了山区基层。作为高育良教授的头号得意弟子，如此安排岂能让祁同伟心理平衡？</p><p>\n" +
                "祁同伟不是一个甘于平庸、甘于寂寞、甘于久居人下的人，但现实中的他又感到很无助、很无奈、甚至很悲催。</p><p>\n" +
                "他确确实实被梁璐及其背后的权力暗算了，就像如来佛手掌一翻便将孙猴子压在了五行山下一样，如何才能翻身？</p><p>\n" +
                "然而祁同伟依然不肯向命运低头，志愿进了缉毒队，身中三枪立了大功之后，又是老梁书记从中作梗，他依然调不到北京。</p><p>\n" +
                "无情的现实再次将他的梦想击得粉碎，只得感慨“英雄只是权力的工具”。</p><p>\n" +
                "于是，祁同伟屈服了，违心的去向梁璐示好。结果，梁璐让他在大庭广众之下下跪求婚。</p><p>\n" +
                "向曾经暗算过自己的女人及其背后的家族求婚，祁同伟做到了能屈能伸，忍不住夸他一句，“包羞忍耻是男儿”。这是其逆袭之路的转折点。</p><p>\n" +
                "然而，真相却是，一个曾经意气风发的缉毒英雄，在灵魂上已经死了，在他下跪的那一刻。</p><p>\n" +
                "取而代之的，是一个发誓要拿到权力，然后向这个世界报复的祁厅长。</p><p>\n" +
                "知道了这一切前因，祁同伟在之前的所作所为都有了心理活动上的合理解释。无论是，对上位者的奴颜婢膝，还是对婚姻的不忠，以及自保时的决绝狠辣。</p><p>\n" +
                "一个心死了的人，什么事干不出来呢？</p><p>\n" +
                "祁同伟是反派，没有错，他应该也必须受到党纪国法的制裁。而在一切的结局，他也的确为他的所作所为付出了代价，生命的代价。</p><p>\n" +
                "但是，即便如此，我仍然忍不住去同情他。</p><p>\n" +
                "因为，我对不受控制的权力，更加恐惧。</p><p>\n" +
                "因为，这种权力的运用，可以轻描淡写的在一瞬间毁掉一个人的一生。</p><p>\n" +
                "而且，最最令人绝望的，不是你求告无门，而是，不同于祁同伟后来的那些贪腐行为，这种权力的运用，从法律上来说，是合法的。</p><p>\n" +
                "法律上不可能追究老梁书记或者梁璐什么责任。</p><p>\n" +
                "甚至，他还有冠冕堂皇的理由：这是锻炼你。</p><p>\n" +
                "就算是梁璐默认了是从中作梗，还可以说：这是考验爱情。</p><p>\n" +
                "这也太可怕了。</p><p>\n" +
                "因为，对于没有掌握这种权力的寒门子弟来说，无论你多努力，都没用，你面前只有两条路：</p><p>\n" +
                "要么，向权力屈服；要么，向司法所的老所长那样，一辈子蹉跎在大山深处，甚至有朝一日，死在大山里。</p><p>\n" +
                "试问，有多少人不会选择跪下？</p><p>\n" +
                "不跪下的，比如陶渊明，不为五斗米折腰，固然是千古传诵的圣贤。但古今能成圣人的，有几个？</p><p>\n" +
                "我们不是圣人，我们是凡人。祁同伟也是。甚至，还是一个出身贫寒农村，吃不饱饭的，有一大堆穷亲戚巴望着他出人头地，好帮衬一点老家的凡人，所以，我们同情他。</p><p>\n" +
                "这也是我越往后看，越发厌恶侯亮平夫妇的原因。</p><p>\n" +
                "他们的一切，都来的太顺了，侯亮平工作一两年可以顺利调北京，这可是祁同伟真正拿命去拼都得不到的。</p><p>\n" +
                "钟小艾，年纪轻轻的正厅级干部。虽然把他们的背景隐去不提，但要说没背景，谁信？</p><p>\n" +
                "反贪局局长安排侯亮平下地方时，明确要求侯先征求其爱人同意，这一切都暗示着钟小艾父亲的地位，结合侯亮平对上省部级领导时的自然大方态度，其岳父至少也是省部级到副国级干部。</p><p>\n" +
                "已经进京的副国级干部赵立春的儿子赵瑞龙要找杀手做掉侯亮平，赵瑞龙的三姐紧急来电制止，告诉弟弟侯亮平要是死了你也得死！这说明了赵立春身为高官也没把握在与侯亮平的对决中保全儿子赵瑞龙，也从侧门佐证了侯亮平的背景惊人。</p><p>\n" +
                "他们得到了凡人们拿命去拼都得不到的东西，而且得到的如此轻易，如此理所当然。然后，还在那里大放厥词，什么“精于算计”啦，什么“信念纸糊的”啦，简直仿佛不食人间烟火的天使。</p><p>\n" +
                "让我想起了晋惠帝最著名的那句话“何不食肉糜”。</p><p>\n" +
                "还有断头皇后的那句“他们没面包吃？那就去吃蛋糕吧”。</p><p>\n" +
                "我一向认为，圣人这种东西，自己自愿做的，那的确令人敬仰。</p><p>\n" +
                "而站在旁边，事不关己，鼓动别人去做圣人的，甚至对做不到的凡人腹诽辱骂的，居心估计都不怎么好。最起码也是“站着说话不腰疼”。</p><p>\n" +
                "信念纸糊？中了三枪还被说成信念纸糊，我不知道侯亮平如果和祁同伟那样生于寒门，有没有身中三枪的执着。</p><p>\n" +
                "更不要说他的老婆钟小艾，言语做派都是一副高人一等的感觉。最深刻的那句话就是：“他摆得正自己的位置”。如果这句话比较隐晦，还有一句直白点的：“一次权力小小的任性”。</p><p>\n" +
                "没错，可以毁人一生的“流放”，在她口里不过是如同小孩之间抢玩具那样的“小小的任性”。</p><p>\n" +
                "那种刻在骨子里的优越感和居高临下的态度，真是跃然纸上。</p><p>\n" +
                "虽然知道他们是正面角色，但听了这两句话，我真的对他们喜欢不起来。</p><p>\n" +
                "我不禁想到另一个人，同样出身寒门的李达康。他虽身为市委书记，省委常委，但那种刻在骨子里的小心谨慎，如履薄冰，与侯亮平夫妇的无所顾忌显得鲜明的对比。</p><p>\n" +
                "不要说像侯亮平那样怒怼上官，李达康甚至永远不为家人做任何私事，为此夫妇反目，也没有朋友，最后孤家寡人。</p><p>\n" +
                "为什么？就像他亲口说的：他怕。</p><p>\n" +
                "所以，除了反腐，我看到了一些新的东西，那些寒门子弟的挣扎。</p><p>\n" +
                "从这个角度看，李达康为什么那么在意GDP,也是可以理解的了，因为他除了自己，没有人可以靠。</p><p>\n" +
                "这些寒门子弟，好一点的，成为李达康，孤家寡人。坏一点的，成为祁同伟，身败名裂。</p><p>\n" +
                "然后，还要被侯亮平，钟小艾他们取笑。</p><p>\n" +
                "为什么这么多人同情祁同伟，就是因为相比腐败，民众更害怕权力在合法的名义下不受控制。也更在意上升的通道是否通畅。</p><p>\n" +
                "祁同伟好歹出卖了灵魂和尊严，还可以卖身上嫁。对于那些，想上嫁都没机会的，如果，靠自身的努力，即便拼了命也得不到应有的结果，那才真是可怕的一件事。</p><p>\n" +
                "虽然，祁同伟咎由自取，但和许多人一样，我还是同情祁同伟。</p><p>\n" +
                "因为，在他的身上，我们看到了自己。</p><p>\n" +
                "梦想在现实面前低头，人生因强势而屈服。</p><p>\n" +
                "曾经年少无知，梦想仗剑走天涯，指点江山，激扬文字。后来经历得多了，社会和现实将梦想和激情磨平。奋起过，抗争过，终究向生存和生活妥协。</p><p>\n" +
                "这就是一个小人物的现实人生，祁同伟就是这样一个人。曾经大好男儿不屈服，为不公流血以命相争，但是终究螳臂当车。看透了之后他不再对抗而选择加入，并借力发展自己。</p><p>\n" +
                "而这就能解释祁同伟为什么拼了命都要向上爬，他被毁过所以他想争取对自己更多的掌控。可以说他为了复仇，也可以说他功利。</p><p>\n" +
                "这就是现实，就这样或多或少的经历，很多人或许都有过，而这是引起大家共鸣的原因。</p><p>\n" +
                "在大环境下，祁同伟不过是权力下的可怜虫，他是一个悲剧，而就这种悲剧，多少人求而不得？</p><p>\n" +
                "说到侯亮平夫妇，大家的厌恶感在于，他们是小人物现实的对立面，有点像仇富心理。</p><p>\n" +
                "借用某剧一句话：有钱长得帅是我的错吗？候夫妇是：有权有背景能平步青云是我的错吗？</p><p>\n" +
                "这不是你的错，这是你与生俱来的，无可指责，不可能为了什么公平让你走一遍祁同伟的路。</p><p>\n" +
                "但这的确也是你的错，你把现实的面具揭开了，人人生而平等终究是个口号，你享受着资源不公带来的好处而义正言辞的斥责新的不公，挺荒诞的吧。</p><p>\n" +
                "屁股决定位置，所以侯亮平夫妇又怎么可能获得大家的认同呢？</p><p>\n" +
                "残酷的是，人民的名义揭露的不过冰山一角。</p><p>\n" +
                "祁同伟原本代表着某种中国梦，然而却被中国的现实层层阻击。</p><p>\n" +
                "他是时代的牺牲品，却未必为后人的道路牺牲。</p><p>\n" +
                "他的失败具有时代溃败、阶层固化的象征意义。</p>";

        mWvNewsDetails.getSettings()
                      .setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mWvNewsDetails.getSettings()
                      .setSupportZoom(true);
        mWvNewsDetails.loadDataWithBaseURL(null,
                                           HtmlFormatUtils.htmlImageMatchingScreen(data),
                                           "text/html",
                                           "utf-8",
                                           null);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_news_doc;
    }

    @Override
    public void setPresenter(Object presenter) {

    }


}

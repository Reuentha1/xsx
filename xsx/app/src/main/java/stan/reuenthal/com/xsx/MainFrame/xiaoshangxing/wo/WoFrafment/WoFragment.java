package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.WoFrafment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.MainActivity;
import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputBoxLayout;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.SettingActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.CirecleImage;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.loadingview.DotsTextView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.PtrDefaultHandler;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.PtrFrameLayout;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.PtrHandler;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.StoreHouseHeader;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.NewsActivity.NewsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FengChaoQun
 * on 2016/7/3
 */
public class WoFragment extends BaseFragment implements WoContract.View, View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-WoFragment";

    private WoContract.Presenter mPresenter;

    private View mView, divider_line, footerview;
    private RelativeLayout headView;
    private ListView listView;
    private PtrFrameLayout ptrFrameLayout;
    private List<String> list = new ArrayList<String>();
    private Wo_listview_adpter adpter;

    private RelativeLayout title;

    //  记录listview点击位置
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;

    //    记录title点击时间  实现双击
    long firstClick;

    private boolean is_refresh = false;           //记录是否正在刷新
    private boolean is_loadMore = false;          //记录是否正在加载更多
    private boolean is_titleMove = false;          //记录是否正在移动导航栏

    private Handler handler;

    private TextView name1, name2;
    private CirecleImage headImage;
    private TextView news;
    private ImageView newsHead;
    private DotsTextView dotsTextView;
    private TextView LoadingText;

    private ImageView set, publish;

    private MainActivity activity;
    private InputBoxLayout inputBoxLayout;

    public static WoFragment newInstance() {
        return new WoFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_wo, container, false);
        mView = view;
        setmPresenter(new WoPresenter(this, getContext()));
        handler = new Handler();
        initView();
        setHead();
        return view;
    }

    private void initView(){
        activity = (MainActivity) getActivity();
        inputBoxLayout=activity.getInputBoxLayout();
        listView=(ListView)mView.findViewById(R.id.listview);
        ptrFrameLayout=(PtrFrameLayout)mView.findViewById(R.id.reflesh_layout);
        divider_line=mView.findViewById(R.id.line);
        title = (RelativeLayout) mView.findViewById(R.id.title);
        set = (ImageView) mView.findViewById(R.id.set);
        set.setOnClickListener(this);
        publish = (ImageView) mView.findViewById(R.id.publish);
        publish.setOnClickListener(this);

        //headview footview
        headView = (RelativeLayout) getActivity().getLayoutInflater().inflate(R.layout.util_wo_header, null);
        footerview = getActivity().getLayoutInflater().inflate(R.layout.footer, null);
        name1 = (TextView) headView.findViewById(R.id.name1);
        name2 = (TextView) headView.findViewById(R.id.name2);
        headImage = (CirecleImage) headView.findViewById(R.id.head_image);
        news = (TextView) headView.findViewById(R.id.news);
        newsHead = (ImageView) headView.findViewById(R.id.news_head);

        dotsTextView = (DotsTextView) footerview.findViewById(R.id.dot);
        LoadingText=(TextView)footerview.findViewById(R.id.text);

        news.setOnClickListener(this);

        listView.addHeaderView(headView);
        listView.addFooterView(footerview);
        //设置listview头
        setName("冯超群");
        setHead();

        //实现双击title返回第一条动态
        title.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if ((System.currentTimeMillis() - firstClick) < 1000) {
                        listView.setSelection(0);
                    }
                    firstClick = System.currentTimeMillis();
                }
                return true;
            }
        });

        /*
        **describe:listview的滑动事件 向上滑显示导航栏下边的分割线
        *           当评论框可见时，隐藏评论框
        */
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x1 = event.getX();
                    y1 = event.getY();

                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    x2 = event.getX();
                    y2 = event.getY();
                    if (y1 - y2 > 15 && !is_titleMove) {
                        hideTitle();
                    } else if (y2 - y1 > 5 & !is_titleMove) {
                        showTitle();
                    }
                }
//               隐藏评论框
                hideEdittext();
                return false;
            }
        });

//      测试使用数据
        for (int i=0;i<=14;i++){
            list.add("hhhh"+i);
        }
        adpter = new Wo_listview_adpter(getContext(), R.layout.item_wo_listview, list, this, getActivity());
        listView.setAdapter(adpter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (totalItemCount - (firstVisibleItem + visibleItemCount - 1) == 5) {
                    if (!is_loadMore) {
                        mPresenter.LoadMore();
                    }
                }
                if (firstVisibleItem+visibleItemCount==totalItemCount){
                    showFooter();
                } else if (firstVisibleItem == 0) {
                    showTitle();
                }
            }
        });

//      初始化刷新布局
        initFresh();

        footerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "正在更新内容", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news:
                gotoNews();
                break;
            case R.id.set:
                gotoSet();
                break;
            case R.id.publish:
                gotopublish();
                break;
        }
    }

    @Override
    public void setName(String name) {
        name1.setText(name);
        name2.setText(name);
    }

    @Override
    public void setHead() {
        Glide.with(getContext()).
                load("http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg")
                .animate(R.anim.fade_in)
                .into(headImage);
    }

    @Override
    public void setNews() {

    }

    private void initFresh(){
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.setPadding(0, getResources().getDimensionPixelSize(R.dimen.y144), 0, 20);
        header.initWithString("SWALK");
        header.setTextColor(getResources().getColor(R.color.green1));
        header.setBackgroundColor(getResources().getColor(R.color.w0));
        header.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_in));
        ptrFrameLayout.setDurationToCloseHeader(2000);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
//        ptrFrameLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ptrFrameLayout.autoRefresh(false);
//            }
//        }, 100);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                divider_line.setVisibility(View.INVISIBLE);
                if (!is_refresh) {
                    mPresenter.RefreshData();
                }
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }
        });
    }

    public void showEdittext(Context context) {
        inputBoxLayout.showOrHideLayout(true);
    }

    @Override
    public void hideEdittext() {
        inputBoxLayout.showOrHideLayout(false);
    }

    public void moveListview(int px) {
        listView.smoothScrollBy(px, Math.abs(px));
    }

    public int get_Editext_Height() {
        return inputBoxLayout.getEdittext_height();
    }

    @Override
    public void setmPresenter(@Nullable WoContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void gotoSet() {
        Intent setIntent = new Intent(getContext(), SettingActivity.class);
        startActivity(setIntent);
    }

    @Override
    public void gotopublish() {
        Intent publish_intent = new Intent(getContext(), InputActivity.class);
        publish_intent.putExtra(InputActivity.LIMIT,9);
        startActivity(publish_intent);
    }

    @Override
    public void gotoNews() {
        Intent new_intent = new Intent(getContext(), NewsActivity.class);
        startActivity(new_intent);
    }

    @Override
    public void showFooter() {
        dotsTextView.start();
    }

    @Override
    public void showNoData() {
        dotsTextView.stop();
        LoadingText.setText("没有动态啦");
    }

    @Override
    public void setEditCallback(InputBoxLayout.CallBack callback) {
        inputBoxLayout.setCallBack(callback);
    }

    @Override
    public String getEdittextText() {
        return null;
    }

    @Override
    public void hideTitle() {
        if (!is_titleMove) {
            int[] xy = new int[2];
            title.getLocationOnScreen(xy);
            if (xy[1] >= 0) {
                ValueAnimator animator = ValueAnimator.ofInt(0, title.getHeight());
                animator.setDuration(1000);

                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int curValue = (int) animation.getAnimatedValue();
                        title.layout(0, -curValue, title.getWidth(), -curValue + title.getHeight());
                    }
                });
                animator.start();
                is_titleMove = true;
                divider_line.setVisibility(View.INVISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        is_titleMove = false;
                    }
                }, 1000);
            }
        }
    }

    @Override
    public void showTitle() {
        if (!is_titleMove) {
            int[] xy = new int[2];
            title.getLocationOnScreen(xy);
            if (xy[1] < -10) {
                ValueAnimator animator = ValueAnimator.ofInt(-title.getHeight(), 0);
                animator.setDuration(1000);

                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int curValue = (int) animation.getAnimatedValue();
                        title.layout(0, curValue, title.getWidth(), curValue + title.getHeight());
                    }
                });
                animator.start();
                is_titleMove = true;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        is_titleMove = false;
                        divider_line.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        }
    }

    @Override
    public void setRefreshState(boolean is) {
        is_refresh = is;
    }

    @Override
    public void setLoadState(boolean is) {
        is_loadMore = is;
    }

    public boolean is_loadMore() {
        return is_loadMore;
    }

    public boolean is_refresh() {
        return is_refresh;
    }

    @Override
    public void refreshPager() {

    }

    @Override
    public void autoRefresh() {
        if (ptrFrameLayout != null) {
            if (mPresenter.isNeedRefresh()) {
                ptrFrameLayout.autoRefresh();
            }
        }
    }
}

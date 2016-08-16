package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.MessageNotice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.loadingview.DotsTextView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.PtrDefaultHandler;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.PtrFrameLayout;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.PtrHandler;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.StoreHouseHeader;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by FengChaoQun
 * on 2016/8/2
 */
public class MessageNoticeActivity extends BaseActivity implements MessageNoticeContract.View {
    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.reflesh_layout)
    PtrFrameLayout ptrFrameLayout;
    private View footview;
    private DotsTextView dotsTextView;
    private TextView loadingText;

    private boolean isRefreshing;
    private boolean isLoading;
    private Message_adpter message_adpter;
    private ArrayList<String> list = new ArrayList<String>();
    private MessageNoticeContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoshang_message);
        ButterKnife.bind(this);
        setmPresenter(new MessageNoticePresenter(this, this));
        initView();
        initFresh();
        autoRefresh();
    }

    private void initView() {
        View view = new View(this);
        listview.addHeaderView(view);
        footview = View.inflate(this, R.layout.footer, null);
        dotsTextView = (DotsTextView) footview.findViewById(R.id.dot);
        dotsTextView.start();
        loadingText = (TextView) footview.findViewById(R.id.text);
        listview.addFooterView(footview);
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (totalItemCount - (firstVisibleItem + visibleItemCount - 1) == 5) {
                    if (!isLoading) {
                        mPresenter.loadMore();
                    }
                }
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    showFooter();
                }
            }
        });
        refreshPager();
    }

    private void initFresh() {
        final StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, getResources().getDimensionPixelSize(R.dimen.y144), 0, 20);
        header.initWithString("SWALK");
        header.setTextColor(getResources().getColor(R.color.green1));
        header.setBackgroundColor(getResources().getColor(R.color.transparent));

        header.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));

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
                isRefreshing = true;
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.refreshData();
                        ptrFrameLayout.refreshComplete();
                        isRefreshing = false;
                    }
                }, 1500);
            }
        });
    }

    @Override
    public void refreshPager() {
        for (int i = 0; i <= 10; i++) {
            list.add("" + i);
        }
        message_adpter = new Message_adpter(this, 1, list);
        listview.setAdapter(message_adpter);
    }

    @Override
    public void autoRefresh() {
        if (ptrFrameLayout != null) {
            if (mPresenter.isNeedRefresh()) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.autoRefresh();
                    }
                }, 100);
            }
        }
    }

    @Override
    public void showNoData() {
        dotsTextView.stop();
        loadingText.setText("没有动态啦");
    }

    @Override
    public void showFooter() {
        dotsTextView.start();
        loadingText.setText("加载中");
    }

    @Override
    public void setRefreshState(boolean is) {
        isRefreshing = is;
    }

    @Override
    public boolean isRefreshing() {
        return isRefreshing;
    }

    @Override
    public void setLoadState(boolean is) {
        isLoading = is;
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void setmPresenter(@Nullable MessageNoticeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}

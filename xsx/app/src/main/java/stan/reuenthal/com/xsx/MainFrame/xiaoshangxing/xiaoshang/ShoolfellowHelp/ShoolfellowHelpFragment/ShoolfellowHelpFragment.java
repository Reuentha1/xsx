package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.ShoolfellowHelpFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.loadingview.DotsTextView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.PtrDefaultHandler;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.PtrFrameLayout;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.PtrHandler;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.StoreHouseHeader;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.MyShoolfellowHelp.MyShoolHelpFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.ShoolfellowHelpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by FengChaoQun
 * on 2016/7/20
 */
public class ShoolfellowHelpFragment extends BaseFragment implements ShoolHelpContract.View{

    public static final String TAG = BaseFragment.TAG + "-ShoolfellowHelpFragment";
    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.myState)
    TextView myState;
    @Bind(R.id.more)
    ImageView more;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.reflesh_layout)
    PtrFrameLayout ptrFrameLayout;

    private View mview;
    private shoolfellow_adpter adpter;
    private List<String> list = new ArrayList<String>();
    private View headview, footview;
    private DotsTextView dotsTextView;
    private TextView loadingText;

    private ShoolHelpContract.Presenter mPresenter;

    public static ShoolfellowHelpFragment newInstance() {
        return new ShoolfellowHelpFragment();
    }

    private boolean isRefreshing;
    private boolean isLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.frag_shoolfellowhelp, null);
        ButterKnife.bind(this, mview);
        setmPresenter(new ShoolHelpPresenter(this, getContext()));
        initFresh();
        initView();
        autoRefresh();
        return mview;
    }

    private void initView() {
        headview = View.inflate(getContext(), R.layout.headview_help_list, null);
        footview = View.inflate(getContext(), R.layout.footer, null);
        dotsTextView = (DotsTextView) footview.findViewById(R.id.dot);
        loadingText = (TextView) footview.findViewById(R.id.text);
        dotsTextView.start();
        listview.addHeaderView(headview);
        listview.addFooterView(footview);

        headview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRule();
            }
        });

        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (totalItemCount - (firstVisibleItem + visibleItemCount - 1) == 5) {
                    if (!isLoading) {
                        mPresenter.LoadMore();
                    }
                }
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    showFooter();
                }
            }
        });
    }

    private void initFresh() {
        final StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.setPadding(0, getResources().getDimensionPixelSize(R.dimen.y144), 0, 20);
        header.initWithString("SWALK");
        header.setTextColor(getResources().getColor(R.color.green1));
        header.setBackgroundColor(getResources().getColor(R.color.transparent));

        header.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));

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
                mPresenter.RefreshData();
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                        isRefreshing = false;
                    }
                }, 1500);
            }
        });
    }

    @Override
    public void showPublishMenu(View v) {

        View menu = View.inflate(getContext(), R.layout.popupmenu_pubulish, null);
        final PopupWindow popupWindow = new PopupWindow(menu, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(getContext().getResources().
                getDrawable(R.drawable.nothing));
        popupWindow.setAnimationStyle(R.style.popwindow_anim);

        menu.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int mShowMorePopupWindowWidth = menu.getMeasuredWidth();

        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);

        popupWindow.showAsDropDown(v,
                -mShowMorePopupWindowWidth + this.getResources().getDimensionPixelSize(R.dimen.x30) + v.getWidth(),
                3);

        View publish = menu.findViewById(R.id.publish);
        View published = menu.findViewById(R.id.published);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPublish();
                popupWindow.dismiss();
            }
        });
        published.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPublished();
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public void gotoPublish() {
        Intent intent=new Intent(getContext(), InputActivity.class);
        intent.putExtra(InputActivity.EDIT_STATE,InputActivity.SHOOLFELLOW_HELP);
        startActivity(intent);
    }

    @Override
    public void gotoPublished() {
        ShoolfellowHelpActivity activity = (ShoolfellowHelpActivity) getActivity();
        MyShoolHelpFragment fragment = activity.getMyShoolHelpFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .hide(this)
//                .add(R.id.main_fragment, fragment, MyShoolHelpFragment.TAG)
                .show(fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setRefreshState(boolean is) {
        isRefreshing = is;
    }

    @Override
    public void setLoadState(boolean is) {
        isLoading = is;
    }

    @Override
    public void refreshPager() {
        for (int i = 0; i <= 10; i++) {
            list.add("" + i);
        }
        adpter = new shoolfellow_adpter(getContext(), 1, list, this, (ShoolfellowHelpActivity) getActivity());
        listview.setAdapter(adpter);
    }

    @Override
    public void autoRefresh() {
        if (ptrFrameLayout != null) {
            if (mPresenter.isNeedRefresh()) {
                ptrFrameLayout.autoRefresh();
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
    public void clickOnRule() {
        showToast("公告规则");
    }

    @Override
    public void setmPresenter(@Nullable ShoolHelpContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.back, R.id.more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                getActivity().finish();
                break;
            case R.id.more:
                showPublishMenu(view);
                break;
        }
    }
}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.ShoolRewardFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.loadingview.DotsTextView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.PtrDefaultHandler;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.PtrFrameLayout;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.PtrHandler;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh.StoreHouseHeader;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.ShoolRewardActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by FengChaoQun
 * on 2016/7/20
 */
public class ShoolRewardFragment extends BaseFragment implements ShoolRewardContract.View {

    public static final String TAG = BaseFragment.TAG + "-ShoolRewardFragment";
    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.more)
    ImageView more;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.reflesh_layout)
    PtrFrameLayout ptrFrameLayout;

    private View mview;
    private shoolreward_adpter adpter;
    private List<String> list = new ArrayList<String>();
    private View headview, footview;
    private ShoolRewardContract.Presenter mPresenter;
    private DotsTextView dotsTextView;
    private TextView loadingText;

    public static ShoolRewardFragment newInstance() {
        return new ShoolRewardFragment();
    }

    private boolean isRefreshing;
    private boolean isLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.frag_shoolreward, null);
        ButterKnife.bind(this, mview);
        setmPresenter(new ShoolRewardPresenter(this, getContext()));
        refreshPager();
        mPresenter.isNeedRefresh();
        initFresh();
        initView();
        return mview;
    }

    private void initView() {
        headview = View.inflate(getContext(), R.layout.headview_help_list, null);
        footview = View.inflate(getContext(), R.layout.footer, null);
        dotsTextView = (DotsTextView) footview.findViewById(R.id.dot);
        dotsTextView.start();
        loadingText = (TextView) footview.findViewById(R.id.text);
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

    @Override
    public boolean isRefreshing() {
        return isRefreshing;
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void showPublishMenu(View v) {
        View menu = View.inflate(getContext(), R.layout.popupmenu_rewardpubulish, null);
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
        View collect=menu.findViewById(R.id.collect);
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
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCollect();
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public void gotoCollect() {
        ShoolRewardActivity activity = (ShoolRewardActivity) getActivity();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .hide(activity.getShoolRewardFragment())
                .show(activity.getCollectFragment())
                .hide(activity.getMyShoolRewardFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoPublish() {
        Intent intent=new Intent(getContext(), InputActivity.class);
        intent.putExtra(InputActivity.EDIT_STATE,InputActivity.SHOOL_REWARD);
        startActivity(intent);
    }

    @Override
    public void gotoPublished() {
        ShoolRewardActivity activity = (ShoolRewardActivity) getActivity();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .hide(activity.getShoolRewardFragment())
                .hide(activity.getCollectFragment())
                .show(activity.getMyShoolRewardFragment())
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
        adpter = new shoolreward_adpter(getContext(), 1, list, this, (ShoolRewardActivity) getActivity());
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
    public void showCollectDialog() {
        DialogUtils.DialogMenu2 dialogMenu2 = new DialogUtils.DialogMenu2(getContext());
        dialogMenu2.addMenuItem("收藏");
        dialogMenu2.setMenuListener(new DialogUtils.DialogMenu2.MenuListener() {
            @Override
            public void onItemSelected(int position, String item) {
                mPresenter.collect();
            }

            @Override
            public void onCancel() {

            }
        });
        dialogMenu2.initView();
        dialogMenu2.show();
        LocationUtil.bottom_FillWidth(getActivity(), dialogMenu2);
    }

    @Override
    public void noticeDialog(String message) {
        DialogUtils.Dialog_No_Button dialog_no_button = new DialogUtils.Dialog_No_Button(getActivity(), message);
        final Dialog alertDialog = dialog_no_button.create();
        alertDialog.show();
        LocationUtil.setWidth(getActivity(), alertDialog,
                getActivity().getResources().getDimensionPixelSize(R.dimen.x420));

        new Handler().postDelayed(new Runnable() {
            public void run() {
                alertDialog.dismiss();
            }
        }, 1000);
    }

    @Override
    public void setmPresenter(@Nullable ShoolRewardContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}

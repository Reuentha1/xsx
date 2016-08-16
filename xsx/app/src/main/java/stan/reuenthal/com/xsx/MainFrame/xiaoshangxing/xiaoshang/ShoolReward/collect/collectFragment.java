package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.collect;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;
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
 * on 2016/7/21
 */
public class collectFragment extends BaseFragment implements CollectContract.View {
    public static final String TAG = BaseFragment.TAG + "-collectFragment";

    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.reflesh_layout)
    PtrFrameLayout ptrFrameLayout;
    @Bind(R.id.hide_trasmit)
    ImageView hideTrasmit;
    @Bind(R.id.hide_delete)
    ImageView hideDelete;
    @Bind(R.id.hideMenu)
    RelativeLayout hideMenu;
    @Bind(R.id.no_content)
    TextView noContent;

    public static collectFragment newInstance() {
        return new collectFragment();
    }

    private collect_adpter adpter;
    private List<String> list = new ArrayList<String>();
    private View view;
    private ShoolRewardActivity activity;
    private CollectContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_myshoolreward, null);
        ButterKnife.bind(this, view);
        setmPresenter(new CollectPresenter(this,getContext()));
        initFresh();
        initView();
        return view;
    }

    private void initView() {
        for (int i = 0; i <= 10; i++) {
            list.add("" + i);
        }

        View view = new View(getContext());
        listview.addHeaderView(view);

        activity=(ShoolRewardActivity)getActivity();
        adpter = new collect_adpter(getContext(), 1, list, this,activity);
        listview.setAdapter(adpter);

        title.setText("收藏");
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
//                ptrFrameLayout.autoRefresh(true);
//            }
//        }, 100);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }
        });
    }

    public void showCollectDialog() {
        DialogUtils.DialogMenu2 dialogMenu2 = new DialogUtils.DialogMenu2(getContext());
        dialogMenu2.addMenuItem("取消收藏");
        dialogMenu2.setMenuListener(new DialogUtils.DialogMenu2.MenuListener() {
            @Override
            public void onItemSelected(int position, String item) {
                mPresenter.unCollect();
            }

            @Override
            public void onCancel() {

            }
        });
        dialogMenu2.initView();
        dialogMenu2.show();
        LocationUtil.bottom_FillWidth(getActivity(), dialogMenu2);
    }

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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void showHideMenu(boolean is) {
        ShoolRewardActivity activity = (ShoolRewardActivity) getActivity();
        if (is) {
            hideMenu.setVisibility(View.VISIBLE);
            activity.setCollect(true);
        } else {
            hideMenu.setVisibility(View.GONE);
            adpter.showSelectCircle(false);
            activity.setCollect(false);
        }
    }

    public void showDeleteSureDialog() {
        adpter.showSelectCircle(false);
        showHideMenu(false);

        DialogUtils.DialogMenu2 dialogMenu2 = new DialogUtils.DialogMenu2(getContext());
        dialogMenu2.addMenuItem("删除");
        dialogMenu2.setMenuListener(new DialogUtils.DialogMenu2.MenuListener() {
            @Override
            public void onItemSelected(int position, String item) {
                mPresenter.delete();
            }

            @Override
            public void onCancel() {

            }
        });
        dialogMenu2.initView();
        dialogMenu2.show();
        LocationUtil.bottom_FillWidth(getActivity(), dialogMenu2);
    }

    public void showNoContentText(boolean is) {
        if (is) {
            noContent.setVisibility(View.VISIBLE);
            listview.setVisibility(View.INVISIBLE);
        } else {
            noContent.setVisibility(View.INVISIBLE);
            listview.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setmPresenter(@Nullable CollectContract.Presenter presenter) {
        this.mPresenter=presenter;
    }

    @OnClick({R.id.back, R.id.hide_trasmit, R.id.hide_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                getFragmentManager().popBackStack();
                break;
            case R.id.hide_trasmit:
                adpter.showSelectCircle(false);
                showHideMenu(false);
                activity.gotoSelectPerson();
                break;
            case R.id.hide_delete:
                showDeleteSureDialog();
                break;
        }
    }

}

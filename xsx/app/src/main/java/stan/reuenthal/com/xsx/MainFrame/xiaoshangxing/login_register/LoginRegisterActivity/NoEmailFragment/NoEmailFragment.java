package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.NoEmailFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.AppealFragment.AppealFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginFragment.LoginFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginRegisterActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public class NoEmailFragment extends BaseFragment implements NoEmailContract.View, View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-NoEmailFragment";

    private View mView;
    private View back;
    private TextView appeal_by_self;
    private NoEmailContract.Presenter mPresenter;

    public static NoEmailFragment newInstance() {
        return new NoEmailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_no_email, container, false);
        mView = view;
        initView();
        setmPresenter(new NoEmailPresenter(this));
        return view;
    }

    private void initView() {
        back = mView.findViewById(R.id.back);
        back.setOnClickListener(this);
        appeal_by_self = (TextView) mView.findViewById(R.id.appeal_by_self);
        appeal_by_self.setOnClickListener(this);
    }

    @Override
    public void setmPresenter(@Nullable NoEmailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void gotoAppeal() {
        AppealFragment frag = ((LoginRegisterActivity) mActivity).getAppealFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.loginregisterContent, frag)
                .addToBackStack(AppealFragment.TAG)
                .commit();
    }

    @Override
    public String getPhoneNUmber() {
        return ((LoginRegisterActivity) getActivity()).getPhoneNumer();
    }

    @Override
    public void showNoNameVertifyDialog() {

        final DialogUtils.Dialog_Center dialogUtils = new DialogUtils.Dialog_Center(getActivity());
        Dialog alertDialog = dialogUtils.Message("你没有对账号进行实名认证,\n所以无法进行实名认证")
                .Button("取消", "前往注册")
                .MbuttonOnClick(new DialogUtils.Dialog_Center.buttonOnClick() {
                    @Override
                    public void onButton1() {
                        dialogUtils.close();
                    }

                    @Override
                    public void onButton2() {
                        dialogUtils.close();
                        LoginFragment frag = ((LoginRegisterActivity) mActivity).getLoginFragment();
                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                                        R.anim.slide_in_left, R.anim.slide_out_left)
                                .replace(R.id.loginregisterContent, frag)
                                .addToBackStack(LoginFragment.TAG)
                                .commit();
                    }
                }).create();
        alertDialog.show();
        LocationUtil.setWidth(getActivity(), alertDialog,
                getActivity().getResources().getDimensionPixelSize(R.dimen.x780));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                getFragmentManager().popBackStack();
                break;
            case R.id.appeal_by_self:
                mPresenter.clickOnAppeal();
                break;
        }
    }
}

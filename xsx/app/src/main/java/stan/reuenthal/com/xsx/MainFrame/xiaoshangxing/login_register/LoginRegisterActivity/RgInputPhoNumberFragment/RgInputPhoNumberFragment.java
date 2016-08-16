package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RgInputPhoNumberFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginFragment.LoginFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginRegisterActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RgInputVertifyCodeFragment.RgInputVertifyCodeFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;

/**
 * Created by FengChaoQun
 * on 2016/6/22
 */
public class RgInputPhoNumberFragment extends BaseFragment implements RgInputPhoNumContract.View, View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-RgInputPhoNumberFragment";

    private RgInputPhoNumContract.Presenter mPresenter;
    private View mView;
    private TextView tv_cancer;
    private EditText et_phoneNumber;
    private Button btn_register;

    public static RgInputPhoNumberFragment newInstance() {
        return new RgInputPhoNumberFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rg_input_pho_number, container, false);
        mView = view;
        initView();
        setmPresenter(new RgInputPhoNumPresenter(this, mContext, getActivity(), getActivity().getFragmentManager()));
        return view;
    }

    private void initView() {
        tv_cancer = (TextView) mView.findViewById(R.id.cancel);
        tv_cancer.setOnClickListener(this);
        btn_register = (Button) mView.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        et_phoneNumber = (EditText) mView.findViewById(R.id.et_account);
        et_phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.isContentOK();
            }
        });
    }

    @Override
    public void setButtonEnable(boolean enable) {
        if (enable) {
            btn_register.setEnabled(true);
            btn_register.setAlpha(1);
        } else {
            btn_register.setEnabled(false);
            btn_register.setAlpha(0.5f);
        }
    }

    @Override
    public String getPhoneNum() {
        return et_phoneNumber.getText().toString();
    }

    @Override
    public void showRegisteredDialog() {
        final DialogUtils.Dialog_Center dialogUtils = new DialogUtils.Dialog_Center(mActivity);
        final Dialog alertDialog = dialogUtils.Message("该手机号已经注册,是否直接\n登录校上行?")
                .Button("取消", "好").MbuttonOnClick(new DialogUtils.Dialog_Center.buttonOnClick() {
                    @Override
                    public void onButton1() {
                        dialogUtils.close();
                    }

                    @Override
                    public void onButton2() {
                        Intent intent = new Intent(mContext, LoginRegisterActivity.class);
                        intent.putExtra(LoginRegisterActivity.INTENT_TYPE, LoginRegisterActivity.LOGIN);
                        intent.putExtra(LoginFragment.LOGIN_WITH_NUMBER, getPhoneNum());
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }
                }).create();
        alertDialog.show();
        LocationUtil.setWidth(getActivity(), alertDialog,
                getActivity().getResources().getDimensionPixelSize(R.dimen.x780));
    }

    @Override
    public void showSureDialog() {
        final DialogUtils.Dialog_Center dialogUtils = new DialogUtils.Dialog_Center(mActivity);
        final Dialog alertDialog = dialogUtils.Title("确认手机号")
                .Message("我们将发送验证码短信到这个号码：\n" + getPhoneNum())
                .Button("取消", "好").MbuttonOnClick(new DialogUtils.Dialog_Center.buttonOnClick() {
                    @Override
                    public void onButton1() {
                        dialogUtils.close();
                    }

                    @Override
                    public void onButton2() {
                        dialogUtils.close();
                        mPresenter.sureSendVertifyCode();
                    }
                }).create();
        alertDialog.show();
        LocationUtil.setWidth(getActivity(), alertDialog,
                getActivity().getResources().getDimensionPixelSize(R.dimen.x780));
    }

    @Override
    public void setmPresenter(@Nullable RgInputPhoNumContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void gotoInputVertifyCode() {
        ((LoginRegisterActivity) getActivity()).setPhoneNumer(getPhoneNum());
        RgInputVertifyCodeFragment frag = ((LoginRegisterActivity) getActivity()).getRgInputVertifyCodeFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.loginregisterContent, frag)
                .addToBackStack(RgInputPhoNumberFragment.TAG)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                mPresenter.clickOnCancer();
                break;
            case R.id.btn_register:
                mPresenter.clickOnRegister();
                break;
        }
    }
}

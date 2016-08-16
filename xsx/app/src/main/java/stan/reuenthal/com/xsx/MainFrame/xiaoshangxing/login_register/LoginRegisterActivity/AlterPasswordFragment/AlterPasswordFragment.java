package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.AlterPasswordFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginFragment.LoginFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginRegisterActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public class AlterPasswordFragment extends BaseFragment implements AlterPasswordContract.View, View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-AlterPasswordFragment";

    private View mView;
    private AlterPasswordContract.Presenter mPresenter;
    private TextView cancel;
    private TextView complete;
    private EditText password1, password2;
    private TextView notice, tv_phonenumber;

    public static AlterPasswordFragment newInstance() {
        return new AlterPasswordFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_alter_password, container, false);
        mView = view;
        setmPresenter(new AlterPasswordPresenter(this));
        initView();
        return view;
    }

    private void initView() {
        cancel = (TextView) mView.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        complete = (TextView) mView.findViewById(R.id.complete);
        complete.setOnClickListener(this);
        password1 = (EditText) mView.findViewById(R.id.et_password);
        password2 = (EditText) mView.findViewById(R.id.et_password2);
        notice = (TextView) mView.findViewById(R.id.notice);
        tv_phonenumber = (TextView) mView.findViewById(R.id.tv_phoneNumber);

        TextWatcher textWatcher = new TextWatcher() {
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
        };

        password1.addTextChangedListener(textWatcher);
        password2.addTextChangedListener(textWatcher);

        setPhoneNumber(((LoginRegisterActivity) getActivity()).getPhoneNumer());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                clickOnCancle();
                break;
            case R.id.complete:
                mPresenter.clickOnComplete();
                break;
        }
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        notice.setText(notice.getText().toString().replace("+86", phoneNumber));
        tv_phonenumber.setText(phoneNumber);
    }

    @Override
    public void showPasswordDiffer() {
        final DialogUtils.Dialog_Center dialogUtils = new DialogUtils.Dialog_Center(getActivity());
        final Dialog alertDialog = dialogUtils.Message("两次输入的密码不一致\n请重新输入")
                .Button("确定")
                .MbuttonOnClick(new DialogUtils.Dialog_Center.buttonOnClick() {
                    @Override
                    public void onButton1() {
                        dialogUtils.close();
                    }

                    @Override
                    public void onButton2() {

                    }
                })
                .create();
        alertDialog.show();
        LocationUtil.setWidth(getActivity(), alertDialog,
                getActivity().getResources().getDimensionPixelSize(R.dimen.x780));
    }

    @Override
    public void clickOnCancle() {
        getActivity().finish();
    }

    @Override
    public void setCompleteState(boolean state) {
        if (state) {
            complete.setEnabled(true);
            complete.setAlpha(1);
        } else {
            complete.setEnabled(false);
            complete.setAlpha(0.5f);
        }
    }

    @Override
    public String getPassword1() {
        return password1.getText().toString();
    }

    @Override
    public String getPassword2() {
        return password2.getText().toString();
    }

    @Override
    public void gotoLogin() {
        Intent intent = new Intent(getContext(), LoginRegisterActivity.class);
        intent.putExtra(LoginRegisterActivity.INTENT_TYPE, LoginRegisterActivity.LOGIN);
        intent.putExtra(LoginFragment.LOGIN_WITH_NUMBER, tv_phonenumber.getText().toString());
        getActivity().startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showAlterSuccess() {
        DialogUtils.Dialog_No_Button dialog_no_button = new DialogUtils.Dialog_No_Button(getActivity(), "已设置密码");
        final Dialog alertDialog = dialog_no_button.create();
        alertDialog.show();
        LocationUtil.setWidth(getActivity(), alertDialog,
                getActivity().getResources().getDimensionPixelSize(R.dimen.x420));

        alertDialog.setCancelable(false);
        alertDialog.show();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                alertDialog.dismiss();
                gotoLogin();
            }
        }, 1000);
    }

    @Override
    public void setmPresenter(@Nullable AlterPasswordContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginRegisterActivity) getActivity()).setIs_finish(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        ((LoginRegisterActivity) getActivity()).setIs_finish(false);
    }
}

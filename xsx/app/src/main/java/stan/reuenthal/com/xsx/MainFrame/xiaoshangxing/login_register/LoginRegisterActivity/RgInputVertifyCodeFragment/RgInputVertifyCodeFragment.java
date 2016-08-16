package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RgInputVertifyCodeFragment;

import android.app.Dialog;
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
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.AlterPasswordFragment.AlterPasswordFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginRegisterActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SetPasswordFragment.SetPasswordFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;

/**
 * Created by FengChaoQun
 * on 2016/6/23
 */
public class RgInputVertifyCodeFragment extends BaseFragment implements RgInputVertifyCodeContract.View, View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-RgInputVertifyCodeFragment";

    private View mView;
    private View back;
    private TextView tv_phoneNumber;
    private EditText et_vertify_code;
    private Button submit;
    private TextView remain_time;

    private RgInputVertifyCodeContract.Presenter mPresenter;

    public static RgInputVertifyCodeFragment newInstance() {
        return new RgInputVertifyCodeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_rg_input_vertifycode, container, false);
        mView = view;
        initView();
        setmPresenter(new RgInputVertifyCodePresenter(this));
        mPresenter.startCountTime();
        return view;
    }

    private void initView() {
        back = mView.findViewById(R.id.back);
        back.setOnClickListener(this);
        tv_phoneNumber = (TextView) mView.findViewById(R.id.phone_number);
        et_vertify_code = (EditText) mView.findViewById(R.id.et_vertify_code);
        et_vertify_code.setText("");
        submit = (Button) mView.findViewById(R.id.btn_submit);
        submit.setOnClickListener(this);
        remain_time = (TextView) mView.findViewById(R.id.remain_time);
        remain_time.setOnClickListener(this);

        setPhoneNumber(((LoginRegisterActivity) getActivity()).getPhoneNumer());

        et_vertify_code.addTextChangedListener(new TextWatcher() {
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
    public void setmPresenter(@Nullable RgInputVertifyCodeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public String getPhone() {
        return ((LoginRegisterActivity) getActivity()).getPhoneNumer();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        tv_phoneNumber.setText(phoneNumber);
    }

    @Override
    public String getVertifyCode() {
        return et_vertify_code.getText().toString();
    }

    @Override
    public void setButtonEnable(boolean enable) {
        if (enable) {
            submit.setEnabled(true);
            submit.setAlpha(1);
        } else {
            submit.setEnabled(false);
            submit.setAlpha(0.5f);
        }
    }

    @Override
    public void setRemainTimeEnable(boolean enable) {
        if (enable) {
            remain_time.setEnabled(true);
        } else {
            remain_time.setEnabled(false);
            remain_time.setTextColor(getResources().getColor(R.color.g0));
        }
    }

    @Override
    public void setRemainTime(String text) {
        remain_time.setText(text);
    }

    @Override
    public void setRemainTimeChange() {
        remain_time.setText("收不到验证码?");
        remain_time.setTextColor(getResources().getColor(R.color.blue1));
        setRemainTimeEnable(true);
    }

    @Override
    public void gotoSetPassword() {
        SetPasswordFragment frag = ((LoginRegisterActivity) getActivity()).getSetPasswordFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.loginregisterContent, frag)
                .addToBackStack(SetPasswordFragment.TAG)
                .commit();
    }

    @Override
    public void gotoResetPassword() {
        AlterPasswordFragment frag = ((LoginRegisterActivity) getActivity()).getAlterPasswordFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.loginregisterContent, frag)
                .addToBackStack(AlterPasswordFragment.TAG)
                .commit();
    }

    @Override
    public void clickOnBack() {
        final DialogUtils.Dialog_Center dialogUtils = new DialogUtils.Dialog_Center(getActivity());
        Dialog alertDialog = dialogUtils.Message("验证码短信可能略有延迟,确定\n返回并重新开始?")
                .Button("等待", "返回")
                .MbuttonOnClick(new DialogUtils.Dialog_Center.buttonOnClick() {
                    @Override
                    public void onButton1() {
                        dialogUtils.close();
                    }

                    @Override
                    public void onButton2() {
                        dialogUtils.close();
                        getFragmentManager().popBackStack();
                    }
                }).create();
        alertDialog.show();
        LocationUtil.setWidth(getActivity(), alertDialog,
                getActivity().getResources().getDimensionPixelSize(R.dimen.x780));
    }

    @Override
    public void showFailDialog() {
        final DialogUtils.Dialog_Center dialogUtils = new DialogUtils.Dialog_Center(getActivity());
        Dialog alertDialog = dialogUtils.Message("验证码不正确，请重新输入。")
                .Button("确定")
                .MbuttonOnClick(new DialogUtils.Dialog_Center.buttonOnClick() {
                    @Override
                    public void onButton1() {
                        dialogUtils.close();
                    }

                    @Override
                    public void onButton2() {

                    }
                }).create();
        alertDialog.show();
        LocationUtil.setWidth(getActivity(), alertDialog,
                getActivity().getResources().getDimensionPixelSize(R.dimen.x780));
    }

    @Override
    public void gotoWhere() {
        boolean is = ((LoginRegisterActivity) getActivity()).get_is_RetrieveByMesFragment();
        if (is) {
            gotoResetPassword();
        } else {
            gotoSetPassword();
        }
    }

    @Override
    public void showNoDialogMenu() {
        DialogUtils.DialogMenu mActionSheet = new DialogUtils.DialogMenu(getActivity());
        mActionSheet.addMenuItem("重新获取验证码");
        mActionSheet.show();
        LocationUtil.bottom_FillWidth(getActivity(), mActionSheet);
        mActionSheet.setMenuListener(new DialogUtils.DialogMenu.MenuListener() {
            @Override
            public void onItemSelected(int position, String item) {
                switch (position) {
                    case 0:
                        mPresenter.stopCountTime();
                        mPresenter.startCountTime();
                        break;
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                clickOnBack();
                break;
            case R.id.btn_submit:
                mPresenter.clickOnSubmit();
                break;
            case R.id.remain_time:
                mPresenter.noReceiveCode();
                break;
        }
    }

    @Override
    public void onResume() {
        ((LoginRegisterActivity) getActivity()).setIs_RgInputVertifyCodeFragment(true);
        super.onResume();
    }


    @Override
    public void onStop() {
        mPresenter.stopCountTime();
        ((LoginRegisterActivity) getActivity()).setIs_RgInputVertifyCodeFragment(false);
        ((LoginRegisterActivity) getActivity()).setIs_RetrieveByMesFragment(false);
        /*
        **describe:防止缓存
        */
        ((LoginRegisterActivity) getActivity()).resetRgInputVertifyCodeFragment();
        super.onPause();
    }

}

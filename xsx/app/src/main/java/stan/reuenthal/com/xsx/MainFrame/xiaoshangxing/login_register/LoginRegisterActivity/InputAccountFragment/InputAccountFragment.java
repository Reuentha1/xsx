package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.InputAccountFragment;

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

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginRegisterActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.NoEmailFragment.NoEmailFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RgInputPhoNumberFragment.RgInputPhoNumberFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SendEmailFragment.SendEmailFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public class InputAccountFragment extends BaseFragment implements InputAccountContract.View, View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-InputAccountFragment";

    private View mView;
    private InputAccountContract.Presenter mPresenter;

    private View back;
    private EditText account;
    private Button makeSure;

    public static InputAccountFragment newInstance() {
        return new InputAccountFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_input_email, container, false);
        mView = view;
        setmPresenter(new InputAccountPresenter(this));
        initView();
        return view;
    }

    private void initView() {
        back = mView.findViewById(R.id.back);
        back.setOnClickListener(this);
        makeSure = (Button) mView.findViewById(R.id.btn_makesure);
        makeSure.setOnClickListener(this);
        account = (EditText) mView.findViewById(R.id.et_account);
        account.addTextChangedListener(new TextWatcher() {
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
    public void setmPresenter(@Nullable InputAccountContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setButtonState(boolean state) {
        if (state) {
            makeSure.setEnabled(true);
            makeSure.setAlpha(1);
        } else {
            makeSure.setEnabled(false);
            makeSure.setAlpha(0.5f);
        }
    }

    @Override
    public void gotoSendedEmail() {
        SendEmailFragment frag = ((LoginRegisterActivity) mActivity).getSendEmailFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.loginregisterContent, frag)
                .addToBackStack(SendEmailFragment.TAG)
                .commit();
    }

    @Override
    public void gotoNoEmail() {
        NoEmailFragment frag = ((LoginRegisterActivity) mActivity).getNoEmailFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.loginregisterContent, frag)
                .addToBackStack(NoEmailFragment.TAG)
                .commit();
    }

    @Override
    public void savePhoneNumber() {
        ((LoginRegisterActivity) getActivity()).setPhoneNumer(getPhoneNumber());
    }

    @Override
    public void showNoRegister() {
        final DialogUtils.Dialog_Center dialog_center=new DialogUtils.Dialog_Center(getActivity());
        Dialog dialog= dialog_center.Message("该手机号尚未注册,是否\n前往注册?").
                Button("取消","去注册")
                .MbuttonOnClick(new DialogUtils.Dialog_Center.buttonOnClick() {
                    @Override
                    public void onButton1() {
                        dialog_center.close();
                    }

                    @Override
                    public void onButton2() {
                        dialog_center.close();
                        gotoRegister();
                    }
                }).create();
        dialog.show();
        LocationUtil.setWidth(getActivity(), dialog,
                getActivity().getResources().getDimensionPixelSize(R.dimen.x780));
    }

    @Override
    public void gotoRegister() {
        RgInputPhoNumberFragment frag = ((LoginRegisterActivity) getActivity()).getRgInputPhoNumberFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.loginregisterContent, frag)
                .addToBackStack(RgInputPhoNumberFragment.TAG)
                .commit();
    }

    @Override
    public String getPhoneNumber() {
        return account.getText().toString();
    }

    @Override
    public void clickOnBack() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                clickOnBack();
                break;
            case R.id.btn_makesure:
                mPresenter.clickOnButton();
                break;
        }
    }
}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RetrieveByMesFragment;

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
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RgInputPhoNumberFragment.RgInputPhoNumberFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RgInputVertifyCodeFragment.RgInputVertifyCodeFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public class RetrieveByMesFragment extends BaseFragment implements RetrieveByMesContract.View, View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-RetrieveByMesFragment";

    private View mview;
    private RetrieveByMesContract.Presenter mPresenter;

    private View back;
    private EditText et_phoneNumber;
    private Button btn_next;


    public static RetrieveByMesFragment newInstance() {
        return new RetrieveByMesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_retieve_by_mess, container, false);
        mview = view;
        setmPresenter(new RetrieveByMesPresenter(this));
        initView();
        return view;
    }

    private void initView() {
        back = mview.findViewById(R.id.back);
        back.setOnClickListener(this);
        et_phoneNumber = (EditText) mview.findViewById(R.id.et_phoneNumber);
        btn_next = (Button) mview.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

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
    public void showSure() {
        final DialogUtils.Dialog_Center dialogUtils = new DialogUtils.Dialog_Center(mActivity);
        final Dialog alertDialog = dialogUtils.Title("确认手机号")
                .Message("我们将发送验证码短信到这个号码：\n" + getPhoneNumber())
                .Button("取消", "好").MbuttonOnClick(new DialogUtils.Dialog_Center.buttonOnClick() {
                    @Override
                    public void onButton1() {
                        dialogUtils.close();
                    }

                    @Override
                    public void onButton2() {
                        dialogUtils.close();
//                        //test
//                        if (getPhoneNumber().equals("88888888888")){
//
//                        }else {
//                            showUnRegiter();
//                        }
                        mPresenter.clickOnSure();

                    }
                }).create();
        alertDialog.show();
        LocationUtil.setWidth(getActivity(), alertDialog,
                getActivity().getResources().getDimensionPixelSize(R.dimen.x780));
    }

    @Override
    public void gotoInputCode() {
        ((LoginRegisterActivity) getActivity()).setPhoneNumer(getPhoneNumber());
        RgInputVertifyCodeFragment frag = ((LoginRegisterActivity) getActivity()).getRgInputVertifyCodeFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.loginregisterContent, frag)
                .addToBackStack(RgInputPhoNumberFragment.TAG)
                .commit();
    }

    @Override
    public void gotoRegiter() {
        RgInputPhoNumberFragment frag = ((LoginRegisterActivity) getActivity()).getRgInputPhoNumberFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.loginregisterContent, frag)
                .addToBackStack(RgInputPhoNumberFragment.TAG)
                .commit();
    }

    @Override
    public void showUnRegiter() {
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
                        gotoRegiter();
                    }
                }).create();
        dialog.show();
        LocationUtil.setWidth(getActivity(), dialog,
                getActivity().getResources().getDimensionPixelSize(R.dimen.x780));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                back();
                break;
            case R.id.btn_next:
                mPresenter.clickOnNext();
                break;
        }
    }

    @Override
    public String getPhoneNumber() {
        return et_phoneNumber.getText().toString();
    }

    @Override
    public void back() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void setButtonState(boolean state) {
        if (state) {
            btn_next.setEnabled(true);
            btn_next.setAlpha(1);
        } else {
            btn_next.setEnabled(false);
            btn_next.setAlpha(0.5f);
        }
    }

    @Override
    public void setmPresenter(@Nullable RetrieveByMesContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginRegisterActivity) getActivity()).setIs_RetrieveByMesFragment(true);
    }

    @Override
    public void onStop() {
        ((LoginRegisterActivity) getActivity()).setIs_RetrieveByMesFragment(false);
        super.onStop();
    }
}

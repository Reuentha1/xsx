package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SchoolNoOpenFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginRegisterActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;

/**
 * Created by FengChaoQun
 * on 2016/6/25
 */
public class SchoolNoOpenFragment extends BaseFragment implements View.OnClickListener, SchoolNoOpenContract.View {
    public static final String TAG = BaseFragment.TAG + "-SchoolNoOpenFragment";

    private View mView;
    private TextView cancle;
    private TextView school_notice;
    private Button btn_appeal;

    private SchoolNoOpenContract.Presenter mPresenter;


    public static SchoolNoOpenFragment newInstance() {
        return new SchoolNoOpenFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_school_no_open, container, false);
        mView = view;
        setmPresenter(new SchoolNoOpenPresenter(this));
        initView();
        return view;
    }

    private void initView() {
        cancle = (TextView) mView.findViewById(R.id.cancel);
        cancle.setOnClickListener(this);
        school_notice = (TextView) mView.findViewById(R.id.school_notice);
        btn_appeal = (Button) mView.findViewById(R.id.appeal_open);
        btn_appeal.setOnClickListener(this);

        String str = ((LoginRegisterActivity) getActivity()).getSchool();
        setSchool(str);
    }

    @Override
    public void setSchool(String school) {
        school_notice.setText(school + "暂未开通");
    }

    @Override
    public void clickOnCancle() {
        getActivity().finish();
    }

    @Override
    public void setmPresenter(@Nullable SchoolNoOpenContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showSuccess() {
        DialogUtils.Dialog_No_Button dialog_no_button = new DialogUtils.Dialog_No_Button(getActivity(), "已提交申请");
        final Dialog alertDialog = dialog_no_button.create();
        alertDialog.show();
        LocationUtil.setWidth(getActivity(), alertDialog,
                getActivity().getResources().getDimensionPixelSize(R.dimen.x420));

        new Handler().postDelayed(new Runnable() {
            public void run() {
                alertDialog.dismiss();
                getActivity().finish();
            }
        }, 1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                clickOnCancle();
                break;
            case R.id.appeal_open:
                mPresenter.clickOnButton();
        }
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

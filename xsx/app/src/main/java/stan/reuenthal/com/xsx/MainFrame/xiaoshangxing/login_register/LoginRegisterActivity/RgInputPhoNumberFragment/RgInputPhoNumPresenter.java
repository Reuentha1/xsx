package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RgInputPhoNumberFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/6/23
 */
public class RgInputPhoNumPresenter implements RgInputPhoNumContract.Presenter {
    private RgInputPhoNumContract.View mView;
    private Context context;
    private Activity activity;
    private FragmentManager mFragmentManager;

    public RgInputPhoNumPresenter(RgInputPhoNumContract.View mView, Context context,
                                  Activity activity, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.context = context;
        this.activity = activity;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public void isContentOK() {
        if (mView.getPhoneNum().length() == 11) {
            mView.setButtonEnable(true);
        } else {
            mView.setButtonEnable(false);
        }
    }

    @Override
    public void clickOnRegister() {
        if (mView.getPhoneNum().equals("88888888888")) {
            mView.showRegisteredDialog();
        } else {
            mView.showSureDialog();
        }
    }

    @Override
    public void sureSendVertifyCode() {
        mView.gotoInputVertifyCode();
    }

    @Override
    public void clickOnCancer() {
        activity.finish();
    }
}

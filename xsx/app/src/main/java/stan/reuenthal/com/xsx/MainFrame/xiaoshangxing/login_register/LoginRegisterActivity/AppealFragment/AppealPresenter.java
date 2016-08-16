package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.AppealFragment;

import android.text.TextUtils;

/**
 * Created by FengChaoQun
 * on 2016/6/25
 */
public class AppealPresenter implements AppealContract.Presenter {
    private AppealContract.View mView;

    public AppealPresenter(AppealContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void isContentOK() {
        if (TextUtils.isEmpty(mView.getEC()) || TextUtils.isEmpty(mView.getGoSchoolTime()) ||
                TextUtils.isEmpty(mView.getID()) || TextUtils.isEmpty(mView.getName()) ||
                TextUtils.isEmpty(mView.getPhoneNumber()) || TextUtils.isEmpty(mView.getRegisterTime()) ||
                TextUtils.isEmpty(mView.getSchool()) || TextUtils.isEmpty(mView.getStudentID())
                ) {
            mView.setButtonState(false);
        } else {
            mView.setButtonState(true);
        }
    }

    @Override
    public void clickOnAppeal() {
        mView.gotoAppealComplete();
    }
}

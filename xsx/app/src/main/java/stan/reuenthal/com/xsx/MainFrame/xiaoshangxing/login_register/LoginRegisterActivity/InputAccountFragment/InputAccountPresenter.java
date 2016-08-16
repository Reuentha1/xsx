package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.InputAccountFragment;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public class InputAccountPresenter implements InputAccountContract.Presenter {
    private InputAccountContract.View mView;

    public InputAccountPresenter(InputAccountContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void isContentOK() {
        if (mView.getPhoneNumber().length() == 11) {
            mView.setButtonState(true);
        } else {
            mView.setButtonState(false);
        }
    }

    @Override
    public void clickOnButton() {
        mView.savePhoneNumber();
        if (mView.getPhoneNumber().equals("88888888888")) {
            mView.gotoSendedEmail();
        } else {
            mView.gotoNoEmail();
        }
    }
}

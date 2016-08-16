package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RetrieveByMesFragment;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public class RetrieveByMesPresenter implements RetrieveByMesContract.Presenter {
    private RetrieveByMesContract.View mView;

    public RetrieveByMesPresenter(RetrieveByMesContract.View mView) {
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
    public void clickOnNext() {
        mView.showSure();
    }

    @Override
    public void clickOnSure() {
        if (true){
            mView.gotoInputCode();
        }else {
            mView.showUnRegiter();
        }
    }
}

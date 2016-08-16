package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.NoEmailFragment;

/**
 * Created by FengChaoQun
 * on 2016/6/25
 */
public class NoEmailPresenter implements NoEmailContract.Presenter {
    private NoEmailContract.View mView;

    public NoEmailPresenter(NoEmailContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void clickOnAppeal() {
        if (mView.getPhoneNUmber().equals("88888888887")) {
            mView.gotoAppeal();
        } else {
            mView.showNoNameVertifyDialog();
        }
    }
}

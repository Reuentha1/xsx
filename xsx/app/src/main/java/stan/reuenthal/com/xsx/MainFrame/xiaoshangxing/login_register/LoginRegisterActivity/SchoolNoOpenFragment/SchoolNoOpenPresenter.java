package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SchoolNoOpenFragment;

/**
 * Created by FengChaoQun
 * on 2016/6/25
 */
public class SchoolNoOpenPresenter implements SchoolNoOpenContract.Presenter {
    private SchoolNoOpenContract.View mView;

    public SchoolNoOpenPresenter(SchoolNoOpenContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void clickOnButton() {
        mView.showSuccess();
    }
}

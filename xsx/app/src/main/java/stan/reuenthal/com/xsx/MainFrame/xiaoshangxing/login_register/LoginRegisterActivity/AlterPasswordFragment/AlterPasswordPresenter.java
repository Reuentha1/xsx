package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.AlterPasswordFragment;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public class AlterPasswordPresenter implements AlterPasswordContract.Presenter {
    private AlterPasswordContract.View mView;

    public AlterPasswordPresenter(AlterPasswordContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void isContentOK() {
        if (mView.getPassword1().length() >= 8 && mView.getPassword2().length() >= 8) {
            mView.setCompleteState(true);
        } else {
            mView.setCompleteState(false);
        }
    }

    @Override
    public void clickOnComplete() {
        if (mView.getPassword1().equals(mView.getPassword2())) {
            mView.showAlterSuccess();
        } else {
            mView.showPasswordDiffer();
        }
    }
}

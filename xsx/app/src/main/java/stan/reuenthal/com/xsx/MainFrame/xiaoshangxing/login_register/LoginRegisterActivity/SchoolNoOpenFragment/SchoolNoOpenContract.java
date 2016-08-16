package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SchoolNoOpenFragment;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/6/25
 */
public interface SchoolNoOpenContract {

    interface View extends IBaseView<Presenter> {
        void clickOnCancle();

        void setSchool(String school);

        void showSuccess();
    }

    interface Presenter extends IBasePresenter {
        void clickOnButton();
    }
}

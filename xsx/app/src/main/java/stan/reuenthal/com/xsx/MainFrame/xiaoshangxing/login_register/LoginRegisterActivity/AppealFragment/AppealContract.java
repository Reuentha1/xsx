package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.AppealFragment;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/6/25
 */
public interface AppealContract {

    interface View extends IBaseView<Presenter> {
        void clickOnBack();

        void setButtonState(boolean state);

        void gotoAppealComplete();

        String getName();

        String getPhoneNumber();

        String getID();

        String getSchool();

        String getStudentID();

        String getEC();

        String getGoSchoolTime();

        String getRegisterTime();
    }

    interface Presenter extends IBasePresenter {
        void isContentOK();

        void clickOnAppeal();
    }
}

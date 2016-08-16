package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.InputAccountFragment;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public interface InputAccountContract {

    interface View extends IBaseView<Presenter> {
        void clickOnBack();

        String getPhoneNumber();

        void setButtonState(boolean state);

        /*
        **describe:跳转到发送邮箱界面
        */
        void gotoSendedEmail();

        /*
       **describe:跳转到未绑定邮箱界面
       */
        void gotoNoEmail();

        /*
       **describe:储存号码
       */
        void savePhoneNumber();

        /*
        **describe:弹出未注册对话框
        */
        void showNoRegister();

        /*
        **describe:跳转到注册界面
        */
        void gotoRegister();

    }

    interface Presenter extends IBasePresenter {
        void isContentOK();

        void clickOnButton();
    }
}

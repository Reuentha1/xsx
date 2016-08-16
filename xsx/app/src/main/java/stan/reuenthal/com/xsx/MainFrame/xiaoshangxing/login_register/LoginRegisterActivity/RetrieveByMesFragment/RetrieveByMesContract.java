package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RetrieveByMesFragment;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public interface RetrieveByMesContract {

    interface View extends IBaseView<Presenter> {
        /*
        **describe:返回
        */
        void back();

        /*
        **describe:获取手机号
        */
        String getPhoneNumber();

        /*
        **describe:设置按钮状态
        */
        void setButtonState(boolean state);

        /*
        **describe:弹出确认对话框
        */
        void showSure();

        /*
        **describe:前往输入验证码界面
        */
        void gotoInputCode();

        /*
        **describe:弹出未注册对话框
        */
        void showUnRegiter();

        /*
        **describe:跳转到注册界面
        */
        void gotoRegiter();
    }

    interface Presenter extends IBasePresenter {
        void isContentOK();

        void clickOnNext();

        void clickOnSure();

    }
}

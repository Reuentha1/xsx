package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.NoEmailFragment;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/6/25
 */
public interface NoEmailContract {

    interface View extends IBaseView<Presenter> {
        /*
        **describe:弹出未实名认证对话框
        */
        void showNoNameVertifyDialog();

        /*
        **describe:跳转到申诉界面
        */
        void gotoAppeal();

        /*
        **describe:获取手机号码
        */
        String getPhoneNUmber();
    }

    interface Presenter extends IBasePresenter {

        void clickOnAppeal();
    }
}

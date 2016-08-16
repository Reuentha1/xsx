package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RgInputPhoNumberFragment;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/6/22
 */
public interface RgInputPhoNumContract {

    interface View extends IBaseView<Presenter> {
        /*
        **describe:设置按钮可否点击
        */
        void setButtonEnable(boolean enable);

        /*
        **describe:获取手机号
        */
        String getPhoneNum();

        /*
        **describe:弹出该账号已注册对话框
        */
        void showRegisteredDialog();

        /*
        **describe:弹出确认对话框
        */
        void showSureDialog();

        /*
        **describe:前往输入验证码界面
        */
        void gotoInputVertifyCode();

    }

    interface Presenter extends IBasePresenter {
        /*
        **describe:内容是否合法
        */
        void isContentOK();

        /*
        **describe:点击注册按钮
        */
        void clickOnRegister();

        /*
        **describe:确定发送验证码
        */
        void sureSendVertifyCode();

        /*
        **describe:点击取消按钮
        */
        void clickOnCancer();

    }
}

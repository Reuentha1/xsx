package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RgInputVertifyCodeFragment;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/6/23
 */
public interface RgInputVertifyCodeContract {

    interface View extends IBaseView<Presenter> {

        /*
        **describe:设置手机号
        */
        void setPhoneNumber(String phoneNumber);

        /*
        **describe:获取验证码
        */
        String getVertifyCode();

        /*
        **describe:设置提交按钮点击状态
        */
        void setButtonEnable(boolean enable);

        /*
        **describe:设置剩余时间
        */
        void setRemainTime(String text);

        /*
        **describe:设置剩余时间点击状态
        */
        void setRemainTimeEnable(boolean enable);

        /*
        **describe:设置剩余时间变为收不到验证码?
        */
        void setRemainTimeChange();

        /*
        **describe:点击返回按钮
        */
        void clickOnBack();

        /*
       **describe:弹出验证码错误对话框
       */
        void showFailDialog();

        /*
       **describe:弹出收不到验证码菜单
       */
        void showNoDialogMenu();

        /*
      **describe:跳转到设置密码界面
      */
        void gotoSetPassword();

        /*
        **describe:跳转到重置密码界面
         */
        void gotoResetPassword();

        /*
        **describe:判断跳转到哪个界面
         */
        void gotoWhere();

        /*
        **describe:获取手机号码
        */
        String getPhone();

    }

    interface Presenter extends IBasePresenter {

        /*
        **describe:点击提交按钮
        */
        void clickOnSubmit();

        /*
        **describe:开始计时
        */
        void startCountTime();

        /*
        **describe:结束计时
        */
        void stopCountTime();

        /*
        **describe:输入内容是否完整
        */
        void isContentOK();

        /*
        **describe:点击收不到验证码
        */
        void noReceiveCode();
    }
}

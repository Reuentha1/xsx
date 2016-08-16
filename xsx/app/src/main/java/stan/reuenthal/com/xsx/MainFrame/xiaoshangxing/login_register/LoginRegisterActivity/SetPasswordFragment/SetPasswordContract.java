package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SetPasswordFragment;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public interface SetPasswordContract {

    interface View extends IBaseView<Presenter> {
        /*
        **describe:点击取消
        */
        void clickOnCancle();

        /*
        **describe:显示隐藏密码
        */
        void showOrHidePassword(boolean showOrHide);

        /*
        **describe:获取密码
        */
        String getPassword();

        /*
        **describe:设置按钮状态
        */
        void setButtonState(boolean state);

        /*
        **describe:弹出注册成功
        */
        void showRegisterSuccess();
        /*
        **describe:获取电话号码
        */
        String getPhone();
        /*
       **describe:跳转到选择学校界面
       */
//        void gotoSelectSchool ();
    }

    interface Presenter extends IBasePresenter {
        /*
        **describe:判断输入内容是否合法
        */
        void isContentOK();

        /*
        **describe:点击完成注册按钮
        */
        void clickOnCompleteButton();
    }
}

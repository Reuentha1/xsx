package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.AlterPasswordFragment;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public interface AlterPasswordContract {

    interface View extends IBaseView<Presenter> {
        void clickOnCancle();

        /*
        **describe:设置完成按钮状态
        */
        void setCompleteState(boolean state);

        /*
        **describe:设置手机号码
        */
        void setPhoneNumber(String phoneNumber);

        /*
        **describe:获取密码
        */
        String getPassword1();

        String getPassword2();

        /*
        **describe:跳转到登录界面
        */
        void gotoLogin();

        /*
       **describe:弹出密码不一致对话框
       */
        void showPasswordDiffer();

        /*
      **describe:弹出修改成功对话框
      */
        void showAlterSuccess();
    }

    interface Presenter extends IBasePresenter {
        void isContentOK();

        void clickOnComplete();
    }
}

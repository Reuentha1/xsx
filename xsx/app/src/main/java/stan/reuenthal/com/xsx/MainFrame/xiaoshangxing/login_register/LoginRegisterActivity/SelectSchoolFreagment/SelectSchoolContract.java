package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SelectSchoolFreagment;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/6/25
 */
public interface SelectSchoolContract {

    interface View extends IBaseView<Presenter> {
        void clickOnSerch();

        void clickOnCurrent();

        void gotoAppeal();

        void gotoMain();

        /*
        **describe:设置listview的Adapter
        */
        void setAdapter(String[] arrayList);

        void setCurrentSchool(String school);
    }

    interface Presenter extends IBasePresenter {
        void clickOnReflesh();

        void isSchoolAccess(String name);

        /*
        **describe:加载数据
        */
        void refreshList();
    }

}

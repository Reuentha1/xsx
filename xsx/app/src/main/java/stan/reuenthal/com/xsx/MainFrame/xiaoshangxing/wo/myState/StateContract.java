package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/8/5
 */
public interface StateContract {

    interface View extends IBaseView<Presenter> {
        /*
        **describe:判断是自己的动态还是别人的
        */
        void typeOfState();
        /*
        **describe:跳转到消息界面
        */
        void gotoNews();

        /*
        **describe:刷新页面数据
        */
        void refreshData();
    }

    interface Presenter extends IBasePresenter {
        void LoadData();

    }
}

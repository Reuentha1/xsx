package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.NewsActivity;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/8/5
 */
public interface NewsContract {

    interface View extends IBaseView<Presenter> {
        /*
        **describe:初始化数据
        */
        void initData();

        /*
        **describe:清除数据
        */
        void cleanData();

        /*
        **describe:弹出清除对话框
        */
        void showCleanDialog();

        /*
        **describe:点击清空
        */
        void clickOnClean();

        /*
        **describe:前往动态详情页面
        */
        void gotoDetail();
    }

    interface Presenter extends IBasePresenter {
        /*
        **describe:加载数据
        */
        void loadData();

        /*
        **describe:清除数据
        */
        void cleanData();


    }
}

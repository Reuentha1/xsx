package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.MessageNotice;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/8/8
 */
public interface MessageNoticeContract {
    interface View extends IBaseView<Presenter>{
        /*
**describe:设置刷新状态
*/
        void setRefreshState(boolean is);

        /*
        **describe:获取刷新状态
        */
        boolean isRefreshing();

        /*
        **describe:设置加载状态
        */
        void setLoadState(boolean is);

        /*
        **describe:获取加载状态
        */
        boolean isLoading();

        /*
        **describe:刷新页面
        */
        void refreshPager();

        /*
        **describe:自动下拉刷新
        */
        void autoRefresh();

        /*
        **describe:没有数据了
        */
        void showNoData();

        /*
       **describe:显示listview尾
       */
        void showFooter();

    }
    interface Presenter extends IBasePresenter{
        /*
        **describe:刷新数据
        */
        void refreshData();
        /*
        **describe:加载更多
        */
        void loadMore();
        /*
        **describe:判断是否需要自动刷新
        */
        boolean isNeedRefresh();
    }
}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState.DetailsActivity;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/8/6
 */
public interface DetailsContract {

    interface View extends IBaseView<Presenter> {
        /*
        **describe:刷新数据
        */
        void refreshData();
        /*
        **describe:跳转到权限界面
        */
        void gotoPermisson();
        /*
        **describe:弹出确认删除对话框
        */
        void showSureDelete();
    }

    interface Presenter extends IBasePresenter {
        /*
        **describe:删除
        */
        void delete();
        /*
        **describe:赞
        */
        void praise();
    }
}

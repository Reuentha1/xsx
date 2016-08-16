package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.MyShoolfellowHelp;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/7/23
 */
public class MyhelpContract {
    public interface View extends IBaseView<Presenter> {
        /*
        **describe:控制隐藏菜单的显示
        */
        void showHideMenu(boolean is);

        /*
        **describe:弹出删除对话框
        */
        void showDeleteSureDialog();

        /*
        **describe:没有内容时
        */
        void showNoContentText(boolean is);
        /*
        **describe:刷新数据
        */
        void refreshData();
    }

    public interface Presenter extends IBasePresenter {
        /*
        **describe:转发 删除
        */
        void transmit();
        void delete();
        /*
        **describe:完成或取消
        */
        void completeOrCancle();
        /*
        **describe:加载数据
        */
        void refreshData();
    }
}

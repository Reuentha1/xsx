package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.collect;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/7/23
 */
public class CollectContract {
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
        **describe:弹出取消收藏对话框
        */
        void showCollectDialog();
        /*
        **describe:提示弹窗
        */
        void noticeDialog(String message);
    }

    public interface Presenter extends IBasePresenter {
        /*
        **describe:转发 删除
        */
        void transmit();
        void delete();

        /*
        **describe:取消收藏
        */
        void unCollect();
    }
}

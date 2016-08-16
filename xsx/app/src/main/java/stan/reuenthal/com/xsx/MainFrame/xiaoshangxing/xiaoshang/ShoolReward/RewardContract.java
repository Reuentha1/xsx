package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward;

import android.app.Dialog;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/8/6
 */
public interface RewardContract {

    interface View extends IBaseView<Presenter> {
        /*
        **describe:弹出转发对话框
        */
        void showTransmitDialog();

        /*
        **describe:弹出转发成功对话框
        */
        void showTransmitSuccess();
    }

    interface Presenter extends IBasePresenter {
        /*
        **describe:转发到校友圈
        */
        void transmit(Dialog dialog);
    }
}

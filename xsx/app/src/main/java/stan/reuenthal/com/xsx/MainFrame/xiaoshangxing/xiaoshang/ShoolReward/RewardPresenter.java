package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/8/6
 */
public class RewardPresenter implements RewardContract.Presenter {
    private RewardContract.View mView;
    private Context context;

    public RewardPresenter(RewardContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void transmit(Dialog dialog) {
        dialog.dismiss();
        mView.showTransmitSuccess();
    }
}

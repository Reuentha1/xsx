package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.RewardDetail;

import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/8/6
 */
public class RewardDetailPresenter implements RewardDetailContract.Presenter {
    private RewardDetailContract.view mView;
    private Context context;

    public RewardDetailPresenter(RewardDetailContract.view mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void transmit() {
        mView.showTransmitSuccess();
    }

    @Override
    public void praise() {
        mView.setPraise();
    }

    @Override
    public void collect() {

    }

    @Override
    public void share() {

    }
}

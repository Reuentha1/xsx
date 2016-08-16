package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.HelpDetail;

import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/8/6
 */
public class HelpDetailPresenter implements HelpDetailContract.Presenter {
    private HelpDetailContract.View mView;
    private Context context;

    public HelpDetailPresenter(HelpDetailContract.View mView, Context context) {
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
    public void share() {

    }
}

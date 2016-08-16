package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/8/6
 */
public class HelpPresenter implements HelpContract.Presenter {
    private HelpContract.View mView;
    private Context context;

    public HelpPresenter(HelpContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void transmit(Dialog dialog) {
        dialog.dismiss();
        mView.showTransmitSuccess();
    }
}

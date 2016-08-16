package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.MyShoolfellowHelp;

import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/8/6
 */
public class MyHelpPresenter implements MyhelpContract.Presenter {
    private MyhelpContract.View mView;
    private Context context;

    public MyHelpPresenter(MyhelpContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void transmit() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void completeOrCancle() {

    }

    @Override
    public void refreshData() {

    }
}

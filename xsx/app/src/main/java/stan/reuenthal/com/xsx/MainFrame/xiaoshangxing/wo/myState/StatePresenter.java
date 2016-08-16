package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState;

import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/8/5
 */
public class StatePresenter implements StateContract.Presenter {
    private StateContract.View mView;
    private Context context;

    public StatePresenter(StateContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void LoadData() {
        mView.refreshData();
    }
}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.WoFrafment;

import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/8/4
 */
public class WoPresenter implements WoContract.Presenter {
    private WoContract.View mView;
    private Context context;

    public WoPresenter(WoContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void RefreshData() {
        mView.setRefreshState(true);
    }

    @Override
    public void LoadMore() {
        mView.setLoadState(true);
        mView.showNoData();
    }

    @Override
    public boolean isNeedRefresh() {
        return true;
    }
}

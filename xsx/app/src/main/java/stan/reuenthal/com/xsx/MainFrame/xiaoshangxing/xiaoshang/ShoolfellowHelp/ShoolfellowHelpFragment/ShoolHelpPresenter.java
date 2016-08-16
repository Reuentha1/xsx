package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.ShoolfellowHelpFragment;

import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/8/6
 */
public class ShoolHelpPresenter implements ShoolHelpContract.Presenter {
    private ShoolHelpContract.View mView;
    private Context context;

    public ShoolHelpPresenter(ShoolHelpContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public boolean isNeedRefresh() {
        return true;
    }

    @Override
    public void RefreshData() {
        mView.refreshPager();
    }

    @Override
    public void LoadMore() {

    }
}

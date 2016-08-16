package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.NewsActivity;

import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/8/5
 */
public class NewsPresenter implements NewsContract.Presenter {
    private NewsContract.View mView;
    private Context context;

    public NewsPresenter(NewsContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void cleanData() {
        mView.cleanData();
    }

    @Override
    public void loadData() {
        mView.initData();
    }
}

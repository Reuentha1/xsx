package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.collect;

import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/8/6
 */
public class CollectPresenter implements CollectContract.Presenter {
    private CollectContract.View mView;
    private Context context;

    public CollectPresenter(CollectContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void delete() {

    }

    @Override
    public void transmit() {

    }

    @Override
    public void unCollect() {
        mView.noticeDialog("已取消收藏");
    }
}

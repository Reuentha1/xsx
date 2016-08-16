package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.MessageNotice;

import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/8/8
 */
public class MessageNoticePresenter implements MessageNoticeContract.Presenter {
    private MessageNoticeContract.View mView;
    private Context context;

    public MessageNoticePresenter(MessageNoticeContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void refreshData() {
        mView.refreshPager();
    }

    @Override
    public void loadMore() {

    }

    @Override
    public boolean isNeedRefresh() {
        return true;
    }
}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.pull_refresh;

import android.view.View;

public interface PtrHandler {


    public boolean checkCanDoRefresh(final PtrFrameLayout frame, final View content, final View header);

    /**
     * When refresh begin
     *
     * @param frame
     */
    public void onRefreshBegin(final PtrFrameLayout frame);
}
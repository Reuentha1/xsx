package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState.DetailsActivity;

import android.content.Context;

/**
 * Created by FengChaoQun
 * on 2016/8/6
 */
public class DetailPresenter implements DetailsContract.Presenter {
    private DetailsContract.View mView;
    private Context context;

    public DetailPresenter(DetailsContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void delete() {

    }

    @Override
    public void praise() {

    }
}

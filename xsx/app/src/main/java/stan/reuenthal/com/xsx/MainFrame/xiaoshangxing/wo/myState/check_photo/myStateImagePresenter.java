package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState.check_photo;

import android.content.Context;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.image.SaveImageTask;

/**
 * Created by FengChaoQun
 * on 2016/8/6
 */
public class myStateImagePresenter implements myStateImagePagerContract.Presenter {
    private myStateImagePagerContract.View mView;
    private Context context;

    public myStateImagePresenter(myStateImagePagerContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void save() {

    }

    @Override
    public void sendToFriend() {

    }

    @Override
    public void saveImage(String url) {
        SaveImageTask s = new SaveImageTask(context);
        s.execute(url);
    }

    @Override
    public void deleteImage() {

    }
}

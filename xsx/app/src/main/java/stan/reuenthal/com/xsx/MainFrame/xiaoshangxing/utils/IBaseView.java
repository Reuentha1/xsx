package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils;

import android.support.annotation.Nullable;

import rx.Subscription;

/**
 * Created by FengChaoQun
 * on 2016/6/22
 */
public interface IBaseView<T> {
    void setmPresenter(@Nullable T presenter);

    /*
     **describe:显示、关闭LoadingDialog
     */
    void showLoadingDialog(String text);

    void hideLoadingDialog();

    void  setonDismiss(LoadingDialog.onDismiss on);

    /*
    **describe:注销Observer
    */
    void unsubscribe();

    /*
    **describe:获取Subscription
    */
    Subscription getSubscription();
    /*
    **describe:设置Subscription
    */
    void setSubscription(Subscription subscription);
    /*
    **describe: Toast
    */
    void showToast(String toast);
}

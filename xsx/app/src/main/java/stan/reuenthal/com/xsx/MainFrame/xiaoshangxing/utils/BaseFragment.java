package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import rx.Subscription;

/**
 * Created by FengChaoQun
 * on 2016/6/22
 */
public class BaseFragment extends Fragment {
    public static final String TAG = AppContracts.TAG + "-Fragment";
    protected BaseActivity mActivity;
    protected Context mContext;
    public Subscription subscription;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        mContext = mActivity;

    }

    /*
    **describe:显示LoadingDialog
    */

    public void showLoadingDialog(String text) {
        mActivity.showLoadingDialog(text);
    }

    /*
    **describe:关闭LoadingDialog
    */
    public void hideLoadingDialog() {
        mActivity.hideLoadingDialog();
    }

    /*
    **describe:设置LoadingDialog关闭时的回调
    */
    public void setonDismiss(LoadingDialog.onDismiss on) {
        mActivity.setonDismiss(on);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    /*
    **describe:注销Observer
    */
    public void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    /*
    **describe:获取Subscription
    */
    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription){
        this.subscription=subscription;
    }

    public void showToast(String toast){
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
    }
}

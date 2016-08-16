package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import stan.reuenthal.com.xsx.MyApplication;
import stan.reuenthal.com.xsx.R;

import rx.Subscription;

/**
 * Created by FengChaoQun
 * on 2016/6/11
 */
public class BaseActivity extends AppCompatActivity {

    public static final String TAG = AppContracts.TAG + "-Activity";

    protected FragmentManager mFragmentManager;
    protected MyApplication mApplication;

    protected LoadingDialog loadingDialog;

    protected Subscription subscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getSupportFragmentManager();
        mApplication = (MyApplication) getApplication();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new LoadingDialog(this);
        /*
        **describe:默认隐藏标题栏
        */
        hideTitle(true);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    protected void hideTitle(boolean hideTitle) {
        if (!hideTitle) {
            getSupportActionBar().show();
        } else {
            getSupportActionBar().hide();
        }

    }

    /*
    **describe:显示LoadingDialog
    */
    public void showLoadingDialog(String text) {
        loadingDialog.setLoadText(text);
        loadingDialog.show();
        WindowManager.LayoutParams lp = loadingDialog.getWindow().getAttributes();
        lp.width = getResources().getDimensionPixelSize(R.dimen.x360); //设置宽度
        lp.height = getResources().getDimensionPixelSize(R.dimen.y360); //设置宽度
        loadingDialog.getWindow().setAttributes(lp);
    }

    /*
    **describe:关闭LoadingDialog
    */
    public void hideLoadingDialog() {
        loadingDialog.dismiss();
    }

    /*
    **describe:设置关闭LoadingDialog时的回调
    */
    public void setonDismiss(LoadingDialog.onDismiss on) {
        loadingDialog.setOnDismiss(on);
    }

    @Override
    protected void onDestroy() {
        unsubscribe();
        super.onDestroy();
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

    /*
    **describe:设置Subscription
    */
    public void setSubscription(Subscription subscription){
        this.subscription=subscription;
    }

    /*
    **describe:Toast
    */
    public void showToast(String toast){
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}

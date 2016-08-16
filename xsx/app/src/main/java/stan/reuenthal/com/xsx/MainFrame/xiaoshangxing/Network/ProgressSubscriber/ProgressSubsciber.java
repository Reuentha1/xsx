package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.ProgressSubscriber;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LoadingDialog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by FengChaoQun
 * on 2016/8/4
 */
public class ProgressSubsciber<T> extends Subscriber<T> {
    private IBaseView baseView;
    private String loadingText = "加载中...";
    private ProgressSubscriberOnNext progressSubscriberOnNext;          //OnNext
    private ProgressSubscriberOnComplete progressSubscriberOnComplete;  //OnComplete
    private ProgressSubscriberOnError progressSubscriberOnError;        //OnError
    private ProgressSubscriberOnStart progressSubscriberOnStart;        //OnStart

    public ProgressSubsciber(ProgressSubscriberOnNext progressSubscriberOnNext, IBaseView baseView) {
        this.baseView = baseView;
        this.progressSubscriberOnNext = progressSubscriberOnNext;
        baseView.setonDismiss(new LoadingDialog.onDismiss() {
            @Override
            public void doBeforDismiss() {
                unsubscribe();
            }
        });
    }

    @Override
    public void onCompleted() {
        baseView.hideLoadingDialog();
        if (progressSubscriberOnComplete != null) {
            progressSubscriberOnComplete.OnComplete();
        }
    }

    @Override
    public void onError(Throwable e) {
        baseView.hideLoadingDialog();
        if (e instanceof SocketTimeoutException) {
            baseView.showToast("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            baseView.showToast("网络中断，请检查您的网络状态");
        } else {
            baseView.showToast("请求错误");
            e.printStackTrace();
        }
        if (progressSubscriberOnError != null) {
            progressSubscriberOnError.OnError(e);
        }
    }

    @Override
    public void onNext(T t) {
        if (progressSubscriberOnNext != null) {
            progressSubscriberOnNext.onNext(t);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        baseView.showLoadingDialog(loadingText);
        if (progressSubscriberOnStart != null) {
            progressSubscriberOnStart.OnStart();
        }
    }

    public void setBaseView(IBaseView baseView) {
        this.baseView = baseView;
    }

    public void setLoadingText(String loadingText) {
        this.loadingText = loadingText;
    }

    public void setProgressSubscriberOnNext(ProgressSubscriberOnNext progressSubscriberOnNext) {
        this.progressSubscriberOnNext = progressSubscriberOnNext;
    }

    public void setProgressSubscriberOnComplete(ProgressSubscriberOnComplete progressSubscriberOnComplete) {
        this.progressSubscriberOnComplete = progressSubscriberOnComplete;
    }

    public void setProgressSubscriberOnError(ProgressSubscriberOnError progressSubscriberOnError) {
        this.progressSubscriberOnError = progressSubscriberOnError;
    }

    public void setProgressSubscriberOnStart(ProgressSubscriberOnStart progressSubscriberOnStart) {
        this.progressSubscriberOnStart = progressSubscriberOnStart;
    }
}

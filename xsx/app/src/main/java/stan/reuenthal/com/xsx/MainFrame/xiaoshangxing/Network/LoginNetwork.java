package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.Bean.CheckCode;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.Bean.Register;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.Bean.login;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.api.CheckCodeApi;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.api.RegisterApi;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.api.loginApi;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FengChaoQun
 * on 2016/8/4
 */
public class LoginNetwork {
    private loginApi mloginApi;
    private RegisterApi registerApi;
    private CheckCodeApi checkCodeApi;

    private LoginNetwork() {

    }

    //在访问LoginNetwork时创建单例
    private static class SingletonHolder {
        private static final LoginNetwork INSTANCE = new LoginNetwork();
    }

    //获取单例
    public static LoginNetwork getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void Login(Subscriber<ResponseBody> subscriber, login login) {
        if (mloginApi == null) {
            mloginApi = Network.getRetrofit().create(loginApi.class);
        }
        Observable<ResponseBody> observable = mloginApi.login(login);
        toSubscribe(observable, subscriber);
    }

    public void Register(Subscriber<ResponseBody> subscriber, Register register){
        if (registerApi == null) {
            registerApi = Network.getRetrofit().create(RegisterApi.class);
        }
        Observable<ResponseBody> observable = registerApi.login(register);
        toSubscribe(observable, subscriber);
    }

    public void CheckCode(Subscriber<ResponseBody> subscriber, CheckCode checkCode){
        if (checkCodeApi == null) {
            checkCodeApi = Network.getRetrofit().create(CheckCodeApi.class);
        }
        Observable<ResponseBody> observable = checkCodeApi.login(checkCode);
        toSubscribe(observable, subscriber);
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}

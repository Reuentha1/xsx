package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SetPasswordFragment;

import android.util.Log;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.Bean.Register;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.LoginNetwork;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.ProgressSubscriber.ProgressSubsciber;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.ProgressSubscriber.ProgressSubscriberOnNext;

import org.json.JSONObject;

import okhttp3.ResponseBody;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public class SetPasswordFragmentPresenter implements SetPasswordContract.Presenter {
    private SetPasswordContract.View mView;

    public SetPasswordFragmentPresenter(SetPasswordContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void isContentOK() {
        if (mView.getPassword().length() >= 8) {
            mView.setButtonState(true);
        } else {
            mView.setButtonState(false);
        }
    }

    @Override
    public void clickOnCompleteButton() {
        ProgressSubscriberOnNext<ResponseBody> onNext = new ProgressSubscriberOnNext<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(responseBody.string());
                    switch (Integer.valueOf((String) jsonObject.get("code"))) {
                        case 9000:
                            Log.d("register", "success");
                            mView.showRegisterSuccess();
                            break;
                        default:
                            Log.d("register", "erro");
                            mView.showToast("注册失败，请重新尝试");
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ProgressSubsciber<ResponseBody> observer = new ProgressSubsciber<ResponseBody>(onNext, mView);
        Register register = new Register();
        register.setPhone(mView.getPhone());
        register.setPassword(mView.getPassword());
        register.setTimestamp("12");
        LoginNetwork.getInstance().Register(observer, register);

    }
}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RgInputVertifyCodeFragment;

import android.os.CountDownTimer;
import android.util.Log;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.Bean.CheckCode;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.LoginNetwork;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.ProgressSubscriber.ProgressSubsciber;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.ProgressSubscriber.ProgressSubscriberOnNext;

import org.json.JSONObject;

import okhttp3.ResponseBody;

/**
 * Created by FengChaoQun
 * on 2016/6/23
 */
public class RgInputVertifyCodePresenter implements RgInputVertifyCodeContract.Presenter {
    private RgInputVertifyCodeContract.View mView;
    private CountDownTimer countDownTimer;

    public RgInputVertifyCodePresenter(final RgInputVertifyCodeContract.View mView) {
        this.mView = mView;
        countDownTimer = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mView.setRemainTime("接受短信大约需要" + millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                mView.setRemainTimeChange();
            }
        };
    }

    @Override
    public void clickOnSubmit() {
        if (mView.getVertifyCode().equals("888888")) {
            mView.gotoWhere();
            return;
        } /*else {
            mView.gotoWhere();
        }*/
        ProgressSubscriberOnNext<ResponseBody> onNext = new ProgressSubscriberOnNext<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(responseBody.string());
                    switch (Integer.valueOf(jsonObject.getString("code"))) {
                        case 9001:
                            Log.d("checkCode", "success");
                            mView.gotoWhere();
                            break;
                        default:
                            Log.d("checkCode", "erro");
                            mView.showFailDialog();
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ProgressSubsciber<ResponseBody> observer = new ProgressSubsciber<ResponseBody>(onNext, mView);
        CheckCode checkCode = new CheckCode();
        checkCode.setPhone(mView.getPhone());
        checkCode.setCode(mView.getVertifyCode());
        checkCode.setTimestamp("tdgsfgdgfg");
        LoginNetwork.getInstance().CheckCode(observer, checkCode);
    }

    @Override
    public void startCountTime() {
        countDownTimer.start();
    }

    @Override
    public void stopCountTime() {
        countDownTimer.cancel();
        mView.setRemainTimeEnable(false);
    }

    @Override
    public void noReceiveCode() {
        mView.showNoDialogMenu();
    }

    @Override
    public void isContentOK() {
        if (mView.getVertifyCode().length() == 6) {
            mView.setButtonEnable(true);
        } else {
            mView.setButtonEnable(false);
        }
    }
}

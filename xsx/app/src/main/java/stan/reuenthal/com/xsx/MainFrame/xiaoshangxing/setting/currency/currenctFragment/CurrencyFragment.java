package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.currency.currenctFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.DataSetting;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.SwitchView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.normalUtils.SPUtils;

/**
 * Created by 15828 on 2016/7/14.
 */
public class CurrencyFragment extends BaseFragment implements View.OnClickListener{
    private View mView;
    private SwitchView receivemode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_setting_currency, container, false);
        receivemode = (SwitchView) mView.findViewById(R.id.currency_receivemode);
        receivemode.setState(DataSetting.IsReceiveMode(getActivity()));
        receivemode.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                SPUtils.put(getActivity(),"currency_receivemode",true);
                receivemode.setState(true);
            }

            @Override
            public void toggleToOff() {
                SPUtils.put(getActivity(),"currency_receivemode",false);
                receivemode.setState(false);
            }
        });
        return mView;
    }

    @Override
    public void onClick(View v) {

    }
}

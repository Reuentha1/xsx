package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.privacy.privacyFragment;

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
public class PrivacyFragment extends BaseFragment {
    private View mView;
    private SwitchView privacy_allow;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_setting_privacy,container,false);
        privacy_allow = (SwitchView) mView.findViewById(R.id.privacy_allow);
        privacy_allow.setState(DataSetting.IsAllowedTenPicture(getActivity()));
        privacy_allow.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                SPUtils.put(getActivity(),"privacy_allow",true);
                privacy_allow.setState(true);
            }

            @Override
            public void toggleToOff() {
                SPUtils.put(getActivity(),"privacy_allow",false);
                privacy_allow.setState(false);
            }
        });

        return mView;
    }
}

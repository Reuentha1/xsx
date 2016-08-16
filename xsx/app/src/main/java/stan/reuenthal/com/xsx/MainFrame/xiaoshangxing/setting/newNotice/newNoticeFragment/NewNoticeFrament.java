package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.newNotice.newNoticeFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.DataSetting;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.SwitchView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.normalUtils.SPUtils;

/**
 * Created by 15828 on 2016/7/13.
 */
public class NewNoticeFrament extends BaseFragment {
    private View mView;
    private SwitchView newnotice_accept,newnotice_inform,newnotice_sound,newnotice_vibration,newnotice_renew;
    private RelativeLayout layout1, layout4;
    private LinearLayout layout2, layout3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_setting_newnotice, container, false);
        newnotice_accept = (SwitchView) mView.findViewById(R.id.newnotice_accept);
        newnotice_inform = (SwitchView) mView.findViewById(R.id.newnotice_inform);
        newnotice_sound = (SwitchView) mView.findViewById(R.id.newnotice_sound);
        newnotice_vibration = (SwitchView) mView.findViewById(R.id.newnotice_vibration);
        newnotice_renew = (SwitchView) mView.findViewById(R.id.newnotice_renew);

        layout1 = (RelativeLayout) mView.findViewById(R.id.newnotice_lay1);
        layout2 = (LinearLayout) mView.findViewById(R.id.newnotice_lay2);
        layout3 = (LinearLayout) mView.findViewById(R.id.newnotice_lay3);
        layout4 = (RelativeLayout) mView.findViewById(R.id.newnotice_lay4);

        setUpData();

        newnotice_accept.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                SPUtils.put(getActivity(),"newnotice_accept",true);
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.VISIBLE);
                layout3.setVisibility(View.VISIBLE);
                layout4.setVisibility(View.VISIBLE);
                //   accept.toggleSwitch(true);
                newnotice_accept.setState(true);
            }

            @Override
            public void toggleToOff() {
                SPUtils.put(getActivity(),"newnotice_accept",false);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
                layout3.setVisibility(View.GONE);
                layout4.setVisibility(View.GONE);
                // accept.toggleSwitch(false);
                newnotice_accept.setState(false);
            }
        });
        newnotice_inform.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                SPUtils.put(getActivity(),"newnotice_inform",true);
                newnotice_inform.setState(true);
            }

            @Override
            public void toggleToOff() {
                SPUtils.put(getActivity(),"newnotice_inform",false);
                newnotice_inform.setState(false);
            }
        });
        newnotice_sound.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                SPUtils.put(getActivity(),"newnotice_sound",true);
                newnotice_sound.setState(true);
            }

            @Override
            public void toggleToOff() {
                SPUtils.put(getActivity(),"newnotice_sound",false);
                newnotice_sound.setState(false);
            }
        });
        newnotice_vibration.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                SPUtils.put(getActivity(),"newnotice_vibration",true);
                newnotice_vibration.setState(true);
            }

            @Override
            public void toggleToOff() {
                SPUtils.put(getActivity(),"newnotice_vibration",false);
                newnotice_vibration.setState(false);
            }
        });
        newnotice_renew.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                SPUtils.put(getActivity(),"newnotice_renew",true);
                newnotice_renew.setState(true);
            }

            @Override
            public void toggleToOff() {
                SPUtils.put(getActivity(),"newnotice_renew",false);
                newnotice_renew.setState(false);
            }
        });
        return mView;
    }

    private void setUpData() {
        newnotice_accept.setState(DataSetting.IsAccepted(getActivity()));
        newnotice_inform.setState(DataSetting.IsInformed(getActivity()));
        newnotice_sound.setState(DataSetting.IsSounded(getActivity()));
        newnotice_vibration.setState(DataSetting.IsVibrationed(getActivity()));
        newnotice_renew.setState(DataSetting.IsRenewed(getActivity()));
        if(newnotice_accept.getState2()) {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);
        }
    }


}



package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.vertify.vertifySuccessFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.PersonalInfoActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.vertify.vertifyFragment.VertifyFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

/**
 * Created by 15828 on 2016/7/12.
 */
public class VertifySucessFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-VertifySucessFragment";
    private View mView;
    private TextView back;
    private PersonalInfoActivity mActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_setting_personalinfo_vertifysucess, container, false);
        mActivity = (PersonalInfoActivity)getActivity();
        mActivity.setVertified(true);
        back = (TextView) mView.findViewById(R.id.vertifysucess_back);
        back.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {
        Log.d("qqq", "back......");
        mActivity.setVertified(false);
        getActivity().getSupportFragmentManager().popBackStack(VertifyFragment.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

    }

    public void onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                getActivity().getSupportFragmentManager().popBackStack(VertifyFragment.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
    }


}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.aftervertifysuccess;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

/**
 * Created by 15828 on 2016/7/15.
 */
public class AfterVertifySuccessFragment extends BaseFragment implements View.OnClickListener{
    private View mView;
    private TextView back;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_setting_aftervertify,container,false);
        back = (TextView) mView.findViewById(R.id.back);
        back.setOnClickListener(this);

        return mView;
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.report.reportNoticeFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

/**
 * Created by tianyang on 2016/7/2.
 */
public class ReportNoticeFragment extends BaseFragment implements View.OnClickListener{
    public static final String TAG = BaseFragment.TAG + "-ReportNoticeFragment";
    private View mView;
    private TextView back;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_report_notice,container,false);
        back = (TextView) mView.findViewById(R.id.toolbar_reportnotice_back);
        back.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_reportnotice_back:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            default:
                break;
        }

    }
}

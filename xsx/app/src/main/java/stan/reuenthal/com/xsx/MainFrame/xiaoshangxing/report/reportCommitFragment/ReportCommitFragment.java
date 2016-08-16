package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.report.reportCommitFragment;

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
public class ReportCommitFragment extends BaseFragment implements View.OnClickListener{
    private View mView;
    public static final String TAG = BaseFragment.TAG + "-ReportCommitFragment";
    private TextView finish;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_report_commit,container,false);
        finish = (TextView) mView.findViewById(R.id.toolbar_reportcommit_finish);
        finish.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_reportcommit_finish:
                getActivity().finish();
                break;
            default:
                break;
        }
    }

}

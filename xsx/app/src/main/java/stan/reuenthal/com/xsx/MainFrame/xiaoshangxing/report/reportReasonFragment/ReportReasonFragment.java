package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.report.reportReasonFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.report.reportEvidenceFragment.ReportEvidenceFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

/**
 * Created by tianyang on 2016/7/1.
 */
public class ReportReasonFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final String TAG = BaseFragment.TAG + "-ReportReasonFragment";
    private View mView;
    private ArrayAdapter mAdapter;
    private ListView listView;
    private TextView cancel, next;
    private String[] mReasons;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_report_reason, container, false);
        mReasons = getResources().getStringArray(R.array.report_reasons);
        mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item_report, mReasons);
        listView = (ListView) mView.findViewById(R.id.list_reportReason);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
        cancel = (TextView) mView.findViewById(R.id.toolbar_report_cancel);
        next = (TextView) mView.findViewById(R.id.toolbar_report_next);
        next.setAlpha(0.5f);
        Log.d("qqq ","we");
        cancel.setOnClickListener(this);
        next.setOnClickListener(this);
        return mView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_report_cancel:
                getActivity().finish();
                break;
            case R.id.toolbar_report_next:
                int position = listView.getCheckedItemPosition();
                if (position == -1) break;
                else {
                    Log.d("qqq", "   " + mReasons[position]);
                    ReportEvidenceFragment reportEvidenceFragment = new ReportEvidenceFragment();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                                    R.anim.slide_in_left, R.anim.slide_out_left)
                            .addToBackStack(ReportEvidenceFragment.TAG)
                            .replace(R.id.reportContent, reportEvidenceFragment, ReportEvidenceFragment.TAG)
                            .commit();
                    listView.setItemChecked(position,false);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        next.setAlpha(1.0f);
    }
}

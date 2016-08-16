package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.report;

import android.os.Bundle;
import android.util.Log;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.report.reportEvidenceFragment.ReportEvidenceFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.report.reportReasonFragment.ReportReasonFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.Bimp;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;

/**
 * Created by tianyang on 2016/7/1.
 */
public class ReportActivity extends BaseActivity {
    public static final String TAG = BaseActivity.TAG + "-LoginRegisterActivity";
    private String reportText;
//    private String folderName;
//    private boolean isCanceled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ReportReasonFragment reportReasonFragment = new ReportReasonFragment();
        ReportEvidenceFragment reportEvidenceFragment = new ReportEvidenceFragment();

        mFragmentManager.beginTransaction()
                .replace(R.id.reportContent, reportReasonFragment, ReportReasonFragment.TAG)
                .commit();

    }

    public void setReportText(String text){
        this.reportText = text;
    }

    public String getReportText() {
        return reportText;
    }

//    public void setFolderName(String folderName) {
//        this.folderName = folderName;
//    }
//
//    public String getFolderName() {
//        return folderName;
//    }

//    public void setCanceled(boolean canceled) {
//        isCanceled = canceled;
//        Log.d("qqq","setting..."+isCanceled);
//    }
//
//    public boolean isCanceled() {
//        return isCanceled;
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("qqq", "destory..");
        Bimp.tempSelectBitmap.clear();
    }
}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.yujian.info;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;

/**
 * Created by 15828 on 2016/7/25.
 */
public class MoreInfoActivity extends BaseActivity {
    private TextView personalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yujian_moreinfo);
        personalInfo = (TextView) findViewById(R.id.moreinfo_text);

        assert personalInfo != null;
        ViewTreeObserver vto = personalInfo.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                int lineCount = personalInfo.getLineCount();
                if (lineCount == 1) personalInfo.setGravity(Gravity.RIGHT);
                else personalInfo.setGravity(Gravity.LEFT);
                // System.out.println(lineCount);
                return true;
            }
        });


    }

    public void Back(View view) {
        finish();
    }

    public void JiHua(View view) {
    }

    public void HuBang(View view) {
    }

    public void XuanShang(View view) {
    }

    public void XianZhi(View view) {
    }
}

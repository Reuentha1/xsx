package stan.reuenthal.com.xsx.IMUI;

import android.os.Bundle;
import android.widget.TextView;

import stan.reuenthal.com.xsx.IMpanel.TActionBarActivity;
import stan.reuenthal.com.xsx.R;

/**
 * Created by G.J.Lee on 八月.
 * Email finalfantasy@games.com
 */
public class AboutActivity extends TActionBarActivity {

    private TextView versionGit;
    private TextView versionDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        findViews();
        initViewData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void findViews() {
        versionGit = (TextView) findViewById(R.id.version_detail_git);
        versionDate = (TextView) findViewById(R.id.version_detail_date);

//        CustomActions.customButton((Button) findViewById(R.id.about_custom_button_1));
    }

    private void initViewData() {
        // 如果使用的IDE是Eclipse， 将该函数体注释掉。这里使用了Android Studio编译期添加BuildConfig字段的特性
        versionGit.setText("Git Version: " + BuildConfig.GIT_REVISION);
        versionDate.setText("Build Date:" + BuildConfig.BUILD_DATE);
    }
}

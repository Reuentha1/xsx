package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.privacy;

import android.os.Bundle;
import android.view.View;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.privacy.blackListFragment.BlackListFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.privacy.privacyFistFragment.PrivacyFistFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.privacy.privacyFragment.PrivacyFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.privacy.privacySecondFragment.PrivacySecondFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;

/**
 * Created by 15828 on 2016/7/14.
 */
public class PrivacyActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_privacy);
        mFragmentManager.beginTransaction()
                .replace(R.id.setting_privacy_Content, new PrivacyFragment())
                .commit();
    }



    public void privacy_back(View view) {
        finish();
    }

    public void blacklist(View view) {
        mFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .addToBackStack(null)
                .replace(R.id.setting_privacy_Content, new BlackListFragment())
                .commit();
    }

    public void PrivacyFirst(View view) {
        mFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .addToBackStack(null)
                .replace(R.id.setting_privacy_Content, new PrivacyFistFragment())
                .commit();
    }


    public void PrivacySecond(View view) {
        mFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .addToBackStack(null)
                .replace(R.id.setting_privacy_Content, new PrivacySecondFragment())
                .commit();
    }
}

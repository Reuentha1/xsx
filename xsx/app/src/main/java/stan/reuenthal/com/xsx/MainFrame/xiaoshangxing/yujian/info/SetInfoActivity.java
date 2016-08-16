package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.yujian.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.report.ReportActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.DataSetting;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.SwitchView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.normalUtils.SPUtils;

/**
 * Created by 15828 on 2016/7/25.
 */
public class SetInfoActivity extends BaseActivity {
    private SwitchView starMarkfriends, crush, bukanwo, bukanta, addToBlackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yujian_setinfo);

        starMarkfriends = (SwitchView) findViewById(R.id.StarMarkfriends);
        crush = (SwitchView) findViewById(R.id.crush);
        bukanwo = (SwitchView) findViewById(R.id.bukanwo);
        bukanta = (SwitchView) findViewById(R.id.bukanta);
        addToBlackList = (SwitchView) findViewById(R.id.addtoblacklist);

        setUpData();

        starMarkfriends.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                SPUtils.put(SetInfoActivity.this, "starMarkfriends", true);
                starMarkfriends.setState(true);
            }

            @Override
            public void toggleToOff() {
                SPUtils.put(SetInfoActivity.this, "starMarkfriends", false);
                starMarkfriends.setState(false);
            }
        });

        crush.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                SPUtils.put(SetInfoActivity.this, "crush", true);
                crush.setState(true);
            }

            @Override
            public void toggleToOff() {
                SPUtils.put(SetInfoActivity.this, "crush", false);
                crush.setState(false);
            }
        });
        bukanwo.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                SPUtils.put(SetInfoActivity.this, "bukanwo", true);
                bukanwo.setState(true);
            }

            @Override
            public void toggleToOff() {
                SPUtils.put(SetInfoActivity.this, "bukanwo", false);
                bukanwo.setState(false);
            }
        });
        bukanta.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                SPUtils.put(SetInfoActivity.this, "bukanta", true);
                bukanta.setState(true);
            }

            @Override
            public void toggleToOff() {
                SPUtils.put(SetInfoActivity.this, "bukanta", false);
                bukanta.setState(false);
            }
        });
        addToBlackList.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                SPUtils.put(SetInfoActivity.this, "addToBlackList", true);
                addToBlackList.setState(true);
            }

            @Override
            public void toggleToOff() {
                SPUtils.put(SetInfoActivity.this, "addToBlackList", false);
                addToBlackList.setState(false);
            }
        });

    }

    private void setUpData() {
        starMarkfriends.setState(DataSetting.IsStarMarkfriends(this));
        crush.setState(DataSetting.IsCrush(this));
        bukanwo.setState(DataSetting.IsBuKanWo(this));
        bukanta.setState(DataSetting.IsBuKanTa(this));
        addToBlackList.setState(DataSetting.IsAddToBlackList(this));

    }

    public void Back(View view) {
        finish();
    }

    public void Report(View view) {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }
}

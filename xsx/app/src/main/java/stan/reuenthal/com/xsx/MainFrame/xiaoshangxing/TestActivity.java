package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import stan.reuenthal.com.xsx.R;

public class TestActivity extends AppCompatActivity {

    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.button4)
    Button button4;
    @Bind(R.id.button5)
    Button button5;
    @Bind(R.id.button6)
    Button button6;
    @Bind(R.id.button7)
    Button button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7})
    public void onClick(View view) {
        Intent intent=new Intent(this, InputActivity.class);
        switch (view.getId()) {
            case R.id.button1:
                intent.putExtra(InputActivity.EDIT_STATE,InputActivity.PUBLISH_STATE);
                break;
            case R.id.button2:
                intent.putExtra(InputActivity.EDIT_STATE,InputActivity.SHOOLFELLOW_HELP);
                break;
            case R.id.button3:
                intent.putExtra(InputActivity.EDIT_STATE,InputActivity.SHOOL_REWARD);
                break;
            case R.id.button4:
                intent.putExtra(InputActivity.EDIT_STATE,InputActivity.LANCH_PLAN);
                break;
            case R.id.button5:
                intent.putExtra(InputActivity.EDIT_STATE,InputActivity.XIANZHI);
                intent.putExtra(InputActivity.LIMIT,3);
                break;
            case R.id.button6:
                intent.putExtra(InputActivity.EDIT_STATE,InputActivity.TRANSMIT);
                break;
            case R.id.button7:
                intent.putExtra(InputActivity.EDIT_STATE,InputActivity.COMMENT);
                break;
        }
        startActivity(intent);
    }
}

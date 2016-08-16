package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.feedback;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;

/**
 * Created by 15828 on 2016/7/12.
 */
public class FeedbackActivity extends BaseActivity {
    private EditText editText;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_feedback);
        editText = (EditText) findViewById(R.id.feedback_editText);
        submit = (Button) findViewById(R.id.feedback_submit);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                   if(s.toString().length()>0){
                       submit.setAlpha(1);
                       submit.setClickable(true);
                   }else {
                       submit.setAlpha((float) 0.5);
                       submit.setClickable(false);
                   }
            }
        });

    }

    public void back(View view) {
        finish();
    }

    public void Commit(View view) {

    }
}

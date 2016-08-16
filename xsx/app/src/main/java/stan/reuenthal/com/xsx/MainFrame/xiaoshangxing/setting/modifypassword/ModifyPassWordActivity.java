package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.modifypassword;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;

/**
 * Created by 15828 on 2016/7/15.
 */
public class ModifyPassWordActivity extends BaseActivity {
    private EditText editText1, editText2, editText3;
    private Button confirm;
    private boolean flag1 = false, flag2 = false, flag3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_modifypasswd);
        editText1 = (EditText) findViewById(R.id.modify_editText1);
        editText2 = (EditText) findViewById(R.id.modify_editText2);
        editText3 = (EditText) findViewById(R.id.modify_editText3);
        confirm = (Button) findViewById(R.id.confirm_modify);
        addListener();
    }

    private void addListener() {
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    flag1 = true;
                    if (flag2 && flag3) {
                        confirm.setAlpha(1);
                        confirm.setClickable(true);
                    }
                } else {
                    flag1 = false;
                    confirm.setAlpha((float) 0.5);
                    confirm.setClickable(false);
                }
            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    flag2 = true;
                    if (flag1 && flag3) {
                        confirm.setAlpha(1);
                        confirm.setClickable(true);
                    }
                } else {
                    flag2 = false;
                    confirm.setAlpha((float) 0.5);
                    confirm.setClickable(false);
                }
            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    flag3 = true;
                    if (flag1 && flag2) {
                        confirm.setAlpha(1);
                        confirm.setClickable(true);
                    }
                } else {
                    flag3 = false;
                    confirm.setAlpha((float) 0.5);
                    confirm.setClickable(false);
                }
            }
        });
    }


    public void Cancel(View view) {
        finish();
    }
}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.vertify.vertifyFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.vertify.vertifyAgreementFragment.VertifyAgreementFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.vertify.vertifySuccessFragment.VertifySucessFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;


/**
 * Created by 15828 on 2016/7/12.
 */
public class VertifyFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-VertifyFragment";
    private View mView;
    private TextView back, agreement;
    private Button submit;
    private EditText account, password;
    private boolean flag1 = false, flag2 = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_setting_personalinfo_vertify, container, false);
        back = (TextView) mView.findViewById(R.id.vertify_back);
        submit = (Button) mView.findViewById(R.id.vertify_submit);
        account = (EditText) mView.findViewById(R.id.editext_account);
        password = (EditText) mView.findViewById(R.id.editext_password);
        agreement = (TextView) mView.findViewById(R.id.VertifyAgreement);
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
        agreement.setOnClickListener(this);
        account.addTextChangedListener(new TextWatcher() {
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
                    if (flag2) {
                        submit.setAlpha(1);
                        submit.setClickable(true);
                    }
                } else {
                    flag1 = false;
                    submit.setAlpha((float) 0.5);
                    submit.setClickable(false);
                }

            }
        });
        password.addTextChangedListener(new TextWatcher() {
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
                    if (flag1) {
                        submit.setAlpha(1);
                        submit.setClickable(true);
                    }
                } else {
                    flag2 = false;
                    submit.setAlpha((float) 0.5);
                    submit.setClickable(false);
                }

            }
        });
        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vertify_back:
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mView, InputMethodManager.SHOW_FORCED);
                imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.vertify_submit:
                InputMethodManager imm2 = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm2.showSoftInput(mView, InputMethodManager.SHOW_FORCED);
                imm2.hideSoftInputFromWindow(mView.getWindowToken(), 0);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                                R.anim.slide_in_left, R.anim.slide_out_left)
                        .addToBackStack(null)
                        .replace(R.id.setting_personinfo_Content, new VertifySucessFragment(), VertifySucessFragment.TAG)
                        .commit();

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(getActivity().getSupportFragmentManager().findFragmentByTag(VertifyFragment.TAG))
                        .commit();
                break;
            case R.id.VertifyAgreement:
                InputMethodManager imm3 = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm3.showSoftInput(mView, InputMethodManager.SHOW_FORCED);
                imm3.hideSoftInputFromWindow(mView.getWindowToken(), 0);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                                R.anim.slide_in_left, R.anim.slide_out_left)
                        .addToBackStack(null)
                        .replace(R.id.setting_personinfo_Content, new VertifyAgreementFragment())
                        .commit();
                break;
            default:
                break;
        }
    }
}

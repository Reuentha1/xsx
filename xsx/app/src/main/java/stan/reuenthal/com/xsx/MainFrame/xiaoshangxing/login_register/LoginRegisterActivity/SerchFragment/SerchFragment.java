package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SerchFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.MainActivity;
import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginRegisterActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SchoolNoOpenFragment.SchoolNoOpenFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

/**
 * Created by FengChaoQun
 * on 2016/6/25
 */
public class SerchFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-SendEmailFragment";

    private View mView;
    private AutoCompleteTextView autoCompleteTextView;
    private TextView cancel;

    public static SerchFragment newInstance() {
        return new SerchFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_serch, container, false);
        mView = view;
        initView();
        return view;
    }

    private void initView() {

        autoCompleteTextView = (AutoCompleteTextView) mView.findViewById(R.id.serch);

        //输入框自动获取焦点 并弹出输入键盘
        autoCompleteTextView.setFocusable(true);
        autoCompleteTextView.setFocusableInTouchMode(true);
        autoCompleteTextView.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        cancel = (TextView) mView.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        String[] test = {"江南大学", "江南小学", "江南中学"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, test);
        autoCompleteTextView.setAdapter(adapter);


        autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    isSchoolAccess(autoCompleteTextView.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    private void isSchoolAccess(String name) {
        if (!name.contains("江南大学")) {
            SchoolNoOpenFragment frag = ((LoginRegisterActivity) mActivity).getSchoolNoOpenFragment();
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                            R.anim.slide_in_left, R.anim.slide_out_left)
                    .replace(R.id.loginregisterContent, frag)
                    .addToBackStack(SchoolNoOpenFragment.TAG)
                    .commit();
        } else {
            Intent intent = new Intent(getContext(), MainActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                getFragmentManager().popBackStack();
                break;
        }
    }
}

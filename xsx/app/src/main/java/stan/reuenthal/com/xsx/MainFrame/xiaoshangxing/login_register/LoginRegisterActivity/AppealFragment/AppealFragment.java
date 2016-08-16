package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.AppealFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.AppealOKFragment.AppealOKFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginRegisterActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

/**
 * Created by FengChaoQun
 * on 2016/6/25
 */
public class AppealFragment extends BaseFragment implements AppealContract.View, View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-AlterPasswordFragment";

    private View mView;
    private AppealContract.Presenter mPresenter;
    private EditText name, phoneNumber, ID, studentID, EC, goSchoolTime, registerTime, school;
    private View back;
    private Button appealNow;

    public static AppealFragment newInstance() {
        return new AppealFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_appeal, container, false);
        mView = view;
        initView();
        setmPresenter(new AppealPresenter(this));
        return view;
    }

    private void initView() {
        name = (EditText) mView.findViewById(R.id.name);
        phoneNumber = (EditText) mView.findViewById(R.id.phone_number);
        ID = (EditText) mView.findViewById(R.id.id);
        EC = (EditText) mView.findViewById(R.id.EC);
        goSchoolTime = (EditText) mView.findViewById(R.id.goSchoolTime);
        studentID = (EditText) mView.findViewById(R.id.studentID);
        registerTime = (EditText) mView.findViewById(R.id.registerTime);
        school = (EditText) mView.findViewById(R.id.school);

        back = mView.findViewById(R.id.back);
        back.setOnClickListener(this);
        appealNow = (Button) mView.findViewById(R.id.btn_appeal);
        appealNow.setOnClickListener(this);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.isContentOK();
            }
        };

        name.addTextChangedListener(textWatcher);
        phoneNumber.addTextChangedListener(textWatcher);
        ID.addTextChangedListener(textWatcher);
        EC.addTextChangedListener(textWatcher);
        goSchoolTime.addTextChangedListener(textWatcher);
        studentID.addTextChangedListener(textWatcher);
        registerTime.addTextChangedListener(textWatcher);
        school.addTextChangedListener(textWatcher);

    }

    @Override
    public void setmPresenter(@Nullable AppealContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public String getRegisterTime() {
        return registerTime.getText().toString();
    }

    @Override
    public String getGoSchoolTime() {
        return goSchoolTime.getText().toString();
    }

    @Override
    public String getEC() {
        return EC.getText().toString();
    }

    @Override
    public String getStudentID() {
        return studentID.getText().toString();
    }

    @Override
    public String getSchool() {
        return school.getText().toString();
    }

    @Override
    public String getID() {
        return ID.getText().toString();
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber.getText().toString();
    }

    @Override
    public String getName() {
        return name.getText().toString();
    }

    @Override
    public void gotoAppealComplete() {
        AppealOKFragment frag = ((LoginRegisterActivity) mActivity).getAppealOKFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.loginregisterContent, frag)
                .addToBackStack(AppealOKFragment.TAG)
                .commit();
    }

    @Override
    public void setButtonState(boolean state) {
        if (state) {
            appealNow.setEnabled(true);
            appealNow.setAlpha(1);
        } else {
            appealNow.setEnabled(false);
            appealNow.setAlpha(0.5f);
        }
    }

    @Override
    public void clickOnBack() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                clickOnBack();
                break;
            case R.id.btn_appeal:
                mPresenter.clickOnAppeal();
                break;
        }
    }
}

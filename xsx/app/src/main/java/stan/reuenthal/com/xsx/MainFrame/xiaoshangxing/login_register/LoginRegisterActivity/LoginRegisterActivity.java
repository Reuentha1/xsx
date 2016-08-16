package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.AlterPasswordFragment.AlterPasswordFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.AppealFragment.AppealFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.AppealOKFragment.AppealOKFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.InputAccountFragment.InputAccountFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginFragment.LoginFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.NoEmailFragment.NoEmailFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RetrieveByMesFragment.RetrieveByMesFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RgInputPhoNumberFragment.RgInputPhoNumberFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.RgInputVertifyCodeFragment.RgInputVertifyCodeFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SchoolNoOpenFragment.SchoolNoOpenFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SelectSchoolFreagment.SelectSchoolFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SendEmailFragment.SendEmailFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SerchFragment.SerchFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SetPasswordFragment.SetPasswordFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

/**
 * Created by FengChaoQun
 * on 2016/6/22
 */
public class LoginRegisterActivity extends BaseActivity {
    public static final String TAG = BaseActivity.TAG + "-LoginRegisterActivity";
    public static final String INTENT_TYPE = "INTENT_TYPE";//判断是显示注册还是登录
    public static final int LOGIN = 10001;//再次登录
    public static final int REGISTER = 20000;//注册

    private LoginFragment loginFragment;
    private RgInputPhoNumberFragment rgInputPhoNumberFragment;
    private RgInputVertifyCodeFragment rgInputVertifyCodeFragment;
    private SetPasswordFragment setPasswordFragment;
    private RetrieveByMesFragment retrieveByMesFragment;
    private AlterPasswordFragment alterPasswordFragment;
    private InputAccountFragment inputEmailFragment;
    private SendEmailFragment sendEmailFragment;
    private NoEmailFragment noEmailFragment;
    private AppealFragment appealFragment;
    private AppealOKFragment appealOKFragment;
    private SelectSchoolFragment selectSchoolFragment;
    private SchoolNoOpenFragment schoolNoOpenFragment;
    private SerchFragment serchFragment;

    private String phoneNumer = "0000";
    private String emai = "599301283@qq.com";
    private String school = "江南大学";


    private boolean is_RgInputVertifyCodeFragment;//记录当前是否是InputVertifyCodeFragment在显示
    private boolean is_finish;//记录是否关闭该Activity
    private boolean is_RetrieveByMesFragment;//记录是否是RetrieveByMesFragment在显示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        BaseFragment frag = (BaseFragment) mFragmentManager.findFragmentById(R.id.loginregisterContent);
        if (frag != null) {
            return;
        }
        initAllViews();
        int type = getIntent().getIntExtra(INTENT_TYPE, LOGIN);
        if (type == LOGIN) {
            frag = getLoginFragment();
            mFragmentManager.beginTransaction().add(R.id.loginregisterContent,
                    frag, LoginFragment.TAG).commit();
//            frag = getRgInputVertifyCodeFragment();
//            mFragmentManager.beginTransaction().add(R.id.loginregisterContent,
//                    frag, RgInputVertifyCodeFragment.TAG).commit();
//            frag = getSetPasswordFragment();
//            mFragmentManager.beginTransaction().add(R.id.loginregisterContent,
//                    frag, SetPasswordFragment.TAG).commit();
//            frag = getAlterPasswordFragment();
//            mFragmentManager.beginTransaction().add(R.id.loginregisterContent,
//                    frag, AlterPasswordFragment.TAG).commit();
//            frag = getInputEmailFragment();
//            mFragmentManager.beginTransaction().add(R.id.loginregisterContent,
//                    frag, InputEmailFragment.TAG).commit();
//            frag = getSendEmailFragment();
//            mFragmentManager.beginTransaction().add(R.id.loginregisterContent,
//                    frag, SendEmailFragment.TAG).commit();
//            frag = getNoEmailFragment();
//            mFragmentManager.beginTransaction().add(R.id.loginregisterContent,
//                    frag, NoEmailFragment.TAG).commit();
//            frag = getSelectSchoolFragment();
//            mFragmentManager.beginTransaction().add(R.id.loginregisterContent,
//                    frag, SelectSchoolFragment.TAG).commit();
//            frag = getSchoolNoOpenFragment();
//            mFragmentManager.beginTransaction().add(R.id.loginregisterContent,
//                    frag, SchoolNoOpenFragment.TAG).commit();
//            frag = getSerchFragment();
//            mFragmentManager.beginTransaction().add(R.id.loginregisterContent,
//                    frag, SerchFragment.TAG).commit();
        } else {
            frag = getRgInputPhoNumberFragment();
            mFragmentManager.beginTransaction().add(R.id.loginregisterContent, frag, RgInputPhoNumberFragment.TAG).commit();
        }
    }

    public void initAllViews() {
        Fragment frag;

        frag = mFragmentManager.findFragmentByTag(LoginFragment.TAG);
        loginFragment = (frag == null) ? LoginFragment.newInstance() : (LoginFragment) frag;

        frag = mFragmentManager.findFragmentByTag(RgInputPhoNumberFragment.TAG);
        rgInputPhoNumberFragment = (frag == null) ?
                RgInputPhoNumberFragment.newInstance() : (RgInputPhoNumberFragment) frag;

        frag = mFragmentManager.findFragmentByTag(RgInputVertifyCodeFragment.TAG);
        rgInputVertifyCodeFragment = (frag == null) ?
                RgInputVertifyCodeFragment.newInstance() : (RgInputVertifyCodeFragment) frag;

        frag = mFragmentManager.findFragmentByTag(SetPasswordFragment.TAG);
        setPasswordFragment = (frag == null) ?
                SetPasswordFragment.newInstance() : (SetPasswordFragment) frag;

        frag = mFragmentManager.findFragmentByTag(RetrieveByMesFragment.TAG);
        retrieveByMesFragment = (frag == null) ?
                RetrieveByMesFragment.newInstance() : (RetrieveByMesFragment) frag;

        frag = mFragmentManager.findFragmentByTag(AlterPasswordFragment.TAG);
        alterPasswordFragment = (frag == null) ?
                AlterPasswordFragment.newInstance() : (AlterPasswordFragment) frag;

        frag = mFragmentManager.findFragmentByTag(InputAccountFragment.TAG);
        inputEmailFragment = (frag == null) ?
                InputAccountFragment.newInstance() : (InputAccountFragment) frag;

        frag = mFragmentManager.findFragmentByTag(SendEmailFragment.TAG);
        sendEmailFragment = (frag == null) ?
                SendEmailFragment.newInstance() : (SendEmailFragment) frag;

        frag = mFragmentManager.findFragmentByTag(NoEmailFragment.TAG);
        noEmailFragment = (frag == null) ?
                NoEmailFragment.newInstance() : (NoEmailFragment) frag;

        frag = mFragmentManager.findFragmentByTag(AppealFragment.TAG);
        appealFragment = (frag == null) ?
                AppealFragment.newInstance() : (AppealFragment) frag;

        frag = mFragmentManager.findFragmentByTag(AppealOKFragment.TAG);
        appealOKFragment = (frag == null) ?
                AppealOKFragment.newInstance() : (AppealOKFragment) frag;

        frag = mFragmentManager.findFragmentByTag(SelectSchoolFragment.TAG);
        selectSchoolFragment = (frag == null) ?
                SelectSchoolFragment.newInstance() : (SelectSchoolFragment) frag;

        frag = mFragmentManager.findFragmentByTag(SchoolNoOpenFragment.TAG);
        schoolNoOpenFragment = (frag == null) ?
                SchoolNoOpenFragment.newInstance() : (SchoolNoOpenFragment) frag;

        frag = mFragmentManager.findFragmentByTag(SerchFragment.TAG);
        serchFragment = (frag == null) ?
                SerchFragment.newInstance() : (SerchFragment) frag;
    }

    public void resetRgInputVertifyCodeFragment() {
        rgInputVertifyCodeFragment = RgInputVertifyCodeFragment.newInstance();
    }

    public LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public RgInputPhoNumberFragment getRgInputPhoNumberFragment() {
        return rgInputPhoNumberFragment;
    }

    public RgInputVertifyCodeFragment getRgInputVertifyCodeFragment() {
        return rgInputVertifyCodeFragment;
    }

    public SendEmailFragment getSendEmailFragment() {
        return sendEmailFragment;
    }

    public NoEmailFragment getNoEmailFragment() {
        return noEmailFragment;
    }

    public RetrieveByMesFragment getRetrieveByMesFragment() {
        return retrieveByMesFragment;
    }

    public AlterPasswordFragment getAlterPasswordFragment() {
        return alterPasswordFragment;
    }

    public InputAccountFragment getInputEmailFragment() {
        return inputEmailFragment;
    }

    public AppealFragment getAppealFragment() {
        return appealFragment;
    }

    public AppealOKFragment getAppealOKFragment() {
        return appealOKFragment;
    }

    public SelectSchoolFragment getSelectSchoolFragment() {
        return selectSchoolFragment;
    }

    public SchoolNoOpenFragment getSchoolNoOpenFragment() {
        return schoolNoOpenFragment;
    }

    public SerchFragment getSerchFragment() {
        return serchFragment;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPhoneNumer() {
        return phoneNumer;
    }

    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumer = phoneNumer;
    }

    public boolean get_is_RgInputVertifyCodeFragment() {
        return is_RgInputVertifyCodeFragment;
    }

    public void setIs_RgInputVertifyCodeFragment(boolean is_RgInputVertifyCodeFragment) {
        this.is_RgInputVertifyCodeFragment = is_RgInputVertifyCodeFragment;
    }

    public boolean get_is_RetrieveByMesFragment() {
        return is_RetrieveByMesFragment;
    }

    public void setIs_RetrieveByMesFragment(boolean is_RetrieveByMesFragment) {
        this.is_RetrieveByMesFragment = is_RetrieveByMesFragment;
    }

    public SetPasswordFragment getSetPasswordFragment() {
        return setPasswordFragment;
    }

    public boolean is_finish() {
        return is_finish;
    }

    public void setIs_finish(boolean is_finish) {
        this.is_finish = is_finish;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    /*
     **describe:有的fragment需要截获back键事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (get_is_RgInputVertifyCodeFragment()) {
                getRgInputVertifyCodeFragment().clickOnBack();
                return true;
            } else if (is_finish) {
                finish();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    public String toString() {
        return TAG;
    }
}

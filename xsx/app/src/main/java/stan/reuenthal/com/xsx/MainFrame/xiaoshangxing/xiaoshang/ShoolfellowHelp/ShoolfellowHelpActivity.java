package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.SelectPerson.SelectPersonActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.MyShoolfellowHelp.MyShoolHelpFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.ShoolfellowHelpFragment.ShoolfellowHelpFragment;

/**
 * Created by FengChaoQun
 * on 2016/7/20
 */
public class ShoolfellowHelpActivity extends BaseActivity implements HelpContract.View {
    public static final String TAG = BaseActivity.TAG + "-ShoolfellowHelpActivity";

    private ShoolfellowHelpFragment shoolfellowHelpFragment;
    private MyShoolHelpFragment myShoolHelpFragment;
    private HelpContract.Presenter mPresenter;

    private boolean isHideMenu;//记录是否需要点击返回键隐藏菜单


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_fraglayout);
        setmPresenter(new HelpPresenter(this, this));
        BaseFragment frag = (BaseFragment) mFragmentManager.findFragmentById(R.id.main_fragment);
        if (frag != null) {
            return;
        }
        initAllFragments();
    }

    private void initAllFragments() {
        Fragment frag;

        frag = mFragmentManager.findFragmentByTag(ShoolfellowHelpFragment.TAG);
        shoolfellowHelpFragment = (frag == null) ? ShoolfellowHelpFragment.newInstance() : (ShoolfellowHelpFragment) frag;

        frag = mFragmentManager.findFragmentByTag(MyShoolHelpFragment.TAG);
        myShoolHelpFragment = (frag == null) ? MyShoolHelpFragment.newInstance() : (MyShoolHelpFragment) frag;

        frag = getMyShoolHelpFragment();
        mFragmentManager.beginTransaction().add(R.id.main_fragment,
                frag, MyShoolHelpFragment.TAG).commit();
        frag = getShoolfellowHelpFragment();
        mFragmentManager.beginTransaction().add(R.id.main_fragment,
                frag, ShoolfellowHelpFragment.TAG).commit();
    }

    public ShoolfellowHelpFragment getShoolfellowHelpFragment() {
        return shoolfellowHelpFragment;
    }

    public MyShoolHelpFragment getMyShoolHelpFragment() {
        return myShoolHelpFragment;
    }

    public boolean isHideMenu() {
        return isHideMenu;
    }

    public void setHideMenu(boolean hideMenu) {
        isHideMenu = hideMenu;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isHideMenu) {
                myShoolHelpFragment.showHideMenu(false);
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    public void showTransmitDialog() {
        final Dialog dialog=new Dialog(this,R.style.ActionSheetDialog);
        View dialogView=View.inflate(this,R.layout.school_help_transmit_dialog,null);
        dialog.setContentView(dialogView);
        TextView cancle=(TextView) dialogView.findViewById(R.id.cancel);
        TextView send=(TextView) dialogView.findViewById(R.id.send);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.transmit(dialog);
            }
        });
        dialog.show();
        LocationUtil.setWidth(this,dialog,getResources().getDimensionPixelSize(R.dimen.x900));
    }

    @Override
    public void showTransmitSuccess() {
        DialogUtils.Dialog_No_Button dialog_no_button =
                new DialogUtils.Dialog_No_Button(ShoolfellowHelpActivity.this, "已分享");
        final Dialog notice_dialog = dialog_no_button.create();
        notice_dialog.show();
        LocationUtil.setWidth(ShoolfellowHelpActivity.this, notice_dialog,
                getResources().getDimensionPixelSize(R.dimen.x420));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                notice_dialog.dismiss();
            }
        }, 500);
    }

    @Override
    public void setmPresenter(@Nullable HelpContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== SelectPersonActivity.SELECT_PERSON_CODE ){
            if (data!=null){
                if (data.getStringArrayListExtra(SelectPersonActivity.SELECT_PERSON).size()>0){
                    showTransmitDialog();
                }else {
                    Toast.makeText(ShoolfellowHelpActivity.this, "未选择联系人", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

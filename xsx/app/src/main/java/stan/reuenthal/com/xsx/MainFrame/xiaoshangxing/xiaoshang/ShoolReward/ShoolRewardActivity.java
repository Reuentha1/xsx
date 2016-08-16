package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.SelectPerson.SelectPersonActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.MyShoolReward.MyShoolRewardFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.ShoolRewardFragment.ShoolRewardFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.MyShoolfellowHelp.MyShoolHelpFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.ShoolfellowHelpFragment.ShoolfellowHelpFragment;

/**
 * Created by FengChaoQun
 * on 2016/7/20
 */
public class ShoolRewardActivity extends BaseActivity implements RewardContract.View {
    public static final String TAG = BaseActivity.TAG + "-ShoolRewardActivity";
    public static final int SELECT_PERSON = 10001;
    private ShoolRewardFragment shoolRewardFragment;
    private MyShoolRewardFragment myShoolRewardFragment;
    private stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.collect.collectFragment collectFragment;
    private RewardContract.Presenter mPresenter;

    private boolean isHideMenu;//记录是否需要点击返回键隐藏菜单
    private boolean isCollect;//记录是否是collect界面在显示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_fraglayout);
        setmPresenter(new RewardPresenter(this,this));
        BaseFragment frag = (BaseFragment) mFragmentManager.findFragmentById(R.id.main_fragment);
        if (frag != null) {
            return;
        }
        initAllFrafments();
    }

    private void initAllFrafments() {
        Fragment frag;

        frag = mFragmentManager.findFragmentByTag(ShoolRewardFragment.TAG);
        shoolRewardFragment = (frag == null) ? ShoolRewardFragment.newInstance() : (ShoolRewardFragment) frag;

        frag = mFragmentManager.findFragmentByTag(MyShoolRewardFragment.TAG);
        myShoolRewardFragment = (frag == null) ? MyShoolRewardFragment.newInstance() : (MyShoolRewardFragment) frag;

        frag = mFragmentManager.findFragmentByTag(stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.collect.collectFragment.TAG);
        collectFragment = (frag == null) ? stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.collect.collectFragment.newInstance() : (stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.collect.collectFragment) frag;


        mFragmentManager.beginTransaction().add(R.id.main_fragment,
                collectFragment, ShoolfellowHelpFragment.TAG).commit();

        mFragmentManager.beginTransaction().add(R.id.main_fragment,
                myShoolRewardFragment, ShoolfellowHelpFragment.TAG).commit();

        mFragmentManager.beginTransaction().add(R.id.main_fragment,
                shoolRewardFragment, MyShoolHelpFragment.TAG).commit();

        mFragmentManager.beginTransaction().hide(collectFragment).hide(myShoolRewardFragment)
                .show(shoolRewardFragment).commit();
    }

    public ShoolRewardFragment getShoolRewardFragment() {
        return shoolRewardFragment;
    }

    public MyShoolRewardFragment getMyShoolRewardFragment() {
        return myShoolRewardFragment;
    }

    public stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.collect.collectFragment getCollectFragment() {
        return collectFragment;
    }

    public boolean isHideMenu() {
        return isHideMenu;
    }

    public void setHideMenu(boolean hideMenu) {
        isHideMenu = hideMenu;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isHideMenu) {
                myShoolRewardFragment.showHideMenu(false);
                return true;
            } else if (isCollect){
                collectFragment.showHideMenu(false);
                return true;
            }
            else {
                return super.onKeyDown(keyCode, event);
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    public void showTransmitDialog(){
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
                new DialogUtils.Dialog_No_Button(ShoolRewardActivity.this, "已分享");
        final Dialog notice_dialog = dialog_no_button.create();
        notice_dialog.show();
        LocationUtil.setWidth(ShoolRewardActivity.this, notice_dialog,
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
    public void setmPresenter(@Nullable RewardContract.Presenter presenter) {
            this.mPresenter=presenter;
    }

    public void gotoSelectPerson(){
        Intent intent=new Intent(this, SelectPersonActivity.class);
        startActivityForResult(intent,SelectPersonActivity.SELECT_PERSON_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== SelectPersonActivity.SELECT_PERSON_CODE ){
            if (data!=null){
                if (data.getStringArrayListExtra(SelectPersonActivity.SELECT_PERSON).size()>0){
                    showTransmitDialog();
                }else {
                    showToast("未选择联系人");
                }
            }
        }
    }
}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import stan.reuenthal.com.xsx.IMUI.HomeFragment;
import stan.reuenthal.com.xsx.IMpanel.TFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotAndPicture.EmotionGrideViewAdapter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionEdittext.EmoticonsEditText;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputBoxLayout;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.normalUtils.SPUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.WoFrafment.WoFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.XiaoShangFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import stan.reuenthal.com.xsx.R;

/**
 * Created by FengChaoQun
 * on 2016/6/21
 */
public class MainActivity extends BaseActivity {
    public static final String TAG = BaseActivity.TAG + "-MainActivity";

    @Bind(R.id.image_xiaoshang)
    ImageView imageXiaoshang;
    @Bind(R.id.xiaoshang)
    TextView xiaoshang;
    @Bind(R.id.xiaoshang_lay)
    RelativeLayout xiaoshangLay;
    @Bind(R.id.image_yujian)
    ImageView imageYujian;
    @Bind(R.id.yujian)
    TextView yujian;
    @Bind(R.id.yujian_lay)
    RelativeLayout yujianLay;
    @Bind(R.id.image_wo)
    ImageView imageWo;
    @Bind(R.id.wo)
    TextView wo;
    @Bind(R.id.wolay)
    RelativeLayout wolay;
    @Bind(R.id.radio_layout)
    LinearLayout radioLayout;

    private int current;

    private WoFragment woFragment;
    private XiaoShangFragment xiaoShangFragment;
    private HomeFragment mainFragment;

    private GridView gridView;
    private List<View> viewlist = new ArrayList<View>();
    private ViewPager viewPager;
    private LinearLayout emotion_lay;
    private EmotionGrideViewAdapter adapter;

    private RelativeLayout comment_input_layout;
    private EmoticonsEditText emoticonsEditText;
    private TextView send;
    private ImageView emot;
    private View normal_emot, favorite,delete_emot;
    private RelativeLayout edit_and_emot;
    private int screenHeight;

    private boolean destroyed = false;


    private InputBoxLayout inputBoxLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
        ButterKnife.bind(this);
        initInputBox();
        initAllFragments();
    }

    private TFragment onInit() {
        showMainFragment();
        return mainFragment;
    }

    private void showMainFragment() {
        if (mainFragment == null && !isDestroyedCompatible()) {
            mainFragment = new HomeFragment();
            //switchFragmentContent(mainFragment);
        }
    }

    protected void switchFragmentContent(TFragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(fragment.getContainerId(), fragment);
        try {
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isDestroyedCompatible() {
        if (Build.VERSION.SDK_INT >= 17) {
            return isDestroyedCompatible17();
        } else {
            return destroyed || super.isFinishing();
        }
    }

    private boolean isDestroyedCompatible17() {
        return super.isDestroyed();
    }

    private void initInputBox(){
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.edit_and_emot);
        inputBoxLayout=new InputBoxLayout(this,relativeLayout,this);
    }

    private void initAllFragments() {
        SPUtils.put(this, SPUtils.IS_FIRS_COME, false);//当这个页面打开时，表明不是第一次进入APP了
        SPUtils.put(this, SPUtils.IS_QUIT, false);//当这个页面打开时，清除退出记录
        Fragment frag;
        frag = mFragmentManager.findFragmentByTag(WoFragment.TAG);
        woFragment = (frag == null) ? WoFragment.newInstance() : (WoFragment) frag;

        frag = mFragmentManager.findFragmentByTag(XiaoShangFragment.TAG);
        xiaoShangFragment = (frag == null) ? XiaoShangFragment.newInstance() : (XiaoShangFragment) frag;

        if (!xiaoShangFragment.isAdded()){
            mFragmentManager.beginTransaction().add(R.id.main_fragment,xiaoShangFragment,XiaoShangFragment.TAG)
                    .commit();
        }
        if (!woFragment.isAdded()){
            mFragmentManager.beginTransaction().add(R.id.main_fragment,woFragment,WoFragment.TAG)
                    .commit();
        }

        setXiaoshang(true);
    }

    @OnClick({R.id.xiaoshang_lay, R.id.yujian_lay, R.id.wolay, R.id.emotion, R.id.normal_emot, R.id.favorite
            , R.id.send,R.id.delete_emot})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xiaoshang_lay:
                if (current == 1) {
                    return;
                } else {
                    setXiaoshang(true);
                    setYUjian(false);
                    setWo(false);
                }

                break;
            case R.id.yujian_lay:
                if (current == 2) {
                    return;
                } else {
                    setXiaoshang(false);
                    setYUjian(true);
                    setWo(false);
                }
                break;
            case R.id.wolay:
                if (current == 3) {
                    return;
                } else {
                    setXiaoshang(false);
                    setYUjian(false);
                    setWo(true);
                }
                break;
            case R.id.emotion:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.normal_emot:
                viewPager.setCurrentItem(0);
                break;
            case R.id.favorite:
                viewPager.setCurrentItem(1);
                break;
            case R.id.send:
//                showOrHideLayout(false);
                inputBoxLayout.showOrHideLayout(false);
                emoticonsEditText.setText("");
                break;
            case R.id.delete_emot:
                emoticonsEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
        }
    }

    private void setWo(boolean is) {
        if (is) {
            imageWo.setImageResource(R.mipmap.wo_on);
            wo.setTextColor(getResources().getColor(R.color.green1));

//            mFragmentManager.beginTransaction().replace(R.id.main_fragment,
//                    woFragment, XiaoShangFragment.TAG).commit();
            mFragmentManager.beginTransaction().hide(xiaoShangFragment).show(woFragment)
                    .commit();
            woFragment.autoRefresh();
            current = 3;
        } else {
            imageWo.setImageResource(R.mipmap.wo_off);
            wo.setTextColor(getResources().getColor(R.color.g0));
        }

    }

    private void setYUjian(boolean is) {
        if (is) {
            imageYujian.setImageResource(R.mipmap.yujian_on);
            yujian.setTextColor(getResources().getColor(R.color.green1));
            //mFragmentManager.beginTransaction().hide(woFragment).show(onInit()).commit();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment,onInit())
                    .addToBackStack(null)
                    .commit();
            current = 2;
        } else {
            imageYujian.setImageResource(R.mipmap.yujian_off);
            yujian.setTextColor(getResources().getColor(R.color.g0));
        }

    }

    private void setXiaoshang(boolean is) {
        if (is) {
            imageXiaoshang.setImageResource(R.mipmap.xiaoshang_on);
            xiaoshang.setTextColor(getResources().getColor(R.color.green1));

//            mFragmentManager.beginTransaction().replace(R.id.main_fragment,
//                    xiaoShangFragment, XiaoShangFragment.TAG).commit();
            mFragmentManager.beginTransaction().hide(woFragment).show(xiaoShangFragment)
                    .commit();
            current = 1;
        } else {
            imageXiaoshang.setImageResource(R.mipmap.xiaoshang_off);
            xiaoshang.setTextColor(getResources().getColor(R.color.g0));
        }
    }

    public InputBoxLayout getInputBoxLayout() {
        return inputBoxLayout;
    }

    @Override
    public String toString() {
        return TAG;
    }


    public TFragment addFragment(TFragment fragment) {
        List<TFragment> fragments = new ArrayList<TFragment>(1);
        fragments.add(fragment);

        List<TFragment> fragments2 = addFragments(fragments);
        return fragments2.get(0);
    }

    public List<TFragment> addFragments(List<TFragment> fragments) {
        List<TFragment> fragments2 = new ArrayList<TFragment>(fragments.size());

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        boolean commit = false;
        for (int i = 0; i < fragments.size(); i++) {
            // install
            TFragment fragment = fragments.get(i);
            int id = fragment.getContainerId();

            // exists
            TFragment fragment2 = (TFragment) fm.findFragmentById(id);

            if (fragment2 == null) {
                fragment2 = fragment;
                transaction.add(id, fragment);
                commit = true;
            }

            fragments2.add(i, fragment2);
        }

        if (commit) {
            try {
                transaction.commitAllowingStateLoss();
            } catch (Exception e) {

            }
        }

        return fragments2;
    }
}

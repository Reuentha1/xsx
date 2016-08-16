package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.HelpDetail;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.SelectPerson.SelectPersonActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionText.EmotinText;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.CirecleImage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by FengChaoQun
 * on 2016/7/20
 */
public class HelpDetailActivity extends BaseActivity implements HelpDetailContract.View {
    public static final String TAG = BaseFragment.TAG + "-HelpDetailActivity";

    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.myState)
    TextView myState;
    @Bind(R.id.more)
    ImageView more;
    @Bind(R.id.title)
    RelativeLayout title;
    @Bind(R.id.head_image)
    CirecleImage headImage;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.college)
    TextView college;
    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.text)
    EmotinText text;
    @Bind(R.id.detail)
    LinearLayout detail;
    @Bind(R.id.transmit_text)
    TextView transmitText;
    @Bind(R.id.comment_text)
    TextView commentText;
    @Bind(R.id.praise_text)
    TextView praiseText;
    @Bind(R.id.cursor)
    View cursor;
    @Bind(R.id.scrollview)
    ViewPager viewpager;
    @Bind(R.id.transmit)
    RelativeLayout transmit;
    @Bind(R.id.comment)
    RelativeLayout comment;
    @Bind(R.id.praiseOrCancel)
    TextView praiseOrCancel;
    @Bind(R.id.praise)
    RelativeLayout praise;

    private int currentItem = 2;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private HelpDetailContract.Presenter mPresenter;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpdetail);
        ButterKnife.bind(this);
        setmPresenter(new HelpDetailPresenter(this, this));
        init();
        moveImediate(currentItem);
    }

    private void init() {
        fragments.add(new TransmitListFrafment());
        fragments.add(new CommentListFrafment());
        fragments.add(new PraiseListFrafment());
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);

        viewpager.setAdapter(adapter);
        viewpager.setPageTransformer(true, new DepthPageTransformer());
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                moveToPosition(position + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setCurrentItem(1);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                moveToPosition(2);
            }
        }, 300);
        /*
        **describe:通过反射修改viewpager的滑动速度
        */
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewpager.getContext(),
                    new LinearInterpolator());
            field.set(viewpager, scroller);
            scroller.setmDuration(300);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("修改viewpager滑动速度", "失败");
        }
        setCount(10, 20, 30);
    }

    @OnClick({R.id.back, R.id.more, R.id.transmit_text, R.id.comment_text, R.id.praise_text, R.id.transmit, R.id.comment, R.id.praiseOrCancel, R.id.praise})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.more:
                showShareDialog();
                break;
            case R.id.transmit_text:
                viewpager.setCurrentItem(0);
                break;
            case R.id.comment_text:
                viewpager.setCurrentItem(1);
                break;
            case R.id.praise_text:
                viewpager.setCurrentItem(2);
                break;
            case R.id.transmit:
                gotoSelectPeson();
                break;
            case R.id.comment:
                gotoInput();
                break;
            case R.id.praiseOrCancel:
                break;
            case R.id.praise:
                mPresenter.praise();
                break;
        }
    }


    //    控制滑块滑动到指定位置
    @Override
    public void moveToPosition(int position) {
        int[] xy = new int[2];
        switch (position) {
            case 1:
                transmitText.getLocationOnScreen(xy);
                break;
            case 2:
                commentText.getLocationOnScreen(xy);
                break;
            case 3:
                praiseText.getLocationOnScreen(xy);
                break;
        }

        ValueAnimator animator = ValueAnimator.ofInt(cursor.getLeft(), xy[0]);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                cursor.layout((int) animation.getAnimatedValue(), cursor.getTop(),
                        cursor.getWidth() + (int) animation.getAnimatedValue(), cursor.getBottom());
            }
        });
        animator.setDuration(300);
        animator.start();
        Log.d("x", "" + xy[0]);
    }

    @Override
    public void moveImediate(int position) {
        int[] xy = new int[2];
        switch (position) {
            case 1:
                transmitText.getLocationOnScreen(xy);
                break;
            case 2:
                commentText.getLocationOnScreen(xy);
                break;
            case 3:
                praiseText.getLocationOnScreen(xy);
                break;
        }
        cursor.layout(xy[0], cursor.getTop(),
                cursor.getWidth() + xy[0], cursor.getBottom());
    }

    @Override
    public void showShareDialog() {
        final Dialog dialog = new Dialog(this, R.style.ActionSheetDialog);
        View view = View.inflate(this, R.layout.util_help_share_dialog, null);
        dialog.setContentView(view);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        Button button = (Button) view.findViewById(R.id.cancel);
        View xsx = view.findViewById(R.id.xsx);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        xsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpDetailActivity.this, InputActivity.class);
                intent.putExtra(InputActivity.EDIT_STATE, InputActivity.TRANSMIT);
                intent.putExtra(InputActivity.TRANSMIT_TYPE, InputActivity.SHOOLFELLOW_HELP);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
        LocationUtil.bottom_FillWidth(this, dialog);
    }

    @Override
    public void setmPresenter(@Nullable HelpDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setPraise() {
        if (praiseOrCancel.getText().equals("赞")) {
            praiseOrCancel.setText("取消");
        } else {
            praiseOrCancel.setText("赞");
        }
    }

    @Override
    public void gotoSelectPeson() {
        Intent intent_selectperson = new Intent(HelpDetailActivity.this, SelectPersonActivity.class);
        startActivityForResult(intent_selectperson, SelectPersonActivity.SELECT_PERSON_CODE);
    }

    @Override
    public void gotoInput() {
        Intent intent = new Intent(HelpDetailActivity.this, InputActivity.class);
        intent.putExtra(InputActivity.EDIT_STATE, InputActivity.COMMENT);
        startActivity(intent);
    }

    private void showTransmitDialog() {
        final Dialog dialog = new Dialog(this, R.style.ActionSheetDialog);
        View dialogView = View.inflate(this, R.layout.school_help_transmit_dialog, null);
        dialog.setContentView(dialogView);
        TextView cancle = (TextView) dialogView.findViewById(R.id.cancel);
        TextView send = (TextView) dialogView.findViewById(R.id.send);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.transmit();
                dialog.dismiss();
            }
        });
        dialog.show();
        LocationUtil.setWidth(this,dialog,getResources().getDimensionPixelSize(R.dimen.x900));
    }

    @Override
    public void setCount(int transmit, int comment, int praise) {
        transmitText.setText("转发" + transmit);
        commentText.setText("评论" + comment);
        praiseText.setText("赞" + praise);
    }

    @Override
    public void showTransmitSuccess() {
        DialogUtils.Dialog_No_Button dialog_no_button =
                new DialogUtils.Dialog_No_Button(HelpDetailActivity.this, "已分享");
        final Dialog notice_dialog = dialog_no_button.create();
        notice_dialog.show();
        LocationUtil.setWidth(HelpDetailActivity.this, notice_dialog,
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode ==SelectPersonActivity.SELECT_PERSON_CODE) {
            if (data != null) {
                if (data.getStringArrayListExtra(SelectPersonActivity.SELECT_PERSON).size() > 0) {
                    showTransmitDialog();
                } else {
                    Toast.makeText(HelpDetailActivity.this, "未选择联系人", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class FragAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            // TODO Auto-generated constructor stub
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            return mFragments.get(arg0);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mFragments.size();
        }

    }

}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.check_photo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.WoFrafment.check_photo.HackyViewPager;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.WoFrafment.check_photo.ImageDetailFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 图片查看器
 */
public class ReviewPictureActivity extends FragmentActivity {
    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";
    @Bind(R.id.pager)
    HackyViewPager pager;
    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.delete)
    ImageView delete;
    @Bind(R.id.title)
    RelativeLayout title;

    private HackyViewPager mPager;
    private int pagerPosition;
    private TextView indicator;
    private int current;
    private ArrayList<String> urls = new ArrayList<String>();
    private ImagePagerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_picture);
        ButterKnife.bind(this);

        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        urls = getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);


        mPager = (HackyViewPager) findViewById(R.id.pager);
        mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), urls);
        mPager.setAdapter(mAdapter);
        indicator = (TextView) findViewById(R.id.indicator);

        CharSequence text = getString(R.string.viewpager_indicator, 1, mPager.getAdapter().getCount());
        indicator.setText(text);
        // 更新下标
        mPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                CharSequence text = getString(R.string.viewpager_indicator, arg0 + 1, mPager.getAdapter().getCount());
                indicator.setText(text);
                current = arg0;
                Log.d("select", "" + arg0);
            }

        });
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }

        mPager.setCurrentItem(pagerPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }

    @OnClick({R.id.back, R.id.delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.delete:
                showSureDialog();
                break;
        }
    }

    public void showSureDialog() {
        final DialogUtils.Dialog_Center center = new DialogUtils.Dialog_Center(this);
        center.Message("确定删除吗?");
        center.Button("删除", "取消");
        center.MbuttonOnClick(new DialogUtils.Dialog_Center.buttonOnClick() {
            @Override
            public void onButton1() {
                urls.remove(current);
                refresh();
                center.close();
            }

            @Override
            public void onButton2() {
                center.close();
            }
        });
        Dialog dialog = center.create();
        dialog.show();
        LocationUtil.setWidth(this, dialog,
                getResources().getDimensionPixelSize(R.dimen.x780));
    }

    private void refresh() {
        if (urls.size()>0){
            if (current>0){
                mAdapter=new ImagePagerAdapter(getSupportFragmentManager(),urls);
                mPager.setAdapter(mAdapter);
                mPager.setCurrentItem(current-1);
            }else {
                mAdapter=new ImagePagerAdapter(getSupportFragmentManager(),urls);
                mPager.setAdapter(mAdapter);
                mPager.setCurrentItem(current);
            }

            CharSequence text = getString(R.string.viewpager_indicator, current + 1, mPager.getAdapter().getCount());
            indicator.setText(text);
        }else {
            mAdapter=new ImagePagerAdapter(getSupportFragmentManager(),urls);
            mPager.setAdapter(mAdapter);
        }

    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public ArrayList<String> fileList;

        public ImagePagerAdapter(FragmentManager fm, ArrayList<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList.get(position);
            return ImageDetailFragment.newInstance(url);
        }

    }

    @Override
    public void finish() {
        Intent intent=new Intent();
        intent.putExtra(InputActivity.SELECT_IMAGE_URLS,urls);
        setResult(InputActivity.REVIEW_PHOTO,intent);
        super.finish();
    }
}

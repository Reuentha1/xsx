package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.ImageBucket;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by FengChaoQun
 * on 2016/8/8
 */
public class AlbumActivity extends BaseActivity {
    @Bind(R.id.main_fragment)
    FrameLayout mainFragment;

    public static final String LIMIT = "LIMIT";
    public static final String SELECTED = "SELECTED";

    private AlbumListFragment albumListFragment;
    private ImageBucket current_imagebucket;
    private int limit;
    private List<String> select_image_urls = new ArrayList<String>();
    private int resultCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_fraglayout);
        ButterKnife.bind(this);
        BaseFragment frag = (BaseFragment) mFragmentManager.findFragmentById(R.id.main_fragment);
        if (frag != null) {
            return;
        }
        initAllFragments();
        initData();
    }

    private void initData() {
        setLimit(getIntent().getIntExtra(LIMIT, 9));
        setSelect_image_urls(getIntent().getStringArrayListExtra(SELECTED));
    }


    private void initAllFragments() {
        Fragment frag;

        frag = mFragmentManager.findFragmentByTag(AlbumListFragment.TAG);
        albumListFragment = (frag == null) ? AlbumListFragment.newInstance() : (AlbumListFragment) frag;

        frag = getAlbumListFragment();
        mFragmentManager.beginTransaction().add(R.id.main_fragment,
                frag, AlbumListFragment.TAG).commit();

    }

    public AlbumListFragment getAlbumListFragment() {
        return albumListFragment;
    }

    public ImageBucket getCurrent_imagebucket() {
        return current_imagebucket;
    }

    public void setCurrent_imagebucket(ImageBucket current_imagebucket) {
        this.current_imagebucket = current_imagebucket;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<String> getSelect_image_urls() {
        return select_image_urls;
    }

    public void setSelect_image_urls(List<String> select_image_urls) {
        this.select_image_urls = select_image_urls;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == InputActivity.SELECT_PHOTO_RESULT_1) {
            setSelect_image_urls(data.getStringArrayListExtra(InputActivity.SELECT_IMAGE_URLS));
        }
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(InputActivity.SELECT_IMAGE_URLS, (ArrayList<String>) select_image_urls);
        setResult(InputActivity.SELECT_PHOTO_FROM_ALBUM, intent);
        super.finish();
    }
}

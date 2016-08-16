package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.album;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.ImageBucket;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by FengChaoQun
 * on 2016/8/8
 */
public class AlbumDetailFragment extends BaseFragment {
    public static final String TAG = BaseFragment.TAG + "-AlbumDetailFragment";

    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.cancel)
    TextView cancel;
    @Bind(R.id.title_lay)
    RelativeLayout titleLay;
    @Bind(R.id.grideview)
    GridView grideview;
    @Bind(R.id.original)
    CheckBox original;
    @Bind(R.id.original_text)
    TextView originalText;
    @Bind(R.id.count)
    TextView count;
    @Bind(R.id.complete)
    RelativeLayout complete;
    @Bind(R.id.details_entry_layout)
    RelativeLayout detailsEntryLayout;

    private View view;
    private AlbumDetailAdapter albumDetailAdapter;
    private int limit;
    private ImageBucket imageBucket;
    private List<String> select_image_urls;
    private AlbumActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.activity_album_detail, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public static AlbumDetailFragment newInstance() {
        return new AlbumDetailFragment();
    }

    private void initView() {
        activity=(AlbumActivity)getActivity();
        limit = activity.getLimit();
        imageBucket = activity.getCurrent_imagebucket();
        select_image_urls =activity.getSelect_image_urls();
        albumDetailAdapter = new AlbumDetailAdapter(getContext(), limit,
                select_image_urls, imageBucket, this,(AlbumActivity)getActivity());
        grideview.setAdapter(albumDetailAdapter);
        setSelectCount(select_image_urls.size());
    }

    public void setSelectCount(int i) {
        count.setText("(" + i +"/"+ limit+")");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.back, R.id.cancel, R.id.original, R.id.original_text, R.id.details_entry_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                activity.setSelect_image_urls(albumDetailAdapter.getSelect_image_urls());
                getFragmentManager().popBackStack();
                break;
            case R.id.cancel:
                getFragmentManager().popBackStack();
                break;
            case R.id.original:
                break;
            case R.id.original_text:
                original.performClick();
                break;
            case R.id.details_entry_layout:
                activity.setSelect_image_urls(albumDetailAdapter.getSelect_image_urls());
                activity.finish();
                break;
        }
    }

    public void setSelect_image_urls(List<String> select_image_urls) {
        this.select_image_urls = select_image_urls;
        albumDetailAdapter.setSelect_image_urls(select_image_urls);
        setSelectCount(select_image_urls.size());
    }

    @Override
    public void onResume() {
        super.onResume();
        setSelect_image_urls(activity.getSelect_image_urls());
    }
}

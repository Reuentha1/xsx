package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.album;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.AlbumHelper;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.ImageBucket;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by FengChaoQun
 * on 2016/8/8
 */
public class AlbumListFragment extends BaseFragment {
    public static final String TAG = BaseFragment.TAG + "-AlbumListFragment";
    @Bind(R.id.myState)
    TextView myState;
    @Bind(R.id.cancel)
    TextView cancel;
    @Bind(R.id.title)
    RelativeLayout title;
    @Bind(R.id.listview)
    ListView listview;
    private View view;
    private AlbumHelper helper;
    public List<ImageBucket> contentList;
    private AlbumListAdpter albumListAdpter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.frag_album_list, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public static AlbumListFragment newInstance() {
        return new AlbumListFragment();
    }

    private void initView() {
        helper = AlbumHelper.getHelper();
        helper.init(getContext());
        contentList = helper.getImagesBucketList(false);
//        for (int i = 0; i < contentList.size(); i++) {
//            dataList.addAll(contentList.get(i).imageList);
//        }
//        ImageItem imageList[] = (ImageItem[]) dataList.toArray();
//        Arrays.sort(imageList, new RencentImageComparator());
//        ImageBucket rencent = new ImageBucket();
//        rencent.bucketName = "最近照片";
//        List<ImageItem> temp_list=new ArrayList<>();
//        for (int i=0;i<100;i++){
//            temp_list.add(imageList[i]);
//        }
//        rencent.imageList=temp_list;
//        contentList.add(0,rencent);
        albumListAdpter = new AlbumListAdpter(getContext(), 1, (ArrayList<ImageBucket>) contentList);
        listview.setAdapter(albumListAdpter);
        View view = new View(getContext());
        view.setMinimumHeight(getResources().getDimensionPixelSize(R.dimen.y48));
        listview.addHeaderView(view);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    return;
                }

                AlbumActivity activity = (AlbumActivity) getActivity();
                activity.setCurrent_imagebucket(contentList.get(position - 1));
                AlbumDetailFragment fragment = AlbumDetailFragment.newInstance();

                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                                R.anim.slide_in_left, R.anim.slide_out_left)
                        .replace(R.id.main_fragment, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.cancel)
    public void onClick() {
    }
}

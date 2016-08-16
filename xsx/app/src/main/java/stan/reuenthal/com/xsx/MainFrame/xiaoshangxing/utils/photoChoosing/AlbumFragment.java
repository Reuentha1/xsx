package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.photoChoosing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.AlbumHelper;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.Bimp;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.ImageBucket;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.ImageItem;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.PublicWay;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.Res;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个是进入相册显示所有图片的界面
 *
 * @author king
 * @version 2014年10月18日  下午11:47:15
 * @QQ:595163260
 */
public class AlbumFragment extends BaseFragment {
    public static final String TAG = BaseFragment.TAG + "-AlbumFragment";

    //显示手机里的所有图片的列表控件
    private GridView gridView;
    //当手机里没有图片时，提示用户没有图片的控件
    private TextView tv;
    //gridView的adapter
    private AlbumGridViewAdapter gridImageAdapter;
    //完成按钮
    private Button okButton;
    // 返回按钮
    private Button back;
    // 取消按钮
    private Button cancel;
    private Intent intent;
    // 预览按钮
    private Button preview;
    //private Context mContext;
    private ArrayList<ImageItem> dataList;
    private AlbumHelper helper;
    public static List<ImageBucket> contentList;
//    public static Bitmap bitmap;
    private View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_camera_album, container, false);

        //注册一个广播，这个广播主要是用于在GalleryActivity进行预览时，防止当所有图片都删除完后，再回到该页面时被取消选中的图片仍处于选中状态
        IntentFilter filter = new IntentFilter("data.broadcast.action");
        getActivity().registerReceiver(broadcastReceiver, filter);
//        bitmap = BitmapFactory.decodeResource(getResources(), Res.getDrawableID("plugin_camera_no_pictures"));
        init();
        initListener();
        //这个函数主要用来控制预览和完成按钮的状态
        isShowOkBt();
        return mView;
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            //mContext.unregisterReceiver(this);
            // TODO Auto-generated method stub  
            gridImageAdapter.notifyDataSetChanged();//通过gridImageAdapter更新
        }
    };

    // 预览按钮的监听
    private class PreviewListener implements OnClickListener {
        public void onClick(View v) {
            if (Bimp.tempSelectBitmap.size() > 0) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                                R.anim.slide_in_left, R.anim.slide_out_left)
                        .addToBackStack(GalleryFragment.TAG)
                        .replace(R.id.photoChooseContent, new GalleryFragment(), GalleryFragment.TAG)
                        .commit();

            }
        }

    }

    // 完成按钮的监听
    private class AlbumSendListener implements OnClickListener {
        public void onClick(View v) {
//            getActivity().getSupportFragmentManager().popBackStack();
            getActivity().finish();
        }
    }

    // 返回按钮监听，返回到相册页面
    private class BackListener implements OnClickListener {
        public void onClick(View v) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                            R.anim.slide_in_left, R.anim.slide_out_left)
                    .addToBackStack(ImageFileFragment.TAG)
                    .replace(R.id.photoChooseContent, new ImageFileFragment(), ImageFileFragment.TAG)
                    .commit();
        }
    }

    // 取消按钮的监听
    private class CancelListener implements OnClickListener {
        public void onClick(View v) {
//            reportActivity.setCanceled(true);
            Bimp.tempSelectBitmap.clear();
//            getActivity().getSupportFragmentManager().popBackStack();
            getActivity().finish();
        }
    }


    // 初始化，给一些对象赋值
    private void init() {
        helper = AlbumHelper.getHelper();
        helper.init(getActivity().getApplicationContext());

        contentList = helper.getImagesBucketList(false);
        dataList = new ArrayList<ImageItem>();
        for (int i = 0; i < contentList.size(); i++) {
            dataList.addAll(contentList.get(i).imageList);
        }

        Res.init(getContext());

        back = (Button) mView.findViewById(Res.getWidgetID("back"));
        cancel = (Button) mView.findViewById(Res.getWidgetID("cancel"));
        cancel.setOnClickListener(new CancelListener());
        back.setOnClickListener(new BackListener());
        preview = (Button) mView.findViewById(Res.getWidgetID("preview"));
        preview.setOnClickListener(new PreviewListener());
        intent = getActivity().getIntent();
        //Bundle bundle = intent.getExtras();
        gridView = (GridView) mView.findViewById(Res.getWidgetID("myGrid"));
        gridImageAdapter = new AlbumGridViewAdapter(getActivity(), dataList,
                Bimp.tempSelectBitmap);
        gridView.setAdapter(gridImageAdapter);
        tv = (TextView) mView.findViewById(Res.getWidgetID("myText"));
        gridView.setEmptyView(tv);
        okButton = (Button) mView.findViewById(Res.getWidgetID("ok_button"));
        okButton.setText(Res.getString("finish") + "(" + Bimp.tempSelectBitmap.size()
                + "/" + PublicWay.num + ")");
    }

    private void initListener() {

        gridImageAdapter
                .setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(final ToggleButton toggleButton,
                                            int position, boolean isChecked, Button chooseBt) {
                        if (Bimp.tempSelectBitmap.size() >= PublicWay.num) {
                            toggleButton.setChecked(false);
                            chooseBt.setVisibility(View.GONE);
                            if (!removeOneData(dataList.get(position))) {
                                Toast.makeText(getActivity(), Res.getString("only_choose_num"),
                                        Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                        if (isChecked) {
                            chooseBt.setVisibility(View.VISIBLE);
                            Bimp.tempSelectBitmap.add(dataList.get(position));
                            okButton.setText(Res.getString("finish") + "(" + Bimp.tempSelectBitmap.size()
                                    + "/" + PublicWay.num + ")");
                        } else {
                            Bimp.tempSelectBitmap.remove(dataList.get(position));
                            chooseBt.setVisibility(View.GONE);
                            okButton.setText(Res.getString("finish") + "(" + Bimp.tempSelectBitmap.size() + "/" + PublicWay.num + ")");
                        }
                        isShowOkBt();
                    }
                });

        okButton.setOnClickListener(new AlbumSendListener());

    }

    private boolean removeOneData(ImageItem imageItem) {
        if (Bimp.tempSelectBitmap.contains(imageItem)) {
            Bimp.tempSelectBitmap.remove(imageItem);
            okButton.setText(Res.getString("finish") + "(" + Bimp.tempSelectBitmap.size() + "/" + PublicWay.num + ")");
            return true;
        }
        return false;
    }

    public void isShowOkBt() {
        if (Bimp.tempSelectBitmap.size() > 0) {
            okButton.setText(Res.getString("finish") + "(" + Bimp.tempSelectBitmap.size() + "/" + PublicWay.num + ")");
            preview.setPressed(true);
            okButton.setPressed(true);
            preview.setClickable(true);
            okButton.setClickable(true);
            okButton.setTextColor(Color.WHITE);
            preview.setTextColor(Color.WHITE);
        } else {
            okButton.setText(Res.getString("finish") + "(" + Bimp.tempSelectBitmap.size() + "/" + PublicWay.num + ")");
            preview.setPressed(false);
            preview.setClickable(false);
            okButton.setPressed(false);
            okButton.setClickable(false);
            okButton.setTextColor(Color.parseColor("#E1E0DE"));
            preview.setTextColor(Color.parseColor("#E1E0DE"));
        }
    }


    @Override
    public void onStart() {
        isShowOkBt();
        super.onStart();
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}

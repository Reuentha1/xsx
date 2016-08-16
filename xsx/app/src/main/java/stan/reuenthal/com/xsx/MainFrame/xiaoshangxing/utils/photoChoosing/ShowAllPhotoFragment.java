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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.Bimp;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.ImageItem;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.PublicWay;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.Res;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

import java.util.ArrayList;

/**
 * 这个是显示一个文件夹里面的所有图片时的界面
 *
 * @author king
 * @version 2014年10月18日  下午11:49:10
 * @QQ:595163260
 */
public class ShowAllPhotoFragment extends BaseFragment {
    private GridView gridView;
    private ProgressBar progressBar;
    private AlbumGridViewAdapter gridImageAdapter;
    // 完成按钮
    private Button okButton;
    // 预览按钮
    private Button preview;
    // 返回按钮
    private Button back;
    // 取消按钮
    private Button cancel;
    // 标题
    private TextView headTitle;

    public static ArrayList<ImageItem> dataList = new ArrayList<ImageItem>();
    private View mView;
    //    private ReportActivity reportActivity;
    private PhotoChoosingActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_camera_show_all_photo, container, false);
        mActivity = (PhotoChoosingActivity) getActivity();
        back = (Button) mView.findViewById(Res.getWidgetID("showallphoto_back"));
        cancel = (Button) mView.findViewById(Res.getWidgetID("showallphoto_cancel"));
        preview = (Button) mView.findViewById(Res.getWidgetID("showallphoto_preview"));
        okButton = (Button) mView.findViewById(Res.getWidgetID("showallphoto_ok_button"));
        headTitle = (TextView) mView.findViewById(Res.getWidgetID("showallphoto_headtitle"));
        String folderName = mActivity.getFolderName();
        if (folderName.length() > 8) {
            folderName = folderName.substring(0, 9) + "...";
        }
        headTitle.setText(folderName);
        cancel.setOnClickListener(new CancelListener());
        back.setOnClickListener(new BackListener());
        preview.setOnClickListener(new PreviewListener());
        init();
        initListener();
        isShowOkBt();
        return mView;
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub  
            gridImageAdapter.notifyDataSetChanged();
        }
    };

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

    private class BackListener implements OnClickListener {// 返回按钮监听

        public void onClick(View v) {
            getActivity().getSupportFragmentManager().popBackStack();
        }

    }

    private class CancelListener implements OnClickListener {// 取消按钮的监听

        public void onClick(View v) {
//            reportActivity.setCanceled(true);
            Bimp.tempSelectBitmap.clear();
//            getActivity().getSupportFragmentManager().popBackStack(ReportEvidenceFragment.TAG, 0);
            getActivity().finish();
        }
    }

    private void init() {
        IntentFilter filter = new IntentFilter("data.broadcast.action");
        getActivity().registerReceiver(broadcastReceiver, filter);
        progressBar = (ProgressBar) mView.findViewById(Res.getWidgetID("showallphoto_progressbar"));
        progressBar.setVisibility(View.GONE);
        gridView = (GridView) mView.findViewById(Res.getWidgetID("showallphoto_myGrid"));
        gridImageAdapter = new AlbumGridViewAdapter(getActivity(), dataList,
                Bimp.tempSelectBitmap);
        gridView.setAdapter(gridImageAdapter);
        okButton = (Button) mView.findViewById(Res.getWidgetID("showallphoto_ok_button"));
    }

    private void initListener() {

        gridImageAdapter
                .setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {
                    public void onItemClick(final ToggleButton toggleButton,
                                            int position, boolean isChecked,
                                            Button button) {
                        if (Bimp.tempSelectBitmap.size() >= PublicWay.num && isChecked) {
                            button.setVisibility(View.GONE);
                            toggleButton.setChecked(false);
                            Toast.makeText(getActivity(), Res.getString("only_choose_num"), Toast.LENGTH_SHORT)
                                    .show();
                            return;
                        }

                        if (isChecked) {
                            button.setVisibility(View.VISIBLE);
                            Bimp.tempSelectBitmap.add(dataList.get(position));
                            okButton.setText(Res.getString("finish") + "(" + Bimp.tempSelectBitmap.size()
                                    + "/" + PublicWay.num + ")");
                        } else {
                            button.setVisibility(View.GONE);
                            Bimp.tempSelectBitmap.remove(dataList.get(position));
                            okButton.setText(Res.getString("finish") + "(" + Bimp.tempSelectBitmap.size() + "/" + PublicWay.num + ")");
                        }
                        isShowOkBt();
                    }
                });

        okButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                okButton.setClickable(false);
//				if (PublicWay.photoService != null) {
//					PublicWay.selectedDataList.addAll(Bimp.tempSelectBitmap);
//					Bimp.tempSelectBitmap.clear();
//					PublicWay.photoService.onActivityResult(0, -2,
//							intent);
//				}

//                getActivity().getSupportFragmentManager().popBackStack(ReportEvidenceFragment.TAG, 0);
                getActivity().finish();

            }
        });

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
        // TODO Auto-generated method stub
        isShowOkBt();
        super.onStart();
    }

}

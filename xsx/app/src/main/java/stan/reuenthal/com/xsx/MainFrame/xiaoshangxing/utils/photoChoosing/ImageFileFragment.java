package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.photoChoosing;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.Bimp;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.Res;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;


/**
 * 这个类主要是用来进行显示包含图片的文件夹
 *
 * @author king
 * @version 2014年10月18日  下午11:48:06
 * @QQ:595163260
 */
public class ImageFileFragment extends BaseFragment {
    public static final String TAG = BaseFragment.TAG + "-ImageFileFragment";
    private FolderAdapter folderAdapter;
    private Button bt_cancel;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_camera_image_file, container, false);
        bt_cancel = (Button) mView.findViewById(Res.getWidgetID("cancel"));
        bt_cancel.setOnClickListener(new CancelListener());
        GridView gridView = (GridView) mView.findViewById(Res.getWidgetID("fileGridView"));
        TextView textView = (TextView) mView.findViewById(Res.getWidgetID("headerTitle"));
        textView.setText(Res.getString("photo"));
        folderAdapter = new FolderAdapter(getActivity());
        gridView.setAdapter(folderAdapter);
        return mView;
    }

    private class CancelListener implements OnClickListener {// 取消按钮的监听

        public void onClick(View v) {
//            reportActivity.setCanceled(true);
            Bimp.tempSelectBitmap.clear();
//            getActivity().getSupportFragmentManager().popBackStack(ReportEvidenceFragment.TAG, 0);
            getActivity().finish();
        }
    }


}

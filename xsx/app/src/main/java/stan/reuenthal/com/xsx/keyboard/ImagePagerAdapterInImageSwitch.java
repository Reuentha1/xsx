package stan.reuenthal.com.xsx.keyboard;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import stan.reuenthal.com.xsx.IMpanel.BaseZoomableImageView;
import stan.reuenthal.com.xsx.IMpanel.TActionBarActivity;
import stan.reuenthal.com.xsx.R;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class ImagePagerAdapterInImageSwitch extends PagerAdapter {
    private Context mContext;
    private List<String> mList;
    private LayoutInflater mInflater;
    private int viewPagerWidth, viewPagerHeight;
    private TActionBarActivity mActivity;

    public ImagePagerAdapterInImageSwitch(Context cx, List<String> list, LayoutInflater inflater, int width, int height, TActionBarActivity activity) {
        mContext = cx;
        mList = list;
        mInflater = inflater;
        viewPagerHeight = height;
        viewPagerWidth = width;
        mActivity = activity;
    }

    @Override
    public void destroyItem (ViewGroup container, int position, Object object) {
        View layout = (View)object;
        BaseZoomableImageView iv = (BaseZoomableImageView)layout.findViewById(R.id.imageView);
        iv.clear();
        container.removeView(layout);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return (arg0 == arg1);
    }

    @Override
    public Object instantiateItem (ViewGroup container, int position) {
//        ImageView iv = new ImageView(mContext);
        View layout = null;

//		if( Build.VERSION.SDK_INT >=  17 )
        if( Build.VERSION.SDK_INT >=  Build.VERSION_CODES.FROYO )
        {
            layout = mInflater.inflate(R.layout.nim_preview_image_layout_multi_touch, null);
        }
        else
        {
            layout = mInflater.inflate(R.layout.nim_preview_image_layout_zoom_control, null);
        }

        container.addView(layout);
        layout.setTag(position);
        viewPagerWidth = ScreenUtil.screenWidth;
        viewPagerHeight = ScreenUtil.screenHeight;

        return layout;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void setPrimaryItem (View container, int position, Object object) {
        ((PreviewImageFromLocalActivity) mActivity).updateCurrentImageView(position);
    }
}

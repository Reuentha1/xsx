package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.image;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import stan.reuenthal.com.xsx.R;

/**
 * Created by FengChaoQun
 * on 2016/7/14
 */
public class MyGlide {

    public static void with(Activity activity, String url, ImageView view) {
        Glide.with(activity)
                .load(url)
                .placeholder(R.mipmap.greyblock)
                .error(R.mipmap.greyblock)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

    public static void with(Fragment fragment, String url, ImageView view) {
        Glide.with(fragment)
                .load(url)
                .placeholder(R.mipmap.greyblock)
                .error(R.mipmap.greyblock)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

    public static void with(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.greyblock)
                .error(R.mipmap.greyblock)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }
}

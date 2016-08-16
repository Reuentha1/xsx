package stan.reuenthal.com.xsx.keyboard;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import sj.keyboard.utils.imageloader.ImageBase;
import sj.keyboard.utils.imageloader.ImageLoader;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class ImageLoadUtils extends ImageLoader {

    public ImageLoadUtils(Context context) {
        super(context);
    }

    @Override
    protected void displayImageFromFile(String imageUri, ImageView imageView) throws IOException {
        String filePath = Scheme.FILE.crop(imageUri);
        Glide.with(imageView.getContext())
                .load(filePath)
                .asBitmap()
                .into(imageView);
    }

    @Override
    protected void displayImageFromAssets(String imageUri, ImageView imageView) throws IOException {
        String uri = Scheme.cropScheme(imageUri);
        ImageBase.Scheme.ofUri(imageUri).crop(imageUri);
        Glide.with(imageView.getContext())
                .load(Uri.parse("file:///android_asset/" + uri))
                .into(imageView);
    }
}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.WoFrafment.check_photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.FileUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.photoview.PhotoViewAttacher;

import java.io.File;


/**
 * 单张图片显示Fragment
 */
public class ImageDetailFragment extends Fragment {
	private String mImageUrl;
	private ImageView mImageView;
	private PhotoViewAttacher mAttacher;

	public static ImageDetailFragment newInstance(String imageUrl) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
		args.putString("url", imageUrl);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.frag_image_detail, container, false);
		mImageView = (ImageView) v.findViewById(R.id.image);
		mAttacher = new PhotoViewAttacher(mImageView);

		mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

			@Override
			public void onPhotoTap(View arg0, float arg1, float arg2) {
				getActivity().finish();
			}
		});

		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Glide.with(getContext()).
				load(mImageUrl)
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.into(new GlideDrawableImageViewTarget(mImageView){
					@Override
					public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
						super.onResourceReady(resource, animation);
						mAttacher.update();
					}
				});
		File file=Glide.getPhotoCacheDir(getContext());
		Log.d("length",""+ FileUtils.getFormatSize(FileUtils.getFolderSize(file)));

//		mImageView.setImageResource(R.mipmap.test);
	}

	@Override
	public void onPause() {
		super.onPause();
		mImageView.destroyDrawingCache();
		mAttacher.cleanup();
	}
}

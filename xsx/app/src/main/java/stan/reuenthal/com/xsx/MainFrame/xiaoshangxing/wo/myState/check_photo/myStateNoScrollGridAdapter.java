package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState.check_photo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import stan.reuenthal.com.xsx.R;

import java.util.ArrayList;

public class myStateNoScrollGridAdapter extends BaseAdapter {

	/** 上下文 */
	private Context ctx;
	/** 图片Url集合 */
	private ArrayList<String> imageUrls;

	public myStateNoScrollGridAdapter(Context ctx, ArrayList<String> urls) {
		this.ctx = ctx;
		this.imageUrls = urls;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageUrls == null ? 0 : imageUrls.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imageUrls.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(ctx, R.layout.item_imageview_256, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
		Glide.with(ctx).
				load(imageUrls.get(position))
				.animate(R.anim.fade_in)
				.into(imageView);
		return view;
	}

}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotAndPicture;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.check_photo.ReviewPictureActivity;

import java.util.ArrayList;
import java.util.List;

public class ShowSelectPictureAdapter extends BaseAdapter {

	/** 上下文 */
	private Context ctx;
	private List<String> list=new ArrayList<String>();
	private InputActivity activity;
	public ShowSelectPictureAdapter(Context ctx, InputActivity activity) {
		this.ctx = ctx;
		this.activity=activity;
		this.list=activity.getSelect_image_urls();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = View.inflate(ctx, R.layout.item_rounde_84_4, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.image);
		Glide.with(ctx).
				load(list.get(position))
				.animate(R.anim.fade_in)
				.into(imageView);

		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(ctx, ReviewPictureActivity.class);
				intent.putExtra(ReviewPictureActivity.EXTRA_IMAGE_INDEX,position);
				intent.putExtra(ReviewPictureActivity.EXTRA_IMAGE_URLS,(ArrayList<String>)list);
				activity.startActivityForResult(intent,InputActivity.REVIEW_PHOTO);
			}
		});

		return view;
	}

	public void refresh(){
		list=activity.getSelect_image_urls();
		notifyDataSetChanged();
	}

}

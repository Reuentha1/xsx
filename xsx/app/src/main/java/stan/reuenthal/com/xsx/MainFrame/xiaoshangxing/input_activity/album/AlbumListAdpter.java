package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.album;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.ImageBucket;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.image.MyGlide;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by FengChaoQun
 * on 2016/8/8
 */
public class AlbumListAdpter extends ArrayAdapter<ImageBucket> {
    private Context context;
    private ArrayList<ImageBucket> list;

    public AlbumListAdpter(Context context, int resource, ArrayList<ImageBucket> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_album_list, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        ImageBucket imageBucket=list.get(position);
        holder.name.setText(imageBucket.bucketName+"("+imageBucket.count+")");
        MyGlide.with(context,imageBucket.imageList.get(0).getImagePath(),holder.image);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.name)
        TextView name;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

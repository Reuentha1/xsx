package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.privacy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import stan.reuenthal.com.xsx.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15828 on 2016/7/20.
 */
public class PrivacyGridAdapter extends BaseAdapter {
    private List<ImageView> redDeleteViews = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    List<Bitmap> data;

    public PrivacyGridAdapter(Context context, List<Bitmap> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        if (data.size() == 0) {
            Log.d("qqq", "null...");
            return 1;
        } else {
            Log.d("qqq", "notnull..." + data.size());
            return data.size() + 2;
        }
    }

    @Override
    public Bitmap getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public List<ImageView> getRedDeleteViews() {
        return redDeleteViews;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("qqq", "position  " + position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_privacy_adapter, parent, false);
            holder = new ViewHolder();
            holder.add = (ImageView) convertView.findViewById(R.id.privacy_add);
            holder.redDelete = (ImageView) convertView.findViewById(R.id.privacy_reddelete);
            redDeleteViews.add(holder.redDelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (data.size() != 0) {
            if (position == getCount() - 2) {
                holder.add.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.privacy_add));
            } else if (position == getCount() - 1) {
                holder.add.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.privacy_remove));
            } else {
                holder.add.setImageBitmap(data.get(position));
            }
        } else {
            holder.add.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.privacy_add));
        }
        return convertView;
    }

    class ViewHolder {
        public ImageView add;
        public ImageView redDelete;
    }


}

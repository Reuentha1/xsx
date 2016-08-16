package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.NewsActivity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionText.EmotinText;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.CirecleImage;

import java.util.List;
import java.util.Random;

/**
 * Created by FengChaoQun
 * on 2016/7/11
 */
public class newsAdapter extends ArrayAdapter<String> {
    private Context context;

    public newsAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        final viewHolder holder;
        if (convertView == null) {
            holder = new viewHolder();
            view =  View.inflate(context, R.layout.item_news_listview, null);
            holder.head = (CirecleImage) view.findViewById(R.id.head_image);
            holder.name=(TextView)view.findViewById(R.id.name);
            holder.praise=(ImageView)view.findViewById(R.id.praise);
            holder.comment_text = (EmotinText) view.findViewById(R.id.comment_text);
            holder.time=(TextView)view.findViewById(R.id.time);
            holder.image=(ImageView)view.findViewById(R.id.image);
            holder.text = (EmotinText) view.findViewById(R.id.text);
            view.setTag(holder);
        }else {
            view=convertView;
            holder=(viewHolder) view.getTag();
            cleanView(holder);
        }

//        test
        Random random = new Random();
        int num=random.nextInt(10);
        if (num>=5){
                holder.praise.setVisibility(View.VISIBLE);
        }else {
            holder.comment_text.setVisibility(View.VISIBLE);
        }

        int num2=random.nextInt(10);
        if (num2>=5){
            holder.image.setVisibility(View.VISIBLE);
        }else {
            holder.text.setVisibility(View.VISIBLE);
        }
        return view;
    }

    private void cleanView(viewHolder holder){
        holder.praise.setVisibility(View.GONE);
        holder.comment_text.setVisibility(View.GONE);
        holder.image.setVisibility(View.GONE);
        holder.text.setVisibility(View.GONE);
    }

    private static class viewHolder {
        private CirecleImage head;
        private TextView name;
        private ImageView praise;
        private EmotinText comment_text;
        private TextView time;
        private ImageView image;
        private EmotinText text;
    }
}

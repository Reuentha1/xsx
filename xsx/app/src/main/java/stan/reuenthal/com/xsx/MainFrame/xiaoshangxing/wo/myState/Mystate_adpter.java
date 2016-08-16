package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.WoFrafment.WoFragment;

import java.util.List;
import java.util.Random;

/**
 * Created by FengChaoQun
 * on 2016/4/20
 */
public class Mystate_adpter extends ArrayAdapter<String> {
    private Context context;
    private int resource;
    List<String> strings;
    WoFragment woFragment;

    private Handler mHandler;
    private myStateActivity activity;

    public Mystate_adpter(Context context, int resource, List<String> objects,myStateActivity activity) {
        super(context, resource, objects);
        this.context = context;
        this.strings = objects;
        this.resource = resource;
        this.activity=activity;
        mHandler = new Handler();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        final mystate_viewholder viewholder;

        if (convertView == null) {
            view = View.inflate(context, R.layout.item_mystate_listview, null);
            viewholder = new mystate_viewholder();
            viewholder.saySomething = (RelativeLayout) view.findViewById(R.id.publish);
            viewholder.only_text = (TextView) view.findViewById(R.id.only_text);
            viewholder.image_and_text = (RelativeLayout) view.findViewById(R.id.image_and_text);
            viewholder.form_other = (LinearLayout) view.findViewById(R.id.from_other);

            viewholder.day=(TextView)view.findViewById(R.id.day);
            viewholder.month=(TextView)view.findViewById(R.id.month);
            viewholder.location=(TextView)view.findViewById(R.id.location);

            viewholder.first_group=(LinearLayout)view.findViewById(R.id.first_group);
            viewholder.second_group=(LinearLayout)view.findViewById(R.id.sencond_group);
            viewholder.first_image=(ImageView)view.findViewById(R.id.first_image);
            viewholder.second_image=(ImageView)view.findViewById(R.id.sencond_image);
            viewholder.third_image=(ImageView)view.findViewById(R.id.third_image);
            viewholder.forth_image=(ImageView)view.findViewById(R.id.forth_image);
            viewholder.image_text=(TextView)view.findViewById(R.id.image_text);
            viewholder.image_count=(TextView)view.findViewById(R.id.image_count);

            viewholder.share_text=(TextView)view.findViewById(R.id.share_text);
            viewholder.good_iamge=(ImageView) view.findViewById(R.id.good_image);
            viewholder.person_iamge =(ImageView) view.findViewById(R.id.person_image);
            viewholder.good_describe=(TextView)view.findViewById(R.id.good_describe);

            view.setTag(viewholder);

        } else {
            view = convertView;
            viewholder = (mystate_viewholder) view.getTag();
            cleanHolder(viewholder);
        }

        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0:
                viewholder.only_text.setVisibility(View.VISIBLE);
                break;
            case 1:
                viewholder.image_and_text.setVisibility(View.VISIBLE);
                viewholder.image_text.setText(context.getText(R.string.longtext));
                switch (random.nextInt(4)){
                    case 3:
                        viewholder.forth_image.setVisibility(View.VISIBLE);
                    case 2:
                        viewholder.third_image.setVisibility(View.VISIBLE);
                    case 1:
                        viewholder.second_group.setVisibility(View.VISIBLE);
                        viewholder.second_image.setVisibility(View.VISIBLE);
                    case 0:
                        viewholder.first_group.setVisibility(View.VISIBLE);
                        viewholder.first_image.setVisibility(View.VISIBLE);
                        break;
                }
//                viewholder.first_group.setVisibility(View.VISIBLE);
//                viewholder.first_image.setVisibility(View.VISIBLE);
//                viewholder.forth_image.setVisibility(View.VISIBLE);
//                viewholder.second_group.setVisibility(View.VISIBLE);
//                viewholder.second_image.setVisibility(View.VISIBLE);
                break;
            case 2:
                viewholder.form_other.setVisibility(View.VISIBLE);
                viewholder.good_iamge.setVisibility(View.VISIBLE);
                break;
        }
        if (position==0){
            if (activity.getCurrent_type()==myStateActivity.SELF){
                viewholder.saySomething.setVisibility(View.VISIBLE);
                viewholder.saySomething.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent publish_intent=new Intent(context, InputActivity.class);
                        publish_intent.putExtra(InputActivity.EDIT_STATE,InputActivity.PUBLISH_STATE);
                        context.startActivity(publish_intent);
                    }
                });
            }
        }
        viewholder.month.setText("七月");
        viewholder.location.setText("江南大学");

        return view;
    }


    private static class mystate_viewholder {
        //各大布局
        private RelativeLayout saySomething;
        private TextView only_text;
        private RelativeLayout image_and_text;
        private LinearLayout form_other;

        //日期地址
        private TextView day;
        private TextView month;
        private TextView location;

        //图文
        private LinearLayout first_group;
        private LinearLayout second_group;
        private ImageView first_image;
        private ImageView second_image;
        private ImageView third_image;
        private ImageView forth_image;
        private TextView image_text;
        private TextView image_count;

        //转发
        private TextView share_text;
        private ImageView good_iamge;
        private ImageView person_iamge;
        private TextView good_describe;
    }

    private void cleanHolder(mystate_viewholder holder){
        holder.saySomething.setVisibility(View.GONE);
        holder.form_other.setVisibility(View.GONE);
        holder.image_and_text.setVisibility(View.GONE);
        holder.only_text.setVisibility(View.GONE);


        holder.second_group.setVisibility(View.GONE);
        holder.first_group.setVisibility(View.GONE);
        holder.first_image.setVisibility(View.GONE);
        holder.second_image.setVisibility(View.GONE);
        holder.third_image.setVisibility(View.GONE);
        holder.forth_image.setVisibility(View.GONE);

        holder.good_iamge.setVisibility(View.GONE);
        holder.person_iamge.setVisibility(View.GONE);
    }
}

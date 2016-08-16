package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.collect;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionText.EmotinText;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.CirecleImage;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.Name;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.RewardDetail.RewardDetailActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.ShoolRewardActivity;

import java.util.List;

/**
 * Created by FengChaoQun
 * on 2016/4/20
 */
public class collect_adpter extends ArrayAdapter<String> {
    private Context context;
    private int resource;
    List<String> strings;
    private collectFragment fragment;
    private boolean showselect;
    private ShoolRewardActivity activity;


    public collect_adpter(Context context, int resource, List<String> objects,
                          collectFragment fragment, ShoolRewardActivity activity  ) {
        super(context, resource, objects);
        this.context = context;
        this.strings = objects;
        this.resource = resource;
        this.fragment=fragment;
        this.activity=activity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final mystate_viewholder viewholder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shoolreward_collect_listview, null);
            viewholder = new mystate_viewholder();
            viewholder.headImage = (CirecleImage) convertView.findViewById(R.id.head_image);
            viewholder.name = (Name) convertView.findViewById(R.id.name);
            viewholder.college = (TextView) convertView.findViewById(R.id.college);
            viewholder.time = (TextView) convertView.findViewById(R.id.time);
            viewholder.text = (EmotinText) convertView.findViewById(R.id.text);
            viewholder.price=(TextView)convertView.findViewById(R.id.price);
            viewholder.button = (ImageView) convertView.findViewById(R.id.button);
            viewholder.down_arrow = (ImageView) convertView.findViewById(R.id.down_arrow);
            viewholder.checkBox=(CheckBox)convertView.findViewById(R.id.checkbox);
            viewholder.cardview=(CardView) convertView.findViewById(R.id.cardview);
            convertView.setTag(viewholder);

        } else {
            viewholder = (mystate_viewholder) convertView.getTag();
        }

        if (showselect){
            viewholder.checkBox.setVisibility(View.VISIBLE);
        }else {
            viewholder.checkBox.setVisibility(View.GONE);
        }

        viewholder.down_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.showCollectDialog();
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               showMenu(v,viewholder);
                viewholder.cardview.setBackground(context.getResources()
                        .getDrawable(R.drawable.circular_8_g1_nostroke));
                return true;

            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RewardDetailActivity.class);
                context.startActivity(intent);
            }
        });

        viewholder.checkBox.setChecked(false);

        return convertView;
    }


    private static class mystate_viewholder {
        private CirecleImage headImage;
        private TextView  college, time, price;
        private Name name;
        private EmotinText text;
        private CheckBox checkBox;
        private ImageView button, down_arrow;
        private CardView cardview;
    }

    private void showMenu(View v, final mystate_viewholder viewholder){
        int []xy=new int[2];
        v.getLocationInWindow(xy);
        View menu;
        if (xy[1]<=context.getResources().getDimensionPixelSize(R.dimen.y300)){
             menu= View.inflate(getContext(),R.layout.popup_myhelp_up,null);
        }else {
             menu= View.inflate(getContext(),R.layout.popup_myhelp_bottom,null);
        }

        final PopupWindow popupWindow=new PopupWindow(menu, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(getContext().getResources().
                getDrawable(R.drawable.nothing));
        popupWindow.setAnimationStyle(R.style.popwindow_anim);

        menu.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int mShowMorePopupWindowWidth = menu.getMeasuredWidth();
        int mShowMorePopupWindowHeight=menu.getMeasuredHeight();
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);

        if (xy[1]<=context.getResources().getDimensionPixelSize(R.dimen.y300)){
            popupWindow.showAsDropDown(v,
                    -mShowMorePopupWindowWidth/2+v.getWidth()/2,
                    0);
        }else {
            popupWindow.showAsDropDown(v,
                    -mShowMorePopupWindowWidth/2+v.getWidth()/2,
                    -mShowMorePopupWindowHeight-v.getHeight());
        }
       
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                viewholder.cardview.setBackground(context.getResources()
                        .getDrawable(R.drawable.circular_8_w0_nostroke));
            }
        });

        final TextView transmit=(TextView)menu.findViewById(R.id.transmit);
        TextView delete=(TextView)menu.findViewById(R.id.delete);
        TextView more=(TextView)menu.findViewById(R.id.more);

        transmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.gotoSelectPerson();
                popupWindow.dismiss();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                fragment.showDeleteSureDialog();
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.showHideMenu(true);
                showSelectCircle(true);
                popupWindow.dismiss();
            }
        });

    }

    public void showSelectCircle(boolean is){
        showselect=is;
        notifyDataSetChanged();
    }
}

package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.ShoolfellowHelpFragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.SelectPerson.SelectPersonActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionText.EmotinText;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.CirecleImage;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.RewardDetail.RewardDetailActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.HelpDetail.HelpDetailActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.ShoolfellowHelpActivity;

import java.util.List;
import java.util.Random;

/**
 * Created by FengChaoQun
 * on 2016/4/20
 */
public class shoolfellow_adpter extends ArrayAdapter<String> {
    private Context context;
    private int resource;
    List<String> strings;
    private ShoolfellowHelpFragment fragment;
    private ShoolfellowHelpActivity activity;


    public shoolfellow_adpter(Context context, int resource, List<String> objects,
                              ShoolfellowHelpFragment fragment,ShoolfellowHelpActivity activity) {
        super(context, resource, objects);
        this.context = context;
        this.strings = objects;
        this.resource = resource;
        this.fragment = fragment;
        this.activity=activity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final mystate_viewholder viewholder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shoolfellowhelp_listview, null);
            viewholder = new mystate_viewholder();
            viewholder.headImage = (CirecleImage) convertView.findViewById(R.id.head_image);
            viewholder.name = (TextView) convertView.findViewById(R.id.name);
            viewholder.college = (TextView) convertView.findViewById(R.id.college);
            viewholder.time = (TextView) convertView.findViewById(R.id.time);
            viewholder.text = (EmotinText) convertView.findViewById(R.id.text);
            viewholder.button = (ImageView) convertView.findViewById(R.id.button);
            viewholder.finish=(ImageView)convertView.findViewById(R.id.finish);
            convertView.setTag(viewholder);

        } else {
            viewholder = (mystate_viewholder) convertView.getTag();
        }

        viewholder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fragment.isRefreshing()) {
                    return;
                }
                View menu = View.inflate(context, R.layout.shoolfellow_popupmenu, null);
                final PopupWindow popupWindow = new PopupWindow(menu, ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(context.getResources().
                        getDrawable(R.drawable.circular_12_b3));
                popupWindow.setAnimationStyle(R.style.popwindow_anim);

                menu.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                int mShowMorePopupWindowWidth = menu.getMeasuredWidth();
                int mShowMorePopupWindowHeight = menu.getMeasuredHeight();
                int heightMoreBtnView = v.getHeight();

                popupWindow.setOutsideTouchable(true);
                popupWindow.setTouchable(true);

                popupWindow.showAsDropDown(v, -mShowMorePopupWindowWidth - context.getResources().getDimensionPixelSize(R.dimen.x6),
                        -(mShowMorePopupWindowHeight + heightMoreBtnView) / 2);

                View transmit = menu.findViewById(R.id.transmit);
                View comment = menu.findViewById(R.id.comment);
                View praise = menu.findViewById(R.id.praise);
                final TextView praiseOrCancle = (TextView) menu.findViewById(R.id.praiseOrCancel);

                transmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context, SelectPersonActivity.class);
                        activity.startActivityForResult(intent,SelectPersonActivity.SELECT_PERSON_CODE);
                        popupWindow.dismiss();
                    }
                });

                comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, HelpDetailActivity.class);
                        context.startActivity(intent);
                        popupWindow.dismiss();
                    }
                });

                praise.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (praiseOrCancle.getText().equals("赞")) {
                            praiseOrCancle.setText("取消");
                            Toast.makeText(context, "赞", Toast.LENGTH_SHORT).show();
                        } else {
                            praiseOrCancle.setText("赞");
                            Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
                        }

                        popupWindow.dismiss();
                    }
                });
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RewardDetailActivity.class);
                context.startActivity(intent);
            }
        });
//        test
        Random random=new Random();
        int ran=random.nextInt(2);
        if (ran==0){
            viewholder.finish.setVisibility(View.VISIBLE);
        }else {
            viewholder.finish.setVisibility(View.GONE);
        }
        return convertView;
    }


    private static class mystate_viewholder {
        private CirecleImage headImage;
        private TextView name, college, time;
        private EmotinText text;
        private ImageView button,finish;
    }


}

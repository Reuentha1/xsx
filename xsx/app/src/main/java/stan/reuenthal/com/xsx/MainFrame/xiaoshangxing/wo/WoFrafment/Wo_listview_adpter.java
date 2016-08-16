package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.WoFrafment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputBoxLayout;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.CirecleImage;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.MoreTextView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.school_circle.PraisePeople;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.school_circle.item_comment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.WoFrafment.check_photo.ImagePagerActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.roll.rollActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by FengChaoQun
 * on 2016/4/20
 */
public class Wo_listview_adpter extends ArrayAdapter<String> {
    private Context context;
    private int resource;
    List<String> strings;
    WoFragment woFragment;
    private Activity activity;
    private Handler mHandler;

    public Wo_listview_adpter(Context context, int resource, List<String> objects, WoFragment woFragment, Activity activity) {
        super(context, resource, objects);
        this.context = context;
        this.strings = objects;
        this.resource=resource;
        this.woFragment = woFragment;
        this.activity = activity;
        mHandler = new Handler();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        final wo_viewholder viewholder;

        if (convertView == null) {
            view = View.inflate(context, R.layout.item_wo_listview, null);
            viewholder = new wo_viewholder();
            viewholder.head = (CirecleImage) view.findViewById(R.id.head_image);
            viewholder.name = (TextView) view.findViewById(R.id.name);
            viewholder.college = (TextView) view.findViewById(R.id.college);
            viewholder.text = (MoreTextView) view.findViewById(R.id.text);
            viewholder.photos1 = (NoScrollGridView) view.findViewById(R.id.photos1);
            viewholder.just_one = (ImageView) view.findViewById(R.id.just_one);
            viewholder.location = (TextView) view.findViewById(R.id.location);
            viewholder.time = (TextView) view.findViewById(R.id.time);
            viewholder.permission = (ImageView) view.findViewById(R.id.permission);
            viewholder.delete = (TextView) view.findViewById(R.id.delete);
            viewholder.praise = (CheckBox) view.findViewById(R.id.praise);
            viewholder.comment = (ImageView) view.findViewById(R.id.comment);
            viewholder.comment_layout = (LinearLayout) view.findViewById(R.id.comment_layout);
            viewholder.praise_people = (LinearLayout) view.findViewById(R.id.praise_people);
            viewholder.comments = (LinearLayout) view.findViewById(R.id.comments);
            viewholder.transmit_content = (LinearLayout) view.findViewById(R.id.transmit_content);
            viewholder.transmit_image = (CirecleImage) view.findViewById(R.id.transmit_image);
            viewholder.transmit_text = (TextView) view.findViewById(R.id.transmit_text);
            view.setTag(viewholder);
        } else {
            view = convertView;
            viewholder = (wo_viewholder) view.getTag();
        }

        viewholder.text.setText(context.getString(R.string.longtext) + context.getString(R.string.longtext));

//测试图片
        String[] urls2 = {"http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
                "http://img.my.csdn.net/uploads/201410/19/1413698867_8323.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg"
        };

        final ArrayList<String> imageUrls = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            imageUrls.add(urls2[i]);
        }

        viewholder.photos1.setAdapter(new NoScrollGridAdapter(context, imageUrls));
        viewholder.photos1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ImagePagerActivity.class);
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, imageUrls);
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
                context.startActivity(intent);
            }
        });

        if (viewholder.praise_people.getChildCount() != 0) {
                viewholder.praise_people.removeAllViews();
        }

        viewholder.just_one.setImageResource(R.mipmap.cirecleiamge_default);
        Glide.with(context)
                .load(R.mipmap.test)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (resource.getHeight() > context.getResources().getDimensionPixelSize(R.dimen.y600)) {
                            viewholder.just_one.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                    context.getResources().getDimensionPixelSize(R.dimen.y600)));
                        }
                        viewholder.just_one.setImageBitmap(resource);
                    }
                });
        viewholder.just_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImagePagerActivity.class);
                ArrayList<String> justone = new ArrayList<String>();
                justone.add("http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg");
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, justone);
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, 0);
                context.startActivity(intent);
            }
        });
//        测试图片及转发显示
        Random random = new Random();
        int ran = random.nextInt(3);
        switch (ran) {
            case 0:
                viewholder.photos1.setVisibility(View.VISIBLE);
                viewholder.just_one.setVisibility(View.GONE);
                viewholder.transmit_content.setVisibility(View.GONE);
                break;
            case 1:
                viewholder.photos1.setVisibility(View.GONE);
                viewholder.just_one.setVisibility(View.VISIBLE);
                viewholder.transmit_content.setVisibility(View.GONE);
                break;
            case 2:
                viewholder.photos1.setVisibility(View.GONE);
                viewholder.just_one.setVisibility(View.GONE);
                viewholder.transmit_content.setVisibility(View.VISIBLE);
                break;
        }
//        测试点赞的人
        PraisePeople praisePeople = new PraisePeople(context);
        praisePeople.addName("冯超群");
        praisePeople.addName("吴天阳");
        praisePeople.addName("王振华");
        praisePeople.addName("徐莹");
        praisePeople.addName("孙璐阳");
        praisePeople.addName("御天证道");
        praisePeople.addName("御天证道");
        praisePeople.addName("御天证道");
        viewholder.praise_people.addView(praisePeople.getTextView());

//        测试评论
        if (viewholder.comments.getChildCount() != 0) {
            viewholder.comments.removeAllViews();
        }

        item_comment comment = new item_comment(context, "御天证道", "哇哇哇哇哇哇哇哇哇哇哇哇"+"[ebt]");
        item_comment comment2 = new item_comment(context, "王振华", "孙璐阳", "和哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和");
        viewholder.comments.addView(comment.getTextView());
        viewholder.comments.addView(comment2.getTextView());

//       评论
        viewholder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                woFragment.showEdittext(context);
                final int[] xy = new int[2];
                v.getLocationOnScreen(xy);
                final int layoutHeight = viewholder.comment_layout.getHeight();
                final View mv = v;
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int editextLocation = woFragment.get_Editext_Height();
                        int destination = xy[1] + mv.getHeight() + layoutHeight - editextLocation;
                        woFragment.moveListview(destination);
                    }
                }, 300);
            }
        });
//        comment.getTextView().setOnClickListener(showEdittext);
//        comment2.getTextView().setOnClickListener(showEdittext);
        setCommentListner(comment.getTextView());
        setCommentListner(comment2.getTextView());

        //头像监听
        viewholder.head.setIntent_type(CirecleImage.PERSON_STATE);
//        删除
        viewholder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogUtils.Dialog_Center center = new DialogUtils.Dialog_Center(context);
                center.Message("确定删除吗?");
                center.Button("删除", "取消");
                center.MbuttonOnClick(new DialogUtils.Dialog_Center.buttonOnClick() {
                    @Override
                    public void onButton1() {
                        Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
                        center.close();
                    }

                    @Override
                    public void onButton2() {
                        Toast.makeText(context, "cancle", Toast.LENGTH_SHORT).show();
                        center.close();
                    }
                });
                Dialog dialog = center.create();
                dialog.show();
                LocationUtil.setWidth(activity, dialog,
                        context.getResources().getDimensionPixelSize(R.dimen.x780));
            }
        });

        viewholder.permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, rollActivity.class);
                intent.putExtra(rollActivity.TYPE, rollActivity.NOTICE);
                context.startActivity(intent);
            }
        });
        return view;
    }

    //    显示输入框的OnClickListener
//    private View.OnClickListener showEdittext = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            woFragment.showEdittext(context);
//            final int[] xy = new int[2];
//            v.getLocationOnScreen(xy);
//            final View mv = v;
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    int editextLocation = woFragment.get_Editext_Height();
//                    int destination = xy[1] + mv.getHeight() - editextLocation;
//                    woFragment.moveListview(destination);
//                }
//            }, 300);
//            woFragment.setEditCallback(new InputBoxLayout.CallBack() {
//                @Override
//                public void callback(String text) {
//
//                }
//            });
//        }
//    };

    private void setCommentListner(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEdittext(v);
                woFragment.setEditCallback(new InputBoxLayout.CallBack() {
                    @Override
                    public void callback(String text) {
                        Log.d("callback", "pp");
                    }
                });
            }
        });
    }

    private void showEdittext(View v) {
        woFragment.showEdittext(context);
        final int[] xy = new int[2];
        v.getLocationOnScreen(xy);
        final View mv = v;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int editextLocation = woFragment.get_Editext_Height();
                int destination = xy[1] + mv.getHeight() - editextLocation;
                woFragment.moveListview(destination);
            }
        }, 300);
        woFragment.setEditCallback(new InputBoxLayout.CallBack() {
            @Override
            public void callback(String text) {

            }
        });
    }

    private static class wo_viewholder{
        private CirecleImage head;
        private TextView name;
        private TextView college;
        private MoreTextView text;
        private NoScrollGridView photos1;
        private ImageView just_one;
        private TextView location;
        private TextView time;
        private ImageView permission;
        private TextView delete;
        private CheckBox praise;
        private ImageView comment;
        private LinearLayout comment_layout;
        private LinearLayout praise_people;
        private LinearLayout comments;
        private LinearLayout transmit_content;
        private CirecleImage transmit_image;
        private TextView transmit_text;
    }
}

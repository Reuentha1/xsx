package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FengChaoQun
 * on 2016/6/22
 */
public class DialogUtils {


    /**
     * 屏幕下方弹出菜单
     */
    public static class DialogMenu extends Dialog {
        private Button mCancel;
        private ListView mMenuItems;
        private ArrayAdapter<String> mAdapter;

        private View mRootView;
        private Animation mShowAnim;
        private Animation mDismissAnim;
        private boolean isDismissing;
        private MenuListener mMenuListener;



        public DialogMenu(Context context) {
            super(context, R.style.ActionSheetDialog);
            getWindow().setGravity(Gravity.BOTTOM);
            initView(context);
            //    initAnim(context);
            //取消按钮的事件
            mCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                }
            });
            // 菜单的事件
            mMenuItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mMenuListener != null) {
                        mMenuListener.onItemSelected(position, mAdapter.getItem(position));
                        dismiss();
                    }
                }
            });
            // 对话框取消的回调
            setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if (mMenuListener != null) {
                        mMenuListener.onCancel();
                    }
                }
            });
        }

        private void initView(Context context) {
            mRootView = View.inflate(context, R.layout.dialog_action_sheet, null);
            mCancel = (Button) mRootView.findViewById(R.id.menu_cancel);
            mMenuItems = (ListView) mRootView.findViewById(R.id.menu_items);
            mAdapter = new ArrayAdapter<String>(context, R.layout.item_textview_center) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView view = (TextView) super.getView(position, convertView, parent);
//                    LinearLayout view = (LinearLayout) getLayoutInflater().inflate(R.layout.textview_144,null);
                    setBackground(position, view);
                    return view;
                }

                private void setBackground(int position, View view) {
                    // int count = getCount();
                    view.setBackgroundResource(R.drawable.menu_item_middle);
                    /*if (count == 1) {
                        view.setBackgroundResource(R.drawable.menu_item_single);
                    } else if (position == 0) {
                        view.setBackgroundResource(R.drawable.menu_item_top);
                    } else if (position == count - 1) {
                        view.setBackgroundResource(R.drawable.menu_item_bottom);
                    } else {
                        view.setBackgroundResource(R.drawable.menu_item_middle);
                    }*/
                }
            };
            mMenuItems.setAdapter(mAdapter);
            initAnim(context);
            this.setContentView(mRootView);

        }

        private void initAnim(Context context) {
            mShowAnim = AnimationUtils.loadAnimation(context, R.anim.translate_up);
            mDismissAnim = AnimationUtils.loadAnimation(context, R.anim.translate_down);
            mDismissAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    dismissMe();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }


        public DialogMenu addMenuItem(String items) {
            mAdapter.add(items);
            return this;
        }


        public void toggle() {
            if (isShowing()) {
                dismiss();
            } else {
                show();
            }
        }

        @Override
        public void show() {
            super.show();

//            mRootView.startAnimation(mShowAnim);
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void dismiss() {
            if (isDismissing) {
                return;
            }
            isDismissing = true;
            mRootView.startAnimation(mDismissAnim);
        }

        private void dismissMe() {
            super.dismiss();
            isDismissing = false;
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                dismiss();
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }

        public interface MenuListener {
            void onItemSelected(int position, String item);

            void onCancel();
        }

        public MenuListener getMenuListener() {
            return mMenuListener;
        }

        public void setMenuListener(MenuListener menuListener) {
            mMenuListener = menuListener;
        }


    }

    public static class DialogMenu2 extends Dialog {
        private Button mCancel;
        private ListView mMenuItems;
        private ArrayAdapter<String> mAdapter;

        private View mRootView;
        private Animation mShowAnim;
        private Animation mDismissAnim;
        private boolean isDismissing;
        private MenuListener mMenuListener;

        private List<String> list;
        private Context context;

        public DialogMenu2(Context context) {
            super(context, R.style.ActionSheetDialog);
            this.context = context;
            getWindow().setGravity(Gravity.BOTTOM);
            list = new ArrayList<String>();
//            initView(context);
            //    initAnim(context);
            //取消按钮的事件

        }

        public void initView() {
            mRootView = View.inflate(context, R.layout.dialog_action_sheet, null);
            mCancel = (Button) mRootView.findViewById(R.id.menu_cancel);
            mMenuItems = (ListView) mRootView.findViewById(R.id.menu_items);
            mAdapter = new ArrayAdapter<String>(context, R.layout.item_textview_center, list) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
//                    TextView view = (TextView) super.getView(position, convertView, parent);
                    LinearLayout view = (LinearLayout) getLayoutInflater().inflate(R.layout.textview_144, null);
                    TextView textView = (TextView) view.findViewById(R.id.text);
                    textView.setText(list.get(position));
                    setBackground(position, view);
                    if (list.get(position).equals("删除")) {
                        textView.setTextColor(Color.RED);
                    }
                    if (list.get(position).equals("清空所有消息")) {
                        textView.setTextColor(Color.RED);
                    }
                    return view;
                }

                private void setBackground(int position, View view) {
                    // int count = getCount();
                    view.setBackgroundResource(R.drawable.menu_item_middle);
                    /*if (count == 1) {
                        view.setBackgroundResource(R.drawable.menu_item_single);
                    } else if (position == 0) {
                        view.setBackgroundResource(R.drawable.menu_item_top);
                    } else if (position == count - 1) {
                        view.setBackgroundResource(R.drawable.menu_item_bottom);
                    } else {
                        view.setBackgroundResource(R.drawable.menu_item_middle);
                    }*/
                }
            };
            mMenuItems.setAdapter(mAdapter);
            initAnim(context);
            this.setContentView(mRootView);

            mCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                }
            });
            // 菜单的事件
            mMenuItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mMenuListener != null) {
                        mMenuListener.onItemSelected(position, mAdapter.getItem(position));
                        dismiss();
                    }
                }
            });
            // 对话框取消的回调
            setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if (mMenuListener != null) {
                        mMenuListener.onCancel();
                    }
                }
            });

        }

        private void initAnim(Context context) {
            mShowAnim = AnimationUtils.loadAnimation(context, R.anim.translate_up);
            mDismissAnim = AnimationUtils.loadAnimation(context, R.anim.translate_down);
            mDismissAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    dismissMe();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }


        public DialogMenu2 addMenuItem(String items) {
            list.add(items);
            return this;
        }


        public void toggle() {
            if (isShowing()) {
                dismiss();
            } else {
                show();
            }
        }

        @Override
        public void show() {
            super.show();
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void dismiss() {
            if (isDismissing) {
                return;
            }
            isDismissing = true;
            mRootView.startAnimation(mDismissAnim);
        }

        private void dismissMe() {
            super.dismiss();
            isDismissing = false;
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                dismiss();
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }

        public interface MenuListener {
            void onItemSelected(int position, String item);

            void onCancel();
        }

        public MenuListener getMenuListener() {
            return mMenuListener;
        }

        public void setMenuListener(MenuListener menuListener) {
            mMenuListener = menuListener;
        }


    }

    /**
     * 屏幕中心弹出对话框（带按钮）
     */
    public static class Dialog_Center {
        private Context activity;
        private String title;
        private String message;
        private String button1 = "确定";
        private String button2;
        int button_count = 1;
        private Dialog dialog;

        private buttonOnClick mbuttonOnClick;


        public Dialog_Center(Context activity) {
            this.activity = activity;
        }

        public Context getActivity() {
            return activity;
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }

        public String getTitle() {
            return title;
        }

        public Dialog_Center Title(String title) {
            this.title = title;
            return this;
        }

        public String getMessage() {
            return this.message;
        }

        public Dialog_Center Message(String message) {
            this.message = message;
            return this;
        }

        public String getButton1() {
            return button1;
        }


        public String getButton2() {
            return button2;
        }

        public Dialog_Center Button(String button1) {
            this.button1 = button1;
            this.button_count = 1;
            return this;
        }

        public Dialog_Center Button(String button1, String button2) {
            this.button1 = button1;
            this.button2 = button2;
            this.button_count = 2;
            return this;
        }

        public int getButton_count() {
            return button_count;
        }


        public buttonOnClick getMbuttonOnClick() {
            return mbuttonOnClick;
        }

        public Dialog_Center MbuttonOnClick(buttonOnClick mbuttonOnClick) {
            this.mbuttonOnClick = mbuttonOnClick;
            return this;
        }

        public Dialog create() {
            dialog = new Dialog(activity, R.style.ActionSheetDialog);
            LinearLayout linearLayout = (LinearLayout) View
                    .inflate(activity, R.layout.dialog_util, null);

            TextView title = (TextView) linearLayout.findViewById(R.id.dialog_title);
            TextView message = (TextView) linearLayout.findViewById(R.id.dialog_message);
            Button btn = (Button) linearLayout.findViewById(R.id.dialog_button);
            LinearLayout twoButton = (LinearLayout) linearLayout.findViewById(R.id.dialog_twoButton);
            Button btn1 = (Button) linearLayout.findViewById(R.id.dialog_button1);
            Button btn2 = (Button) linearLayout.findViewById(R.id.dialog_button2);

            if (!TextUtils.isEmpty(getTitle())) {
                title.setText(getTitle());
                title.setVisibility(View.VISIBLE);
                message.setTextSize(13);
            }

            if (!TextUtils.isEmpty(getMessage())) {
                message.setText(getMessage());
            }

            if (button_count == 1) {
                twoButton.setVisibility(View.GONE);
                btn.setText(getButton1());
                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mbuttonOnClick.onButton1();
                    }
                });
            } else {
                btn1.setText(getButton1());
                btn2.setText(getButton2());
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mbuttonOnClick.onButton1();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mbuttonOnClick.onButton2();

                    }
                });
            }

            dialog.setContentView(linearLayout);
            //        alertDialog.setCancelable(false);
            return dialog;
        }


        public interface buttonOnClick {
            void onButton1();

            void onButton2();
        }

        public void close() {
            dialog.dismiss();
        }
    }

    /**
     * 屏幕中心弹出对话框（不带按钮）
     */
    public static class Dialog_No_Button {
        private Activity activity;
        private Dialog dialog;
        private String message;

        public Dialog_No_Button(Activity activity, String message) {
            this.activity = activity;
            this.message = message;
        }

        public Dialog create() {
            LinearLayout linearLayout = (LinearLayout) activity
                    .getLayoutInflater().inflate(R.layout.dialog_nobutton, null);
            dialog = new Dialog(activity, R.style.ActionSheetDialog);
            TextView tv_message = (TextView) linearLayout.findViewById(R.id.dialog_message);
            tv_message.setText(message);
            dialog.setContentView(linearLayout);
            return dialog;
        }

        public Dialog_No_Button setMessage(String message) {
            this.message = message;
            return this;
        }

    }

    /**
     * 屏幕中心弹出对话框（带按钮有编辑框）
     */
    public static class Dialog_WithEditText {
        private Activity activity;
        private String title;
        private String message;
        private String button1 = "确定";
        private String button2;
        int button_count = 1;
        private Dialog dialog;
        private EditText editText;
        private ImageView clear;

        private buttonOnClick mbuttonOnClick;


        public Dialog_WithEditText(Activity activity) {
            this.activity = activity;
        }

        public Activity getActivity() {
            return activity;
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }

        public String getTitle() {
            return title;
        }

        public Dialog_WithEditText Title(String title) {
            this.title = title;
            return this;
        }

        public String getMessage() {
            return this.message;
        }

        public Dialog_WithEditText Message(String message) {
            this.message = message;
            return this;
        }

        public String getButton1() {
            return button1;
        }


        public String getButton2() {
            return button2;
        }

        public Dialog_WithEditText Button(String button1) {
            this.button1 = button1;
            this.button_count = 1;
            return this;
        }

        public Dialog_WithEditText Button(String button1, String button2) {
            this.button1 = button1;
            this.button2 = button2;
            this.button_count = 2;
            return this;
        }
        public String getText(){
            return editText.getText().toString();
        }

        public int getButton_count() {
            return button_count;
        }


        public buttonOnClick getMbuttonOnClick() {
            return mbuttonOnClick;
        }

        public Dialog_WithEditText MbuttonOnClick(buttonOnClick mbuttonOnClick) {
            this.mbuttonOnClick = mbuttonOnClick;
            return this;
        }

        public Dialog create() {
            dialog = new Dialog(activity, R.style.ActionSheetDialog);
            LinearLayout linearLayout = (LinearLayout) activity.
                    getLayoutInflater().inflate(R.layout.dialog_withedittext, null);
            TextView title = (TextView) linearLayout.findViewById(R.id.dialog_title);
            TextView message = (TextView) linearLayout.findViewById(R.id.dialog_message);
            Button btn = (Button) linearLayout.findViewById(R.id.dialog_button);
            LinearLayout twoButton = (LinearLayout) linearLayout.findViewById(R.id.dialog_twoButton);
            Button btn1 = (Button) linearLayout.findViewById(R.id.dialog_button1);
            Button btn2 = (Button) linearLayout.findViewById(R.id.dialog_button2);
            editText = (EditText) linearLayout.findViewById(R.id.dialog_edittext);
            clear = (ImageView) linearLayout.findViewById(R.id.dialog_clear);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().length() > 0) clear.setVisibility(View.VISIBLE);
                    else clear.setVisibility(View.GONE);
                }
            });
            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editText.setText("");
                }
            });

            if (!TextUtils.isEmpty(getTitle())) {
                title.setText(getTitle());
                title.setVisibility(View.VISIBLE);
                message.setTextSize(13);
            }

            if (!TextUtils.isEmpty(getMessage())) {
                message.setText(getMessage());
            }

            if (button_count == 1) {
                twoButton.setVisibility(View.GONE);
                btn.setText(getButton1());
                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mbuttonOnClick.onButton1();
                    }
                });
            } else {
                btn1.setText(getButton1());
                btn2.setText(getButton2());
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mbuttonOnClick.onButton1();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mbuttonOnClick.onButton2();
                    }
                });
            }

            dialog.setContentView(linearLayout);
            //        alertDialog.setCancelable(false);
            return dialog;
        }


        public interface buttonOnClick {
            void onButton1();

            void onButton2();
        }

        public void close() {
            dialog.dismiss();
        }
    }

    /**
     * 屏幕中心弹出对话框（带按钮） Message black_16sp
     */
    public static class Dialog_Center2 {
        private Context activity;
        private String title;
        private String message;
        private String button1 = "确定";
        private String button2;
        int button_count = 1;
        private Dialog dialog;

        private buttonOnClick mbuttonOnClick;


        public Dialog_Center2(Context activity) {
            this.activity = activity;
        }

        public Context getActivity() {
            return activity;
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }

        public String getTitle() {
            return title;
        }

        public Dialog_Center2 Title(String title) {
            this.title = title;
            return this;
        }

        public String getMessage() {
            return this.message;
        }

        public Dialog_Center2 Message(String message) {
            this.message = message;
            return this;
        }

        public String getButton1() {
            return button1;
        }


        public String getButton2() {
            return button2;
        }

        public Dialog_Center2 Button(String button1) {
            this.button1 = button1;
            this.button_count = 1;
            return this;
        }

        public Dialog_Center2 Button(String button1, String button2) {
            this.button1 = button1;
            this.button2 = button2;
            this.button_count = 2;
            return this;
        }

        public int getButton_count() {
            return button_count;
        }


        public buttonOnClick getMbuttonOnClick() {
            return mbuttonOnClick;
        }

        public Dialog_Center2 MbuttonOnClick(buttonOnClick mbuttonOnClick) {
            this.mbuttonOnClick = mbuttonOnClick;
            return this;
        }

        public Dialog create() {
            dialog = new Dialog(activity, R.style.ActionSheetDialog);
            LinearLayout linearLayout = (LinearLayout) View
                    .inflate(activity, R.layout.dialog_util2, null);

            TextView title = (TextView) linearLayout.findViewById(R.id.dialog_title);
            TextView message = (TextView) linearLayout.findViewById(R.id.dialog_message);
            Button btn = (Button) linearLayout.findViewById(R.id.dialog_button);
            LinearLayout twoButton = (LinearLayout) linearLayout.findViewById(R.id.dialog_twoButton);
            Button btn1 = (Button) linearLayout.findViewById(R.id.dialog_button1);
            Button btn2 = (Button) linearLayout.findViewById(R.id.dialog_button2);

            if (!TextUtils.isEmpty(getTitle())) {
                title.setText(getTitle());
                title.setVisibility(View.VISIBLE);
                message.setTextSize(13);
            }

            if (!TextUtils.isEmpty(getMessage())) {
                message.setText(getMessage());
            }

            if (button_count == 1) {
                twoButton.setVisibility(View.GONE);
                btn.setText(getButton1());
                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mbuttonOnClick.onButton1();
                    }
                });
            } else {
                btn1.setText(getButton1());
                btn2.setText(getButton2());
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mbuttonOnClick.onButton1();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mbuttonOnClick.onButton2();

                    }
                });
            }

            dialog.setContentView(linearLayout);
            //        alertDialog.setCancelable(false);
            return dialog;
        }


        public interface buttonOnClick {
            void onButton1();

            void onButton2();
        }

        public void close() {
            dialog.dismiss();
        }
    }






}



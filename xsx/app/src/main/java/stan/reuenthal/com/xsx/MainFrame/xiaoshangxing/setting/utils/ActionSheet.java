package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import stan.reuenthal.com.xsx.R;

/**
 * Created by tianyang on 2016/7/10.
 */
public class ActionSheet extends Dialog {
    private Button mCancel;
    private ListView mMenuItemsTop,mMenuItemsBottom,mMenuItems;
    private ArrayAdapter<String> mAdapterTop, mAdapterBottom,mAdapter;
    private View mRootView;
    private Animation mShowAnim;
    private Animation mDismissAnim;
    private boolean isDismissing;
    private MenuListener mMenuTopListener,mMenuBottomListener,mMenuListener;


    public ActionSheet(Context context) {
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
        mMenuItemsTop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mMenuTopListener != null) {
                    mMenuTopListener.onItemSelected(position, mAdapterTop.getItem(position));
                    dismiss();
                }
            }
        });
        mMenuItemsBottom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mMenuBottomListener != null) {
                    mMenuBottomListener.onItemSelected(position, mAdapterBottom.getItem(position));
                    dismiss();
                }
            }
        });
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
                if(mMenuTopListener != null) {
                    mMenuTopListener.onCancel();
                }
                if(mMenuBottomListener != null) {
                    mMenuBottomListener.onCancel();
                }
                if(mMenuListener != null) {
                    mMenuListener.onCancel();
                }
            }
        });
    }

    private void initView(Context context) {
        mRootView = View.inflate(context, R.layout.dialog_setting_exit, null);
        mCancel = (Button) mRootView.findViewById(R.id.dialog_cancel);
        mMenuItemsTop = (ListView) mRootView.findViewById(R.id.dialog_listtop);
        mAdapterTop = new ArrayAdapter<String>(context, R.layout.dialog_listtop_item) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                setBackground(position, view);
                return view;
            }

            private void setBackground(int position, View view) {
                view.setBackgroundResource(R.drawable.menu_item_middle);
            }
        };
        mMenuItemsTop.setAdapter(mAdapterTop);

        mMenuItemsBottom = (ListView)mRootView.findViewById(R.id.dialog_listbotton);
        mAdapterBottom = new ArrayAdapter<String>(context, R.layout.dialog_listbottom_item) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                setBackground(position, view);
                return view;
            }

            private void setBackground(int position, View view) {
                view.setBackgroundResource(R.drawable.menu_item_middle);
            }
        };
        mMenuItemsBottom.setAdapter(mAdapterBottom);

        mMenuItems = (ListView)mRootView.findViewById(R.id.dialog_list);
        mAdapter = new ArrayAdapter<String>(context, R.layout.dialog_list_item) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                setBackground(position, view);
                return view;
            }
            private void setBackground(int position, View view) {
                view.setBackgroundResource(R.drawable.menu_item_middle);
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

    public ActionSheet addMenuItem(String items) {
        mAdapter.add(items);
        return this;
    }

    public ActionSheet addMenuTopItem(String items) {
        mAdapterTop.add(items);
        return this;
    }

    public ActionSheet addMenuBottomItem(String items) {
        mAdapterBottom.add(items);
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

        mRootView.startAnimation(mShowAnim);
        mAdapterTop.notifyDataSetChanged();
        mAdapterBottom.notifyDataSetInvalidated();
    }

    @Override
    public void dismiss() {
        if(isDismissing) {
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

/*    public MenuListener getMenuListener() {
        return mMenuListener;
    }*/

    public void setMenuTopListener(MenuListener menuListener) {
        mMenuTopListener = menuListener;
    }

    public void setMenuBottomListener(MenuListener menuListener) {
        mMenuBottomListener = menuListener;
    }
    public void setMenuListener(MenuListener menuListener) {
        mMenuListener = menuListener;
    }


}

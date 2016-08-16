package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotAndPicture.EmotionGrideViewAdapter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionEdittext.EmoticonsEditText;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.normalUtils.KeyBoardUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.normalUtils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FengChaoQun
 * on 2016/7/27
 */
public class InputBoxLayout implements View.OnClickListener {
    private Context context;
    private GridView gridView;
    private List<View> viewlist = new ArrayList<View>();
    private ViewPager viewPager;
    private LinearLayout emotion_lay;
    private EmotionGrideViewAdapter adapter;

    private RelativeLayout comment_input_layout;
    private EmoticonsEditText emoticonsEditText;
    private TextView send;
    private ImageView emot;
    private View normal_emot, favorite, delete_emot;
    private RelativeLayout edit_and_emot;
    private int screenHeight;

    private Activity activity;
    private CallBack callBack;
    private InputMethodManager imm;

    public InputBoxLayout(Context context, RelativeLayout edit_and_emot, Activity activity) {
        this.context = context;
        this.edit_and_emot = edit_and_emot;
        this.activity=activity;
        initView();
    }

    private void initView() {

        viewPager = (ViewPager) edit_and_emot.findViewById(R.id.scrollview);
        emotion_lay = (LinearLayout) edit_and_emot.findViewById(R.id.emot_lay);

        comment_input_layout = (RelativeLayout) edit_and_emot.findViewById(R.id.comment_input_layout);
        emoticonsEditText = (EmoticonsEditText) edit_and_emot.findViewById(R.id.comment_input);
        emoticonsEditText.setOnClickListener(this);
        send = (TextView) edit_and_emot.findViewById(R.id.send);
        send.setOnClickListener(this);
        emot = (ImageView) edit_and_emot.findViewById(R.id.emotion);
        emot.setOnClickListener(this);
        edit_and_emot = (RelativeLayout) edit_and_emot.findViewById(R.id.edit_and_emot);
        normal_emot = edit_and_emot.findViewById(R.id.normal_emot);
        normal_emot.setOnClickListener(this);
        favorite = edit_and_emot.findViewById(R.id.favorite);
        favorite.setOnClickListener(this);
        delete_emot = edit_and_emot.findViewById(R.id.delete_emot);
        delete_emot.setOnClickListener(this);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        initEmot();
    }

    private void initEmot() {

        //监听键盘高度  让输入框保持在键盘上面
        //把输入框在的布局往上移动直到输入框出现在键盘上面
        screenHeight = ScreenUtils.getScreenHeight(context);
        KeyBoardUtils.observeSoftKeyboard(activity, new KeyBoardUtils.OnSoftKeyboardChangeListener() {
            @Override
            public void onSoftKeyBoardChange(int softKeybardHeight, boolean visible) {

                if (softKeybardHeight > 100) {
                    comment_input_layout.layout(0, screenHeight - softKeybardHeight -comment_input_layout.getHeight(),
                            comment_input_layout.getWidth(),
                            screenHeight - softKeybardHeight);
                }
            }
        });

        //监听输入框内容
        emoticonsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (emoticonsEditText.getText().length() > 0) {
                    send.setBackground(context.getResources().getDrawable(R.drawable.btn_circular_green1));
                    send.setEnabled(true);
                } else {
                    send.setBackground(context.getResources().getDrawable(R.drawable.btn_circular_g1));
                    send.setEnabled(false);
                }
            }
        });

        gridView = (GridView) View.inflate(context, R.layout.gridelayout, null);
        adapter = new EmotionGrideViewAdapter(context);
        adapter.setMcallBack(new EmotionGrideViewAdapter.callBack() {
            @Override
            public void callback(String emot) {
                emoticonsEditText.append(emot);
            }
        });
        gridView.setAdapter(adapter);
        TextView textView = new TextView(context);
        textView.setText("55555");
        viewlist.add(gridView);
        viewlist.add(textView);

        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return 2;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewlist.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewlist.get(position));
                return viewlist.get(position);
            }
        };

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
        selectItem(0);
    }

    private void selectItem(int position) {
        switch (position) {
            case 0:
                normal_emot.setBackgroundColor(context.getResources().getColor(R.color.w2));
                favorite.setBackgroundColor(context.getResources().getColor(R.color.w1));
                break;
            case 1:
                normal_emot.setBackgroundColor(context.getResources().getColor(R.color.w1));
                favorite.setBackgroundColor(context.getResources().getColor(R.color.w2));
                break;
        }
    }

    public void showOrHideLayout(boolean is) {
        if (is) {
            emotion_lay.setVisibility(View.VISIBLE);
            edit_and_emot.setVisibility(View.VISIBLE);
            comment_input_layout.setVisibility(View.VISIBLE);
            //输入框自获取焦点 并弹出输入键盘
            emoticonsEditText.setFocusable(true);
            emoticonsEditText.setFocusableInTouchMode(true);
            emoticonsEditText.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            emotion_lay.setVisibility(View.GONE);
            edit_and_emot.setVisibility(View.GONE);
            comment_input_layout.setVisibility(View.INVISIBLE);
            KeyBoardUtils.closeKeybord(emoticonsEditText, context);
        }
    }

    public void remainEdittext(){
        emotion_lay.setVisibility(View.GONE);
        KeyBoardUtils.closeKeybord(emoticonsEditText, context);
    }

    public int getEdittext_height() {
        int xy[] = new int[2];
        comment_input_layout.getLocationOnScreen(xy);
        return xy[1];
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.emotion:
                if (emotion_lay.getVisibility()!=View.VISIBLE){
                    emotion_lay.setVisibility(View.VISIBLE);
                }
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.normal_emot:
                viewPager.setCurrentItem(0);
                break;
            case R.id.favorite:
                viewPager.setCurrentItem(1);
                break;
            case R.id.send:
                if (callBack!=null){
                    callBack.callback(emoticonsEditText.getText().toString());
                }
                showOrHideLayout(false);
                emoticonsEditText.setText("");
                break;
            case R.id.delete_emot:
                emoticonsEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
            case R.id.comment_input:
//                imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                emotion_lay.setVisibility(View.VISIBLE);
                edit_and_emot.setVisibility(View.VISIBLE);
                comment_input_layout.setVisibility(View.VISIBLE);
                //输入框自获取焦点 并弹出输入键盘
                emoticonsEditText.setFocusable(true);
                emoticonsEditText.setFocusableInTouchMode(true);
                emoticonsEditText.requestFocus();
                break;
        }
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void setRootVisible( int type){
        edit_and_emot.setVisibility(type);
    }

    public void setInputLayVisible(int type){
        comment_input_layout.setVisibility(type);
    }

    public void setEmotion_layVisible(int type){
        emotion_lay.setVisibility(type);
    }

    public interface CallBack{
        void callback(String text);
    }
}

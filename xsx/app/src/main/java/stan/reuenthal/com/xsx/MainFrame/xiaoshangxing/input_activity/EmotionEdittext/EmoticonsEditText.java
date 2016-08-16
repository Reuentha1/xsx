package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionEdittext;

/**
 * Created by FengChaoQun
 * on 2016/7/25
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmoticonsEditText extends EditText {
    private List<EmotionFilter> mFilterList=new ArrayList();
    EmoticonsEditText.OnBackKeyClickListener onBackKeyClickListener;
    EmoticonsEditText.OnSizeChangedListener onSizeChangedListener;

    public EmoticonsEditText(Context context) {
        this(context, (AttributeSet)null);
    }

    public EmoticonsEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmoticonsEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mFilterList.add(new EmotionFilter());
    }


    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } catch (ArrayIndexOutOfBoundsException var4) {
            this.setText(this.getText().toString());
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(oldh > 0 && this.onSizeChangedListener != null) {
            this.onSizeChangedListener.onSizeChanged(w, h, oldw, oldh);
        }

    }

    public void setGravity(int gravity) {
        try {
            super.setGravity(gravity);
        } catch (ArrayIndexOutOfBoundsException var3) {
            this.setText(this.getText().toString());
            super.setGravity(gravity);
        }

    }

    public void setText(CharSequence text, BufferType type) {
        try {
            super.setText(text, type);
        } catch (ArrayIndexOutOfBoundsException var4) {
            this.setText(text.toString());
        }

    }

    protected final void onTextChanged(CharSequence arg0, int start, int lengthBefore, int after) {
        super.onTextChanged(arg0, start, lengthBefore, after);
        if(this.mFilterList != null) {
            Iterator i$ = this.mFilterList.iterator();

            while(i$.hasNext()) {
                EmotionFilter emoticonFilter = (EmotionFilter)i$.next();
                emoticonFilter.filter(this, arg0, start, lengthBefore, after);
            }

        }
    }

    public void addEmoticonFilter(EmotionFilter emoticonFilter) {
        if(this.mFilterList == null) {
            this.mFilterList = new ArrayList();
        }

        this.mFilterList.add(emoticonFilter);
    }

    public void removedEmoticonFilter(EmotionFilter emoticonFilter) {
        if(this.mFilterList != null && this.mFilterList.contains(emoticonFilter)) {
            this.mFilterList.remove(emoticonFilter);
        }

    }

    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if(this.onBackKeyClickListener != null) {
            this.onBackKeyClickListener.onBackKeyClick();
        }

        return super.dispatchKeyEventPreIme(event);
    }

    public void setOnBackKeyClickListener(EmoticonsEditText.OnBackKeyClickListener i) {
        this.onBackKeyClickListener = i;
    }

    public void setOnSizeChangedListener(EmoticonsEditText.OnSizeChangedListener i) {
        this.onSizeChangedListener = i;
    }

    public interface OnSizeChangedListener {
        void onSizeChanged(int var1, int var2, int var3, int var4);
    }

    public interface OnBackKeyClickListener {
        void onBackKeyClick();
    }
}

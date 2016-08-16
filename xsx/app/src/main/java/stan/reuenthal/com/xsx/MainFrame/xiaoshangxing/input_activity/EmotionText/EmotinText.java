package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionText;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.TextView;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionEdittext.EmotionFilter;

/**
 * Created by FengChaoQun
 * on 2016/8/5
 */
public class EmotinText extends TextView {
    public EmotinText(Context context) {
        super(context);
    }

    public EmotinText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmotinText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
//        EmotTextUtil.spannableEmoticonFilter(this,text.toString());
//    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        Spannable spannable = QqFilter.spannableFilter(this.getContext(),
                spannableStringBuilder,
                text,
                EmotionFilter.getFontHeight(this));
        super.setText(spannable, type);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        Spannable spannable = QqFilter.spannableFilter(this.getContext(),
                spannableStringBuilder,
                text,
                EmotionFilter.getFontHeight(this));
        super.onTextChanged(spannable, start, lengthBefore, lengthAfter);
    }

    @Override
    public void append(CharSequence text, int start, int end) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        Spannable spannable = QqFilter.spannableFilter(this.getContext(),
                spannableStringBuilder,
                text,
                EmotionFilter.getFontHeight(this));
        super.append(spannable, start, end);
    }
}

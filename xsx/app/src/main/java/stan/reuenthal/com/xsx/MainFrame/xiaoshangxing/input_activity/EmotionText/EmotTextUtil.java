package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionText;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionEdittext.EmotionFilter;

/**
 * Created by FengChaoQun
 * on 2016/8/5
 */
public class EmotTextUtil {
    public static void spannableEmoticonFilter(TextView tv_content, String content) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(content);
        Spannable spannable = QqFilter.spannableFilter(tv_content.getContext(),
                spannableStringBuilder,
                content,
                EmotionFilter.getFontHeight(tv_content));
        tv_content.setText(spannable);
    }
}

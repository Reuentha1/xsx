package stan.reuenthal.com.xsx.keyboard;

import android.app.Activity;
import android.widget.TextView;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class QqChattingListAdapter extends ChattingListAdapter {

    public QqChattingListAdapter(Activity activity) {
        super(activity);
    }

    public void setContent(TextView tv_content, String content) {
        QqUtils.spannableEmoticonFilter(tv_content, content);
    }
}
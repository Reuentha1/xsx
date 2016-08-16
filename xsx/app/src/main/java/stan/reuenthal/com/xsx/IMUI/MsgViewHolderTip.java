package stan.reuenthal.com.xsx.IMUI;

import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.keyboard.MoonUtil;
import stan.reuenthal.com.xsx.keyboard.MsgViewHolderBase;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
/**
 * Created by huangjun on 2015/11/25.
 * Tip类型消息ViewHolder
 */
public class MsgViewHolderTip extends MsgViewHolderBase {

    protected TextView notificationTextView;

    @Override
    protected int getContentResId() {
        return R.layout.nim_message_item_notification;
    }

    @Override
    protected void inflateContentView() {
        notificationTextView = (TextView) view.findViewById(R.id.message_item_notification_label);
    }

    @Override
    protected void bindContentView() {
        String text = "未知通知提醒";
        if(TextUtils.isEmpty(message.getContent())) {
            Map<String, Object> content = message.getRemoteExtension();
            if (content != null && !content.isEmpty()) {
                text = (String) content.get("content");
            }
        } else {
            text = message.getContent();
        }

        handleTextNotification(text);
        timeTextView.setVisibility(View.GONE);
    }

    private void handleTextNotification(String text) {
        MoonUtil.identifyFaceExpressionAndATags(context, notificationTextView, text, ImageSpan.ALIGN_BOTTOM);
        notificationTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected boolean isMiddleItem() {
        return true;
    }
}

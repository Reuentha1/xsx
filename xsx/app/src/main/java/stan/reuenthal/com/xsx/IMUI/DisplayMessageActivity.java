package stan.reuenthal.com.xsx.IMUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import stan.reuenthal.com.xsx.IMpanel.Container;
import stan.reuenthal.com.xsx.IMpanel.MessageListPanel;
import stan.reuenthal.com.xsx.IMpanel.ModuleProxy;
import stan.reuenthal.com.xsx.IMpanel.TActionBarActivity;
import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.keyboard.UserInfoHelper;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class DisplayMessageActivity extends TActionBarActivity implements ModuleProxy {

    private static String EXTRA_ANCHOR = "anchor";

    public static void start(Context context, IMMessage anchor) {
        Intent intent = new Intent();
        intent.setClass(context, DisplayMessageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //search extra
        intent.putExtra(EXTRA_ANCHOR, anchor);

        context.startActivity(intent);
    }

    // context
    private SessionTypeEnum sessionType;
    private String account; // 对方帐号
    private IMMessage anchor;

    private MessageListPanel messageListPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = LayoutInflater.from(this).inflate(R.layout.message_history_activity, null);
        setContentView(rootView);

        onParseIntent();

        Container container = new Container(this, account, sessionType, this);
        messageListPanel = new MessageListPanel(container, rootView, anchor, true, false);
        messageListPanel.scrollToItem(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        messageListPanel.onDestroy();
    }

    protected void onParseIntent() {
        anchor = (IMMessage) getIntent().getSerializableExtra(EXTRA_ANCHOR);
        account = anchor.getSessionId();
        sessionType = anchor.getSessionType();

        setTitle(UserInfoHelper.getUserTitleName(account, sessionType));
    }

    @Override
    public boolean sendMessage(IMMessage msg) {
        return false;
    }

    @Override
    public void onInputPanelExpand() {

    }

    @Override
    public void shouldCollapseInputPanel() {

    }

    @Override
    public boolean isLongClickEnabled() {
        return true;
    }

}

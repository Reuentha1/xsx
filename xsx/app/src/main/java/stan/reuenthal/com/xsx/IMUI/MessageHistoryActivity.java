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

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class MessageHistoryActivity extends TActionBarActivity implements ModuleProxy {

    private static final String EXTRA_DATA_ACCOUNT = "EXTRA_DATA_ACCOUNT";
    private static final String EXTRA_DATA_SESSION_TYPE = "EXTRA_DATA_SESSION_TYPE";

    // context
    private SessionTypeEnum sessionType;
    private String account; // 对方帐号

    private MessageListPanel messageListPanel;

    public static void start(Context context, String account, SessionTypeEnum sessionType) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATA_ACCOUNT, account);
        intent.putExtra(EXTRA_DATA_SESSION_TYPE, sessionType);
        intent.setClass(context, MessageHistoryActivity.class);
        context.startActivity(intent);
    }

    /**
     * ***************************** life cycle *******************************
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = LayoutInflater.from(this).inflate(R.layout.message_history_activity, null);
        setContentView(rootView);

        setTitle(R.string.message_history_query);

        onParseIntent();

        Container container = new Container(this, account, sessionType, this);
        messageListPanel = new MessageListPanel(container, rootView, true, true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        messageListPanel.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        messageListPanel.onDestroy();
    }

    protected void onParseIntent() {
        account = getIntent().getStringExtra(EXTRA_DATA_ACCOUNT);
        sessionType = (SessionTypeEnum) getIntent().getSerializableExtra(EXTRA_DATA_SESSION_TYPE);
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

package stan.reuenthal.com.xsx.IMUI;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */

import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.keyboard.BaseAction;

/**
 * Tip类型消息测试
 * Created by hzxuwen on 2016/3/9.
 */
public class TipAction extends BaseAction {

    public TipAction() {
        super(R.drawable.message_plus_tip_selector, R.string.input_panel_tip);
    }
    @Override
    public void onClick() {
        IMMessage msg = MessageBuilder.createTipMessage(getAccount(), getSessionType());
        msg.setContent("一条Tip测试消息");

        CustomMessageConfig config = new CustomMessageConfig();
        config.enablePush = false; // 不推送
        msg.setConfig(config);

        sendMessage(msg);
    }
}

package stan.reuenthal.com.xsx.IMUI;

import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.io.File;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.keyboard.PickImageAction;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class SnapChatAction extends PickImageAction {

    public SnapChatAction() {
        super(R.drawable.message_plus_snapchat_selector, R.string.input_panel_snapchat, false);
    }

    @Override
    protected void onPicked(File file) {
        SnapChatAttachment snapChatAttachment = new SnapChatAttachment();
        snapChatAttachment.setPath(file.getPath());
        snapChatAttachment.setSize(file.length());
        CustomMessageConfig config = new CustomMessageConfig();
        config.enableHistory = false;
        config.enableRoaming = false;
        config.enableSelfSync = false;
        IMMessage stickerMessage = MessageBuilder.createCustomMessage(getAccount(), getSessionType(), "阅后即焚消息", snapChatAttachment, config);
        sendMessage(stickerMessage);
    }

}

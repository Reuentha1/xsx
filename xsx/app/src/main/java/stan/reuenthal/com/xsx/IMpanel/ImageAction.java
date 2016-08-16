package stan.reuenthal.com.xsx.IMpanel;

import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.io.File;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.keyboard.PickImageAction;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class ImageAction extends PickImageAction {

    public ImageAction() {
        super(R.drawable.nim_message_plus_photo_selector, R.string.input_panel_photo, true);
    }

    @Override
    protected void onPicked(File file) {
        IMMessage message = MessageBuilder.createImageMessage(getAccount(), getSessionType(), file, file.getName());
        sendMessage(message);
    }
}


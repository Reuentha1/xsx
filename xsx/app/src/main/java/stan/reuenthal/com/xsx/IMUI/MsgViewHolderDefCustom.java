package stan.reuenthal.com.xsx.IMUI;

import stan.reuenthal.com.xsx.keyboard.MsgViewHolderText;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class MsgViewHolderDefCustom extends MsgViewHolderText {

    @Override
    protected String getDisplayText() {
        DefaultCustomAttachment attachment = (DefaultCustomAttachment) message.getAttachment();

        return "type: " + attachment.getType() + ", data: " + attachment.getContent();
    }
}

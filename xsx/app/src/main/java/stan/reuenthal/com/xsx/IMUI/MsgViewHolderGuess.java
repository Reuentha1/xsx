package stan.reuenthal.com.xsx.IMUI;

import stan.reuenthal.com.xsx.keyboard.MsgViewHolderText;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class MsgViewHolderGuess extends MsgViewHolderText {

    @Override
    protected String getDisplayText() {
        GuessAttachment attachment = (GuessAttachment) message.getAttachment();

        return attachment.getValue().getDesc() + "!";
    }
}

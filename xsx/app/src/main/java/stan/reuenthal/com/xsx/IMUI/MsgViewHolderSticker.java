package stan.reuenthal.com.xsx.IMUI;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import stan.reuenthal.com.xsx.IMpanel.MsgViewHolderThumbBase;
import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.keyboard.MsgViewHolderBase;
import stan.reuenthal.com.xsx.keyboard.ScreenUtil;
import stan.reuenthal.com.xsx.keyboard.StickerManager;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class MsgViewHolderSticker extends MsgViewHolderBase {

    private ImageView baseView;

    @Override
    protected int getContentResId() {
        return R.layout.nim_message_item_sticker;
    }

    @Override
    protected void inflateContentView() {
        baseView = findViewById(R.id.message_item_sticker_image);
        baseView.setMaxWidth(MsgViewHolderThumbBase.getImageMaxEdge());
    }

    @Override
    protected void bindContentView() {
        StickerAttachment attachment = (StickerAttachment) message.getAttachment();
        if (attachment == null) {
            return;
        }

        ImageLoader.getInstance().displayImage(StickerManager.getInstance().getStickerBitmapUri(attachment.getCatalog
                (), attachment.getChartlet()), baseView, StickerManager.getInstance().getStickerImageOptions
                (ScreenUtil.dip2px(R.dimen.mask_sticker_bubble_width)));
    }

    @Override
    protected int leftBackground() {
        return 0;
    }

    @Override
    protected int rightBackground() {
        return 0;
    }
}

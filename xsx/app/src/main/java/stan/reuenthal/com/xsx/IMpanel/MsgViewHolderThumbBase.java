package stan.reuenthal.com.xsx.IMpanel;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.attachment.VideoAttachment;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;

import java.io.File;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.keyboard.MsgViewHolderBase;
import stan.reuenthal.com.xsx.keyboard.ScreenUtil;
import stan.reuenthal.com.xsx.keyboard.StringUtil;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public abstract class MsgViewHolderThumbBase extends MsgViewHolderBase {

    protected MsgThumbImageView thumbnail;
    protected View progressCover;
    protected TextView progressLabel;

    @Override
    protected void inflateContentView() {
        thumbnail = findViewById(R.id.message_item_thumb_thumbnail);
        progressBar = findViewById(R.id.message_item_thumb_progress_bar); // 覆盖掉
        progressCover = findViewById(R.id.message_item_thumb_progress_cover);
        progressLabel = findViewById(R.id.message_item_thumb_progress_text);
    }

    @Override
    protected void bindContentView() {
        FileAttachment msgAttachment = (FileAttachment) message.getAttachment();
        String path = msgAttachment.getPath();
        String thumbPath = msgAttachment.getThumbPath();
        if (!TextUtils.isEmpty(thumbPath)) {
            loadThumbnailImage(thumbPath);
        } else if (!TextUtils.isEmpty(path)) {
            loadThumbnailImage(thumbFromSourceFile(path));
        } else {
            loadThumbnailImage(null);
            if (message.getAttachStatus() == AttachStatusEnum.transferred
                    || message.getAttachStatus() == AttachStatusEnum.def) {
                downloadAttachment();
            }
        }

        refreshStatus();
    }

    private void refreshStatus() {
        FileAttachment attachment = (FileAttachment) message.getAttachment();
        if (TextUtils.isEmpty(attachment.getPath()) && TextUtils.isEmpty(attachment.getThumbPath())) {
            if (message.getAttachStatus() == AttachStatusEnum.fail || message.getStatus() == MsgStatusEnum.fail) {
                alertButton.setVisibility(View.VISIBLE);
            } else {
                alertButton.setVisibility(View.GONE);
            }
        }

        if (message.getStatus() == MsgStatusEnum.sending || message.getAttachStatus() == AttachStatusEnum.transferring) {
            progressCover.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressCover.setVisibility(View.GONE);
        }
        progressLabel.setText(StringUtil.getPercentString(getAdapter().getProgress(message)));
    }

    private void loadThumbnailImage(String thumbPath) {
        setImageSize(thumbPath);
        if (thumbPath != null) {
            thumbnail.loadAsPath(thumbPath, getImageMaxEdge(), getImageMaxEdge(), maskBg());
        } else {
            thumbnail.loadAsResource(R.mipmap.nim_image_default, 30, 20, maskBg());
        }
    }

    private void setImageSize(String thumbPath) {
        int[] bounds = null;
        if (thumbPath != null) {
            bounds = stan.reuenthal.com.xsx.IMpanel.BitmapDecoder.decodeBound(new File(thumbPath));
        }
        if (bounds == null) {
            if (message.getMsgType() == MsgTypeEnum.image) {
                ImageAttachment attachment = (ImageAttachment) message.getAttachment();
                bounds = new int[]{attachment.getWidth(), attachment.getHeight()};
            } else if (message.getMsgType() == MsgTypeEnum.video) {
                VideoAttachment attachment = (VideoAttachment) message.getAttachment();
                bounds = new int[]{attachment.getWidth(), attachment.getHeight()};
            }
        }

        if (bounds != null) {
            ImageUtil.ImageSize imageSize = ImageUtil.getThumbnailDisplaySize(bounds[0], bounds[1], getImageMaxEdge(), getImageMinEdge());
            setLayoutParams(imageSize.width, imageSize.height, thumbnail);
        }
    }

    private int maskBg() {
        return R.drawable.nim_message_item_round_bg;
    }
    public static int getImageMaxEdge() {
        return (int) (165.0 / 320.0 * ScreenUtil.screenWidth);
    }

    public static int getImageMinEdge() {
        return (int) (76.0 / 320.0 * ScreenUtil.screenWidth);
    }

    protected abstract String thumbFromSourceFile(String path);
}

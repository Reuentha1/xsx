package stan.reuenthal.com.xsx.IMpanel;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public interface IEmoticonSelectedListener {
    void onEmojiSelected(String key);

    void onStickerSelected(String categoryName, String stickerName);
}

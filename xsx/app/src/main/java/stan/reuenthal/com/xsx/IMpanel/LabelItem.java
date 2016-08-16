package stan.reuenthal.com.xsx.IMpanel;

import stan.reuenthal.com.xsx.keyboard.AbsContactItem;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class LabelItem extends AbsContactItem {
    private final String text;

    public LabelItem(String text) {
        this.text = text;
    }

    @Override
    public int getItemType() {
        return ItemTypes.LABEL;
    }

    @Override
    public String belongsGroup() {
        return null;
    }

    public final String getText() {
        return text;
    }
}
